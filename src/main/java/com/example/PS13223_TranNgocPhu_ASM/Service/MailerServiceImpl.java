package com.example.PS13223_TranNgocPhu_ASM.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.example.PS13223_TranNgocPhu_ASM.Model.Mailinfor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service

public class MailerServiceImpl implements MailerService {

    @Autowired
    JavaMailSender sender;

    @Override
    public void send(Mailinfor mail) throws MessagingException {
        // TODO mã nguồn gửi email đặt ở đây
        // Tạo message
        MimeMessage message = sender.createMimeMessage();
        // Sử dụng Helper để thiết lập các thông tin cần thiết cho message
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody(), true);
        helper.setReplyTo(mail.getFrom());
        String[] cc = mail.getCc();
        if (cc != null && cc.length > 0) {
            helper.setCc(cc);
        }
        String[] bcc = mail.getBcc();
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }
        String[] attachments = mail.getAttachments();
        if (attachments != null && attachments.length > 0) {
            for (String path : attachments) {
                File file = new File(path);
                helper.addAttachment(file.getName(), file);
            }
        }
        // Gửi message đến SMTP server
        sender.send(message);

    }

    @Override
    public void send(String to, String subject, String body) throws MessagingException {
        this.send(new Mailinfor(to, subject, body));
    }

    List<Mailinfor> list = new ArrayList<>();

    @Override
    public void queue(Mailinfor mail) {
        // TODO Auto-generated method stub
        list.add(mail);

    }

    @Override
    public void queue(String to, String subject, String body) {
        // TODO Auto-generated method stub
        queue(new Mailinfor(to, subject, body));

    }

    @Scheduled(fixedDelay = 5000)
    public void run() {
        while (!list.isEmpty()) {
            Mailinfor mail = list.remove(0);
            try {
                this.send(mail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

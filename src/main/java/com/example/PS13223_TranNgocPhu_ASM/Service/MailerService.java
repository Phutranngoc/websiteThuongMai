package com.example.PS13223_TranNgocPhu_ASM.Service;

import javax.mail.MessagingException;

import com.example.PS13223_TranNgocPhu_ASM.Model.Mailinfor;

public interface MailerService {
    /**
	 * Gửi email
	 * @param mail thông tin email
	 * @throws MessagingException lỗi gửi email
	 */
	void send(Mailinfor mail) throws MessagingException;
	/**
	 * Gửi email đơn giản
	 * @param to email người nhận
	 * @param subject tiêu đề email
	 * @param body nội dung email
	 * @throws MessagingException lỗi gửi email
	 */
	void send(String to, String subject, String body) throws MessagingException;

/**
 * Xếp mail vào hàng đợi
 * @param mail thông tin email
 */	
void queue(Mailinfor mail);
/**
 * Tạo MailInfo và xếp vào hàng đợi
 * @param to email người nhận
 * @param subject tiêu đề email
 * @param body nội dung email
 */	
void queue(String to, String subject, String body);
}

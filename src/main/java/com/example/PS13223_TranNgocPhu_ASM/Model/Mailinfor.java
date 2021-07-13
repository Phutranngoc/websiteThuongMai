package com.example.PS13223_TranNgocPhu_ASM.Model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mailinfor {
    String from;
	String to;
	String[] cc;
	String[] bcc;
	String subject;
	String body;
	String[] attachments;
	public Mailinfor(String to, String subject, String body) {
		super();
		this.from = "phutnps13223@fpt.edu.vn";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}

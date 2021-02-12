package member.model.vo;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSend {
	public String mailSend(String email, String n_pw) {
		
//		final String user = "kokoder@hs.ac.kr";
//		final String password = "password";
//		
//		Properties prop = new Properties();
//		prop.put("mail.smtp.host", "smtp.gmail.com");
//		prop.put("mail.smtp.port", 465);
//		prop.put("mail.smtp.auth", "true");
//		prop.put("mail.smtp.ssl.enable","true");
//		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//		
//		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(user, password);
//			}
//		});
//		
//		MimeMessage message = new MimeMessage(session);
//		try {
//			message.setFrom(new InternetAddress(user));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//			message.setSubject("Study With Us 임시 비밀번호 발급");
//			message.setText("<h1>안녕하세요 임시 비밀번호 입니다. 임시 비밀번호는 ["+n_pw+"] 입니다.</h1>","text/html;charset=UTF-8");
//			Transport.send(message);
//			System.out.print("전송성공");
//		} catch (AddressException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// 랜덤 코드 생성
		// ↓ 메일 전송 코드
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable","true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		// ↓ Gmail 계정 및 Password 정보가 들어가는 곳 
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("kokoder@hs.ac.kr", "rlgus0214!");
			}
		});
		// ↓ 실제 메일 작성
		MimeMessage msg = new MimeMessage(session);
		
	
			try {
				msg.setSentDate(new Date()); //보내는 시간 설정 
				msg.setFrom(new InternetAddress("kokoder@hs.ac.kr","조기현")); //보내는 사람 설정
				InternetAddress to = new InternetAddress(email); // 수신인 설정
				msg.setRecipient(Message.RecipientType.TO, to); // 수신인 설정 
				msg.setSubject("Study With Us 임시 비밀번호 발급."); // 제목
				msg.setContent("<h1>안녕하세요 임시 비밀번호 입니다. 임시 비밀번호는 ["+n_pw+"] 입니다.</h1>","text/html;charset=UTF-8");
				Transport.send(msg); // 전송
			} catch (javax.mail.MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return n_pw;
	}
}

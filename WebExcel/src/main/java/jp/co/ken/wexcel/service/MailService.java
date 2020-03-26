package jp.co.ken.wexcel.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jp.co.ken.wexcel.dao.WebTableDao;
import jp.co.ken.wexcel.entity.WebTable;

public class MailService {

	public void sendMail() throws MessagingException, UnsupportedEncodingException{
		Properties prop = new Properties();
		prop.put("mail.smtp.host","mail.ken-first.com"); // SMTPサーバ名
		prop.put("mail.host","mail.ken-first.com"); // 接続するホスト名
		prop.put("mail.smtp.port", "587");	// SMTPポートを設定
		prop.put("mail.smtp.auth", "true" );	// 認証を有効に設定
		// メールセッションを確立
		Session session = Session.getInstance( prop, new myAuth());
		// 送信メッセージを生成
		MimeMessage objMsg = new MimeMessage(session);
		// 送信先（TOのほか、CCやBCCも設定可能）
		objMsg.setRecipients(Message.RecipientType.TO,"amano@ken-first.com");
		// Fromヘッダ
		InternetAddress objFrm = new InternetAddress("amano");
		objMsg.setFrom(objFrm);
		// 件名
		objMsg.setSubject("メールテスト","ISO-2022-JP");
		// 本文
		objMsg.setText("こんにちは","ISO-2022-JP");
		// メール送信
		Transport.send(objMsg);
	}

	 class myAuth extends Authenticator {
	      protected PasswordAuthentication getPasswordAuthentication(){
	          return new PasswordAuthentication("amano","amano");
	      }
	  }
}

package xyz.lhweb.furns.utils;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SendEmail {


    public static void sendMail(String to, String code) throws Exception {
        Properties properties = new Properties();
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("service@store.com", "root");
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("service@store.com"));
        message.setRecipient(RecipientType.TO, new InternetAddress(to));
        message.setSubject("来自XX网站的激活邮件");
        message.setContent(
                "<h1>来自罗汉家居网站的激活邮件，点击</h1><h3><a href='http://localhost:8888/regist/ActiveServlet?code="
                        + code
                        + "'>http://localhost:8080/regist/ActiveServlet?code="
                        + code + "</a></h3>", "text/html;charset=utf-8");
        Transport.send(message);
    }

}
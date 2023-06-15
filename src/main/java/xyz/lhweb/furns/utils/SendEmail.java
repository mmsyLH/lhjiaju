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


/**
 * 发送电子邮件
 *
 * @author 罗汉
 * @date 2023/06/07
 */
public class SendEmail {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            sendMail("2425450942@qq.com","110");

        }
    }

    /**
     * 发送邮件
     *
     * @param to   收件人邮箱地址
     * @param code 激活码
     * @throws Exception 异常
     */
    public static void sendMail(String to, String code) throws Exception {
        /**
         * @param Receive： -->> 收件人
         * @param Title -->> 标题
         * @param Content -->> 内容
         */
        String Receive = to;

        String Title = "罗汉家居激活验证";

        Properties properties = new Properties();
        //协议
        properties.put("mail.transport.protocol", "smtp");
        //服务器
        properties.put("mail.smtp.host", "smtp.qq.com");
        //端口
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.auth", "true");
        //SSL加密传输
        properties.put("mail.smtp.ssl.enable", "true");
        //true-->>在控制台显示信息，false-->>不显示
        properties.put("mail.debug", "false");
        //得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        //发件人邮箱
        message.setFrom(new InternetAddress("1072344372@qq.com"));
        //设置收件人
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[] { new InternetAddress(Receive) });
        //邮箱标题
        message.setSubject(Title);
        //邮箱内容
        message.setContent(
                "<h1>来自罗汉家居网站的激活邮件，点击即可进行激活</h1>" +
                        "<h3><a href='http://localhost:8888/2203840110_luohan/memberServlet?action=active&code="
                        + code
                        + "'>罗汉家居网在线激活~"
                    + "</a></h3>", "text/html;charset=utf-8");
        //创建对象
        Transport transport = session.getTransport();
        //连接服务
        /**
         * @param 第一个参数为自己QQ邮箱
         * @param 第二个参数为授权码
         */
        transport.connect("1072344372@qq.com", "jaaijktbnnwfbdie");
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        //关闭服务
        transport.close();
    }
}
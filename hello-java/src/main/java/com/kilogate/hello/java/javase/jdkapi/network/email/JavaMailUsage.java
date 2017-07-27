package com.kilogate.hello.java.javase.jdkapi.network.email;

import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

/**
 * JavaMail 用法
 *
 * @author fengquanwei
 * @create 2017/7/25 23:04
 **/
public class JavaMailUsage {
    public static Logger logger = LoggerFactory.getLogger(JavaMailUsage.class);

    public static void main(String[] args) throws MessagingException {
        String username = "quqingruxu@163.com";
        String password = "******"; // 密码不提交到 git
        String from = "quqingruxu@163.com";
        String to = "kilogate@163.com";
        String subject = "测试邮件";
        String text = "这是一封测试邮件的正文部分";

        Properties properties = new Properties();
        properties.setProperty("mail.debug", "true"); // debug
        properties.setProperty("mail.transport.protocol", "smtp"); // 邮件协议
        properties.setProperty("mail.host", "smtp.163.com"); // 服务器主机
        properties.setProperty("mail.smtp.auth", "true"); // 认证

        MailSSLSocketFactory mailSSLSocketFactory = null;
        try {
            mailSSLSocketFactory = new MailSSLSocketFactory();
            mailSSLSocketFactory.setTrustAllHosts(true);
        } catch (GeneralSecurityException e) {
            logger.error("Java Mail 开启 SSL 异常", e);
        }
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);

        Session session = Session.getDefaultInstance(properties, new MyAuthenticator(username, password));

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setSentDate(new Date());

        message.setText(text);
        message.setContent(text, "text/html;charset=utf-8");

        message.saveChanges();

        Transport.send(message);
    }
}

class MyAuthenticator extends Authenticator {
    private String username;
    private String password;

    public MyAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}

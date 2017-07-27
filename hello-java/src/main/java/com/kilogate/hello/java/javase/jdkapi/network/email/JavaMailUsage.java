package com.kilogate.hello.java.javase.jdkapi.network.email;

import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
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
        // 环境
        Properties properties = new Properties();
        properties.setProperty("mail.debug", "true"); // debug
        properties.setProperty("mail.transport.protocol", "smtp"); // 邮件协议
        properties.setProperty("mail.host", "smtp.163.com"); // 服务器主机
        properties.setProperty("mail.smtp.auth", "true"); // 认证

        // 开启 SSL
        MailSSLSocketFactory mailSSLSocketFactory = null;
        try {
            mailSSLSocketFactory = new MailSSLSocketFactory();
            mailSSLSocketFactory.setTrustAllHosts(true);
        } catch (GeneralSecurityException e) {
            logger.error("Java Mail 开启 SSL 异常", e);
        }
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);

        // 正文 html
        MimeBodyPart html = new MimeBodyPart();
        html.setContent("<span style='color:red'>示例图片如下</span><img src=\"http://www.kilogate.com/pic/IdcardHold.jpeg\">", "text/html;charset=utf-8");

        // 正文 image
        MimeBodyPart image = new MimeBodyPart();
        DataSource imageDataSource = new FileDataSource(new File("/Users/kilogate/Pictures/IdcardHold.jpeg"));
        DataHandler imageDataHandler = new DataHandler(imageDataSource);
        image.setDataHandler(imageDataHandler);
        image.setHeader("Content-Location", "http://www.kilogate.com/pic/IdcardHold.jpeg");

        // 正文内容
        MimeMultipart content = new MimeMultipart("related");
        content.addBodyPart(html);
        content.addBodyPart(image);

        // 正文
        MimeBodyPart body = new MimeBodyPart();
        body.setContent(content); // 设置正文内容

        // 附件一
        MimeBodyPart attachment1 = new MimeBodyPart();
        DataSource dataSource1 = new FileDataSource(new File("/opt/log/blp/statistics.log"));
        DataHandler dataHandler1 = new DataHandler(dataSource1);
        attachment1.setDataHandler(dataHandler1);
        attachment1.setFileName("statistics.log");

        // 附件二
        MimeBodyPart attachment2 = new MimeBodyPart();
        DataSource dataSource2 = new FileDataSource(new File("/Users/kilogate/Pictures/IdcardHold.jpeg"));
        DataHandler dataHandler2 = new DataHandler(dataSource2);
        attachment2.setDataHandler(dataHandler2);
        attachment2.setFileName("IdcardHold.jpeg");

        // 邮件消息体
        MimeMultipart mimeMultipart = new MimeMultipart("mixed"); // 组合关系：混合
        mimeMultipart.addBodyPart(body); // 添加正文内容
        mimeMultipart.addBodyPart(attachment1); // 添加附件一
        mimeMultipart.addBodyPart(attachment2); // 添加附件二

        // 服务器认证
        Session session = Session.getDefaultInstance(properties, new MyAuthenticator("quqingruxu@163.com", "auth4client"));

        // 邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress("quqingruxu@163.com")); // From
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("kilogate@163.com")); // To
        mimeMessage.setSubject("测试邮件"); // 主题
        mimeMessage.setSentDate(new Date()); // 发送日期
        // 设置邮件消息体
        mimeMessage.setContent(mimeMultipart);

        // 生成邮件
        mimeMessage.saveChanges();

        // 发送
        Transport.send(mimeMessage);
    }
}

/**
 * 认证
 */
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

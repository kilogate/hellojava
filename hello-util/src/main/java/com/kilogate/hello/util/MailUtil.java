package com.kilogate.hello.util;

import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 * 发邮件工具类
 *
 * @author fengquanwei
 * @create 2017/7/27 16:15
 **/
public class MailUtil {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MailUtil.class);

    private static String USERNAME = "quqingruxu@163.com"; // 用户名
    private static String PASSWORD = "auth4client"; // 密码
    private static String FROM = "quqingruxu@163.com"; // 发件地址

    private static Properties properties = null;

    static {
        properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", "smtp.163.com");
        properties.setProperty("mail.smtp.auth", "true");
        //开启安全协议
        MailSSLSocketFactory mailSSLSocketFactory = null;
        try {
            mailSSLSocketFactory = new MailSSLSocketFactory();
            mailSSLSocketFactory.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);
    }

    /**
     * 发送邮件
     *
     * @param recipients 收件人列表
     * @param subject    主题
     * @param html       正文
     * @param filepaths  附件文件路径列表
     */
    public static void send(String[] recipients, String subject, String html, String[] filepaths) {
        try {
            // 消息体
            MimeMultipart mimeMultipart = new MimeMultipart("mixed");

            // 正文
            MimeBodyPart body = new MimeBodyPart();
            body.setContent(html, "text/html;charset=utf-8");
            mimeMultipart.addBodyPart(body);

            // 附件
            if (filepaths != null && filepaths.length > 0) {
                for (String filepath : filepaths) {
                    File file = new File(filepath);
                    if (file.exists()) {
                        MimeBodyPart attachment = new MimeBodyPart();
                        DataSource attachmentDataSource = new FileDataSource(file);
                        DataHandler attachmentDataHandler = new DataHandler(attachmentDataSource);
                        attachment.setDataHandler(attachmentDataHandler);
                        attachment.setFileName(MimeUtility.encodeText(file.getName(), "UTF-8", null));
                        mimeMultipart.addBodyPart(attachment);
                    }
                }
            }

            // 服务器认证
            Session session = Session.getDefaultInstance(properties, new MyAuthenticator(USERNAME, PASSWORD));
            session.setDebug(true);

            // 邮件
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(FROM));
            if (recipients != null && recipients.length > 0) {
                for (String recipient : recipients) {
                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                }
            }
            mimeMessage.setSubject(subject, "UTF-8");
            mimeMessage.setSentDate(new Date());

            // 设置邮件消息体
            mimeMessage.setContent(mimeMultipart);

            // 生成邮件
            mimeMessage.saveChanges();

            // 发送
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("发送邮件异常，recipients：{}，subject：{}，html：{}，filepaths：{}", Arrays.toString(recipients), subject, html, Arrays.toString(filepaths), e);
        } catch (UnsupportedEncodingException e) {
            logger.error("发送邮件异常，recipients：{}，subject：{}，html：{}，filepaths：{}", Arrays.toString(recipients), subject, html, Arrays.toString(filepaths), e);
        }
    }

    /**
     * 测试邮件发送
     */
    public static void main(String[] args) {
        MailUtil.send(new String[]{"fengquanwei1996@163.com"}, "测试邮件", "", new String[]{"/Users/fengquanwei/abc.txt"});
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
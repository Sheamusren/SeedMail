package util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.huateng.topbpm.common.StringUtil;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class SendEmail {
	private static String ALIDM_SMTP_HOST = "smtp.";
    private static final int ALIDM_SMTP_PORT = 25;
    /**
     * To HR
     * @param addresser
     * @param recipient
     * @param password
     * @param emailSuffix
     * @param copyPerson
     * @param emailContent
     * @param cino
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
	public static void sendEmailHR(String addresser ,String recipient,
    		String password,String emailSuffix, String[] copyPerson ,String emailContent,String cino) throws MessagingException, UnsupportedEncodingException{
    	 // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST + emailSuffix);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        // 发件人的账号
        props.put("mail.user", addresser);
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", password);
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人
        InternetAddress to = new InternetAddress(recipient);
        message.setRecipient(MimeMessage.RecipientType.TO, to);
        // 抄送人信息   注：此处可写成循环形式
        if(copyPerson != null){
        	StringBuilder sendPerson = new StringBuilder();
        	for(String address : copyPerson){
        		sendPerson.append(address).append(",");
        	}
        	message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(sendPerson.substring(0,sendPerson.lastIndexOf(",")).toString()));
        }
        // 设置邮件标题
        message.setSubject("借款人信息调查-"+ cino);
        //message.setSubject(MimeUtility.encodeText("借款人信息调查-"+ cino, "GBK", "B"));
        //message.setSubject("=?UTF-8?B?" + Base64.encode(("借款人信息调查-" + cino).getBytes("UTF-8")) + "?=");
        //message.setSubject(new String("借款人信息调查-".getBytes("utf-8"),"utf-8")+ cino);
        // 设置邮件的内容体
        StringBuffer emailMsg = new StringBuffer();
        emailMsg.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
        .append("<html>")
        .append("<head>")
        .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")
        .append("<title>华钦员工贷借款人信息调查</title>")
        .append("<style type=\"text/css\">")
        .append("p{ text-indent:4em;}")
        .append("</style>")
        .append("</head>")
        .append("<body>")
        .append(emailContent)
        .append("</body>")
        .append("</html>");
        Multipart multipart = new MimeMultipart();
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(emailMsg.toString(), "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        message.setContent(multipart);
        Transport.send(message);
    }
	/**
	 * To 审批人
	 * @param addresser
	 * @param recipient
	 * @param password
	 * @param emailSuffix
	 * @param copyPerson
	 * @param emailContent
	 * @param filePath
	 * @param fileName
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendEmail(String addresser ,String recipient,
    		String password,String emailSuffix, String[] copyPerson ,String emailContent,String filePath,String fileName) throws MessagingException, UnsupportedEncodingException{
    	 // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST + emailSuffix);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);   
        // 发件人的账号
        props.put("mail.user", addresser);
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", password);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人
        InternetAddress to = new InternetAddress(recipient);
        message.setRecipient(MimeMessage.RecipientType.TO, to);

        // 抄送人信息   注：此处可写成循环形式
        if(copyPerson != null){
        	StringBuilder sendPerson = new StringBuilder(); 
        	for(String address : copyPerson){
        		sendPerson.append(address).append(",");
        	}
        	message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(sendPerson.substring(0,sendPerson.lastIndexOf(",")).toString()));
        	
        }
        // 设置邮件标题
        //message.setSubject("华钦员工贷审批/放款通知");
        message.setSubject(MimeUtility.encodeText("华钦员工贷审批/放款通知", "GBK", "B"));
        //message.setSubject("=?UTF-8?B?" + Base64.encode("华钦员工贷审批/放款通知".getBytes("UTF-8")) + "?=");
        // 设置邮件的内容体
        StringBuffer emailMsg = new StringBuffer();
        emailMsg.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
        .append("<html>")  
        .append("<head>")  
        .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")  
        .append("<title>华钦员工贷审批/放款通知</title>")
        .append("<style type=\"text/css\">")
        .append("p{ text-indent:4em;}")
        .append("</style>")
        .append("</head>")  
        .append("<body>")
        .append(emailContent)
        .append("</body>")  
        .append("</html>");

        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();         
        //   设置邮件的文本内容
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(emailMsg.toString(), "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        //添加附件
        if(StringUtil.isNotBlank(filePath) && StringUtil.isNotBlank(fileName)){
        	message.setSubject("钦钦贷放款通知");
	        BodyPart messageBodyPart= new MimeBodyPart();
	        
	        File sendFile = new File(filePath);
	        if(!sendFile.exists()){
	        	sendFile.mkdirs();
	        }
	        DataSource source = new FileDataSource(new File(filePath + "/"+ fileName));
	        //添加附件的内容
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        //添加附件的标题
	        //这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
	        sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
	        try {
	        	fileName = MimeUtility.encodeText(fileName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	        fileName = fileName.replaceAll("\r", "").replaceAll("\n", "");
	        messageBodyPart.setFileName(fileName);
	        multipart.addBodyPart(messageBodyPart);
        } else {
        	 message.setSubject("钦钦贷审批通知");
        }
        //将multipart对象放到message中
        message.setContent(multipart);
        // 发送邮件
        Transport.send(message);
    }
}

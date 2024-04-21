package tn.encar.gestnotes.services.impl;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.*;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    Configuration config ;
    
@Async
    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bey.asma22@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");


    }
/*to send maybe a certification*/
@Async
public void sendEmailWithAttachment(String toEmail,
                                        String body,
                                        String subject,
                                        String attachment
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("bey.asma22@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystemResource=
                new FileSystemResource( new File(attachment));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),
                fileSystemResource
        );
        mailSender.send(mimeMessage);
        System.out.println("Mail with attachment sent..");


    }
    @Async
    public void sendEmail(String toEmail,
                          String body,
                          String subject,int cin , String password1, String role) {
    	
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessage message = mailSender.createMimeMessage();

        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // add attachment
            //helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

            Template t = config.getTemplate("email-template.ftl");
            Map<String, Object> model=new HashMap<>();
            model.put("cin",cin);
            model.put("password",password1);
            model.put("role", role);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t,model);

            helper.setTo(toEmail);
            helper.setText(html, true);
            helper.setSubject(subject);
            helper.setFrom("bey.asma22@gmail.com");
            mailSender.send(message);



        } catch (MessagingException | IOException | TemplateException e) {
            System.out.println("Mail Sending failure : "+e.getMessage());
        }

    }
    @Async
    public void sendEmailConfirm(String toEmail,
                          String body,
                          String subject,String name1 , String password1) {
    	String img = "email.gif";
    	ClassPathResource imgFile = new ClassPathResource("email1.gif");

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessage message = mailSender.createMimeMessage();

        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // add attachment
            helper.addAttachment("email1.gif", new ClassPathResource("email1.gif"));

            Template t = config.getTemplate("recupererPassEmail.ftl");
            Map<String, Object> model=new HashMap<>();
            model.put("name",name1);
            model.put("password",password1);
            model.put("img",imgFile);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t,model);

            helper.setTo(toEmail);
            helper.setText(html, true);
            helper.setSubject(subject);
            helper.setFrom("bey.asma22@gmail.com");
            mailSender.send(message);



        } catch (MessagingException | IOException | TemplateException e) {
            System.out.println("Mail Sending failure : "+e.getMessage());
        }

    }    
}

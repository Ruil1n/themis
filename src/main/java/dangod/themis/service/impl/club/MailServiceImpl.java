package dangod.themis.service.impl.club;

import com.alibaba.fastjson.JSON;
import dangod.themis.service.club.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Async
    @Override
    public Integer sendMessage(String to, String subject, String content) {
        return sendMail(to, subject, content, null);
    }
    @Value("${spring.mail.username}")
    private String username;

    private Integer sendMail(String to, String subject, String content, String[]carbonCopy) {
        int status = 0;
        try {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            mimeMessage.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(username));
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "utf-8");
            message.setFrom(username);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content,true);
            this.mailSender.send(mimeMessage);
            System.out.println("发送成功");
        }
        catch(Exception e) {
            logger.warn("邮件发送失败");
            e.printStackTrace();
            status = -1;
        }
        return status;
    }
}

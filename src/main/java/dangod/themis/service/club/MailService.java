package dangod.themis.service.club;

public interface MailService {

    Integer sendMessage(String to, String subject, String content);

}

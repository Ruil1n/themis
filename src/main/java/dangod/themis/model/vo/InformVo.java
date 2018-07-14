package dangod.themis.model.vo;


import dangod.themis.model.po.common.Inform;

import java.text.SimpleDateFormat;
public class InformVo {
    private long informId;
    private long userId;
    private String title;
    private String content;
    private String date;
    private String modified;
    private String author;

    public InformVo() {
    }

    public InformVo(Inform inform, String author) {
        this.informId = inform.getId();
        this.userId = inform.getUser().getId();
        this.title = inform.getTitle();
        this.content = inform.getContent();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        this.date = sdf.format(inform.getDate());
        if(inform.getModified() != null)
            this.modified = sdf.format(inform.getModified());
        else
            this.modified = this.date;
        if(author == null) this.author = "未知";
        else this.author = author;
    }

    public InformVo(long informId, long userId, String title, String content, String date, String modified, String author) {
        this.informId = informId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.modified = modified;
        this.author = author;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getInformId() {
        return informId;
    }

    public void setInformId(long informId) {
        this.informId = informId;
    }
}

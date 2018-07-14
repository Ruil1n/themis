package dangod.themis.model.vo.club;

import dangod.themis.model.po.club.ClubFile;

import java.io.File;

public class ClubFileVo {
    private String fileName;
    private String originName;
    private String path;
    private long applicationId;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public ClubFileVo() {
    }
    public ClubFileVo(ClubFile clubFile) {
        this.fileName = clubFile.getFileName();
        this.originName = clubFile.getOriginName();
        this.path = clubFile.getPath().replaceAll("[/\\\\]", File.separator);
        this.applicationId = clubFile.getApplication().getId();
    }
}

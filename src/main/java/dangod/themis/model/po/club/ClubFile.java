package dangod.themis.model.po.club;

import javax.persistence.*;

@Entity
@Table(name = "club_file")
public class ClubFile {
    @Id
    @GeneratedValue
    private long id;
    private String fileName;
    private String originName;
    private String path;
    @OneToOne(fetch= FetchType.LAZY, cascade= CascadeType.DETACH)
    @JoinColumn(name="application_id",nullable=true)
    private Application application;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public ClubFile() {
    }

    public ClubFile(String fileName, String originName, String path, Application application) {
        this.fileName = fileName;
        this.originName = originName;
        this.path = path;
        this.application = application;
    }
}

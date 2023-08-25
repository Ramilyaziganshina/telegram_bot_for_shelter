package skypro.teamwork.telegram_bot_for_shelter.model.pet;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * эта модель описывает таблицу photo_pets_report
 */
@Entity
@Table(name = "photo_pets_report")
public class PhotoPetReport {
    @Id
    @GeneratedValue(generator = "photo_pets_report_seq")
    private long id;
    private String filePath;
    private long fileSize;
    private String mediaType;

    /**
     * уменьшенная версия фото
     */
    @Lob
    private byte[] data;

    /**
     * к каждому отчету по питомцу прикреплена одна фотография, зависимость OneToOne
     */
    @OneToOne
    @JoinColumn(name = "pets_Report_id")
    private ReportPet reportPet;

    public PhotoPetReport() {
    }

    public PhotoPetReport(Long id, String filePath, byte[] data, String mediaType, long fileSize, ReportPet reportPet) {
        this.id = id;
        this.filePath = filePath;
        this.data = data;
        this.mediaType = mediaType;
        this.fileSize = fileSize;
        this.reportPet = reportPet;
    }

    public PhotoPetReport(Long id, String filePath, long fileSize, ReportPet reportPet) {
        this.id = id;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.reportPet = reportPet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public ReportPet getReportPet() {
        return reportPet;
    }

    public void setReportPet(ReportPet reportPet) {
        this.reportPet = reportPet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoPetReport)) return false;
        PhotoPetReport that = (PhotoPetReport) o;
        return id == that.id && fileSize == that.fileSize && Objects.equals(filePath, that.filePath) && Objects.equals(mediaType, that.mediaType) && Arrays.equals(data, that.data) && Objects.equals(reportPet, that.reportPet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, fileSize, mediaType, reportPet);
    }

    @Override
    public String toString() {
        return "PhotoPetReport{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", reportPet=" + reportPet +
                '}';
    }
}
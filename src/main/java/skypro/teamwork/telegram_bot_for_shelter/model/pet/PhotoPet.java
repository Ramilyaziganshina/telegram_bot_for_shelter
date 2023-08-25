package skypro.teamwork.telegram_bot_for_shelter.model.pet;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * это модель для таблицы photo_pet
 */
@Entity
@Table(name = "photo_pet")
public class PhotoPet {
    @Id
    @GeneratedValue(generator = "photo_pet_seq")
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
     * к каждому питомцу прикреплено одно фото, зависимость OneToOne
     */
    @OneToOne
    private Pet pet;

    public PhotoPet() {
    }

    public PhotoPet(Long id, String filePath, String mediaType, long fileSize, Pet pet) {
        this.id = id;
        this.filePath = filePath;
        this.mediaType = mediaType;
        this.fileSize = fileSize;
        this.pet = pet;
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoPet)) return false;
        PhotoPet photoPets = (PhotoPet) o;
        return id == photoPets.id && fileSize == photoPets.fileSize && Objects.equals(filePath, photoPets.filePath) && Objects.equals(mediaType, photoPets.mediaType) && Arrays.equals(data, photoPets.data) && Objects.equals(pet, photoPets.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, fileSize, mediaType, pet);
    }

    @Override
    public String toString() {
        return "PhotoPets{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", pet=" + pet +
                '}';
    }
}
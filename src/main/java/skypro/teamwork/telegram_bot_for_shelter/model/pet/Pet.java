package skypro.teamwork.telegram_bot_for_shelter.model.pet;


import skypro.teamwork.telegram_bot_for_shelter.model.user.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * Этот класс реализует сущность питомец, описывает таблицу pet
 */
@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(generator = "pet_seq")
    private Long id;
    private String animal;
    private String petPassport;
    private String name;

    /**
     * у каждого питомца в базе есть одно фото, зависимость OneToOne
     * привязка фото к id питомца в колонке id в таблице photo_pet
     */
    @OneToOne
    @JoinColumn(name = "photo_pet_id")
    private PhotoPet photoPet;
    private Date dateOfAdoption;
    private String description;

    /**
     * у одного питомца должны быть множество отчетов от усыновителя,
     * присланных в период испытательного срока, зависимость OneToMany
     */
    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY)
    private Collection<ReportPet> reportPets;

    /**
     * множество питомцев может быть у усыновителя, зависимость ManyToOne
     * привязка коллекции питомцев к колонке id в таблице user
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Pet(Long id, String petPassport, String animal, String name, PhotoPet photoPet, String description) {
        this.id = id;
        this.petPassport = petPassport;
        this.animal = animal;
        this.name = name;
        this.photoPet = photoPet;
        this.description = description;
    }

    public Pet() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPetPassport() {
        return petPassport;
    }

    public void setPetPassport(String petPassport) {
        this.petPassport = petPassport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhotoPet getPhotoPets() {
        return photoPet;
    }

    public void setPhotoPets(PhotoPet photoPet) {
        this.photoPet = photoPet;
    }

    public Date getDateOfAdoption() {
        return dateOfAdoption;
    }

    public void setDateOfAdoption(Date dateOfAdoption) {
        this.dateOfAdoption = dateOfAdoption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<ReportPet> getReportPets() {
        return reportPets;
    }

    public void setReportPets(Collection<ReportPet> reportPets) {
        this.reportPets = reportPets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(petPassport, pet.petPassport) && Objects.equals(name, pet.name) && Objects.equals(photoPet, pet.photoPet) && Objects.equals(dateOfAdoption, pet.dateOfAdoption) && Objects.equals(description, pet.description) && Objects.equals(reportPets, pet.reportPets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petPassport, name, photoPet, dateOfAdoption, description, reportPets);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", animal='" + animal + '\'' +
                ", petPassport='" + petPassport + '\'' +
                ", name='" + name + '\'' +
                ", photoPet=" + photoPet +
                ", dateOfAdoption=" + dateOfAdoption +
                ", description='" + description + '\'' +
                ", reportPets=" + reportPets +
                ", user=" + user +
                '}';
    }
}
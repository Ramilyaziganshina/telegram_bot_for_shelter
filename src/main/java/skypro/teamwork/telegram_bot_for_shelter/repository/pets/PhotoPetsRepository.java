package skypro.teamwork.telegram_bot_for_shelter.repository.pets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.Pet;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.PhotoPet;

/**
 * Класс для хранения фотографий питомцев
 */
@Repository
public interface PhotoPetsRepository extends JpaRepository<PhotoPet, Long> {
    Pet findPhotoPetById(long catId);
}

package skypro.teamwork.telegram_bot_for_shelter.repository.pets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.Pet;

/**
 * репозиторий для хранения питомцев
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet findByPetPassport(String petPassport);
}

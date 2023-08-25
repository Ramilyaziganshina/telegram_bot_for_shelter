package skypro.teamwork.telegram_bot_for_shelter.service.service_database.pets;

import org.springframework.stereotype.Service;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.Pet;
import skypro.teamwork.telegram_bot_for_shelter.repository.pets.PetRepository;

/**
 * сервис для реализации методов для pet
 */
@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * находит в базе данных питомца по идентификатору
     *
     * @param id идентификатор питомца
     * @return возвращает найденного питомца
     */
    public Pet findPet(long id) {
        return petRepository.findById(id).orElse(null);
    }
}
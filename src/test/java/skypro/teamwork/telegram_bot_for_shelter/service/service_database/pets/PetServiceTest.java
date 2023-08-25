package skypro.teamwork.telegram_bot_for_shelter.service.service_database.pets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.Pet;
import skypro.teamwork.telegram_bot_for_shelter.repository.pets.PetRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class PetServiceTest {

    @Mock
    private PetRepository petRepository;
    private PetService petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        petService = new PetService(petRepository);
    }

    @Test
    void testFindPet() {
        Pet expectedPet = new Pet();
        expectedPet.setId(1L);

        when(petRepository.findById(anyLong())).thenReturn(java.util.Optional.of(expectedPet));

        Pet actualPet = petService.findPet(1L);

        assertNotNull(actualPet);
        assertEquals(expectedPet, actualPet);
    }
}
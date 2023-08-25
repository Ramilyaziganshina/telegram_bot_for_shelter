package skypro.teamwork.telegram_bot_for_shelter.service.function;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.teamwork.telegram_bot_for_shelter.service.function.button.ButtonCatService;
import skypro.teamwork.telegram_bot_for_shelter.service.function.button.ButtonDogService;
import skypro.teamwork.telegram_bot_for_shelter.service.function.button.ButtonService;
import skypro.teamwork.telegram_bot_for_shelter.service.function.button.TextVaultService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BotServiceTest {
    private static final long HAT_ID = 1111;
    private static final long MESSAGE_ID = 111;
    private static final String NAME = "tesName";

    @InjectMocks
    private BotService out;
    @Mock
    private ButtonService buttonService;
    @Mock
    private TextVaultService textVaultService;

    @Test
    void startCommandReceivedTest() {
        when(buttonService.prepareKeyboard(
                ButtonService.textButtonsAfterCommandStart,
                ButtonService.callbackQueryAfterCommandStart)).thenReturn(null);
        out.startCommandReceived(HAT_ID, NAME);
        verify(buttonService).responseStartButton(HAT_ID, "Добрый день " + NAME +
                "\nВыберите интересующий Вас приют", null);
    }

    @Test
    public void startCommandReceivedForEditMessageTest() {
        when(buttonService.prepareKeyboard(
                ButtonService.textButtonsAfterCommandStart,
                ButtonService.callbackQueryAfterCommandStart)).thenReturn(null);
        out.startCommandReceivedForEditMessage(HAT_ID, MESSAGE_ID, NAME);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, "Добрый день " + NAME +
                "\nВыберите интересующий Вас приют", null);
    }

    @Test
    public void responseOnPressButtonCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandCat,
                ButtonCatService.callbackQueryAfterCommandCat)).thenReturn(null);
        out.responseOnPressButtonCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.firstLineCat,
                null);
    }

    @Test
    public void responseOnPressButtonAboutShelterCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandAboutShelterCat,
                ButtonCatService.callbackQueryAfterCommandAboutShelterCat)).thenReturn(null);
        out.responseOnPressButtonAboutShelterCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.aboutShelterCat,
                null);
    }

    @Test
    public void responseOnPressButtonHistoryOfShelterCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupAboutShelterCat,
                ButtonCatService.callbackQueryAfterCommandGroupAboutShelterCat)).thenReturn(null);
        out.responseOnPressButtonHistoryOfShelterCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.historyOfShelterCat,
                null);
    }

    @Test
    public void responseOnPressButtonScheduleAndAddressCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupAboutShelterCat,
                ButtonCatService.callbackQueryAfterCommandGroupAboutShelterCat)).thenReturn(null);
        out.responseOnPressButtonScheduleAndAddressCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.scheduleAndAddressCat,
                null);
    }

    @Test
    public void responseOnPressButtonContactSecurityCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupAboutShelterCat,
                ButtonCatService.callbackQueryAfterCommandGroupAboutShelterCat)).thenReturn(null);
        out.responseOnPressButtonContactSecurityCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.contactSecurityCat,
                null);
    }

    @Test
    public void responseOnPressButtonRecommendationLeafyCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupAboutShelterCat,
                ButtonCatService.callbackQueryAfterCommandGroupAboutShelterCat)).thenReturn(null);
        out.responseOnPressButtonRecommendationLeafyCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.recommendationLeafyCat,
                null);
    }

    @Test
    public void responseOnPressButtonHowTakeCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandHowTakeCat)).thenReturn(null);
        out.responseOnPressButtonHowTakeCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.howToTakeCat,
                null);
    }

    @Test
    public void responseOnPressButtonDatingRulesCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat)).thenReturn(null);
        out.responseOnPressButtonDatingRulesCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.datingRulesCat,
                null);
    }

    @Test
    public void responseOnPressButtonListOfDocumentsForAdoptingCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat)).thenReturn(null);
        out.responseOnPressButtonListOfDocumentsForAdoptingCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.listOfDocumentsForAdoptingCat,
                null);
    }

    @Test
    public void responseOnPressButtonRecommendationsForTransportationCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat)).thenReturn(null);
        out.responseOnPressButtonRecommendationsForTransportationCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.recommendationsForTransportationCat,
                null);
    }

    @Test
    public void responseOnPressButtonRecommendationsForHomeDesignForLittleCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat)).thenReturn(null);
        out.responseOnPressButtonRecommendationsForHomeDesignForLittleCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.recommendationsForHomeDesignForLittleCat,
                null);
    }

    @Test
    public void responseOnPressButtonRecommendationsForHomeDesignForAdultCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat)).thenReturn(null);
        out.responseOnPressButtonRecommendationsForHomeDesignForAdultCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.recommendationsForHomeDesignForAdultCat,
                null);
    }

    @Test
    public void responseOnPressButtonRecommendationsForHomeDesignForDisabledCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat)).thenReturn(null);
        out.responseOnPressButtonRecommendationsForHomeDesignForDisabledCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.recommendationsForHomeDesignForDisabledCat,
                null);
    }

    @Test
    public void responseOnPressButtonListOfReasonForAdoptingCatTest() {
        when(buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat)).thenReturn(null);
        out.responseOnPressButtonListOfReasonForAdoptingCat(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.listOfReasonForAdoptingCat,
                null);
    }

    @Test
    public void responseOnPressButtonDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandDog,
                ButtonDogService.callbackQueryAfterCommandDog)).thenReturn(null);
        out.responseOnPressButtonDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.firstLineDog,
                null);
    }

    @Test
    public void responseOnPressButtonAboutShelterDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandAboutShelterDog,
                ButtonDogService.callbackQueryAfterCommandAboutShelterDog)).thenReturn(null);
        out.responseOnPressButtonAboutShelterDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.aboutShelterDog,
                null);
    }

    @Test
    public void responseOnPressButtonHistoryOfShelterDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupAboutShelterDog,
                ButtonDogService.callbackQueryAfterCommandGroupAboutShelterDog)).thenReturn(null);
        out.responseOnPressButtonHistoryOfShelterDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.historyOfShelterDog,
                null);
    }

    @Test
    public void responseOnPressButtonScheduleAndAddressDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupAboutShelterDog,
                ButtonDogService.callbackQueryAfterCommandGroupAboutShelterDog)).thenReturn(null);
        out.responseOnPressButtonScheduleAndAddressDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.scheduleAndAddressDog,
                null);
    }

    @Test
    public void responseOnPressButtonContactSecurityDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupAboutShelterDog,
                ButtonDogService.callbackQueryAfterCommandGroupAboutShelterDog)).thenReturn(null);
        out.responseOnPressButtonContactSecurityDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.contactSecurityDog,
                null);
    }

    @Test
    public void responseOnPressButtonRecommendationLeafyDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupAboutShelterDog,
                ButtonDogService.callbackQueryAfterCommandGroupAboutShelterDog)).thenReturn(null);
        out.responseOnPressButtonRecommendationLeafyDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.recommendationLeafyDog,
                null);
    }

    @Test
    public void responseOnPressButtonHowTakeDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandHowTakeDog)).thenReturn(null);
        out.responseOnPressButtonHowTakeDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.howToTakeDog,
                null);
    }

    @Test
    public void responseOnPressButtonDatingRulesDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog)).thenReturn(null);
        out.responseOnPressButtonDatingRulesDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.datingRulesDog,
                null);
    }

    @Test
    public void responseOnPressButtonListOfDocumentsForAdoptingDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog)).thenReturn(null);
        out.responseOnPressButtonListOfDocumentsForAdoptingDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.listOfDocumentsForAdoptingDog,
                null);
    }

    @Test
    public void responseOnPressButtonRecommendationsForTransportationDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog)).thenReturn(null);
        out.responseOnPressButtonRecommendationsForTransportationDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.recommendationsForTransportationDog,
                null);
    }

    @Test
    public void responseOnPressButtonRecommendationsForHomeDesignForLittleDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog)).thenReturn(null);
        out.responseOnPressButtonRecommendationsForHomeDesignForLittleDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.recommendationsForHomeDesignForLittleDog,
                null);
    }

    @Test
    public void responseOnPressButtonRecommendationsForHomeDesignForAdultDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog)).thenReturn(null);
        out.responseOnPressButtonRecommendationsForHomeDesignForAdultDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.recommendationsForHomeDesignForAdultDog,
                null);
    }

    @Test
    public void responseOnPressButtonRecommendationsForHomeDesignForDisabledDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog)).thenReturn(null);
        out.responseOnPressButtonRecommendationsForHomeDesignForDisabledDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.recommendationsForHomeDesignForDisabledDog,
                null);
    }

    @Test
    public void responseOnPressButtonListOfReasonForAdoptingDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog)).thenReturn(null);
        out.responseOnPressButtonListOfReasonForAdoptingDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.listOfReasonForAdoptingDog,
                null);
    }

    @Test
    public void responseOnPressButtonListOfVerifiedCynologistDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog)).thenReturn(null);
        out.responseOnPressButtonListOfVerifiedCynologistDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.listOfVerifiedCynologistDog,
                null);
    }

    @Test
    public void responseOnPressButtonCynologistAdviceDogTest() {
        when(buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog)).thenReturn(null);
        out.responseOnPressButtonCynologistAdviceDog(HAT_ID, MESSAGE_ID);
        verify(buttonService).responseOnPressButton(HAT_ID, MESSAGE_ID, textVaultService.cynologistAdviceDog,
                null);
    }
}
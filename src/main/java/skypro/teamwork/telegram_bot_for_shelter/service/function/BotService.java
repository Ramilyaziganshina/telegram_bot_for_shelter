package skypro.teamwork.telegram_bot_for_shelter.service.function;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import skypro.teamwork.telegram_bot_for_shelter.service.function.button.ButtonCatService;
import skypro.teamwork.telegram_bot_for_shelter.service.function.button.ButtonDogService;
import skypro.teamwork.telegram_bot_for_shelter.service.function.button.ButtonService;
import skypro.teamwork.telegram_bot_for_shelter.service.function.button.TextVaultService;

@Service
public class BotService {

    private final ButtonService buttonService;
    private final TextVaultService textVaultService;

    public BotService(@Lazy ButtonService buttonService, TextVaultService textVaultService) {
        this.buttonService = buttonService;
        this.textVaultService = textVaultService;
    }

    /**
     * Данный метод реагирует на case /start и направляет в метод sendMessage запрос на его срабатывание
     */
    public void startCommandReceived(long chatId, String name) {
        String msgText = ("Добрый день " + name +
                "\nВыберите интересующий Вас приют");
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonService.textButtonsAfterCommandStart,
                ButtonService.callbackQueryAfterCommandStart);
        buttonService.responseStartButton(chatId, msgText, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "START_BUTTON_FOR_EDIT_MESSAGE"
     * вызывает новый набор кнопок для выбора приюта
     */
    public void startCommandReceivedForEditMessage(long chatId, long messageId, String name) {
        String msgText = ("Добрый день " + name +
                "\nВыберите интересующий Вас приют");
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonService.textButtonsAfterCommandStart,
                ButtonService.callbackQueryAfterCommandStart);
        buttonService.responseOnPressButton(chatId, messageId, msgText, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "CHOOSE_A_SHELTER_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandCat,
                ButtonCatService.callbackQueryAfterCommandCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.firstLineCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "ABOUT_SHELTER_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonAboutShelterCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandAboutShelterCat,
                ButtonCatService.callbackQueryAfterCommandAboutShelterCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.aboutShelterCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "HISTORY_OF_SHELTER_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonHistoryOfShelterCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupAboutShelterCat,
                ButtonCatService.callbackQueryAfterCommandGroupAboutShelterCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.historyOfShelterCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "SCHEDULE_AND_ADDRESS_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonScheduleAndAddressCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupAboutShelterCat,
                ButtonCatService.callbackQueryAfterCommandGroupAboutShelterCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.scheduleAndAddressCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "CONTACT_SECURITY_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonContactSecurityCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupAboutShelterCat,
                ButtonCatService.callbackQueryAfterCommandGroupAboutShelterCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.contactSecurityCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "RECOMMENDATION_LEAFY_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonRecommendationLeafyCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupAboutShelterCat,
                ButtonCatService.callbackQueryAfterCommandGroupAboutShelterCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.recommendationLeafyCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "HOW_TAKE_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonHowTakeCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandHowTakeCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.howToTakeCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "DATING_RULES_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonDatingRulesCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.datingRulesCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "LIST_OF_DOCUMENTS_FOR_ADOPTING_A_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonListOfDocumentsForAdoptingCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.listOfDocumentsForAdoptingCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "RECOMMENDATIONS_FOR_TRANSPORTATION_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonRecommendationsForTransportationCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.recommendationsForTransportationCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_LITTLE_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonRecommendationsForHomeDesignForLittleCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.recommendationsForHomeDesignForLittleCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_ADULT_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonRecommendationsForHomeDesignForAdultCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.recommendationsForHomeDesignForAdultCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_DISABLED_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonRecommendationsForHomeDesignForDisabledCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.recommendationsForHomeDesignForDisabledCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "LIST_OF_REASONS_FOR_ADOPTING_CAT"
     * вызывает новый набор кнопок для меню приюта кошек
     */
    public void responseOnPressButtonListOfReasonForAdoptingCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandGroupHowTakeCat,
                ButtonCatService.callbackQueryAfterCommandGroupHowTakeCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.listOfReasonForAdoptingCat, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "CHOOSE_A_SHELTER_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandDog,
                ButtonDogService.callbackQueryAfterCommandDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.firstLineDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "ABOUT_SHELTER_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonAboutShelterDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandAboutShelterDog,
                ButtonDogService.callbackQueryAfterCommandAboutShelterDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.aboutShelterDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "HISTORY_OF_SHELTER_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonHistoryOfShelterDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupAboutShelterDog,
                ButtonDogService.callbackQueryAfterCommandGroupAboutShelterDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.historyOfShelterDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "SCHEDULE_AND_ADDRESS_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonScheduleAndAddressDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupAboutShelterDog,
                ButtonDogService.callbackQueryAfterCommandGroupAboutShelterDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.scheduleAndAddressDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "CONTACT_SECURITY_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonContactSecurityDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupAboutShelterDog,
                ButtonDogService.callbackQueryAfterCommandGroupAboutShelterDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.contactSecurityDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "RECOMMENDATION_LEAFY_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonRecommendationLeafyDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupAboutShelterDog,
                ButtonDogService.callbackQueryAfterCommandGroupAboutShelterDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.recommendationLeafyDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "HOW_TAKE_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonHowTakeDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandHowTakeDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.howToTakeDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "DATING_RULES_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonDatingRulesDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.datingRulesDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "LIST_OF_DOCUMENTS_FOR_ADOPTING_A_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonListOfDocumentsForAdoptingDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.listOfDocumentsForAdoptingDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "RECOMMENDATIONS_FOR_TRANSPORTATION_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonRecommendationsForTransportationDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.recommendationsForTransportationDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_LITTLE_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonRecommendationsForHomeDesignForLittleDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.recommendationsForHomeDesignForLittleDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_ADULT_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonRecommendationsForHomeDesignForAdultDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.recommendationsForHomeDesignForAdultDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_DISABLED_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonRecommendationsForHomeDesignForDisabledDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.recommendationsForHomeDesignForDisabledDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "LIST_OF_REASONS_FOR_ADOPTING_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonListOfReasonForAdoptingDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.listOfReasonForAdoptingDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "LIST_OF_VERIFIED_CYNOLOGISTS_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonListOfVerifiedCynologistDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.listOfVerifiedCynologistDog, inlineKeyboard);
    }

    /**
     * Данный метод реагирует на нажатие кнопки с идентификатором "CYNOLOGISTS_ADVICE_DOG"
     * вызывает новый набор кнопок для меню приюта собак
     */
    public void responseOnPressButtonCynologistAdviceDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandGroupHowTakeDog,
                ButtonDogService.callbackQueryAfterCommandGroupHowTakeDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.cynologistAdviceDog, inlineKeyboard);
    }

    public void responseOnPressButtonVollunterCatBefore(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandVollunterCat,
                ButtonCatService.callbackQueryAfterCommandVollunterCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.ansewerToUserFromVolunnetBefore, inlineKeyboard);
    }

    public void responseOnPressButtonVollunterDogBefore(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandVollunterDog,
                ButtonDogService.callbackQueryAfterCommandVollunterDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.ansewerToUserFromVolunnetBefore, inlineKeyboard);
    }

    public void responseOnPressButtonVollunterCatAfter(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandVollunterCat,
                ButtonCatService.callbackQueryAfterCommandVollunterCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.ansewerToUserFromVolunnetAfter, inlineKeyboard);
    }

    public void responseOnPressButtonVollunterDogAfter(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandVollunterDog,
                ButtonDogService.callbackQueryAfterCommandVollunterDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.ansewerToUserFromVolunnetAfter, inlineKeyboard);
    }

    public void responseOnPressButtonSendReportCat(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonCatService.textButtonsAfterCommandSendReportCat,
                ButtonCatService.callbackQueryAfterCommandSendReportCat);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.sendReportFormText, inlineKeyboard);
    }

    public void responseOnPressButtonSendReportDog(long chatId, long messageId) {
        InlineKeyboardMarkup inlineKeyboard = buttonService.prepareKeyboard(
                ButtonDogService.textButtonsAfterCommandSendReportDog,
                ButtonDogService.callbackQueryAfterCommandSendReportDog);
        buttonService.responseOnPressButton(chatId, messageId, textVaultService.sendReportFormText, inlineKeyboard);
    }
}
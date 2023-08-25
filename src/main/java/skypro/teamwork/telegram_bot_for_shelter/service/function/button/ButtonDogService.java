package skypro.teamwork.telegram_bot_for_shelter.service.function.button;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * класс содержит названия кнопок для приюта собак
 */
@Component
public class ButtonDogService {

    /**
     * ArrayList c кнопками меню после нажатия кнопки "CHOOSE_A_SHELTER_DOG"
     */
    public static final List<String> textButtonsAfterCommandDog = new ArrayList<>(List.of(
            "О собачьем приюте",
            "Как взять питомца из приюта",
            "Прислать отчет о питомце",
            "Позвать волонтёра",
            "Назад"
    ));

    /**
     * ArrayList c идентификаторами кнопок меню после нажатия кнопки "CHOOSE_A_SHELTER_DOG"
     */
    public static final List<String> callbackQueryAfterCommandDog = new ArrayList<>(List.of(
            "ABOUT_SHELTER_DOG",
            "HOW_TAKE_DOG",
            "SEND_REPORT_DOG",
            "VOLUNTEER_DOG",
            "START_BUTTON_FOR_EDIT_MESSAGE"
    ));

    /**
     * ArrayList c кнопками меню после нажатия кнопки "ABOUT_SHELTER_DOG"
     */
    public static final List<String> textButtonsAfterCommandAboutShelterDog = new ArrayList<>(List.of(
            "Рассказ о приюте",
            "Расписание и адрес",
            "Контактные данные охраны",
            "Рекомандация по технике безопасности на территории",
            "Принять контактные данные для связи",
            "Вызвать волонтёра",
            "Назад"
    ));

    /**
     * ArrayList c идентификаторами кнопок меню после нажатия кнопки "ABOUT_SHELTER_DOG"
     */
    public static final List<String> callbackQueryAfterCommandAboutShelterDog = new ArrayList<>(List.of(
            "HISTORY_OF_SHELTER_DOG",
            "SCHEDULE_AND_ADDRESS_DOG",
            "CONTACT_SECURITY_DOG",
            "RECOMMENDATION_LEAFY_DOG",
            "VOLUNTEER_DOG",
            "VOLUNTEER_DOG",
            "CHOOSE_A_SHELTER_DOG"
    ));

    /**
     * ArrayList c кнопками меню после нажатия кнопки "HOW_TAKE_DOG"
     */
    public static final List<String> textButtonsAfterCommandHowTakeDog = new ArrayList<>(List.of(
            "Правила знакомства с животным",
            "Список документов для опеки",
            "Рекоммендации по транспортировке",
            "Рекомандации по обустройству дома для юного питомца",
            "Рекомандации по обустройству дома для взрослого питомца",
            "Рекомандации по обустройству дома для питомца с ограниченными возможностями",
            "Список причин отказа в опеке",
            "Список проверенных кинологов",
            "Советы кинологов по первичному общению с питомцем",
            "Принять контактные данные для связи",
            "Вызвать волонтёра",
            "Назад"
    ));

    /**
     * ArrayList c идентификаторами кнопок меню после нажатия кнопки "HOW_TAKE_DOG"
     */
    public static final List<String> callbackQueryAfterCommandHowTakeDog = new ArrayList<>(List.of(
            "DATING_RULES_DOG",
            "LIST_OF_DOCUMENTS_FOR_ADOPTING_DOG",
            "RECOMMENDATIONS_FOR_TRANSPORTATION_DOG",
            "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_LITTLE_DOG",
            "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_ADULT_DOG",
            "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_DISABLED_DOG",
            "LIST_OF_REASONS_FOR_ADOPTING_DOG",
            "LIST_OF_VERIFIED_CYNOLOGISTS_DOG",
            "CYNOLOGISTS_ADVICE_DOG",
            "VOLUNTEER_DOG",
            "VOLUNTEER_DOG",
            "CHOOSE_A_SHELTER_DOG"
    ));

    /**
     * ArrayList c кнопками меню после нажатия кнопок выдаваемых кнопкой  "ABOUT_SHELTER_DOG"
     * список кнопок
     * HISTORY_OF_SHELTER_DOG
     * SCHEDULE_AND_ADDRESS_DOG
     * CONTACT_SECURITY_DOG
     * RECOMMENDATION_LEAFY_DOG"
     */
    public static final List<String> textButtonsAfterCommandGroupAboutShelterDog = new ArrayList<>(List.of(
            "Назад",
            "Вернуться в начало"
    ));

    /**
     * ArrayList c идентификаторами кнопок меню после нажатия кнопок выдаваемых кнопкой  "ABOUT_SHELTER_DOG"
     * список кнопок
     * HISTORY_OF_SHELTER_DOG
     * SCHEDULE_AND_ADDRESS_DOG
     * CONTACT_SECURITY_DOG
     * RECOMMENDATION_LEAFY_DOG"
     */
    public static final List<String> callbackQueryAfterCommandGroupAboutShelterDog = new ArrayList<>(List.of(
            "ABOUT_SHELTER_DOG",
            "START_BUTTON_FOR_EDIT_MESSAGE"
    ));

    /**
     * ArrayList c кнопками меню после нажатия кнопок выдаваемых кнопкой  "HOW_TAKE_DOG"
     * список кнопок
     * DATING_RULES_DOG
     * LIST_OF_DOCUMENTS_FOR_ADOPTING_DOG
     * RECOMMENDATIONS_FOR_TRANSPORTATION_DOG
     * RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_LITTLE_DOG
     * RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_ADULT_DOG
     * RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_DISABLED_DOG
     * LIST_OF_REASONS_FOR_ADOPTING_DOG
     * LIST_OF_VERIFIED_CYNOLOGISTS
     * CYNOLOGISTS_ADVICE
     */
    public static final List<String> textButtonsAfterCommandGroupHowTakeDog = new ArrayList<>(List.of(
            "Назад",
            "Вернуться в начало"
    ));

    /**
     * ArrayList c идентификаторами кнопок меню после нажатия кнопок выдаваемых кнопкой  "HOW_TAKE_DOG"
     * список кнопок
     * DATING_RULES_DOG
     * LIST_OF_DOCUMENTS_FOR_ADOPTING_DOG
     * RECOMMENDATIONS_FOR_TRANSPORTATION_DOG
     * RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_LITTLE_DOG
     * RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_ADULT_DOG
     * RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_DISABLED_DOG
     * LIST_OF_REASONS_FOR_ADOPTING_DOG
     * LIST_OF_REASONS_FOR_ADOPTING_DOG
     * LIST_OF_VERIFIED_CYNOLOGISTS
     */
    public static final List<String> callbackQueryAfterCommandGroupHowTakeDog = new ArrayList<>(List.of(
            "HOW_TAKE_DOG",
            "START_BUTTON_FOR_EDIT_MESSAGE"
    ));

    public static final List<String> textButtonsAfterCommandVollunterDog = new ArrayList<>(List.of(
            "Назад",
            "Вернуться в начало"));
    public static final List<String> callbackQueryAfterCommandVollunterDog = new ArrayList<>(List.of(
            "CHOOSE_A_SHELTER_DOG", "START_BUTTON_FOR_EDIT_MESSAGE"
    ));

    public static final List<String> textButtonsAfterCommandSendReportDog = new ArrayList<>(List.of(
            "Назад",
            "Вернуться в начало"));
    public static final List<String> callbackQueryAfterCommandSendReportDog = new ArrayList<>(List.of(
            "SEND_REPORT_DOG_BACK", "SEND_REPORT_DOG_HOME"
    ));
}
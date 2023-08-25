package skypro.teamwork.telegram_bot_for_shelter.service.function.button;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * класс содержит названия кнопок для приюта кошек
 */
@Component
public class ButtonCatService {

    /**
     * ArrayList c кнопками меню после нажатия кнопки "CHOOSE_A_SHELTER_CAT"
     */
    public static final List<String> textButtonsAfterCommandCat = new ArrayList<>(List.of(
            "О приюте кошек",
            "Как взять питомца из приюта",
            "Прислать отчет о питомце",
            "Позвать волонтёра",
            "Назад"
    ));

    /**
     * ArrayList c идентификаторами кнопок меню после нажатия кнопки "CHOOSE_A_SHELTER_CAT"
     */
    public static final List<String> callbackQueryAfterCommandCat = new ArrayList<>(List.of(
            "ABOUT_SHELTER_CAT",
            "HOW_TAKE_CAT",
            "SEND_REPORT_CAT",
            "VOLUNTEER_CAT",
            "START_BUTTON_FOR_EDIT_MESSAGE"
    ));

    /**
     * ArrayList c кнопками меню после нажатия кнопки "ABOUT_SHELTER_CAT"
     */
    public static final List<String> textButtonsAfterCommandAboutShelterCat = new ArrayList<>(List.of(
            "Рассказ о приюте",
            "Расписание и адрес",
            "Контактные данные охраны",
            "Рекомандация по технике безопасности на территории",
            "Принять контактные данные для связи",
            "Вызвать волонтёра",
            "Назад"
    ));

    /**
     * ArrayList c идентификаторами кнопок меню после нажатия кнопки "ABOUT_SHELTER_CAT"
     */
    public static final List<String> callbackQueryAfterCommandAboutShelterCat = new ArrayList<>(List.of(
            "HISTORY_OF_SHELTER_CAT",
            "SCHEDULE_AND_ADDRESS_CAT",
            "CONTACT_SECURITY_CAT",
            "RECOMMENDATION_LEAFY_CAT",
            "VOLUNTEER_CAT",
            "VOLUNTEER_CAT",
            "CHOOSE_A_SHELTER_CAT"
    ));

    /**
     * ArrayList c кнопками меню после нажатия кнопки "HOW_TAKE_CAT"
     */
    public static final List<String> textButtonsAfterCommandHowTakeCat = new ArrayList<>(List.of(
            "Правила знакомства с животным",
            "Список документов для опеки",
            "Рекоммендации по транспортировке",
            "Рекомандации по обустройству дома для юного питомца",
            "Рекомандации по обустройству дома для взрослого питомца",
            "Рекомандации по обустройству дома для питомца с ограниченными возможностями",
            "Список причин отказа в опеке",
            "Принять контактные данные для связи",
            "Вызвать волонтёра",
            "Назад"
    ));

    /**
     * ArrayList c идентификаторами кнопок меню после нажатия кнопки "HOW_TAKE_CAT"
     */
    public static final List<String> callbackQueryAfterCommandHowTakeCat = new ArrayList<>(List.of(
            "DATING_RULES_CAT",
            "LIST_OF_DOCUMENTS_FOR_ADOPTING_A_CAT",
            "RECOMMENDATIONS_FOR_TRANSPORTATION_CAT",
            "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_LITTLE_CAT",
            "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_ADULT_CAT",
            "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_DISABLED_CAT",
            "LIST_OF_REASONS_FOR_ADOPTING_CAT",
            "VOLUNTEER_CAT",
            "VOLUNTEER_CAT",
            "CHOOSE_A_SHELTER_CAT"
    ));

    /**
     * ArrayList c кнопками меню после нажатия кнопок выдаваемых кнопкой  "ABOUT_SHELTER_CAT"
     * список кнопок
     * HISTORY_OF_SHELTER_CAT
     * SCHEDULE_AND_ADDRESS_CAT
     * CONTACT_SECURITY_CAT
     * RECOMMENDATION_LEAFY_CAT"
     */
    public static final List<String> textButtonsAfterCommandGroupAboutShelterCat = new ArrayList<>(List.of(
            "Назад",
            "Вернуться в начало"
    ));

    /**
     * ArrayList c идентификаторами кнопок меню после нажатия кнопок выдаваемых кнопкой  "ABOUT_SHELTER_CAT"
     * список кнопок
     * HISTORY_OF_SHELTER_CAT
     * SCHEDULE_AND_ADDRESS_CAT
     * CONTACT_SECURITY_CAT
     * RECOMMENDATION_LEAFY_CAT"
     */
    public static final List<String> callbackQueryAfterCommandGroupAboutShelterCat = new ArrayList<>(List.of(
            "ABOUT_SHELTER_CAT",
            "START_BUTTON_FOR_EDIT_MESSAGE"
    ));

    /**
     * ArrayList c кнопками меню после нажатия кнопок выдаваемых кнопкой  "HOW_TAKE_CAT"
     * список кнопок
     * DATING_RULES_CAT
     * LIST_OF_DOCUMENTS_FOR_ADOPTING_A_CAT
     * RECOMMENDATIONS_FOR_TRANSPORTATION_CAT
     * RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_LITTLE_CAT
     * RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_ADULT_CAT
     * RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_DISABLED_CAT
     * LIST_OF_REASONS_FOR_ADOPTING_CAT
     */
    public static final List<String> textButtonsAfterCommandGroupHowTakeCat = new ArrayList<>(List.of(
            "Назад",
            "Вернуться в начало"
    ));

    /**
     * ArrayList c идентификаторами кнопок меню после нажатия кнопок выдаваемых кнопкой  "HOW_TAKE_CAT"
     * список кнопок
     * DATING_RULES_CAT
     * LIST_OF_DOCUMENTS_FOR_ADOPTING_A_CAT
     * RECOMMENDATIONS_FOR_TRANSPORTATION_CAT
     * RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_LITTLE_CAT
     * RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_ADULT_CAT
     * RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_DISABLED_CAT
     * LIST_OF_REASONS_FOR_ADOPTING_CAT
     */
    public static final List<String> callbackQueryAfterCommandGroupHowTakeCat = new ArrayList<>(List.of(
            "HOW_TAKE_CAT",
            "START_BUTTON_FOR_EDIT_MESSAGE"
    ));

    public static final List<String> textButtonsAfterCommandVollunterCat = new ArrayList<>(List.of(
            "Назад",
            "Вернуться в начало"));
    public static final List<String> callbackQueryAfterCommandVollunterCat = new ArrayList<>(List.of(
            "CHOOSE_A_SHELTER_CAT", "START_BUTTON_FOR_EDIT_MESSAGE"
    ));

    public static final List<String> textButtonsAfterCommandSendReportCat = new ArrayList<>(List.of(
            "Назад",
            "Вернуться в начало"));
    public static final List<String> callbackQueryAfterCommandSendReportCat = new ArrayList<>(List.of(
            "SEND_REPORT_CAT_BACK", "SEND_REPORT_CAT_HOME"
    ));
}
package skypro.teamwork.telegram_bot_for_shelter.service.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import skypro.teamwork.telegram_bot_for_shelter.config.BotConfig;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс наследуется из TelegramLongPollingBot и переопределяет методы в конструкторе
 * для взаимодействия нашей программы с ботом через класс BotConfig
 */
@Service
public class TelegramBot extends TelegramLongPollingBot {
    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    /**
     * Инициализация класса, в котором хранится имя и токен бота
     */
    private final BotConfig config;
    private final BotService botService;
    private final ReportService reportService;
    private final UserFunction userFunction;

    public TelegramBot(BotConfig config, BotService botService, ReportService reportService, UserFunction userFunction) {
        this.config = config;
        this.botService = botService;
        this.reportService = reportService;
        this.userFunction = userFunction;
    }

    /**
     * Переопределение методов под наши задачи из класса TelegramLongPollingBot
     */
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    /**
     * Данный метод отслеживает какое сообщение пришло от пользователя в update и выполняет функционал в зависимости
     * от полученного сообщения путем switch-case
     */
    @Override
    public void onUpdateReceived(Update update) {
        /*
        VOLUNTEER_DOG, VOLUNTEER_CAT после того как произошла регистрация в HashMap(находится в классе UserFunction)
        происходит проверка на наличие контакта
         */
        if ((update.hasMessage()
                && UserFunction.getLast_message().containsKey(update.getMessage().getChatId())
                && update.getMessage().hasContact())) {
            Long chatId = update.getMessage().getChatId();
            // Идёт сохраниение в БД
            userFunction.saveUserInDB(
                    update.getMessage().getChatId(),
                    update.getMessage().getContact().getPhoneNumber(),
                    update.getMessage().getChat().getFirstName());
            //Удаление сообщения отправденное пользователем
            DeleteMessage deleteMessage = new DeleteMessage(String.valueOf(chatId), update.getMessage().getMessageId());
            DeleteMessage deleteMessageSendedToUser = new DeleteMessage(String.valueOf(chatId),
                    UserFunction.getLast_message().get(chatId).getIdMessageToUser());
            try {
                execute(deleteMessage);
                execute(deleteMessageSendedToUser);
            } catch (TelegramApiException e) {
                logger.error(e.getMessage());
            }
            //Отправка сообщения пользователю об успешном сохраниении в БД
            String tag = UserFunction.getLast_message().get(chatId).getMessageCommand();
            if (tag.equals("VOLUNTEER_DOG")) {
                botService.responseOnPressButtonVollunterDogAfter(chatId, UserFunction.getMessageID());
            }
            if (tag.equals("VOLUNTEER_CAT")) {
                botService.responseOnPressButtonVollunterCatAfter(chatId, UserFunction.getMessageID());
            }
            UserFunction.last_message_clear(chatId);
        }

        /**
         * Данный иф делает ряд проверок
         * 1. Находится ли пользователь в режиме сдачи отчета
         * 2. Соответствует ли введенный номер паспорта животного стандарту
         * 3. Есть ли питомец с таким номером паспорта в базе
         * 4. Что фото и текст были переданы в сообщении
         */
        if ((update.hasMessage() &&
                reportService.activeReportUsers.contains(update.getMessage().getChatId()))) {
            if (reportService.verifyPetPassportWithoutErrors(update)) {
                if (reportService.verifyPetPassport(update)) {
                    if (update.getMessage().getCaption() != null && !update.getMessage().getPhoto().isEmpty()) {
                        reportService.processDoc(update);
                        sendMessage(update.getMessage().getChatId(), "Ваш отчет сохранен");
                    } else {
                        sendMessage(update.getMessage().getChatId(), "Данное сообщение не удовлетворяет требованиям отчета");
                        reportService.activeReportCheck(update.getMessage().getChatId());
                    }
                    reportService.activeReportCheck(update.getMessage().getChatId());
                } else {
                    sendMessage(update.getMessage().getChatId(), "Ваш питомец не найден");
                }
            } else {
                sendMessage(update.getMessage().getChatId(), "Вначале сообщения должны быть 6 цифр номера паспорта питомца, далее отчет");
            }

        } else if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            botService.startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
            DeleteMessage deleteMessage1 = new DeleteMessage(String.valueOf(chatId), update.getMessage().getMessageId());
            try {
                execute(deleteMessage1);
            } catch (TelegramApiException e) {
                logger.error(e.getMessage());
            }
        } else if (update.hasCallbackQuery()) {
            String callbackQuery = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            String firstName = update.getCallbackQuery().getFrom().getFirstName();

            switch (callbackQuery) {
//                 кпопки первого уровня
                case "START_BUTTON_FOR_EDIT_MESSAGE":
                    botService.startCommandReceivedForEditMessage
                            (chatId, messageId, firstName);
                    break;

//                 кпопки второго уровня
                case "CHOOSE_A_SHELTER_CAT":
                    botService.responseOnPressButtonCat(chatId, messageId);
                    break;
                case "CHOOSE_A_SHELTER_DOG":
                    botService.responseOnPressButtonDog(chatId, messageId);
                    break;

//                кнопка Рассказ о приюте и ее подкнопки кошки
                case "ABOUT_SHELTER_CAT":
                    botService.responseOnPressButtonAboutShelterCat(chatId, messageId);
                    break;
                case "HISTORY_OF_SHELTER_CAT":
                    botService.responseOnPressButtonHistoryOfShelterCat(chatId, messageId);
                    break;
                case "SCHEDULE_AND_ADDRESS_CAT":
                    botService.responseOnPressButtonScheduleAndAddressCat(chatId, messageId);
                    break;
                case "CONTACT_SECURITY_CAT":
                    botService.responseOnPressButtonContactSecurityCat(chatId, messageId);
                    break;
                case "RECOMMENDATION_LEAFY_CAT":
                    botService.responseOnPressButtonRecommendationLeafyCat(chatId, messageId);
                    break;

                /** кнопка Рассказ о приюте и ее подкнопки собаки*/
                case "ABOUT_SHELTER_DOG":
                    botService.responseOnPressButtonAboutShelterDog(chatId, messageId);
                    break;
                case "HISTORY_OF_SHELTER_DOG":
                    botService.responseOnPressButtonHistoryOfShelterDog(chatId, messageId);
                    break;
                case "SCHEDULE_AND_ADDRESS_DOG":
                    botService.responseOnPressButtonScheduleAndAddressDog(chatId, messageId);
                    break;
                case "CONTACT_SECURITY_DOG":
                    botService.responseOnPressButtonContactSecurityDog(chatId, messageId);
                    break;
                case "RECOMMENDATION_LEAFY_DOG":
                    botService.responseOnPressButtonRecommendationLeafyDog(chatId, messageId);
                    break;

//                 кнопка Как взять питомца из приюта и ее подкнопки кошки
                case "HOW_TAKE_CAT":
                    botService.responseOnPressButtonHowTakeCat(chatId, messageId);
                    break;
                case "DATING_RULES_CAT":
                    botService.responseOnPressButtonDatingRulesCat(chatId, messageId);
                    break;
                case "LIST_OF_DOCUMENTS_FOR_ADOPTING_CAT":
                    botService.responseOnPressButtonListOfDocumentsForAdoptingCat(chatId, messageId);
                    break;
                case "RECOMMENDATIONS_FOR_TRANSPORTATION_CAT":
                    botService.responseOnPressButtonRecommendationsForTransportationCat(chatId, messageId);
                    break;
                case "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_LITTLE_CAT":
                    botService.responseOnPressButtonRecommendationsForHomeDesignForLittleCat(chatId, messageId);
                    break;
                case "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_ADULT_CAT":
                    botService.responseOnPressButtonRecommendationsForHomeDesignForAdultCat(chatId, messageId);
                    break;
                case "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_DISABLED_CAT":
                    botService.responseOnPressButtonRecommendationsForHomeDesignForDisabledCat(chatId, messageId);
                    break;
                case "LIST_OF_REASONS_FOR_ADOPTING_CAT":
                    botService.responseOnPressButtonListOfReasonForAdoptingCat(chatId, messageId);
                    break;

                //Кнопка Как взять питомца из приюта и ее подкнопки собаки
                case "HOW_TAKE_DOG":
                    botService.responseOnPressButtonHowTakeDog(chatId, messageId);
                    break;
                case "DATING_RULES_DOG":
                    botService.responseOnPressButtonDatingRulesDog(chatId, messageId);
                    break;
                case "LIST_OF_DOCUMENTS_FOR_ADOPTING_DOG":
                    botService.responseOnPressButtonListOfDocumentsForAdoptingDog(chatId, messageId);
                    break;
                case "RECOMMENDATIONS_FOR_TRANSPORTATION_DOG":
                    botService.responseOnPressButtonRecommendationsForTransportationDog(chatId, messageId);
                    break;
                case "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_LITTLE_DOG":
                    botService.responseOnPressButtonRecommendationsForHomeDesignForLittleDog(chatId, messageId);
                    break;
                case "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_ADULT_DOG":
                    botService.responseOnPressButtonRecommendationsForHomeDesignForAdultDog(chatId, messageId);
                    break;
                case "RECOMMENDATIONS_FOR_HOME_DESIGN_FOR_DISABLED_DOG":
                    botService.responseOnPressButtonRecommendationsForHomeDesignForDisabledDog(chatId, messageId);
                    break;
                case "LIST_OF_REASONS_FOR_ADOPTING_DOG":
                    botService.responseOnPressButtonListOfReasonForAdoptingDog(chatId, messageId);
                    break;
                case "LIST_OF_VERIFIED_CYNOLOGISTS_DOG":
                    botService.responseOnPressButtonListOfVerifiedCynologistDog(chatId, messageId);
                    break;
                case "CYNOLOGISTS_ADVICE_DOG":
                    botService.responseOnPressButtonCynologistAdviceDog(chatId, messageId);
                    break;

                case "SEND_REPORT_CAT":
                case "SEND_REPORT_DOG":
                    if (reportService.verifyUserByChatId(chatId)) {
                        botService.responseOnPressButtonSendReportDog(chatId, messageId);
                        reportService.activeReportCheck(chatId);
                    } else {
                        sendMessage(chatId, "Данный аккаунт не числится в баззе опекунов");
                        break;
                    }
                    break;
                case "SEND_REPORT_CAT_BACK":
                    botService.responseOnPressButtonCat(chatId, messageId);
                    reportService.activeReportCheck(chatId);
                    break;
                case "SEND_REPORT_DOG_BACK":
                    botService.responseOnPressButtonDog(chatId, messageId);
                    reportService.activeReportCheck(chatId);
                    break;
                case "SEND_REPORT_CAT_HOME":
                case "SEND_REPORT_DOG_HOME":
                    botService.startCommandReceivedForEditMessage(chatId, messageId, firstName);
                    reportService.activeReportCheck(chatId);
                    break;

                case "TAKE_CONTACT_FOR_FEEDBACK":
                    sendMessage(chatId, "Раздел в стадии разработки, " +
                            "тут вы сможете оставить свои данные для передачи их волонтеру");
                    break;
                /**
                  Здесь происходит регистрация события готовности пользователя
                  отправить контакт для связи с волонтером
                 */
                case "VOLUNTEER_CAT":
                    botService.responseOnPressButtonVollunterCatBefore(chatId, messageId);
                    LocalDateTime ldt = LocalDateTime.now();
                    UserFunction.setLastMessage(chatId, ldt, responseOnPressButtonForVolunteer(chatId,
                                    new TelegramBot(config, botService, reportService, userFunction)),
                            "VOLUNTEER_CAT");
                    UserFunction.setMessageID(messageId);
                    break;
                case "VOLUNTEER_DOG":
                    botService.responseOnPressButtonVollunterDogBefore(chatId, messageId);
                    LocalDateTime ldt1 = LocalDateTime.now();
                    UserFunction.setLastMessage(chatId, ldt1, responseOnPressButtonForVolunteer(chatId,
                            new TelegramBot(config, botService, reportService, userFunction)), "VOLUNTEER_DOG");
                    UserFunction.setMessageID(messageId);
                    break;
                default:
                    sendMessage(chatId, "Повторите попытку, такой команды нет");
                    break;
            }
        }
    }

    /**
     * Данный метод получает входящие данные и несет в себе функционал отправки их пользователю
     */
    public void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {

        }
    }

    /**
     * Этот метод создаёт кнопку контакта для удобства пользователя.
     * Она позоволяет с помощью клавиатуры отправить пользователю свой контакт
     */
    public static Integer responseOnPressButtonForVolunteer(long chatId, TelegramBot telegramBot) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Вы можете кнопкой отправить свой контакт"); // Set a whitespace character as the message text

        //Создать объект KeyboardButton
        KeyboardButton keyboardButton = new KeyboardButton("Отправить контакт");
        keyboardButton.setRequestContact(true);

        //Создайте объект KeyboardRow и добавьте к нему KeyboardButton
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(keyboardButton);

        //Создайте список объектов KeyboardRow и добавьте в него строку
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardRow);

        //Создайте объект ReplyKeyboardMarkup и установите клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        //Установливает разметку клавиатуры для ответа в сообщении
        message.setReplyMarkup(replyKeyboardMarkup);
        try {
            Message messageInfo = telegramBot.execute(message);
            return messageInfo.getMessageId();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
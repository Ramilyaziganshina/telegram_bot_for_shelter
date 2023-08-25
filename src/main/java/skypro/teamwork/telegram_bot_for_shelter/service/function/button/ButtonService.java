package skypro.teamwork.telegram_bot_for_shelter.service.function.button;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import skypro.teamwork.telegram_bot_for_shelter.service.function.TelegramBot;

import java.util.ArrayList;
import java.util.List;

/**
 * класс создает кнопки, определяет их количество и порядок предоставления пользователю
 */
@Service
public class ButtonService {

    private final Logger logger = LoggerFactory.getLogger(ButtonService.class);

    /**
     * ArrayList c кнопками меню после команды "start"
     */
    public static final List<String> textButtonsAfterCommandStart = new ArrayList<>(List.of(
            "Приют для кошек ",
            "Приют для собак "
    ));

    /**
     * ArrayList c идентификаторами кнопок меню после команды "start"
     */
    public static final List<String> callbackQueryAfterCommandStart = new ArrayList<>(List.of(
            "CHOOSE_A_SHELTER_CAT",
            "CHOOSE_A_SHELTER_DOG"
    ));

    private TelegramBot telegramBot;

    public ButtonService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * Метод создает InlineKeyboardMarkup клавиатуру (в каждой строке одна кнопка)
     *
     * @param buttonsTexts    текст на кнопках
     * @param buttonsCallback идентификаторы кнопок
     * @return инлайн клавитура c кнопками
     * <p>
     * создаем клавиатуру
     * создаем список строк
     * в цикле:
     * создаем кнопки и добавляем их в строку, а строки в лист строк
     * за один проход цикла создается одна кнопка в каждой строке
     * количество кнопок определяется входящим параметром buttonsTexts (количеством записей)
     */
    public InlineKeyboardMarkup prepareKeyboard(List<String> buttonsTexts, List<String> buttonsCallback) {
        //создаем клавиатуру
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        //создаем список строк
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        //в цикле:
        //создаем кнопки и добавляем их в строку, а строки в лист строк
        //за один проход цикла создается одна кнопка в каждой строке
        //количество кнопок определяется входящим параметром buttonsTexts (количеством записей)
        for (int i = 0; i < buttonsTexts.size(); i++) {
            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(buttonsTexts.get(i));
            inlineKeyboardButton.setCallbackData(buttonsCallback.get(i));
            keyboardButtonsRow.add(inlineKeyboardButton);
            rowList.add(keyboardButtonsRow);
        }

        markupInline.setKeyboard(rowList);

        return markupInline;
    }

    /**
     * Метод отправляет пользователю сообщение с клавиатурой под сообщением
     *
     * @param chatId         идентификатор чата для отправки сообщения
     * @param messageText    текст сообщения
     * @param inlineKeyboard клавиатура под сообщением
     *                       <p>
     *                       responseStartButton отлавливает сообщение старт и создает первое сообщение с клавиатурой,
     *                       далее кнопки обращаются к методу responseOnPressButton и он заменяет предыдущее сообщение новым с новой клавиатурой
     */
    public void responseStartButton(long chatId, String messageText, InlineKeyboardMarkup inlineKeyboard) {
        SendMessage sendMess = new SendMessage(String.valueOf(chatId), messageText);
        sendMess.setReplyMarkup(inlineKeyboard);
        try {
            telegramBot.execute(sendMess);
        } catch (TelegramApiException e) {
            logger.error("Произошла ошибка в методе responseStartButton: " + e.getMessage());
        }
    }

    public void responseOnPressButton(long chatId, long messageId, String messageText, InlineKeyboardMarkup inlineKeyboard) {
        EditMessageText sendMess = new EditMessageText();
        sendMess.setChatId(String.valueOf(chatId));
        sendMess.setText(messageText);
        sendMess.setMessageId((int) messageId);
        sendMess.setReplyMarkup(inlineKeyboard);
        try {
            telegramBot.execute(sendMess);
        } catch (TelegramApiException e) {
            logger.error("Произошла ошибка в методе responseOnPressButton: " + e.getMessage());
        }
    }
}
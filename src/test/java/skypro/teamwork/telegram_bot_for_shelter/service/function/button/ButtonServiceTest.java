package skypro.teamwork.telegram_bot_for_shelter.service.function.button;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import skypro.teamwork.telegram_bot_for_shelter.service.function.TelegramBot;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ButtonService.class})
@ExtendWith(SpringExtension.class)
public class ButtonServiceTest {
    @MockBean
    TelegramBot telegramBot;

    private final ButtonService buttonService = new ButtonService(telegramBot);

    /**
     * Этот тест проверяет метод prepareKeyboard класса ButtonService.
     * Метод prepareKeyboard принимает два аргумента: buttonsTexts, список текстов кнопок, и buttonsCallback, список обратных вызовов кнопок.
     * Он создает экземпляр класса InlineKeyboardMarkup и устанавливает его кнопки клавиатуры
     * на основе предоставленного текста и списков обратного вызова.
     */
    @Test
    void responseStartButtonTest() throws TelegramApiException {
        TelegramBot telegramBot = Mockito.mock(TelegramBot.class);

        ButtonService buttonService = new ButtonService(telegramBot);

        doAnswer(invocation -> null).when(telegramBot).execute(any(SendMessage.class));

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        buttonService.responseStartButton(12345, "Test message", inlineKeyboardMarkup);

        verify(telegramBot, times(1)).execute(any(SendMessage.class));
    }

    /**
     * Тест создает два списка, textButtonsAfterCommand и callbackQueryAfterCommand,
     * которые содержат ожидаемый текст кнопки и значения запроса обратного вызова соответственно.
     * Затем он вызывает метод prepare Keyboard()
     * с этим списком в качестве входных данных и сравнивает результирующий объект InlineKeyboardMarkup
     * с ожидаемым объектом InlineKeyboardMarkup, который был создан вручную.
     */
    @Test
    void prepareKeyboardTest() {
        List<String> textButtonsAfterCommand = new ArrayList<>(List.of(
                "Приют для кошек ",
                "Приют для собак "
        ));
        List<String> callbackQueryAfterCommand = new ArrayList<>(List.of(
                "cat",
                "dog"
        ));

        InlineKeyboardMarkup actual = buttonService.prepareKeyboard(textButtonsAfterCommand, callbackQueryAfterCommand);

        InlineKeyboardMarkup expected = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button1.setText("Приют для кошек ");
        button2.setText("Приют для собак ");
        button1.setCallbackData("cat");
        button2.setCallbackData("dog");
        row1.add(button1);
        row2.add(button2);
        rows.add(row1);
        rows.add(row2);
        expected.setKeyboard(rows);

        Assertions.assertThat(expected).isEqualTo(actual);
    }

    /**
     * проверяет поведение метода "responseOnPressButton" класса "ButtonService".
     * Метод принимает четыре параметра - два целых числа, строку и объект "InlineKeyboardMarkup".
     * Цель этого теста - убедиться, что метод ведет себя корректно, проверив, что метод "TelegramBot.execute"
     * вызывается ровно один раз с экземпляром класса "editMessageText", когда вызывается метод
     */
    @Test
    void responseOnPressButtonTest() throws TelegramApiException {
        ButtonService buttonService = new ButtonService(telegramBot);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        doAnswer(invocation -> null).when(telegramBot).execute(any(EditMessageText.class));

        buttonService.responseOnPressButton(12345, 67890, "Test message", inlineKeyboardMarkup);

        verify(telegramBot, times(1)).execute(any(EditMessageText.class));
    }
}
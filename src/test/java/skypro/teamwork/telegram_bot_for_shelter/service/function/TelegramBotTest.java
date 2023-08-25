package skypro.teamwork.telegram_bot_for_shelter.service.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import skypro.teamwork.telegram_bot_for_shelter.config.BotConfig;
import skypro.teamwork.telegram_bot_for_shelter.service.function.button.ButtonService;

import static org.mockito.Mockito.*;

public class TelegramBotTest {

    private static final String BOT_NAME = "testBotName";
    private static final String TOKEN = "testToken";

    @Mock
    private ButtonService buttonService;
    @Mock
    private BotService mockBotService;
    @Mock
    private ReportService mockReportService;
    private TelegramBot telegramBot;

    @InjectMocks
    private BotService botService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        BotConfig botConfig = new BotConfig();
        botConfig.setBotName(BOT_NAME);
        botConfig.setToken(TOKEN);
        UserFunction userFunction = Mockito.mock(UserFunction.class);
        telegramBot = new TelegramBot(botConfig, mockBotService, mockReportService, userFunction);
    }

    @Test
    public void testStartCommandReceived() {
        long chatId = 123;
        String name = "John";
        InlineKeyboardMarkup inlineKeyboard = mock(InlineKeyboardMarkup.class);
        when(buttonService.prepareKeyboard(
                ButtonService.textButtonsAfterCommandStart,
                ButtonService.callbackQueryAfterCommandStart)).thenReturn(inlineKeyboard);

        botService.startCommandReceived(chatId, name);

        verify(buttonService).responseStartButton(chatId, "Добрый день " + name +
                "\nВыберите интересующий Вас приют", inlineKeyboard);
    }

    @Test
    public void testStartCommandReceivedForEditMessage() {
        long chatId = 123;
        long messageId = 456;
        String name = "John";
        InlineKeyboardMarkup inlineKeyboard = mock(InlineKeyboardMarkup.class);
        when(buttonService.prepareKeyboard(
                ButtonService.textButtonsAfterCommandStart,
                ButtonService.callbackQueryAfterCommandStart)).thenReturn(inlineKeyboard);

        botService.startCommandReceivedForEditMessage(chatId, messageId, name);

        verify(buttonService).responseOnPressButton(chatId, messageId, "Добрый день " + name +
                "\nВыберите интересующий Вас приют", inlineKeyboard);
    }
}
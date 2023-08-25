package skypro.teamwork.telegram_bot_for_shelter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import skypro.teamwork.telegram_bot_for_shelter.service.function.TelegramBot;

/**
 * Данный класс служит для инициализации бота, без него каждую команду придется оборачивать в try\catch
 * для вылавливания TelegramApiException
 */
@Component
public class BotInitializer {
    @Autowired
    TelegramBot bot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
        }
    }
}
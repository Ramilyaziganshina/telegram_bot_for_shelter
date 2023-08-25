package skypro.teamwork.telegram_bot_for_shelter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")

/**
 * Данный класс хранит в себе токен и имя бота для того, чтобы их можно было запрашивать из методов с помощью геттеров
 */
public class BotConfig {
    @Value("${bot.name}")
    String botName;
    @Value("${bot.token}")
    String token;

    public String getBotName() {
        return botName;
    }

    public String getToken() {
        return token;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
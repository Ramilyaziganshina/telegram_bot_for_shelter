package skypro.teamwork.telegram_bot_for_shelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TelegramBotForShelterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotForShelterApplication.class, args);
    }

}
package skypro.teamwork.telegram_bot_for_shelter.service.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.Pet;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.ReportPet;
import skypro.teamwork.telegram_bot_for_shelter.repository.pets.PetRepository;
import skypro.teamwork.telegram_bot_for_shelter.service.service_database.pets.ReportPetService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static skypro.teamwork.telegram_bot_for_shelter.service.function.UserFunction.getLast_message;

/**
 * сервис для рассылки напоминания пользователям о необходимости прислать ежедневный отчет
 */
@Service
public class BotScheduledService {

    private final Logger logger = LoggerFactory.getLogger(BotService.class);

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ReportPetService reportPetService;

    /**
     * Метод каждый день в 21:00 проверяет БД, чтобы убедиться,
     * что все вчерашние отчеты присутствуют в БД
     * Если отчетов нет, направляет пользователям напоминания
     */
    @Scheduled(cron = "${cron.report.user}")
    public void sendToUserIfNoReport() {
        List<SendMessage> messageList = checkReportsForTheLastDay();
        for (SendMessage message : messageList) {
            message.getText();
            try {
                telegramBot.execute(message);
            } catch (TelegramApiException e) {
                logger.error("Произошла ошибка в методе sendToUserIfNoReport: " + e.getMessage());
            }
        }
    }

    /**
     * Проверяет наличие отчета в базе данных ReportPet для питомца
     * Если отчета нет, создает SendMessage для отправки пользователю напоминания
     *
     * @return List of SendMessage
     */
    private List<SendMessage> checkReportsForTheLastDay() {
        List<SendMessage> sendMessage = new ArrayList<>();
        SendMessage sMsg = new SendMessage();
        String textToSend;
        // получить всех питомцев
        List<Pet> listAllPet = petRepository.findAll();
        logger.info("------ Список всех питомцев: " + listAllPet);
        // Проверить, есть ли отчет по питомцу за прошедший день
        for (Pet pet : listAllPet) {
            ReportPet petReportYesterday = reportPetService.findReportPetByPetAndTime(pet, LocalDate.now().minusDays(1L));
            if (petReportYesterday == null) {
                // если пользователь вчера не отправил отчет, напомнить ему
                textToSend = "Добрый день, мы не получили отчет о питомце за вчерашний день, пожалуйста, " +
                        "пришлите сегодня фотоотчет и информацию о питомце";
                //получаем chatId user к которому относится питомец и добавляем его в SendMessage
                Long userChatId = pet.getUser().getChatId();
                sMsg.setChatId(String.valueOf(userChatId));
                sMsg.setText(textToSend);
                sendMessage.add(sMsg);
            }
        }
        return sendMessage;
    }

    /**
     * Если пользователь больше 5 минут находится во временной базе в HashMap {@link = UserFunction.java} , удаляется из базы
     */
    @Scheduled(cron = "0 0/5 * * * *")
    public void clearMapGetLastMessage() {
        System.out.println(UserFunction.getLast_message());
        Iterator<Map.Entry<Long, UserFunction.UserForMap>> entryIterator = getLast_message().entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Long, UserFunction.UserForMap> entry = entryIterator.next();
            if (entry.getValue().getLocalDateTime().plusMinutes(5).isAfter(LocalDateTime.now())) {
                UserFunction.last_message_clear(entry.getKey());
            }
        }
    }
}
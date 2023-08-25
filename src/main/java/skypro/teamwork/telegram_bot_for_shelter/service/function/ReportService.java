package skypro.teamwork.telegram_bot_for_shelter.service.function;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;
import skypro.teamwork.telegram_bot_for_shelter.exceptions.UploadFileException;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.Pet;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.PhotoPetReport;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.ReportPet;
import skypro.teamwork.telegram_bot_for_shelter.repository.pets.PetRepository;
import skypro.teamwork.telegram_bot_for_shelter.repository.pets.PhotoPetReportRepository;
import skypro.teamwork.telegram_bot_for_shelter.repository.pets.ReportPetRepository;
import skypro.teamwork.telegram_bot_for_shelter.repository.user.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * класс для реализации методов чтобы принять ежедневный отчет о животном от пользователя в течение испытательного срока
 */
@Service
public class ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private final PetRepository petRepository;
    private final PhotoPetReportRepository photoPetReportRepository;
    private final ReportPetRepository reportPetRepository;
    private final UserRepository userRepository;
    public String petPassport;
    public String textReport;

    @Value("${bot.token}")
    private String token;
    @Value("${service.file_info.uri}")
    private String fileInfoUri;
    @Value("${service.file_storage.uri}")
    private String fileStorageUri;

    /**
     * здесь хранятся пользователи, которые обязаны присылать ежедневный отчет о животном в течение испытательного срока
     */
    public Set<Long> activeReportUsers = new HashSet<>(Set.of());

    public ReportService(PetRepository petRepository, PhotoPetReportRepository photoPetReportRepository, ReportPetRepository reportPetRepository, UserRepository userRepository) {
        this.petRepository = petRepository;
        this.photoPetReportRepository = photoPetReportRepository;
        this.reportPetRepository = reportPetRepository;
        this.userRepository = userRepository;
    }

    /**
     * метод работает с хешсетом по принципу шлагбаума, если человек в сете значит режим сдачи отчета активен
     * если его нет в сете то добавляет, если он есть в сете то удаляет при сл вызове метода
     *
     * @param chatId идентификатор чата пользователя
     *               проверяется его наличие в списке пользователей
     */
    public void activeReportCheck(long chatId) {
        logger.info("Вызван метод обработки отчета пользователя об опеке");

        if (!activeReportUsers.contains(chatId)) {
            activeReportUsers.add(chatId);
            logger.info("Пользователь входит в режим сдачи отчета");

        } else {
            activeReportUsers.remove(chatId);
            logger.info("Пользователь выводится из режима сдачи отчета");
        }
    }

    /**
     * основной метод данного класса
     * Получает по из апдейта файл айди, вызывает метод нахождения файла по файл айди
     * вызывает метод превращения полученного файла в массив байтов
     * создает сущность отчета наполняя его информацией для создания отчета
     *
     * @param update входящее сообщение пользователя
     */
    public void processDoc(Update update) {
        String fileId = update.getMessage().getPhoto().get(2).getFileId();
        ResponseEntity<String> response = getFilePath(fileId);

        petPassport = extractPetPassport(update.getMessage().getCaption());

        try {
            logger.info((petRepository.findByPetPassport(petPassport).getName()));
        } catch (NullPointerException e) {
            logger.info("petRepository.findByPetPassport(petPassport).getName() null pointer");
        }

        Pet pet = petRepository.findByPetPassport(petPassport);

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonObject = new JSONObject(response.getBody());
            String filePath = String.valueOf(jsonObject.getJSONObject("result").getString("file_path"));
            byte[] fileInByte = downloadFile(filePath);
            textReport = update.getMessage().getCaption();

            // создаем объект reportPet и сохраняем его в базе данных
            ReportPet reportPet = new ReportPet();

            reportPet.setRept(textReport);
            reportPet.setPet(pet);
            reportPet.setTime(LocalDateTime.now());
            reportPetRepository.save(reportPet);

            // создаем объект PhotoPetReport и устанавливаем свойства, включая reportPet
            PhotoPetReport photoPetReport = new PhotoPetReport();
            photoPetReport.setFilePath(filePath);
            photoPetReport.setFileSize(update.getMessage().getPhoto().get(2).getFileSize());
            photoPetReport.setMediaType("photo");
            photoPetReport.setData(fileInByte);
            photoPetReport.setReportPet(reportPet);
            photoPetReportRepository.save(photoPetReport);

            reportPet.setPhotoPetsReport(photoPetReport);
            logger.info("Пришло сообщение: " + update.getMessage().getCaption());
        }
    }

    /**
     * извлекает из сообщения текст и из него вычленяет номер паспорта
     *
     * @param incomeText входящее сообщение
     * @return номер паспорта
     */
    public static String extractPetPassport(String incomeText) {
        Pattern pattern = Pattern.compile("(\\d{6})\\s(.*)");
        Matcher matcher = pattern.matcher(incomeText);

        if (matcher.matches()) {
            String passport = matcher.group(1);
            logger.info("Номер паспорта: " + passport);
            return passport;

        } else {
            throw new IllegalArgumentException("Номер паспорта не найден в сообщении");
        }
    }

    /**
     * извлекает из входящего отчета путь к фотографии из телеграмма
     *
     * @param fileId идентификатор файла в телеграмме, содержащего фотографию
     * @return возвращает путь к файлу
     */
    public ResponseEntity<String> getFilePath(String fileId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(headers);

        return restTemplate.exchange(
                fileInfoUri,
                HttpMethod.GET,
                request,
                String.class,
                token, fileId
        );
    }

    /**
     * скачивает фотографию отчета в базу данных
     *
     * @param filePath путь к файлу
     * @return возвращает массив байтов
     */
    public byte[] downloadFile(String filePath) {
        String fullUri = fileStorageUri.replace("{bot.token}", token)
                .replace("{filePath}", filePath);
        URL urlObj = null;
        try {
            urlObj = new URL(fullUri);
        } catch (MalformedURLException e) {
            throw new UploadFileException(e);
        }

        try (InputStream is = urlObj.openStream()) {
            return is.readAllBytes();
        } catch (IOException e) {
            throw new UploadFileException(urlObj.toExternalForm(), e);
        }
    }

    /**
     * метод проверяет в базе есть ли питомец с подходящим номером паспорта, если нет отчет бракуется
     *
     * @param chatId
     * @return
     */
    public boolean verifyUserByChatId(Long chatId) {
        return userRepository.findById(chatId).isPresent();
    }

    /**
     * метод проверяет в базе есть ли пользователь с таким чат айди, если нет, то прием отчета не начинается
     */
    public boolean verifyPetPassport(Update update) {
        petPassport = extractPetPassport(update.getMessage().getCaption());
        try {
            petRepository.findByPetPassport(petPassport).getPetPassport();
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * метод проверяет являются ли первые 6 знаков в сообщении номером паспорта питомца,
     * нужен чтобы лишний раз не обращаться к базе с очевидно неправильным номером паспорта
     */
    public static boolean verifyPetPassportWithoutErrors(Update update) {
        String incomeText = update.getMessage().getCaption();

        Pattern pattern = Pattern.compile("(\\d{6})\\s(.*)");
        try {
            Matcher matcher = pattern.matcher(incomeText);
            if (matcher.matches()) {
                String passport = matcher.group(1);
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }
}
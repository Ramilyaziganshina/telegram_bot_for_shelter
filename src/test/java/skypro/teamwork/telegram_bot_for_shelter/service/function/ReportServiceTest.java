package skypro.teamwork.telegram_bot_for_shelter.service.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.PhotoPetReport;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.ReportPet;
import skypro.teamwork.telegram_bot_for_shelter.repository.pets.PetRepository;
import skypro.teamwork.telegram_bot_for_shelter.repository.pets.PhotoPetReportRepository;
import skypro.teamwork.telegram_bot_for_shelter.repository.pets.ReportPetRepository;
import skypro.teamwork.telegram_bot_for_shelter.repository.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static skypro.teamwork.telegram_bot_for_shelter.service.function.ReportService.extractPetPassport;

@ContextConfiguration(classes = {ReportService.class})
@ExtendWith({MockitoExtension.class, SpringExtension.class})
class ReportServiceTest {
    // Навешиваю зависимости
    @MockBean
    private PetRepository petRepository;
    @MockBean
    private PhotoPetReportRepository photoPetReportRepository;
    @MockBean
    private ReportPetRepository reportPetRepository;
    @Autowired
    private ReportService reportService;
    @MockBean
    private UserRepository userRepository;

    // Эту аннотацию использую, чтобы каждый раз создавать объект reportService перед каждым запуском метода теста
    @BeforeEach
    void setUp() {
        reportService = new ReportService(petRepository, photoPetReportRepository, reportPetRepository, userRepository);
    }

    /**
     * Это тест проверяет функциональность метда activeReportCheck.
     * Первый assert проверяет, что до вызова метода activeReportCheck, поле activeReportUsers, в HashMap отчета, пустое.
     * Затем, метод activeReportCheck вызывается и передает ему chatId.
     * А следующие два assert проверяют, что после вызова метода activeReportCheck, поле activeReportUsers содержит chatId как
     * ключ и значчение этого ключа равно 1.
     * Короч тест проверяет коректность работы метода activeReportCheck при создании нового чатаОтчета в поле
     * activeReportUsers с chatId в качестве ключа.
     */
    @Test
    public void activeReportCheck_newUser() {
        long chatId = 1L;
        assertThat(reportService.activeReportUsers).isEmpty();
        reportService.activeReportCheck(chatId);
        assertThat(reportService.activeReportUsers).contains(chatId);
        assertThat(reportService.activeReportUsers).contains(1L);
    }

    /**
     * Этот тест проверяет метод activeReportCheck,
     * который, используется для проверки активных отчетов юзеров в чате.
     * Вначалле Тест проверяет что список пуст.
     * Затем вызывается метод activeReportCheck(chatId) двараза, который
     * должен добавлять юзера в спсок активных отчетов.
     * Дальше тест снова проверяет что список activeReportUsers все еще пуст.
     * Крч этот тест проверяет, что метод activeReportCheck правильно добавляет юзера в
     * список активных отчетов, и что список имеющихся отчетов корректно очищается после завершения метода.
     */
    @Test
    public void activeReportCheck_userOneMessage() {
        long chatId = 1L;
        assertThat(reportService.activeReportUsers).isEmpty();
        reportService.activeReportCheck(chatId);
        reportService.activeReportCheck(chatId);
        assertThat(reportService.activeReportUsers).isEmpty();
    }

    /**
     * этот тест проверяет, что метод activeReportCheck коректно добавляет юзера
     * в список активных отчетов и закрепляет нужное колличество сообщений для юзера в списке.
     */
    @Test
    public void activeReportCheck_userMultipleMessages() {
        long chatId = 1L;
        assertThat(reportService.activeReportUsers).isEmpty();
        reportService.activeReportCheck(chatId);
        reportService.activeReportCheck(chatId);
        reportService.activeReportCheck(chatId);
        assertThat(reportService.activeReportUsers).contains(chatId);
        assertThat(reportService.activeReportUsers).contains(1L);
    }

    /**
     * Тест создается неверный объект Update, который не содержит сообщения. Затем выполняется метод
     * processDoc и проверяется, было ли брошено исключение НулПоинтерЭксепшен в результате попытки
     * вызвать методы на null объектах.
     * Также в этом тесте проверяется что ни какие методы репозиторев не были вызваны
     */
    @Test
    public void processDoc_invalidUpdate() {
        // Входные параметры (создаем неверный обьект который не содержит сообщения)
        Message message = new Message();
        message.setText("fake message");
        Update update = new Update();
        update.setMessage(message);

        // действия ( вызываем метод processDoc и проверяем был ли брошен НулПоинтерЭксепшен)
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            reportService.processDoc(update);
        });

        // проверка выполнения
        assertNotNull(exception);
        assertEquals(exception.getMessage(),
                exception.getMessage());

        // использую verify чтоб убедиться что никакие методы репозиториев не были вызваны,
        // так как обработка была прервана до их вызова.
        verify(petRepository, never()).findByPetPassport(anyString());
        verify(reportPetRepository, never()).save(any(ReportPet.class));
        verify(photoPetReportRepository, never()).save(any(PhotoPetReport.class));
    }

    /**
     * Тест проверяет, что метод правильно извлекает паспорт из строки в правильном форматее
     */
    @Test
    void shouldReturnPetPassport() {
        // Входные данные
        String input = "123456 Some text";
        String expectedOutput = "123456";

        // Актуальные
        String actualOutput = extractPetPassport(input);

        // Проверка
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * тест проверяет, что метод выбрасывает эксепшенIllegalArgumentException при попытке проверить строку,
     * которая не соответствует формату вввода.
     */
    @Test
    void testExtractPetPassport_exception() {
        // Верный ввод
        String input1 = "123456 Бла-бла-бла";
        String output1 = "123456";
        assertEquals(output1, extractPetPassport(input1));

        // Неверный Ввод
        String input2 = "Некоректный текст";
        assertThrows(IllegalArgumentException.class, () -> extractPetPassport(input2));
    }
}
package skypro.teamwork.telegram_bot_for_shelter.service.service_database.pets;

import org.springframework.stereotype.Service;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.Pet;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.ReportPet;
import skypro.teamwork.telegram_bot_for_shelter.repository.pets.ReportPetRepository;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Сервис для реализации методов для ежедневных отчетов по питомцу, присылаемых усыновителем в испытательный период
 */
@Service
public class ReportPetService {
    private final ReportPetRepository reportPetRepository;

    public ReportPetService(ReportPetRepository reportPetRepository) {
        this.reportPetRepository = reportPetRepository;
    }

    /**
     * метод для добавления очета в базу данных
     *
     * @param reportPet отчет, который необходимо добавить
     * @return добавленный отчет
     */
    public ReportPet addReportPet(ReportPet reportPet) {
        return reportPetRepository.save(reportPet);
    }

    /**
     * метод для поиска отчета по идентификатору
     *
     * @param id идентификатор отчета
     * @return найденный отчет
     */
    public ReportPet findReportPet(long id) {
        return reportPetRepository.findById(id).orElse(null);
    }

    /**
     * находит отчет с таким же идентификатором и заменяет его
     *
     * @param reportPet все параметры отчета по питомцу
     * @return питомца, которого внесли в базу данных
     */
    public ReportPet editReportPet(ReportPet reportPet) {
        return reportPetRepository.save(reportPet);
    }

    /**
     * находит питомца по идентификатору и удаляет его
     *
     * @param id идентификатор питомца
     */
    public void deletePet(long id) {
        reportPetRepository.deleteById(id);
    }

    /**
     * выводит коллекцию всех питомцев
     *
     * @return коллекция всех питомцев
     */
    public Collection<ReportPet> getAll() {
        return reportPetRepository.findAll();
    }

    /**
     * получить отчет из базы данных по Pet и LocalDateTime
     * используется метод ReportPetRepository  {@link ReportPetRepository#findReportPetByPetAndTime(Pet, LocalDate)} (<ReportPet>)}
     *
     * @param pet          (object)
     * @param dateOfReport дата отчета
     * @return ReportPet с User And dateOfReport
     */
    public ReportPet findReportPetByPetAndTime(Pet pet, LocalDate dateOfReport) {
        return reportPetRepository.findReportPetByPetAndTime(pet, dateOfReport);
    }
}
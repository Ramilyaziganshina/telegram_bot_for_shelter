package skypro.teamwork.telegram_bot_for_shelter.service.service_database.pets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.PhotoPetReport;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.ReportPet;
import skypro.teamwork.telegram_bot_for_shelter.repository.pets.PhotoPetReportRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * Сервис для реализации методов для фотографий из ежедневного отчета по питомцу, присылаемого пользователем в испытательный период
 */
@Service
@Transactional
public class PhotoPetsReportService {

    /**
     * путь к папке photopetsreport, в котором хранятся фотографии
     */
    @Value("${path.to.photopatsreport.folder}")
    private String photoPetsReportDir;

    private final ReportPetService reportPetService;
    private final PhotoPetReportRepository photoPetReportRepository;

    public PhotoPetsReportService(PhotoPetReportRepository photoPetReportRepository, ReportPetService reportPetService) {
        this.photoPetReportRepository = photoPetReportRepository;
        this.reportPetService = reportPetService;
    }

    /**
     * находит фотографию по идентификатору отчета
     *
     * @param reportPetId идентификатор отчета по питомцу
     * @return фотографию, прикрепленную к отчету с указанным идентификатором
     */
    public PhotoPetReport findPhotoByReportPet(long reportPetId) {
        return photoPetReportRepository.findPhotoPetReportById(reportPetId).getPhotoPetsReport();
    }

    /**
     * @param reportPetId находит отчет по питомцу по идентификатору
     * @param file        к этому отчету прикрепляет фото, если он там уже есть, заменяет
     * @throws IOException генерирует, если случилась какая-либо ошибка ввода-вывода
     */
    public void uploadPhotoPetsReport(Long reportPetId, MultipartFile file) throws IOException {
        ReportPet reportPet = reportPetService.findReportPet(reportPetId);

        Path filePath = Path.of(photoPetsReportDir, reportPetId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

        PhotoPetReport photoPetReport = findPhotoByReportPet(reportPetId);
        photoPetReport.setReportPet(reportPet);
        photoPetReport.setFilePath(filePath.toString());
        photoPetReport.setFileSize(file.getSize());
        photoPetReport.setMediaType(file.getContentType());
        photoPetReport.setData(file.getBytes());

        photoPetReportRepository.save(photoPetReport);
    }

    /**
     * выявляет расширение файла путем выделения буков после точки в названии
     *
     * @param fileName название файла
     * @return расширение
     */
    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * генерирует уменьшенную версию фотографии для просмотра
     *
     * @param filePath путь к файлу
     * @return массив байтов
     * @throws IOException ошибка ввода-вывода, если что-то пошло не так
     */
    public byte[] generateImagePreview(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }
}
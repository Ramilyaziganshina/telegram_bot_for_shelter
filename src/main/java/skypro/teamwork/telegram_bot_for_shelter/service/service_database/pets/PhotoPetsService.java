package skypro.teamwork.telegram_bot_for_shelter.service.service_database.pets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.Pet;
import skypro.teamwork.telegram_bot_for_shelter.model.pet.PhotoPet;
import skypro.teamwork.telegram_bot_for_shelter.repository.pets.PhotoPetsRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * сервис для реализации методов для фотографий питомцев
 */
@Service
@Transactional
public class PhotoPetsService {

    /**
     * путь к папке photopets, в котором хранятся фотографии
     */
    @Value("${path.to.photopats.folder}")
    private String photoPetsDir;

    private final PetService petService;
    private final PhotoPetsRepository photoPetsRepository;

    public PhotoPetsService(PhotoPetsRepository photoPetsRepository, PetService petService) {
        this.photoPetsRepository = photoPetsRepository;
        this.petService = petService;
    }

    /**
     * @param pet_id находит фотографию питомца по идентификатору питомца
     * @return фотографию питомца
     */
    public PhotoPet findPhotoByPet(long pet_id) {
        return photoPetsRepository.findPhotoPetById(pet_id).getPhotoPets();
    }

    /**
     * метод для загрузки в базу данных фотографии питомца
     *
     * @param petId идентификатор питомца
     * @param file  файл, который необходимо загрузить
     *              питомцу присваивается фотография, если она уже есть, заменяется
     * @throws IOException генерирует ошибку ввода-вывода, если что-то пошло не так
     */
    public void uploadPhotoPets(Long petId, MultipartFile file) throws IOException {
        Pet pet = petService.findPet(petId);

        Path filePath = Path.of(photoPetsDir, petId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

        PhotoPet photoPets = findPhotoByPet(petId);
        photoPets.setPet(pet);
        photoPets.setFilePath(filePath.toString());
        photoPets.setFileSize(file.getSize());
        photoPets.setMediaType(file.getContentType());
        photoPets.setData(file.getBytes());

        photoPetsRepository.save(photoPets);
    }

    /**
     * получает расширение файла
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
     * @throws IOException генерирует ошибку ввода-вывода, если что-то пошло не так
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
package skypro.teamwork.telegram_bot_for_shelter.service.service_database.user;

import org.springframework.stereotype.Service;
import skypro.teamwork.telegram_bot_for_shelter.model.user.User;
import skypro.teamwork.telegram_bot_for_shelter.repository.user.UserRepository;

import java.util.Collection;

/**
 * сервис для реализации методов для пользователей
 */
@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * добавляет пользователя в базу данных
     *
     * @param user пользователь для ввода в базу
     * @return пользователя, который внесен в базу данных
     */
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /**
     * метод для поиска пользователя по идентификатору
     *
     * @param id идентификатор пользователя
     * @return возвращает найденного пользователя
     */
    public User findUser(long id) {
        return userRepository.findById(id).get();
    }

    /**
     * метод находит пользователя по идентификатору и заменяет его данные новыми данными
     *
     * @param user все параметры класса user
     * @return пользователя, которого добавили
     */
    public User editUser(User user) {
        return userRepository.save(user);
    }

    /**
     * метод для удаления пользователя
     *
     * @param id идентификатор пользователя
     */
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    /**
     * метод для вывода всех пользователей
     *
     * @return возвращает коллекцию всех пользователей из базы данных
     */
    public Collection<User> getAll() {
        return userRepository.findAll();
    }
}
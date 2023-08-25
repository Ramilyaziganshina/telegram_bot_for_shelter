package skypro.teamwork.telegram_bot_for_shelter.service.function;

import org.springframework.stereotype.Service;
import skypro.teamwork.telegram_bot_for_shelter.model.user.User;
import skypro.teamwork.telegram_bot_for_shelter.service.service_database.user.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * этот сервис создает временную базу, в котором хранятся пользователи
 * с того момента как решили связаться с волонтёром и до того как
 * будут внесены в полноценную базу пользователей или будут удалены по какой-либо причине
 */
@Service
public class UserFunction {

    /**
     * класс создания модели пользователей, которые захотели связаться с волонтёром
     * и прислали свои контактные данные, но еще не внесены в модель усыновителей
     * временная база пользователей
     */
    static class UserForMap {
        private LocalDateTime localDateTime;
        private String messageCommand;
        private int idMessageToUser;

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }

        public String getMessageCommand() {
            return messageCommand;
        }

        public void setMessageCommand(String messageCommand) {
            this.messageCommand = messageCommand;
        }

        public int getIdMessageToUser() {
            return idMessageToUser;
        }

        public void setIdMessageToUser(int idMessageToUser) {
            this.idMessageToUser = idMessageToUser;
        }

        @Override
        public String toString() {
            return "UserForMap{" +
                    "localDateTime=" + localDateTime +
                    ", messageCommand='" + messageCommand + '\'' +
                    ", idMessageToUser=" + idMessageToUser +
                    '}';
        }
    }

    // Сохранение сообщения отправленное ботом для изменения меню приюта
    private static long messageID;
    private UserService userService;

    private static Map<Long, UserForMap> last_message = new HashMap<>();

    public UserFunction(UserService userService) {
        this.userService = userService;
    }

    public static long getMessageID() {
        return messageID;
    }

    public static void setMessageID(long messageID) {
        UserFunction.messageID = messageID;
    }

    public static Map<Long, UserForMap> getLast_message() {
        return last_message;
    }

    public static void setLastMessage(Long chat_id, LocalDateTime localDateTime, int idMessageToUser, String message) {
        if (chat_id != null && message != null) {
            UserForMap userForMap = new UserForMap();
            userForMap.setLocalDateTime(localDateTime);
            userForMap.setIdMessageToUser(idMessageToUser);
            userForMap.setMessageCommand(message);
            last_message.put(chat_id, userForMap);
        }
    }

    /**
     * удаляет из временной базы пользователей
     *
     * @param chatId идентификатор пользователя в телеграмм
     */
    public static void last_message_clear(Long chatId) {
        last_message.entrySet().removeIf(entry -> entry.getKey().equals(chatId));
    }

    /**
     * вносит в модель полноценных пользователей, взявших из приюта животное
     *
     * @param chatId  идентификатор чата с пользователем в телеграмме
     * @param contact контактные данные пользователя
     * @param name    имя пользователя
     */
    public void saveUserInDB(Long chatId, String contact, String name) {
        User user = new User();
        user.setChatId(chatId);
        user.setContact(contact);
        user.setName(name);

        userService.addUser(user);
    }
}
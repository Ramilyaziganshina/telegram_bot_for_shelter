package skypro.teamwork.telegram_bot_for_shelter.service.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import skypro.teamwork.telegram_bot_for_shelter.model.user.User;
import skypro.teamwork.telegram_bot_for_shelter.service.service_database.user.UserService;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserFunctionTest {

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUserInDB() {
        Long chatId = 12345L;
        String contact = "test_contact";
        String name = "test_name";
        UserFunction userFunction = new UserFunction(userService);

        userFunction.saveUserInDB(chatId, contact, name);

        verify(userService, times(1)).addUser(any(User.class));
    }

    @Test
    public void testSetLastMessage() {
        Long chatId = 12345L;
        LocalDateTime localDateTime = LocalDateTime.now();
        String message = "test_message";
        UserFunction.setLastMessage(chatId, localDateTime, 1, message);

        UserFunction.UserForMap userForMap = UserFunction.getLast_message().get(chatId);

        assertThat(userForMap.getLocalDateTime()).isEqualTo(localDateTime);
        assertThat(userForMap.getMessageCommand()).isEqualTo(message);
    }

    @Test
    public void testLastMessageClear() {
        Long chatId = 12345L;
        UserFunction.setLastMessage(chatId, LocalDateTime.now(), 1, "test_message");

        UserFunction.last_message_clear(chatId);

        assertThat(UserFunction.getLast_message().containsKey(chatId)).isFalse();
    }
}
import com.library.validation.impl.GeneralValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    @Test
    void testUsingUnicodeRegex() {
        String emailAddress = "bugra@burunguz.com";
        String regexPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        assertTrue(GeneralValidator.validateEmail(emailAddress), regexPattern);
    }
}

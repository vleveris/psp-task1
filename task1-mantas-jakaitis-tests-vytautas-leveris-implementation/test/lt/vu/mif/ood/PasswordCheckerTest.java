package lt.vu.mif.ood;

import lt.vu.mif.ood.exceptions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordCheckerTest {

    PasswordChecker passwordChecker;

    @BeforeEach
    void setUp() {
         passwordChecker = new PasswordChecker();
    }

    @Test
    public void validPassword() {
        assertDoesNotThrow(() -> passwordChecker.validate("Password123!"));
    }

    @Test
    public void passwordIsNull() {
        assertThrows(NullPasswordException.class, () -> passwordChecker.validate(null));
    }

    @Test
    public void passwordOfInvalidLength() {
        assertThrows(InvalidPasswordLengthException.class, () -> passwordChecker.validate("Pass"));
    }

    @Test
    public void passwordDoesNotContainUpperCase() {
        assertThrows(LowercasePasswordException.class, () -> passwordChecker.validate("password123!"));
    }

    @Test
    public void passwordDoesNotContainSpecialChar() {
        assertThrows(SpecialCharacterNotFoundInPasswordException.class, () -> passwordChecker.validate("paS5word123"));
    }
}

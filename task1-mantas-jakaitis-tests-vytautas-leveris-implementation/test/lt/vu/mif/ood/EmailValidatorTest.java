package lt.vu.mif.ood;

import lt.vu.mif.ood.exceptions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailValidatorTest {

    EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    @Test
    public void validEmail() {
        assertDoesNotThrow(() -> emailValidator.validate("mjakaitis18@gmail.com"));
    }

    @Test
    public void emailIsNull() {
        assertThrows(NullEmailException.class, () -> emailValidator.validate(null));
    }

    @Test
    public void emailDoesNotContainAddressSign() {
        assertThrows(EmailWithoutAtSymbolException.class, () -> emailValidator.validate("mjakaitisgmail.com"));
    }

    @Test
    public void emailContainsInvalidSymbols() {
        assertThrows(EmailContainsInvalidSymbolsException.class, () -> emailValidator.validate("m.jakait (,\"is99@gmail.com"));
    }

    @Test
    public void emailHasInvalidDomain() {
        assertThrows(EmailHasInvalidDomainException.class, () -> emailValidator.validate("mjakaitis18@gmail.co-%kk"));
    }
}

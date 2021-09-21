package mif.psp.tasks.library;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PasswordCheckerTest {
    @BeforeAll
    static void setUp() {
        PasswordChecker passwordChecker = new PasswordChecker();
        List<Character> specialCharacters = new ArrayList<>();
        specialCharacters.add('/');
        specialCharacters.add(' ');
        specialCharacters.add('?');
        specialCharacters.add('_');
        specialCharacters.add('%');
        passwordChecker.setSpecialCharacters(specialCharacters);
    }

    @Test
    public void passwordNull() {
        assertFalse(passwordChecker.validate(null));
    }

    @Test
    public void passwordEmpty() {
        assertFalse(passwordChecker.validate(""));
    }

    @Test
    public void passwordTooWeak() {
        assertFalse(passwordChecker.validate("LAbas 1"));
    }

    @Test
    public void missingUppercaseLetter() {
        assertFalse(passwordChecker.validate("vie?nas_123"));
    }

    @Test
    public void missingLowercaseLetter() {
        assertFalse(passwordChecker.validate("VIE_     NAS123"));
    }

    @Test
    public void missingSymbol() {
        assertFalse(passwordChecker.validate("VIENasdu3"));
    }

    @Test
    public void missingNumber() {
        assertFalse(passwordChecker.validate("VIEn__%as"));
    }

    @Test
    public void passwordOk() {
        assertTrue(passwordChecker.validate("LAbAS R1"));
    }

    @Test
    public void passwordLongerOk() {
        assertTrue(passwordChecker.validate("VVVVV   ?___ieieeejfVl555EAa./4422513''"));
    }

    @Test
    public void canContainUnicodeCharacters() {
        assertTrue(passwordChecker.validate("MANO_??/labai1556ĮšššmanūūūūųžžsisSLAptAžŽŽžžžžodis"));
    }

    @Test
    public void addSpecialCharacter() {
        passwordChecker.addSpecialCharacter('\t');
        assertTrue(passwordChecker.validate("EEEEjfjfjf\t123456"));
    }

    @Test
    public void removeSpecialCharacter() {
        passwordChecker.remove('?');
        assertFalse(passwordChecker.validate("FFFFffff?456"));
    }

}
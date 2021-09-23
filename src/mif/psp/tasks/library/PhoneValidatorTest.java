package mif.psp.tasks.library;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PhoneValidatorTest {
    @BeforeAll
    static void setUp() {
        PhoneValidator phoneValidator = new PhoneValidator();
        CountryRule lithuania = new CountryRule("LT", 12, "+370");
        phoneValidator.addCountryRule(lithuania);
    }

    @Test
    public void numberContainsNonDigits() {
        assertFalse(phoneValidator.validate("(+370)6 455 555 9"));
    }

    @Test
    public void changeToCountryCode() {
        assertEquals("+37061234567", phoneValidator.formatNumberToString("LT", "861234567"));
    }

    @Test
    public void NumberOk() {
        assertTrue(phoneValidator.validate("+37054444444"));
        assertEquals("+37054444444", phoneValidator.formatNumberToString("LT", "+37054444444"));
    }

    @Test
    public void unknownRuleOrIncorrectNumber() {
        assertFalse(phoneValidator.validate("+12399999999"));
    }

    @Test
    public void newRule() {
        CountryRule exoticIsland = new CountryRule("exotic_island", 10, "+25");
        phoneValidator.addNewCountryRule(exoticIsland);

        assertEquals("+254455666", phoneValidator.formatNumberToString("egzotine_sala", "+254455666"));
    }


}
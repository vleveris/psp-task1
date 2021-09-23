package mif.psp.tasks.library;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EmailValidatorTest {
    @BeforeAll
    static void setUp() {
        EmailValidator emailValidator = new EmailValidator();
    }

    @Test
    public void missingAtSymbol() {
        assertFalse(emailValidator.validate("petras.petraitismif.stud.vu.lt"));
    }

    @Test
    public void invalidCharacter() {
        assertFalse(emailValidator.validate("jonas jon'<?php/>@gmail.com"));
    }

    @Test
    public void dotFirst() {
        assertFalse(emailValidator.validate(".klausk@lrt.lt"));
    }

    @Test
    public void dotLast() {
        assertFalse(emailValidator.validate("klausk@lrt.lt."));
    }

    @Test
    public void dotsConsecutive() {
        assertFalse(emailValidator.validate("klau..sk@lr%*t.lt"));
    }

    @Test
    public void mixedCaseInRecipiantName() {
        assertTrue(emailValidator.validate("PAgaLbA@topocentras.org"));
    }

    @Test
    public void hyphenInDomainName() {
        assertTrue(emailValidator.validate("keturi@penki-skaicius.lt"));
    }

    @Test
    public void unicodeCharactersInRecipientName() {
        assertFalse(emailValiator.validate("ramūnas.bėgikas@sportas.eu"));
    }

}
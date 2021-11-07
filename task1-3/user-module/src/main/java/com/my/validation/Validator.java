package com.my.validation;

import com.my.validation.implimentations.EmailValidatorImpl;
import com.my.validation.implimentations.PasswordCheckerImpl;
import com.my.validation.implimentations.PhoneValidatorImpl;

public class Validator {
    EmailValidatorImpl emailValidator = new EmailValidatorImpl();
    PhoneValidatorImpl phoneValidator = new PhoneValidatorImpl();
    PasswordCheckerImpl passwordValidator = new PasswordCheckerImpl();

    public boolean validateEmail(String email) {
        return emailValidator.checkEmail(email) && emailValidator.checkEmailSymbol(email) && emailValidator.checkDomainAndTLD(email);
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        return phoneValidator.checkPhoneNumber(phoneValidator.checkFirstNumber(phoneNumber));
    }

    public boolean validatePassword(String password) {
        return passwordValidator.checkLength(password) && passwordValidator.checkUpperCase(password) && passwordValidator.checkSpecialSymbol(password);
    }

    public void addPhoneNumberPrefixes(int length, String prefix) {
            phoneValidator.addPhonePrefix(length, prefix);
    }

    public void setPasswordValidatorSettings(int minLength, char[] specialSymbols) {
        passwordValidator.setMinPassWordLength(minLength);
        passwordValidator.setSpecialSymbols(specialSymbols);
    }
}

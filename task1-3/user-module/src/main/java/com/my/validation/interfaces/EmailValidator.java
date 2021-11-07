package com.my.validation.interfaces;

public interface EmailValidator {

    boolean checkEmailSymbol(String email);

    boolean checkEmail(String email);

    boolean checkDomainAndTLD(String email);
}

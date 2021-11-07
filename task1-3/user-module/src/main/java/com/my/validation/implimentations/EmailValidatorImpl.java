package com.my.validation.implimentations;

public class EmailValidatorImpl implements com.my.validation.interfaces.EmailValidator {

    public boolean checkEmailSymbol(String email) {
        if (email.contains("@")) {
            return true;
        }
        return false;
    }

    public boolean checkEmail(String email) {
        if (email.chars().allMatch(c -> c < 128)) {
            return true;
        }
        return false;
    }

    public boolean checkDomainAndTLD(String email) {
        if (!checkEmailSymbol(email)) {
            return false;
        }

        char[] emailCharList = email.toCharArray();
        int namePos = 0;
        int domainPos = 0;

        for (int i = 0; emailCharList[i] != '@'; i++) { // randa poziciją iki @
            namePos = i;
        }

        for (int i = namePos; (emailCharList[i] != '.') && i + 1 != email.length(); i++) { // randa domain poziciją iki tdl
            domainPos = i;
        }

        return emailCharList[domainPos + 1] != 0 && namePos > 0 && domainPos - namePos - 1 > 0; // pavadinimas > 0, domain > 0 ir tdl > 0
    }
}

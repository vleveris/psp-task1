package com.my.validation.interfaces;

public interface PasswordChecker {

    boolean checkLength(String password);

    boolean checkUpperCase(String password);

    boolean checkSpecialSymbol(String password);

    void setMinPassWordLength(int minPassWordLength);

    void setSpecialSymbols(char[] specialSymbols);
}

package com.my.validation.interfaces;

public interface PhoneValidator {

    boolean checkPhoneNumber(String phoneNumber);

    String checkFirstNumber(String phoneNumber);

    void addPhonePrefix(int length, String prefix);
}

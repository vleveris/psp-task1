package com.my.validation.implimentations;

import java.util.ArrayList;
import java.util.Arrays;

import com.my.model.phoneNumberPrefix;

public class PhoneValidatorImpl implements com.my.validation.interfaces.PhoneValidator {
    private final ArrayList<phoneNumberPrefix> prefixes = new ArrayList<>(Arrays.asList(
            new phoneNumberPrefix("+370", 12),
            new phoneNumberPrefix("8", 9)));

    public boolean checkPhoneNumber(String phoneNumber) {
        char[] phoneNumberCharList = phoneNumber.toCharArray();

        for (int i = 0; i < phoneNumber.length(); i++) {
            if (i != 0 && phoneNumberCharList[i] != '+') {
                if (!Character.isDigit(phoneNumberCharList[i])) {
                    return false;
                }
            }
        }

        for (phoneNumberPrefix prefix : prefixes) {
            if (phoneNumber.startsWith(prefix.getPrefix()) && phoneNumber.length() == prefix.getLength()) {
                return true;
            }
        }
        return false;
    }

    public String checkFirstNumber(String phoneNumber) {
        char[] phoneNumberCharList = phoneNumber.toCharArray();

        if (phoneNumberCharList[0] == '8') {
            char[] newNumerCharList = {'+', '3', '7', '0', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'};

            System.arraycopy(phoneNumberCharList, 1, newNumerCharList, 4, phoneNumberCharList.length - 1);
            return new String(newNumerCharList);
        }
        return phoneNumber;
    }

    public void addPhonePrefix(int length, String prefix) {
        prefixes.add(new phoneNumberPrefix(prefix, length));
    }
}

package com.my.model;

public class phoneNumberPrefix {
    String prefix;
    int length;

    public phoneNumberPrefix(String prefix, int length) {
        this.prefix = prefix;
        this.length = length;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getLength() {
        return length;
    }
}

package com.my.validation.implimentations;

public class PasswordCheckerImpl implements com.my.validation.interfaces.PasswordChecker {
	int minPassWordLength = 7; // default reikšmė, bet gali būti keičiama setteriu
	char[] specialSymbols = {'@'}; // default reikšmė, bet gali būti keičiama setteriu

	public boolean checkLength(String password) {
		if (password.length() >= minPassWordLength) {
			return true;
		}
		return false;
	}

	public boolean checkUpperCase(String password) {
		char[] passCharList = password.toCharArray();

		for(char passChar : passCharList) {
			if (Character.isUpperCase(passChar)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkSpecialSymbol(String password) {
		char[] passCharList = password.toCharArray();

		for (char passChar : passCharList) {
			for (char symbol : specialSymbols) {
				if (passChar == symbol) {
					return true;
				}
			}
		}
		return false;
	}

	public void setMinPassWordLength(int minPassWordLength) {
		this.minPassWordLength = minPassWordLength;
	}

	public void setSpecialSymbols(char[] specialSymbols) {
		this.specialSymbols = specialSymbols;
	}
}

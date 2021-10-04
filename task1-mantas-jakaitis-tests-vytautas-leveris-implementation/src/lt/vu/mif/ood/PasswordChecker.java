package lt.vu.mif.ood;

import java.util.ArrayList;
import java.util.List;

import lt.vu.mif.ood.exceptions.InvalidPasswordLengthException;
import lt.vu.mif.ood.exceptions.LowercasePasswordException;
import lt.vu.mif.ood.exceptions.NullPasswordException;
import lt.vu.mif.ood.exceptions.NumberNotFoundInPasswordException;
import lt.vu.mif.ood.exceptions.SpecialCharacterNotFoundInPasswordException;
import lt.vu.mif.ood.exceptions.UppercasePasswordException;
import lt.vu.mif.ood.exceptions.ValidationException;

/**
 * Validates a password
 */
public class PasswordChecker implements IValidator {
	private List<Character> specialSymbols = new ArrayList<>();
	private final int minLength = 8;

	public PasswordChecker() {
		specialSymbols.add(' ');
		specialSymbols.add('!');
		specialSymbols.add('\"');
		specialSymbols.add('#');
		specialSymbols.add('$');
		specialSymbols.add('%');
		specialSymbols.add('&');
		specialSymbols.add('\'');
		specialSymbols.add('(');
		specialSymbols.add(')');
		specialSymbols.add('*');
		specialSymbols.add('+');
		specialSymbols.add(',');
		specialSymbols.add('-');
		specialSymbols.add('.');
		specialSymbols.add('/');
		specialSymbols.add(':');
		specialSymbols.add(';');
		specialSymbols.add('<');
		specialSymbols.add('=');
		specialSymbols.add('>');
		specialSymbols.add('?');
		specialSymbols.add('@');
		specialSymbols.add('[');
		specialSymbols.add('\\');
		specialSymbols.add(']');
		specialSymbols.add('^');
		specialSymbols.add('_');
		specialSymbols.add('`');
		specialSymbols.add('{');
		specialSymbols.add('|');
		specialSymbols.add('}');
		specialSymbols.add('~');
	}

	/**
	 * @return list of special symbols
	 */
	public List<Character> getSpecialSymbols() {
		return specialSymbols;
	}

	/**
	 * Sets a list of special symbols
	 *
	 * @param specialSymbols current list of special symbols
	 */
	public void setSpecialSymbolList(List<Character> specialSymbols) {
		this.specialSymbols = specialSymbols;
	}

	/**
	 * Validates password or throws ValidationException
	 *
	 * @param password password to be validated
	 * @throws NullPasswordException                       if password is null
	 * @throws InvalidPasswordLengthException              if length is less than
	 *                                                     minimum
	 * @throws UppercasePasswordException                  if password doesn't
	 *                                                     contain lowercase letters
	 * @throws LowercasePasswordException                  if password doesn't
	 *                                                     contain uppercase letters
	 * @throws SpecialCharacterNotFoundInPasswordException if password doesn't
	 *                                                     contain special
	 *                                                     characters from the list
	 * @throws NumberNotFoundInPasswordException           if password doesn't
	 *                                                     contain numbers
	 */
	@Override
	public void validate(String password) throws ValidationException {
		if (password == null) {
			throw new NullPasswordException();
		}
		int length = password.length();
		if (length < minLength) {
			throw new InvalidPasswordLengthException();
		}
		boolean containsUppercase = false, containsLowercase = false, containsNumber = false, containsSpecial = false;
		for (int i = 0; i < length; i++) {
			char symbol = password.charAt(i);
			int code = symbol;
			if (specialSymbols.contains(symbol)) {
				containsSpecial = true;
			} else if (code > 47 && code < 58) {
				containsNumber = true;
			} else if (code > 64 && code < 91) {
				containsUppercase = true;
			} else if (code > 96 && code < 123) {
				containsLowercase = true;
			}
		}
		if (!containsLowercase) {
			throw new UppercasePasswordException();
		}
		if (!containsSpecial) {
			throw new SpecialCharacterNotFoundInPasswordException();
		}
		if (!containsUppercase) {
			throw new LowercasePasswordException();
		}
		if (!containsNumber) {
			throw new NumberNotFoundInPasswordException();
		}
	}
}

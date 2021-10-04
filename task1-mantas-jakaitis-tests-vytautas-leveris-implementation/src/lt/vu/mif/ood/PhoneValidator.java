package lt.vu.mif.ood;

import java.util.HashMap;
import java.util.Map;

import lt.vu.mif.ood.exceptions.IncorrectPhoneValidationInstructionException;
import lt.vu.mif.ood.exceptions.InvalidPhoneLengthException;
import lt.vu.mif.ood.exceptions.InvalidPhoneNumberException;
import lt.vu.mif.ood.exceptions.InvalidPhoneSymbolException;
import lt.vu.mif.ood.exceptions.NullPhoneException;
import lt.vu.mif.ood.exceptions.PhoneValidationInstructionNotFoundException;
import lt.vu.mif.ood.exceptions.ValidationException;

/**
 * Validates a phone number
 */
public class PhoneValidator implements IValidator {
	private Map<String, ValidationInstruction> validationInstructionMap = new HashMap<>();
	private String phoneNumber;

	/**
	 * Initialized with Lithuanian phone number validation instruction
	 */
	public PhoneValidator() {
		ValidationInstruction lithuania = new ValidationInstruction(8, "+370", "8");
		validationInstructionMap.put("lithuania", lithuania);
	}

	/**
	 * Adds new validation instruction
	 *
	 * @param name                  instruction name
	 * @param validationInstruction phone number validation instruction
	 */
	public void addValidationInstruction(String name, ValidationInstruction validationInstruction) {
		validationInstructionMap.put(name, validationInstruction);
	}

	/**
	 * @return phone number of last validated number or null if validation was
	 *         unsuccessful
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets validation instruction map
	 *
	 * @param validationInstructionMap new validation instruction map
	 */
	public void setValidationInstructions(Map<String, ValidationInstruction> validationInstructionMap) {
		this.validationInstructionMap = validationInstructionMap;
	}

	/**
	 * Validates phone number by the first matching validation instruction or throws
	 * ValidationException
	 *
	 * Sets phoneNumber field to the last validated number or null if validation was
	 * unsuccessful
	 *
	 * @param number phone number to be validated
	 * @throws PhoneValidationInstructionNotFoundException if no instruction matches
	 *                                                     given number
	 */
	@Override
	public void validate(String number) throws ValidationException {
		int exitCode = 2;
		if (number == null) {
			exitCode = 1;
		} else if (number.length() < 2) {
			exitCode = 3;
		} else {
			for (Map.Entry<String, ValidationInstruction> entry : validationInstructionMap.entrySet()) {
				exitCode = validateWithCode(number, entry.getKey());
				if (exitCode == 0) {
					break;
				}
			}
			if (exitCode != 0) {
				exitCode = -2;
			}
		}
		returnByExitCode(exitCode);
	}

	/**
	 * Validates phone number by a given validation instruction or throws
	 * ValidationException
	 *
	 * Sets phoneNumber field to the last validated number or null if validation was
	 * unsuccessful
	 *
	 * @param number      phone number to be validated
	 * @param instruction phone validation instruction
	 * @throws NullPhoneException                           if number is null
	 * @throws InvalidPhoneNumberException                  if number is not valid
	 *                                                      using given validation
	 *                                                      instruction
	 * @throws InvalidPhoneLengthException                  if number is too long or
	 *                                                      too short for a given
	 *                                                      validation instruction
	 * @throws InvalidPhoneSymbolException                  if number contains
	 *                                                      nondigit, it is not '+'
	 *                                                      and not in the first
	 *                                                      position
	 * @throws IncorrectPhoneValidationInstructionException if provided phone
	 *                                                      validation instruction
	 *                                                      is not correct
	 */
	public void validate(String number, String instruction) throws ValidationException {
		int exitCode = validateWithCode(number, instruction);
		returnByExitCode(exitCode);
	}

	private int validateWithCode(String number, String instruction) {
		phoneNumber = null;
		if (!validationInstructionMap.containsKey(instruction)) {
			return -1;
		}
		if (number == null) {
			return 1;
		}
		if (number.length() < 2) {
			return 3;
		}
		ValidationInstruction validationInstruction = validationInstructionMap.get(instruction);
		if (!validationInstruction.isValid()) {
			return -1;
		}
		int length = validationInstruction.getLength();
		String trunkPrefix = validationInstruction.getTrunkPrefix();
		String internationalPrefix = validationInstruction.getInternationalPrefix();
		boolean useInternational = number.charAt(0) == '+';
		String numberPrefix = internationalPrefix;
		if (!useInternational) {
			numberPrefix = trunkPrefix;
		}
		if (numberPrefix == null) {
			return 2;
		}
		boolean validationResult = validateLength(numberPrefix, length, number);
		if (!validationResult) {
			return 3;
		}
		validationResult = validatePrefix(numberPrefix, number);
		if (!validationResult) {
			return 2;
		}
		String numberPart = number.substring(numberPrefix.length(), number.length());
		validationResult = validateNumber(numberPart);
		if (!validationResult) {
			return 4;
		}
		if (!useInternational && internationalPrefix != null) {
			numberPrefix = internationalPrefix;
		}
		phoneNumber = numberPrefix + numberPart;
		return 0;
	}

	private boolean validateLength(String prefix, int length, String number) {
		int prefixLength = prefix.length();
		int numberLength = length + prefixLength;
		if (number.length() != numberLength) {
			return false;
		}
		return true;
	}

	private boolean validatePrefix(String prefix, String number) {
		int prefixLength = prefix.length();
		String numberPrefix = number.substring(0, prefixLength);
		return numberPrefix.equals(prefix);
	}

	private boolean validateNumber(String number) {
		for (int i = 0; i < number.length(); i++) {
			if (!Character.isDigit(number.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	private void returnByExitCode(int exitCode) throws ValidationException {
		switch (exitCode) {
		case 1:
			throw new NullPhoneException();
		case 2:
			throw new InvalidPhoneNumberException();
		case 3:
			throw new InvalidPhoneLengthException();
		case 4:
			throw new InvalidPhoneSymbolException();
		case -1:
			throw new IncorrectPhoneValidationInstructionException();
		case -2:
			throw new PhoneValidationInstructionNotFoundException();
		default:
			break;
		}
	}

}

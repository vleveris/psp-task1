package lt.vu.mif.ood;

import lt.vu.mif.ood.exceptions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneValidatorTest {

	PhoneValidator phoneValidator;
	Map<String, ValidationInstruction> validationInstructionMap;

	@BeforeEach
	void setUp() {
		phoneValidator = new PhoneValidator();
		validationInstructionMap = new HashMap<>();
	}

	@Test
	void validPhoneNumber() {
		ValidationInstruction validationInstructionLT = new ValidationInstruction(8, null, "8");
		validationInstructionMap.put("INSTRUCTIONLT", validationInstructionLT);
		phoneValidator.setValidationInstructions(validationInstructionMap);
		assertDoesNotThrow(() -> phoneValidator.validate("864256951", "INSTRUCTIONLT"));
	}

	@Test
	void validPhoneNumberWithoutInstruction() {
		assertDoesNotThrow(() -> phoneValidator.validate("864256951"));
	}

	@Test
	void validPhoneNumberWithPrefix() {
		ValidationInstruction validationInstructionLT = new ValidationInstruction(8, "+370", "8");
		validationInstructionMap.put("INSTRUCTIONLT", validationInstructionLT);
		phoneValidator.setValidationInstructions(validationInstructionMap);
		assertDoesNotThrow(() -> phoneValidator.validate("+37065211512", "INSTRUCTIONLT"));
	}

	@Test
	public void phoneIsNull() {
		assertThrows(NullPhoneException.class, () -> phoneValidator.validate(null));
	}

	@Test
	public void phoneContainsInvalidSymbols() {
		ValidationInstruction validationInstructionNo2 = new ValidationInstruction(12, "+320", "2");
		validationInstructionMap.put("INSTRUCTION2", validationInstructionNo2);
		phoneValidator.addValidationInstruction("INSTRUCTION2", validationInstructionNo2);
		assertThrows(InvalidPhoneSymbolException.class,
				() -> phoneValidator.validate("+320211AAB154469", "INSTRUCTION2"));
	}

	@Test
	public void phoneIsOfInvalidLength() {
		ValidationInstruction validationInstructionNo2 = new ValidationInstruction(12, "+320", "0");
		validationInstructionMap.put("INSTRUCTION2", validationInstructionNo2);
		phoneValidator.setValidationInstructions(validationInstructionMap);
		assertThrows(InvalidPhoneLengthException.class, () -> phoneValidator.validate("8621118A", "INSTRUCTION2"));
	}
}

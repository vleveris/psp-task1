package lt.vu.mif.ood;
import java.util.HashMap;
import java.util.Map;

import lt.vu.mif.ood.exceptions.*;

public class PhoneValidator implements IValidator{
	private Map<String, ValidationInstruction> validationInstructionMap = new HashMap<>();
	private String phoneNumber;

	public PhoneValidator()
{
	ValidationInstruction lithuania = new ValidationInstruction(8, "+370", "8");
	validationInstructionMap.put("lithuania", lithuania);
}

@Override
public void validate(String number) throws ValidationException
{
int exitCode = 2;
for (Map.Entry<String, ValidationInstruction> entry : validationInstructionMap.entrySet())
{
	exitCode = validateWithCode(number, entry.getKey());
	if (exitCode == 0)
		break;
}
returnByExitCode(exitCode);
}

public void validate(String number, String instruction) throws ValidationException
{
int exitCode = validateWithCode(number, instruction);
returnByExitCode(exitCode);
}

private int validateWithCode(String number, String instruction)
{
phoneNumber = null;
if (!validationInstructionMap.containsKey(instruction))
	return -1;
if (number == null)
	return 1;
if (number.length() <2)
	return 3;
ValidationInstruction validationInstruction = validationInstructionMap.get(instruction);
if (!validationInstruction.isValid())
	return -1;
int length = validationInstruction.getLength();
String trunkPrefix = validationInstruction.getTrunkPrefix();
String internationalPrefix = validationInstruction.getInternationalPrefix();
boolean useInternational = number.charAt(0) == '+';
String numberPrefix = internationalPrefix;
if (!useInternational)
numberPrefix = trunkPrefix;
if (numberPrefix == null)
	return 2;
boolean validationResult = validateLength(numberPrefix, length, number);
if (!validationResult)
	return 3;
validationResult = validatePrefix(numberPrefix, number);
if (!validationResult)
	return 2;
String numberPart = number.substring(numberPrefix.length(), number.length());
validationResult = validateNumber(numberPart);
if (!validationResult)
	return 4;
if (!useInternational && internationalPrefix != null)
	numberPrefix = internationalPrefix;
phoneNumber = numberPrefix + numberPart;
return 0;
}

private boolean validateLength(String prefix, int length, String number)
{
	int prefixLength = prefix.length();
	int numberLength = length+prefixLength;
if (number.length() != numberLength)
	return false;
return true;
}

private boolean validatePrefix(String prefix, String number)
{
	int prefixLength = prefix.length();
		String numberPrefix = number.substring(0, prefixLength);
	return numberPrefix.equals(prefix);
}

private boolean validateNumber(String number)
{
	for (int i=0;i<number.length(); i++)
		if (!Character.isDigit(number.charAt(i)))
			return false;
			
return true;
}

private void returnByExitCode(int exitCode) throws ValidationException
{
	switch (exitCode)
	{
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
	default:
			break;
	}
}

public void setValidationInstructions(Map<String, ValidationInstruction> validationInstructionMap)
{
	this.validationInstructionMap = validationInstructionMap;
}

public void addValidationInstruction(String name, ValidationInstruction validationInstruction)
{
	validationInstructionMap.put(name, validationInstruction);
}

public String getPhoneNumber()
{
	return phoneNumber;
}
}

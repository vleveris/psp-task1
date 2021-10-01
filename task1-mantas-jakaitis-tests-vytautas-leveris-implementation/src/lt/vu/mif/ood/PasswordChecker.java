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

public class PasswordChecker implements IValidator{
private List<Character> specialSymbols = new ArrayList<>();
public PasswordChecker()
{
	specialSymbols.add('!');
	specialSymbols.add('#');
	specialSymbols.add('$');
	specialSymbols.add('%');
	specialSymbols.add('&');
			specialSymbols.add('\'');
			specialSymbols.add('*');
			specialSymbols.add('+');
					specialSymbols.add('-');
					specialSymbols.add('/');
					specialSymbols.add('=');
					specialSymbols.add('?');
					specialSymbols.add('^');
					specialSymbols.add('_');
					specialSymbols.add('`');
					specialSymbols.add('{');
					specialSymbols.add('|');
					specialSymbols.add('}');
					specialSymbols.add('~');
specialSymbols.add(' ');
specialSymbols.add('.');
specialSymbols.add(',');
specialSymbols.add('?');
}

public void setSpecialSymbolList(List<Character> specialSymbols)
{
this.specialSymbols = specialSymbols;
}

public List<Character> getSpecialSymbols()
{
	return specialSymbols;
}

	@Override
public void validate(String password) throws ValidationException
{
if (password == null)
	throw new NullPasswordException();
int length = password.length();
if (length <8)
	throw new InvalidPasswordLengthException();
boolean containsUppercase = false, containsLowercase = false, containsNumber = false, containsSpecial = false;
for (int i=0;i<length; i++)
{
	char symbol = password.charAt(i);
	int code = (int) symbol;
	if (specialSymbols.contains(symbol))
		containsSpecial = true;
	else if (code>47 && code<58)
		containsNumber = true;
	else if (code>64 && code<91)
		containsUppercase = true;
	else if (code>96 && code<123)
		containsLowercase = true;
	}
if (!containsLowercase)
	throw new UppercasePasswordException();
if (!containsSpecial)
	throw new SpecialCharacterNotFoundInPasswordException();
if (!containsUppercase)
	throw new LowercasePasswordException();
if (!containsNumber)
	throw new NumberNotFoundInPasswordException();
}
}


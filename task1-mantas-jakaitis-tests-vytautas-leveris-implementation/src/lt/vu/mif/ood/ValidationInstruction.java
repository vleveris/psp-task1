package lt.vu.mif.ood;

public class ValidationInstruction {
private String internationalPrefix, trunkPrefix;
private int length;
private boolean valid = true;
public ValidationInstruction(int length, String internationalPrefix, String trunkPrefix)
{
	this.length = length;
this.internationalPrefix = internationalPrefix;
this.trunkPrefix = trunkPrefix;
validate();
}

public String getInternationalPrefix()
{
	return internationalPrefix;
}

public String getTrunkPrefix()
{
	return trunkPrefix;
}

public int getLength()
{
	return length;
}

public boolean isValid()
{
	return valid;
}

private void validate()
{
if (length <1)
	valid = false;
int i;
	if (trunkPrefix != null)
	{
		for (i=0; i<trunkPrefix.length(); i++)
	{
		char symbol = trunkPrefix.charAt(i);
		int code = (int)symbol;
		if (!(code >47 && code<58))
			valid = false;
	}
		if (length <= trunkPrefix.length())valid = false;
	}
	if (internationalPrefix != null)
	{
		if (!internationalPrefix.startsWith("+"))
			valid = false;
		for (i=1; i<internationalPrefix.length(); i++)
		{
			char symbol = internationalPrefix.charAt(i);
			int code = (int)symbol;
			if (!(code >47 && code<58))
				valid = false;
		}
		if (length <= internationalPrefix.length())
			valid = false;
		}
	if (trunkPrefix == null && internationalPrefix == null)
		valid = false;
}
}

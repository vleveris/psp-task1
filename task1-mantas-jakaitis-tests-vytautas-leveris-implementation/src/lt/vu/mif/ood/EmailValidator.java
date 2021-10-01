package lt.vu.mif.ood;

import java.util.ArrayList;
import java.util.List;

import lt.vu.mif.ood.exceptions.EmailContainsInvalidSymbolsException;
import lt.vu.mif.ood.exceptions.EmailHasInvalidDomainException;
import lt.vu.mif.ood.exceptions.EmailWithoutAtSymbolException;
import lt.vu.mif.ood.exceptions.NullEmailException;
import lt.vu.mif.ood.exceptions.ValidationException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmailValidator implements IValidator {
	private String username, domain;

	public String getUsername() {
		return username;
	}

	public String getDomain() {
		return domain;
	}

	@Override
	public void validate(String email) throws ValidationException {
		username = null;
		domain = null;
		if (email == null)
			throw new NullEmailException();

		int lastAt = email.lastIndexOf("@");
		if (lastAt == -1)
			throw new EmailWithoutAtSymbolException();
		String localPart = email.substring(0, lastAt + 1);
		String domain = email.substring(lastAt + 1, email.length());
		boolean usernameResult = validateLocalPart(localPart);
		boolean domainResult = false;
		if (usernameResult)
			domainResult = validateDomain(domain);
		if (!usernameResult)
			throw new EmailContainsInvalidSymbolsException();
		if (!domainResult)
			throw new EmailHasInvalidDomainException();
	}

	private boolean validateLocalPart(String localPart) {
		if (localPart.length() > 64)
			return false;

		int length = localPart.length();
		boolean validationResult;
		if (!localPart.startsWith("\""))
			validationResult = validateUnquoted(localPart);
		else if (localPart.endsWith("\\") && localPart.charAt(length - 2) != '\\')
			validationResult = validateQuoted(localPart.substring(1, length - 2));

		else
			return false;
		if (!validationResult)
			return false;
		this.username = localPart;

		return true;
	}

	private boolean validateUnquoted(String localPart) {
		List<Character> forbiddenSymbols = Stream.of('\"', '(', ')', ',', ':', ';', '<', '>', '[', '\\', ']')
				.collect(Collectors.toCollection(ArrayList::new));

		if (localPart.startsWith(".") || localPart.endsWith(".") || localPart.contains(".."))
			return false;
		for (int i = 0; i < localPart.length(); i++) {
			char symbol = localPart.charAt(i);
			int code = (int) symbol;
			if (code < 33 || code > 126)
				return false;
			if (forbiddenSymbols.contains(symbol))
				return false;
		}

		return true;
	}

	private boolean validateQuoted(String localPart) {
		int length = localPart.length();
		if (localPart.endsWith("\\") && localPart.charAt(length - 2) != '\\')
			return false;
		for (int i = 0; i < length; i++) {
			char symbol = localPart.charAt(i);
			if ((int) symbol > 126)
				return false;
			else if (symbol == '\\') {
				char nextSymbol = localPart.charAt(i + 1);
				if (nextSymbol == '\\' || nextSymbol == '"')
					i++;
				else
					return false;
			}
		}

		return true;
	}

	private boolean validateDomain(String domain) {
		if (domain.length() > 253)
			return false;
		String[] dnsLabels = domain.split("\\.", -1);
		for (String dns : dnsLabels) {
			int length = dns.length();
			if (length > 63 || length < 1)
				return false;
			if (dns.startsWith("-") || dns.endsWith("-"))
				return false;
			for (int i = 0; i < dns.length(); i++) {
				char symbol = dns.charAt(i);
				if (!(symbol == '-' || isLetterOrDigit(symbol)))
					return false;
			}
		}
		String topLevel = dnsLabels[dnsLabels.length - 1];
		boolean topLevelContainsLetter = false;
		for (int i = 0; i < topLevel.length(); i++) {
			char symbol = topLevel.charAt(i);
			if (symbol == '-' || isLetterOrDigit(symbol))
				topLevelContainsLetter = true;
		}
		if (!topLevelContainsLetter)
			return false;

		this.domain = domain;
		return true;
	}

	private boolean isLetterOrDigit(char s) {
		int code = (int) s;
		return ((code > 47 && code < 58) || (code > 64 && code < 91) || (code > 96 && code < 123));
	}
}
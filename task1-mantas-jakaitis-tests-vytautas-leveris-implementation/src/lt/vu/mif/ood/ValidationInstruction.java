package lt.vu.mif.ood;

/**
 * Phone number validation rule
 */
public class ValidationInstruction {
	private final String internationalPrefix, trunkPrefix;
	private final int length;
	private boolean valid = true;

	/**
	 * @param length              number length excluding prefix length
	 * @param internationalPrefix prefix used for calls from all the world
	 * @param trunkPrefix         prefix used for calls from the same country/state
	 */
	public ValidationInstruction(int length, String internationalPrefix, String trunkPrefix) {
		this.length = length;
		this.internationalPrefix = internationalPrefix;
		this.trunkPrefix = trunkPrefix;
		validate();
	}

	public String getInternationalPrefix() {
		return internationalPrefix;
	}

	public String getTrunkPrefix() {
		return trunkPrefix;
	}

	public int getLength() {
		return length;
	}

	/**
	 * @return true if instruction is valid, false otherwise
	 */
	public boolean isValid() {
		return valid;
	}

	private void validate() {
		if (length < 1) {
			valid = false;
		}
		int i;
		if (trunkPrefix != null) {
			for (i = 0; i < trunkPrefix.length(); i++) {
				char symbol = trunkPrefix.charAt(i);
				int code = symbol;
				if (!(code > 47 && code < 58)) {
					valid = false;
				}
			}
			if (length <= trunkPrefix.length()) {
				valid = false;
			}
		}
		if (internationalPrefix != null) {
			if (!internationalPrefix.startsWith("+")) {
				valid = false;
			}
			for (i = 1; i < internationalPrefix.length(); i++) {
				char symbol = internationalPrefix.charAt(i);
				int code = symbol;
				if (!(code > 47 && code < 58)) {
					valid = false;
				}
			}
			if (length <= internationalPrefix.length()) {
				valid = false;
			}
		}
		if (trunkPrefix == null && internationalPrefix == null) {
			valid = false;
		}
	}
}

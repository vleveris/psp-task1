package lt.vu.mif.ood;

import lt.vu.mif.ood.exceptions.ValidationException;

/**
 * Common interface for validator classes
 */
public interface IValidator {
	/**
	 * Validates given string
	 *
	 * @param content to be validated
	 * @throws ValidationException if validation is unsuccessful
	 */
	public void validate(String content) throws ValidationException;
}

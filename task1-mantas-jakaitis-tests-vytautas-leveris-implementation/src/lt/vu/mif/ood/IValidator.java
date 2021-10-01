package lt.vu.mif.ood;

import lt.vu.mif.ood.exceptions.ValidationException;

public interface IValidator{
public void validate(String content) throws ValidationException;
}

package jrn.dao.entities;


public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
    String message;
    public String getValidationExceptionMessage()
    {
    	return this.message;
    }
	public ValidationException(String message) {

		super(message);
	}

	@Override
	public String toString() {
		return getValidationExceptionMessage();
	}
}

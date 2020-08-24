package br.com.gregory.apirest.config.validations;

public class ErrorFormDto {

	private String field;
	private String message;

	public ErrorFormDto(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
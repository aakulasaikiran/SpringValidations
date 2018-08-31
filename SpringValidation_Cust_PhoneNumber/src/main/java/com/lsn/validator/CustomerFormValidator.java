package com.lsn.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lsn.domain.Customer;

public class CustomerFormValidator implements Validator {

	public boolean supports(Class<?> paramClass) {

		return Customer.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
		Customer customer = (Customer) obj;

		if (customer.getId() <= 0) {
			errors.rejectValue("id", "negativeValue", new Object[] { "'id'" }, "id can't be negative");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.required");

	}

}

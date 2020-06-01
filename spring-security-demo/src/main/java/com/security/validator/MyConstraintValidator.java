/**
 *
 */
package com.security.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author zhailiang
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {



	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		System.out.println(value);
		return true;
	}

}

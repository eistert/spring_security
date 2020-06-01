/**
 *
 */
package com.security.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.security.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author zhailiang
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
    // 可以注入任何实体来实现你的逻辑
    @Autowired
    private HelloService helloService;

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

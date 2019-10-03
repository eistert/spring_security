/**
 *
 */
package com.imooc.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @Target 标注在方法或字段上
 * @Retention(RetentionPolicy.RUNTIME) 运行时的注解
 * @Constraint(validatedBy = MyConstraintValidator.class) 用于校验的一个注解，指定某个校验类
 * @author ai
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field() default "";

}

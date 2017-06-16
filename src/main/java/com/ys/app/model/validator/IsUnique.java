package com.ys.app.model.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IsUniqueValidator.class)

public @interface IsUnique {

	String message() default "Already exists";

	@SuppressWarnings("rawtypes")
	Class[]groups() default {};

	@SuppressWarnings("rawtypes")
	Class[]payload() default {};

	String fieldName();

}

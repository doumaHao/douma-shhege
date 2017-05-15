package com.shhege.core.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface Cache {
	
	long life();

	String value() default "";
	
}

package org.discover.cdi.axon.demo.infrastructure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;

import org.axonframework.commandhandling.model.AggregateRoot;
import org.discovertypes.cdi.Discoverable;

/**
 * Example Meta/Stereotype-Annotation that marks an aggregate.
 * 
 * @author Johannes Troppacher
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Stereotype
@AggregateRoot
@Discoverable(ignoreBean = true)
public @interface ExampleAggregate {

	/**
	 * Get the String representation of the aggregate's type. Optional. This
	 * defaults to the simple name of the annotated class.
	 */
	String type() default "";

}
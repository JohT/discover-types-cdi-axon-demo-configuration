package org.discover.cdi.axon.demo.infrastructure.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.axonframework.eventhandling.EventHandler;
import org.discovertypes.cdi.Discoverable;

/**
 * Example Meta-Annotation that marks an event-handling method typically used in
 * "projections".
 * 
 * @author Johannes Troppacher
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@EventHandler
@Discoverable
public @interface ExampleEventHandler {

	/**
	 * The type of event this method handles. This handler will only be considered
	 * for invocation if the event message's payload is assignable to this type.
	 * <p>
	 * Optional. If unspecified, the first parameter of the method defines the type
	 * of supported event.
	 *
	 * @return The type of the event this method handles.
	 */
	Class<?> payloadType() default Object.class;

}

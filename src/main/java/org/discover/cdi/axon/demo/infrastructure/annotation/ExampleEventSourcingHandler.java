package org.discover.cdi.axon.demo.infrastructure.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.discovertypes.cdi.Discoverable;

/**
 * Marks a EventHandling inside an eventsourcing-capable Aggregate (Domain Driven Design).
 * @see EventSourcingHandler
 */
@Documented
@Discoverable
@EventSourcingHandler
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface ExampleEventSourcingHandler {
	/**
     * The type of event this method handles. If specified, this handler will only be invoked for message that have a
     * payload assignable to the given payload type.
     * <p>
     * Optional. If unspecified, the first parameter of the method defines the type of supported event.
     *
     * @return The type of the event this method handles.
     */
    Class<?> payloadType() default Object.class;
}

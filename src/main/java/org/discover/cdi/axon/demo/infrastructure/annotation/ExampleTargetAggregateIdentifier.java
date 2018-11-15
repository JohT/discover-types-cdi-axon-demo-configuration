package org.discover.cdi.axon.demo.infrastructure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.discovertypes.cdi.Discoverable;

/**
 * Example Meta-Annotation that marks the aggregate identifier field inside a
 * command value object.
 * 
 * @see TargetAggregateIdentifier
 * @author Johannes Troppacher
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@TargetAggregateIdentifier
@Discoverable(ignoreBean = true)
public @interface ExampleTargetAggregateIdentifier {

}
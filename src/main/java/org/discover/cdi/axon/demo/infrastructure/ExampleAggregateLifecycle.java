package org.discover.cdi.axon.demo.infrastructure;

import org.axonframework.commandhandling.model.AggregateLifecycle;

/**
 * Represents the AggregateLifecycle inside the example project. <br>
 * Delegates the calls to axon framework.
 * <p>
 * You may implement this as a singleton to get rid of the classic
 * "utility"-static-feel. This is only an example.
 * <p>
 * The important thing is to prevent the domain code ("that matters") from
 * getting polluted by library- and frameworks-dependencies (at least to
 * decouple them as much as possible). Libraries are likely to get updated for
 * different reasons than domain code. It should make a difference, if there is
 * a new security fix for library x or if a new law leads to domain behaviour
 * changes. If both of these things lead to changes in many (same) classes, then
 * something is wrong with the "extensibility" and "modularity".
 * 
 * @see AggregateLifecycle
 * @author Johannes Troppacher
 */
public class ExampleAggregateLifecycle {

	private ExampleAggregateLifecycle() {
		super();
	}

	/**
	 * @see AggregateLifecycle#apply(Object)
	 * @param payload - {@link Object}
	 */
	public static void apply(Object payload) {
		AggregateLifecycle.apply(payload);
	}

}

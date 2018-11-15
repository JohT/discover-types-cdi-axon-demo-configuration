package org.discover.cdi.axon.demo.infrastructure;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.axonframework.eventhandling.GenericEventMessage;
import org.axonframework.messaging.MetaData;
import org.discover.cdi.axon.demo.infrastructure.configurataion.ExampleConfiguration;

/**
 * Represents the EventBus inside the example project. <br>
 * Delegates the calls to axon framework.
 * <p>
 * This is the service used by the domain ("ubiquitous language").
 * Axon-Framework dependencies should remain only in this package (and
 * sub-packages).
 * 
 * @author Johannes Troppacher
 */
@ApplicationScoped
public class ExampleEventBusService {

	@Inject
	private ExampleConfiguration configuration;

	public void sendEvent(Object event) {
		configuration.getConfiguration().eventBus().publish(new GenericEventMessage<>(event, MetaData.emptyInstance()));
	}

}

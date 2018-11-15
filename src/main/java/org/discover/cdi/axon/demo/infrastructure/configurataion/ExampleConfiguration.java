package org.discover.cdi.axon.demo.infrastructure.configurataion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

import org.axonframework.config.AggregateConfigurer;
import org.axonframework.config.Configuration;
import org.axonframework.config.Configurer;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.discover.cdi.axon.demo.infrastructure.annotation.ExampleAggregate;
import org.discovertypes.cdi.DiscoveredType;
import org.discovertypes.cdi.DiscoveredTypes;

/**
 * Example Configuration for axon using the discoverable cdi extension.
 * 
 * @author Johannes Troppacher
 */
@ApplicationScoped
public class ExampleConfiguration {

	private Configuration configuration;

	private final Collection<Class<?>> aggregateTypes = new ArrayList<>();
	private final Collection<Class<?>> eventHandlerTypes = new ArrayList<>();

	@Inject
	private DiscoveredTypes discoveredTypes;

	@PostConstruct
	public void postConstruct() {
		Configurer configurer = DefaultConfigurer.defaultConfiguration();
		configurer = registerAggregates(configurer);
		configurer = registerEventHandler(configurer);
		configurer = configurer.configureEmbeddedEventStore(c -> new InMemoryEventStorageEngine());
		// This is just an example. You may use a JpaEventStore , register sagas, ....
		configuration = configurer.start();
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Only for demo/test purposes. The "real" configuration would not need to
	 * publish the discovered aggregates.
	 * 
	 * @return {@link Collection} of {@link Class}es.
	 */
	public Collection<Class<?>> getAggregateTypes() {
		return Collections.unmodifiableCollection(aggregateTypes);
	}

	/**
	 * Only for demo/test purposes. Only for test purposes. The "real" configuration
	 * would not need to publish the discovered event handlers.
	 * 
	 * @return {@link Collection} of {@link Class}es.
	 */
	public Collection<Class<?>> getEventHandlerTypes() {
		return Collections.unmodifiableCollection(eventHandlerTypes);
	}

	private Configurer registerAggregates(Configurer configurer) {
		// Both the original "AggregateRoot" and "ExampleAggregate" (meta-annotation)
		// could be queried here. Since the meta-annotation reflects the ubiquitous
		// language of the domain, it is preferred here. On the other hand it does not
		// matter that much, because this configuration class is the adapter between the
		// framework (axon) and the domain and has dependencies to both of them.
		Collection<DiscoveredType> aggregates = discoveredTypes.annotatedWith(ExampleAggregate.class);
		for (DiscoveredType aggregate : aggregates) {
			aggregateTypes.add(aggregate.getAnnotatedClass());
			configurer.configureAggregate(AggregateConfigurer.defaultConfiguration(aggregate.getAnnotatedClass()));
		}
		return configurer;
	}

	private Configurer registerEventHandler(Configurer configurer) {
		EventHandlingConfiguration eventHandlingConfiguration = new EventHandlingConfiguration();

		Collection<DiscoveredType> aggregates = discoveredTypes.annotatedWith(EventHandler.class);
		for (DiscoveredType eventHandler : aggregates) {
			eventHandlerTypes.add(eventHandler.getAnnotatedClass());
			eventHandlingConfiguration.registerEventHandler(createBean(eventHandler));
		}
		return configurer.registerModule(eventHandlingConfiguration);
	}

	private Function<Configuration, Object> createBean(DiscoveredType type) {
		return c -> CDI.current().select(type.getAnnotatedClass(), type.getQualifiers()).get();
	}
}
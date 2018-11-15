package org.discover.cdi.axon.demo.infrastructure.configurataion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.annotation.Annotation;

import org.discover.cdi.axon.demo.aggregate.SampleAggregate;
import org.discover.cdi.axon.demo.infrastructure.ExampleEventBusService;
import org.discover.cdi.axon.demo.infrastructure.annotation.ExampleCdiQualifier;
import org.discover.cdi.axon.demo.messages.SampleCreatedEvent;
import org.discover.cdi.axon.demo.projection.SampleProjection;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

public class ExampleConfigurationIntegrationTest {

	@Test
	public void startUpConfigurationExample() {
		try (WeldContainer container = new Weld().initialize()) {
			checkConfiguration(container);
			checkEventHandling(container);
		}
	}

	private void checkConfiguration(WeldContainer container) {
		ExampleConfiguration configuration = container.select(ExampleConfiguration.class).get();
		assertNotNull(configuration.getConfiguration());
		assertEquals(SampleAggregate.class, configuration.getAggregateTypes().iterator().next());
		assertEquals(SampleProjection.class, configuration.getEventHandlerTypes().iterator().next());
	}

	private void checkEventHandling(WeldContainer container) {
		ExampleEventBusService eventBusService = container.select(ExampleEventBusService.class).get();

		SampleCreatedEvent event = new SampleCreatedEvent("9541");
		eventBusService.sendEvent(event);

		SampleProjection projection = container.select(SampleProjection.class, exampleCdiQualifier()).get();
		projection.getCatchedEvents().contains(event);
	}

	private static ExampleCdiQualifier exampleCdiQualifier() {
		return new ExampleCdiQualifier() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExampleCdiQualifier.class;
			}
		};
	}
}

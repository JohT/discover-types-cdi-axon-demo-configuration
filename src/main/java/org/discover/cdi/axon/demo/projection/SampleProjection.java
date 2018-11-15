package org.discover.cdi.axon.demo.projection;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;

import javax.enterprise.context.Dependent;

import org.discover.cdi.axon.demo.infrastructure.annotation.ExampleCdiQualifier;
import org.discover.cdi.axon.demo.infrastructure.annotation.ExampleEventHandler;
import org.discover.cdi.axon.demo.messages.SampleCreatedEvent;

@Dependent // Projections need to be dependent scope
@ExampleCdiQualifier // To demonstrate qualifier support
public class SampleProjection {

	private final Collection<SampleCreatedEvent> catchedEvents = new ConcurrentLinkedDeque<>();

	@ExampleEventHandler
	public void handleEvent(SampleCreatedEvent event) {
		catchedEvents.add(event);
	}

	/**
	 * Normally, the collected events would be used to update the projection model.
	 * This method is only meant to demonstrate, that the event listener works.
	 * 
	 * @return {@link Collection} of {@link SampleCreatedEvent}s.
	 */
	public Collection<SampleCreatedEvent> getCatchedEvents() {
		return catchedEvents;
	}

	@Override
	public String toString() {
		return "SampleProjection [catchedEvents=" + catchedEvents + "]";
	}
}

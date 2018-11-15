package org.discover.cdi.axon.demo.aggregate;

import static org.discover.cdi.axon.demo.infrastructure.ExampleAggregateLifecycle.apply;

import org.discover.cdi.axon.demo.infrastructure.annotation.ExampleAggregate;
import org.discover.cdi.axon.demo.infrastructure.annotation.ExampleAggregateIdentifier;
import org.discover.cdi.axon.demo.infrastructure.annotation.ExampleCommandHandler;
import org.discover.cdi.axon.demo.infrastructure.annotation.ExampleEventSourcingHandler;
import org.discover.cdi.axon.demo.messages.CreateSampleCommand;
import org.discover.cdi.axon.demo.messages.SampleCreatedEvent;

/**
 * Demonstrates a simple event-sourcing aggregate build upon meta-annotations.
 * <p>
 * As you can see, there is no import of axon or any other external
 * libraries.<br>
 * This may be too extreme and complex for every case. It though has the
 * advantage to decouple business logic from frameworks (like axon) that may
 * change, provides a defined interface of all the functions you really use of
 * the framework and enables changes to e.g. all event apply-Method calls.
 * 
 * @author Johannes Troppacher
 */
@ExampleAggregate
public class SampleAggregate {

	@ExampleAggregateIdentifier
	private String sampleId;

	/**
	 * @deprecated Only for frameworks. Not meant to be called directly.
	 */
	@Deprecated
	protected SampleAggregate() {
		super();
	}

	protected SampleAggregate(String sampleId) {
		this.sampleId = sampleId;
	}

	@ExampleCommandHandler
	public static final SampleAggregate createWith(CreateSampleCommand command) {
		apply(new SampleCreatedEvent(command.getSampleId()));
		return new SampleAggregate(command.getSampleId());
	}

	@ExampleEventSourcingHandler
	private void on(SampleCreatedEvent event) {
		this.sampleId = event.getSampleId();
	}

	@Override
	public String toString() {
		return "SampleAggregate [sampleId=" + sampleId + "]";
	}
}
package org.discover.cdi.axon.demo.aggregate;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.discover.cdi.axon.demo.messages.CreateSampleCommand;
import org.discover.cdi.axon.demo.messages.SampleCreatedEvent;
import org.junit.Before;
import org.junit.Test;

/**
 * "Unit-Integration-Test" to check {@link ExampleAggregate}'s event-sourcing
 * capabilities. This test has a dependency to "axon-test". Since this is only a
 * test dependency, decoupling it is skipped it.
 * 
 * @author Johannes Troppacher
 */
public class SampleAggregateEventSourcingTest {

	private AggregateTestFixture<SampleAggregate> fixture;

	@Before
	public void setUp() throws Exception {
		fixture = new AggregateTestFixture<>(SampleAggregate.class);
	}

	@Test
	public void neuanlageEventAusgeloest() {
		String exampleId = "1234";
		fixture.givenNoPriorActivity()
				.when(new CreateSampleCommand(exampleId))
				.expectEvents(new SampleCreatedEvent(exampleId));
	}

}

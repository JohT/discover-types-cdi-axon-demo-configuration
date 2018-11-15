package org.discover.cdi.axon.demo.messages;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class SampleCreatedEvent {

	private final String sampleId;

	@ConstructorProperties({ "sampleId" })
	public SampleCreatedEvent(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getSampleId() {
		return sampleId;
	}

	@Override
	public boolean equals(final Object other) {
		if (other == null) {
			return false;
		}
		if (!getClass().equals(other.getClass())) {
			return false;
		}
		SampleCreatedEvent castOther = (SampleCreatedEvent) other;
		return Objects.equals(sampleId, castOther.sampleId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sampleId);
	}

	@Override
	public String toString() {
		return "SampleCreatedEvent [sampleId=" + sampleId + "]";
	}
}
package org.discover.cdi.axon.demo.messages;

import java.beans.ConstructorProperties;
import java.util.Objects;

import org.discover.cdi.axon.demo.infrastructure.annotation.ExampleTargetAggregateIdentifier;

public class CreateSampleCommand {

	@ExampleTargetAggregateIdentifier
	private final String sampleId;

	@ConstructorProperties({ "sampleId" })
	public CreateSampleCommand(String sampleId) {
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
		CreateSampleCommand castOther = (CreateSampleCommand) other;
		return Objects.equals(sampleId, castOther.sampleId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sampleId);
	}

	@Override
	public String toString() {
		return "CreateSampleCommand [sampleId=" + sampleId + "]";
	}
}
package org.discover.cdi.axon.demo.internal;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DiscoverableTypesTestUtil {

	private DiscoverableTypesTestUtil() {
		super();
	}
	
	public static <T> void assertContains(T element, final Collection<T> collection) {
		assertTrue(collection.toString(), collection.contains(element));
	}
	
	public static <T> List<T> iterableAsList(Iterable<T> iterable) {
		List<T> types = new ArrayList<>();
		for (T element : iterable) {
			types.add(element);
		}
		return types;
	}
}
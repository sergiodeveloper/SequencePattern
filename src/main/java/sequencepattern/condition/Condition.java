package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public interface Condition<E> {
	boolean appliesTo(ElementSequence<E> sequence);
}

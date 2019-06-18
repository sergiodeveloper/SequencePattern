package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public interface Countable<E> extends Comparable<E> {

	int calculate(ElementSequence<E> sequence);

	boolean isGreaterThan(Countable<E> comparable, ElementSequence<E> sequence);

	boolean isSmallerThan(Countable<E> comparable, ElementSequence<E> sequence);

}

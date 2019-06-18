package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public interface Comparable<E> {

	boolean isEqualTo(Comparable<E> comparable, ElementSequence<E> sequence);

}

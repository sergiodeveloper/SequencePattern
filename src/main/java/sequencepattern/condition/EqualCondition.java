package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public class EqualCondition<E> implements Condition<E> {

	private final Comparable<E> firstValue;
	private final Comparable<E> secondValue;

	public EqualCondition(final Comparable<E> firstValue, final Comparable<E> secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}

	public boolean appliesTo(final ElementSequence<E> sequence) {
		return firstValue.isEqualTo(secondValue, sequence);
	}

	@Override
	public String toString() {
		return firstValue + " == " + secondValue;
	}
}

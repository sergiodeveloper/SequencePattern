package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public class SmallerThanCondition<E> implements Condition<E> {

	private final Countable<E> firstValue;
	private final Countable<E> secondValue;

	public SmallerThanCondition(final Countable<E> firstValue, final Countable<E> secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}

	@Override
	public boolean appliesTo(final ElementSequence<E> sequence) {
		return firstValue.isSmallerThan(secondValue, sequence);
	}

	@Override
	public String toString() {
		return firstValue + " < " + secondValue;
	}

}

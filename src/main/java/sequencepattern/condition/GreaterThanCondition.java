package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public class GreaterThanCondition<E> implements Condition<E> {

	private final Countable<E> firstValue;
	private final Countable<E> secondValue;

	public GreaterThanCondition(final Countable<E> firstValue, final Countable<E> secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}

	@Override
	public boolean appliesTo(final ElementSequence<E> sequence) {
		return firstValue.isGreaterThan(secondValue, sequence);
	}

	@Override
	public String toString() {
		return firstValue + " > " + secondValue;
	}

}

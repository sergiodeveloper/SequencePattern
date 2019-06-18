package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public class NotCondition<E> implements Condition<E> {

	private final Condition<E> condition;

	public NotCondition(final Condition<E> condition) {
		this.condition = condition;
	}

	@Override
	public boolean appliesTo(final ElementSequence<E> sequence) {
		return !condition.appliesTo(sequence);
	}

	@Override
	public String toString() {
		return "not " + condition;
	}

}

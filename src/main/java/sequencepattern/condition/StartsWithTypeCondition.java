package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public class StartsWithTypeCondition<E> implements Condition<E> {

	private final Class<? extends E> type;

	public StartsWithTypeCondition(final Class<? extends E> type) {
		this.type = type;
	}

	@Override
	public boolean appliesTo(final ElementSequence<E> sequence) {
		return !sequence.isEmpty() && type.isInstance(sequence.get(0));
	}

	@Override
	public String toString() {
		return "[starts with type \"" + type.getSimpleName() + "\"]";
	}

}

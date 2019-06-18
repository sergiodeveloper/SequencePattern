package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public class StartsWithCondition<E> implements Condition<E> {

	private final E element;

	public StartsWithCondition(final E element) {
		this.element = element;
	}

	@Override
	public boolean appliesTo(final ElementSequence<E> sequence) {
		return !sequence.isEmpty() && sequence.get(0).equals(element);
	}

	@Override
	public String toString() {
		return "[starts with \"" + element + "\"]";
	}

}

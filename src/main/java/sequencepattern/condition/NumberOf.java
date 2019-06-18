package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public class NumberOf<E> implements Countable<E> {

	private final E element;

	public NumberOf(final E element) {
		this.element = element;
	}

	@Override
	public int calculate(final ElementSequence<E> sequence) {
		int n = 0;
		for (int i = 0; i < sequence.size(); i++) {
			if (sequence.get(i).equals(element)) {
				n++;
			}
		}
		return n;
	}

	@Override
	public boolean isEqualTo(final Comparable<E> comparable, final ElementSequence<E> sequence) {
		if (comparable instanceof Countable<?>) {
			return calculate(sequence) == ((Countable<E>) comparable).calculate(sequence);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean isGreaterThan(final Countable<E> comparable, final ElementSequence<E> sequence) {
		return calculate(sequence) > comparable.calculate(sequence);
	}

	@Override
	public boolean isSmallerThan(final Countable<E> comparable, final ElementSequence<E> sequence) {
		return calculate(sequence) < comparable.calculate(sequence);
	}

	@Override
	public String toString() {
		return "[number of \"" + element + "\"]";
	}

}

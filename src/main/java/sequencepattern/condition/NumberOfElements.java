package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public class NumberOfElements<E> implements Countable<E> {

	@Override
	public int calculate(final ElementSequence<E> sequence) {
		return sequence.size();
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
		return "[total number of elements]";
	}

}

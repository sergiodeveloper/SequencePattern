package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public class ExactNumber<E> implements Countable<E> {

	private final int number;

	public ExactNumber(final int number) {
		this.number = number;
	}

	@Override
	public int calculate(final ElementSequence<E> sequence) {
		return number;
	}

	@Override
	public boolean isEqualTo(final Comparable<E> comparable, final ElementSequence<E> sequence) {
		if (comparable instanceof Countable<?>) {
			return number == ((Countable<E>) comparable).calculate(sequence);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public boolean isGreaterThan(final Countable<E> comparable, final ElementSequence<E> sequence) {
		return number > comparable.calculate(sequence);
	}

	@Override
	public boolean isSmallerThan(final Countable<E> comparable, final ElementSequence<E> sequence) {
		return number < comparable.calculate(sequence);
	}

	@Override
	public String toString() {
		return number + "";
	}

}

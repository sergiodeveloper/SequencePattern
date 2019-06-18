package sequencepattern.condition;

import sequencepattern.pattern.ElementSequence;

public class NumberOfInstancesOf<E> implements Countable<E> {

	private final Class<? extends E> elementClass;

	public NumberOfInstancesOf(final Class<? extends E> elementClass) {
		this.elementClass = elementClass;
	}

	@Override
	public int calculate(final ElementSequence<E> sequence) {
		int n = 0;
		for (int i = 0; i < sequence.size(); i++) {
			if (elementClass.isInstance(sequence.get(i))) {
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
		return "[number of instances of \"" + elementClass.getSimpleName() + "\"]";
	}

}

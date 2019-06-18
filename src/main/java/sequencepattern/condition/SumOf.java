package sequencepattern.condition;

import java.util.ArrayList;
import java.util.List;

import sequencepattern.pattern.ElementSequence;

public class SumOf<E> implements Countable<E> {

	private final List<Countable<E>> elements = new ArrayList<>();

	@SafeVarargs
	public SumOf(final Countable<E>... elements) {
		for (Countable<E> element : elements) {
			this.elements.add(element);
		}
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
	public int calculate(final ElementSequence<E> sequence) {
		int n = 0;
		for (int i = 0; i < elements.size(); i++) {
			n += elements.get(i).calculate(sequence);
		}
		return n;
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
		return "[sum of " + elements.toString() + "]";
	}

}

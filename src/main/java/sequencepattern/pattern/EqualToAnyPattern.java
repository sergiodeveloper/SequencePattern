package sequencepattern.pattern;

import java.util.Arrays;
import java.util.List;

public class EqualToAnyPattern<E> extends Pattern<E> {
	private final E[] elements;

	@SafeVarargs
	public EqualToAnyPattern(final E... element) {
		this.elements = element;
	}

	@Override
	public List<ElementSequence<E>> execute(final ElementSequence<E> element) {
		if (!element.isEmpty()) {
			for (E e : this.elements) {
				if (e.equals(element.get(0))) {
					return new ElementSequence<ElementSequence<E>>(new ElementSequence<>(element.get(0)));
				}
			}
		}
		return Arrays.asList();
	}

	@Override
	public String asString() {
		if (elements.length == 1) {
			return elements[0].toString();
		}
		StringBuilder sb = new StringBuilder("equal to any: [ ");
		for (int i = 0; i < elements.length; i++) {
			sb.append(elements[i]);
			if (i + 1 < elements.length) {
				sb.append(", ");
			}
		}
		sb.append(" ]");
		return sb.toString();
	}
}
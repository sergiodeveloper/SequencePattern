package sequencepattern.pattern;

import java.util.Arrays;
import java.util.List;

public class NotEqualToAnyPattern<E> extends Pattern<E> {
	private final E[] elements;

	@SafeVarargs
	public NotEqualToAnyPattern(final E... element) {
		this.elements = element;
	}

	@Override
	public List<ElementSequence<E>> execute(final ElementSequence<E> element) {
		if (!element.isEmpty()) {
			for (E e : this.elements) {
				if (e.equals(element.get(0))) {
					return Arrays.asList();
				}
			}
			return new ElementSequence<>(new ElementSequence<>(element.get(0)));
		}
		return Arrays.asList();
	}

	@Override
	public String asString() {
		if (elements.length == 1) {
			return "(not: " + elements[0].toString() + ")";
		}
		StringBuilder sb = new StringBuilder("[^ ");
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
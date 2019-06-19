package sequencepattern.pattern;

import java.util.Arrays;
import java.util.List;

public class NotAnyClassPattern<E> extends Pattern<E> {
	private final Class<? extends E>[] elementClasses;

	@SafeVarargs
	public NotAnyClassPattern(final Class<? extends E>... elementClasses) {
		this.elementClasses = elementClasses;
	}

	@Override
	public List<ElementSequence<E>> execute(final ElementSequence<E> element) {
		if (element.isEmpty()) {
			return Arrays.asList();
		}

		for (Class<? extends E> e : this.elementClasses) {
			if (e.isInstance(element.get(0))) {
				return Arrays.asList();
			}
		}

		return new ElementSequence<ElementSequence<E>>(new ElementSequence<>(element.get(0)));
	}

	@Override
	public String asString() {
		if (elementClasses.length == 1) {
			return "not " + elementClasses[0].getSimpleName();
		}
		StringBuilder sb = new StringBuilder("[^ ");
		for (int i = 0; i < elementClasses.length; i++) {
			sb.append(elementClasses[i].getSimpleName());
			if (i + 1 < elementClasses.length) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

}

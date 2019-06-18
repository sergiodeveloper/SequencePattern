package sequencepattern.pattern;

import java.util.Arrays;
import java.util.List;

public class AnyClassPattern<E> extends Pattern<E> {
	private final Class<? extends E>[] elementClasses;

	@SafeVarargs
	public AnyClassPattern(final Class<? extends E>... elementClasses) {
		this.elementClasses = elementClasses;
	}

	@Override
	public List<ElementSequence<E>> execute(final ElementSequence<E> element) {
		if (!element.isEmpty()) {
			for (Class<? extends E> e : this.elementClasses) {
				if (e.isInstance(element.get(0))) {
					return new ElementSequence<>(new ElementSequence<>(element.get(0)));
				}
			}
		}
		return Arrays.asList();
	}

	@Override
	public String asString() {
		if (elementClasses.length == 1) {
			return elementClasses[0].getSimpleName();
		}
		StringBuilder sb = new StringBuilder("any class: [");
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

package sequencepattern.pattern;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Pattern<E> {

	public abstract List<ElementSequence<E>> execute(ElementSequence<E> elements);

	public List<ElementSequence<E>> execute(List<E> elements) {
		return execute(new ElementSequence<>(elements));
	}

	protected abstract String asString();

	private static final Set<Pattern<?>> stringifying = new HashSet<>();

	@Override
	public String toString() {
		if (stringifying.contains(this)) {
			return "<circular reference>";
		} else {
			stringifying.add(this);
			String string = asString();
			stringifying.remove(this);
			return string;
		}
	}

}

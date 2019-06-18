package sequencepattern.pattern;

import java.util.ArrayList;
import java.util.List;

public class OptionalPattern<E> extends Pattern<E> {
	private final Pattern<E> pattern;

	public OptionalPattern(final Pattern<E> pattern) {
		this.pattern = pattern;
	}

	@Override
	public List<ElementSequence<E>> execute(ElementSequence<E> element) {
		List<ElementSequence<E>> matchResult = element.match(pattern);

		List<ElementSequence<E>> result = new ArrayList<>();
		result.addAll(matchResult);

		result.add(new ElementSequence<>());
		return result;
	}

	@Override
	public String asString() {
		return pattern.toString() + "?";
	}
}
package sequencepattern.pattern;

import java.util.ArrayList;
import java.util.List;

import sequencepattern.condition.Condition;

public class IfPattern<E> extends Pattern<E> {

	private final Pattern<E> pattern;
	private final Condition<E> condition;

	public IfPattern(final Pattern<E> pattern, final Condition<E> condition) {
		this.pattern = pattern;
		this.condition = condition;
	}

	@Override
	public List<ElementSequence<E>> execute(final ElementSequence<E> elements) {
		List<ElementSequence<E>> passed = new ArrayList<>();

		List<ElementSequence<E>> result = pattern.execute(elements);

		for (ElementSequence<E> r : result) {
			if (condition.appliesTo(r)) {
				passed.add(r);
			}
		}

		return passed;
	}

	@Override
	public String asString() {
		return "(" + pattern.asString() + "){if: " + condition + "}";
	}

}

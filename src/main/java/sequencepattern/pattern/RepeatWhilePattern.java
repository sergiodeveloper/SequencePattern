package sequencepattern.pattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sequencepattern.condition.Condition;

public class RepeatWhilePattern<E> extends Pattern<E> {

	private final Pattern<E> pattern;
	private final Condition<E> condition;

	public RepeatWhilePattern(final Pattern<E> pattern, final Condition<E> condition) {
		this.pattern = pattern;
		this.condition = condition;
	}

	@Override
	public List<ElementSequence<E>> execute(final ElementSequence<E> elements) {
		List<ElementSequence<E>> results = new ArrayList<>();
		results.addAll(pattern.execute(elements));

		for (int i = 0; i < results.size(); i++) {
			ElementSequence<E> result = results.get(i);

			int currentIndex = result.size();

			while (condition.appliesTo(result) && currentIndex < elements.size()) {
				ElementSequence<E> remaining = elements.subSequence(result.size());

				List<ElementSequence<E>> resultsFromRemaining = pattern.execute(remaining);

				if (resultsFromRemaining.isEmpty()) {
					break;
				}

				for (int r = 1; r < resultsFromRemaining.size(); r++) {
					ElementSequence<E> additional = new ElementSequence<>(result);
					additional.add(resultsFromRemaining.get(r));
					results.add(additional);
				}

				result.add(resultsFromRemaining.get(0));
				currentIndex += resultsFromRemaining.get(0).size();
			}
		}

		for (Iterator<ElementSequence<E>> iterator = results.iterator(); iterator.hasNext();) {
			if (condition.appliesTo(iterator.next())) {
				iterator.remove();
			}
		}

		if (results.isEmpty()) {
			results.add(new ElementSequence<>());
		}

		return results;
	}

	@Override
	public String asString() {
		return "(" + pattern.asString() + "){while: " + condition + "}";
	}

}

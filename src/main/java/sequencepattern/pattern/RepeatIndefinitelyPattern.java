package sequencepattern.pattern;

import java.util.ArrayList;
import java.util.List;

public class RepeatIndefinitelyPattern<E> extends Pattern<E> {

	private final Pattern<E> pattern;

	public RepeatIndefinitelyPattern(final Pattern<E> pattern) {
		this.pattern = pattern;
	}

	@Override
	public List<ElementSequence<E>> execute(final ElementSequence<E> elements) {
		List<ElementSequence<E>> results = new ArrayList<>();
		results.addAll(pattern.execute(elements));

		for (int i = 0; i < results.size(); i++) {
			while (true) {
				ElementSequence<E> remaining = elements.subSequence(results.get(i).size());

				List<ElementSequence<E>> resultsFromRemaining = pattern.execute(remaining);

				if (resultsFromRemaining.isEmpty()) {
					break;
				}
				for (int r = 1; r < resultsFromRemaining.size(); r++) {
					ElementSequence<E> additional = new ElementSequence<>(results.get(i));
					additional.add(resultsFromRemaining.get(r));
					results.add(additional);
				}
				results.get(i).add(resultsFromRemaining.get(0));
			}
		}

		if (results.isEmpty()) {
			results.add(new ElementSequence<>());
		}

		return results;
	}

	@Override
	public String asString() {
		return "(" + pattern.asString() + ")*";
	}

}

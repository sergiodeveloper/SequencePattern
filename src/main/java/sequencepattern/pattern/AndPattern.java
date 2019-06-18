package sequencepattern.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AndPattern<E> extends Pattern<E> {

	private final List<Pattern<E>> patterns;

	public AndPattern(final List<Pattern<E>> patterns) {
		this.patterns = patterns;
	}

	@Override
	public List<ElementSequence<E>> execute(final ElementSequence<E> elements) {
		List<ElementSequence<E>> results = new ArrayList<>();

		if (patterns.isEmpty()) {
			return results;
		}

		results.addAll(patterns.get(0).execute(elements));

		if (results.isEmpty()) {
			return results;
		}

		List<Integer> startIndices = new ArrayList<>();

		for (int i = 0; i < results.size(); i++) {
			startIndices.add(1);
		}

		for (int i = 0; i < results.size(); i++) {
			ElementSequence<E> result = results.get(i);

			ElementSequence<E> remaining = elements.subSequence(result.size());

			int pattern = startIndices.get(i);

			while (pattern < patterns.size()) {
				List<ElementSequence<E>> resultsInRemaining = patterns.get(pattern).execute(remaining);

				if (resultsInRemaining.isEmpty()) {
					results.set(i, null);
					break;
				}

				for (int r = 1; r < resultsInRemaining.size(); r++) {
					ElementSequence<E> additional = new ElementSequence<>(result);
					additional.add(resultsInRemaining.get(r));
					results.add(additional);
					startIndices.add(pattern + 1);
				}

				result.add(resultsInRemaining.get(0));
				remaining = remaining.subSequence(resultsInRemaining.get(0).size());

				pattern++;
			}
		}

		results.removeAll(Collections.singleton(null));

		return results;
	}

	@Override
	public String asString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < patterns.size(); i++) {
			sb.append(patterns.get(i));
			if (i + 1 < patterns.size()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

}

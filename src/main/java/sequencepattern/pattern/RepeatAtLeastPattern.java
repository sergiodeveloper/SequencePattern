package sequencepattern.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepeatAtLeastPattern<E> extends Pattern<E> {

	private final Pattern<E> pattern;
	private final int nTimes;

	public RepeatAtLeastPattern(final Pattern<E> pattern, final int nTimes) {
		this.pattern = pattern;
		this.nTimes = nTimes;
	}

	@Override
	public List<ElementSequence<E>> execute(final ElementSequence<E> elements) {
		List<ElementSequence<E>> results = new ArrayList<>();
		results.addAll(pattern.execute(elements));

		if (results.isEmpty()) {
			return results;
		}

		List<Boolean> whoBroke = new ArrayList<>();

		for (int i = 0; i < results.size(); i++) {
			whoBroke.add(false);
		}

		for (int i = 0; i < results.size() && whoBroke.contains(false); i++) {
			int counter = 1;

			while (true) {
				ElementSequence<E> remaining = elements.subSequence(results.get(i).size());
				List<ElementSequence<E>> resultsFromRemaining = pattern.execute(remaining);

				if (resultsFromRemaining.isEmpty()) {
					whoBroke.set(i, true);
					break;
				}
				for (int r = 1; r < resultsFromRemaining.size(); r++) {
					ElementSequence<E> additional = new ElementSequence<>(results.get(i));
					additional.add(resultsFromRemaining.get(r));
					results.add(additional);
				}
				results.get(i).add(resultsFromRemaining.get(0));
				counter++;
			}

			if (counter < nTimes) {
				results.set(i, null);
			}
		}

		results.removeAll(Collections.singleton(null));
		return results;
	}

	@Override
	public String asString() {
		return "(" + pattern.asString() + "){" + nTimes + ",}";
	}

}

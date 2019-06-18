package sequencepattern.pattern;

import java.util.ArrayList;
import java.util.List;

public class OrPattern<E> extends Pattern<E> {

	private final List<Pattern<E>> patternList;

	public OrPattern(final List<Pattern<E>> list) {
		this.patternList = list;
	}

	@Override
	public List<ElementSequence<E>> execute(final ElementSequence<E> element) {
		List<ElementSequence<E>> list = new ArrayList<>();
		for (Pattern<E> pattern : patternList) {
			List<ElementSequence<E>> result = pattern.execute(element);
			list.addAll(result);
		}
		
		return list;
	}

	@Override
	public String asString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < patternList.size(); i++) {
			sb.append(patternList.get(i));
			if (i + 1 < patternList.size()) {
				sb.append(" | \n");
			}
		}

		return sb.toString();
	}

}

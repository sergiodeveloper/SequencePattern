package sequencepattern.pattern;

import java.util.Arrays;
import java.util.List;

public class AnyPattern<E> extends Pattern<E> {

	@Override
	public List<ElementSequence<E>> execute(ElementSequence<E> element) {
		if (element.isEmpty()) {
			return Arrays.asList();
		}
		return new ElementSequence<>(new ElementSequence<>(element.get(0)));
	}

	@Override
	public String asString() {
		return " . ";
	}

}

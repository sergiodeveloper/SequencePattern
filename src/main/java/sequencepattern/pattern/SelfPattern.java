package sequencepattern.pattern;

import java.util.List;

public class SelfPattern<E> extends Pattern<E> {

	private Pattern<E> pattern;

	public SelfPattern(Pattern<E> pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public List<ElementSequence<E>> execute(ElementSequence<E> element) {
		return this.pattern.execute(element);
	}

	@Override
	public String asString() {
		return "[self pattern]";
	}

}

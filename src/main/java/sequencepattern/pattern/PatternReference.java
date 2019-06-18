package sequencepattern.pattern;

import java.util.List;
import java.util.Objects;

public class PatternReference<E> extends Pattern<E> {

	private Pattern<E> pattern;

	public PatternReference() {
		this.pattern = null;
	}

	public void set(final Pattern<E> pattern) {
		this.pattern = pattern;
	}

	public boolean isSet() {
		return pattern != null;
	}

	@Override
	public List<ElementSequence<E>> execute(final ElementSequence<E> element) {
		if (pattern == null) {
			throw new IllegalStateException("Pattern reference was not defined");
		}
		return this.pattern.execute(element);
	}

	@Override
	public String asString() {
		if (pattern == null) {
			return "<undefined pattern reference>";
		}
		return pattern.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(pattern);
	}

	@Override
	public boolean equals(final Object obj) {
		return obj == pattern;
	}

}

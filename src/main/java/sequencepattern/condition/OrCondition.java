package sequencepattern.condition;

import java.util.ArrayList;
import java.util.List;

import sequencepattern.pattern.ElementSequence;

public class OrCondition<E> implements Condition<E> {

	private final List<Condition<E>> conditions;

	public OrCondition(final List<Condition<E>> conditions) {
		this.conditions = conditions;
	}

	@SafeVarargs
	public OrCondition(final Condition<E>... conditions) {
		this.conditions = new ArrayList<>();
		for (Condition<E> condition : conditions) {
			this.conditions.add(condition);
		}
	}

	@Override
	public boolean appliesTo(final ElementSequence<E> sequence) {
		for (Condition<E> c : conditions) {
			if (c.appliesTo(sequence)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		for (int i = 0; i < conditions.size(); i++) {
			sb.append(conditions.get(i));
			if (i + 1 < conditions.size()) {
				sb.append(" || ");
			}
		}
		sb.append(")");
		return sb.toString();
	}

}

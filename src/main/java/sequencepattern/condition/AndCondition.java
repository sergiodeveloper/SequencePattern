package sequencepattern.condition;

import java.util.List;

import sequencepattern.pattern.ElementSequence;

public class AndCondition<E> implements Condition<E> {

	private final List<Condition<E>> conditions;

	public AndCondition(final List<Condition<E>> conditions) {
		this.conditions = conditions;
	}

	@Override
	public boolean appliesTo(final ElementSequence<E> sequence) {
		for (Condition<E> c : conditions) {
			if (!c.appliesTo(sequence)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		for (int i = 0; i < conditions.size(); i++) {
			sb.append(conditions.get(i));
			if (i + 1 < conditions.size()) {
				sb.append(" && ");
			}
		}
		sb.append(")");
		return sb.toString();
	}

}

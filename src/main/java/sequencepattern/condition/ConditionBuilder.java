package sequencepattern.condition;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ConditionBuilder<E> {

	private final Deque<List<Condition<E>>> stack = new ArrayDeque<>();

	public ConditionBuilder() {
		stack.addFirst(new ArrayList<>());
	}

	public ConditionBuilder<E> isEqual(final Comparable<E> firstValue, final Comparable<E> secondValue) {
		stack.peek().add(new EqualCondition<E>(firstValue, secondValue));
		return this;
	}

	public ConditionBuilder<E> isNotEqual(final Comparable<E> firstValue, final Comparable<E> secondValue) {
		stack.peek().add(new NotCondition<>(new EqualCondition<E>(firstValue, secondValue)));
		return this;
	}

	public ConditionBuilder<E> isGreaterThan(final Countable<E> firstValue, final Countable<E> secondValue) {
		stack.peek().add(new GreaterThanCondition<E>(firstValue, secondValue));
		return this;
	}

	public ConditionBuilder<E> isGreaterOrEqualTo(final Countable<E> firstValue, final Countable<E> secondValue) {
		stack.peek().add(new OrCondition<E>(new GreaterThanCondition<E>(firstValue, secondValue),
				new EqualCondition<E>(firstValue, secondValue)));
		return this;
	}

	public ConditionBuilder<E> isSmallerThan(final Countable<E> firstValue, final Countable<E> secondValue) {
		stack.peek().add(new SmallerThanCondition<E>(firstValue, secondValue));
		return this;
	}

	public ConditionBuilder<E> isSmallerOrEqualTo(final Countable<E> firstValue, final Countable<E> secondValue) {
		stack.peek().add(new OrCondition<E>(new SmallerThanCondition<E>(firstValue, secondValue),
				new EqualCondition<E>(firstValue, secondValue)));
		return this;
	}

	public ConditionBuilder<E> isEqual(final Comparable<E> firstValue, final int number) {
		stack.peek().add(new EqualCondition<E>(firstValue, new ExactNumber<>(number)));
		return this;
	}

	public ConditionBuilder<E> isNotEqual(final Comparable<E> firstValue, final int number) {
		stack.peek().add(new NotCondition<>(new EqualCondition<E>(firstValue, new ExactNumber<>(number))));
		return this;
	}

	public ConditionBuilder<E> isGreaterThan(final Countable<E> firstValue, final int number) {
		stack.peek().add(new GreaterThanCondition<E>(firstValue, new ExactNumber<>(number)));
		return this;
	}

	public ConditionBuilder<E> isGreaterOrEqualTo(final Countable<E> firstValue, final int number) {
		ExactNumber<E> secondValue = new ExactNumber<>(number);
		stack.peek().add(new OrCondition<E>(new GreaterThanCondition<>(firstValue, secondValue),
				new EqualCondition<>(firstValue, secondValue)));
		return this;
	}

	public ConditionBuilder<E> isSmallerThan(final Countable<E> firstValue, final int number) {
		stack.peek().add(new SmallerThanCondition<E>(firstValue, new ExactNumber<>(number)));
		return this;
	}

	public ConditionBuilder<E> isSmallerOrEqualTo(final Countable<E> firstValue, final int number) {
		ExactNumber<E> secondValue = new ExactNumber<>(number);
		stack.peek().add(new OrCondition<E>(new SmallerThanCondition<E>(firstValue, secondValue),
				new EqualCondition<E>(firstValue, secondValue)));
		return this;
	}

	public ConditionBuilder<E> startsWith(final E element) {
		stack.peek().add(new StartsWithCondition<E>(element));
		return this;
	}

	public ConditionBuilder<E> startsWith(final Class<? extends E> type) {
		stack.peek().add(new StartsWithTypeCondition<E>(type));
		return this;
	}

	public ConditionBuilder<E> startBlock() {
		stack.addFirst(new ArrayList<>());
		return this;
	}

	public ConditionBuilder<E> endAndBlock() {
		List<Condition<E>> top = stack.pop();
		if (stack.isEmpty()) {
			stack.addFirst(new ArrayList<>());
		}
		stack.peek().add(new AndCondition<>(top));
		return this;
	}

	public ConditionBuilder<E> endOrBlock() {
		List<Condition<E>> top = stack.pop();
		if (stack.isEmpty()) {
			stack.addFirst(new ArrayList<>());
		}
		stack.peek().add(new OrCondition<>(top));
		return this;
	}

	public Condition<E> build() {
		if (stack.size() > 1) {
			throw new IllegalStateException("Unclosed blocks");
		}
		return new AndCondition<>(stack.peek());
	}

}

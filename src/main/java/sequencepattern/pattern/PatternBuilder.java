package sequencepattern.pattern;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import sequencepattern.condition.Condition;
import sequencepattern.condition.NotCondition;

public class PatternBuilder<E> {

	private final Deque<List<Pattern<E>>> stack = new ArrayDeque<>();

	private final PatternReference<E> patternReference = new PatternReference<>();

	public PatternBuilder() {
		stack.addFirst(new ArrayList<>());
	}

	public PatternBuilder<E> a(final E element) {
		stack.peek().add(new EqualToAnyPattern<E>(element));
		return this;
	}

	public PatternBuilder<E> optional(final E element) {
		return startBlock().a(element).optional();
	}

	public PatternBuilder<E> repeatIndefinitely(final E element) {
		return startBlock().a(element).repeatIndefinitely();
	}

	public PatternBuilder<E> a(final Class<? extends E> elementClass) {
		stack.peek().add(new AnyClassPattern<>(elementClass));
		return this;
	}

	public PatternBuilder<E> optional(final Class<? extends E> elementClass) {
		return startBlock().a(elementClass).optional();
	}

	public PatternBuilder<E> repeatIndefinitely(final Class<? extends E> elementClass) {
		return startBlock().a(elementClass).repeatIndefinitely();
	}

	public PatternBuilder<E> a(final Pattern<E> pattern) {
		stack.peek().add(pattern);
		return this;
	}

	public PatternBuilder<E> a(final PatternBuilder<E> patternBuilder) {
		return a(patternBuilder.getPatternReference());
	}

	public PatternBuilder<E> optional(final Pattern<E> pattern) {
		return startBlock().a(pattern).optional();
	}

	public PatternBuilder<E> optional(final PatternBuilder<E> patternBuilder) {
		return optional(patternBuilder.getPatternReference());
	}

	public PatternBuilder<E> repeatIndefinitely(final Pattern<E> pattern) {
		return startBlock().a(pattern).repeatIndefinitely();
	}

	public PatternBuilder<E> repeatIndefinitely(final PatternBuilder<E> patternBuilder) {
		return repeatIndefinitely(patternBuilder.getPatternReference());
	}

	public PatternBuilder<E> not(@SuppressWarnings("unchecked") final E... element) {
		stack.peek().add(new NotEqualToAnyPattern<E>(element));
		return this;
	}

	public PatternBuilder<E> not(@SuppressWarnings("unchecked") final Class<? extends E>... elementClass) {
		stack.peek().add(new NotAnyClassPattern<>(elementClass));
		return this;
	}

	public PatternBuilder<E> startBlock() {
		stack.addFirst(new ArrayList<>());
		return this;
	}

	public PatternBuilder<E> endBlock() {
		List<Pattern<E>> top = stack.pop();
		if (stack.isEmpty()) {
			stack.addFirst(new ArrayList<>());
		}
		stack.peek().add(new AndPattern<>(top));
		return this;
	}

	public PatternBuilder<E> endOrBlock() {
		List<Pattern<E>> top = stack.pop();
		if (stack.isEmpty()) {
			stack.addFirst(new ArrayList<>());
		}
		stack.peek().add(new OrPattern<>(top));
		return this;
	}

	public PatternBuilder<E> optional() {
		List<Pattern<E>> top = stack.pop();
		if (stack.isEmpty()) {
			stack.addFirst(new ArrayList<>());
		}
		stack.peek().add(new OptionalPattern<>(new AndPattern<>(top)));
		return this;
	}

	public PatternBuilder<E> repeatIndefinitely() {
		List<Pattern<E>> top = stack.pop();
		if (stack.isEmpty()) {
			stack.addFirst(new ArrayList<>());
		}
		stack.peek().add(new RepeatIndefinitelyPattern<>(new AndPattern<>(top)));
		return this;
	}

	public PatternBuilder<E> repeatAtLeast(final int nTimes) {
		List<Pattern<E>> top = stack.pop();
		if (stack.isEmpty()) {
			stack.addFirst(new ArrayList<>());
		}
		stack.peek().add(new RepeatAtLeastPattern<>(new AndPattern<>(top), nTimes));
		return this;
	}

	public PatternBuilder<E> repeatExactly(final int nTimes) {
		List<Pattern<E>> top = stack.pop();
		if (stack.isEmpty()) {
			stack.addFirst(new ArrayList<>());
		}
		stack.peek().add(new RepeatExactlyPattern<>(new AndPattern<>(top), nTimes));
		return this;
	}

	public PatternBuilder<E> repeatWhileFalse(final Condition<E> condition) {
		List<Pattern<E>> top = stack.pop();
		if (stack.isEmpty()) {
			stack.addFirst(new ArrayList<>());
		}
		stack.peek().add(new RepeatWhilePattern<E>(new AndPattern<>(top), new NotCondition<>(condition)));
		return this;
	}

	public PatternBuilder<E> repeatWhile(final Condition<E> condition) {
		List<Pattern<E>> top = stack.pop();
		if (stack.isEmpty()) {
			stack.addFirst(new ArrayList<>());
		}
		stack.peek().add(new RepeatWhilePattern<E>(new AndPattern<>(top), condition));
		return this;
	}

	public PatternBuilder<E> ifTrue(final Condition<E> condition) {
		List<Pattern<E>> top = stack.pop();
		if (stack.isEmpty()) {
			stack.addFirst(new ArrayList<>());
		}
		stack.peek().add(new IfPattern<E>(new AndPattern<>(top), condition));
		return this;
	}

	public PatternBuilder<E> ifFalse(final Condition<E> condition) {
		List<Pattern<E>> top = stack.pop();
		if (stack.isEmpty()) {
			stack.addFirst(new ArrayList<>());
		}
		stack.peek().add(new IfPattern<E>(new AndPattern<>(top), new NotCondition<>(condition)));
		return this;
	}

	public PatternBuilder<E> any() {
		stack.peek().add(new AnyPattern<>());
		return this;
	}

	public PatternBuilder<E> itself() {
		stack.peek().add(new SelfPattern<>(new AndPattern<>(stack.getLast())));
		return this;
	}

	public Pattern<E> build() {
		if (stack.size() > 1) {
			throw new IllegalStateException("Unclosed blocks");
		}

		AndPattern<E> pattern = new AndPattern<>(stack.peek());
		patternReference.set(pattern);
		return pattern;
	}

	public PatternReference<E> getPatternReference() {
		return patternReference;
	}

}

package sequencepattern.test;

import org.junit.Assert;
import org.junit.Test;

import sequencepattern.condition.Condition;
import sequencepattern.condition.ConditionBuilder;
import sequencepattern.condition.NumberOf;
import sequencepattern.pattern.ElementSequence;
import sequencepattern.pattern.Pattern;
import sequencepattern.pattern.PatternBuilder;

public class RepeatWhileTest {

	@Test
	public void repeatWhileTest() {
		ElementSequence<Character> sequence = new ElementSequence<>('(', '(', 'A', ')', ')', 'E');

		Condition<Character> condition = new ConditionBuilder<Character>()
				.startBlock()
					.isGreaterThan(new NumberOf<>('('), 0)
					.isEqual(new NumberOf<>('('), new NumberOf<>(')'))
				.endAndBlock()
				.build();

		Pattern<Character> pattern = new PatternBuilder<Character>()
				.startBlock()
					.any()
				.repeatWhileFalse(condition)
				.a('E')
				.build();

		System.out.println("Repeat while test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}

	@Test
	public void repeatWhileTest2() {
		ElementSequence<Character> sequence = new ElementSequence<>('(', '{', '(', 'A', ')', ')', '}', 'E');

		Condition<Character> condition = new ConditionBuilder<Character>()
				.startBlock()
					.isGreaterThan(new NumberOf<>('('), 0)
					.isEqual(new NumberOf<>('('), new NumberOf<>(')'))
					.isEqual(new NumberOf<>('{'), new NumberOf<>('}'))
				.endAndBlock()
				.build();

		Pattern<Character> pattern = new PatternBuilder<Character>()
				.startBlock()
					.any()
				.repeatWhileFalse(condition)
				.a('E')
				.build();

		System.out.println("Repeat while test2");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}
}

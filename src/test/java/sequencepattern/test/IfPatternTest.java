package sequencepattern.test;

import org.junit.Assert;
import org.junit.Test;

import sequencepattern.condition.Condition;
import sequencepattern.condition.ConditionBuilder;
import sequencepattern.condition.NotCondition;
import sequencepattern.condition.NumberOf;
import sequencepattern.pattern.ElementSequence;
import sequencepattern.pattern.Pattern;
import sequencepattern.pattern.PatternBuilder;

public class IfPatternTest {

	@Test
	public void ifPatternTest() {
		ElementSequence<Character> sequence = new ElementSequence<>('(', '(', 'A', ')', ')', 'E');
	
		Condition<Character> condition = new ConditionBuilder<Character>()
				.startBlock()
					.isGreaterThan(new NumberOf<>('('), 0)
					.isEqual(new NumberOf<>('('), new NumberOf<>(')'))
				.endAndBlock()
				.build();
		
		Condition<Character> hasLetterAOrB = new ConditionBuilder<Character>()
				.startBlock()
					.isGreaterThan(new NumberOf<Character>('B'), 0)
					.isGreaterThan(new NumberOf<Character>('A'), 0)
				.endOrBlock()
				.build();
	
		Pattern<Character> pattern = new PatternBuilder<Character>()
				.startBlock()
					.startBlock()
						.any()
					.repeatWhileFalse(condition)
				.ifFalse(new NotCondition<>(hasLetterAOrB))
				.a('E')
				.build();
	
		System.out.println("If pattern test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}
	
}

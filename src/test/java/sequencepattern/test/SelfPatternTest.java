package sequencepattern.test;

import org.junit.Assert;
import org.junit.Test;

import sequencepattern.pattern.ElementSequence;
import sequencepattern.pattern.Pattern;
import sequencepattern.pattern.PatternBuilder;

public class SelfPatternTest {

	@Test
	public void selfPatternTest() {
		ElementSequence<Character> sequence = new ElementSequence<>('(', '(', 'A', ')', ')', 'K');

		Pattern<Character> parenthesis = new PatternBuilder<Character>()
			.a('(')
			.startBlock()
				.itself()
				.a(Character.class)
			.endOrBlock()
			.a(')')
			.build();
		
		Pattern<Character> pattern = new PatternBuilder<Character>()
				.a(parenthesis)
				.a('K')
				.build();

		System.out.println("Self pattern test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}
	
	@Test
	public void earlySelfTest() {
//		ElementSequence<Character> sequence = new ElementSequence<>('A', '.', 'A', '.', 'A');
//		
//		Pattern<Character> pattern = new PatternBuilder<Character>()
//			.startBlock()
//				.itself()
//				.a('.')
//			.optional()
//			.a('A')
//			.build();
//
//		System.out.println("Early self test");
//		System.out.println(sequence.match(pattern));
//		Assert.assertTrue(sequence.matchesExactly(pattern));
	}
	
	
}

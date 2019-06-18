package sequencepattern.test;

import org.junit.Assert;
import org.junit.Test;

import sequencepattern.pattern.ElementSequence;
import sequencepattern.pattern.Pattern;
import sequencepattern.pattern.PatternBuilder;

public class AndPatternTest {

	@Test
	public void simpleAndPatternTest() {
		PatternBuilder<Number> builder = new PatternBuilder<>();

		ElementSequence<Number> sequence = new ElementSequence<>(2, 5, 4);

		Pattern<Number> pattern = builder.a(Integer.class).a(5).a(Number.class).build();

		System.out.println("Simple AndPattern test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}

	@Test
	public void multipleResultAndPatternTest() {
		PatternBuilder<Number> builder = new PatternBuilder<>();

		ElementSequence<Number> sequence = new ElementSequence<>(2, 5, 4);

		Pattern<Number> pattern = builder
				.startBlock()
					.a(Integer.class)
					.a(Number.class)
				.endOrBlock()
				.a(5)
				.a(Number.class)
				.build();

		System.out.println("AndPattern test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}

	@Test
	public void multipleResultAndPatternTest2() {
		PatternBuilder<Number> builder = new PatternBuilder<>();

		ElementSequence<Number> sequence = new ElementSequence<>(2, 5, 5, 4);

		Pattern<Number> pattern = builder
				.startBlock()
					.startBlock()
						.a(2)
						.startBlock()
							.a(Integer.class)
							.a(Number.class)
						.endOrBlock()
						.startBlock()
							.a(5)
						.optional()
					.endBlock()
					.startBlock()
						.a(2)
						.startBlock()
							.a(5)
						.repeatIndefinitely()
					.endBlock()
				.endOrBlock()
				.a(Number.class)
				.optional()
				.build();

		System.out.println("AndPattern test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}
	
}

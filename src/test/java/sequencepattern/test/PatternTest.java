package sequencepattern.test;

import org.junit.Assert;
import org.junit.Test;

import sequencepattern.pattern.ElementSequence;
import sequencepattern.pattern.Pattern;
import sequencepattern.pattern.PatternBuilder;

public class PatternTest {

	@Test
	public void simplePatternTest() {
		PatternBuilder<Number> builder = new PatternBuilder<>();

		ElementSequence<Number> sequence = new ElementSequence<>(2, 5, 4, 4);

		Pattern<Number> pattern = builder.a(Integer.class).a(5).any().a(Number.class).build();

		System.out.println("Simple pattern test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}

	@Test
	public void simpleRepeatTest() {
		PatternBuilder<Number> builder = new PatternBuilder<>();

		ElementSequence<Number> sequence = new ElementSequence<>(2, 2, new Float(5), 4);

		Pattern<Number> pattern = builder.a(Integer.class).repeatIndefinitely().any().a(4).build();

		System.out.println("Simple repeat test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}

	@Test
	public void multipleRepeatTest() {
		PatternBuilder<Number> builder = new PatternBuilder<>();

		ElementSequence<Number> sequence = new ElementSequence<>(2, 2, 5, 2, 5, 5);

		Pattern<Number> pattern = builder
				.startBlock()
					.startBlock()
						.a(2)
					.repeatIndefinitely()
					.a(5)
				.repeatIndefinitely()
				.build();

		System.out.println("Multiple repeat test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}

	@Test
	public void repeatNTimesTest() {
		PatternBuilder<Number> builder = new PatternBuilder<>();

		ElementSequence<Number> sequence = new ElementSequence<>(2, 5, 2, 5, 2, 5);

		Pattern<Number> pattern = builder.startBlock()
				.a(2).a(5).repeatExactly(3).build();

		System.out.println("Repeat n times test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}
	
	@Test
	public void simpleOrTest() {
		PatternBuilder<Number> builder = new PatternBuilder<>();

		ElementSequence<Number> sequence = new ElementSequence<>(2, 5, 2, 4, 2, 7, 1, 2, 3);

		Pattern<Number> pattern = builder.a(2).startBlock().a(5).a(4).a(7).endOrBlock().repeatIndefinitely()
				.a(1).a(2).a(3).build();

		System.out.println("Simple or test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}

	@Test
	public void simpleOptionalTest() {
		PatternBuilder<Number> builder = new PatternBuilder<>();

		ElementSequence<Number> sequence1 = new ElementSequence<>(1, 2, 4);
		ElementSequence<Number> sequence2 = new ElementSequence<>(1, 2, 3, 4);

		Pattern<Number> pattern = builder.a(1).a(2).startBlock().a(3).optional().a(4).build();

		System.out.println("Simple optional test");
		System.out.println(sequence1.match(pattern));
		Assert.assertTrue(sequence1.matchesExactly(pattern));

		System.out.println(sequence2.match(pattern));
		Assert.assertTrue(sequence2.matchesExactly(pattern));
	}

}

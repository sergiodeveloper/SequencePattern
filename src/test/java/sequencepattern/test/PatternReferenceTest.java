package sequencepattern.test;

import org.junit.Assert;
import org.junit.Test;

import sequencepattern.pattern.ElementSequence;
import sequencepattern.pattern.Pattern;
import sequencepattern.pattern.PatternBuilder;
import sequencepattern.pattern.PatternReference;

public class PatternReferenceTest {

	@Test
	public void simplePatternReferenceTest() {
		PatternBuilder<Number> builder = new PatternBuilder<>();

		ElementSequence<Number> sequence = new ElementSequence<>(2, 5, 4);

		PatternReference<Number> pattern = new PatternReference<>();

		pattern.set(builder.a(Integer.class).a(5).a(Number.class).build());

		System.out.println("Simple PatternReference test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}

	@Test
	public void builderReferenceTest() {
		ElementSequence<Number> sequence = new ElementSequence<>(2, 5, 4);

		PatternBuilder<Number> builder1 = new PatternBuilder<>();
		PatternBuilder<Number> builder2 = new PatternBuilder<>();

		builder1.a(2).a(5);

		builder2.a(builder1).a(4);
		builder1.build();

		Pattern<Number> pattern = builder2.build();

		System.out.println("Builder reference test");
		System.out.println(sequence.match(pattern));
		Assert.assertTrue(sequence.matchesExactly(pattern));
	}

}

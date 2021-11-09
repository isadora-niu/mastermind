package mastermind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResolverTest {

	@Test
	@DisplayName("secret: 1243, answer: 1254, should return 2 exacts 1 partial")
	void testExemple1() {
		List<Integer> secret = Tools.getUnmodifiableList(1, 2, 4, 3);
		List<Integer> answer = Tools.getUnmodifiableList(1, 2, 5, 4);
		Resolver resolver = new Resolver(secret);
		Matches result = resolver.resolve(answer);
		assertEquals(2, result.getPerfectMatches());
		assertEquals(1, result.getPartialMatches());
	}
	
	@Test
	@DisplayName("secret: 1243, answer: 2143, should return 2 exacts 2 partials")
	void testExemple2() {
		List<Integer> secret = Tools.getUnmodifiableList(1, 2, 4, 3);
		List<Integer> answer = Tools.getUnmodifiableList(2, 1, 4, 3);
		Resolver resolver = new Resolver(secret);
		Matches result = resolver.resolve(answer);
		assertEquals(2, result.getPerfectMatches());
		assertEquals(2, result.getPartialMatches());
	}
	
	@Test
	@DisplayName("secret: 7734, answer: 1270, should return 0 exacts 1 partials")
	void testExemple3() {
		List<Integer> secret = Tools.getUnmodifiableList(7, 7, 3, 4);
		List<Integer> answer = Tools.getUnmodifiableList(1, 2, 7, 0);
		Resolver resolver = new Resolver(secret);
		Matches result = resolver.resolve(answer);
		assertEquals(0, result.getPerfectMatches());
		assertEquals(1, result.getPartialMatches());
	}
	
	@Test
	@DisplayName("secret: 1234, answer: 2002, should return 0 exacts 1 partials")
	void testExemple4() {
		List<Integer> secret = Tools.getUnmodifiableList(1, 2, 3, 4);
		List<Integer> answer = Tools.getUnmodifiableList(2, 0, 0, 2);
		Resolver resolver = new Resolver(secret);
		Matches result = resolver.resolve(answer);
		assertEquals(0, result.getPerfectMatches());
		assertEquals(1, result.getPartialMatches());
	}
	
	@Test
	@DisplayName("secret: 1234, answer: 2200, should return 1 exacts 0 partials")
	void testExemple5() {
		List<Integer> secret = Tools.getUnmodifiableList(1, 2, 3, 4);
		List<Integer> answer = Tools.getUnmodifiableList(2, 2, 0, 0);
		Resolver resolver = new Resolver(secret);
		Matches result = resolver.resolve(answer);
		assertEquals(1, result.getPerfectMatches());
		assertEquals(0, result.getPartialMatches());
	}
	
	@Test
	@DisplayName("secret: 3129, answer: 1249, should return 1 exacts 2 partials")
	void testExemple6() {
		List<Integer> secret = Tools.getUnmodifiableList(3, 1, 2, 9);
		List<Integer> answer = Tools.getUnmodifiableList(1, 2, 4, 9);
		Resolver resolver = new Resolver(secret);
		Matches result = resolver.resolve(answer);
		assertEquals(1, result.getPerfectMatches());
		assertEquals(2, result.getPartialMatches());
	}
	
	@Test
	@DisplayName("secret: 1234, answer: 1234, should return 4 exacts 0 partials")
	void testExemple7() {
		List<Integer> secret = Tools.getUnmodifiableList(1, 2, 3, 4);
		List<Integer> answer = Tools.getUnmodifiableList(1, 2, 3, 4);
		Resolver resolver = new Resolver(secret);
		Matches result = resolver.resolve(answer);
		assertEquals(4, result.getPerfectMatches());
		assertEquals(0, result.getPartialMatches());
	}
	
	@Test
	@DisplayName("secret: 2234, answer: 2234, should return 4 exacts 0 partials")
	void testExemple8() {
		List<Integer> secret = Tools.getUnmodifiableList(2, 2, 3, 4);
		List<Integer> answer = Tools.getUnmodifiableList(2, 2, 3, 4);
		Resolver resolver = new Resolver(secret);
		Matches result = resolver.resolve(answer);
		assertEquals(4, result.getPerfectMatches());
		assertEquals(0, result.getPartialMatches());
	}

}

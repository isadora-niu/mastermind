package mastermind;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CodeElementTest {

	@Test
	void testPerfectMatch() {
		CodeElement secretElement = new CodeElement(2,3);
		CodeElement answerElement = new CodeElement(2,3);
		boolean perfect = secretElement.perfectmatch(answerElement);
		assertEquals(true, perfect);
		assertEquals(secretElement, answerElement.getPerfectMatch());
		assertEquals(answerElement, secretElement.getPerfectMatch());
	}

	@Test
	void testPerfectMatchWrongIndex() {
		CodeElement secretElement = new CodeElement(2,3);
		CodeElement answerElement = new CodeElement(2,1);
		boolean perfect = secretElement.perfectmatch(answerElement);
		assertEquals(false, perfect);
		assertEquals(false, answerElement.hasPerfectMatch());
		assertEquals(false, secretElement.hasPerfectMatch());
	}
	
	@Test
	void testPerfectMatchWrongDigit() {
		CodeElement secretElement = new CodeElement(5,1);
		CodeElement answerElement = new CodeElement(2,1);
		boolean perfect = secretElement.perfectmatch(answerElement);
		assertEquals(false, perfect);
		assertEquals(false, answerElement.hasPerfectMatch());
		assertEquals(false, secretElement.hasPerfectMatch());
	}
}

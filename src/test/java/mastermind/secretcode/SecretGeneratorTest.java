package mastermind.secretcode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class SecretGeneratorTest {

	@Test
	void testgeneration() {
		SecretGenerator generator = new SecretGenerator();
		List<Integer> secret = generator.generateSecretCode(4);
		assertEquals(4, secret.size());
		secret.forEach(i -> {
			assertTrue(i<10);
		});
		assertThrows(UnsupportedOperationException.class, ()->{
			secret.add(5);
		});
	}

}

package mastermind.secretcode;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import mastermind.ISecretGenerator;

public class SecretGenerator implements ISecretGenerator {

	@Override
	public List<Integer> generateSecretCode(int size) {
		Random r = new Random();
		return r.ints(4,0,9)
				.mapToObj(Integer::new)
				.collect(Collectors.toUnmodifiableList());
	}

}

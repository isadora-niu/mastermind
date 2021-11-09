package mastermind;

import java.util.List;

public interface ISecretGenerator {

	/**
	 * generate a secret code containing [size] randoms digits
	 * @param size
	 * @return
	 */
	public List<Integer> generateSecretCode(int size);
}

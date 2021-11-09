package mastermind;

import java.io.IOException;
import java.util.List;

public interface IActionsController {
	/**
	 * warn the user that a new game is starting and ask for the first answer
	 * @return ListwInteger> containing the first try of the user
	 * @throws IOException
	 */
	public List<Integer> startGame() throws IOException;

	/**
	 * warn the user about partial and perfect matches and ask for a new answer
	 * @param matches
	 * @return ListwInteger> containing the new try of the user
	 * @throws IOException
	 */
	public List<Integer> askForAnswer(Matches matches) throws IOException;

	/**
	 * warn the user that he/she won and ask him/her if he/she wants to replay
	 * @return
	 * @throws IOException
	 */
	public boolean askToPlayAgain() throws IOException;
}

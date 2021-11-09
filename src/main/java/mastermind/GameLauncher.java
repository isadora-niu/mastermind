package mastermind;

import java.io.IOException;
import java.util.List;

public class GameLauncher {

	private IActionsController controller;
	private ISecretGenerator secretGenerator;

	public GameLauncher(IActionsController controller, ISecretGenerator secretGenerator) {
		this.controller = controller;
		this.secretGenerator = secretGenerator;
	}
	
	public void launch() throws IOException {
		do {
			playAGame(controller, secretGenerator);
		} while (controller.askToPlayAgain());
	}

	private void playAGame(IActionsController controller, ISecretGenerator secretGenerator) throws IOException {
		List<Integer> secretCode = secretGenerator.generateSecretCode(4);
		Resolver resolver = new Resolver(secretCode);
		List<Integer> answer = controller.startGame();

		Matches resolveResult = resolver.resolve(answer);
		while (!resolveResult.isSecretBroken()) {
			answer = controller.askForAnswer(resolveResult);
			resolveResult = resolver.resolve(answer);
		}
	}
}

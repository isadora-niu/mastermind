package mastermind;

import java.io.IOException;

import mastermind.secretcode.SecretGenerator;
import mastermind.userSide.ConsoleController;

public class Starter {

	public static void main(String[] args) throws IOException {
		
		IActionsController controller = new ConsoleController(4);
		ISecretGenerator secretGenerator = new SecretGenerator();
		GameLauncher launcher = new GameLauncher(controller, secretGenerator);
		launcher.launch();
	}

}

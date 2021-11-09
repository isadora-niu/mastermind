package mastermind.userSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import mastermind.IActionsController;
import mastermind.Matches;

public class ConsoleController implements IActionsController {

	private int answerSize;

	public ConsoleController(int answerSize) {
		this.answerSize = answerSize;
	}

	@Override
	public boolean askToPlayAgain() throws IOException {
		String output = String.join(System.lineSeparator(), "You win !",
				"Do you want to play again ? (y/n)");
		System.out.println(output);
		return playAgain();
	}

	@Override
	public List<Integer> startGame() throws IOException {
		String output = String.join(System.lineSeparator(), "New Game !",
				"Enter a code composed of 4 digits until you brake the secret", "e.g: 1234",
				"Look at the Readme file for more explanations about the game");
		System.out.println(output);

		String answer = checkAndGetAnswer();
		return converttUserCode(answer);
	}

	@Override
	public List<Integer> askForAnswer(Matches matches) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int nbPerfect = 0; nbPerfect < matches.getPerfectMatches(); nbPerfect++) {
			sb.append("+");
		}
		for (int nbPartial = 0; nbPartial < matches.getPartialMatches(); nbPartial++) {
			sb.append("-");
		}
		sb.append(System.lineSeparator()).append("Enter a new code");
		System.out.println(sb.toString());
		String answer = checkAndGetAnswer();
		return converttUserCode(answer);
	}

	private String checkAndGetAnswer() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String answer = br.readLine();
		answer = answer.replaceAll("\\s", "");
		if (!Pattern.matches("^\\d{"+answerSize+"}$", answer)) {
			System.out.println("please enter a valid code containing only 4 digits.");
			return checkAndGetAnswer();
		}
		return answer;
	}

	private List<Integer> converttUserCode(String answer) {
		return answer.chars()
				.mapToObj(asc -> asc - '0')
				.collect(Collectors.toUnmodifiableList());
	}

	private boolean playAgain() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String answer = br.readLine().trim().toLowerCase();
		if ("y".equals(answer) || "n".equals(answer)) {
			return "y".equals(answer);
		}
		System.out.println("please answer by y or n");
		return playAgain();
	}
}

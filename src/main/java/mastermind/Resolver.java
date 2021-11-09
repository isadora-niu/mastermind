package mastermind;

import java.util.ArrayList;
import java.util.List;

public class Resolver {

	private List<Integer> secret;
	private int secretSize;
	private int perfectMatches;
	private int partialMatches;

	public Resolver(List<Integer> secret) {
		this.secret = secret;
		secretSize = secret.size();
	}

	public Matches resolve(List<Integer> userAnswer) {
		perfectMatches = 0;
		partialMatches = 0;
		if(userAnswer.size() != secretSize) {
			throw new IllegalStateException();
		}
		List<CodeElement> secretElements = new ArrayList<>(secretSize);
		List<CodeElement> answer = new ArrayList<>(secretSize);
		for(int i = 0; i < secretSize; i++) {
			secretElements.add(new CodeElement(secret.get(i), i));
			answer.add(new CodeElement(userAnswer.get(i), i));
		}
		secretElements.forEach(s -> {
			if(s.perfectmatch(answer.get(s.getIndex()))) {
				perfectMatches ++;
			}
		});
		secretElements.stream()
			.filter(CodeElement::hasNoMatch)
			.forEachOrdered(s -> {
				makePartialMatch(answer, s);
		});
		return new Matches(secretSize, perfectMatches, partialMatches);
	}

	private void makePartialMatch(List<CodeElement> answer, CodeElement s) {
		answer.stream()
			.filter(a -> a.hasNoMatch() && s.match(a))
			.findFirst()
			.ifPresent(a -> {
				a.setPartialMatch(s);
				s.setPartialMatch(a);
				partialMatches ++;
			});
	}

}

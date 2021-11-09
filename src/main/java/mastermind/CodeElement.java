package mastermind;

import java.util.Optional;

public class CodeElement {

	private int digit;
	private int index;
	private Optional<CodeElement> partialMatch;
	private Optional<CodeElement> perfectMatch;

	public CodeElement(int digit, int index) {
		this.partialMatch = Optional.empty();
		this.perfectMatch = Optional.empty();
		this.digit = digit;
		this.index = index;
	}

	public boolean hasPartialMatch() {
		return partialMatch.isPresent();
	}

	public boolean hasPerfectMatch() {
		return perfectMatch.isPresent();
	}
	
	public boolean hasNoMatch() {
		return !(hasPerfectMatch()||hasPartialMatch());
	}

	public CodeElement getPerfectMatch() {
		return perfectMatch.get();
	}

	public CodeElement getPartialtMatch() {
		return partialMatch.get();
	}

	public int getDigit() {
		return digit;
	}

	public int getIndex() {
		return index;
	}

	public void setPerfectMatch(CodeElement element) {
		if (hasPerfectMatch() || hasPartialMatch()) {
			throw new IllegalStateException("This element has already a match");
		}
		this.perfectMatch = Optional.of(element);
	}

	public void setPartialMatch(CodeElement element) {
		if (hasPerfectMatch() || hasPartialMatch()) {
			throw new IllegalStateException("This element has already a match");
		}
		this.partialMatch = Optional.of(element);
	}

	public boolean perfectmatch(CodeElement element) {
		boolean isPerfect = element.getDigit() == digit && element.getIndex() == index;
		if (isPerfect) {
			setPerfectMatch(element);
			element.setPerfectMatch(this);
		}
		return isPerfect;
	}

	public boolean match(CodeElement element) {
		return element.getDigit() == digit;
	}
}

package mastermind;

public class Matches {

	private int numberOfElement;
	private int perfectMatches;
	private int partialMatches;
	
	public Matches(int nbElements, int perfect, int partial) {
		this.numberOfElement = nbElements;
		this.perfectMatches = perfect;
		this.partialMatches = partial;
	}
	
	public int getPerfectMatches() {
		return perfectMatches;
	}
	public int getPartialMatches() {
		return partialMatches;
	}
	
	public boolean isSecretBroken() {
		return perfectMatches == numberOfElement;
	}
}

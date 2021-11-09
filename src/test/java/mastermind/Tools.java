package mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import mastermind.secretcode.SecretGenerator;

public class Tools {

	public static ISecretGenerator makeSecretGenerator(int a, int b, int c, int d) {
		ISecretGenerator generator = mock(SecretGenerator.class);
		final List<Integer> unmodifiableList = getUnmodifiableList(a, b ,c, d);
		doReturn(unmodifiableList).when(generator).generateSecretCode(4);
		return generator;
	}
	
	public static List<Integer> getUnmodifiableList(int a, int b, int c, int d){
		final List<Integer> list = new ArrayList<>(Arrays.asList(a, b, c, d));
	    return List.of(list.toArray(new Integer[]{}));
	}
}

package mastermind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import mastermind.secretcode.SecretGenerator;
import mastermind.userSide.ConsoleController;

@ExtendWith(MockitoExtension.class)
class GameLauncherIntegrationTest {

	@Mock
	ConsoleController controller;

	@Mock
	SecretGenerator generator;

	@InjectMocks
	GameLauncher launcher;

	@BeforeEach
	public void init() {
		reset(controller);
		reset(generator);
	}

	@Test
	void testMastermind() throws IOException {
		ArgumentCaptor<Matches> matchesCaptor = ArgumentCaptor.forClass(Matches.class);
		InOrder inorder = inOrder(controller);

		// secrets for the games
		doReturn(Tools.getUnmodifiableList(1, 2, 3, 4))
			.doReturn(Tools.getUnmodifiableList(2, 7, 0, 7))
			.when(generator).generateSecretCode(4);
		
		// start values to the games
		when(controller.startGame()).thenReturn(Tools.getUnmodifiableList(1, 2, 4, 5)).thenReturn(Tools.getUnmodifiableList(1, 2, 6, 5));
		// answers to the first game
		OngoingStubbing<List<Integer>> stubAnswers = when(controller.askForAnswer(any())).thenReturn(
				Tools.getUnmodifiableList(4, 5, 1, 2), Tools.getUnmodifiableList(2, 7, 4, 5),
				Tools.getUnmodifiableList(1, 2, 5, 6), Tools.getUnmodifiableList(1, 2, 8, 4),
				Tools.getUnmodifiableList(1, 2, 3, 4));

		// answers to the second game
		stubAnswers.thenReturn(Tools.getUnmodifiableList(1, 2, 5, 7), Tools.getUnmodifiableList(2, 7, 4, 5),
				Tools.getUnmodifiableList(7, 2, 7, 0), Tools.getUnmodifiableList(0, 2, 7, 7),
				Tools.getUnmodifiableList(2, 7, 0, 7));
		
		//replay once
		doReturn(true).doReturn(false).when(controller).askToPlayAgain();

		launcher.launch();

		// verify first game
		inorder.verify(controller, times(1)).startGame();
		inorder.verify(controller, times(5)).askForAnswer(matchesCaptor.capture());
		inorder.verify(controller, times(1)).askToPlayAgain();

		// verify second game
		inorder.verify(controller).startGame();
		inorder.verify(controller, times(5)).askForAnswer(matchesCaptor.capture());
		inorder.verify(controller).askToPlayAgain();
		inorder.verifyNoMoreInteractions();

		List<Matches> capturedMatches = matchesCaptor.getAllValues();
		assertEquals(10, capturedMatches.size());
		
		//s: 1234 a: 1245
		assertEquals(2, capturedMatches.get(0).getPerfectMatches());
		assertEquals(1, capturedMatches.get(0).getPartialMatches());
		
		//s: 1234 a: 4512
		assertEquals(0, capturedMatches.get(1).getPerfectMatches());
		assertEquals(3, capturedMatches.get(1).getPartialMatches());
		
		//s: 1234 a: 2745
		assertEquals(0, capturedMatches.get(2).getPerfectMatches());
		assertEquals(2, capturedMatches.get(2).getPartialMatches());
		
		//s: 1234 a: 1256
		assertEquals(2, capturedMatches.get(3).getPerfectMatches());
		assertEquals(0, capturedMatches.get(3).getPartialMatches());
		
		//s: 1234 a: 1284
		assertEquals(3, capturedMatches.get(4).getPerfectMatches());
		assertEquals(0, capturedMatches.get(4).getPartialMatches());
		
		//s: 2707 a: 1265
		assertEquals(0, capturedMatches.get(5).getPerfectMatches());
		assertEquals(1, capturedMatches.get(5).getPartialMatches());
		
		//s: 2707 a: 1257
		assertEquals(1, capturedMatches.get(6).getPerfectMatches());
		assertEquals(1, capturedMatches.get(6).getPartialMatches());
		
		//s: 2707 a: 2745
		assertEquals(2, capturedMatches.get(7).getPerfectMatches());
		assertEquals(0, capturedMatches.get(7).getPartialMatches());
		
		//s: 2707 a: 7270
		assertEquals(0, capturedMatches.get(8).getPerfectMatches());
		assertEquals(4, capturedMatches.get(8).getPartialMatches());
		
		//s: 2707 a: 0277
		assertEquals(1, capturedMatches.get(9).getPerfectMatches());
		assertEquals(3, capturedMatches.get(9).getPartialMatches());
		
	}

}

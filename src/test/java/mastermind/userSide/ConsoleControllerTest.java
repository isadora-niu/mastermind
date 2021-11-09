package mastermind.userSide;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import mastermind.Matches;

class ConsoleControllerTest {

	ConsoleController controller = new ConsoleController(4);

	@Test
	void testPlayAgain() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream("y".getBytes());
		System.setIn(in);
		boolean replay = controller.askToPlayAgain();
		assertTrue(replay);

		in = new ByteArrayInputStream("n ".getBytes());
		System.setIn(in);
		replay = controller.askToPlayAgain();
		assertFalse(replay);
		/*
		 * ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 * System.setOut(new PrintStream(outContent)); in = new
		 * ByteArrayInputStream(String.join(System.lineSeparator(), "nn",
		 * "n").getBytes()); System.setIn(in); replay = controller.askToPlayAgain();
		 * assertFalse(replay);
		 * assertTrue(outContent.toString().contains("please answer by y or n"));
		 */
	}

	@Test
	void teststartGame() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream("1234".getBytes());
		System.setIn(in);
		List<Integer> codes = controller.startGame();
		assertTrue(codes.get(0) == 1);
		assertTrue(codes.get(1) == 2);
		assertTrue(codes.get(2) == 3);
		assertTrue(codes.get(3) == 4);

		in = new ByteArrayInputStream(" 6 2 3 4".getBytes());
		System.setIn(in);
		codes = controller.startGame();
		assertTrue(codes.get(0) == 6);
		assertTrue(codes.get(1) == 2);
		assertTrue(codes.get(2) == 3);
		assertTrue(codes.get(3) == 4);
	}

	@Test
	void testaskForAnswer() throws IOException {
		Matches matches = new Matches(4,2,1);
		ByteArrayInputStream in = new ByteArrayInputStream("1234".getBytes());
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setIn(in);
		List<Integer> codes = controller.askForAnswer(matches);
		assertTrue(codes.get(0) == 1);
		assertTrue(codes.get(1) == 2);
		assertTrue(codes.get(2) == 3);
		assertTrue(codes.get(3) == 4);
		assertTrue(outContent.toString().contains("++-"));
	}
}

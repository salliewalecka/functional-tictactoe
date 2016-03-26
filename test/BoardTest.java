import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BoardTest {

    private PrintStream printStream;
    private Board board;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        board = new Board(printStream, asList(" ", " ", " ", " ", " ", " ", " ", " ", " "));
    }

    @Test
    public void shouldPrintBoard() {
        Board board2 = new Board(printStream, asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));

        board2.printBoard();

        verify(printStream).println(contains(
                " 1| 2 |3\n" +
                        "---------\n" +
                        " 4| 5 |6\n" +
                        "---------\n" +
                        " 7| 8 |9"));
    }

    @Test
    public void shouldNotUpdateBoardIfPositionIsTaken() {
        Board board = new Board(printStream, asList(" ", " ", "X", " ", " ", " ", " ", " ", " "));

        assertFalse(board.updateBoard("O", 2));
    }

    @Test
    public void shouldUpdateBoardIfPositionIsNotTaken() {
        Board board = new Board(printStream, asList(" ", " ", "X", " ", " ", " ", " ", " ", " "));

        assertTrue(board.updateBoard("O", 1));
    }

    @Test
    public void shouldBeFilled() {
        Board board = new Board(printStream, asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));

        assertFalse(board.notFilled());
    }

    @Test
    public void shouldNotBeFilled() {
        Board board = new Board(printStream, asList("1", "2", " ", "4", "5", "6", "7", "8", "9"));

        assertTrue(board.notFilled());
    }




}
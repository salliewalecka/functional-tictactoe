import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.*;

public class GameTest {

    private Board board;
    private PrintStream printStream;
    private BufferedReader reader;
    private Game game;
    private final String DEFAULT_MOVE = "1";
    private final String symbol = "O";

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        board = mock(Board.class);
        reader = mock(BufferedReader.class);
        game = new Game(printStream, reader, board ,symbol);
    }

    @Test
    public void shouldPrintMoveMessageWhenPlayerGetsMove() throws IOException {
        when(reader.readLine()).thenReturn(DEFAULT_MOVE);
        when(board.notFilled()).thenReturn(true).thenReturn(false);
        when(board.updateBoard(anyString(), anyInt())).thenReturn(true);

        game.start();

        verify(printStream).println(contains("Make a move by entering an integer from 1-9"));
    }

    @Test
    public void shouldRedrawBoardAfterMove() throws IOException {
        when(reader.readLine()).thenReturn(DEFAULT_MOVE);
        when(board.notFilled()).thenReturn(true).thenReturn(false);
        when(board.updateBoard(anyString(), anyInt())).thenReturn(true);

        game.start();

        verify(board).updateBoard("X", 0);
        verify(board).printBoard();
    }

    @Test
    public void shouldPromptSecondPlayerToMakeMove() throws IOException {
        when(reader.readLine()).thenReturn(DEFAULT_MOVE).thenReturn("4");
        when(board.notFilled()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(board.updateBoard(anyString(), anyInt())).thenReturn(true);

        game.start();

        verify(board).updateBoard("X", 0);
        verify(board).updateBoard("O", 3);
        verify(board, times(2)).printBoard();
    }

    @Test
    public void alertWhenGameIsADraw() throws IOException {
        when(board.notFilled()).thenReturn(false);

        game.start();

        verify(printStream).println(contains("Game Is A Draw"));
    }




}
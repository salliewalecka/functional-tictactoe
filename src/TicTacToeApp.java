import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static java.util.Arrays.asList;

public class TicTacToeApp {
    public static void main(String[] args) {

        List<String> positions = asList(" ", " ", " ", " ", " ", " ", " ", " ", " ");
        Board board = new Board(System.out, positions);
        Game game = new Game(System.out, new BufferedReader(new InputStreamReader(System.in)), board, "O");
        board.printBoard();
        game.start();

    }
}

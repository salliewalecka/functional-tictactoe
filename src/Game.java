import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Game {

    private PrintStream printStream;
    private BufferedReader reader;
    private Board board;
    private String player;
    private List<String> players = asList("X", "O");

    public Game(PrintStream printStream, BufferedReader reader, Board board, String player) {
        this.printStream = printStream;
        this.reader = reader;
        this.board = board;
        this.player = player;
    }

    public void start() {
        while (board.notFilled()) {
            togglePlayer();
            int position = promptMove();
            makeMove(player, position);
            board.printBoard();
        }
        printStream.println("Game Is A Draw");
    }

    private int promptMove() {
        int move = 0;
        printStream.println("Make a move by entering an integer from 1-9");
        try {
            String response = reader.readLine();
            move = Integer.parseInt(response);
        } catch (IOException e) {
        }
        return move - 1;
    }


    private void makeMove(String symbol, int position) {

        Optional getCell = board.emptyCell(position);
        getCell.orElseGet(() -> {
            printStream.println("Location already taken");
            int newPosition = promptMove();
            makeMove(symbol, newPosition);
            return null;
        });
        board.updateBoard(symbol, position);
    }

    private void togglePlayer() {
        Predicate<String> isNotCurrentPlayer = x -> !x.equals(this.player);

        player = players.stream()
                .filter(isNotCurrentPlayer)
                .collect(Collectors.joining(""));
    }
}

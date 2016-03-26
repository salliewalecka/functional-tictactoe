import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Board {

    private PrintStream printStream;
    private List<String> positions;

    public Board(PrintStream printStream, List<String> positions) {
        this.printStream = printStream;
        this.positions = positions;
    }

    public void printBoard() {
        printStream.println(
                " " + positions.get(0) + "| " + positions.get(1) + " |" + positions.get(2) + "\n" +
                        "---------\n" +
                        " " + positions.get(3) + "| " + positions.get(4) + " |" + positions.get(5) + "\n" +
                        "---------\n" +
                        " " + positions.get(6) + "| " + positions.get(7) + " |" + positions.get(8) + "\n");
    }

    public boolean updateBoard(String symbol, int movePosition) {
        Optional madeMove = Stream.of(movePosition)
                .filter(move -> locationFree(move))
                .map(move -> positions.set(move, symbol))
                .findFirst();
//        madeMove.ifPresent(o -> {
//            printBoard();
//        });

        return madeMove.isPresent();
    }

    public boolean locationFree(int movePosition) {
        return positions.get(movePosition) == " ";
    }

    public boolean notFilled() {
        return positions.stream().filter(position -> position == " ").count() != 0;
    }

    public Optional emptyCell(int move) {
        return Stream.of(positions.get(move))
                .filter(symbol -> symbol.equals(" ") ).findFirst();
    }
}

import board.Board;
import board.Square;
import controller.GameController;
import controller.GameState;
import pieces.Color;
import player.Player;
import player.commands.Move;
import renderer.BoardRenderer;
import renderer.TerminalBoardRenderer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController controller = new GameController();
        BoardRenderer renderer = new TerminalBoardRenderer();
        Board board = Board.getInstance();
        Player white = new Player("p1", Color.WHITE);
        Player black = new Player("p2", Color.BLACK);
        controller.addPlayer(white);
        controller.addPlayer(black);

        System.out.println("Welcome to Chess!");

        while (controller.getGameState() == GameState.ACTIVE) {
            Player current = controller.getCurrentPlayer();
            renderer.render(board);
            System.out.println(current.getColor() + "'s turn. Enter move (e.g. e2 e4): ");

            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;

            try {
                // parse input
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    System.out.println("Invalid format. Use: e2 e4");
                    continue;
                }
                Square from = board.getSquareById(parts[0]);
                Square to = board.getSquareById(parts[1]);

                Move move = new Move(from, to, current);
                move.execute(controller);

                controller.switchTurn();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

//        System.out.println("Game over! Result: " + controller.getResult());
        scanner.close();
    }
}
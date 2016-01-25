package qwirkle.shared;

// game

import qwirkle.game.Bag;
import qwirkle.game.Board;
import qwirkle.game.Stone;
import qwirkle.player.HumanPlayer;
import qwirkle.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles generic CLI output across server and clients.
 */
public class GameController {

    public static final String ANSI_CLS = "\u001b[2J";
    public static final String ANSI_HOME = "\u001b[H";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ERROR_COLOR = "\u001B[31m";
    public static final String RETURN = "\n\r";

    private Game game;
    private GameView gameView;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    public int readInteger(String message) {
        int input = 0;
        boolean found = false;

        Scanner scanner = new Scanner(System.in);
        do {
            this.logSimple(message);
            try (Scanner line = new Scanner(scanner.nextLine());) {
                if (line.hasNextInt()) {
                    found = true;
                    input = line.nextInt();
                }
            }
        } while (!found);
        return input;
    }

    public String readString(String message) {
        String input = "";
        boolean stringRead = false;

        Scanner scanner = new Scanner(System.in);
        do {
            this.logSimple(message);
            try (Scanner line = new Scanner(scanner.nextLine());) {
                if (line.hasNext()) {
                    stringRead = true;
                    input = line.next();
                }
            }
        } while (!stringRead);
        return input;
    }

    public String[] readArray(String message) {
        String[] input;

        Scanner scanner = new Scanner(System.in);

        this.logSimple(message);
        input = scanner.nextLine().split(" ", -1);

        return input;
    }

    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //TODO: Handle Exceptions
        }
    }

    public void printScreen(String message) {
        this.clearScreen();
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
        System.out.println(message);
    }

    public void determineMove() {
        this.readArray("Make a move: (Format: A0 A0 1)");


    }

    public void logSimple(String message) {
        System.out.println(message + ANSI_RESET);
    }

    public void logBold(String message) {
        System.out.println(ANSI_BOLD + message + ANSI_RESET);
    }

    public static void logServerError(Exception e) {
        // print this message in red
        System.out.println(ERROR_COLOR + e.getMessage() + ANSI_RESET);
    }

    public static void logClientError(Exception e) {
        // print this message in red
        System.out.println(ERROR_COLOR + e.getMessage() + ANSI_RESET);
    }

    private Player testPlayer;
    private Board board;

    public void startGame() {
        this.board = game.getBoard();

        testPlayer = new HumanPlayer(this, "Bouke");
        game.addPlayer(testPlayer);

        Stone stone1 = new Stone(Stone.Color.RED, Stone.Shape.STAR);
        Stone stone2 = new Stone(Stone.Color.GREEN, Stone.Shape.STAR);
        Stone stone3 = new Stone(Stone.Color.BLUE, Stone.Shape.STAR);
        Stone stone4 = new Stone(Stone.Color.YELLOW, Stone.Shape.STAR);
        Stone stone5 = new Stone(Stone.Color.PURPLE, Stone.Shape.STAR);
        Stone stone6 = new Stone(Stone.Color.ORANGE, Stone.Shape.STAR);

        Stone stone7 = new Stone(Stone.Color.GREEN, Stone.Shape.SQUARE);
        Stone stone8 = new Stone(Stone.Color.GREEN, Stone.Shape.CIRCLE);
        Stone stone9 = new Stone(Stone.Color.GREEN, Stone.Shape.HEART);

        Stone stone10 = new Stone(Stone.Color.RED, Stone.Shape.HEART);
        Stone stone11 = new Stone(Stone.Color.RED, Stone.Shape.SQUARE);
        Stone stone12 = new Stone(Stone.Color.RED, Stone.Shape.STAR);
        Stone stone13 = new Stone(Stone.Color.RED, Stone.Shape.CIRCLE);
        Stone stone14 = new Stone(Stone.Color.RED, Stone.Shape.CROSS);
        Stone stone15 = new Stone(Stone.Color.RED, Stone.Shape.DIAMOND);

        Stone stone16 = new Stone(Stone.Color.BLUE, Stone.Shape.HEART);
        Stone stone17 = new Stone(Stone.Color.BLUE, Stone.Shape.SQUARE);
        Stone stone18 = new Stone(Stone.Color.BLUE, Stone.Shape.CROSS);
        Stone stone19 = new Stone(Stone.Color.BLUE, Stone.Shape.CIRCLE);
        Stone stone20 = new Stone(Stone.Color.BLUE, Stone.Shape.DIAMOND);


        stone1.setLocation(0, -1);
        stone2.setLocation(0, 0);
        stone3.setLocation(0, 1);
        stone4.setLocation(0, 2);
        stone5.setLocation(0, 3);
        stone6.setLocation(0, 4);

        stone7.setLocation(-1, 0);
        stone8.setLocation(1, 0);
        stone9.setLocation(2, 0);

        stone10.setLocation(1, -1);
        stone11.setLocation(1, -2);
        stone12.setLocation(1, -3);
        stone13.setLocation(1, -4);
        stone14.setLocation(1, -5);
        stone15.setLocation(1, -6);

        stone16.setLocation(-1, 1);
        stone17.setLocation(-2, 1);
        stone18.setLocation(-3, 1);
        stone19.setLocation(1, 1);
        stone20.setLocation(2, 1);

        ArrayList<Stone> addStones = new ArrayList<>();
        addStones.add(stone1);
        addStones.add(stone2);
        addStones.add(stone3);
        addStones.add(stone4);
        addStones.add(stone5);
        addStones.add(stone6);

        System.out.println("points: " + board.placeStones(addStones));

//        addStones = new ArrayList<>();
//        addStones.add(stone7);
//        addStones.add(stone8);
//        addStones.add(stone9);
//
//        System.out.println("points: " + board.placeStones(addStones));

        addStones = new ArrayList<>();
        addStones.add(stone10);
        addStones.add(stone11);
        addStones.add(stone12);
        addStones.add(stone13);
        addStones.add(stone14);
        addStones.add(stone15);

        System.out.println("points: " + board.placeStones(addStones));

        addStones = new ArrayList<>();
        addStones.add(stone16);
        addStones.add(stone17);
        addStones.add(stone18);
        addStones.add(stone19);
        addStones.add(stone20);

        System.out.println("points: " + board.placeStones(addStones));

        System.out.println("Stone Board: " + board.coordinateToStone("a7", "b0"));

        System.out.println("Stone Hand: " + testPlayer.getHand().coordinateToStone("4"));
    }

    public void stopGame() {

    }

    public void getScore() {

    }

    public Bag getBag() {
        return game.getBag();
    }

    public Board getBoard() {
        return game.getBoard();
    }

    public boolean isFinished() {
        return game.isFinished();
    }

    public void updateView() {
        gameView.printGame(game.toString());
    }
}
package qwirkle.shared;

// game

import qwirkle.game.Bag;
import qwirkle.game.Board;
import qwirkle.game.Game;
import qwirkle.player.Player;

/**
 * Handles generic CLI output across server and clients.
 */
public class GameController {

    private Game game;
    private GameView gameView;

    private Player testPlayer1;
    private Player testPlayer2;
    private Board board;

    private Cli cli;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    public void startGame() {
//        this.board = game.getBoard();
//        this.cli = new Cli();
//
//        testPlayer1 = new HumanPlayer(this, "Bouke");
//        testPlayer2 = new HumanPlayer(this, "Chris");
//
//        game.players.setCurrentPlayer(testPlayer1);
//
//        game.addPlayer(testPlayer1);
//        game.addPlayer(testPlayer2);
//
//        Stone stone1 = new Stone(Stone.Color.RED, Stone.Shape.STAR);
//        Stone stone2 = new Stone(Stone.Color.GREEN, Stone.Shape.STAR);
//        Stone stone3 = new Stone(Stone.Color.BLUE, Stone.Shape.STAR);
//        Stone stone4 = new Stone(Stone.Color.YELLOW, Stone.Shape.STAR);
//        Stone stone5 = new Stone(Stone.Color.PURPLE, Stone.Shape.STAR);
//        Stone stone6 = new Stone(Stone.Color.ORANGE, Stone.Shape.STAR);
//
//        Stone stone7 = new Stone(Stone.Color.GREEN, Stone.Shape.SQUARE);
//        Stone stone8 = new Stone(Stone.Color.GREEN, Stone.Shape.CIRCLE);
//        Stone stone9 = new Stone(Stone.Color.GREEN, Stone.Shape.HEART);
//
//        Stone stone10 = new Stone(Stone.Color.RED, Stone.Shape.HEART);
//        Stone stone11 = new Stone(Stone.Color.RED, Stone.Shape.SQUARE);
//        Stone stone12 = new Stone(Stone.Color.RED, Stone.Shape.STAR);
//        Stone stone13 = new Stone(Stone.Color.RED, Stone.Shape.CIRCLE);
//        Stone stone14 = new Stone(Stone.Color.RED, Stone.Shape.CROSS);
//        Stone stone15 = new Stone(Stone.Color.RED, Stone.Shape.DIAMOND);
//
//        Stone stone16 = new Stone(Stone.Color.BLUE, Stone.Shape.HEART);
//        Stone stone17 = new Stone(Stone.Color.BLUE, Stone.Shape.SQUARE);
//        Stone stone18 = new Stone(Stone.Color.BLUE, Stone.Shape.CROSS);
//        Stone stone19 = new Stone(Stone.Color.BLUE, Stone.Shape.CIRCLE);
//        Stone stone20 = new Stone(Stone.Color.BLUE, Stone.Shape.DIAMOND);



//
//        stone1.setLocation(0, -1);
//        stone2.setLocation(0, 0);
//        stone3.setLocation(0, 1);
//        stone4.setLocation(0, 2);
//        stone5.setLocation(0, 3);
//        stone6.setLocation(0, 4);
//
//        stone7.setLocation(-1, 0);
//        stone8.setLocation(1, 0);
//        stone9.setLocation(2, 0);
//
//        stone10.setLocation(1, -1);
//        stone11.setLocation(1, -2);
//        stone12.setLocation(1, -3);
//        stone13.setLocation(1, -4);
//        stone14.setLocation(1, -5);
//        stone15.setLocation(1, -6);
//
//        stone16.setLocation(-1, 1);
//        stone17.setLocation(-2, 1);
//        stone18.setLocation(-3, 1);
//        stone19.setLocation(1, 1);
//        stone20.setLocation(2, 1);

//        ArrayList<Stone> addStones = new ArrayList<>();
//        addStones.add(stone1);
//        addStones.add(stone2);
//        addStones.add(stone3);
//        addStones.add(stone4);
//        addStones.add(stone5);
//        addStones.add(stone6);
//
//        System.out.println("first move 1: " + game.firstMove(testPlayer1, addStones));

//        addStones = new ArrayList<>();
//        addStones.add(stone7);
//        addStones.add(stone8);
//        addStones.add(stone9);
//
//        System.out.println("points: " + board.placeStones(addStones));
//
//        addStones = new ArrayList<>();
//        addStones.add(stone10);
//        addStones.add(stone11);
//        addStones.add(stone12);
//        addStones.add(stone13);
//        addStones.add(stone14);
//        addStones.add(stone15);

//        System.out.println("first move 2: " + game.firstMove(testPlayer2, addStones));

//        System.out.println("points: " + board.placeStones(addStones));

//        addStones = new ArrayList<>();
//        addStones.add(stone16);
//        addStones.add(stone17);
//        addStones.add(stone18);
//        addStones.add(stone19);
//        addStones.add(stone20);
//
//        System.out.println("first move 2: " + game.firstMove(testPlayer2, addStones));
//
//
//        System.out.println("Stone Hand: " + testPlayer1.getHand().toString());
//
//        System.out.println("Start with: " + game.longestRow());
//
//
//        this.updateView();
//        this.determineMove();
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
package qwirkle;

// game

import qwirkle.game.Game;
import qwirkle.shared.CliController;
//import qwirkle.shared.CliObserver;
// shared
// java

/**
 * The Qwirkle Main
 *
 * Created by Chris ter Beke and Bouke Regnerus
 */
public class Qwirkle {

    public static void main(String[] args) {
        // TODO: implement gameplay

//        Input input = new Input();
//        CliObserver cli = new CliObserver();

        CliController cli = new CliController();
        Game game = new Game();

        game.startGame();

//        cli.addObserver((Observable obj, Object arg) -> {
//            System.out.println("\nReceived response: " + arg);
//        });

//        new Thread(cli).start();
//
//        while(true){
//
            cli.logSimple(game.toString());
//
//            System.out.println(input.get());
//
//            if(input.get().equals("exit")){
//                break;
//            }
//
//            cli.addObserver((Observable obj, Object arg) -> {
//                out.println("\nReceived response: " + arg);
//            });
//
//            break;
//        }

//        input.close();
    }
}
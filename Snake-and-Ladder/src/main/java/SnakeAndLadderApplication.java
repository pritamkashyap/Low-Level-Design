import com.lld.model.Game;
import com.lld.model.Player;

import java.util.Scanner;

public class SnakeAndLadderApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ENTER BOARD SIZE");
        int boardSize = scanner.nextInt();
        System.out.println("ENTER NUMBER OF PLAYERS");
        int numOfPlayers = scanner.nextInt();
        System.out.println("ENTER NUMBER OF SNAKES");
        int numOfSnakes = scanner.nextInt();
        System.out.println("ENTER NUMBER OF LADDERS");
        int numOfLadders = scanner.nextInt();

        Game game = new Game(numOfLadders, numOfSnakes, boardSize);
        for(int i = 0 ; i<numOfPlayers; i++) {
            System.out.println("ENTER PLAYER NAME");
            String pName = scanner.next();
            Player player = new Player(pName);
            game.addPlayer(player);
        }
        game.playGame();

    }
}

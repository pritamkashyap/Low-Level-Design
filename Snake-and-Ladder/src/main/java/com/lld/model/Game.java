package com.lld.model;

import lombok.Getter;

import java.util.*;

@Getter
public class Game {
    private int numOfSnakes;
    private int numOfLadders;

    private Queue<Player> players;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    private Board board;
    private Dice dice;
    private  Random random;

    public Game (int numOfLadders, int numOfSnakes, int boardSize) {
        this.numOfLadders = numOfLadders;
        this.numOfSnakes = numOfSnakes;
        this.players = new ArrayDeque<>();
        snakes = new ArrayList<>(numOfSnakes);
        ladders = new ArrayList<>(numOfLadders);
        board = new Board(boardSize);
        dice = new Dice(1, 6, 2);
    }

    private void initBoard() {
        Set<String> startEndSet = new HashSet<>();
        random = new Random();
        for(int i = 0 ; i < numOfSnakes; i++) {
            while (true) {
                int snakeStart = random.nextInt(board.getStart(), board.getEnd() - 1);
                int snakeEnd = random.nextInt(board.getStart(), board.getEnd() - 1);
                if (snakeEnd >= snakeStart)
                    continue;
                String startEndPair = String.valueOf(snakeStart) + String.valueOf(snakeEnd);
                if (!startEndSet.contains(startEndPair)) {
                    Snake snake = new Snake(snakeStart, snakeEnd);
                    snakes.add(snake);
                    startEndSet.add(startEndPair);
                    break;
                }
            }
        }

        for(int i = 0 ; i < numOfLadders; i++) {
            while (true) {
                int ladderStart = random.nextInt(board.getStart(), board.getEnd() - 1);
                int ladderEnd = random.nextInt(board.getStart(), board.getEnd() - 1);
                if (ladderEnd <= ladderStart)
                    continue;
                String startEndPair = String.valueOf(ladderEnd) + String.valueOf(ladderStart);
                if (!startEndSet.contains(startEndPair)) {
                    Ladder ladder = new Ladder(ladderStart, ladderEnd);
                    ladders.add(ladder);
                    startEndSet.add(startEndPair);
                    break;
                }
            }
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    // 1, 2 , 3, 4, 5, 6

    public void playGame() {
        System.out.println("STARTED THE GAME");
        while (true) {
            Player currentPlayer = players.poll();
            System.out.println("CURRENT PLAYER " + currentPlayer.getName() + " " + currentPlayer.getPosition());
            int val = dice.roll();
            System.out.println("DICE VALUE " +val);
            int newPosition = currentPlayer.getPosition() + val;
            System.out.println("NEW POSITION " + newPosition);
            if(newPosition > board.getEnd()) {
                System.out.println("END OF BOARD IS " + board.getEnd());
                currentPlayer.setPosition(currentPlayer.getPosition());
                players.offer(currentPlayer);
            } else {
                currentPlayer.setPosition(getNewPosition(newPosition));
                System.out.println("NEW POSITION IS SET TO " + currentPlayer.getPosition());
                if(currentPlayer.getPosition() == board.getEnd()) {
                    currentPlayer.setWon(true);
                    System.out.println("Player " + currentPlayer.getName() + "WON");
                } else {
                    System.out.println("SETTING PLAYER " + currentPlayer.getName() + " POSITION TO" + currentPlayer.getPosition());
                    players.offer(currentPlayer);
                }
            }

            if(players.size() < 2) {
                break;
            }
        }
    }

    private int getNewPosition(int newPosition) {
        for(Snake snake : snakes) {
            System.out.println("SNAKE BITE ");
            return snake.getTail();
        }
        for (Ladder ladder: ladders) {
            if (ladder.getStart() == newPosition) {
                System.out.println("LADDER FOUND");
                return ladder.getEnd();
            }
        }
        return newPosition;
    }
}

package qwirkle.game;

import org.apache.commons.collections4.ListUtils;
import qwirkle.shared.Cli;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Board {

    private Map<Position, Stone> board;
    private List<Position> possibleMoves;

    public static final int SPACE_AROUND_BOARD = 3;
    public static final int ASCII_INTEGER = 64; //for upper case

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Initialize an empty board.
     */
    public Board() {
        this.board = new HashMap<>();
        this.possibleMoves = new ArrayList();

        // TODO: randomize first stone
//        Stone firstMove = new Stone(Stone.Color.BLUE, Stone.Shape.CIRCLE);
//        placeStone(firstMove, 0, 0);
    }

    public Map<Position, Stone> getBoard() {
        return this.board;
    }

    private Bounds boundsX = new Bounds();
    private Bounds boundsY = new Bounds();

    /**
     * Calculate and set the bounds of the board.
     *
     * @param position
     */
    private void calculateBoardSize(Position position) {
        int x = position.getX();
        int y = position.getY();

        if (x < boundsX.getMin()) {
            boundsX.setMin(x);
        } else if (x > boundsX.getMax()) {
            boundsX.setMax(x);
        }

        if (y < boundsY.getMin()) {
            boundsY.setMin(y);
        } else if (y > boundsY.getMax()) {
            boundsY.setMax(y);
        }
    }

    /**
     * @return X bounds of the board.
     */
    public Bounds getBoundsX() {
        return boundsX;
    }

    /**
     * @return Y bounds of the board.
     */
    public Bounds getBoundsY() {
        return boundsY;
    }

    /**
     * Place a stone on the board, check if it is valid to place this stone at the given position.
     *
     * @param stone Stone to place on the board.
     * @return Return boolean true or false depending on if the stone was placed.
     */
    public boolean placeStone(Stone stone) {
        if (!this.board.containsKey(stone.getLocation())) {
            if (this.validMove(stone)) {
                this.calculateBoardSize(stone.getLocation());
                this.removePossibleMove(stone);
                this.addPossibleMoves(stone);

                board.put(stone.getLocation(), stone);
                stone.setPlaced(true);
                return true;
            }
        }

        return false;
    }

    /**
     * @return Return true if the board is empty.
     */
    public boolean isEmpty() {
        return board.size() < 1;
    }

    /**
     * Return possible moves on the board.
     *
     * @return Return a list of possible moves.
     */
    public List<Position> getPossibleMoves() {
        return this.possibleMoves;
    }

    /**
     * Remove a stone from the board.
     *
     * @param stone Stone to remove.
     */
    public void removeStone(Stone stone) {
        // TODO: update possible moves
        this.board.remove(stone.getLocation());
        stone.setPlaced(false);
    }

    /**
     * Place a list of stones on the board.
     *
     * Amount of points for the stones that have been placed on the board,
     * or -1 if the list of stones could not be placed.
     *
     * @param stones List of stones to be placed.
     * @return points or -1
     */
    public int placeStones(List<Stone> stones) {
        List<List<Stone>> connectedColumns = new ArrayList<>();
        List<List<Stone>> connectedRows = new ArrayList<>();

        List<List<Stone>> connectedAll;
        List<Stone> placedStones = new ArrayList<>();
        int points = 0;

        for (Stone stone : stones) {
            if (this.placeStone(stone)) {
                placedStones.add(stone);
            } else {
                placedStones.forEach(this::removeStone);
                return -1;
            }
        }

        for (Stone stone : stones) {
            if (this.getColumn(stone).size() > 1) {
                if (connectedColumns.size() < 1) {
                    connectedColumns.add(this.getColumn(stone));
                } else {
                    connectedColumns.addAll(connectedColumns.stream()
                            .filter(list -> !list.equals(this.getColumn(stone)))
                            .map(list -> this.getColumn(stone))
                            .collect(Collectors.toList()));

                }
            }

            if (this.getRow(stone).size() > 1) {
                if (connectedRows.size() < 1) {
                    connectedRows.add(this.getRow(stone));
                } else {
                    connectedRows.addAll(connectedRows.stream()
                            .filter(list -> !list.equals(this.getRow(stone)))
                            .map(list -> this.getRow(stone))
                            .collect(Collectors.toList()));
                }
            }
        }

        connectedAll = ListUtils.union(connectedColumns, connectedRows);

        for (List<Stone> stonesList : connectedAll) {
            //Add six points if the user has a Qwirkle!
            if (stonesList.size() > 5) {
                points += 6;
            }
            points += stonesList.size();
        }

        return points;
    }

    /**
     * @param location Location on the board.
     * @return Return stone at the give location or an "empty" stone.
     */
    public Stone getStone(Position location) {
        Stone stone = this.board.get(location);

        if (stone == null) {
            return new Stone(Stone.Color.NULL, Stone.Shape.NULL);
        } else {
            return stone;
        }
    }

    /**
     * @param location     Location on the board.
     * @param possibleMove true if the stone returned should be a possible move or not.
     * @return Return stone at the give location or an "empty" stone.
     */
    public Stone getStone(Position location, boolean possibleMove) {
        Stone stone = this.board.get(location);

        if (stone == null) {
            Stone s = new Stone(Stone.Color.NULL, Stone.Shape.NULL);
            s.setTemporary(possibleMove);
            return s;
        } else {
            return stone;
        }
    }

    /**
     * Check if a given stone is unique in the given list.
     *
     * @param list  List of stones to be checked against.
     * @param stone Stone that should be checked.
     * @return Return boolean statement if the check was true or false.
     */
    public static boolean isUniqueShape(List<Stone> list, Stone stone) {
        for (Stone s : list) {
            if (s.getShape() == stone.getShape()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if a given stone is matching in the given list.
     *
     * @param list  List of stones to be checked against.
     * @param stone Stone that should be checked.
     * @return Return boolean statement if the check was true or false.
     */
    public static boolean isMatchingShape(List<Stone> list, Stone stone) {
        for (Stone s : list) {
            if (s.getShape() != stone.getShape()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if a given stone is unique in the given list.
     *
     * @param list  List of stones to be checked against.
     * @param stone Stone that should be checked.
     * @return Return boolean statement if the check was true or false.
     */
    public static boolean isUniqueColor(List<Stone> list, Stone stone) {
        for (Stone s : list) {
            if (s.getColor() == stone.getColor()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if a given stone is matching in the given list.
     *
     * @param list  List of stones to be checked against.
     * @param stone Stone that should be checked.
     * @return Return boolean statement if the check was true or false.
     */
    public static boolean isMatchingColor(List<Stone> list, Stone stone) {
        for (Stone s : list) {
            if (s.getColor() != stone.getColor()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param stone     Stone
     * @param direction Enum direction UP, DOWN, LEFT or RIGHT
     * @return Return a list of stones in that direction from the given stone.
     */
    public List<Stone> getDirection(Stone stone, Direction direction) {
        Position location = stone.getLocation();
        List<Stone> list = new ArrayList<>();

        Stone currentStone = stone;
        while (currentStone != null) {
            switch (direction) {
                case UP:
                    location = location.above();
                    break;
                case DOWN:
                    location = location.below();
                    break;
                case LEFT:
                    location = location.left();
                    break;
                case RIGHT:
                    location = location.right();
                    break;
            }

            if (stone != currentStone) {
                list.add(currentStone);
            }

            currentStone = this.board.get(location);
        }

        if (direction == Direction.LEFT || direction == Direction.UP) {
            Collections.reverse(list);
        }

        return list;
    }

    /**
     * @param stone       Stone
     * @param includeSelf Should the given stone be included in the row or not.
     * @return Return a list of stones that are in a connected row from the given stone.
     */
    public List<Stone> getRow(Stone stone, boolean includeSelf) {
        List<Stone> left = this.getDirection(stone, Direction.LEFT);
        List<Stone> right = this.getDirection(stone, Direction.RIGHT);

        if (includeSelf) {
            left.add(stone);
        }

        return ListUtils.union(left, right);
    }

    /**
     * @param stone       Stone
     * @param includeSelf Should the given stone be included in the column or not.
     * @return Return a list of stones that are in a connected column from the given stone.
     */
    public List<Stone> getColumn(Stone stone, boolean includeSelf) {
        List<Stone> up = this.getDirection(stone, Direction.UP);
        List<Stone> down = this.getDirection(stone, Direction.DOWN);

        if (includeSelf) {
            up.add(stone);
        }

        return ListUtils.union(up, down);
    }

    public List<Stone> getRow(Stone stone) {
        return this.getRow(stone, true);
    }

    public List<Stone> getColumn(Stone stone) {
        return this.getColumn(stone, true);
    }

    /**
     * @param list  Given list to be checked against.
     * @param stone Stone that should be checked.
     * @return Return boolean statement if the check was true or false.
     */
    public static boolean validateList(List<Stone> list, Stone stone) {
        if (Board.isUniqueShape(list, stone) && Board.isMatchingColor(list, stone)) {
            return true;
        } else if (Board.isMatchingShape(list, stone) && Board.isUniqueColor(list, stone)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param stone Stone that could be added to the board.
     * @return Return boolean statement if the move is valid or not.
     */
    public boolean validMove(Stone stone) {
        List<Stone> row = this.getRow(stone, false);
        List<Stone> column = this.getColumn(stone, false);

        if (this.board.size() < 1) {
            return true;
        }

        if (row.size() >= 1 && row.size() < 6 && column.size() >= 1 && column.size() < 6) {
            return validateList(row, stone) && validateList(column, stone);
        } else if (row.size() >= 1 && row.size() < 6) {
            return validateList(row, stone);
        } else if (column.size() >= 1 && column.size() < 6) {
            return validateList(column, stone);
        } else {
            return false;
        }
    }

    /**
     * Remove possible move from the list of possible moves.
     *
     * @param stone Position to be removed from the list.
     */
    public void removePossibleMove(Stone stone) {
        if (possibleMoves.contains(stone.getLocation())) {
            possibleMoves.remove(stone.getLocation());
        }
    }

    /**
     * Add possible moves to the list of possible moves.
     *
     * @param stone Stone for which possible moves should be checked and added.
     */
    public void addPossibleMoves(Stone stone) {
        if (!board.keySet().contains(stone.getLocation())) {
            Position location = stone.getLocation();

            if (this.getStone(location.above()).getShape() == Stone.Shape.NULL) {
                this.possibleMoves.add(location.above());
            }

            if (this.getStone(location.below()).getShape() == Stone.Shape.NULL) {
                this.possibleMoves.add(location.below());
            }

            if (this.getStone(location.left()).getShape() == Stone.Shape.NULL) {
                this.possibleMoves.add(location.left());
            }

            if (this.getStone(location.right()).getShape() == Stone.Shape.NULL) {
                this.possibleMoves.add(location.right());
            }
        }
    }

    /**
     * @param x X Coordinate (e.g. A3)
     * @param y Y Coordinate (e.g. B1)
     * @return Return the actual position on the board from the given coordinates.
     */
    public Position coordinateToPosition(String x, String y) {
        char[] charsX = x.toCharArray();
        char[] charsY = y.toCharArray();

        int letterX = (int) Character.toUpperCase(charsX[0]);
        int letterY = (int) Character.toUpperCase(charsY[0]);

        int positionX = 0, positionY = 0;

        if (letterX <= 90 & letterX >= 65) {
            positionX += ((letterX - ASCII_INTEGER) * 10) - 10;
            positionX += Character.getNumericValue(charsX[1]);
            positionX += this.getBoundsX().getMin() - SPACE_AROUND_BOARD;

        }

        if (letterY <= 90 & letterY >= 65) {
            positionY += ((letterY - ASCII_INTEGER) * 10) - 10;
            positionY += Character.getNumericValue(charsY[1]);
            positionY += this.getBoundsY().getMin() - SPACE_AROUND_BOARD;
        }

        return new Position(positionX, positionY);

    }

    /**
     * Create a ASCII representation of the board.
     * <p>
     * This method return an ASCII representation of the board,
     * it uses the shapes and colours of the stones.
     * <p>
     * It adds empty fields around the placed stones where new stones could be placed.
     *
     * @return ASCII representation of the board.
     */
    @Override
    public String toString() {
        String s = "";

        char sectionX = 'A', sectionY = 'A';
        int numberX = 0, numberY = 0;

        //Letter Row
        s += "    ";
        for (int x = this.getBoundsX().getMin() - SPACE_AROUND_BOARD;
             x <= this.getBoundsX().getMax() + SPACE_AROUND_BOARD;
             x++) {
            s += ((numberX % 10 == 0) ? sectionX : " ") + " ";

            if (numberX % 10 == 0) {
                sectionX++;
            }

            numberX++;
        }

        s += Cli.RETURN;

        //Number Row
        numberX = 0;

        s += "    ";
        for (int x = this.getBoundsX().getMin() - SPACE_AROUND_BOARD;
             x <= this.getBoundsX().getMax() + SPACE_AROUND_BOARD;
             x++) {
            s += numberX % 10 + " ";
            numberX++;
        }

        s = s + Cli.RETURN;

        //Board
        for (int y = this.getBoundsY().getMin() - SPACE_AROUND_BOARD;
             y <= this.getBoundsY().getMax() + SPACE_AROUND_BOARD;
             y++) {
            s += ((numberY % 10 == 0) ? sectionY : " ") + " ";
            s += numberY % 10 + " ";

            for (int x = this.getBoundsX().getMin() - SPACE_AROUND_BOARD;
                 x <= this.getBoundsX().getMax() + SPACE_AROUND_BOARD;
                 x++) {
                s += this.getStone(new Position(x, y),
                        possibleMoves.contains(new Position(x, y))).toString() + " ";
            }

            s += "" + numberY % 10;
            s += " " + ((numberY % 10 == 0) ? sectionY : " ");

            if (numberY % 10 == 0) {
                sectionY++;
            }

            numberY++;
            s += Cli.RETURN;
        }

        //Number Row
        numberX = 0;

        s += "    ";
        for (int x = this.getBoundsX().getMin() - SPACE_AROUND_BOARD;
             x <= this.getBoundsX().getMax() + SPACE_AROUND_BOARD;
             x++) {
            s += numberX % 10 + " ";
            numberX++;
        }

        s = s + Cli.RETURN;

        //Letter Row
        numberX = 0;
        sectionX = 'A';

        s += "    ";
        for (int x = this.getBoundsX().getMin() - SPACE_AROUND_BOARD;
             x <= this.getBoundsX().getMax() + SPACE_AROUND_BOARD;
             x++) {
            s += ((numberX % 10 == 0) ? sectionX : " ") + " ";

            if (numberX % 10 == 0) {
                sectionX++;
            }

            numberX++;
        }

        s += Cli.RETURN;

        return s;
    }
}



package com.example.catanhelper;
import java.util.*;
public class Board {
    private ArrayList<ArrayList<Hex>> board;
    private static final int TILE_COUNT = 19;
    private static final int WHEAT_COUNT = 4;
    private static final int SHEEP_COUNT = 4;
    private static final int ORE_COUNT = 3;
    private static final int TREE_COUNT = 4;
    private static final int BRICK_COUNT = 3;

    public static void main(String[] args) {
        Board b = new Board();
        b.printBoard();

    }
    /**
     * Board Constructor
     * Fills out the 2-d board array.
     */
    Board() {
        board = new ArrayList<ArrayList<Hex>>();
        ArrayList<Integer> numbers = new ArrayList<Integer>(
                Arrays.asList(0, 2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12));
        HashMap<Resource, Integer> resources = new HashMap<Resource, Integer>() {{
           put(Resource.WHEAT, WHEAT_COUNT);
           put(Resource.SHEEP, SHEEP_COUNT);
           put(Resource.ORE, ORE_COUNT);
           put(Resource.TREE, TREE_COUNT);
           put(Resource.BRICK, BRICK_COUNT);
        }};
        int tileCount = 3;
        int increment = 1;
        for (int i = 0; i < 5; i++) {
            if (tileCount == 5) {
                increment = -1;
            }
            ArrayList<Hex> row = new ArrayList<Hex>();
            for (int j = 0; j < tileCount; j++) {
                int randNum = numbers.get((int) (Math.random() * numbers.size()));
                numbers.remove(new Integer(randNum));
                if (randNum == 0) {
                    row.add(new Hex(Resource.DESERT, randNum, false));
                } else {
                    Object[] resKeys = resources.keySet().toArray();
                    while (true) {
                        Resource resKey = (Resource) (resKeys[(int) (Math.random() * resKeys.length)]);
                        if (resources.get(resKey) != 0) {
                            resources.put(resKey, resources.get(resKey) - 1);
                            row.add(new Hex(resKey, randNum, false));
                            break;
                        }
                    }
                }
            }
            board.add(row);
            tileCount += increment;
        }
    }
    void printBoard() {
        for (ArrayList<Hex> row : board) {
            for (Hex h : row) {
                System.out.print(h + " ");
            }
            System.out.println();
        }
    }
}

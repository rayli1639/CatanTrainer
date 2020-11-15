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

    /**
     * Board Constructor
     * Fills out the 2-d board array.
     */
    Board() {
        ArrayList<Integer> numbers = new ArrayList<Integer>(
                Arrays.asList(2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12));
        HashMap<String, Integer> resources = new HashMap<String, Integer>() {{
           put("Wheat", WHEAT_COUNT);
           put("Sheep", SHEEP_COUNT);
           put("Ore", ORE_COUNT);
           put("TREE", TREE_COUNT);
           put("BRICK", BRICK_COUNT);
        }};
        for (int i = 0; i < 5; i++) {
            int length = numbers.size();
            int rand = numbers.get((int) (Math.random() * length));
            int num = numbers.get(rand);
            numbers.remove(new Integer(rand));
        }
    }
}

package com.CC.engine.board;

/**
 * Created by all4win78 on 8/31/2017.
 */
public class BoardUtils {


    public static final int NUM_TILES = 90;

    private BoardUtils() {
        throw new RuntimeException("Not allowed to instantiate");
    }


    public static boolean isValidCoordinate(int[] XYCoordinates) {
        return XYCoordinates[0] >= 0 && XYCoordinates[0] < 9 &&
                XYCoordinates[1] >= 0 && XYCoordinates[1] < 10;
    }

    public static int[] toXYCoordinates(int position) {
        return new int[]{position % 9, position / 9};
    }

    public static int toCoordinate(int[] XYCoordinates) {
        return XYCoordinates[0] * 9 + XYCoordinates[1];
    }
}

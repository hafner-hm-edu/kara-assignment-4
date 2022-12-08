package edu.hm.hafner.kara;

import static de.i8k.karalight.Kara.*;

/**
 * KaraLight: solution of assignment 16.
 *
 * @author Ullrich Hafner
 */
public class Assignment16 {
    /**
     * Entrypoint for Kara: this method is called once if you press the 'Ausführen' button in KaraLight.
     *
     * @param unused
     *         this parameter is not used by KaraLight
     */
    public static void main(final String... unused) {
        int shift = -askNumber("Um wie viel horizontal verschieben?");
        int width = computeLength();
        turnRight();
        int height = computeLength();
        turnLeft();

        for (int y = 0; y < height; y++) {
            boolean[] row = copyRow(width);
            shiftRow(row, shift % width);
            if (y < height - 1) {
                moveDown();
            }
        }
        turnLeft();
        moveBy(height - 1);
        turnRight();
    }

    private static void moveDown() {
        turnRight();
        move();
        turnLeft();
    }

    static boolean[] copyRow(final int width) {
        boolean[] column = new boolean[width];
        column[0] = isOnLeaf();
        for (int i = 1; i < width; i++) {
            move();
            column[i] = isOnLeaf();
        }
        turnAround();
        moveBy(width - 1);
        turnAround();

        return column;
    }

    static void shiftRow(final boolean[] row, final int shift) {
        shift(row, 0, shift);
        for (int i = 1; i < row.length; i++) {
            move();
            shift(row, i, shift);
        }

        turnAround();
        moveBy(row.length - 1);
        turnAround();
    }

    static void shift(final boolean[] row, final int i, final int shift) {
        int index = index(shift, i, row.length);
        if (row[index] && !isOnLeaf()) {
            putLeaf();
        }
        else if (!row[index] && isOnLeaf()) {
            pickLeaf();
        }

    }

    private static int index(final int max, final int x, final int width) {
        if (max >= 0) {
            return (x + max) % width;
        }
        else {
            return (x + (width + max)) % width;
        }
    }

    static int computeLength() {
        int length = 0;
        while (!isTreeInFront()) {
            length++;
            move();
        }
        turnAround();
        moveBy(length);
        turnAround();
        return length + 1;
    }

    static void turnAround() {
        turnLeft();
        turnLeft();
    }

    static void moveBy(final int moves) {
        for (int i = 0; i < moves; i++) {
            move();
        }

    }
}

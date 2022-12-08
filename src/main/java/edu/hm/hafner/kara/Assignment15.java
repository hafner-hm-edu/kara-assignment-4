package edu.hm.hafner.kara;

import static de.i8k.karalight.Kara.*;

/**
 * KaraLight: solution of assignment 15.
 *
 * @author Ullrich Hafner
 */
public class Assignment15 {
    private static final int DUPLICATE_COUNT = 2;

    /**
     * Entrypoint for Kara: this method is called once if you press the 'Ausführen' button in KaraLight.
     *
     * @param unused
     *         this parameter is not used by KaraLight
     */
    public static void main(final String... unused) {
        int[] duplicates = findDuplicates();
        if (duplicates.length == DUPLICATE_COUNT) {
            int y = 0;
            for (; y < duplicates[0]; y++) {
                moveDown();
            }
            moveMushroom();
            for (; y < duplicates[1]; y++) {
                moveDown();
            }
            moveMushroom();
            for (; y < 10; y++) {
                moveDown();
            }
        }
    }

    private static void moveMushroom() {
        moveBy(11);
        turnAround();
        moveBy(11);
        turnAround();
    }

    private static void turnAround() {
        turnRight();
        turnRight();
    }

    private static void moveBy(final int distance) {
        for (int y = 0; y < distance; y++) {
            move();
        }
    }

    private static int[] findDuplicates() {
        int[] found = new int[0];
        for (int y = 0; y < 10 - 1; y++) {
            for (int c = 0; c < y; c++) {
                moveDown();
            }
            boolean[] compare = copyColumn(10);

            for (int c = y + 1; c < 10; c++) {
                moveDown();
                boolean[] check = copyColumn(10);
                if (equals(compare, check)) {
                    found = new int[]{y, c};
                }
            }
            moveDown();
        }
        return found;
    }

    static void moveDown() {
        turnRight();
        move();
        turnLeft();
    }

    static boolean equals(final boolean[] a1, final boolean[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

    static boolean[] copyColumn(final int width) {
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

}

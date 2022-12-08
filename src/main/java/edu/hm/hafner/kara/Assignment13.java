package edu.hm.hafner.kara;

import static de.i8k.karalight.Kara.*;

/**
 * KaraLight: solution of assignment 13.
 *
 * @author Ullrich Hafner
 */
public class Assignment13 {
    /**
     * Entrypoint for Kara: this method is called once if you press the 'Ausführen' button in KaraLight.
     *
     * @param unused
     *         this parameter is not used by KaraLight
     */
    public static void main(final String... unused) {
        int height = computeHeight();
        int width = computeWidth();

        for (int x = 0; x < width; x++) {
            boolean[] column = copyColumn(height);

            pasteColumn(column);
            turnLeft();
            move();
        }
        turnRight();
        move();
        turnLeft();
    }

    static void pasteColumn(final boolean[] column) {
        int height = column.length;
        for (boolean b : column) {
            move();
            if (b && !isOnLeaf()) {
                putLeaf();
            }
            else if (!b && isOnLeaf()) {
                pickLeaf();
            }
        }
        turnAround();
        moveBy(height - 1);
    }

    static boolean[] copyColumn(final int height) {
        turnRight();
        boolean[] column = new boolean[height];
        for (int i = 0; i < height; i++) {
            move();
            column[i] = isOnLeaf();
        }
        turnAround();
        moveBy(height - 1);
        return column;
    }

    static int computeHeight() {
        turnLeft();
        int height = 0;
        while (!isTreeInFront()) {
            height++;
            move();
        }
        turnAround();
        moveBy(height - 1);
        turnLeft();
        return height;
    }

    static int computeWidth() {
        int width = 0;
        while (!isOnLeaf()) {
            width++;
            putLeaf();
            move();
        }
        return width;
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

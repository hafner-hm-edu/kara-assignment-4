package edu.hm.hafner.kara;

import static de.i8k.karalight.Kara.*;

/**
 * KaraLight: solution of assignment 14.
 *
 * @author Ullrich Hafner
 */
public class Assignment14 {
    /**
     * Entrypoint for Kara: this method is called once if you press the 'Ausführen' button in KaraLight.
     *
     * @param unused
     *         this parameter is not used by KaraLight
     */
    public static void main(final String... unused) {
        int height = computeHeight();

        for (int position = 2; position * position < height * 10; position++) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < 10; x++) {
                    int number = y * 10 + x + 1;
                    if (number > position && (number % position) == 0) {
                        if (isOnLeaf()) {
                            pickLeaf();
                        }
                    }
                    move();
                }
                moveDown();
            }
        }
    }

    static int computeHeight() {
        int length = 0;

        pickLeaf();
        turnRight();
        do {
            move();
            length++;
        } while (isOnLeaf());

        turnLeft();
        return length;
    }

    static void moveDown() {
        turnRight();
        move();
        turnLeft();
    }
}

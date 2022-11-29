package edu.hm.hafner.kara;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junitpioneer.jupiter.params.IntRangeSource;

import de.i8k.karalight.Kara;
import de.i8k.karalight.test.TestKaraController;
import de.i8k.karalight.world.RepresentationMode;
import de.i8k.karalight.world.World;

import static org.assertj.core.api.Assertions.*;

class Assignment16Test extends AbstractKaraTest {
    @ValueSource(ints = {-16, -8, 0, 8, 16})
    @ParameterizedTest(name = "{index} => Shift um {0}")
    @DisplayName("Shift um Vielfaches der Breite")
    @Timeout(value = 2, threadMode = ThreadMode.SEPARATE_THREAD)
    void shouldDoNothingForOffsetEqualsWidth(final int shift) {
        final String fileName = "16-Punkt";

        var start = readGivenWorld(fileName);
        var controller = new TestKaraController(start);
        controller.prepareInput(String.valueOf(-shift));

        Kara.setController(controller);

        Assignment16.main();

        assertThat(start.getRepresentation(RepresentationMode.NONE))
                .isEqualTo(new World("Assignment" + (fileName + ".world")).getRepresentation(RepresentationMode.NONE));
    }

    @DisplayName("Immer gleich gefülltes Quadrat")
    @Timeout(value = 2, threadMode = ThreadMode.SEPARATE_THREAD)
    @ParameterizedTest
    @IntRangeSource(from = -8, to = 8)
    void shouldDoNothingInFilledSquare(final int shift) {
        final String fileName = "16-Filled";

        var start = readGivenWorld(fileName);
        var controller = new TestKaraController(start);
        controller.prepareInput(String.valueOf(-shift));

        Kara.setController(controller);

        Assignment16.main();

        assertThat(start.getRepresentation(RepresentationMode.NONE))
                .isEqualTo(new World("Assignment" + (fileName + ".world")).getRepresentation(RepresentationMode.NONE));
    }

    @MethodSource
    @ParameterizedTest(name = "{index} => Welt {0} mit einem Shift von {1}")
    @DisplayName("Shift um x")
    @Timeout(value = 2, threadMode = ThreadMode.SEPARATE_THREAD)
    void shouldSolveAssignment(final String fileName, final int shift) {
        var start = readGivenWorld(fileName);
        var controller = new TestKaraController(start);
        controller.prepareInput(String.valueOf(shift));

        Kara.setController(controller);

        Assignment16.main();

        assertThat(start.getRepresentation(RepresentationMode.NONE))
                .isEqualTo(readExpectedWorld(fileName).getRepresentation(RepresentationMode.NONE));
    }

    static Stream<Arguments> shouldSolveAssignment() {
        return Stream.of(
                Arguments.of("16-Punkt", 1),
                        Arguments.of("16-Rectangle-Left", 3),
                        Arguments.of("16-Rectangle-Right", -3)
        );
    }
}


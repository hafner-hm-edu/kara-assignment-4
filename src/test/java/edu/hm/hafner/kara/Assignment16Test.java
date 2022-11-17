package edu.hm.hafner.kara;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import de.i8k.karalight.Kara;
import de.i8k.karalight.test.TestKaraController;
import de.i8k.karalight.world.RepresentationMode;

import static org.assertj.core.api.Assertions.*;

class Assignment16Test extends AbstractKaraTest {
    @MethodSource
    @ParameterizedTest(name = "{index} => Welt {0} hat {1} Hindernisse")
    @DisplayName("Ermittle die meisten Blätter pro Zeile")
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
                Arguments.of("16-Punkt", 1)
        );
    }
}


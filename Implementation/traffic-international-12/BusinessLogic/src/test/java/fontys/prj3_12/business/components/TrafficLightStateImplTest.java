package fontys.prj3_12.business.components;

import fontys.prj3_12.business.factory.TrafficLightFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TrafficLightStateImplTest {
    TrafficLight trafficLight = TrafficLightFactory.createBulgarianTL();
    TrafficLight pedestrianLight = TrafficLightFactory.createPedestrianTrafficLight();

    @Test
    void TrafficLightSetGetTimerTest() {
        trafficLight.getCurrentState().setTimer(25);

        assertThat(trafficLight.getCurrentState().getTimer()).isEqualTo(25);
    }

    @Test
    void PedestrianLightSetGetTimerTest() {
        pedestrianLight.setState(BlinkingState.BLINKING_GO);
        pedestrianLight.getCurrentState().setTimer(30);

        assertThat(pedestrianLight.getCurrentState().getTimer()).isEqualTo(30);
    }

    @Test
    void PedestrianLightNextStateTest() {
        SoftAssertions.assertSoftly(softly -> {
            pedestrianLight.setState(BlinkingState.BLINKING_STOP);
            softly.assertThat(pedestrianLight.getCurrentState().next())
                    .isEqualTo(TrafficLightStateImpl.PREPARE_TO_GO);

            pedestrianLight.setState(BlinkingState.BLINKING_PREPARE_TO_GO);
            softly.assertThat(pedestrianLight.getCurrentState().next())
                    .isEqualTo(TrafficLightStateImpl.GO);

            pedestrianLight.setState(BlinkingState.BLINKING_GO);
            softly.assertThat(pedestrianLight.getCurrentState().next())
                    .isEqualTo(TrafficLightStateImpl.PREPARE_TO_STOP);

            pedestrianLight.setState(BlinkingState.BLINKING_PREPARE_TO_STOP);
            softly.assertThat(pedestrianLight.getCurrentState().next())
                    .isEqualTo(TrafficLightStateImpl.STOP);
        });
    }
}

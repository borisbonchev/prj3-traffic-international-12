package fontys.prj3_12.business.observer;

import fontys.prj3_12.business.components.TrafficLight;
import fontys.prj3_12.business.components.TrafficLightState;
import fontys.prj3_12.business.components.TrafficLightStateImpl;
import fontys.prj3_12.business.factory.TrafficLightFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ObserverTest {
    TrafficLight trafficLight = TrafficLightFactory.createBulgarianTL();
    TrafficLight pedestrianLight = TrafficLightFactory.createPedestrianTrafficLight();

    @Test
    void observerTest() {
        pedestrianLight.setState(TrafficLightStateImpl.PREPARE_TO_STOP);
        trafficLight.getTls().subscribe(pedestrianLight);
        trafficLight.changeState();

        assertThat(pedestrianLight.getCurrentState()).isEqualTo(TrafficLightStateImpl.STOP);
    }

    @Test
    void unsubscribeTest() {
        pedestrianLight.setState(TrafficLightStateImpl.PREPARE_TO_STOP);
        trafficLight.getTls().subscribe(pedestrianLight);
        trafficLight.changeState();

        trafficLight.getTls().unsubscribe(pedestrianLight);
        trafficLight.changeState();

        assertThat(pedestrianLight.getCurrentState()).isEqualTo(TrafficLightStateImpl.STOP);
    }

    @Test
    void updateTest() {
        pedestrianLight.setState(TrafficLightStateImpl.PREPARE_TO_STOP);
        pedestrianLight.update();
        assertThat(pedestrianLight.getCurrentState()).isEqualTo(TrafficLightStateImpl.STOP);
    }
}

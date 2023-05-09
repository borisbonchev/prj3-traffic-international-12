package fontys.prj3_12.business.components;

import fontys.prj3_12.business.factory.TrafficLightFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TrafficLightControllerTest {
    TrafficLight trafficLight = TrafficLightFactory.createBulgarianTL();

    @Test
    void trafficLightControllerConstructorTest() {
        assertThat(new TrafficLightController(trafficLight))
                .isExactlyInstanceOf(TrafficLightController.class);
    }
}

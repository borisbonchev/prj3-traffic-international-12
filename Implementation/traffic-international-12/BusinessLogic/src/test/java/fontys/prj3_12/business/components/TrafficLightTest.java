package fontys.prj3_12.business.components;

import fontys.prj3_12.business.factory.TrafficLightFactory;
import fontys.prj3_12.business.lightProperties.Colour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class TrafficLightTest {
    private TrafficLight trafficLight;
    private String country;

    @BeforeEach
    public void setup() {
        this.country = "BG";
        this.trafficLight = TrafficLightFactory.createTrafficLight(country);
    }

    @Test
    void numberOfAllLights() {
        assertThat(trafficLight.numberOfAllLights()).isEqualTo(3);
    }

    @ParameterizedTest
    @CsvSource({
            "GO,GREEN",
            "PREPARE_TO_GO,YELLOW",
            "STOP,RED",
            "PREPARE_TO_STOP, YELLOW"
    })
    void showActiveLightsBG(TrafficLightStateImpl state, Colour colour) {
        trafficLight.setState(state);
        trafficLight.getCurrentState().turnLightsOn(trafficLight);

        assertThat(trafficLight.showActiveLights()).contains(List.of(colour).toString());
    }

    @Test
    void testUpdate() {
        trafficLight.setState(TrafficLightStateImpl.PREPARE_TO_STOP);
        trafficLight.update();

        assertThat(trafficLight.getCurrentState()).isEqualTo(TrafficLightStateImpl.STOP);
    }
}

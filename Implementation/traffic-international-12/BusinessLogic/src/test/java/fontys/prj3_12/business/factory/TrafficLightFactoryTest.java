package fontys.prj3_12.business.factory;

import fontys.prj3_12.business.components.TrafficLight;
import fontys.prj3_12.business.components.TrafficLightImpl;
import fontys.prj3_12.business.behaviours.*;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class TrafficLightFactoryTest {
    private TrafficLight trafficLight;
    private TrafficLightImpl trafficLightImpl;
    private String country;


    @Test
    void invalidCountryCodeThrowsException() {
        assertThatThrownBy(() -> {
        TrafficLight trafficLight = TrafficLightFactory.createTrafficLight("EU");
        trafficLight.getBehaviour();
        }).isInstanceOf(FactoryClassException.class).hasMessageContaining("Invalid country code!");

    }

    @Test
    void createBulgarianTLTest() {
        TrafficLight bulgarianTL = TrafficLightFactory.createTrafficLight("BG");
        assertThat(bulgarianTL.getBehaviour()).isExactlyInstanceOf(BulgarianTLB.class);
    }
    @Test
    void dutchTLTest() {
        TrafficLight dutchTL = TrafficLightFactory.createTrafficLight("NL");
        assertThat(dutchTL.getBehaviour()).isExactlyInstanceOf(DutchTLB.class);
    }

    @Test
    void germanTLTest() {
        TrafficLight germanTL = TrafficLightFactory.createTrafficLight("DE");
        assertThat(germanTL.getBehaviour()).isExactlyInstanceOf(GermanTLB.class);
    }

    @Test
    void japaneseTLTest() {
        TrafficLight japaneseTL = TrafficLightFactory.createTrafficLight("JP");
        assertThat(japaneseTL.getBehaviour()).isExactlyInstanceOf(JapaneseTLB.class);
    }

    @Test
    void pedestrianTLTest() {
        TrafficLight pedestrianTL = TrafficLightFactory.createTrafficLight("PD");
        assertThat(pedestrianTL.getBehaviour()).isExactlyInstanceOf(PedestrianLightBehaviour.class);
    }
}

package fontys.prj3_12.business.behaviours;

import fontys.prj3_12.business.components.*;
import fontys.prj3_12.business.factory.TrafficLightFactory;
import fontys.prj3_12.business.lightProperties.Colour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class TrafficLightStateBehaviourTest {

    private HashMap<String, Colour> colourConverter;

    // Mapping the string of the colours: RED,YELLOW,GREEN,BLUE to the corresponding properties
    // from our ENUM class - Colour - RED,YELLOW,GREEN,BLUE
    @BeforeEach
    void setup() {
        colourConverter = new HashMap<>();

        colourConverter.put("RED", Colour.RED);
        colourConverter.put("YELLOW", Colour.YELLOW);
        colourConverter.put("GREEN", Colour.GREEN);
        colourConverter.put("BLUE", Colour.BLUE);
    }

    //Test for stateStop for the different traffic lights -
    //DutchTLB, GermanTLB, BulgarianTLB, JapaneseTLB, PedestrianTLB
    @ParameterizedTest
    @CsvSource({
            // country ( string ) , colour ( string )
            "NL,RED", // DutchTBL
            "DE,RED", // GermanTLB
            "BG,RED", // BulgarianTLB
            "JP,RED", // JapaneseTLB
            "PD,RED",  // PedestrianTLB
            "NM,YELLOW" //NightTLB
    })
    void stateStop(String countryCode, String colours) {
        List<Colour> translatedColours = Arrays.stream(colours.split(","))
                .map(String::toUpperCase)
                .map(s -> colourConverter.get(s))
                .collect(Collectors.toList());

        TrafficLight trafficLight = TrafficLightFactory.createTrafficLight(countryCode);

        trafficLight.setState(TrafficLightStateImpl.PREPARE_TO_STOP);
        trafficLight.changeState();

        assertThat(trafficLight.getCurrentState().getColours().containsAll(translatedColours)).isTrue();
    }

    //Test for statePrepareToGo for the different traffic lights -
    //DutchTLB, GermanTLB, BulgarianTLB, JapaneseTLB, PedestrianTLB
    @ParameterizedTest
    @CsvSource({
            // country ( string ) , colour ( string )
            "NL,RED", // DutchTBL
            "DE,YELLOW", // GermanTLB
            "BG,YELLOW", // BulgarianTLB
            "JP,YELLOW", // JapaneseTLB
            "PD,RED",  // PedestrianTLB
            "NM,YELLOW" //NightTLB
    })
    void statePrepareToGo(String countryCode, String colours) {
        List<Colour> translatedColours = Arrays.stream(colours.split(","))
                .map(String::toUpperCase)
                .map(s -> colourConverter.get(s))
                .collect(Collectors.toList());

        TrafficLight trafficLight = TrafficLightFactory.createTrafficLight(countryCode);

        trafficLight.setState(TrafficLightStateImpl.STOP);
        trafficLight.changeState();

        assertThat(trafficLight.getCurrentState().getColours().containsAll(translatedColours)).isTrue();
    }

    //Test for stateGo for the different traffic lights -
    //DutchTLB, GermanTLB, BulgarianTLB, JapaneseTLB, PedestrianTLB
    @ParameterizedTest
    @CsvSource({
            // country ( string ) , colour ( string )
            "NL,GREEN", // DutchTBL
            "DE,GREEN", // GermanTLB
            "BG,GREEN", // BulgarianTLB
            "JP,BLUE", // JapaneseTLB
            "PD,GREEN",  // PedestrianTLB
            "NM,YELLOW" //NightTLB
    })
    void stateGo(String countryCode, String colours) {
        List<Colour> translatedColours = Arrays.stream(colours.split(","))
                .map(String::toUpperCase)
                .map(s -> colourConverter.get(s))
                .collect(Collectors.toList());

        TrafficLight trafficLight = TrafficLightFactory.createTrafficLight(countryCode);

        trafficLight.setState(TrafficLightStateImpl.PREPARE_TO_GO);
        trafficLight.changeState();

        assertThat(trafficLight.getCurrentState().getColours().containsAll(translatedColours)).isTrue();
    }

    //Test for statePrepareToStop for the different traffic lights -
    //DutchTLB, GermanTLB, BulgarianTLB, JapaneseTLB, PedestrianTLB
    @ParameterizedTest
    @CsvSource({
            // country ( string ) , colour ( string )
            "NL,YELLOW", // DutchTBL
            "DE,YELLOW", // GermanTLB
            "BG,YELLOW", // BulgarianTLB
            "JP,YELLOW", // JapaneseTLB
            "PD,GREEN",  // PedestrianTLB
            "NM,YELLOW" //NightTLB
    })
    void statePrepareToStop(String countryCode, String colours) {
        List<Colour> translatedColours = Arrays.stream(colours.split(","))
                .map(String::toUpperCase)
                .map(s -> colourConverter.get(s))
                .collect(Collectors.toList());

        TrafficLight trafficLight = TrafficLightFactory.createTrafficLight(countryCode);

        trafficLight.setState(TrafficLightStateImpl.GO);
        trafficLight.changeState();

        assertThat(trafficLight.getCurrentState().getColours().containsAll(translatedColours)).isTrue();
    }
}
package fontys.prj3_12.business.lightProperties;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class LightStateTest {

    @Test
    void lightStateTest() {
        Light light = new Light(Colour.RED, LightState.OFF);
        LightState testLightState1 = light.getLightState().next();
        LightState testLightState2 = testLightState1.next();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(testLightState1).isSameAs(LightState.ON);
            softly.assertThat(testLightState2).isSameAs(LightState.OFF);
        });
    }
}

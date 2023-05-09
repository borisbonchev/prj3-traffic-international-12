package fontys.prj3_12.business.components;

import fontys.prj3_12.business.lightProperties.Colour;

import java.util.List;

public interface TrafficLightState {

    void setColours(List<Colour> colours);
    void setTimer(long timer);
    List<Colour> getColours();
    long getTimer();

    /**
     * This method helps in changing the state of a traffic light.
     * The cycle for each traffic light is always:
     * STOP -> PREPARE_TO_GO -> GO -> PREPARE_TO_STOP -> STOP
     *
     * @return the next state.
     */
    TrafficLightStateImpl next();

    /**
     * This method is responsible for turning all lights in a given state,
     * for a given traffic light, ON.
     *
     * @param ctx the current traffic light
     */
    void turnLightsOn(Context ctx);
}

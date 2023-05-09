package fontys.prj3_12.business.components;

import fontys.prj3_12.business.lightProperties.Light;

import java.util.List;

public interface TrafficLight extends Context {
    /**
     * @return the number of lights the current traffic light has.
     */
    Integer numberOfAllLights();

    /**
     * @return a list containing all lights that the current TL consists of.
     */
    List<Light> getListOfAllLights();

    /**
     * This method is responsible for returning a list (as a String), containing
     * all lights that are ON in the current state of the TL.
     *
     * @return a list of all lights that are ON in the current state of the TL as a String.
     */
    String showActiveLights();
}

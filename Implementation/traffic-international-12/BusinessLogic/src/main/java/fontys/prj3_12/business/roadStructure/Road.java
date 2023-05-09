package fontys.prj3_12.business.roadStructure;

import fontys.prj3_12.business.components.TrafficLight;
import fontys.prj3_12.business.components.TrafficLightController;

/**
 * A road represents a two-way street with a TrafficLight at the end of it (just before
 * a crossroad) as well as two pedestrian traffic lights on each side of the side road
 * (just next to the TrafficLight).
 */
public interface Road {
    TrafficLight getMainTrafficLight();

    /**
     * Uses the provided road to link its main traffic light to the main traffic light
     * of this road.
     *
     * @param road to be linked.
     */
    void linkRoad(Road road);

    /**
     * This method is used for activating the startCycle of the TL controller for this road.
     *
     * @see TrafficLightController#startCycle()
     */
    void startRoadCycle();

    /**
     * This configuration method is mainly used for roads that are perpendicular compared
     * to the original road configuration (90 deg angle).
     */
    void invertDefaultConfig();

    /**
     * This method is used for linking the main traffic light of another road to
     * the main traffic light of this one.
     *
     * @param trafficLight to be linked
     */
    default void link(TrafficLight trafficLight) {
        getMainTrafficLight().getTls().subscribe(trafficLight);
    }

    void openRoad();
    void closeRoad();
}

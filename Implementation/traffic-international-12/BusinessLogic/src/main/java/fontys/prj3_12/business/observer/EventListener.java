package fontys.prj3_12.business.observer;

/**
 * The EventListener is part of the TrafficLightService subscribers List
 * and requires an update method to handle the next TrafficLightState in order,
 * whenever all the observers are notified of a change of state.
 */
public interface EventListener {
    /**
     * Updates the current state.
     */
    void update();
}
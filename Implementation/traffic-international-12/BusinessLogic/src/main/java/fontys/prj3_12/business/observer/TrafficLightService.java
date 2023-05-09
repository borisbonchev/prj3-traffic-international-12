package fontys.prj3_12.business.observer;

import fontys.prj3_12.business.components.TrafficLight;

import java.util.ArrayList;
import java.util.List;

/**
 * The TrafficLightService is a class that handles the observer subscriptions.
 * Multiple Pedestrian and Traffic lights can be linked interchangeably.
 * <br>
 * Primarily used in complex roads - @Road and @Crossroad.
 */

public class TrafficLightService {
    private final List<TrafficLight> subscribers;

    public TrafficLightService() {
        subscribers = new ArrayList<>();
    }

    /**
     * Adds a subscriber to the subscriber list.
     * @param subscriber Should be a valid TrafficLight.
     */
    public void subscribe(TrafficLight subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * Removes a subscriber from the subscriber list.
     * @param subscriber Should be an already existing TrafficLight inside the subscribers List.
     */
    public void unsubscribe(TrafficLight subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Alerts all subscribers currently active in the List
     * and invokes the update() method on them.
     */
    public void notifyObservers() {
        subscribers.forEach(TrafficLight::update);
    }
}
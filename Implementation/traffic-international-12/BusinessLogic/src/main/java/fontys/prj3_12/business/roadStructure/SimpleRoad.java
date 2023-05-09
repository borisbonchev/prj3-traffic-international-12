package fontys.prj3_12.business.roadStructure;

import fontys.prj3_12.business.components.TrafficLight;
import fontys.prj3_12.business.components.TrafficLightController;
import fontys.prj3_12.business.components.TrafficLightStateImpl;
import fontys.prj3_12.business.factory.TrafficLightFactory;

/**
 * The simple road is the most basic form of a <a href="">road</a>. It only has one pedestrian
 * traffic light, since both sides show the same state of the TL. The main traffic light can
 * be configured to a specific country. The simple road is a multi-directional road, meaning
 * there isn't a specific TL for turning right or turning left. State GO, mean you can go
 * in all directions (road rules for turning left apply).
 *
 * @see Road
 */
public class SimpleRoad implements Road {
    private final TrafficLight mainTrafficLight;

    //? There are two pedestrian traffic lights, but they are the same.
    private final TrafficLight pedestrianTrafficLight;

    private final TrafficLightController controller;

    public SimpleRoad(TrafficLight trafficLight) {
        this.mainTrafficLight = trafficLight;
        this.pedestrianTrafficLight = TrafficLightFactory.createPedestrianTrafficLight();

        this.mainTrafficLight.setState(TrafficLightStateImpl.PREPARE_TO_STOP);
        this.pedestrianTrafficLight.setState(TrafficLightStateImpl.PREPARE_TO_GO);

        this.controller = new TrafficLightController(mainTrafficLight);
        link(this.pedestrianTrafficLight);
    }

    @Override
    public TrafficLight getMainTrafficLight() {
        return this.mainTrafficLight;
    }

    public TrafficLight getPedestrianTrafficLights() {
        return this.pedestrianTrafficLight;
    }

    @Override
    public void linkRoad(Road road) {
        link(road.getMainTrafficLight());
    }

    @Override
    public void startRoadCycle() {
        this.controller.startCycle();
    }

    public void stopCycle() {
        this.controller.getTimer().cancel();
    }

    @Override
    public void invertDefaultConfig() {
        getMainTrafficLight().setState(TrafficLightStateImpl.PREPARE_TO_GO);
        getPedestrianTrafficLights().setState(TrafficLightStateImpl.PREPARE_TO_STOP);
    }

    @Override
    public void openRoad() {
        stopCycle();
        this.mainTrafficLight.setState(TrafficLightStateImpl.GO);
        this.pedestrianTrafficLight.setState(TrafficLightStateImpl.STOP);
    }

    @Override
    public void closeRoad() {
        stopCycle();
        this.mainTrafficLight.setState(TrafficLightStateImpl.STOP);
        this.pedestrianTrafficLight.setState(TrafficLightStateImpl.GO);
    }
}
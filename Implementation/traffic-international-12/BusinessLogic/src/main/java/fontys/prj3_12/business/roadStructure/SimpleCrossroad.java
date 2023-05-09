package fontys.prj3_12.business.roadStructure;

import fontys.prj3_12.business.factory.TrafficLightFactory;

/**
 * The SimpleCrossroad implementation of the crossroad represents a four-way crossroad.
 * This means that there are two Roads in the Y axis and two in the X axis. The two pairs
 * consist of the same traffic lights with the same configuration. For simplicity, this class
 * only has two roads, which both represent a pair of equal TLs.
 */
public class SimpleCrossroad implements Crossroad {
    private final SimpleRoad road;
    private final SimpleRoad invertedRoad;

    /**
     * SimpleCrossroad requires the user to specify what behaviour is desired. Once specified,
     * the behaviour cannot be changed.
     *
     * @param countryCode for specifying which behaviour of the traffic lights is desired.
     */
    public SimpleCrossroad(String countryCode) {
        this.road = new SimpleRoad(TrafficLightFactory.createTrafficLight(countryCode));
        this.invertedRoad = new SimpleRoad(TrafficLightFactory.createTrafficLight(countryCode));

        this.invertedRoad.invertDefaultConfig();
        this.road.linkRoad(invertedRoad);
    }

    @Override
    public void startCrossroadCycle() {
        this.road.startRoadCycle();
    }

    public SimpleRoad getRoad() {
        return road;
    }

    public SimpleRoad getInvertedRoad() {
        return invertedRoad;
    }

    public void emergencyModeMainRoad() {
        this.road.openRoad();
        this.invertedRoad.closeRoad();
    }
}

package fontys.prj3_12.business.roadStructure;

/**
 * A crossroad is an intersection of two or more roads.
 */
public interface Crossroad {

    /**
     * This method is responsible for starting the automatic traffic light changing of states. Before stating,
     * make sure that all configurations of all roads in the Crossroad are correctly set up.
     */
    void startCrossroadCycle();
}

package fontys.prj3_12.business.components;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The TLC is a helper class that allows the periodic and automatic changing of states for the provided
 * traffic light. When multiple traffic lights are linked*, one controller is enough for controlling
 * even multiple complex road structures.
 * <br>
 * <br>
 * Mainly used in road structures like Road and Crossroad.
 */
public class TrafficLightController {

    private final TrafficLight trafficLight;
    private final Timer timer;

    public TrafficLightController(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
        this.timer = new Timer();
    }

    /**
     * This method is responsible for starting the working cycle of the system. It only
     * changes its state when the timer of the MAIN traffic light runs out (only one timer
     * is being taken into account). After scheduling a change of state, it also displays
     * the changes in the console.
     * <br>
     * In case the system is turned OFF, and exception is thrown.
     */
    public void startCycle() {
        TimerTask change = new TimerTask() {
            @Override
            public void run() {
                System.out.println(trafficLight.getCurrentState().next());
                trafficLight.changeState();
                startCycle();
            }
        };

            this.timer.schedule(change, trafficLight.getCurrentState().getTimer());
    }

    public Timer getTimer() {
        return timer;
    }
}

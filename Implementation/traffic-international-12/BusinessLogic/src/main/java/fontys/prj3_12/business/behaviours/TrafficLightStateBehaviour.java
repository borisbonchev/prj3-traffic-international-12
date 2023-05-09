package fontys.prj3_12.business.behaviours;

import fontys.prj3_12.business.components.Context;

public interface TrafficLightStateBehaviour {
    void stateStop(Context ctx);
    void statePrepareToGo(Context ctx);
    void stateGo(Context ctx);
    void statePrepareToStop(Context ctx);
}
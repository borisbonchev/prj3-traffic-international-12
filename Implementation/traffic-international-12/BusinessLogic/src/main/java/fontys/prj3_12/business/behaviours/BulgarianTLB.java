package fontys.prj3_12.business.behaviours;

import fontys.prj3_12.business.components.Context;
import fontys.prj3_12.business.lightProperties.Colour;

import java.util.List;

public class BulgarianTLB implements TrafficLightStateBehaviour{

    //Traffic Light - State Stop - Red Light - needs to be 6s
    @Override
    public void stateStop(Context ctx) {
        ctx.getCurrentState().setColours(List.of(Colour.RED));
        ctx.getCurrentState().setTimer(2000);
    }

    //Traffic Light - State Prepare To Go - Yellow Light - needs to be 3s
    @Override
    public void statePrepareToGo(Context ctx) {
        ctx.getCurrentState().setColours(List.of(Colour.YELLOW));
        ctx.getCurrentState().setTimer(1000);
    }

    //Traffic Light - State Go - Green Light - needs to be 6s
    @Override
    public void stateGo(Context ctx) {
        ctx.getCurrentState().setColours(List.of(Colour.GREEN));
        ctx.getCurrentState().setTimer(2000);
    }

    //Traffic Light - State Prepare To Stop - Yellow Light - needs to be 3s
    @Override
    public void statePrepareToStop(Context ctx) {
        ctx.getCurrentState().setColours(List.of(Colour.YELLOW));
        ctx.getCurrentState().setTimer(1000);
    }
}

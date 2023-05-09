package fontys.prj3_12.business.behaviours;

import fontys.prj3_12.business.components.BlinkingState;
import fontys.prj3_12.business.components.Context;
import fontys.prj3_12.business.lightProperties.Colour;

import java.util.List;

public class NightTLB implements TrafficLightStateBehaviour {

    //Traffic Light - State Stop - Yellow Blinking Light - needs to be 3s
    @Override
    public void stateStop(Context ctx) {
        ctx.setState(BlinkingState.BLINKING_STOP);
        ctx.getCurrentState().setColours(List.of(Colour.YELLOW));
        ctx.getCurrentState().setTimer(1000);
    }

    //Traffic Light - State Prepare To Go - Yellow Blinking Light - needs to be 3s
    @Override
    public void statePrepareToGo(Context ctx) {
        ctx.setState(BlinkingState.BLINKING_STOP);
        ctx.getCurrentState().setColours(List.of(Colour.YELLOW));
        ctx.getCurrentState().setTimer(1000);
    }

    //Traffic Light - State Go - Yellow Blinking Light - needs to be 3s
    @Override
    public void stateGo(Context ctx) {
        ctx.setState(BlinkingState.BLINKING_STOP);
        ctx.getCurrentState().setColours(List.of(Colour.YELLOW));
        ctx.getCurrentState().setTimer(1000);
    }

    //Traffic Light - State Prepare To Stop - Yellow Blinking Light - needs to be 3s
    @Override
    public void statePrepareToStop(Context ctx) {
        ctx.setState(BlinkingState.BLINKING_STOP);
        ctx.getCurrentState().setColours(List.of(Colour.YELLOW));
        ctx.getCurrentState().setTimer(1000);
    }
}

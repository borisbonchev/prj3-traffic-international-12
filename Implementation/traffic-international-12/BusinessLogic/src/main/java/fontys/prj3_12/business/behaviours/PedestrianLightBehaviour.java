package fontys.prj3_12.business.behaviours;

import fontys.prj3_12.business.components.BlinkingState;
import fontys.prj3_12.business.components.Context;
import fontys.prj3_12.business.lightProperties.Colour;

import java.util.List;

public class PedestrianLightBehaviour implements TrafficLightStateBehaviour{
    public void stateStop(Context ctx) {
        ctx.getCurrentState().setColours(List.of(Colour.RED));
    }

    @Override
    public void statePrepareToGo(Context ctx) {
        ctx.getCurrentState().setColours(List.of(Colour.RED));
    }

    public void stateGo(Context ctx) {
        ctx.getCurrentState().setColours(List.of(Colour.GREEN));
    }

    /**
     * Invokes a BLINKING_PREPARE_TO_STOP state, that is unique to the PedestrianLight.
     */
    public void statePrepareToStop(Context ctx) {
        ctx.setState(BlinkingState.BLINKING_PREPARE_TO_STOP);
        ctx.getCurrentState().setColours(List.of(Colour.GREEN));
    }
}

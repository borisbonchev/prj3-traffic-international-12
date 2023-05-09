package fontys.prj3_12.business.components;

import fontys.prj3_12.business.behaviours.TrafficLightStateBehaviour;
import fontys.prj3_12.business.lightProperties.Colour;
import fontys.prj3_12.business.lightProperties.Light;
import fontys.prj3_12.business.lightProperties.LightState;

import java.util.*;
import java.util.stream.Collectors;

public class TrafficLightImpl implements TrafficLight {
    private final List<Light> listOfAllLights;
    private TrafficLightState state;
    private final TrafficLightStateBehaviour behaviour;

    //! Lights MUST be added from TOP to BOTTOM
    public TrafficLightImpl(TrafficLightStateBehaviour behaviour, Light... lights) {
        this.listOfAllLights = Arrays.stream(lights).collect(Collectors.toList());
        this.behaviour = behaviour;
        this.state = TrafficLightStateImpl.STOP;
    }

    @Override
    public void lightsInUse(List<Colour> coloursToBeOn) {
        getListOfAllLights().forEach(light -> light.setLightState(LightState.OFF));
        getListOfAllLights().stream()
                .filter(light -> coloursToBeOn.contains(light.getColour()))
                .forEach(light -> light.setLightState(LightState.ON));
        showActiveLights();
    }

    @Override
    public Integer numberOfAllLights() {
        return getListOfAllLights().size();
    }

    @Override
    public List<Light> getListOfAllLights() {
        return this.listOfAllLights;
    }

    @Override
    public String showActiveLights() {
        ArrayList<Colour> activeLights = new ArrayList<>();

        for (Light light : getListOfAllLights()) {
            if (light.getLightState().equals(LightState.ON)) activeLights.add(light.getColour());
        }

        System.out.println(activeLights + this.toString());
        return activeLights.toString();
    }

    @Override
    public TrafficLightState getCurrentState() {
        return this.state;
    }

    @Override
    public TrafficLightStateBehaviour getBehaviour() {
        return this.behaviour;
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    @Override
    public void update() {
        this.setState(this.getCurrentState().next());
        this.getCurrentState().turnLightsOn(this);
    }

    @Override
    public String toString() {
        String behaviourStr = "behaviour=" + behaviour.getClass().getSimpleName();
        String stateStr = "";

        if (this.state instanceof BlinkingState) stateStr = "BLINKING";

        return behaviourStr + " " + stateStr;
    }
}

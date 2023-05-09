package fontys.prj3_12.business.components;

import fontys.prj3_12.business.behaviours.TrafficLightStateBehaviour;
import fontys.prj3_12.business.lightProperties.Colour;
import fontys.prj3_12.business.observer.EventListener;
import fontys.prj3_12.business.observer.TrafficLightService;

import java.util.List;

public interface Context extends EventListener {
    TrafficLightService TLS = new TrafficLightService();

    TrafficLightState getCurrentState();
    TrafficLightStateBehaviour getBehaviour();
    void setState(TrafficLightState state);

    /**
     * This method is responsible for turning all lights from the current state (coloursToBeOn) ON,
     * and all lights that are part of the TL and not in the current state OFF.
     *
     * @param coloursToBeOn A list of all colours that need to be turned ON for the current state.
     */
    void lightsInUse(List<Colour> coloursToBeOn);

    /**
     * This method is responsible for changing the current state to the next one. It is also responsible
     * for turning the lights ON and OFF by calling the lightsInUse method in each state.
     */
    default void changeState() {
        setState(getCurrentState().next());
        TLS.notifyObservers();
        getCurrentState().turnLightsOn(this);
    }

    default TrafficLightService getTls() {
        return TLS;
    }
}
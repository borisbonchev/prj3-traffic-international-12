package fontys.prj3_12.business.components;

import fontys.prj3_12.business.lightProperties.Colour;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public enum BlinkingState implements TrafficLightState {
    BLINKING_STOP() {
        @Override
        public TrafficLightStateImpl next() {
            return TrafficLightStateImpl.PREPARE_TO_GO;
        }

        @Override
        public void turnLightsOn(Context ctx) {
            activateBlinkingMode((TrafficLight) ctx, this, getTimer());
        }
    },
    BLINKING_PREPARE_TO_GO() {
        @Override
        public TrafficLightStateImpl next() {
            return TrafficLightStateImpl.GO;
        }

        @Override
        public void turnLightsOn(Context ctx) {
            activateBlinkingMode((TrafficLight) ctx, this, getTimer());
        }
    },
    BLINKING_GO() {
        @Override
        public TrafficLightStateImpl next() {
            return TrafficLightStateImpl.PREPARE_TO_STOP;
        }

        @Override
        public void turnLightsOn(Context ctx) {
            activateBlinkingMode((TrafficLight) ctx, this, getTimer());
        }
    },
    BLINKING_PREPARE_TO_STOP() {
        @Override
        public TrafficLightStateImpl next() {
            return TrafficLightStateImpl.STOP;
        }

        @Override
        public void turnLightsOn(Context ctx) {
            activateBlinkingMode((TrafficLight) ctx, this, getTimer());
        }
    };

    private List<Colour> colours;
    private long timer;

    BlinkingState() {
        this.colours = List.of();
        this.timer = 1000;
    }

    @Override
    public void setColours(List<Colour> colours) {
        this.colours = colours;
    }

    @Override
    public void setTimer(long timer) {
        this.timer = timer;
    }

    @Override
    public List<Colour> getColours() {
        return this.colours;
    }

    @Override
    public long getTimer() {
        return this.timer;
    }

    /**
     * This method is responsible for turning the LightState of the Lights with active Colours ON/OFF (blinking).
     * <br><br>
     * <b>PS: </b>It has two parameters to prevent any accidents on the road! They are both needed. Do not remove
     * any of them!
     *
     * @param trafficLight             is the context, whose lights will be affected.
     * @param startingState            is the state that this method was called on. It is used for reference, since the timer
     *                                 can otherwise be activated during the wrong state.
     * @param remainingTimeForBlinking is also used for checking that this state does not overlap or execute any
     *                                 code after the state is changed.
     */

    //! DO NOT TOUCH THIS!!! VERY BREAKABLE!
    void activateBlinkingMode(TrafficLight trafficLight, TrafficLightState startingState, long remainingTimeForBlinking) {
        long blinkingSpeed = 245;
        TimerTask blink = new TimerTask() {
            @Override
            public void run() {
                if (trafficLight.getCurrentState().equals(startingState)) {
                    trafficLight.getListOfAllLights().stream().filter(light -> getColours().contains(light.getColour()))
                            .forEach(light -> light.setLightState(light.getLightState().next()));

//                    trafficLight.showActiveLights();
                    System.out.println(trafficLight.getCurrentState().getColours() + " -> " + trafficLight.getListOfAllLights().get(1).getLightState());
                    activateBlinkingMode(trafficLight, startingState, remainingTimeForBlinking - (blinkingSpeed + 6));
                }
            }
        };

        Timer clock = new Timer();

        if (trafficLight.getCurrentState().equals(startingState)) {
            if (remainingTimeForBlinking - blinkingSpeed >= 0) {
                //? The delay represents how many times the light is going to blink. It's equal to the (timerTime / delay) rounded down.
                clock.schedule(blink, blinkingSpeed);
            }
        } else {
            clock.cancel();
            trafficLight.getCurrentState().turnLightsOn(trafficLight);
        }
    }
}

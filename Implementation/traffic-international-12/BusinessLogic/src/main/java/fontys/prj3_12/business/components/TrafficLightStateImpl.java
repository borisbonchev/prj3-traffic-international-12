package fontys.prj3_12.business.components;

import fontys.prj3_12.business.lightProperties.Colour;

import java.util.List;

public enum TrafficLightStateImpl implements TrafficLightState {
    STOP() {
        @Override
        public TrafficLightStateImpl next() {
            return TrafficLightStateImpl.PREPARE_TO_GO;
        }

        @Override
        public void turnLightsOn(Context ctx) {
            ctx.getBehaviour().stateStop(ctx);
            checkBlinking(ctx);
        }
    },
    PREPARE_TO_GO() {
        @Override
        public TrafficLightStateImpl next() {
            return TrafficLightStateImpl.GO;
        }

        @Override
        public void turnLightsOn(Context ctx) {
            ctx.getBehaviour().statePrepareToGo(ctx);
            checkBlinking(ctx);
        }
    },
    GO() {
        @Override
        public TrafficLightStateImpl next() {
            return TrafficLightStateImpl.PREPARE_TO_STOP;
        }

        @Override
        public void turnLightsOn(Context ctx) {
            ctx.getBehaviour().stateGo(ctx);
            checkBlinking(ctx);
        }
    },
    PREPARE_TO_STOP() {
        @Override
        public TrafficLightStateImpl next() {
            return TrafficLightStateImpl.STOP;
        }

        @Override
        public void turnLightsOn(Context ctx) {
            ctx.getBehaviour().statePrepareToStop(ctx);
            checkBlinking(ctx);
        }
    };

    private List<Colour> colours;
    private long timer;

    TrafficLightStateImpl() {
        this.colours = List.of();
        this.timer = 0;
    }

    @Override
    public void setColours(List<Colour> colours) {
        this.colours = colours;
    }

    @Override
    public List<Colour> getColours() {
        return this.colours;
    }

    @Override
    public void setTimer(long timer) {
        this.timer = timer;
    }

    @Override
    public long getTimer() {
        return this.timer;
    }

    void checkBlinking(Context ctx) {
        if (ctx.getCurrentState() instanceof BlinkingState) {
            ctx.getCurrentState().turnLightsOn(ctx);
            return;
        }

        ctx.lightsInUse(this.getColours());
    }
}

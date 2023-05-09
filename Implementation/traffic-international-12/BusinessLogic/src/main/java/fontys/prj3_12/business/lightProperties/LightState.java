package fontys.prj3_12.business.lightProperties;

public enum LightState {
    ON {
        @Override
        public LightState next() {
            return LightState.OFF;
        }
    },
    OFF {
        @Override
        public LightState next() {
            return LightState.ON;
        }
    };

    public abstract LightState next();
}

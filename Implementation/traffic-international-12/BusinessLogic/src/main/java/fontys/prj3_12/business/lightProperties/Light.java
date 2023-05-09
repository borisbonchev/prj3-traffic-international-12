package fontys.prj3_12.business.lightProperties;

public class Light {
    private final Colour colour;
    private LightState lightState;

    public Light(Colour colour, LightState lightState) {
        this.colour = colour;
        this.lightState = lightState;
    }

    public Light(Colour colour) {
        this(colour, LightState.OFF);
    }

    public Colour getColour() {
        return this.colour;
    }

    public LightState getLightState() {
        return this.lightState;
    }

    public void setLightState(LightState lightState) {
        this.lightState = lightState;
    }
}
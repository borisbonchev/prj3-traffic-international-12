package fontys.prj3_12.business.factory;

import fontys.prj3_12.business.behaviours.*;
import fontys.prj3_12.business.components.TrafficLightImpl;
import fontys.prj3_12.business.lightProperties.Colour;
import fontys.prj3_12.business.lightProperties.Light;

public abstract class TrafficLightFactory {

    public static TrafficLightImpl createTrafficLight(String country) {

        switch (country.toUpperCase()) {
            case "BG":
                return createBulgarianTL();
            case "NL":
                return createDutchTL();
            case "DE":
                return createGermanTL();
            case "JP":
                return createJapaneseTL();
            case "PD":
                return createPedestrianTrafficLight();
            case "NM":
                return createNightModeTL();
        }
        throw new FactoryClassException("Invalid country code!");
    }

    public static TrafficLightImpl createPedestrianTrafficLight() {
        return new TrafficLightImpl(new PedestrianLightBehaviour(), new Light(Colour.RED), new Light(Colour.GREEN));
    }

    public static TrafficLightImpl createBulgarianTL() {
        return new TrafficLightImpl(new BulgarianTLB(), new Light(Colour.RED), new Light(Colour.YELLOW), new Light(Colour.GREEN));
    }

    public static TrafficLightImpl createDutchTL() {

        return new TrafficLightImpl(new DutchTLB(), new Light(Colour.RED), new Light(Colour.YELLOW), new Light(Colour.GREEN));
    }

    public static TrafficLightImpl createGermanTL() {

        return new TrafficLightImpl(new GermanTLB(), new Light(Colour.RED), new Light(Colour.YELLOW), new Light(Colour.GREEN));
    }

    public static TrafficLightImpl createJapaneseTL() {
        return new TrafficLightImpl(new JapaneseTLB(), new Light(Colour.RED), new Light(Colour.YELLOW), new Light(Colour.BLUE));
    }

    public static TrafficLightImpl createNightModeTL() {
        return new TrafficLightImpl(new NightTLB(), new Light(Colour.RED), new Light(Colour.YELLOW), new Light(Colour.GREEN));
    }
}

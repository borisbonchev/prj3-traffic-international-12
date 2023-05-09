package fontys.prj3_12.gui;

import fontys.prj3_12.business.components.BlinkingState;
import fontys.prj3_12.business.components.TrafficLightState;
import fontys.prj3_12.business.lightProperties.Colour;
import fontys.prj3_12.business.roadStructure.SimpleCrossroad;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

public class CreateCrossroadController {
    private static final double ON = 1d;
    private static final double OFF = 0.25;

    private final Timer timer;
    private final Supplier<SceneManager> sceneManagerSupplier;
    private final SimpleCrossroad crossroad;

    private final List<List<Node>> trafficLightList, inversedTrafficLightList;
    private List<List<Node>> pedestrianLightList, invertedPedestrianLightList;

    @FXML
    private Group TL1, TL2, TL3, TL4,
            PL1, PL2, PL3, PL4, PL5, PL6, PL7, PL8;

    public CreateCrossroadController(Supplier<SceneManager> sceneManagerSupplier) {
        this.timer = new Timer();
        this.sceneManagerSupplier = sceneManagerSupplier;
        this.crossroad = new SimpleCrossroad(sceneManagerSupplier.get().getCountryCode());

        this.trafficLightList = new ArrayList<>();
        this.inversedTrafficLightList = new ArrayList<>();
        this.pedestrianLightList = new ArrayList<>();
        this.invertedPedestrianLightList = new ArrayList<>();
    }

    @FXML
    public void initialize() {
        createTrafficLights();
        checkJapanLights();

        ObjectProperty<TrafficLightState> mainRoadMainTLProperty = new SimpleObjectProperty<>();
        mainRoadMainTLProperty.set(crossroad.getRoad().getMainTrafficLight().getCurrentState());

        ObjectProperty<TrafficLightState> invertedRoadMainTLProperty = new SimpleObjectProperty<>();
        invertedRoadMainTLProperty.set(crossroad.getInvertedRoad().getMainTrafficLight().getCurrentState());

        ObjectProperty<TrafficLightState> mainRoadPedestrianTLProperty = new SimpleObjectProperty<>();
        mainRoadPedestrianTLProperty.set(crossroad.getRoad().getPedestrianTrafficLights().getCurrentState());

        ObjectProperty<TrafficLightState> invertedRoadPedestrianTLProperty = new SimpleObjectProperty<>();
        invertedRoadPedestrianTLProperty.set(crossroad.getInvertedRoad().getPedestrianTrafficLights().getCurrentState());

        mainRoadMainTLProperty.addListener(new Listener(trafficLightList));
        invertedRoadMainTLProperty.addListener(new Listener(inversedTrafficLightList));
        mainRoadPedestrianTLProperty.addListener(new Listener(pedestrianLightList));
        invertedRoadPedestrianTLProperty.addListener(new Listener(invertedPedestrianLightList));

        crossroad.startCrossroadCycle();

        startCycle(mainRoadMainTLProperty);
        startInvertedCycle(invertedRoadMainTLProperty);
        startPedestrianCycle(mainRoadPedestrianTLProperty);
        startInvertedPedestrianCycle(invertedRoadPedestrianTLProperty);
    }

    public void onBackButtonPress() throws IOException {
        this.crossroad.getRoad().stopCycle();
        this.crossroad.getInvertedRoad().stopCycle();

        sceneManagerSupplier.get().setCountryCode(null);
        sceneManagerSupplier.get().changeScene(SceneManager.MAIN_MENU);
    }

    public void onEmergencyModeButtonPress() {
        this.crossroad.emergencyModeMainRoad();
    }

    private void startCycle(ObjectProperty<TrafficLightState> objectProperty) {
        TimerTask change = new TimerTask() {
            @Override
            public void run() {
                if (!crossroad.getRoad().getMainTrafficLight().getCurrentState().equals(objectProperty.get()))
                    objectProperty.set(crossroad.getRoad().getMainTrafficLight().getCurrentState());

                startCycle(objectProperty);
            }
        };

        timer.schedule(change, 1);
    }

    private void startInvertedCycle(ObjectProperty<TrafficLightState> objectProperty) {
        TimerTask change = new TimerTask() {
            @Override
            public void run() {
                if (!crossroad.getInvertedRoad().getMainTrafficLight().getCurrentState().equals(objectProperty.get()))
                    objectProperty.set(crossroad.getInvertedRoad().getMainTrafficLight().getCurrentState());

                startInvertedCycle(objectProperty);
            }
        };

        timer.schedule(change, 1);
    }

    private void startPedestrianCycle(ObjectProperty<TrafficLightState> objectProperty) {
        TimerTask change = new TimerTask() {
            @Override
            public void run() {
                if (!crossroad.getRoad().getPedestrianTrafficLights().getCurrentState().equals(objectProperty.get()))
                    objectProperty.set(crossroad.getRoad().getPedestrianTrafficLights().getCurrentState());

                startPedestrianCycle(objectProperty);
            }
        };

        timer.schedule(change, 1);
    }

    private void startInvertedPedestrianCycle(ObjectProperty<TrafficLightState> objectProperty) {
        TimerTask change = new TimerTask() {
            @Override
            public void run() {
                if (!crossroad.getInvertedRoad().getPedestrianTrafficLights().getCurrentState().equals(objectProperty.get()))
                    objectProperty.set(crossroad.getInvertedRoad().getPedestrianTrafficLights().getCurrentState());

                startInvertedPedestrianCycle(objectProperty);
            }
        };

        timer.schedule(change, 1);
    }

    private void createTrafficLights() {
        trafficLightList.add(TL1.getChildren().stream().filter(node -> node instanceof Circle).toList());
        trafficLightList.add(TL3.getChildren().stream().filter(node -> node instanceof Circle).toList());

        inversedTrafficLightList.add(TL2.getChildren().stream().filter(node -> node instanceof Circle).toList());
        inversedTrafficLightList.add(TL4.getChildren().stream().filter(node -> node instanceof Circle).toList());

        pedestrianLightList.add(PL1.getChildren().stream().filter(node -> node instanceof Circle).toList());
        pedestrianLightList.add(PL2.getChildren().stream().filter(node -> node instanceof Circle).toList());
        pedestrianLightList.add(PL5.getChildren().stream().filter(node -> node instanceof Circle).toList());
        pedestrianLightList.add(PL6.getChildren().stream().filter(node -> node instanceof Circle).toList());

        invertedPedestrianLightList.add(PL3.getChildren().stream().filter(node -> node instanceof Circle).toList());
        invertedPedestrianLightList.add(PL4.getChildren().stream().filter(node -> node instanceof Circle).toList());
        invertedPedestrianLightList.add(PL7.getChildren().stream().filter(node -> node instanceof Circle).toList());
        invertedPedestrianLightList.add(PL8.getChildren().stream().filter(node -> node instanceof Circle).toList());

        trafficLightList.forEach(nodes -> nodes.forEach(node -> node.setOpacity(OFF)));
        inversedTrafficLightList.forEach(nodes -> nodes.forEach(node -> node.setOpacity(OFF)));
        pedestrianLightList.forEach(nodes -> nodes.forEach(node -> node.setOpacity(OFF)));
        invertedPedestrianLightList.forEach(nodes -> nodes.forEach(node -> node.setOpacity(OFF)));

        checkNightMode();
    }

    private void checkJapanLights() {
        if (!this.sceneManagerSupplier.get().getCountryCode().equals("JP")) return;

        trafficLightList.forEach(circleList -> ((Circle) circleList.get(2)).setFill(Paint.valueOf("blue")));
        inversedTrafficLightList.forEach(circleList -> ((Circle) circleList.get(2)).setFill(Paint.valueOf("blue")));
    }

    private void checkNightMode() {
        if (!this.sceneManagerSupplier.get().getCountryCode().equals("NM")) return;

        pedestrianLightList = new ArrayList<>();
        invertedPedestrianLightList = new ArrayList<>();
    }

    private class Listener implements ChangeListener<TrafficLightState> {
        private final List<List<Node>> list;
        private final HashMap<Colour, Integer> map;


        public Listener(List<List<Node>> list) {
            this.list = list;
            this.map = new HashMap<>();

            this.map.put(Colour.RED, 0);

            if (this.list.equals(pedestrianLightList) ||
                    this.list.equals(invertedPedestrianLightList)) {
                this.map.put(Colour.GREEN, 1);
            } else {
                this.map.put(Colour.YELLOW, 1);
                this.map.put(Colour.GREEN, 2);
                this.map.put(Colour.BLUE, 2);
            }
        }

        @Override
        public void changed(ObservableValue<? extends TrafficLightState> observable, TrafficLightState oldValue, TrafficLightState newValue) {
            checkBlinking(observable, newValue, false);
        }

        private void checkBlinking(ObservableValue<? extends TrafficLightState> currentState, TrafficLightState originalState, boolean state) {
            if (originalState instanceof BlinkingState && currentState.getValue().equals(originalState)) {

                TimerTask change = new TimerTask() {
                    @Override
                    public void run() {
                        list.forEach(circleList -> {
                            List<Node> circlesToBeOn = new ArrayList<>();

                            originalState.getColours().forEach(colour -> circlesToBeOn.add(circleList.get(map.get(colour))));

                           circlesToBeOn.forEach(node -> node.setOpacity(Objects.equals(state, true) ? ON : OFF));
                        });

                        checkBlinking(currentState, originalState, !Objects.equals(state, true));
                    }
                };

                timer.schedule(change, 200);
            } else {
                turnLightsOn(currentState.getValue());
            }
        }

        private void turnLightsOn(TrafficLightState state) {
            list.forEach(circleList -> {
                List<Node> circlesToBeOn = new ArrayList<>();

                state.getColours().forEach(colour -> circlesToBeOn.add(circleList.get(map.get(colour))));

                circleList.forEach(node -> node.setOpacity(OFF));
                circlesToBeOn.forEach(node -> node.setOpacity(ON));
            });
        }
    }
}
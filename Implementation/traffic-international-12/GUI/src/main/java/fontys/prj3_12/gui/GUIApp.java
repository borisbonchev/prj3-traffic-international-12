package fontys.prj3_12.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class GUIApp extends Application {

    private SceneManager sceneManager;

    private final Callback<Class<?>, Object> controllerFactory = (Class<?> c) -> switch (c.getName()) {
        case "fontys.prj3_12.gui.MainMenuController" -> new MainMenuController(this::getSceneManager);
        case "fontys.prj3_12.gui.CreateCrossroadController" -> new CreateCrossroadController(this::getSceneManager);
        default -> null;
    };

    private static final String INITIAL_VIEW = "mainMenu";

    public GUIApp() {
    }

    public void show() throws IOException {
        Platform.startup(() -> {
        });

        initializeSceneManager();

        Platform.runLater(() -> {
            Stage stage = new Stage();
            try {
                start(stage);
            } catch (Exception e) {
                throw new RuntimeException( e + " Something happened here...");
            }
            stage.show();
        });
    }

    private void initializeSceneManager() throws IOException {
        sceneManager = new SceneManager(controllerFactory, INITIAL_VIEW);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Crossroad");
        sceneManager.displayOn(stage, 1200, 960);
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}
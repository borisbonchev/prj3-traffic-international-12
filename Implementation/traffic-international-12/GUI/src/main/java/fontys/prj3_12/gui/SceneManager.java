package fontys.prj3_12.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SceneManager {

    public static final String MAIN_MENU = "mainMenu";
    private final Scene scene;
    private final Callback<Class<?>, Object> controllerFactory;

    private String countryCode;

    public SceneManager(Callback<Class<?>, Object> controllerFactory, String initialView) throws IOException {
        this.controllerFactory = controllerFactory;
        scene = new Scene(loadScene(initialView, null));
    }

    public Scene getScene() {
        return scene;
    }

    public final void changeScene(String view) throws IOException {
        scene.setRoot(loadScene(view, null));
    }

    public final <T> void changeScene(String view, Consumer<T> consumer) throws IOException {
        scene.setRoot(loadScene(view, consumer));
    }

    private <T> Parent loadScene(String fxml, Consumer<T> consumer) throws IOException {
        var fxmlSource = getResource(fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlSource);
        fxmlLoader.setControllerFactory(controllerFactory);
        return fxmlLoader.load();
    }

    public <T> Parent createModule(String name, Supplier<T> function) throws IOException {
        var resource = getResource(name);
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.setControllerFactory(cls -> function.get());
        return fxmlLoader.load();
    }

    private URL getResource(String fxml) {
        return GUIApp.class.getResource("/" + fxml + ".fxml");
    }

    void displayOn(Stage stage, int width, int height) {
        scene.getStylesheets()
                .add(Objects.requireNonNull(GUIApp.class.getResource("/styles/defaultStyle.css")).toExternalForm());
        stage.setScene(scene);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.show();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}

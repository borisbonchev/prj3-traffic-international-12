package fontys.prj3_12.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.Supplier;

public class MainMenuController {
    private final Supplier<SceneManager> sceneManagerSupplier;

    @FXML
    private ComboBox<String> dropdown;

    @FXML
    private Label errorMsg;

    public MainMenuController(Supplier<SceneManager> sceneManagerSupplier) {
        this.sceneManagerSupplier = sceneManagerSupplier;
    }

    @FXML
    public void initialize() {
        errorMsg.setVisible(false);
        dropdown.getItems().addAll(supportedCountries().keySet().stream().sorted().toList());
    }

    private HashMap<String, String> supportedCountries() {
        HashMap<String, String> list = new HashMap<>();

        list.put("Bulgaria", "BG");
        list.put("Netherlands", "NL");
        list.put("Germany", "DE");
        list.put("Japan", "JP");
        list.put("Night mode", "NM");

        return list;
    }

    @FXML
    public void onStartButtonPress() throws IOException {
        if (dropdown.getValue() == null) {
            errorMsg.setVisible(true);
            return;
        }

        sceneManagerSupplier.get().setCountryCode(supportedCountries().get(dropdown.getValue()));
        sceneManagerSupplier.get().changeScene("crossroad");
    }
}

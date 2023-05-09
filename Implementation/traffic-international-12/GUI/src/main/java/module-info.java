module gui_moddule {
    requires businesslogic_module;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.base;

    exports fontys.prj3_12.gui;

    opens fontys.prj3_12.gui to javafx.fxml;
}
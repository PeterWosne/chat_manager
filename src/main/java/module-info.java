module sample.chat_manager {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample.chat_manager to javafx.fxml;
    exports sample.chat_manager;
}
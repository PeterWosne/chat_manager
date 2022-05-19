package sample.chat_manager;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.List;


public class HelloController {
    @FXML
    private MenuItem websiteMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Button btnSend;

    @FXML
    private TextArea chatArea;

    @FXML
    private MenuItem closeBtn;

    @FXML
    private ListView<String> contacts;

    @FXML
    private TextField inputField;

    private ObservableList<String> datalist = FXCollections.observableArrayList();
    private String defaultText = "Broadcast to all: ";

    @FXML
    private void initialize() {
        if(contacts.getSelectionModel().getSelectedIndex() == -1) {
            inputField.setText(defaultText);
        }
        closeBtn.setOnAction(e -> Platform.exit());

        List<String> names = List.of("Nastya", "Tatiana", "Peter", "Anna", "Frederika", "Paul");
        datalist.addAll(names);
        contacts.setItems(datalist);

        contacts.setOnMouseClicked(e -> {
            inputField.setText("to " + datalist.get(contacts.getSelectionModel().getSelectedIndex()) + ": ");
            defaultText = "to " + datalist.get(contacts.getSelectionModel().getSelectedIndex()) + ": ";
        });

        btnSend.setOnAction(e -> {
            String text = inputField.getText();
            if(text.equals(defaultText)) {return;}

            chatArea.appendText(text + System.lineSeparator());
            if(contacts.getSelectionModel().getSelectedIndex() == -1) {
                inputField.setText("Broadcast to all: ");
            }else {
                inputField.setText("to " + datalist.get(contacts.getSelectionModel().getSelectedIndex()) + ": ");
            }
        });

        aboutMenuItem.setOnAction(e -> {
            Label label = new Label("Chat manager\nVersion 0.1\ncreated May 2022");
            VBox secondLayout = new VBox();
            secondLayout.setAlignment(Pos.CENTER);
            secondLayout.getChildren().add(label);
            Scene secondScene = new Scene(secondLayout, 330, 150);
            Stage newWindow = new Stage();
            newWindow.setTitle("About programm");
            newWindow.setResizable(false);
            newWindow.setScene(secondScene);
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.show();
        });
    }
}

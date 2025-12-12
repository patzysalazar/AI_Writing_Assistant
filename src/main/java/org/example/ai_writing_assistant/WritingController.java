package org.example.ai_writing_assistant;

import API.AIWritingAPIClient;
import Model.WritingRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;


public class WritingController {

    @FXML
    private TextArea UserInput;

    @FXML
    private RadioButton CreativeMode;

    @FXML
    private RadioButton ProfessionalMode;

    @FXML
    private RadioButton AcademicMode;

    @FXML
    private TextArea Output;

    @FXML
    private Button GenerateButton;

    @FXML
    private ToggleGroup Modes = new ToggleGroup();

    private AIWritingAPIClient Client = new AIWritingAPIClient();

    @FXML
    public void ToggleGroupInitialization(){

        CreativeMode.setToggleGroup(Modes);
        ProfessionalMode.setToggleGroup(Modes);
        AcademicMode.setToggleGroup(Modes);

    }

    @FXML
    protected void onButtonClick() throws IOException, ParseException {

        if(UserInput.getText().isBlank()){
            Output.setText("Enter a prompt please");
            return;
        }

        if(Modes.getSelectedToggle() == null ){
            Output.setText("Select a mode before continuing");
            return;
        }
        //selection will be our mode
        //UserInput.getText will be our input
        //tokes stays the same
        WritingRequest request = new WritingRequest(UserInput.getText(),((RadioButton) Modes.getSelectedToggle()).getText(), 500);
        Output.setText(Client.Authentication(request));

        //GenerateButton.setDisable(false);




    }
}

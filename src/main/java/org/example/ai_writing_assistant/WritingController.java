package org.example.ai_writing_assistant;

import API.AIWritingAPIClient;
import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;

//essentially calls the model and the writing modes and deals with the output of clicking the buttons and outputting a response based on the input
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
//if something is missing
        if(UserInput.getText().isBlank()){
            Output.setText("Enter a prompt please");
            return;
        }
        if(Modes.getSelectedToggle() == null ){
            Output.setText("Select a mode before continuing");
            return;
        }
        //mode selection
        WritingMode ModeType = null;
        if(Modes.getSelectedToggle() != null && UserInput.getText() != null && CreativeMode.isSelected()) {
            ModeType = new CreativeMode();
        }
        else if(Modes.getSelectedToggle() != null && UserInput.getText() != null && ProfessionalMode.isSelected()) {
            ModeType = new ProfessionalMode();
        }
        else if(Modes.getSelectedToggle() != null && UserInput.getText() != null && AcademicMode.isSelected()) {
            ModeType = new AcademicMode();
        }
        //call to AI
        if (ModeType != null){
            Output.setText(ModeType.sendToAI(UserInput.getText()));

        }

    }
}

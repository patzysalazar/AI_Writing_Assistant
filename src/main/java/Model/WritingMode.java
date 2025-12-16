package Model;
import API.AIWritingAPIClient;
import Model.WritingRequest;
//Inheritance - The Writing mode is the abstract parent of the 3 subclasses creativeMode, professionalMode and academicMode of writing(each one demonstrates an is-a relationship). Each subclass inherits the sendToAI() method.
//Polymorphism - each subclass overrides the styleOfWriting method depending on the writing style called based on what the user selected and send the AI the result of the modified prompt to generate a correct output

import java.io.IOException;
import java.text.ParseException;

public abstract class WritingMode {
    public abstract String styleOfWriting(String input);

    //method that has the input of a string
    public String sendToAI(String input) throws IOException, ParseException {//this.getClass().getSimpleName() will be great for debugging issues
        //creation of a writing request object that calls the subclass implementation to modify the users text based on selection, and the mode name is stored to track and later debug
        WritingRequest WR = new WritingRequest(styleOfWriting(input),this.getClass().getSimpleName(), 75);//data object
        AIWritingAPIClient Client = new AIWritingAPIClient();//Creates API client to send request to the AI based on JSOn and the code done in AIWritingAPIClient and receive response
        return Client.Authentication(WR); //sends the AI text to contoller to then display to the app that the user will see
    }
}

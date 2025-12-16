package Model;

public class AcademicMode extends WritingMode{//modes controls what text is sent  - whych the AI then reacts to the text
    //modes used to decide what text the AI recieves

    @Override
    public String styleOfWriting(String input) {
        return "Based on the following writing, turn it into more of an academic text: " + input;
    }
}

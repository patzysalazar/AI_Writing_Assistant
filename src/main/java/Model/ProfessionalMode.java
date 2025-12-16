package Model;

public class ProfessionalMode extends WritingMode{
    @Override
    public String styleOfWriting(String input) {
        return "Based on the following writing, turn it into more of a professional text: " + input;
    }
}

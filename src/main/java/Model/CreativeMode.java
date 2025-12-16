package Model;

public class CreativeMode extends WritingMode{

    @Override
    public String styleOfWriting(String input) {
        return "Based on the following writing, turn it into more of a creative and imaginative text: " + input;
    }
}

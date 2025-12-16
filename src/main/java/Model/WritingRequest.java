package Model;

//prompt
//selection of mode
//needed to store all info needed to make the request to the AI API
public class WritingRequest {
//fields
    protected  String prompt;
    protected  String mode;
    protected  int tokens = 75;
//constructor - initializes objects
    public WritingRequest(String prompt, String mode, int tokens){
       this.prompt = prompt;
       this.mode = mode;
       this.tokens = tokens;
    }

    public  String getPrompt(){
        return prompt;
    }

    public int getTokens(){
        return tokens;
    }
    public String getMode(){
        return mode;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }


    //
 }

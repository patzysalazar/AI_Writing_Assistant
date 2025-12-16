package API;

import Config.Config;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.io.IOException;
import Model.WritingRequest;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//communication of client and server using JSON
public class AIWritingAPIClient {


    //the authentication takes the writing request and returns a string
    public String Authentication(WritingRequest request) throws IOException, ParseException{
     String APIKey = Config.apiKey;//takes api key from my configuration and makes the api accessible within this class
    try {
        URL url = new URL(Config.url);//creates url object
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();//http connection to URL, converts the API url into a url object
        connection.setRequestProperty("Authorization", "Bearer " + APIKey);//authorization header to request, tells server who you are and with this you are able to use such service///////////
        connection.setRequestProperty("Content-Type", "application/json");//tells server the body of the request is JSON// format used to send a request to AI API and receive response back
        connection.setDoOutput(true);//sends data to server
        connection.setRequestMethod("POST");//set method to post//using post - sending data to the AI API



        //Request Body - communcation with AI server
        //JSON Visualization
        //{
        //"max_tokens": in this case 75 due to AI and api key maulfunctions and 400 error request
        //"messages":[{"role:" "system", "content": "writing assistant"}],{{"role:" "user", "content": "write about this topic in a creative, professional or academic way "}],
        //"model":"gpt - the latest model"

        JsonObject jsonBody = new JsonObject();//creats a json body object
        jsonBody.addProperty("max_tokens", request.getTokens());
        JsonArray Messages = new JsonArray();//{"messages": [.........]} - has to be an array of role and content - the role tells the AI who is speaking in the conversation, who is listening and who will be responding.

        JsonObject Chat = new JsonObject();//creation of the writing assistant object
        Chat.addProperty("role", "system");//role is in charge of who is speaking
        Chat.addProperty("content", "writing assistant");//text provided of what each role says - without roles the ai wouldnt know what the instruction/input is from the content/output

        JsonObject roleUser = new JsonObject();//creation of the user object
        roleUser.addProperty("role", "user");
        roleUser.addProperty("content", request.getPrompt());

        Messages.add(Chat);//add both roles and content to the messages array that obtains the messages in the conversation
        Messages.add(roleUser);

        //adds tokens to json"max_tokens":......
        jsonBody.add("messages", Messages);//adds messages array to the json body
        jsonBody.addProperty("model", Config.model);//adds the type of model used to teh json body
        //jsonBody.addProperty("mode", request.getMode()); //- api will not know what modes are - the CReative, professional and Academic modes do not go in the JSON as a mode
        String requestTheBody = jsonBody.toString();//converts json object to json string

        WriteBody(requestTheBody, connection);


        StringBuilder Response = new StringBuilder();//stringbuilder to store API response
        return StringBuilderHelper(Response, connection);//reads response and returns string using the helper that we created below

    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
    }
//helper method that writes json string into HTTP request so that the server receives the information
    public void WriteBody(String Body, HttpURLConnection Connection) throws IOException {
        try (BufferedWriter buffWriter = new BufferedWriter( new OutputStreamWriter(Connection.getOutputStream(), StandardCharsets.UTF_8))){
            //Gets the output stream of the HTTP connection., Converts your text into bytes using UTF-8 encoding. creates a buffer reader to send data more efficiently

            buffWriter.write(Body);//writes the data into a buffer
            buffWriter.flush();//sends the data to server
            buffWriter.close();
        }
    }
            public String StringBuilderHelper(StringBuilder response, HttpURLConnection conn) throws IOException {// takes a string builder object and takes the HTTP connection and returns the server response as a string
                try(Scanner scan = new Scanner(conn.getResponseCode()>=400? conn.getErrorStream():conn.getInputStream())) {
                    String line = "";
                    while (scan.hasNextLine() && line != null) {//loop- while the server has another line of text it will read the next line, add the line to the string builder and turns the stringbuilder to a normal string and returns it to the authentication above in the code
                line = scan.nextLine();
                response.append(line);
                response.append("\n");
            }//before this section of code, the output would be the token usage and some other information that was not needed as a response or should not be shown to the viewer, so I had to implement the code below to only display the correct output.
            //Gson usage where get(0) accesses the first element of the choices array, then the .getAsJsonObjvcet converts the element into an object to access the fields, from there the .getAsJsonObject("message ") accesses the message object that is inside the choice.Then the .get("content") accesses the content field inside the message and getAsString() converst the message into the java string so it could be read as the response of the AI.
                    //essentially we are obtaining the output response in the JSON nested structure by calling only the content and only that. So we go from going inside choices, to then messages and only printing out content.
                    return JsonParser.parseString(response.toString()).getAsJsonObject().getAsJsonArray("choices").get(0).getAsJsonObject().getAsJsonObject("message").get("content").getAsString();
                    //return response.toString().contains("\"content\":\"")? response.toString().split("\"content\":\"")[1].split("\"")[0].replace("\\n", "\n") : "[No AI response]";
                    // JsonParser.parseString(response.toString()).getAsJsonObject().getAsJsonArray("choices").get(0).getAsJsonObject().getAsJsonObject("messages").get("content").getAsString();
                    //added section to output the text of content only
        }
    }


}

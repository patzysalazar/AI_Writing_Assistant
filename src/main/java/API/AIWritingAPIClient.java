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
import com.google.gson.JsonObject;

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
        JsonObject jsonBody = new JsonObject();//creats a json body object
        jsonBody.addProperty("prompt", request.getPrompt());//adds prompt to JSon
        jsonBody.addProperty("max_tokens",request.getTokens());//adds tokens to json
        jsonBody.addProperty("mode", request.getMode());
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
                try(Scanner scan = new Scanner(conn.getInputStream())) {
                    String line = "";
                    while (scan.hasNextLine() && line != null) {//loop- while the server has another line of text it will read the next line, add the line to the string builder and turns the stringbuilder to a normal string and returns it to the authentication above in the code
                line = scan.nextLine();
                response.append(line);
                response.append("\n");
            }
            return response.toString();//converts the response to string
        }
    }


}

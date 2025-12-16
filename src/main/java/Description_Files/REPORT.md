# Project Report

## Challenges I Faced
**Challenge 1: JSON and HTTP URL Connections**
Problem: Learning what HTTP URL connections and JSON are and implementing them within my code
Solution: Watched a couple of videos and used AI to help me understand the concepts
Learned: I learned about the request body and its implementations when accessing my API key.
**Challenge 1 extended: Specifications on JSON part 2**
Problem: The JSON connection was incorrectly coded compared to the programming 3 assignment due to still learning the roles and understanding its structure. I know the very basics as I have just been taught recently in my Web design course, but this was still a challenge for me as videos were still a bit confusing to understand when it came to writing it in Java.
Solution: Essentially I added the role of the AI to display the communication between the user and the AI with its respective contents/text.
Learned:I learned about how the JSON connection sets roles and how the structure matters(which is displayed as pseudocode above within the code). In specification, I learned about managing roles and how a message should be read based on the role of user or AI to understand who is speaking in order for the other role to respond and display result in the textfield.

**Challenge 2: Writing view to controller connections of buttons**
Problem: I had a hard time with adding exceptions and creating connections to the different writing modes from my writing view to the controller.
Solution: I Had to implement the specific ID within my view and call the buttons within my controller to be able to adjust the behaviors of selections within the app.
Learned:I learned about the different text area and radio button features and how within my code they are used to create exceptions and handle error when a client is using my app.
**Challenge 2 extended:**
Additions: I added code since the Programming 3 Assignment since I implemented new code in my model section that deals with each mode(which displays OOP requirements). I added if-else statements to call each mode based on the button clicked. The addition was not an issue of some sort at all, but just thought to add this commentary as I updated that section by the end.

## Design Pattern Justifications
**Strategy Pattern:** - Different writing mode(Creative, professional, and academic) based on the radio buttons
**Factory Pattern:**  - creating writing request object where it sets the prompt, mode and limits tokens
**Observer Pattern:**  - WritingController is the observer since it must run when receiving a client response

## OOP Four Pillars (where & why in your codebase)
Within my model package you can see how I implemented the different types of Writing Modes that use Inheritance and polymorphism. I chose to have Writing mode as the parent class and have the academic, creative and professional modes be the child classes since each one is-a writing mode. All of the subclass modes inherit the sendToAI() method. Polymorphism is seen when each subclass overrides the styleOfWriting method depending on the writing style called based on what the user selected and send the AI the result of the modified prompt to generate a correct output. I also implemented abstraction when dealing with each mode, within the controller each mode is set as a writing mode rather than calling each specifc mode as the controller does not need to know that information, just that a writing mode is used. Although I didn't set my API key as a private variable, I stored it in the Config class that is not directly accessed by the controller, so in a way that demonstrates encapsulation.

## AI Usage (BE HONEST!)
I Used ChatGPT to understand JSON and the HTTPURLConnection concepts as I had no idea how to implement them in my code or how it worked. I explained what it does within the video and made sure really analyze the steps. Within my last weeks of school I was able to learn about HTTP requests through my Web Design course so now I seem to be a bit more familiar with the concepts. But JSON was a completely new concept that I had to learn by watching several youtube videos and through ChatGPT. Most of the time I would try to implement my own knowledge but get help with syntax if it wasn't correct with JSON and HTTPConnections.What also helped with that was the music recommender code as well
## Time Spent: ~6 days cumulative in hours roughly
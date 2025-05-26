🗨️ Java Online Chat Application
This is a console-based multi-user chat application built using Java Socket programming. It allows multiple users to connect to a central server, send messages, and receive messages from others in real time. The app demonstrates the use of threads, networking, I/O streams, and concurrent programming in Java.
_________________________________________________________________________________________________________________
📌 Features
•	🌐 Multi-user support using Socket and ServerSocket
•	👤 Each client is automatically assigned a unique user ID (e.g., User1, User2, etc.)
•	💬 Real-time message broadcasting to all connected clients
•	🧵 Multi-threaded server to handle each client independently
•	🖥️ Text-based user interface for input/output
•	📜 Clean disconnection and server-side logging
•	✅ Modular code design for maintainability
_________________________________________________________________________________________________________________
🛠️ Technologies Used
•	Java 17+ (compatible with Java 8+)
•	Socket Programming
•	Multithreading
•	Java I/O (DataInputStream, DataOutputStream)
•	IntelliJ IDEA (or any Java IDE)
_________________________________________________________________________________________________________________

🗃️ Project Structure
online-chatApp/
│
├── ChatServer.java       # Main server program
├── ChatClient.java       # Client program to connect to the server
└── ClientHandler.java    # Runnable class to handle client connections on the server
_________________________________________________________________________________________________________________
▶️ How to Run
🖥️ Step 1: Compile all classes
bash
javac ChatServer.java ChatClient.java ClientHandler.java
🌐 Step 2: Start the Chat Server
bash
java ChatServer
👥 Step 3: Connect Clients (in new terminals or IntelliJ tabs)
bash
java ChatClient
💡 Repeat step 3 in multiple terminal windows to simulate multiple users.
________________________________________________________________________________________________________________
✅ Sample Interaction
User1: Hello everyone!
User2: Hi User1!
User3: Great to be here.
User1 has left the chat.
_________________________________________________________________________________________________________________
🧱 Code Design (Modular Overview)
•	ChatServer.java
o	Accepts new client connections
o	Assigns unique IDs
o	Broadcasts messages using broadcast() method
•	ClientHandler.java
o	Each client runs on its own thread
o	Handles reading and writing from/to its socket
•	ChatClient.java
o	Connects to server
o	Reads input from user
o	Displays messages from other users in real time
___________________________________________________________________________________________________________
📌 Future Improvements (Optional Ideas)
•	GUI version using JavaFX or Swing
•	User authentication with usernames/passwords
•	Private messaging (DMs)
•	File transfer support
•	Chat room creation and management
_____________________________________________________________________________________________________________
📄 License
This project is open-source and available under the MIT License.
✍️ Author
Name: Joshua Monday
GitHub: https://github.com/Ojobumiche/online-chatApplication.git
Email: Bumiche@gmail.com
LinkedIn: https://www.linkedin.com/in/monday-joshua-42875a21a/

____________________________________________________________________________________________________________
🤝 Contributing
Feel free to fork this repo and contribute! Issues and pull requests are welcome.
_______________________________________________________________________________
🙋 Question for Readers
How could this chat application be scaled for deployment in a production-grade environment using non-blocking I/O (NIO) or frameworks like Netty?


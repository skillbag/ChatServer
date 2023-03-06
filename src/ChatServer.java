import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    
    ArrayList<Client> Clients = new ArrayList<>();

    ServerSocket serverSocket;

    ChatServer() throws IOException {
        // создаем серверный сокет на порту 1244
        serverSocket = new ServerSocket(1244);
    }
void sendAll(String message){
        for(Client client : Clients){
            client.receive(message);
        }
}
    public void run() {
        while (true) {
            System.out.println("Waiting...");
            try {
                // ждем клиента
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                Clients.add(new Client(socket, this));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();

    }
}

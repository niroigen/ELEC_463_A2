import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    void runServer(ServerUI serverUI) {
        try {
            ServerSocket welcomeSocket = new ServerSocket(6789);

            while (true) {
                Socket connectionSocket = welcomeSocket.accept();

                BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                String clientSentence = inputFromClient.readLine();

                serverUI.updateUI(clientSentence);

                DataOutputStream outputToClient = new DataOutputStream(connectionSocket.getOutputStream());

                outputToClient.writeBytes("From Server: Connected...\n");

                do {
                    clientSentence = inputFromClient.readLine();
                } while(!clientSentence.contains("Disconnect"));

                connectionSocket.close();

                serverUI.updateUI("Disconnect from client");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        ServerUI f = new ServerUI();
        f.setVisible(true);
        f.runServer();
    }
}

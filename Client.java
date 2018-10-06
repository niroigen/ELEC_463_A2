import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private Socket clientSocket;

    void connect(ClientUI clientUI) {
        try {
            clientSocket = new Socket("localhost", 6789);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            String sendingSentence = "Connection Request: Socket[addr=" + clientSocket.getLocalAddress() + ",port=" + clientSocket.getPort() + ",localport=" + clientSocket.getLocalPort() + "]\n";

            outToServer.writeBytes(sendingSentence);

            BufferedReader inFromServer = new BufferedReader (new
                    InputStreamReader(clientSocket.getInputStream()));

            String receivedSentence = inFromServer.readLine();

            clientUI.updateLog(receivedSentence, true);
        } catch (Exception ex) {
            clientUI.updateLog("Cannot connect to server", false);
        }
    }

    void disconnect(ClientUI clientUI) {
        try {
            if (clientSocket != null) {
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

                String sendingSentence = "Disconnect\n";

                outToServer.writeBytes(sendingSentence);

                clientSocket.close();
                clientUI.updateStatus(false);
            }
        } catch (Exception ex) {
            clientUI.updateLog("Cannot disconnect to server", false);
        }
    }

    public static void main(String[] args) {
        ClientUI clientUI = new ClientUI();
        clientUI.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ServerUI extends JFrame {
    private JLabel connectionStatus = new JLabel("Connection Status: Not Connected");
    private JTextArea connectionLog = new JTextArea();
    private Server server = new Server();
    private boolean isConnected = false;

    ServerUI(){
        setTitle("Server TCP");
        setSize(600,600);
        setLocation(new Point(300,200));
        setLayout(null);
        setResizable(false);

        initComponent();
        initEvent();
    }

    void runServer() {
        server.runServer(this);
    }

    void updateUI(String input) {
        updateLog(input);
        updateStatus();
    }

    private void updateLog(String input) {
        isConnected = !input.contains("Disconnect");

        if (isConnected) {
            connectionLog.append(input + "\n");
        }
    }

    private void updateStatus() {
        if (isConnected) {
            connectionStatus.setText("Connection Status: Connected");
            connectionStatus.setForeground(Color.BLUE);
        } else {
            connectionStatus.setText("Connection Status: Not Connected");
            connectionStatus.setForeground(Color.RED);
        }
    }

    private void initComponent(){
        connectionStatus.setBounds(100,50,300,20);
        connectionLog.setBounds(20,100,550,450);

        connectionStatus.setForeground(Color.RED);
        connectionLog.setEditable(false);

        add(connectionStatus);
        add(connectionLog);
    }

    private void initEvent(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ClientUI extends JFrame {
    private JLabel connectionStatus = new JLabel("Connection Status: Not Connected");
    private JTextArea connectionLog = new JTextArea();
    private JButton connectDisconnectButton = new JButton("Connect");
    private boolean isConnected = false;
    private Client client = new Client();

    ClientUI(){
        setTitle("Client TCP");
        setSize(600,600);
        setLocation(new Point(300,200));
        setLayout(null);
        setResizable(false);

        initComponent();
        initEvent();
    }

    void updateLog(String input, boolean isConnected) {
        this.isConnected = isConnected;
        connectionLog.append(input + "\n");
    }

    public void updateStatus(boolean isConnected) {
        this.isConnected = isConnected;
        updateStatus();
    }

    private void updateStatus() {
        if (isConnected) {
            connectionStatus.setText("Connection Status: Connected");
            connectDisconnectButton.setText("Disconnect");
            connectionStatus.setForeground(Color.BLUE);
        } else {
            connectionStatus.setText("Connection Status: Not Connected");
            connectDisconnectButton.setText("Connect");
            connectionStatus.setForeground(Color.RED);
        }
    }

    private void initComponent(){
        connectionStatus.setBounds(100,20,300,20);
        connectDisconnectButton.setBounds(130, 50, 100,30);
        connectionLog.setBounds(20,100,550,450);

        connectionStatus.setForeground(Color.RED);
        connectionLog.setEditable(false);

        add(connectionStatus);
        add(connectDisconnectButton);
        add(connectionLog);
    }

    private void initEvent(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });

        connectDisconnectButton.addActionListener(e -> {
            if (isConnected) client.disconnect(this);
            else client.connect(this);
            updateStatus();
        });
    }
}

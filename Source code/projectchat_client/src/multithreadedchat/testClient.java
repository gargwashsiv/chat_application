package multithreadedchat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class testClient extends javax.swing.JFrame {

    private static int port;
    private static String address;
    static String location = "/home/dell/NetBeansProjects/projectchat_client/saved";
    private Socket socket;
    private boolean connected = false;
    private ObjectOutputStream outstream;
    private ObjectInputStream instream;
    DefaultListModel listModel = new DefaultListModel();
    File file;
    FileWriter fw = null;

    public testClient() {
        initComponents();

        Password.setEchoChar('*');

        fchooser = new JFileChooser();
        fchooser.setFileSelectionMode(fchooser.FILES_ONLY);
        fchooser.setVisible(false);
        fchooser.setCurrentDirectory(new File(location));

        userslist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        userslist.addListSelectionListener(new SharedListSelectionHandler());


        disconnect.setEnabled(false);
        sendButton.setEnabled(false);
        saveButton.setEnabled(false);
        openButton.setEnabled(false);

        signup.setEnabled(false);
        submitButton.setEnabled(false);
        go.setEnabled(false);


        initName.setEditable(false);
        intiPassword.setEditable(false);
        initId.setEditable(false);
        email.setEditable(false);


        editText.setEditable(false);
        userName.setEditable(false);
        Password.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        editText = new javax.swing.JTextField();
        chatPane = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        userName = new javax.swing.JTextField();
        user = new javax.swing.JLabel();
        secret = new javax.swing.JLabel();
        sendButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        signup = new javax.swing.JButton();
        go = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        userslist = new javax.swing.JList(listModel);
        Password = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        initName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();
        disconnect = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        initId = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        intiPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        editText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTextActionPerformed(evt);
            }
        });

        chatArea.setColumns(20);
        chatArea.setRows(5);
        chatPane.setViewportView(chatArea);

        user.setText("login id");

        secret.setText("password :");

        sendButton.setText("send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        saveButton.setText("save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        openButton.setText("open");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        signup.setText("sign up");
        signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupActionPerformed(evt);
            }
        });

        go.setText("go");
        go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(userslist);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(user)
                                    .addComponent(secret))
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Password)
                                    .addComponent(userName, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))
                            .addComponent(chatPane, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(go, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(signup, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(editText, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(46, Short.MAX_VALUE))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(user)
                    .addComponent(go)
                    .addComponent(signup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(secret)
                                .addGap(3, 3, 3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(chatPane, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sendButton)
                        .addComponent(saveButton)
                        .addComponent(openButton)))
                .addGap(68, 68, 68))
        );

        jLabel1.setText("enter name");

        jLabel2.setText("enter password");

        jLabel3.setText("enter email");

        submitButton.setText("submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        disconnect.setText("disconnect");
        disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectActionPerformed(evt);
            }
        });

        jLabel4.setText("enter id");

        connectButton.setText("connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(disconnect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(submitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(initName, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(intiPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(initId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(disconnect)
                            .addComponent(connectButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(initName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(initId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(intiPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(submitButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void showMembers(String[] members) {
        for (int i = 0; i < members.length; i++) {
            addOnList(members[i]);
        }
    }

    public void addOnList(String name) {
        if (!name.equals("")) {
            System.out.println("addOnList is Called");
            int size = listModel.getSize();
            listModel.addElement(name);
            userslist.setSelectedIndex(size);
            userslist.ensureIndexIsVisible(size);
        }

    }

    public void removeFromList(String name) {
        listModel.removeElement(name);
    }

    public void removeAll() {
        listModel.removeAllElements();
    }

    class SharedListSelectionHandler implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {
                if (userslist.getSelectedIndex() == -1) {
                } else {
                }
            }

        }
    }

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        try {
            socket = new Socket(address, port);
            outstream = new ObjectOutputStream(socket.getOutputStream());
            instream = new ObjectInputStream(socket.getInputStream());
            System.out.println("successfully connected");


            connectButton.setEnabled(false);
            disconnect.setEnabled(true);
            signup.setEnabled(true);
            go.setEnabled(true);
            userName.setEditable(true);
            Password.setEditable(true);
            connected = true;


        } catch (IOException ex) {
            System.out.println("can not connect to the server");
        }
    }//GEN-LAST:event_connectButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            outstream.writeObject(file);
            outstream.flush();
        } catch (IOException ex) {
            Logger.getLogger(testClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        String msg = editText.getText();
        editText.setText("");
        chatArea.append("me>>" + " " + msg + "\n");
        try {
            fw = new FileWriter(file, true);
            fw.write("me>>" + " " + msg + "\n");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(testClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        // if get information to send all
        String clients = "";

        String text = "$M$" + " ";

        Object names[] = userslist.getSelectedValues();
        for (int i = 0; i < names.length - 1; i++) {
            clients = clients + (String) names[i] + " ";
        }
        clients = clients + (String) names[names.length - 1];

        text = text + clients + "-" + " " + msg;
        try {
            outstream.writeObject(text);
        } catch (IOException ex) {
            Logger.getLogger(testClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_sendButtonActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        fchooser.setVisible(true);
        int returnVal = fchooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fchooser.getSelectedFile();
                Runtime runtime = Runtime.getRuntime();
                Process p = runtime.exec("gedit "+ file.getAbsolutePath());

            } catch (IOException ex) {
                Logger.getLogger(testClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_openButtonActionPerformed

    private void editTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editTextActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed



        String name = initName.getText();
        String password = intiPassword.getText();
        String mail = email.getText();
        String loginid = initId.getText();

        initName.setText("");
        intiPassword.setText("");
        email.setText("");
        initId.setText("");

        // insert code for verification of mail

        String text = "$S$" + " " + name + " " + loginid + " " + password + " " + mail;
        try {
            outstream.writeObject(text);
            outstream.flush();
        } catch (IOException ex) {
            System.out.println("there is some problem in sending signup info");
        }
        try {
            String fullresponse = instream.readObject().toString();
            String response = fullresponse.substring(4);



        } catch (IOException ex) {
            Logger.getLogger(testClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(testClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        initName.setEditable(false);
        intiPassword.setEditable(false);
        email.setEditable(false);
        initId.setEditable(false);
        submitButton.setEnabled(false);
    }//GEN-LAST:event_submitButtonActionPerformed

    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed
        submitButton.setEnabled(true);
        initName.setEditable(true);
        intiPassword.setEditable(true);
        initId.setEditable(true);
        email.setEditable(true);
    }//GEN-LAST:event_signupActionPerformed

    private void goActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goActionPerformed

        String loginid = userName.getText();
        String password = Password.getText();
        file = new File(location + "/" + loginid + ".txt");
        String text = "$L$" + " " + loginid + " " + password;
        try {
            outstream.writeObject(text);
            outstream.flush();
        } catch (IOException ex) {
            System.out.println("there is some problem in login info");
        }
        try {
            String fullresponse = instream.readObject().toString();
            String response = fullresponse.substring(4);
            JOptionPane pane = new JOptionPane();
            pane.showMessageDialog(null, response);

            if(!response.equals("either password or id not valid")){
            String fclients = instream.readObject().toString();
            String clients = fclients.substring(4);
            String[] members = clients.split(" ");
            showMembers(members);

             go.setEnabled(false);
            userName.setEditable(false);
            Password.setEditable(false);
            editText.setEditable(true);
            sendButton.setEnabled(true);
            openButton.setEnabled(true);
            saveButton.setEnabled(true);

            fw = new FileWriter(file, true);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            fw.write("\n logged in AT - " + dateFormat.format(date) + "\n\n");
            fw.close();

            new readThread().start();
        }
          else {

          }

           

        } catch (IOException ex) {
            go.setEnabled(true);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(testClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_goActionPerformed

    private void disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectActionPerformed



        String loginid = userName.getText();
        String text = "$D$" + " " + loginid;
        try {
            fw = new FileWriter(file, true);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            fw.write("\n disconnected at " + dateFormat.format(date) + "\n");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(testClient.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {
            connected = false;
            outstream.writeObject(text);
            outstream.flush();

        } catch (IOException ex) {
            System.out.println("there is some problem in sending disconnecting request");
        }


        try {
            instream.close();
            outstream.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(testClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        removeAll();


        disconnect.setEnabled(false);
        connectButton.setEnabled(true);

        sendButton.setEnabled(false);
        saveButton.setEnabled(false);
        openButton.setEnabled(false);

        signup.setEnabled(false);
        submitButton.setEnabled(false);
        go.setEnabled(false);


        initName.setEditable(false);
        intiPassword.setEditable(false);
        initId.setEditable(false);
        email.setEditable(false);


        editText.setEditable(false);
        userName.setEditable(false);
        Password.setEditable(false);
    }//GEN-LAST:event_disconnectActionPerformed

    public static void main(String args[]) throws UnknownHostException {

        
        JOptionPane pane1 = new JOptionPane();
        JOptionPane pane2 = new JOptionPane();

        String sport = pane1.showInputDialog(null,"enter port");
        port = Integer.parseInt(sport);
        address = pane2.showInputDialog(null,"enter your ip");
        address = address.trim();
        location = pane1.showInputDialog(null,"enter location where you want to save your chat detail");
        location = location.trim();
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                testClient tclient = new testClient();
                tclient.setVisible(true);
            }
        });
    }

    class readThread extends Thread {

        public void run() {

            String lid = userName.getText();




            while (connected) {
                try {
 

                    String fullmessage = instream.readObject().toString();
                    String header = fullmessage.substring(0, 3);
                    String message = fullmessage.substring(4);
                    if (header.equals("$M$")) {
                        chatArea.append(message);
                        fw = new FileWriter(file, true);
                        fw.write(message);
                        fw.close();
                    } else if (header.equals("$A$")) {
                        addOnList(message);
                    } else if (header.equals("$R$")) {
                        removeFromList(message);
                    }
                } catch (IOException ex) {
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(testClient.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Password;
    private javax.swing.JTextArea chatArea;
    private javax.swing.JScrollPane chatPane;
    private javax.swing.JButton connectButton;
    private javax.swing.JButton disconnect;
    private javax.swing.JTextField editText;
    private javax.swing.JTextField email;
    private javax.swing.JButton go;
    private javax.swing.JTextField initId;
    private javax.swing.JTextField initName;
    private javax.swing.JPasswordField intiPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton openButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel secret;
    private javax.swing.JButton sendButton;
    private javax.swing.JButton signup;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel user;
    private javax.swing.JTextField userName;
    private javax.swing.JList userslist;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JFileChooser fchooser;
}

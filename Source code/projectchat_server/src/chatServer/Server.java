package chatServer;


//  userlist = U, signup response = S,  login response = L
//  signup request = S, login request = L, message request = M, disconnect request = D
//  add notification = A  remove notification = R
import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Server {

    private static int port ;
    private ServerSocket chatconnection;
    private Socket socket;
    static String location = "/home/dell/NetBeansProjects/projectchat_server/clients_files";
    private Hashtable<String, clientInfo> clientTable = new Hashtable<String, clientInfo>();

    public static void main(String args[]) {

        JOptionPane pane = new JOptionPane();
        String sport = pane.showInputDialog(null, "enter your port" );
        port = Integer.parseInt(sport);
        System.out.println(port);

        location = pane.showInputDialog(null, "enter the location where server want to store the chat of all clients");
        location = location.trim();
       
        Server server = new Server(port);

    }

    // constructor
    public Server(int port) {
        try {
            chatconnection = new ServerSocket(port);
            while (true) {
                try {
                    socket = chatconnection.accept();
                    System.out.println("another connection accepted");
                    clientThread ct = new clientThread(this, socket);

                } catch (IOException ex) {
                    System.out.println("connection cannot be accepted");
                }
            }

        } catch (IOException ex) {
            System.out.println("server can not be started");
        }
    }

    class clientThread extends Thread {

        private Socket socket;
        Server server;
        private ObjectOutputStream outstream;
        private ObjectInputStream instream;
        private String loginid;
        private boolean connected = false;

        public clientThread(Server server, Socket socket) {
            this.server = server;
            this.socket = socket;
            try {

                outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.flush();
                instream = new ObjectInputStream(socket.getInputStream());
                System.out.println("successfully got streams");
                connected = true;
                start();
            } catch (IOException ex) {
                System.out.println("can not get streams");


            }
        }

        public void run() {



            while (connected) {
                try {

                    Object o = instream.readObject();

                    if(o instanceof File ){
                             File file = (File) o;
                             File newfile = new File(location + "/"+file.getName());
                             FileInputStream fin = new FileInputStream(file);
                             FileOutputStream fstream = new FileOutputStream(newfile);
                             byte[] buffer = new byte[1024];
                             while(fin.read(buffer)!=-1){
                                 fstream.write(buffer);
                             }
                             fin.close();
                             fstream.close();
                    }


                    else{
                    String fullrequest = o.toString();
                    String header = fullrequest.substring(0, 3);
                    String request = fullrequest.substring(4);


                    if (header.equals("$S$")) {
                        System.out.println("user is signing in");
                        String[] info = request.split(" ");
                        String name = info[0];
                        String id = info[1];
                        String password = info[2];
                        String email = info[3];

                        Readxml checker = new Readxml();
        

                        if (checker.checkidOnSignin(loginid)) {
                            System.out.println("user has signed in");
                            try {

                                InsertNewEntry inew = new InsertNewEntry(id, name, password, email);
                            } catch (ParserConfigurationException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SAXException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            outstream.writeObject("$S$" + " " + "you are successfully signed up and got a mail with your id and password ");
                            outstream.flush();

                            String sms = "thankyou" + "\n" + "your id: " + id + "\n" + "password: " + password + "\n";
                            try {
                                mailService ms = new mailService(email, sms);
                            } catch (MessagingException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }




                        } else {
                            outstream.writeObject("$L$" + " " + "either password or id not valid");
                            outstream.flush();
                        }

                        for (int i = 0; i < info.length; i++) {
                            System.out.println(info[i]);
                        }


                        // process signup request

                    } else if (header.equals("$L$")) {
                        // process login request
                        String[] info = request.split(" ");
                        loginid = info[0];
                        String password = info[1];
                        for (int i = 0; i < 2; i++) {
                            System.out.println(info[i]);
                        }
                       
                        Readxml checker = new Readxml();
                        String username = checker.checkidOnlogin(loginid, password);
                        if (username != null) {
                            outstream.writeObject("$L$" + " " + "you are successfully logged in");
                            outstream.flush();

                        clientInfo ci = new clientInfo();
                        ci.setID(loginid);
                        ci.setName(username);
                        ci.setSocket(socket);
                        ci.Setoutstream(outstream);
                        ci.Setinstream(instream);
                        ci.Setonline(true);



                        String clients = "";
                        Enumeration e = clientTable.keys();
                        while (e.hasMoreElements()) {
                            String s = (String) e.nextElement();
                            clientInfo ccc = clientTable.get(s);
                            clients = clients + ccc.getName() + " ";
                        }

                        notifyall(username, 'A');

                        clientTable.put(loginid, ci);

                        System.out.println("total online peopole - " + clientTable.size());
                        System.out.println(username + " has joined");
                        outstream.writeObject("$U$" + " " + clients);
                        outstream.flush();

                        } else {
                            outstream.writeObject("$L$" + " " + "either password or id not valid");
                            outstream.flush();
                        }



                    } else if (header.equals("$M$")) {
                        new sendThread(server, request, loginid);
                    } else if (header.equals("$D$")) {
                        String requestid = request;
                        String username = clientTable.get(requestid).getName();
                        notifyall(username, 'R');
                        connected = false;
                        clientInfo infoo = clientTable.get(requestid);
                        infoo.Getoutstream().close();
                        infoo.Setoutstream(null);
                        infoo.Getinstream().close();
                        infoo.Setinstream(null);
                        infoo.getSocket().close();
                        infoo.setSocket(null);

                        clientTable.remove(loginid);

                        System.out.println(loginid + " has disconnected");

                    }

                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        public boolean checkIDfromTable(String id) {
            return server.clientTable.containsKey(id);
        }

        public void notifyall(String name, char c) {
            ObjectOutputStream out;
            String text = "";
            if (c == 'A') {
                text = "$A$" + " " + name;
            }
            if (c == 'R') {
                text = "$R$" + " " + name;
            }
            try {

                Enumeration e = clientTable.keys();
                while (e.hasMoreElements()) {
                    String s = (String) e.nextElement();
                    clientInfo ccc = clientTable.get(s);
                    out = ccc.Getoutstream();
                    out.writeObject(text);
                    out.flush();
                }

            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void sendtoall(String message, String senderid, Server server) throws IOException {

        Socket sock;
        ObjectOutputStream out;
        clientInfo cin;
 
        String[] part = message.split("-");
        String clients = part[0];
        String text = part[1];
        String[] clo = clients.split(" ");
        cin = server.clientTable.get(senderid);
        String name = cin.getName();

        Enumeration e = server.clientTable.keys();
        while (e.hasMoreElements()) {
            String s = e.nextElement().toString();
            cin = server.clientTable.get(s);
            String member = cin.getName();
            for (int i = 0; i < clo.length; i++) {
                if (!s.equals(senderid) && member.equals(clo[i])) {
                    out = cin.Getoutstream();
                    out.writeObject("$M$" + " " + name + ">> " + text + "\n");
                    out.flush();
                }
            }
        }
    }

    class sendThread extends Thread {

        String request;
        String senderid;
        Server server;

        public sendThread(Server server, String request, String senderid) {
            this.request = request;
            this.senderid = senderid;
            this.server = server;
            start();

        }

        public void run() {
            try {
                sendtoall(request, senderid, server);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}


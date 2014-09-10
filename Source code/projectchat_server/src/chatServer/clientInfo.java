package chatServer;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

 class clientInfo implements Serializable{
       private String name;
       private String password;
       private String email;
       private String id;
       private String[] friendlist;
       private Socket socket = null;
       private ObjectOutputStream outer = null;
       private ObjectInputStream inner = null;
       private boolean online = false;

       public void Setonline(boolean online){
          this.online = online;
       }

        public boolean Getonline(){
          return online;
       }

       public void setSocket(Socket socket){
       this.socket = socket;

       }

       public Socket getSocket(){
         return socket;
       }

       public void setName(String name){
        this.name = name;
       }

       public void setPassword(String password){
        this.password = password;
       }

       public void setEmail(String email){
        this.email = email;
       }

       public void setID(String id){
        this.id = id;
       }

       public void setFrienflist(String[] friendlist){
        this.friendlist = friendlist;
       }

       //  add more friends

       public String getName(){
         return name;
       }

       public String getPassword(){
          return password;
       }

       public String getEmail(){
          return email;
       }

       public String getID(){
          return id;
       }

       public String[] getFrinedlist(){
         return friendlist;
       }

       public ObjectOutputStream Getoutstream(){
            return outer;
       }

       public ObjectInputStream Getinstream(){
            return inner;
       }


       public void Setoutstream(ObjectOutputStream outer){
            this.outer =  outer;
       }

       public void Setinstream(ObjectInputStream inner){
           this.inner = inner;
       }
    }



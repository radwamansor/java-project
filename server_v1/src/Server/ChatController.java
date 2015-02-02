/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DatabaseHandler.UserData;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import model.Contact;
import model.Message;
import model.Room;
import model.State;
import model.User;

import rmi.client.IClientListener;
import view.ModelType;

/**
 *
 * @author it
 */
public class ChatController implements IChatController {

    UserData userData;
    IChatModel chatModel;

    //Vector<view.IClientAction> clientsvector = new Vector<view.IClientAction>();
    Hashtable<String, IClientListener> onlineUsers = new Hashtable<>();
    public static Hashtable<String, Integer> onlineState = new Hashtable<>();
    HashSet<String> roomIds = new HashSet<>();
    static int roomId = 0;

    public ChatController() {
        userData = new UserData();
        chatModel = new ChatModel();
    }

    @Override

    public void sendMessage(Room room, Message msg) {
        Vector<Contact> conts = room.contactVector;
        for (int i = 0; i < conts.size(); i++) {
            try {
                IChatModel model = new ChatModel();
                model.setServiceNumber(ModelType.RECIEVE_MESSAGE);
                model.setMsg(msg);
                model.setRoom(room);
                if (!onlineUsers.containsKey(conts.get(i).getEmail())) {
                    //if user is offline
                    //add to its offline message
                } else {
                    onlineUsers.get(conts.get(i).getEmail()).changeModel(model);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void sendFile(Room room, byte[] bs,String filename,User user) {

        Vector<Contact> conts = room.contactVector;

        for (int i = 0; i < conts.size(); i++) {
            try {
                IChatModel model = new ChatModel();
                model.setServiceNumber(ModelType.RECICVE_FILE);
                model.setBs(bs);
                model.setFileName(filename);
                model.setRoom(room);
                model.setUser(user);
                onlineUsers.get(conts.get(i).getEmail()).changeModel(model);
            } catch (RemoteException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    public void recieveFile(byte[] bs) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addContactToRoom(Contact contact) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addUser(User user) {
        nullChatModel();
        boolean inserted = userData.InsertUser(user);
        String str = user.getUserEmail();
        /* rejectFriend(new User(str,null,null,null), new Contact(str, null, null, null, 8));
         System.out.println("rejected frined succsssefully");*/

        if (inserted == false) {
            chatModel.setJoptionPaneMassage("E-mail is Already used");
            System.out.println("E-mail is Already used");
            chatModel.setServiceNumber(ModelType.USER_FOUND);
            /*  rejectFriend(user, new Contact(str, null, null, null, 8));
             System.out.println("rejected frined succsssefully");*/
        } else {
            System.out.println("User E-mail: " + str);
            chatModel.setJoptionPaneMassage("Inserted successfully");
            chatModel.setServiceNumber(ModelType.USER_NOTFOUND);
            chatModel.setUser(userData.selectUser(user.getUserEmail()));
            /*  User u=new User();
             u=chatModel.getUser();
             System.out.println("User E-mail: "+u.getUserEmail());*/
            /*  System.out.println("connect to database");
             Contact c=new Contact("sara@yahoo", "ahmed", "ay7aga", null, 8);
             User u=new User();
             u.setUserEmail("rania.huissen@gmail.com");
             acceptFriend(u,c );
             System.out.println("accept frined succsssefully");*/
            // rania.huissen@gmail.com
        }

        //  sendChatModel();
    }

    @Override
    public void addContact(User user, Contact contact) {
        boolean flag = userData.validateMail(contact.getEmail());
        
       // boolean flag1=userData.selectFriend(user, contact.getEmail());
        // System.out.println(flag1);
       /* if(flag1==true){
         try {
         System.out.println("already found");
         chatModel.setServiceNumber(ModelType.REQUEST_NOT_SEND);
         chatModel.setJoptionPaneMassage("E-Mail is Already in Friend List");
         onlineUsers.get(user.getUserEmail()).changeModel(chatModel);
         } catch (RemoteException ex) {
         Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
         }
         }*/
        if (flag && user.getUserEmail().equals(contact.getEmail())==false ) {
            try {

                boolean send = userData.addContact(user.getUserEmail(), contact.getEmail());
                System.out.println("flag");
                if (send) {
                    chatModel.setServiceNumber(ModelType.REQUEST_SEND);
                    chatModel.setJoptionPaneMassage("Request Send");
                    chatModel.setContact(contact);
                    if (onlineUsers.containsKey(contact.getEmail())) {
                        onlineUsers.get(contact.getEmail()).changeModel(chatModel);
                    } else {
                        onlineUsers.get(user.getUserEmail()).changeModel(chatModel);
                    }
                } else {
                    chatModel.setServiceNumber(ModelType.REQUEST_NOT_SEND);
                    chatModel.setJoptionPaneMassage("Already In Friend List");
                    onlineUsers.get(user.getUserEmail()).changeModel(chatModel);
                }

            } catch (RemoteException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            if(flag==false){
                try {
                    chatModel.setServiceNumber(ModelType.REQUEST_NOT_SEND);
                    chatModel.setJoptionPaneMassage("E-Mail Not Found");
                    onlineUsers.get(user.getUserEmail()).changeModel(chatModel);
                } catch (RemoteException ex) {
                    Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else {
                try {
                    chatModel.setServiceNumber(ModelType.REQUEST_NOT_SEND);
                    chatModel.setJoptionPaneMassage("You May send Request to Yourself:)");
                    onlineUsers.get(user.getUserEmail()).changeModel(chatModel);
                } catch (RemoteException ex) {
                    Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    @Override
    public void removeContact(User user, Contact contact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void acceptFriend(User user, Contact contact) {
        try {
            System.out.println("accepted");
            boolean accepted = userData.acceptContact(user.getUserEmail(), contact.getEmail());
            if (accepted) {
                System.out.println("Accept Friend: ");
                chatModel.setJoptionPaneMassage("Accepted successfully");
                chatModel.setServiceNumber(ModelType.ACCEPT_FRIEND);
                chatModel.setContact(userData.selectContact(contact.getEmail()));
                onlineUsers.get(user.getUserEmail()).changeModel(chatModel);

                chatModel.setJoptionPaneMassage(" accepted your friend request");
                chatModel.setServiceNumber(ModelType.RECIEVE_ACCEPTANCE);
                chatModel.setContact(userData.selectContact(user.getUserEmail()));
                if (onlineUsers.containsKey(contact.getEmail())) {
                    onlineUsers.get(user.getUserEmail()).changeModel(chatModel);
                } else {
                    //send offline notification
                }

            } else {
                System.out.println("NOT Accept Friend: ");
                chatModel.setJoptionPaneMassage("NOt Accepted");
                chatModel.setServiceNumber(ModelType.NOT_ACCEPT_FRIEND);
                onlineUsers.get(user.getUserEmail()).changeModel(chatModel);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void rejectFriend(User user, Contact contact) {
        boolean rejected = userData.rejectContact(user.getUserEmail(), contact.getEmail());
        if (rejected) {
            System.out.println("reject Friend: ");
            chatModel.setJoptionPaneMassage("rejected successfully");
            chatModel.setServiceNumber(ModelType.REJECTED);

        } else {
            System.out.println("NOT reject Friend: ");
            chatModel.setJoptionPaneMassage("NOt rejected");
            chatModel.setServiceNumber(ModelType.NOT_REJECTED);
        }
    }

    @Override
    public void changeState(User user) {
        if (onlineUsers.containsKey(user.getUserEmail())) {
            onlineState.put(user.getUserEmail(), user.getUserState());

            for (int i = 0; i < user.userContacts.size(); i++) {
                if (onlineUsers.containsKey(user.userContacts.get(i).getEmail())) {
                    try {
                        chatModel.setContact(new Contact(user.getUserEmail(), user.getUserName(), user.getUserStatus(), user.getUserImage(), user.getUserState()));
                        chatModel.setServiceNumber(ModelType.CHANGE_STATE);
                        onlineUsers.get(user.userContacts.get(i).getEmail()).changeModel(chatModel);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }

    }

    @Override
    public void changeStatus(User user) {
        userData.changeStatus(user);

        /*try {
         //IClientListener clientListner = new ClientListener();
         chatModel.setServiceNumber(ModelType.CHANGE_STATUS);
         clientListner.changeModel(chatModel);

         } catch (RemoteException ex) {
         Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
         }*/
    }

    @Override
    public void ChangeProfilePic(User user, FileInputStream image) {
        userData.updateImage(user.getUserEmail(), image);
        chatModel.setImg(image);
        chatModel.setServiceNumber(ModelType.PHOTO_CHANGED);
        for (int i = 0; i < user.userContacts.size(); i++) {
            if (onlineUsers.containsKey(user.userContacts.get(i))) {
                try {
                    onlineUsers.get(user.userContacts.get(i)).changeModel(chatModel);
                } catch (RemoteException ex) {
                    Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        /* if(changed){
         try {
         chatModel.setServiceNumber(ModelType.PHOTO_CHANGED);
         chatModel.setUser(user);
         chatModel.setJoptionPaneMassage("Profile picture changed");
         chatModel.setBs(user.getUserImage());
         // user=userData.selectUser(contact.getEmail());
         onlineUsers.get(user.getUserEmail()).changeModel(chatModel);
         } catch (RemoteException ex) {
         Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
         }
         }else{
         try {
         chatModel.setServiceNumber(ModelType.PHOTO_NOT_CHANGED);
         chatModel.setJoptionPaneMassage("Profile picture Not Changed yet,try Again");
         onlineUsers.get(user.getUserEmail()).changeModel(chatModel);
         } catch (RemoteException ex) {
         Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
         }
         }
         */
    }

    @Override
    public void leaveConversation(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override

    public void register(String mail, IClientListener clientRef) {
        onlineUsers.put(mail, clientRef);
        System.out.println("no. of online " + onlineUsers.size());
        System.out.println("Client Added");
    }

    @Override

    public void unRegister(String mail) {
        onlineUsers.remove(mail);

        System.out.println("Client Removed");
    }

    void sendChatModel() {
        /*try {
         ClientListener clientListener = new ClientListener();
         clientListener.changeModel(chatModel);
         } catch (RemoteException ex) {
         Logger.getLogger(ChatModel.class.getName()).log(Level.SEVERE, null, ex);
         }*/

    }

    void nullChatModel() {
        chatModel.setJoptionPaneMassage(null);
        chatModel.setServiceNumber(0);
        chatModel.setUser(null);
    }

    @Override
    public void signIn(User user, IClientListener clientListener) {
        nullChatModel();
        User u;
        boolean validMail = false;
        if (userData.validateMail(user.getUserEmail())) {
            System.out.println(user.getUserEmail() + " sign in");
            if (userData.validatePass(user.getUserEmail(), user.getUserPassword())) {
                try {
                    chatModel.setServiceNumber(ModelType.USER_FOUND);
                    u = userData.selectUser(user.getUserEmail());
                    u.setUserState(user.getUserState());
                    chatModel.setUser(u);
                    register(u.getUserEmail(), clientListener);
                    clientListener.changeModel(chatModel);
                } catch (RemoteException ex) {
                    Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    chatModel.setJoptionPaneMassage("Invalid password");
                    chatModel.setServiceNumber(ModelType.SERROR_MESSAGE);
                    clientListener.changeModel(chatModel);
                } catch (RemoteException ex) {
                    Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                chatModel.setJoptionPaneMassage("Invalid Email");
                chatModel.setServiceNumber(ModelType.SERROR_MESSAGE);
                clientListener.changeModel(chatModel);
            } catch (RemoteException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void startConversation(Room room, User user) {
        /*try {
         room.setRoomId(roomId++);
         nullChatModel();
         chatModel.setServiceNumber(ModelType.RECIEVE_ROOM_ID);
         chatModel.setRoom(room);
            
            
         onlineUsers.get(user.getUserEmail()).changeModel(chatModel);
         } catch (RemoteException ex) {
         Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("gggg");

         }*/

    }

}

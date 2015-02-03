
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.client;

import Server.IChatModel;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.parser.TokenType;
import model.Contact;
import model.Room;
import model.User;
import pkg1.ContactPanel;
import pkg1.chatCui;
import pkg1.conversation;
import pkg1.messenger;
import pkg1.request;
import view.ModelType;

/**
 *
 * @author Radwa Manssour
 */
public class ClientListener extends UnicastRemoteObject implements IClientListener {
    /* IClientView clientView;
     public ClientListener(IClientView clientView)throws RemoteException{
     this.clientView=clientView;
     }*/

    chatCui gui;

    public ClientListener(chatCui gui) throws RemoteException {
        this.gui = gui;
    }

    @Override
    public void changeModel(IChatModel chatModel) {
        switch (chatModel.getServiceNumber()) {
            case ModelType.USER_FOUND:
                User user = chatModel.getUser();
                System.out.println(user.getUserEmail() + "clientlistener");
                gui.setUser(user);
                CardLayout card = (CardLayout) gui.parentPanel.getLayout();
                gui.mess = new messenger(gui, user);
                gui.parentPanel.add("messenger", gui.mess);
                card.show(gui.parentPanel, "messenger");
                break;

            case ModelType.SERROR_MESSAGE:
                String errorMessage = chatModel.getJoptionPaneMassage();
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        JOptionPane.showMessageDialog(null, new String(errorMessage));
                    }
                });

                break;

            case ModelType.RECIEVE_MESSAGE:
                //boolean foundId = false;
                
                    
                     if(!gui.rooms.containsKey(chatModel.getRoom().getRoomId())){
                         conversation conv=new conversation(gui,chatModel.getRoom());
                         conv.setRoom(chatModel.getRoom());
                         conv.setVisible(true);
                         gui.rooms.put(chatModel.getRoom().getRoomId(), conv);
                         gui.rooms.get(chatModel.getRoom().getRoomId()).text2.append("\n" + chatModel.getMsg().getSender() + " : " + chatModel.getMsg().getTxt());
                     }else{
                         gui.rooms.get(chatModel.getRoom().getRoomId()).setVisible(true);
                         gui.rooms.get(chatModel.getRoom().getRoomId()).text2.append("\n" + chatModel.getMsg().getSender() + " : " + chatModel.getMsg().getTxt());
                         System.out.println("room id  :  "+chatModel.getRoom().getRoomId());
                   

                }

                break;
            case ModelType.RECIEVE_ROOM_ID:
                conversation conv = new conversation(gui,chatModel.getRoom());
                conv.setRoomId(chatModel.getRoom().getRoomId());
                conv.setRoom(chatModel.getRoom());
                //gui.room.rooms_tabs.insertTab(chatModel.getRoom().getName(), null, conv, null, gui.room.rooms_tabs.getTabCount());
                break;

            case ModelType.RECICVE_FILE:
                String msg = chatModel.getJoptionPaneMassage();
                String userName=chatModel.getUser().getUserName();
                byte[] bs = chatModel.getBs();
                String name=chatModel.getFileName();
                
                System.out.println("recive file ");
                
                java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Object[] options = { "OK", "CANCEL" };
                int selectedOption=JOptionPane.showOptionDialog(null, "Do You Want To Save File Received From "+ userName, "Save File !", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
                if(selectedOption ==JOptionPane.YES_OPTION){
                      FileOutputStream fos;
                 try {
                     fos = new FileOutputStream("D:/"+name);
                     fos.write(bs);
                     fos.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                     Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                else {
                    
                }
                    
                


                //JOptionPane.showMessageDialog(null, new String(msg));
            }
        });
          
               
                    
               /* java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 JFileChooser f = new JFileChooser();
                if (f.showSaveDialog(gui) == JFileChooser.APPROVE_OPTION) {
                    String path = f.getSelectedFile().getPath();
                try {
                    FileOutputStream fos=new FileOutputStream(path);
                    fos.write(bs);
                    fos.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
            }
        });*/
               
            break;
                
                
            case ModelType.REQUEST_SEND:
                java.awt.EventQueue.invokeLater( new Runnable() {

            @Override
            public void run() {
               JOptionPane.showMessageDialog(null,chatModel.getJoptionPaneMassage());
            }
        });
                    Contact c1 = chatModel.getContact();
                    request r1 = new request();
                    ImageIcon ico1 = new ImageIcon(c1.getPhoto());
                    r1.img.setIcon(ico1);
                    r1.name.setText(c1.getName());
                    gui.mess.requestsPanel.add(r1);
                    r1.accept.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.cih.acceptContact(gui.user, c1);

                        }
                    });
                

                gui.mess.requestsPanel.repaint();
                
                break;
                
            case ModelType.REQUEST_NOT_SEND:
                String errorMsg=chatModel.getJoptionPaneMassage();
                java.awt.EventQueue.invokeLater( new Runnable() {

            @Override
            public void run() {
               JOptionPane.showMessageDialog(null,new String(errorMsg));
            }
        });
                gui.setUser(chatModel.getUser());
                CardLayout card2 = (CardLayout) gui.parentPanel.getLayout();
                gui.mess = new messenger( gui, chatModel.getUser());
                gui.parentPanel.add("messenger", gui.mess);
                card2.show(gui.parentPanel, "messenger");
                break;
            case ModelType.ACCEPT_FRIEND:
                gui.user.userRequests.remove(chatModel.getContact().getEmail());
                gui.user.userContacts.add(chatModel.getContact());
                
                gui.mess.requestsPanel.removeAll();
                gui.mess.requestsPanel.repaint();
                Set<String> entrySet = gui.user.userRequests.keySet();
                Iterator<String> it = entrySet.iterator();

                while (it.hasNext()) {
                    System.out.println("requesttttttt");
                    String i = it.next();
                    Contact c = gui.user.userRequests.get(i);
                    request r = new request();
                    ImageIcon ico = new ImageIcon(c.getPhoto());
                    r.img.setIcon(ico);
                    r.name.setText(c.getName());
                    gui.mess.requestsPanel.add(r);
                    r.accept.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.cih.acceptContact(gui.user, c);


                            /*ContactPanel c1=new ContactPanel(window,c);
                             ImageIcon i =new ImageIcon(c.getPhoto());
                             c1.img.setIcon(i);
                             c1.name.setText(c.getName());
                             c1.status.setText(c.getStatus());
                             c1.state.setIcon(stateColor[c.getState()]);
                             contactsPanel.add(c1);*/
                        }
                    });
                }

                gui.mess.requestsPanel.repaint();
                ContactPanel cont = new ContactPanel(gui, chatModel.getContact());
                gui.mess.contacts.put(chatModel.getContact().getEmail(), cont);
                gui.mess.contactsPanel.add(cont);

                //cont.img.setIcon(new ImageIcon(chatModel.getContact().getPhoto()));
                cont.name.setText(chatModel.getContact().getName());
                cont.status.setText(chatModel.getContact().getStatus());
                break;
                case ModelType.PHOTO_NOT_CHANGED:
                java.awt.EventQueue.invokeLater( new Runnable() {

            @Override
            public void run() {
               JOptionPane.showMessageDialog(null,chatModel.getJoptionPaneMassage());
            }
        });
                /*gui.setUser(chatModel.getUser());
                CardLayout card4 = (CardLayout) gui.parentPanel.getLayout();
                gui.mess = new messenger( gui, chatModel.getUser());
                gui.parentPanel.add("messenger", gui.mess);
                card4.show(gui.parentPanel, "messenger");*/
                break;
                 
                case ModelType.PHOTO_CHANGED:
                    
                gui.setUser(chatModel.getUser());
                CardLayout card5 = (CardLayout) gui.parentPanel.getLayout();
                gui.mess = new messenger( gui, chatModel.getUser());
                gui.parentPanel.add("messenger", gui.mess);
                card5.show(gui.parentPanel, "messenger");
                
                break;
            case ModelType.REJECTED:
                gui.user.userRequests.remove(chatModel.getContact().getEmail());
                gui.user.userContacts.add(chatModel.getContact());
                gui.mess.requestsPanel.removeAll();
                gui.mess.requestsPanel.repaint();
                Set<String> entrySet1 = gui.user.userRequests.keySet();
                Iterator<String> it1 = entrySet1.iterator();

                while (it1.hasNext()) {
                    String i = it1.next();
                    Contact c = gui.user.userRequests.get(i);
                    request r = new request();
                    ImageIcon ico = new ImageIcon(c.getPhoto());
                    r.img.setIcon(ico);
                    r.name.setText(c.getName());
                    gui.mess.requestsPanel.add(r);
                    r.accept.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.cih.acceptContact(gui.user, c);
                        }
                    });
                }

                gui.mess.requestsPanel.repaint();
                break;
            case ModelType.RECIEVE_ACCEPTANCE:
                ContactPanel cont1 = new ContactPanel(gui, chatModel.getContact());
                gui.mess.contactsPanel.add(cont1);
                //cont.img.setIcon(new ImageIcon(chatModel.getContact().getPhoto()));
                cont1.name.setText(chatModel.getContact().getName());
                cont1.status.setText(chatModel.getContact().getStatus());
                gui.mess.contacts.put(chatModel.getContact().getEmail(), cont1);
                java.awt.EventQueue.invokeLater(new Runnable() {
                    String message=chatModel.getJoptionPaneMassage();
                    public void run() {
                        JOptionPane.showMessageDialog(null, new String(message));
                    }
                });
                break;
            case ModelType.RECIEVE_REJECT:
                java.awt.EventQueue.invokeLater(new Runnable() {
                    String message=chatModel.getJoptionPaneMassage();
                    public void run() {
                        JOptionPane.showMessageDialog(null, new String(message));
                    }
                });
                break;
            case ModelType.CHANGE_STATE:
                //gui.mess.userContacts.
                ContactPanel cont2=gui.mess.contacts.get(chatModel.getContact().getEmail());
                cont2.state.setIcon(gui.mess.stateColor[chatModel.getContact().getState()]);

        }
        
        

    }

    @Override

    public void isConnected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import model.Contact;
import model.Message;
import model.Room;
import model.User;
import pkg1.request;
import rmi.client.IClientListener;

/**
 *
 * @author Radwa Manssour
 */
public interface IClientInputHandler {
    
    public void sendFile(Room room,byte[] bs,String name,User user);
    public void reciveFile(byte[] bs);
    public void signUp(User user);
    public void signIn(User user);
    public void addContact(User user,String mail);
    public void removeContact(String mail);    
    public void changePhoto(User user,byte[] bs);
    public void changeStatus(User user);
    public void changeState(User user);
    public void sendMessage(Room room,Message message);
    public void addMember(String RoomId,String mail);
    public void leaveConversation(int RoomId);
    public void acceptContact(User user,Contact contact);
    public void rejectContact(User user, Contact contact);
    public void displayMessage(Message message);
    public void register(User user , IClientListener cl);
    public void startConversation(Room room ,User user);
    
    
    
}

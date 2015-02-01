/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.FileInputStream;
import java.io.Serializable;
import java.rmi.Remote;
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
public interface IClientAction extends Serializable {

    public User getUser();

    public int getServiceNum();

    public Message getMessage();

    public Contact getContact();


    public Room getRoom();

    
    public IClientListener getClientModel();
    public void setClientModel(IClientListener clientModel);

    public byte[] getB();

    public void setB(byte[] b);

   public int getContIdx();

    
     public FileInputStream getImage();

    public void setImage(FileInputStream image);

    public void setUser(User user);

    public void setServiceNum(int SIGN_UP);

    public void setCon(Contact con);

    public void setMessage(Message message);

    public void setRoom(Room room);


}
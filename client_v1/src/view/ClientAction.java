/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.io.FileInputStream;
import model.Contact;
import model.Message;
import model.Room;
import model.User;
import pkg1.request;
import rmi.client.ClientListener;
import rmi.client.IClientListener;

/**
 *
 * @author Radwa Manssour
 */
public class ClientAction implements IClientAction {
    private User user;
    private int serviceNum;
    private Contact con;
    private int ContIdx;
    private Message message;
    private IClientListener clientModel;
    private Room room;
    private byte[] bs;
    private FileInputStream image;

   
    @Override
    public FileInputStream getImage() {
        return image;
    }

    public void setImage(FileInputStream image) {
        this.image = image;
    }

    public void setContIdx(int idx) {
        ContIdx=idx;
    }

    @Override
    public int getContIdx() {
        return ContIdx;
    }

    
    
    
    @Override
    public byte[] getB() {
        return bs;
    }

    public void setB(byte[] b) {
        this.bs = b;
    }
 
    @Override
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
    

    public IClientListener getClientModel() {
        return clientModel;
    }

    public void setClientModel(IClientListener clientModel) {
        this.clientModel = clientModel;
    }
    
    public ClientAction(){
        
    }
    @Override
    public User getUser() {
        return user;
    }

    @Override
    public int getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(int serviceNum) {
        this.serviceNum = serviceNum;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Contact getContact() {
        return con;
    }

    public void setCon(Contact con) {
        this.con = con;
    }

    @Override
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
   
    
    
    
}

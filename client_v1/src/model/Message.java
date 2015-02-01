/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author bishoy
 */
public class Message implements Serializable{
    private String roomId;
    private Timestamp timestamp;
    private String sender;
    private String txt;
    private boolean seen;
    
    public Message(String room,Timestamp timestamp,String sender,String txt,boolean seen){
        this.timestamp =timestamp;
        this.sender = sender;
        this.txt = txt;
        this.seen = seen;
    }
    public String getRoomId(){
        return roomId;
    }
    public Timestamp getTimestamp(){
        return timestamp;
    }
    public String getSender(){
        return sender;
    }
    public String getTxt(){
        return txt;
    }
    public boolean getSeen(){
        return seen;
    }
}

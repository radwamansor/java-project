/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.swing.text.SimpleAttributeSet;

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
    private SimpleAttributeSet keyWord;

    public void setKeyWord(SimpleAttributeSet keyWord) {
        this.keyWord = keyWord;
    }

    public SimpleAttributeSet getKeyWord() {
        return keyWord;
    }
    
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

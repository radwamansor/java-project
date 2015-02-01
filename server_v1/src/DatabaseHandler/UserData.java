/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseHandler;

import Server.ChatController;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.*;

/**
 *
 * @author it
 */
public class UserData {

    Connection con;
    public Vector<Contact> userContacts = new Vector<Contact>();
    public Vector<Room> userRooms = new Vector<Room>();
    /* IUser user;
     IRoom room;
     IContact contact;*/

    public UserData() {
        /*  user = new User();
         room = new Room();
         contact = new Contact(null, null, null, null, null);*/
    }

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/mydb", "root", "root");
        } catch (SQLException ex) {
            //System.out.println("error");
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean InsertUser(User user) {
        boolean flag = false;
        try {
            connect();
            //Statement stmt = con.createStatement();
            /*String queryString = new String("select * from User_Table");
             ResultSet rs = stmt.executeQuery(queryString);
             while (rs.next()) {
             System.out.println(rs.getString(1));
             }*/
            /*String insertString = new String("UPDATE tab SET id=35 WHERE id =3");
             stmt.executeUpdate(insertString);*/

            /* String deleteString = new String("DELETE FROM tab WHERE id ='2' ");
             stmt.executeUpdate(deleteString);*/
            String insertString = new String("INSERT INTO User_Table (user_Email,user_Name,password,gender) VALUES(?,?,?,?) ");
            PreparedStatement pst = con.prepareStatement(insertString);
            //pst.setInt(1, id);
            pst.setString(1, user.getUserEmail());
            pst.setString(2, user.getUserName());
            pst.setString(3, user.getUserPassword());
            pst.setString(4, user.getUserGender());
            if (pst.execute()== false ) {
                System.out.println("insert success");
                flag = true;
            } else {
                System.out.println("insert failled");
                flag = false;
            }
            /*else{
             System.out.println("Not Inserted");
             }*/
            /* PreparedStatement pst = con.prepareStatement("select * from tab where id=?");
             //String queryString = new String("select * from tab");
             pst.setInt(1, 2);
             ResultSet rs = pst.executeQuery();
             while (rs.next()) {
             System.out.println(rs.getString(2));
             }*/
        } catch (SQLException ex) {
            // ex.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public User selectUser(String mail) {
        boolean flag = true;
        User user = new User();
        String e;
        try {
            PreparedStatement pst = con.prepareStatement("select * from User_Table where user_Email=?");
            pst.setString(1, mail);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                System.out.println("userlisttable");
                user.setUserEmail(rs.getString(1));
                e=rs.getString(1);
                user.setUserName(rs.getString(2));
                user.setUserPassword(rs.getString(3));
                user.setUserGender(rs.getString(4));
                user.setUserStatus(rs.getString(5));
                //user.setUserImage(userImage);
                byte[] image = null;

                image = rs.getBytes("user_image");
                user.setUserImage(image);

            
            }
            PreparedStatement pst2 = con.prepareStatement("select * from user_request_table where receiver_Email=?");
            pst2.setString(1, mail);
            ResultSet rs2=pst2.executeQuery();
            System.out.println("ay7aga");
            while(rs2.next()){
                System.out.println("userlisttable");
                String m=rs2.getString(2);
                PreparedStatement pst3 = con.prepareStatement("select * from User_Table where user_Email=?");
                pst3.setString(1, m);
                ResultSet rs1=pst3.executeQuery();
                while(rs1.next()){
                     
                     byte[] image = null;

                     image = rs1.getBytes("user_image");
                     Contact cont=new Contact(rs1.getString(1),rs1.getString(2) , rs1.getString(5), image, 0); 
                     
                     user.userRequests.put(cont.getEmail(),cont);
                     
                }
            }
            PreparedStatement pst4 = con.prepareStatement("select * from user_list_table where user_Email=?");
            pst4.setString(1, mail);
            ResultSet rs5=pst4.executeQuery();
            while(rs5.next()){
                System.out.println("userlisttable");
                String m=rs5.getString(1);
                PreparedStatement pst3 = con.prepareStatement("select * from User_Table where user_Email=?");
                pst3.setString(1, m);
                ResultSet rs1=pst3.executeQuery();
                while(rs1.next()){
                     byte[] image = null;

                     image = rs1.getBytes("user_image");
                     int state=State.OFFLINE;
                     if(ChatController.onlineState.containsKey(rs1.getString(1))){
                         state=ChatController.onlineState.get(rs1.getString(1));
                     }
                     Contact cont=new Contact(rs1.getString(1),rs1.getString(2) , "Chat model", image, state);   
                     
                     user.userContacts.add(cont);
                }
            }
            /*
             pst = con.prepareStatement("select reciever_email from User_list_table where user_Email='"+mail+"'");
            rs=pst.executeQuery();
            while(rs.next()){
                ResultSet rs1=con.createStatement().executeQuery("select * from user_table where user_Email='"+rs.getString("reciever_email")+"'");
                Contact cont=new Contact(rs1.getString("user_email"),rs1.getString("user_name") , rs1.getString("user_status"), null, 0);
                user.userContacts.add(cont);
            }
            PreparedStatement pst = con.prepareStatement("select reciever_email from User_list_table where user_Email='"+mail+"'");
            rs=pst.executeQuery();
            while(rs.next()){
                ResultSet rs1=con.createStatement().executeQuery("select * from user_table where user_Email='"+rs.getString("reciever_email")+"'");
                Contact cont=new Contact(rs1.getString("user_email"),rs1.getString("user_name") , rs1.getString("user_status"), null, 0);
                user.userContacts.add(cont);
            }*/
            
        } catch (SQLException ex) {            
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
            flag = false;
        }
        if (flag == true) {
            return user;
        } else {
            System.out.println("not found ");
            return null;
        }
    }
    public boolean addContact(String user,String contact){
        boolean flag=true;
        System.out.println("database insert user");
        try {
            System.out.println("inserted");
            String insertString = new String("INSERT INTO user_request_table (user_Email,receiver_Email) VALUES(?,?) ");
            PreparedStatement pst = con.prepareStatement(insertString);
            //pst.setInt(1, id);
            pst.setString(1, user);
            pst.setString(2, contact);
            System.out.println("inserted tani");
            if (pst.execute()== false ) {
                System.out.println("request send");
                flag = true;
            } else {
                System.out.println("request not send");
                flag = false;
            } 
            
        } catch (SQLException ex) {
            flag=false;
            //System.out.println("");
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    
    
    }
//Radwa
    public boolean validateMail(String mail){
        try {
            connect();            
            PreparedStatement pst=con.prepareStatement("select * from User_Table where user_Email=?");
            pst.setString(1, mail);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
        
    }
    public boolean validatePass(String mail , String pass){
        try {
            PreparedStatement pst=con.prepareStatement("select password from User_Table where user_Email=?");
            pst.setString(1, mail);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString("password"));
                if(rs.getString("password").equals(pass)){
                    return true;
                }
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
        
    }
    public User retrievetUserInfo(User user) {
        String mail=user.getUserEmail();
        try {       
            
            PreparedStatement pst = con.prepareStatement("select * from User_Table where user_Email=?");
            pst.setString(1, mail);
            ResultSet rs = pst.executeQuery();
            
            user.setUserEmail(rs.getString("user_email"));
            user.setUserName(rs.getString("user_name"));
            user.setUserPassword(rs.getString("password"));
            user.setUserGender(rs.getString("gender"));
            user.setUserStatus(rs.getString("status"));
            File image = new File(getClass().getResource("/pkg1/m.jpg").toURI());
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(image);
                byte[] buffer = new byte[1];
                InputStream is = rs.getBinaryStream("user_image");
                while (is.read(buffer) > 0) {
                    fos.write(buffer);
                    System.out.println("1");
                }
                FileOutputStream fout=new FileOutputStream("C:\\Users\\it\\Downloads\\image.jpg");
                fout.write(buffer);
                user.setUserImage(buffer);
                ImageIcon c=new ImageIcon("C:\\Users\\it\\Downloads\\image.jpg");
                fout.close();
            
            
                
                
                fos.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
            }

            
            
            //image
            pst = con.prepareStatement("select reciever_email from User_request_table where user_Email='"+mail+"'");
            rs=pst.executeQuery();
            while(rs.next()){
                ResultSet rs1=con.createStatement().executeQuery("select * from user_table where user_Email='"+rs.getString("reciever_email")+"'");
                Contact cont=new Contact(rs1.getString("user_email"),rs1.getString("user_name") , rs1.getString("user_status"), null, 0);
                user.userRequests.put(cont.getEmail(),cont);
            }
            pst = con.prepareStatement("select reciever_email from user_list_table where user_Email='"+mail+"'");
            rs=pst.executeQuery();
            while(rs.next()){
                ResultSet rs1=con.createStatement().executeQuery("select * from User_Table where user_Email='"+rs.getString("reciever_email")+"'");
                Contact cont=new Contact(rs1.getString("user_email"),rs1.getString("user_name") , rs1.getString("user_status"), null, 0);
                user.userContacts.add(cont);
            }
            //add also offline messages
            
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

//Radwa

//bishoy
    /*public void sendFriendRequest(String mail){
        PreparedStatement pst2 = con.prepareStatement("update table User_Request_Table set user_Email=?");
            pst2.setString(1, mail);
            ResultSet rs2=pst2.executeQuery();
    }*/
//bishoy

//Aliaa
    public void updateImage(String user_Email,FileInputStream userImage){
    
        try {
            //fc.setFileFilter(new JPEGImageFileFilter());-->not
            connect();
            
            int len_file = (int) userImage.available();
            java.sql.PreparedStatement stmt1 = con.prepareStatement("update User_Table set user_Image=? Where user_Email=?");
            stmt1.setString(2,user_Email);
            stmt1.setBlob(1, userImage, len_file);
            
            stmt1.executeUpdate();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                userImage.close();
            } catch (IOException ex) {
                Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
public void update_user_status(String user_Email,String status){

        try {
            connect();
            java.sql.PreparedStatement stmt1 = con.prepareStatement("update User_Table set status=? Where user_Email=?)");
            
            stmt1.setString(1, status);
            stmt1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
}


    public void delete_user_request_table(String user_Email, String receiver_Email) {

       
        try {
            connect();
            java.sql.PreparedStatement stmt1 = con.prepareStatement("DELETE FROM user_request_table WHERE user_Email=? and receiver_Email=?");

            stmt1.setString(1, user_Email);
            stmt1.setString(2, receiver_Email);
            stmt1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }

         
       

    }
    
    
    public void insert_user_request_table(String user_Email, String receiver_Email) {

        try {
            connect();
            java.sql.PreparedStatement stmt1 = con.prepareStatement("insert into user_request_table values(?,?)");

            stmt1.setString(1, user_Email);
            stmt1.setString(2, receiver_Email);
            stmt1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }
    
   

//Aliaa

//Sarah
   public void changeStatus(User user){
        //implementation
        try {
            connect();
            String query = "UPDATE User_Table SET status = ? where user_Email = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(5,user.getUserStatus()); 
            preparedStmt.setString(1, user.getUserEmail());
            preparedStmt.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//Sarah


//Jihad
    
  
    public boolean acceptContact(String userEmail,String friendEmail) {
        boolean flag = true;
        try {
            connect();
            
          
            PreparedStatement stmt1 = con.prepareStatement("insert into user_list_table values(?,?)");

            stmt1.setString(1,friendEmail );
            stmt1.setString(2, userEmail);
            stmt1.executeUpdate();
            stmt1 = con.prepareStatement("insert into user_list_table values(?,?)");

            stmt1.setString(1, userEmail);
            stmt1.setString(2, friendEmail);
            stmt1.executeUpdate();
            PreparedStatement pst = con.prepareStatement("DELETE FROM user_request_table WHERE user_Email=? and receiver_Email=?");

            pst.setString(1, friendEmail);
            pst.setString(2, userEmail);
            pst.executeUpdate();
        } catch (SQLException ex) {
             flag = false;
        }
           
       return flag;
}
     public boolean rejectContact(String userEmail,String friendEmail) {
        boolean flag = true;
        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM user_request_table WHERE user_Email=? and receiver_Email=?");

            pst.setString(1, friendEmail);
            pst.setString(2, userEmail);
            pst.executeUpdate();
           
        } catch (SQLException ex) {
            flag = false;
        }
       return flag;
}
    public Contact selectContact(String mail) {
        boolean flag = true;
        Contact contact=null;
        try {
            PreparedStatement pst = con.prepareStatement("select * from User_Table where user_Email=?");
            pst.setString(1, mail);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                contact=new Contact(rs.getString(1),rs.getString(2),rs.getString(5),null,1);
            }
        } catch (SQLException ex) {
            flag = false;
        }
        if (flag == true) {
            return contact;
        } else {
            System.out.println("not found ");
            return null;
        }
    }
//Jihad
    
    public static void main(String[] args){
        //new UserData().updateImage("user@yahoo.com", "src/login/bg.jpg");
    }
     
}

//Radwa

//Radwa

//bishoy

//bishoy

//Aliaa

//Aliaa

//Sarah

//Sarah

//Jihad
    
  
//    public boolean acceptContact(String userEmail,String friendEmail) {
//        boolean flag = true;
//        try {
//            connect();
//            PreparedStatement pst = con.prepareStatement("DELETE FROM user_request_table WHERE user_Email=? and receiver_Email=?");
//
//            stmt1.setString(1, userEmail);
//            stmt1.setString(2, friendEmail);
//           stmt1.executeUpdate();
//            
//        } catch (SQLException ex) {
//            flag = false;
//        }
//       return flag;
//}
//     public boolean rejectContact(String userEmail,String friendEmail) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = con.prepareStatement("select * from User_Table where user_Email=?");
//            pst.setString(1, mail);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                user.setUserEmail(rs.getString(1));
//                user.setUserName(rs.getString(2));
//                user.setUserPassword(rs.getString(3));
//                user.setUserGender(rs.getString(4));
//                user.setUserStatus(rs.getString(5));
//            }
//        } catch (SQLException ex) {
//            flag = false;
//        }
//       return flag;
//}

//Jihad

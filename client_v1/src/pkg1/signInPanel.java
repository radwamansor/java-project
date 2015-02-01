/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg1;

import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.User;
import view.ClientInputHandler;

/**
 *
 * @author Radwa Manssour
 */
public class signInPanel extends javax.swing.JPanel {

    /**
     * Creates new form signInPanel
     */
    
     private ClientInputHandler handler;
    chatCui gui;

    /**
     * Creates new form LOGS
     */
    public signInPanel(chatCui gui,ClientInputHandler cih) {
        initComponents();
        imagePanel panel = new imagePanel(new ImageIcon(getClass().getResource("/login/bg.jpg")).getImage());
        this.gui=gui;
        this.handler=cih;

         ImageIcon i =new ImageIcon(getClass().getResource("/login/bg.jpg"));
        jLabel1.setIcon(i);


        // JFrame frame = new JFrame();
        //getContentPane().add(panel);
        //pack();
        setVisible(true);

        //jPanel1.setBackground(java.awt.Color.RGBtoHSB());
    }

    
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        state = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(25, 173, 250));
        jPanel1.setForeground(java.awt.SystemColor.textHighlight);
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setMaximumSize(new java.awt.Dimension(100, 32767));
        jPanel1.setName("Log In"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 503));

        jLabel1.setBackground(new java.awt.Color(21, 160, 250));
        jLabel1.setText("jLabel1");

        jPasswordField1.setBackground(new java.awt.Color(191, 225, 252));
        jPasswordField1.setForeground(new java.awt.Color(0, 170, 240));
        jPasswordField1.setText("password");
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusLost(evt);
            }
        });
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField1MouseClicked(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(21, 91, 141));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Sign In");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Create New Account.");
        jLabel2.setToolTipText("");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jTextField2.setBackground(new java.awt.Color(191, 225, 252));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 170, 240));
        jTextField2.setText("User Name");
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
        });

        state.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "available", "busy ", "away" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("sign in as");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                                    .addComponent(jPasswordField1))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(117, 117, 117)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(49, 49, 49)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusLost
        String str = jPasswordField1.getText();
        if (str.equals("")) {
            jPasswordField1.setText("password");
            jPasswordField1.setForeground(java.awt.Color.getHSBColor(0, 170, 240));
        } else {
            jPasswordField1.setText(str);
            jPasswordField1.setForeground(java.awt.Color.gray);
        }
    }//GEN-LAST:event_jPasswordField1FocusLost

    private void jPasswordField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MouseClicked
        String str = jPasswordField1.getText();
        if (str.equals("password")) {
            jPasswordField1.setText("");
            jPasswordField1.setForeground(java.awt.Color.gray);
            //[25,173,250]
        } else {
            jPasswordField1.setText(str);
            jPasswordField1.setForeground(java.awt.Color.gray);
        }
    }//GEN-LAST:event_jPasswordField1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        User u=new User();
        String p1 = new String(jPasswordField1.getText());
        String em1 = new String(jTextField2.getText());

        boolean pf=true;
        boolean emf =true;

        if(emf) {
            if(!u.setUserEmail(em1)) {
                emf = false;
                JOptionPane.showMessageDialog(null, "bad Email format");
            }
        }
        if(pf){
            if(!u.setUserPassword(p1)){
                JOptionPane.showMessageDialog(null, "bad Password format");
            }
        }

        if(pf && emf ){
            System.out.println(p1);
            System.out.println(em1);
            u.setUserState(state.getSelectedIndex());
            gui.setUser(u);
            System.out.println("ggggggg"+gui.user.getUserEmail());
            handler.signIn(u);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        CardLayout card=(CardLayout)gui.parentPanel.getLayout();
        card.show(gui.parentPanel, "signup");

    }//GEN-LAST:event_jLabel2MouseClicked

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        String str = jTextField2.getText();
        if (str.equals("")) {
            jTextField2.setText("User Name");
            jTextField2.setForeground(java.awt.Color.getHSBColor(0, 170, 240));
        } else {
            jTextField2.setText(str);
            jTextField2.setForeground(java.awt.Color.gray);
        }
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        String str = jTextField2.getText();
        if (str.equals("User Name")) {
            jTextField2.setText("");
            jTextField2.setForeground(java.awt.Color.gray);
        } else {
            jTextField2.setText(str);
            jTextField2.setForeground(java.awt.Color.gray);
        }
    }//GEN-LAST:event_jTextField2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JComboBox state;
    // End of variables declaration//GEN-END:variables
}

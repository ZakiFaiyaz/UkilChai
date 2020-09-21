/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lawyer;

import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class cancleRequest extends javax.swing.JFrame {

    /**
     * Creates new form cancleRequest
     */
    String[] nam = new String[10];
    String[] pic = new String[10];
    int[] rid = new int[10];
    int index = 0;
    String S;
    public cancleRequest(String s) {
        initComponents();
        S = s;
        showTheList();
    }

    private void showTheList(){
        String n, p;
        index = 0;
        Color c = new Color(153,153,153);
        Color c1 = new Color(240,240,240);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet rs = null;
            String query;
            query = "SELECT request.requestID, lawyerRegistration.lawyerPhoto, lawyerRegistration.lawyerName FROM lawyerRegistration, request WHERE lawyerRegistration.lawyerID = request.lawyerID and id ="+S;
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery();                
            while(rs.next()){
                rid[index] = rs.getInt("requestID");
                pic[index] = rs.getString("lawyerPhoto");
                nam[index] = rs.getString("lawyerName");
                index++;
            }
            if(index == 0){
                cann1.setForeground(c1);
                cann1.setText("You have sent no request so far");
                cann1.setForeground(c);
                canp1.setIcon(null);
                canc1.setForeground(c);
                cann2.setForeground(c);
                canp2.setIcon(null);
                canc2.setForeground(c);
                cann3.setForeground(c);
                canp3.setIcon(null);
                canc3.setForeground(c);
                cann4.setForeground(c);
                canp4.setIcon(null);
                canc4.setForeground(c);
                cann5.setForeground(c);
                canp5.setIcon(null);
                canc5.setForeground(c);
            }
            else if(index == 1){
                cann1.setForeground(c1);
                cann1.setText(nam[0]);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(pic[0]).getImage().getScaledInstance(canp1.getWidth(), canp1.getHeight(), Image.SCALE_SMOOTH));
                canp1.setIcon(imageIcon);
                canc1.setForeground(c1);
                cann2.setForeground(c);
                canp2.setIcon(null);
                canc2.setForeground(c);
                cann3.setForeground(c);
                canp3.setIcon(null);
                canc3.setForeground(c);
                cann4.setForeground(c);
                canp4.setIcon(null);
                canc4.setForeground(c);
                cann5.setForeground(c);
                canp5.setIcon(null);
                canc5.setForeground(c);
            }
            else if(index == 2){
                cann1.setForeground(c1);
                cann1.setText(nam[0]);
                cann2.setText(nam[1]);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(pic[0]).getImage().getScaledInstance(canp1.getWidth(), canp1.getHeight(), Image.SCALE_SMOOTH));
                canp1.setIcon(imageIcon);
                canc1.setForeground(c1);
                cann2.setForeground(c1);
                imageIcon = new ImageIcon(new ImageIcon(pic[1]).getImage().getScaledInstance(canp2.getWidth(), canp2.getHeight(), Image.SCALE_SMOOTH));
                canp2.setIcon(imageIcon);
                canc2.setForeground(c1);
                cann3.setForeground(c);
                canp3.setIcon(null);
                canc3.setForeground(c);
                cann4.setForeground(c);
                canp4.setIcon(null);
                canc4.setForeground(c);
                cann5.setForeground(c);
                canp5.setIcon(null);
                canc5.setForeground(c);
            }
            else if(index == 3){
                cann1.setForeground(c1);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(pic[0]).getImage().getScaledInstance(canp1.getWidth(), canp1.getHeight(), Image.SCALE_SMOOTH));
                canp1.setIcon(imageIcon);
                canc1.setForeground(c1);
                cann2.setForeground(c1);
                imageIcon = new ImageIcon(new ImageIcon(pic[1]).getImage().getScaledInstance(canp2.getWidth(), canp2.getHeight(), Image.SCALE_SMOOTH));
                canp2.setIcon(imageIcon);
                canc2.setForeground(c1);
                cann3.setForeground(c1);
                cann1.setText(nam[0]);
                cann2.setText(nam[1]);
                cann3.setText(nam[2]);
                imageIcon = new ImageIcon(new ImageIcon(pic[2]).getImage().getScaledInstance(canp3.getWidth(), canp3.getHeight(), Image.SCALE_SMOOTH));
                canp3.setIcon(imageIcon);
                canc3.setForeground(c1);
                cann4.setForeground(c);
                canp4.setIcon(null);
                canc4.setForeground(c);
                cann5.setForeground(c);
                canp5.setIcon(null);
                canc5.setForeground(c);
            }
            else if(index == 4){
                cann1.setForeground(c1);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(pic[0]).getImage().getScaledInstance(canp1.getWidth(), canp1.getHeight(), Image.SCALE_SMOOTH));
                canp1.setIcon(imageIcon);
                canc1.setForeground(c1);
                cann2.setForeground(c1);
                imageIcon = new ImageIcon(new ImageIcon(pic[1]).getImage().getScaledInstance(canp2.getWidth(), canp2.getHeight(), Image.SCALE_SMOOTH));
                canp2.setIcon(imageIcon);
                canc2.setForeground(c1);
                cann3.setForeground(c1);
                imageIcon = new ImageIcon(new ImageIcon(pic[2]).getImage().getScaledInstance(canp3.getWidth(), canp3.getHeight(), Image.SCALE_SMOOTH));
                canp3.setIcon(imageIcon);
                canc3.setForeground(c1);
                cann4.setForeground(c1);
                cann1.setText(nam[0]);
                cann2.setText(nam[1]);
                cann3.setText(nam[2]);
                cann4.setText(nam[3]);
                imageIcon = new ImageIcon(new ImageIcon(pic[3]).getImage().getScaledInstance(canp4.getWidth(), canp4.getHeight(), Image.SCALE_SMOOTH));
                canp4.setIcon(imageIcon);
                canc4.setForeground(c1);
                cann5.setForeground(c);
                canp5.setIcon(null);
                canc5.setForeground(c);
            }
            else{
                cann1.setForeground(c1);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(pic[0]).getImage().getScaledInstance(canp1.getWidth(), canp1.getHeight(), Image.SCALE_SMOOTH));
                canp1.setIcon(imageIcon);
                canc1.setForeground(c1);
                cann2.setForeground(c1);
                imageIcon = new ImageIcon(new ImageIcon(pic[1]).getImage().getScaledInstance(canp2.getWidth(), canp2.getHeight(), Image.SCALE_SMOOTH));
                canp2.setIcon(imageIcon);
                canc2.setForeground(c1);
                cann3.setForeground(c1);
                imageIcon = new ImageIcon(new ImageIcon(pic[2]).getImage().getScaledInstance(canp3.getWidth(), canp3.getHeight(), Image.SCALE_SMOOTH));
                canp3.setIcon(imageIcon);
                canc3.setForeground(c1);
                cann4.setForeground(c1);
                cann1.setText(nam[0]);
                cann2.setText(nam[1]);
                cann3.setText(nam[2]);
                cann4.setText(nam[3]);
                cann5.setText(nam[4]);
                imageIcon = new ImageIcon(new ImageIcon(pic[3]).getImage().getScaledInstance(canp4.getWidth(), canp4.getHeight(), Image.SCALE_SMOOTH));
                canp4.setIcon(imageIcon);
                canc4.setForeground(c1);
                cann5.setForeground(c1);
                imageIcon = new ImageIcon(new ImageIcon(pic[4]).getImage().getScaledInstance(canp5.getWidth(), canp5.getHeight(), Image.SCALE_SMOOTH));
                canp5.setIcon(imageIcon);
                canc5.setForeground(c1);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
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
        closing = new javax.swing.JLabel();
        canp5 = new javax.swing.JLabel();
        canp1 = new javax.swing.JLabel();
        canp2 = new javax.swing.JLabel();
        canp3 = new javax.swing.JLabel();
        canp4 = new javax.swing.JLabel();
        cann5 = new javax.swing.JLabel();
        canc5 = new javax.swing.JLabel();
        cann1 = new javax.swing.JLabel();
        cann2 = new javax.swing.JLabel();
        cann3 = new javax.swing.JLabel();
        cann4 = new javax.swing.JLabel();
        canc1 = new javax.swing.JLabel();
        canc2 = new javax.swing.JLabel();
        canc3 = new javax.swing.JLabel();
        canc4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closing.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        closing.setForeground(new java.awt.Color(255, 255, 255));
        closing.setText("X");
        closing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closingMouseClicked(evt);
            }
        });
        jPanel1.add(closing, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, 30));
        jPanel1.add(canp5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 700, 80, 100));
        jPanel1.add(canp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 80, 100));
        jPanel1.add(canp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 80, 100));
        jPanel1.add(canp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 80, 100));
        jPanel1.add(canp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 80, 100));

        cann5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cann5.setText("jLabel7");
        jPanel1.add(cann5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 730, 200, 40));

        canc5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        canc5.setForeground(new java.awt.Color(255, 0, 102));
        canc5.setText("Cancle");
        canc5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canc5MouseClicked(evt);
            }
        });
        jPanel1.add(canc5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 730, -1, -1));

        cann1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cann1.setText("jLabel7");
        jPanel1.add(cann1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 220, 40));

        cann2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cann2.setText("jLabel7");
        jPanel1.add(cann2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 210, 40));

        cann3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cann3.setText("jLabel7");
        jPanel1.add(cann3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 220, 40));

        cann4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cann4.setText("jLabel7");
        jPanel1.add(cann4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 570, 200, 40));

        canc1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        canc1.setForeground(new java.awt.Color(255, 0, 102));
        canc1.setText("Cancle");
        canc1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canc1MouseClicked(evt);
            }
        });
        jPanel1.add(canc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, -1));

        canc2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        canc2.setForeground(new java.awt.Color(255, 0, 102));
        canc2.setText("Cancle");
        canc2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canc2MouseClicked(evt);
            }
        });
        jPanel1.add(canc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, -1, -1));

        canc3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        canc3.setForeground(new java.awt.Color(255, 0, 102));
        canc3.setText("Cancle");
        canc3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canc3MouseClicked(evt);
            }
        });
        jPanel1.add(canc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, -1, -1));

        canc4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        canc4.setForeground(new java.awt.Color(255, 0, 102));
        canc4.setText("Cancle");
        canc4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canc4MouseClicked(evt);
            }
        });
        jPanel1.add(canc4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 570, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 830));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closingMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_closingMouseClicked

    private void deleteRequest(int n){
        String ID = "", NAM = "";
        int limit = 0;
        if(n == 1){
            ID = String.valueOf(rid[0]);
            NAM = cann1.getText();}
        else if(n == 2){
            ID = String.valueOf(rid[1]);
            NAM = cann2.getText();}
        else if(n == 3){
            ID = String.valueOf(rid[2]);
            NAM = cann3.getText();}
        else if(n == 4){
            ID = String.valueOf(rid[3]);
            NAM = cann4.getText();}
            else{
            ID = String.valueOf(rid[4]);
            NAM = cann5.getText();}
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            String query;
            query = "Delete From request where requestID = "+ID; 
            PreparedStatement pst = connection.prepareStatement(query);
            pst.executeUpdate();
            PreparedStatement prepStmnt = null;
            ResultSet rs = null;
            query = "select sentRequest from userRegistration where id = "+S;
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery();                
            while(rs.next()){
                limit = rs.getInt("sentRequest");
            }
            limit--;
            String q = String.valueOf(limit);
            query = "update userRegistration set  sentRequest ="+q+" where id = "+S;
            pst = connection.prepareStatement(query);
            pst.executeUpdate();
            //lawyer
            prepStmnt = null;rs = null;
            query = "select limit from lawyerRegistration where lawyerName = '"+NAM+"'";
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery();                
            while(rs.next()){
                limit = rs.getInt("limit");
            }
            limit--;
            q = String.valueOf(limit);
            query = "update lawyerRegistration set  limit ="+q+" where lawyerName = '"+NAM+"'";
            pst = connection.prepareStatement(query);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted Successfully");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void canc1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canc1MouseClicked
        // TODO add your handling code here:
        deleteRequest(1);
        showTheList();
    }//GEN-LAST:event_canc1MouseClicked

    private void canc2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canc2MouseClicked
        // TODO add your handling code here:
        deleteRequest(2);
        showTheList();
    }//GEN-LAST:event_canc2MouseClicked

    private void canc3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canc3MouseClicked
        // TODO add your handling code here:
        deleteRequest(3);
        showTheList();
    }//GEN-LAST:event_canc3MouseClicked

    private void canc4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canc4MouseClicked
        // TODO add your handling code here:
        deleteRequest(4);
        showTheList();
    }//GEN-LAST:event_canc4MouseClicked

    private void canc5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canc5MouseClicked
        // TODO add your handling code here:
        deleteRequest(5);
        showTheList();
    }//GEN-LAST:event_canc5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(cancleRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cancleRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cancleRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cancleRequest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cancleRequest("s").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel canc1;
    private javax.swing.JLabel canc2;
    private javax.swing.JLabel canc3;
    private javax.swing.JLabel canc4;
    private javax.swing.JLabel canc5;
    private javax.swing.JLabel cann1;
    private javax.swing.JLabel cann2;
    private javax.swing.JLabel cann3;
    private javax.swing.JLabel cann4;
    private javax.swing.JLabel cann5;
    private javax.swing.JLabel canp1;
    private javax.swing.JLabel canp2;
    private javax.swing.JLabel canp3;
    private javax.swing.JLabel canp4;
    private javax.swing.JLabel canp5;
    private javax.swing.JLabel closing;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lawyer;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Asus
 */
public class LawyerDashboard extends javax.swing.JFrame {

    /**
     * Creates new form LawyerDashboard
     */
    Color e = new Color(204,255,255);//exception
    Color c = new Color(204,204,255);
    String fileName = null;
    String clientName, lawyerName, lawyerType;
    int clientID, lawyerID, CaseID;
    String[] pic5 = new String[50];
    String[] name5 = new String[50];
    String[] feed5 = new String[50];
    int[] id5 = new int[50];
    String[] case5 = new String[50];
    int index = 0, clicking = 1;

    public LawyerDashboard(String clientName, String lawyerName, String lawyerType, int clientID, int lawyerID, int CaseID) {
        this.clientName = clientName;
        this.lawyerName = lawyerName;
        this.lawyerType = lawyerType;
        this.clientID = clientID;
        this.lawyerID = lawyerID;
        this.CaseID = CaseID;
    }
    
    public LawyerDashboard(String name) {
        lawyerName = name;
        initComponents();
        String l = "D:\\Icon\\logo.png";
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(l).getImage().getScaledInstance(dan.getWidth(), dan.getHeight(), Image.SCALE_SMOOTH));
        dan.setIcon(imageIcon);
        imageIcon = new ImageIcon(new ImageIcon(l).getImage().getScaledInstance(bam.getWidth(), bam.getHeight(), Image.SCALE_SMOOTH));
        bam.setIcon(imageIcon);
        home.setBackground(e);
        setHome();
    }
    private void setHome(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet dataset = null;
            String query = "select lawyerPhoto, totalCases, win, loss, rating, lawyerID, typ from lawyerRegistration where lawyerName = '"+lawyerName+"'";
            prepStmnt = connection.prepareStatement(query);
            dataset = prepStmnt.executeQuery();
            String z;int u = 0, n = 0, res = 0;
            hname.setText(lawyerName);
            while(dataset.next()){
                lawyerID = dataset.getInt("lawyerID");
                lawyerType = dataset.getString("typ");
                z = dataset.getString("lawyerPhoto");
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(z).getImage().getScaledInstance(hpic.getWidth(), hpic.getHeight(), Image.SCALE_SMOOTH));
                hpic.setIcon(imageIcon);
                n = dataset.getInt("totalCases");
                htotal.setText(String.valueOf(n));
                u = dataset.getInt("win");
                hwin.setText(String.valueOf(u));
                res = dataset.getInt("loss");
                hloss.setText(String.valueOf(res));
                float w = dataset.getFloat("rating");
                hrating.setText(String.valueOf(w));
            }
            res = (u*100)/n;
            System.out.println(res);
            z = String.valueOf(res);
            hratio.setText(z+"%");
            index = 0;
            prepStmnt = null;dataset = null;
            query = "select cases.caseID, userRegistration.id, photo, userName from userRegistration,cases where userRegistration.id = cases.id and cases.lawyerID = "+String.valueOf(lawyerID)+" and done = 0";
            prepStmnt = connection.prepareStatement(query);
            dataset = prepStmnt.executeQuery();
            while(dataset.next()){
                name5[index] = dataset.getString("userName");
                pic5[index] = dataset.getString("photo");
                id5[index] = dataset.getInt("id");
                case5[index] = String.valueOf(dataset.getInt("caseID"));
                index++;
            }
            index--;
            setCases(0, index);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void setCases(int x, int y){
        int a = y-x+1;
        Color c = new Color(153,153,255);
        if(a == 0){
            c1.setForeground(Color.white);c1.setText("You have no cases now");homeNext.setForeground(c);
            c2.setForeground(c);c3.setForeground(c);c4.setForeground(c);c5.setForeground(c);case1.setText(case5[x]);
        }
        else if(a == 1){
            c1.setForeground(Color.white);c1.setText(name5[x]+" has an case under you");hid1.setText(String.valueOf(id5[x]));
            c2.setForeground(c);c3.setForeground(c);c4.setForeground(c);c5.setForeground(c);homeNext.setForeground(c);
            case1.setText(case5[x]);
        }
        else if(a == 2){
            c1.setForeground(Color.white);c1.setText(name5[x]+" has an case under you");hid1.setText(String.valueOf(id5[x]));
            c2.setForeground(Color.white);c2.setText(name5[x+1]+" has an case under you");hid2.setText(String.valueOf(id5[x+1]));
            c3.setForeground(c);c4.setForeground(c);c5.setForeground(c);homeNext.setForeground(c);
            case1.setText(case5[x]);case2.setText(case5[x+1]);
        }
        else if(a == 3){
            c1.setForeground(Color.white);c1.setText(name5[x]+" has an case under you");hid1.setText(String.valueOf(id5[x]));
            c2.setForeground(Color.white);c2.setText(name5[x+1]+" has an case under you");hid2.setText(String.valueOf(id5[x+1]));
            c3.setForeground(Color.white);c3.setText(name5[x+2]+" has an case under you");hid3.setText(String.valueOf(id5[x+2]));
            c4.setForeground(c);c5.setForeground(c);homeNext.setForeground(c);
        case1.setText(case5[x]);case2.setText(case5[x+1]);case3.setText(case5[x+2]);
        }
        else if(a == 4){
            c1.setForeground(Color.white);c1.setText(name5[x]+" has an case under you");hid1.setText(String.valueOf(id5[x]));
            c2.setForeground(Color.white);c2.setText(name5[x+1]+" has an case under you");hid2.setText(String.valueOf(id5[x+1]));
            c3.setForeground(Color.white);c3.setText(name5[x+2]+" has an case under you");hid3.setText(String.valueOf(id5[x+2]));
            c4.setForeground(Color.white);c4.setText(name5[x+3]+" has an case under you");hid4.setText(String.valueOf(id5[x+3]));
            c5.setForeground(c);homeNext.setForeground(c);
            case1.setText(case5[x]);case2.setText(case5[x+1]);case3.setText(case5[x+2]);case4.setText(case5[x+3]);
        }
        else if(a == 5){
            c1.setForeground(Color.white);c1.setText(name5[x]+" has an case under you");hid1.setText(String.valueOf(id5[x]));
            c2.setForeground(Color.white);c2.setText(name5[x+1]+" has an case under you");hid2.setText(String.valueOf(id5[x+1]));
            c3.setForeground(Color.white);c3.setText(name5[x+2]+" has an case under you");hid3.setText(String.valueOf(id5[x+2]));
            c4.setForeground(Color.white);c4.setText(name5[x+3]+" has an case under you");hid4.setText(String.valueOf(id5[x+3]));
            c5.setForeground(Color.white);c5.setText(name5[x+4]+" has an case under you");hid5.setText(String.valueOf(id5[x+4]));
            homeNext.setForeground(c);
            case1.setText(case5[x]);case2.setText(case5[x+1]);case3.setText(case5[x+2]);case4.setText(case5[x+3]);case6.setText(case5[x+4]);
        }
        else{
            c1.setForeground(Color.white);c1.setText(name5[x]+" has an case under you");hid1.setText(String.valueOf(id5[x]));
            c2.setForeground(Color.white);c2.setText(name5[x+1]+" has an case under you");hid2.setText(String.valueOf(id5[x+1]));
            c3.setForeground(Color.white);c3.setText(name5[x+2]+" has an case under you");hid3.setText(String.valueOf(id5[x+2]));
            c4.setForeground(Color.white);c4.setText(name5[x+3]+" has an case under you");hid4.setText(String.valueOf(id5[x+3]));
            c5.setForeground(Color.white);c5.setText(name5[x+4]+" has an case under you");hid5.setText(String.valueOf(id5[x+4]));
            homeNext.setForeground(Color.BLUE);
            case1.setText(case5[x]);case2.setText(case5[x+1]);case3.setText(case5[x+2]);case4.setText(case5[x+3]);case6.setText(case5[x+4]);
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
        jPanel2 = new javax.swing.JPanel();
        bam = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dan = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        home = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        feedback = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        request = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        settings = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelCenter = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        homeNext = new javax.swing.JLabel();
        feedbackHead2 = new javax.swing.JLabel();
        hname = new javax.swing.JLabel();
        hrating = new javax.swing.JLabel();
        reqn5 = new javax.swing.JLabel();
        c2 = new javax.swing.JLabel();
        htotal = new javax.swing.JLabel();
        reqn7 = new javax.swing.JLabel();
        hwin = new javax.swing.JLabel();
        reqn8 = new javax.swing.JLabel();
        hratio = new javax.swing.JLabel();
        hpic = new javax.swing.JLabel();
        reqn9 = new javax.swing.JLabel();
        hloss = new javax.swing.JLabel();
        reqn10 = new javax.swing.JLabel();
        c3 = new javax.swing.JLabel();
        c5 = new javax.swing.JLabel();
        c1 = new javax.swing.JLabel();
        c4 = new javax.swing.JLabel();
        hid1 = new javax.swing.JLabel();
        hid3 = new javax.swing.JLabel();
        hid5 = new javax.swing.JLabel();
        hid4 = new javax.swing.JLabel();
        hid2 = new javax.swing.JLabel();
        case1 = new javax.swing.JLabel();
        case2 = new javax.swing.JLabel();
        case3 = new javax.swing.JLabel();
        case4 = new javax.swing.JLabel();
        case6 = new javax.swing.JLabel();
        feedbackPanel = new javax.swing.JPanel();
        feedbackHead = new javax.swing.JLabel();
        feedNext = new javax.swing.JLabel();
        f2 = new javax.swing.JLabel();
        f3 = new javax.swing.JLabel();
        f4 = new javax.swing.JLabel();
        f5 = new javax.swing.JLabel();
        f1 = new javax.swing.JLabel();
        requestPanel = new javax.swing.JPanel();
        reqn1 = new javax.swing.JLabel();
        reqd2 = new javax.swing.JLabel();
        reqp3 = new javax.swing.JLabel();
        reqp1 = new javax.swing.JLabel();
        reqp2 = new javax.swing.JLabel();
        reqn2 = new javax.swing.JLabel();
        reqn3 = new javax.swing.JLabel();
        reqHead = new javax.swing.JLabel();
        reqd3 = new javax.swing.JLabel();
        reqd1 = new javax.swing.JLabel();
        settingsPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        adres = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        nam = new javax.swing.JTextField();
        pass = new javax.swing.JTextField();
        mail = new javax.swing.JTextField();
        phn = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        picN = new javax.swing.JLabel();
        button2 = new java.awt.Button();
        txtN = new javax.swing.JLabel();
        UpdateLaw = new javax.swing.JLabel();
        clientPanel = new javax.swing.JPanel();
        cID = new javax.swing.JLabel();
        cMail = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cPhn = new javax.swing.JTextField();
        cName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        cMsg = new javax.swing.JTextArea();
        cPhoto = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cancleRequest = new java.awt.Button();
        acceptRequest = new java.awt.Button();
        jLabel23 = new javax.swing.JLabel();
        casePanel = new javax.swing.JPanel();
        casePic = new javax.swing.JLabel();
        reqHead1 = new javax.swing.JLabel();
        cphn = new javax.swing.JLabel();
        cnam = new javax.swing.JLabel();
        cmail = new javax.swing.JLabel();
        done = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        bam.setText("jLabel1");

        jLabel12.setBackground(new java.awt.Color(204, 255, 255));
        jLabel12.setFont(new java.awt.Font("Sitka Display", 0, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 255, 255));
        jLabel12.setText("UKIL CHAI");

        dan.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(479, 479, 479)
                .addComponent(bam, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(345, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bam, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(dan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        home.setBackground(new java.awt.Color(204, 204, 255));
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LawyerDashboard.this.mouseClicked(evt);
            }
        });
        home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lawyer/homeIcon.png"))); // NOI18N
        home.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 60, 60));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel2.setText("Home");
        home.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 80, 20));

        jPanel3.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 110));

        feedback.setBackground(new java.awt.Color(204, 204, 255));
        feedback.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LawyerDashboard.this.mouseClicked(evt);
            }
        });
        feedback.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lawyer/feedbackIcon.png"))); // NOI18N
        feedback.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 60, 60));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel4.setText("Feedback");
        feedback.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 120, 20));

        jPanel3.add(feedback, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 210, 110));

        request.setBackground(new java.awt.Color(204, 204, 255));
        request.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LawyerDashboard.this.mouseClicked(evt);
            }
        });
        request.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lawyer/requestIcon.png"))); // NOI18N
        request.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 60, 60));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel6.setText("Requests");
        request.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 120, 20));

        jPanel3.add(request, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 210, 110));

        settings.setBackground(new java.awt.Color(204, 204, 255));
        settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LawyerDashboard.this.mouseClicked(evt);
            }
        });
        settings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lawyer/settingsIcon.png"))); // NOI18N
        settings.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 60, 60));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel8.setText("Settings");
        settings.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 100, 20));

        jPanel3.add(settings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 210, 110));

        logout.setBackground(new java.awt.Color(204, 204, 255));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LawyerDashboard.this.mouseClicked(evt);
            }
        });
        logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lawyer/logoutIcon.png"))); // NOI18N
        logout.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 60, 60));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel10.setText("Log out");
        logout.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 90, 40));

        jPanel3.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 210, 110));

        jPanel1.add(jPanel3, java.awt.BorderLayout.LINE_START);

        panelCenter.setLayout(new java.awt.CardLayout());

        homePanel.setBackground(new java.awt.Color(153, 153, 255));
        homePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        homeNext.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        homeNext.setForeground(new java.awt.Color(0, 0, 255));
        homeNext.setText("Next");
        homeNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeNextMouseClicked(evt);
            }
        });
        homePanel.add(homeNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 510, -1, -1));

        feedbackHead2.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        feedbackHead2.setForeground(new java.awt.Color(51, 0, 51));
        feedbackHead2.setText("Welcome");
        homePanel.add(feedbackHead2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 170, 30));

        hname.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        hname.setForeground(new java.awt.Color(0, 102, 102));
        hname.setText("Welcome");
        homePanel.add(hname, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 330, 30));

        hrating.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        hrating.setForeground(new java.awt.Color(255, 0, 51));
        hrating.setText("0");
        homePanel.add(hrating, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 50, 30));

        reqn5.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        reqn5.setText("Success rate : ");
        homePanel.add(reqn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 150, 30));

        c2.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        c2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c2MouseClicked(evt);
            }
        });
        homePanel.add(c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 750, 30));

        htotal.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        htotal.setForeground(new java.awt.Color(255, 255, 255));
        htotal.setText("0");
        homePanel.add(htotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 50, 30));

        reqn7.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        reqn7.setText("Win : ");
        homePanel.add(reqn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 60, 30));

        hwin.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        hwin.setForeground(new java.awt.Color(255, 255, 255));
        hwin.setText("0");
        homePanel.add(hwin, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 50, 30));

        reqn8.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        reqn8.setText("Loss :");
        homePanel.add(reqn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 60, 30));

        hratio.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        hratio.setForeground(new java.awt.Color(255, 255, 255));
        hratio.setText("0");
        homePanel.add(hratio, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 50, 30));

        hpic.setText("jLabel12");
        homePanel.add(hpic, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 30, 140, 180));

        reqn9.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        reqn9.setText("Current rating : ");
        homePanel.add(reqn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 150, 30));

        hloss.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        hloss.setForeground(new java.awt.Color(255, 255, 255));
        hloss.setText("0");
        homePanel.add(hloss, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 50, 30));

        reqn10.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        reqn10.setText("Total cases : ");
        homePanel.add(reqn10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 130, 30));

        c3.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        c3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c3MouseClicked(evt);
            }
        });
        homePanel.add(c3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 770, 30));

        c5.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        c5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c5MouseClicked(evt);
            }
        });
        homePanel.add(c5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 780, 30));

        c1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        c1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c1MouseClicked(evt);
            }
        });
        homePanel.add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 740, 30));

        c4.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        c4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c4MouseClicked(evt);
            }
        });
        homePanel.add(c4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 780, 30));

        hid1.setForeground(new java.awt.Color(153, 153, 255));
        hid1.setText("jLabel12");
        homePanel.add(hid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        hid3.setForeground(new java.awt.Color(153, 153, 255));
        hid3.setText("jLabel12");
        homePanel.add(hid3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, -1, -1));

        hid5.setForeground(new java.awt.Color(153, 153, 255));
        hid5.setText("jLabel12");
        homePanel.add(hid5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        hid4.setForeground(new java.awt.Color(153, 153, 255));
        hid4.setText("jLabel12");
        homePanel.add(hid4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, -1, -1));

        hid2.setForeground(new java.awt.Color(153, 153, 255));
        hid2.setText("jLabel12");
        homePanel.add(hid2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, -1, -1));

        case1.setForeground(new java.awt.Color(153, 153, 255));
        case1.setText("jLabel12");
        homePanel.add(case1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        case2.setForeground(new java.awt.Color(153, 153, 255));
        case2.setText("jLabel12");
        homePanel.add(case2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        case3.setForeground(new java.awt.Color(153, 153, 255));
        case3.setText("jLabel12");
        homePanel.add(case3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, -1));

        case4.setForeground(new java.awt.Color(153, 153, 255));
        case4.setText("jLabel12");
        homePanel.add(case4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, -1));

        case6.setForeground(new java.awt.Color(153, 153, 255));
        case6.setText("jLabel12");
        homePanel.add(case6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        panelCenter.add(homePanel, "card2");

        feedbackPanel.setBackground(new java.awt.Color(153, 153, 255));
        feedbackPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        feedbackHead.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        feedbackHead.setForeground(new java.awt.Color(51, 0, 51));
        feedbackHead.setText("Your Feedbacks");
        feedbackPanel.add(feedbackHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 450, 30));

        feedNext.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        feedNext.setForeground(new java.awt.Color(0, 0, 255));
        feedNext.setText("Next");
        feedNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                feedNextMouseClicked(evt);
            }
        });
        feedbackPanel.add(feedNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 500, 50, 30));

        f2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        f2.setForeground(new java.awt.Color(255, 255, 255));
        f2.setText("See details");
        f2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f2MouseClicked(evt);
            }
        });
        feedbackPanel.add(f2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, 30));

        f3.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        f3.setForeground(new java.awt.Color(255, 255, 255));
        f3.setText("See details");
        f3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f3MouseClicked(evt);
            }
        });
        feedbackPanel.add(f3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, 30));

        f4.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        f4.setForeground(new java.awt.Color(255, 255, 255));
        f4.setText("See details");
        f4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f4MouseClicked(evt);
            }
        });
        feedbackPanel.add(f4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, -1, 30));

        f5.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        f5.setForeground(new java.awt.Color(255, 255, 255));
        f5.setText("See details");
        f5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f5MouseClicked(evt);
            }
        });
        feedbackPanel.add(f5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, -1, 30));

        f1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        f1.setForeground(new java.awt.Color(255, 255, 255));
        f1.setText("See details");
        f1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f1MouseClicked(evt);
            }
        });
        feedbackPanel.add(f1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, 30));

        panelCenter.add(feedbackPanel, "card3");

        requestPanel.setBackground(new java.awt.Color(153, 153, 255));
        requestPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        reqn1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        reqn1.setText("Your case Request");
        requestPanel.add(reqn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 180, 30));

        reqd2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        reqd2.setForeground(new java.awt.Color(255, 255, 255));
        reqd2.setText("See details");
        reqd2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reqd2MouseClicked(evt);
            }
        });
        requestPanel.add(reqd2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 280, -1, 30));
        requestPanel.add(reqp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 100, 110));
        requestPanel.add(reqp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 90, 110));
        requestPanel.add(reqp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 90, 110));

        reqn2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        reqn2.setText("Your case Request");
        requestPanel.add(reqn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 180, 30));

        reqn3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        reqn3.setText("Your case Request");
        requestPanel.add(reqn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, 180, 30));

        reqHead.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        reqHead.setForeground(new java.awt.Color(51, 0, 51));
        reqHead.setText("Your case Requests");
        requestPanel.add(reqHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 450, 30));

        reqd3.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        reqd3.setForeground(new java.awt.Color(255, 255, 255));
        reqd3.setText("See details");
        reqd3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reqd3MouseClicked(evt);
            }
        });
        requestPanel.add(reqd3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, -1, 30));

        reqd1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        reqd1.setForeground(new java.awt.Color(255, 255, 255));
        reqd1.setText("See details");
        reqd1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reqd1MouseClicked(evt);
            }
        });
        requestPanel.add(reqd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, -1, 30));

        panelCenter.add(requestPanel, "card4");

        settingsPanel.setBackground(new java.awt.Color(153, 153, 255));
        settingsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel11.setText("Address");
        settingsPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 100, 30));

        adres.setBackground(new java.awt.Color(153, 153, 255));
        adres.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        adres.setBorder(null);
        settingsPanel.add(adres, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 280, 30));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel14.setText("User Name");
        settingsPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 100, 30));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel18.setText("Password");
        settingsPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 100, 30));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel19.setText("Email");
        settingsPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 100, 30));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel20.setText("Phone");
        settingsPanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 100, 30));

        nam.setBackground(new java.awt.Color(153, 153, 255));
        nam.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        nam.setBorder(null);
        settingsPanel.add(nam, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 280, 30));

        pass.setBackground(new java.awt.Color(153, 153, 255));
        pass.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        pass.setBorder(null);
        settingsPanel.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 280, 30));

        mail.setBackground(new java.awt.Color(153, 153, 255));
        mail.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        mail.setBorder(null);
        settingsPanel.add(mail, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 280, 30));

        phn.setBackground(new java.awt.Color(153, 153, 255));
        phn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        phn.setBorder(null);
        settingsPanel.add(phn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 280, 30));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        settingsPanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 300, 10));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        settingsPanel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 300, 10));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        settingsPanel.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 300, 10));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        settingsPanel.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 300, 20));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        settingsPanel.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 300, 10));
        settingsPanel.add(picN, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 150, 190));

        button2.setBackground(new java.awt.Color(153, 153, 255));
        button2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setLabel("Upload");
        button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button2MouseClicked(evt);
            }
        });
        settingsPanel.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, 110, 40));

        txtN.setForeground(new java.awt.Color(153, 153, 255));
        txtN.setText("abc");
        settingsPanel.add(txtN, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 390, 260, -1));

        UpdateLaw.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        UpdateLaw.setForeground(new java.awt.Color(255, 255, 255));
        UpdateLaw.setText("UPDATE");
        UpdateLaw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateLawMouseClicked(evt);
            }
        });
        settingsPanel.add(UpdateLaw, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 470, 150, 50));

        panelCenter.add(settingsPanel, "card5");

        clientPanel.setBackground(new java.awt.Color(153, 153, 255));
        clientPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cID.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cID.setForeground(new java.awt.Color(153, 153, 255));
        cID.setText("Email");
        clientPanel.add(cID, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 100, 30));

        cMail.setBackground(new java.awt.Color(153, 153, 255));
        cMail.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cMail.setBorder(null);
        clientPanel.add(cMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 270, 30));

        jLabel24.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel24.setText("Case details");
        clientPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 130, 30));

        jLabel25.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel25.setText("User Name");
        clientPanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 100, 30));

        jLabel26.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel26.setText("User Name");
        clientPanel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 100, 30));

        cPhn.setBackground(new java.awt.Color(153, 153, 255));
        cPhn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cPhn.setBorder(null);
        clientPanel.add(cPhn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 270, 30));

        cName.setBackground(new java.awt.Color(153, 153, 255));
        cName.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cName.setBorder(null);
        clientPanel.add(cName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 280, 30));

        cMsg.setBackground(new java.awt.Color(204, 204, 255));
        cMsg.setColumns(20);
        cMsg.setRows(5);
        jScrollPane1.setViewportView(cMsg);

        clientPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 410, 160));
        clientPanel.add(cPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, 150, 180));

        jLabel28.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel28.setText("Phone");
        clientPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 100, 30));

        cancleRequest.setBackground(new java.awt.Color(255, 204, 204));
        cancleRequest.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cancleRequest.setForeground(new java.awt.Color(255, 51, 51));
        cancleRequest.setLabel("Cancle");
        cancleRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleRequestActionPerformed(evt);
            }
        });
        clientPanel.add(cancleRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 420, 130, 40));

        acceptRequest.setBackground(new java.awt.Color(204, 255, 255));
        acceptRequest.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        acceptRequest.setForeground(new java.awt.Color(0, 204, 0));
        acceptRequest.setLabel("Accept");
        acceptRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptRequestActionPerformed(evt);
            }
        });
        clientPanel.add(acceptRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 420, 130, 40));

        jLabel23.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel23.setText("Email");
        clientPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 100, 30));

        panelCenter.add(clientPanel, "card6");

        casePanel.setBackground(new java.awt.Color(153, 153, 255));
        casePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        casePic.setText("jLabel12");
        casePanel.add(casePic, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 170, 240));

        reqHead1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        reqHead1.setForeground(new java.awt.Color(153, 255, 255));
        reqHead1.setText("Client Details");
        casePanel.add(reqHead1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 230, 30));

        cphn.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cphn.setText("User Name");
        casePanel.add(cphn, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 560, 30));

        cnam.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cnam.setText("User Name");
        casePanel.add(cnam, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 340, 30));

        cmail.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cmail.setText("User Name");
        casePanel.add(cmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 420, 30));

        done.setBackground(new java.awt.Color(153, 255, 153));
        done.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        done.setForeground(new java.awt.Color(255, 255, 255));
        done.setLabel("Mark as done");
        done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneActionPerformed(evt);
            }
        });
        casePanel.add(done, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, 270, 50));

        panelCenter.add(casePanel, "card7");

        jPanel1.add(panelCenter, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 650));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClicked
        // TODO add your handling code here:
        if(evt.getSource() == home){
            homePanel.setVisible(true);
            feedbackPanel.setVisible(false);
            requestPanel.setVisible(false);
            settingsPanel.setVisible(false);
            clientPanel.setVisible(false);
            casePanel.setVisible(false);
            home.setBackground(e);feedback.setBackground(c);request.setBackground(c);settings.setBackground(c);
            setHome();
        }
        else if(evt.getSource() == feedback){
            homePanel.setVisible(false);
            feedbackPanel.setVisible(true);
            requestPanel.setVisible(false);
            settingsPanel.setVisible(false);
            clientPanel.setVisible(false);
            casePanel.setVisible(false);
            home.setBackground(c);feedback.setBackground(e);request.setBackground(c);settings.setBackground(c);
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet dataset = null;
            String query = "select feedback from cases where feedback is not null and lawyerID = "+String.valueOf(lawyerID);
            prepStmnt = connection.prepareStatement(query);
            dataset = prepStmnt.executeQuery();
            index = 0;
            while(dataset.next()){
                feed5[index] = dataset.getString("feedback");
                index++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
            index--;
            clicking = 1;
            setFeedback(0, index);
        }
        else if(evt.getSource() == request){
            homePanel.setVisible(false);
            feedbackPanel.setVisible(false);
            requestPanel.setVisible(true);
            settingsPanel.setVisible(false);
            clientPanel.setVisible(false);
            casePanel.setVisible(false);
            home.setBackground(c);feedback.setBackground(c);request.setBackground(e);settings.setBackground(c);
            setRequest();
        }
        else if(evt.getSource() == settings){
            homePanel.setVisible(false);
            feedbackPanel.setVisible(false);
            requestPanel.setVisible(false);
            settingsPanel.setVisible(true);
            clientPanel.setVisible(false);
            casePanel.setVisible(false);
            home.setBackground(c);feedback.setBackground(c);request.setBackground(c);settings.setBackground(e);
            setNotification();
        }
        else if(evt.getSource() == logout){
            this.setVisible(false);
            new LawyerLogin().setVisible(true);
        }
    }//GEN-LAST:event_mouseClicked

    private void setFeedback(int x, int y){
        int a = y-x+1;
        Color c = new Color(153,153,255);
        if(a == 0){
            feedbackHead.setText("No feedback so far");feedNext.setForeground(c);
            f1.setForeground(c);f2.setForeground(c);f3.setForeground(c);f4.setForeground(c);f5.setForeground(c);
        }
        else if(a == 1){
            feedbackHead.setText("Your Feedback");feedNext.setForeground(c);
            f1.setForeground(Color.white);f2.setForeground(c);f3.setForeground(c);f4.setForeground(c);f5.setForeground(c);
            f1.setText(feed5[x]);
        }
        else if(a == 2){
            feedbackHead.setText("Your Feedback");feedNext.setForeground(c);
            f1.setForeground(Color.white);f2.setForeground(Color.white);f3.setForeground(c);f4.setForeground(c);f5.setForeground(c);
            f1.setText(feed5[x]);f2.setText(feed5[x+1]);
        }
        else if(a == 3){
            feedbackHead.setText("Your Feedback");feedNext.setForeground(c);
            f1.setForeground(Color.white);f2.setForeground(Color.white);f3.setForeground(Color.white);f4.setForeground(c);f5.setForeground(c);
            f1.setText(feed5[x]);f2.setText(feed5[x+1]);f3.setText(feed5[x+2]);
        }
        else if(a == 4){
            feedbackHead.setText("Your Feedback");feedNext.setForeground(c);
            f1.setForeground(Color.white);f2.setForeground(Color.white);f3.setForeground(Color.white);f4.setForeground(Color.white);f5.setForeground(c);
            f1.setText(feed5[x]);f2.setText(feed5[x+1]);f3.setText(feed5[x+2]);f4.setText(feed5[x+3]);
        }
        else if(a == 5){
            feedbackHead.setText("Your Feedback");feedNext.setForeground(c);
            f1.setForeground(Color.white);f2.setForeground(Color.white);f3.setForeground(Color.white);f4.setForeground(Color.white);f5.setForeground(Color.white);
            f1.setText(feed5[x]);f2.setText(feed5[x+1]);f3.setText(feed5[x+2]);f4.setText(feed5[x+3]);f5.setText(feed5[x+4]);
        }
        else{
            feedbackHead.setText("Your Feedback");feedNext.setForeground(Color.blue);
            f1.setForeground(Color.white);f2.setForeground(Color.white);f3.setForeground(Color.white);f4.setForeground(Color.white);f5.setForeground(Color.white);
            f1.setText(feed5[x]);f2.setText(feed5[x+1]);f3.setText(feed5[x+2]);f4.setText(feed5[x+3]);f5.setText(feed5[x+4]);
        }
    }
    private void setRequest(){
        index = 0;
        Color c2 = new Color(153,153,255);
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            //Statement st = connection.createStatement();
            ResultSet rs = null;
            String query = "select userRegistration.id, userName, photo from userRegistration,request where userRegistration.id = request.id and (flag = 1 and lawyerID = "+String.valueOf(lawyerID)+")";
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery();
            while(rs.next()){
                name5[index] = rs.getString("userName");
                pic5[index] = rs.getString("photo");
                id5[index] = rs.getInt("id");
                index++;
            }
            if(index == 0){
                reqHead.setText("You have no request");
                reqp1.setIcon(null);reqp2.setIcon(null);reqp2.setIcon(null);
                reqn1.setForeground(c2);reqn2.setForeground(c2);reqn3.setForeground(c2);
                reqd1.setForeground(c2);reqd2.setForeground(c2);reqd3.setForeground(c2);
            }
            else if(index == 1){
                reqHead.setText("Your Case Requests");
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(pic5[0]).getImage().getScaledInstance(reqp1.getWidth(), reqp1.getHeight(), Image.SCALE_SMOOTH));
                reqp1.setIcon(imageIcon);reqp2.setIcon(null);reqp3.setIcon(null);
                reqn1.setForeground(Color.black);reqn2.setForeground(c2);reqn3.setForeground(c2);
                reqn1.setText(name5[0]);
                reqd1.setForeground(Color.white);reqd2.setForeground(c2);reqd3.setForeground(c2);           
            }
            else if(index == 2){
                reqHead.setText("Your Case Requests");
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(pic5[0]).getImage().getScaledInstance(reqp1.getWidth(), reqp1.getHeight(), Image.SCALE_SMOOTH));
                reqp1.setIcon(imageIcon);reqp3.setIcon(null);
                imageIcon = new ImageIcon(new ImageIcon(pic5[1]).getImage().getScaledInstance(reqp2.getWidth(), reqp2.getHeight(), Image.SCALE_SMOOTH));
                reqp2.setIcon(imageIcon);
                reqn1.setForeground(Color.black);reqn2.setForeground(Color.black);reqn3.setForeground(c2);
                reqn1.setText(name5[0]);reqn2.setText(name5[1]);
                reqd1.setForeground(Color.white);reqd2.setForeground(Color.white);reqd3.setForeground(c2);
            }
            else if(index == 3){
                reqHead.setText("Your Case Requests");
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(pic5[0]).getImage().getScaledInstance(reqp1.getWidth(), reqp1.getHeight(), Image.SCALE_SMOOTH));
                reqp1.setIcon(imageIcon);
                imageIcon = new ImageIcon(new ImageIcon(pic5[1]).getImage().getScaledInstance(reqp2.getWidth(), reqp2.getHeight(), Image.SCALE_SMOOTH));
                reqp2.setIcon(imageIcon);
                imageIcon = new ImageIcon(new ImageIcon(pic5[2]).getImage().getScaledInstance(reqp3.getWidth(), reqp3.getHeight(), Image.SCALE_SMOOTH));
                reqp3.setIcon(imageIcon);
                reqn1.setForeground(Color.black);reqn2.setForeground(Color.black);reqn3.setForeground(Color.black);
                reqn1.setText(name5[0]);reqn2.setText(name5[1]);reqn3.setText(name5[2]);
                reqd1.setForeground(Color.white);reqd2.setForeground(Color.white);reqd3.setForeground(Color.white);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void setNotification(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            //Statement st = connection.createStatement();
            ResultSet rs = null;
            String query = "select lawyerName, lawyerPhone, officeAddress, passwrd, email, lawyerPhoto from lawyerRegistration where lawyerID = "+String.valueOf(lawyerID);
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery();
            while(rs.next()){
                String image =  rs.getString("lawyerPhoto");
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(picN.getWidth(), picN.getHeight(), Image.SCALE_SMOOTH));
                picN.setIcon(imageIcon);
                nam.setText(rs.getString("lawyerName"));
                pass.setText(rs.getString("passwrd"));
                mail.setText(rs.getString("email"));
                phn.setText(rs.getString("lawyerPhone"));
                adres.setText(rs.getString("officeAddress"));
                txtN.setText(image);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void button2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button2MouseClicked
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            fileName = f.getAbsolutePath();
            txtN.setText(fileName);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(fileName).getImage().getScaledInstance(picN.getWidth(), picN.getHeight(), Image.SCALE_SMOOTH));
            picN.setIcon(imageIcon);
        }
    }//GEN-LAST:event_button2MouseClicked

    private void UpdateLawMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateLawMouseClicked
        // TODO add your handling code here:
        String snam = nam.getText();
        String spass = pass.getText();
        String smail = mail.getText();
        String sphn = phn.getText();
        String sad = adres.getText();
        String image = txtN.getText();
        String qsql = "update lawyerRegistration set lawyerName = '"+snam+"',lawyerPhone = '"+sphn+"',passwrd = '"+spass+"',email = '"+smail+"',officeAddress = '"+sad+"',lawyerPhoto = '"+image+"' where lawyerID = "+String.valueOf(lawyerID);
            if (snam.isEmpty() || spass.isEmpty() || smail.isEmpty() || sphn.isEmpty() || sad.isEmpty() || image.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Fill up carefully");
            } else {
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection connection = DriverManager
                    .getConnection(
                        "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
                    String sql = qsql;
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.executeUpdate();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Updated Successfully!!");
            }
    }//GEN-LAST:event_UpdateLawMouseClicked

    private void clientDetails(int a){
        clientID = a;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet dataset = null;
            String query = "select userName, email, phone, photo from userRegistration where id = "+String.valueOf(a);
            prepStmnt = connection.prepareStatement(query);
            dataset = prepStmnt.executeQuery();
            while(dataset.next()){
                cName.setText(dataset.getString("userName"));
                String qw = dataset.getString("photo");
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(qw).getImage().getScaledInstance(cPhoto.getWidth(), cPhoto.getHeight(), Image.SCALE_SMOOTH));
                cPhoto.setIcon(imageIcon);
                cMail.setText(dataset.getString("email"));
                cPhn.setText(dataset.getString("phone"));
            }
            prepStmnt = null;dataset = null;
            query = "select clientMsg from request where id ="+String.valueOf(a)+" and lawyerID = "+String.valueOf(lawyerID);
            prepStmnt = connection.prepareStatement(query);
            dataset = prepStmnt.executeQuery();
            while(dataset.next()){
                cMsg.setText(dataset.getString("clientMsg"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void reqd1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reqd1MouseClicked
        // TODO add your handling code here:
            homePanel.setVisible(false);
            feedbackPanel.setVisible(false);
            requestPanel.setVisible(false);
            settingsPanel.setVisible(false);
            clientPanel.setVisible(true);
            casePanel.setVisible(false);
            cID.setText(String.valueOf(id5[0]));
            clientDetails(id5[0]);
    }//GEN-LAST:event_reqd1MouseClicked

    private void acceptRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptRequestActionPerformed
        // TODO add your handling code here:
        int caseID = 0, l = 0, l1 = 0, newIndex = 0;
        int[] sameType = new int[50];
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            String sql = "delete from request where id ="+ cID.getText()+" and lawyerID = "+String.valueOf(lawyerID);
            PreparedStatement pst = null;
            pst = connection.prepareStatement(sql);
            pst.executeUpdate();
            sql = "Insert into cases"
                +"(caseType, lawyerID, id, lawyerName, done)"
                +"values(?,?,?,?,?)";
                pst = connection.prepareStatement(sql);
                pst.setString(1, lawyerType);
                pst.setInt(2, lawyerID);
                pst.setInt(3, clientID);
                pst.setString(4, lawyerName);
                pst.setInt(5, 0);
                pst.executeUpdate();
                ResultSet dataset = null;pst = null;
                sql = "select caseID from cases where id ="+ cID.getText()+" and (lawyerID = "+String.valueOf(lawyerID)+" and done = 0)";
                pst = connection.prepareStatement(sql);
                dataset = pst.executeQuery();
                while(dataset.next()){
                    caseID = dataset.getInt("caseID");
                }
                System.out.println(caseID);
                sql = "Insert into userNotification"
                +"(notiReceiver, notiReceiverID, notiSender, notiSenderID, notiType, caseID, done, descrip)"
                +"values(?,?,?,?,?,?,?,?)";    
                pst = connection.prepareStatement(sql);
                pst.setString(1, cName.getText());
                pst.setInt(2, clientID);
                pst.setString(3, lawyerName);
                pst.setInt(4, lawyerID);
                pst.setString(5, "request");
                pst.setInt(6, caseID);
                pst.setInt(7, 0);
                String a = lawyerName+" has accepted your request";
                pst.setString(8, a);
                pst.executeUpdate();
                dataset = null;pst = null;
                sql = "select sentRequest from userRegistration where id = "+String.valueOf(clientID);
                pst = connection.prepareStatement(sql);
                dataset = pst.executeQuery();
                while(dataset.next()){
                    l = dataset.getInt("sentRequest");
                }
                l--;
                dataset = null;pst = null;
                sql = "update userRegistration set sentRequest = "+String.valueOf(l)+" where id = "+String.valueOf(clientID);
                pst = connection.prepareStatement(sql);
                pst.executeUpdate();
                dataset = null;pst = null;
                sql = "select limit from lawyerRegistration where lawyerID = "+String.valueOf(lawyerID);///
                pst = connection.prepareStatement(sql);
                dataset = pst.executeQuery();
                while(dataset.next()){
                    l = dataset.getInt("limit");
                }
                l--;
                dataset = null;pst = null;
                sql = "update lawyerRegistration set limit = "+String.valueOf(l)+" where lawyerID = "+String.valueOf(lawyerID);
                pst = connection.prepareStatement(sql);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null,"Request Accepted");
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }//GEN-LAST:event_acceptRequestActionPerformed

    private void cancleRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleRequestActionPerformed
        // TODO add your handling code here:
        int l = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            String sql = "delete from request where id ="+ cID.getText()+" and lawyerID = "+String.valueOf(lawyerID);
            PreparedStatement pst = null;
            pst = connection.prepareStatement(sql);
            pst.executeUpdate();
            ResultSet dataset = null;
            dataset = null;pst = null;
                sql = "select sentRequest from userRegistration where id = "+String.valueOf(clientID);
                pst = connection.prepareStatement(sql);
                dataset = pst.executeQuery();
                while(dataset.next()){
                    l = dataset.getInt("sentRequest");
                }
                l--;
                dataset = null;pst = null;
                sql = "update userRegistration set sentRequest = "+String.valueOf(l)+" where id = "+String.valueOf(clientID);
                pst = connection.prepareStatement(sql);
                pst.executeUpdate();
                dataset = null;pst = null;
                sql = "select limit from lawyerRegistration where lawyerID = "+String.valueOf(lawyerID);///
                pst = connection.prepareStatement(sql);
                dataset = pst.executeQuery();
                while(dataset.next()){
                    l = dataset.getInt("limit");
                }
                l--;
                dataset = null;pst = null;
                sql = "update lawyerRegistration set limit = "+String.valueOf(l)+" where lawyerID = "+String.valueOf(lawyerID);
                pst = connection.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Request Cancled");
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_cancleRequestActionPerformed

    private void feedNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedNextMouseClicked
        // TODO add your handling code here:
        setFeedback(5*clicking, index);
        clicking++;
    }//GEN-LAST:event_feedNextMouseClicked

    private void f2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_f2MouseClicked

    private void f3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_f3MouseClicked

    private void f4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_f4MouseClicked

    private void f5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f5MouseClicked
        // TODO add your handling code here
    }//GEN-LAST:event_f5MouseClicked

    private void f1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_f1MouseClicked

    private void homeNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeNextMouseClicked
        // TODO add your handling code here:
        setCases(5*clicking, index);
        clicking++;
    }//GEN-LAST:event_homeNextMouseClicked

    private void showCaseDetails(String s1){
        System.out.println("DDD");
        homePanel.setVisible(false);
        feedbackPanel.setVisible(false);
        requestPanel.setVisible(false);
        settingsPanel.setVisible(false);
        clientPanel.setVisible(false);
        casePanel.setVisible(true);
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet dataset = null;
            String query = "select userName, email, phone, photo from userRegistration where id = "+s1;
            prepStmnt = connection.prepareStatement(query);
            dataset = prepStmnt.executeQuery();
            while(dataset.next()){
                cnam.setText(dataset.getString("userName"));
                String qw = dataset.getString("photo");
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(qw).getImage().getScaledInstance(casePic.getWidth(), casePic.getHeight(), Image.SCALE_SMOOTH));
                casePic.setIcon(imageIcon);
                cmail.setText(dataset.getString("email"));
                cphn.setText(dataset.getString("phone"));
            }
            clientName = cnam.getText();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void c1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c1MouseClicked
        // TODO add your handling code here:
        showCaseDetails(hid1.getText());
        //System.out.println(case1.getText());
        //System.out.println(hid1.getText());
        String a1 = case1.getText();
        String a2 = hid1.getText();       
        int b1 = Integer.parseInt(a1);
        int b2 = Integer.parseInt(a2);
        CaseID = b1;
        clientID = b2;
    }//GEN-LAST:event_c1MouseClicked

    private void doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneActionPerformed
        // TODO add your handling code here:
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = null;
            String sql = "Insert into userNotification"
                +"(notiReceiver, notiReceiverID, notiSender, notiSenderID, notiType, caseID, done, descrip)"
                +"values(?,?,?,?,?,?,?,?)";    
                pst = connection.prepareStatement(sql);
                pst.setString(1, clientName);
                pst.setInt(2, clientID);
                pst.setString(3, lawyerName);
                pst.setInt(4, lawyerID);
                pst.setString(5, "rating");
                pst.setInt(6, CaseID);
                pst.setInt(7, 0);
                String a = "You have to rate "+lawyerName;
                pst.setString(8, a);
                pst.executeUpdate();
                pst = null;
                sql = "update cases set done = 1 where caseID = "+String.valueOf(CaseID);
                pst = connection.prepareStatement(sql);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Marked as Done!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_doneActionPerformed

    private void c2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c2MouseClicked
        // TODO add your handling code here:
        showCaseDetails(hid2.getText());
        CaseID = Integer.parseInt(case2.getText());
        clientID = Integer.parseInt(hid2.getText());
    }//GEN-LAST:event_c2MouseClicked

    private void c3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c3MouseClicked
        // TODO add your handling code here:
        showCaseDetails(hid3.getText());
        CaseID = Integer.parseInt(case3.getText());
        clientID = Integer.parseInt(hid3.getText());
    }//GEN-LAST:event_c3MouseClicked

    private void c4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c4MouseClicked
        // TODO add your handling code here:
        showCaseDetails(hid4.getText());
        CaseID = Integer.parseInt(case4.getText());
        clientID = Integer.parseInt(hid4.getText());
    }//GEN-LAST:event_c4MouseClicked

    private void c5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c5MouseClicked
        // TODO add your handling code here:
        showCaseDetails(hid5.getText());
        CaseID = Integer.parseInt(case6.getText());
        clientID = Integer.parseInt(hid5.getText());
    }//GEN-LAST:event_c5MouseClicked

    private void reqd2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reqd2MouseClicked
        // TODO add your handling code here:
        homePanel.setVisible(false);
            feedbackPanel.setVisible(false);
            requestPanel.setVisible(false);
            settingsPanel.setVisible(false);
            clientPanel.setVisible(true);
            casePanel.setVisible(false);
            cID.setText(String.valueOf(id5[1]));
            clientDetails(id5[1]);
    }//GEN-LAST:event_reqd2MouseClicked

    private void reqd3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reqd3MouseClicked
        // TODO add your handling code here:
        homePanel.setVisible(false);
            feedbackPanel.setVisible(false);
            requestPanel.setVisible(false);
            settingsPanel.setVisible(false);
            clientPanel.setVisible(true);
            casePanel.setVisible(false);
            cID.setText(String.valueOf(id5[2]));
            clientDetails(id5[2]);
    }//GEN-LAST:event_reqd3MouseClicked

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
            java.util.logging.Logger.getLogger(LawyerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LawyerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LawyerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LawyerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LawyerDashboard("s").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel UpdateLaw;
    private java.awt.Button acceptRequest;
    private javax.swing.JTextField adres;
    private javax.swing.JLabel bam;
    private java.awt.Button button2;
    private javax.swing.JLabel c1;
    private javax.swing.JLabel c2;
    private javax.swing.JLabel c3;
    private javax.swing.JLabel c4;
    private javax.swing.JLabel c5;
    private javax.swing.JLabel cID;
    private javax.swing.JTextField cMail;
    private javax.swing.JTextArea cMsg;
    private javax.swing.JTextField cName;
    private javax.swing.JTextField cPhn;
    private javax.swing.JLabel cPhoto;
    private java.awt.Button cancleRequest;
    private javax.swing.JLabel case1;
    private javax.swing.JLabel case2;
    private javax.swing.JLabel case3;
    private javax.swing.JLabel case4;
    private javax.swing.JLabel case6;
    private javax.swing.JPanel casePanel;
    private javax.swing.JLabel casePic;
    private javax.swing.JPanel clientPanel;
    private javax.swing.JLabel cmail;
    private javax.swing.JLabel cnam;
    private javax.swing.JLabel cphn;
    private javax.swing.JLabel dan;
    private java.awt.Button done;
    private javax.swing.JLabel f1;
    private javax.swing.JLabel f2;
    private javax.swing.JLabel f3;
    private javax.swing.JLabel f4;
    private javax.swing.JLabel f5;
    private javax.swing.JLabel feedNext;
    private javax.swing.JPanel feedback;
    private javax.swing.JLabel feedbackHead;
    private javax.swing.JLabel feedbackHead2;
    private javax.swing.JPanel feedbackPanel;
    private javax.swing.JLabel hid1;
    private javax.swing.JLabel hid2;
    private javax.swing.JLabel hid3;
    private javax.swing.JLabel hid4;
    private javax.swing.JLabel hid5;
    private javax.swing.JLabel hloss;
    private javax.swing.JLabel hname;
    private javax.swing.JPanel home;
    private javax.swing.JLabel homeNext;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel hpic;
    private javax.swing.JLabel hrating;
    private javax.swing.JLabel hratio;
    private javax.swing.JLabel htotal;
    private javax.swing.JLabel hwin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JPanel logout;
    private javax.swing.JTextField mail;
    private javax.swing.JTextField nam;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JTextField pass;
    private javax.swing.JTextField phn;
    private javax.swing.JLabel picN;
    private javax.swing.JLabel reqHead;
    private javax.swing.JLabel reqHead1;
    private javax.swing.JLabel reqd1;
    private javax.swing.JLabel reqd2;
    private javax.swing.JLabel reqd3;
    private javax.swing.JLabel reqn1;
    private javax.swing.JLabel reqn10;
    private javax.swing.JLabel reqn2;
    private javax.swing.JLabel reqn3;
    private javax.swing.JLabel reqn5;
    private javax.swing.JLabel reqn7;
    private javax.swing.JLabel reqn8;
    private javax.swing.JLabel reqn9;
    private javax.swing.JLabel reqp1;
    private javax.swing.JLabel reqp2;
    private javax.swing.JLabel reqp3;
    private javax.swing.JPanel request;
    private javax.swing.JPanel requestPanel;
    private javax.swing.JPanel settings;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JLabel txtN;
    // End of variables declaration//GEN-END:variables
}

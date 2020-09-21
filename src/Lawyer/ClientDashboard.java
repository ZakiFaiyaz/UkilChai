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
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Asus
 */
public class ClientDashboard extends javax.swing.JFrame {
    String clientName, lawyerName, clientPhoto;
    int clientID, LawyerID;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String sqlString;
    DefaultTableModel model ;
    String[] nam5 = new String[100]; 
    String[] ima5 = new String[100];
    String[] law5 = new String[100];
    String[] typ5 = new String[100];
    String[] notification5 = new String[100];
    int[] id5 = new int[100];
    int finish = 0, clicking = 1, CID = 0, WIN = 0;

    public ClientDashboard(String clientName, String lawyerName, String clientPhoto, int clientID, int LawyerID) {
        this.clientName = clientName;
        this.lawyerName = lawyerName;
        this.clientPhoto = clientPhoto;
        this.clientID = clientID;
        this.LawyerID = LawyerID;
    }
    
    public ClientDashboard(String s) {
        initComponents();
        homePanel.setBackground(Color.LIGHT_GRAY);
        clientName = s;
        String l = "D:\\Icon\\logo.png";
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(l).getImage().getScaledInstance(dan.getWidth(), dan.getHeight(), Image.SCALE_SMOOTH));
        dan.setIcon(imageIcon);
        imageIcon = new ImageIcon(new ImageIcon(l).getImage().getScaledInstance(bam.getWidth(), bam.getHeight(), Image.SCALE_SMOOTH));
        bam.setIcon(imageIcon);
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet dataset = null;
            String query = "select id, photo from userRegistration where userName = '"+clientName+"'";
            prepStmnt = connection.prepareStatement(query);
            dataset = prepStmnt.executeQuery();
            while(dataset.next()){
                clientID = dataset.getInt("id");
                clientPhoto = dataset.getString("photo");
            }
            homeName.setText(clientName);
            finish = 0;
            imageIcon = new ImageIcon(new ImageIcon(clientPhoto).getImage().getScaledInstance(homePic.getWidth(), homePic.getHeight(), Image.SCALE_SMOOTH));
            homePic.setIcon(imageIcon);
            query = "select top 5 caseType, lawyerName from cases where id = "+String.valueOf(clientID)+" and done = 0 order by caseID desc";
            prepStmnt = connection.prepareStatement(query);
            dataset = prepStmnt.executeQuery();
            while(dataset.next()){
                typ5[finish] = dataset.getString("caseType");
                law5[finish] = dataset.getString("lawyerName");
                finish++;
            }
            Color c = new Color(35,45,35);
            if(finish == 0){
                case1.setForeground(Color.white);case1.setText("You have no cases so far.");
                case2.setForeground(c);case3.setForeground(c);case4.setForeground(c);case5.setForeground(c);
            }
            else if(finish == 1){
                case1.setForeground(Color.white);case1.setText("* You have "+typ5[0]+" case under Mr. "+law5[0]);
                case2.setForeground(c);case3.setForeground(c);case4.setForeground(c);case5.setForeground(c);
            }
            else if(finish == 2){
                case1.setForeground(Color.white);case1.setText("* You have "+typ5[0]+" case under Mr. "+law5[0]);
                case2.setForeground(Color.white);case2.setText("* You have "+typ5[1]+" case under Mr. "+law5[1]);
                case3.setForeground(c);case4.setForeground(c);case5.setForeground(c);
            }
            else if(finish == 3){
                case1.setForeground(Color.white);case1.setText("* You have "+typ5[0]+" case under Mr. "+law5[0]);
                case2.setForeground(Color.white);case2.setText("* You have "+typ5[1]+" case under Mr. "+law5[1]);
                case3.setForeground(Color.white);case3.setText("* You have "+typ5[2]+" case under Mr. "+law5[2]);
                case4.setForeground(c);case5.setForeground(c);
            }
            else if(finish == 4){
                case1.setForeground(Color.white);case1.setText("* You have "+typ5[0]+" case under Mr. "+law5[0]);
                case2.setForeground(Color.white);case2.setText("* You have "+typ5[1]+" case under Mr. "+law5[1]);
                case3.setForeground(Color.white);case3.setText("* You have "+typ5[2]+" case under Mr. "+law5[2]);
                case4.setForeground(Color.white);case4.setText("* You have "+typ5[3]+" case under Mr. "+law5[3]);
                case5.setForeground(c);
            }
            else{
                case1.setForeground(Color.white);case1.setText("* You have "+typ5[0]+" case under Mr. "+law5[0]);
                case2.setForeground(Color.white);case2.setText("* You have "+typ5[1]+" case under Mr. "+law5[1]);
                case3.setForeground(Color.white);case3.setText("* You have "+typ5[2]+" case under Mr. "+law5[2]);
                case4.setForeground(Color.white);case4.setText("* You have "+typ5[3]+" case under Mr. "+law5[3]);
                case5.setForeground(Color.white);case5.setText("* You have "+typ5[4]+" case under Mr. "+law5[4]);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        panelCenter.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        home1 = new javax.swing.JLabel();
        notiPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        home2 = new javax.swing.JLabel();
        findPanel = new javax.swing.JPanel();
        home3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        settingsPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        home4 = new javax.swing.JLabel();
        logoutPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        home = new javax.swing.JLabel();
        panelCenter = new javax.swing.JPanel();
        panelHome = new javax.swing.JPanel();
        homePic = new javax.swing.JLabel();
        homeName = new javax.swing.JLabel();
        case5 = new javax.swing.JLabel();
        noti8 = new javax.swing.JLabel();
        case1 = new javax.swing.JLabel();
        case2 = new javax.swing.JLabel();
        case3 = new javax.swing.JLabel();
        case4 = new javax.swing.JLabel();
        panelNoti = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        noti1 = new javax.swing.JLabel();
        noti2 = new javax.swing.JLabel();
        noti3 = new javax.swing.JLabel();
        noti4 = new javax.swing.JLabel();
        noti5 = new javax.swing.JLabel();
        notiNext = new javax.swing.JLabel();
        cid3 = new javax.swing.JLabel();
        cid4 = new javax.swing.JLabel();
        cid2 = new javax.swing.JLabel();
        cid5 = new javax.swing.JLabel();
        cid1 = new javax.swing.JLabel();
        panelFind = new javax.swing.JPanel();
        combo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        search = new javax.swing.JButton();
        p1 = new javax.swing.JLabel();
        p2 = new javax.swing.JLabel();
        p3 = new javax.swing.JLabel();
        p4 = new javax.swing.JLabel();
        p5 = new javax.swing.JLabel();
        n5 = new javax.swing.JLabel();
        n1 = new javax.swing.JLabel();
        n2 = new javax.swing.JLabel();
        n3 = new javax.swing.JLabel();
        n4 = new javax.swing.JLabel();
        next = new javax.swing.JLabel();
        i1 = new javax.swing.JLabel();
        i2 = new javax.swing.JLabel();
        i3 = new javax.swing.JLabel();
        i4 = new javax.swing.JLabel();
        i5 = new javax.swing.JLabel();
        panelSettings = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        emil = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        adrs = new javax.swing.JTextField();
        pas = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        naam = new javax.swing.JTextField();
        con = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        up = new java.awt.Button();
        Update = new javax.swing.JLabel();
        txt = new javax.swing.JLabel();
        notice = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        pic = new javax.swing.JLabel();
        panelLawyer = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lawPic = new javax.swing.JLabel();
        lawSuccess = new javax.swing.JLabel();
        lawName1 = new javax.swing.JLabel();
        lawName = new javax.swing.JLabel();
        lawName3 = new javax.swing.JLabel();
        lawName4 = new javax.swing.JLabel();
        lawName5 = new javax.swing.JLabel();
        lawName6 = new javax.swing.JLabel();
        lawName7 = new javax.swing.JLabel();
        lawName8 = new javax.swing.JLabel();
        lawRate = new javax.swing.JLabel();
        lawWin = new javax.swing.JLabel();
        lawLoss = new javax.swing.JLabel();
        lawTotal = new javax.swing.JLabel();
        lawAdd = new javax.swing.JLabel();
        lawMail = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lawMsg = new javax.swing.JTextArea();
        specificLawyerId = new javax.swing.JLabel();
        lawName10 = new javax.swing.JLabel();
        lawName11 = new javax.swing.JLabel();
        lawNotice = new javax.swing.JLabel();
        lawName12 = new javax.swing.JLabel();
        panelRating = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lossButton = new javax.swing.JRadioButton();
        winButton = new javax.swing.JRadioButton();
        rateName = new javax.swing.JLabel();
        lawName14 = new javax.swing.JLabel();
        ratingNotice = new javax.swing.JLabel();
        rateField = new javax.swing.JTextField();
        lawName16 = new javax.swing.JLabel();
        lawName17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        feedbackField = new javax.swing.JTextArea();
        rateLawName = new javax.swing.JLabel();
        lawName18 = new javax.swing.JLabel();
        backNoti = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        bam = new javax.swing.JLabel();
        dan = new javax.swing.JLabel();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        homePanel.setBackground(new java.awt.Color(41, 44, 51));
        homePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homePanelMouseClicked(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 51, 51));
        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setText("Home");

        home1.setBackground(new java.awt.Color(255, 255, 255));
        home1.setForeground(new java.awt.Color(255, 255, 255));
        home1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lawyer/homeIcon.png"))); // NOI18N
        home1.setText("jLabel1");

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(home1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(homePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 270, 110));

        notiPanel.setBackground(new java.awt.Color(41, 44, 51));
        notiPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notiPanelMouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 51, 51));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Notification");

        home2.setBackground(new java.awt.Color(255, 255, 255));
        home2.setForeground(new java.awt.Color(255, 255, 255));
        home2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lawyer/notificationIcon.png"))); // NOI18N
        home2.setText("jLabel1");

        javax.swing.GroupLayout notiPanelLayout = new javax.swing.GroupLayout(notiPanel);
        notiPanel.setLayout(notiPanelLayout);
        notiPanelLayout.setHorizontalGroup(
            notiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, notiPanelLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(63, 63, 63))
            .addGroup(notiPanelLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(home2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        notiPanelLayout.setVerticalGroup(
            notiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, notiPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(home2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(19, 19, 19))
        );

        jPanel1.add(notiPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 270, 110));

        findPanel.setBackground(new java.awt.Color(41, 44, 51));
        findPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                findPanelMouseClicked(evt);
            }
        });

        home3.setBackground(new java.awt.Color(255, 255, 255));
        home3.setForeground(new java.awt.Color(255, 255, 255));
        home3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lawyer/searchIcon.png"))); // NOI18N
        home3.setText("jLabel1");

        jLabel4.setBackground(new java.awt.Color(0, 51, 51));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setText("Find Lawyer");

        javax.swing.GroupLayout findPanelLayout = new javax.swing.GroupLayout(findPanel);
        findPanel.setLayout(findPanelLayout);
        findPanelLayout.setHorizontalGroup(
            findPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findPanelLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(63, 63, 63))
            .addGroup(findPanelLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(home3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        findPanelLayout.setVerticalGroup(
            findPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, findPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        jPanel1.add(findPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 270, 110));

        settingsPanel.setBackground(new java.awt.Color(41, 44, 51));
        settingsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsPanelMouseClicked(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 51, 51));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 255));
        jLabel5.setText("Settings");

        home4.setBackground(new java.awt.Color(255, 255, 255));
        home4.setForeground(new java.awt.Color(255, 255, 255));
        home4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lawyer/settingsIcon.png"))); // NOI18N
        home4.setText("jLabel1");

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(home4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel5)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(settingsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 270, 110));

        logoutPanel.setBackground(new java.awt.Color(41, 44, 51));
        logoutPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutPanelMouseClicked(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 51, 51));
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 255));
        jLabel3.setText("Log Out");

        home.setBackground(new java.awt.Color(255, 255, 255));
        home.setForeground(new java.awt.Color(255, 255, 255));
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lawyer/logoutIcon.png"))); // NOI18N
        home.setText("jLabel1");

        javax.swing.GroupLayout logoutPanelLayout = new javax.swing.GroupLayout(logoutPanel);
        logoutPanel.setLayout(logoutPanelLayout);
        logoutPanelLayout.setHorizontalGroup(
            logoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoutPanelLayout.createSequentialGroup()
                .addGroup(logoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logoutPanelLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(logoutPanelLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel3)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        logoutPanelLayout.setVerticalGroup(
            logoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoutPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanel1.add(logoutPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 270, 120));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 640));

        panelCenter.setBackground(new java.awt.Color(102, 102, 102));
        panelCenter.setLayout(new java.awt.CardLayout());

        panelHome.setBackground(new java.awt.Color(35, 45, 35));
        panelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelHome.add(homePic, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 170, 210));

        homeName.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        homeName.setForeground(new java.awt.Color(204, 153, 255));
        homeName.setText("WELCOME");
        homeName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeNameMouseClicked(evt);
            }
        });
        panelHome.add(homeName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 270, 40));

        case5.setBackground(new java.awt.Color(255, 255, 255));
        case5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        case5.setForeground(new java.awt.Color(255, 255, 255));
        case5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                case5MouseClicked(evt);
            }
        });
        panelHome.add(case5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 710, 30));

        noti8.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        noti8.setForeground(new java.awt.Color(153, 153, 255));
        noti8.setText("WELCOME");
        noti8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noti8MouseClicked(evt);
            }
        });
        panelHome.add(noti8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 130, 40));

        case1.setBackground(new java.awt.Color(255, 255, 255));
        case1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        case1.setForeground(new java.awt.Color(255, 255, 255));
        case1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                case1MouseClicked(evt);
            }
        });
        panelHome.add(case1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 640, 30));

        case2.setBackground(new java.awt.Color(255, 255, 255));
        case2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        case2.setForeground(new java.awt.Color(255, 255, 255));
        case2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                case2MouseClicked(evt);
            }
        });
        panelHome.add(case2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 640, 30));

        case3.setBackground(new java.awt.Color(255, 255, 255));
        case3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        case3.setForeground(new java.awt.Color(255, 255, 255));
        case3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                case3MouseClicked(evt);
            }
        });
        panelHome.add(case3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 650, 30));

        case4.setBackground(new java.awt.Color(255, 255, 255));
        case4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        case4.setForeground(new java.awt.Color(255, 255, 255));
        case4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                case4MouseClicked(evt);
            }
        });
        panelHome.add(case4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 650, 30));

        panelCenter.add(panelHome, "card2");

        panelNoti.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(35, 45, 35));
        jPanel4.setForeground(new java.awt.Color(35, 45, 35));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        noti1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        noti1.setForeground(new java.awt.Color(255, 153, 153));
        noti1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noti1MouseClicked(evt);
            }
        });
        jPanel4.add(noti1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 870, 30));

        noti2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        noti2.setForeground(new java.awt.Color(255, 153, 153));
        noti2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noti2MouseClicked(evt);
            }
        });
        jPanel4.add(noti2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 870, 30));

        noti3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        noti3.setForeground(new java.awt.Color(255, 153, 153));
        noti3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noti3MouseClicked(evt);
            }
        });
        jPanel4.add(noti3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 870, 30));

        noti4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        noti4.setForeground(new java.awt.Color(255, 153, 153));
        noti4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noti4MouseClicked(evt);
            }
        });
        jPanel4.add(noti4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 870, 30));

        noti5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        noti5.setForeground(new java.awt.Color(255, 153, 153));
        noti5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noti5MouseClicked(evt);
            }
        });
        jPanel4.add(noti5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 870, 30));

        notiNext.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        notiNext.setForeground(new java.awt.Color(255, 255, 153));
        notiNext.setText("Next  ->");
        notiNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notiNextMouseClicked(evt);
            }
        });
        jPanel4.add(notiNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 490, 70, 30));

        cid3.setForeground(new java.awt.Color(35, 45, 35));
        cid3.setText("jLabel7");
        jPanel4.add(cid3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, -1, -1));

        cid4.setForeground(new java.awt.Color(35, 45, 35));
        cid4.setText("jLabel7");
        jPanel4.add(cid4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, -1, -1));

        cid2.setForeground(new java.awt.Color(35, 45, 35));
        cid2.setText("jLabel7");
        jPanel4.add(cid2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 480, -1, -1));

        cid5.setForeground(new java.awt.Color(35, 45, 35));
        cid5.setText("jLabel7");
        jPanel4.add(cid5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, -1, -1));

        cid1.setForeground(new java.awt.Color(35, 45, 35));
        cid1.setText("jLabel7");
        jPanel4.add(cid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, -1, -1));

        panelNoti.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 560));

        panelCenter.add(panelNoti, "card3");

        panelFind.setBackground(new java.awt.Color(35, 45, 35));
        panelFind.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combo.setBackground(new java.awt.Color(35, 45, 35));
        combo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        combo.setForeground(new java.awt.Color(204, 204, 204));
        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Criminal Law", "Civil Rights", "Digital Media and Internet", "Personal Injury", "Income Tax", "Entertainment" }));
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });
        panelFind.add(combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 360, 40));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 255, 255));
        jLabel6.setText("Top rated lawyers of this week");
        panelFind.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 450, 50));

        search.setBackground(new java.awt.Color(0, 153, 153));
        search.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        search.setForeground(new java.awt.Color(102, 255, 102));
        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        panelFind.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 120, 40));

        p1.setForeground(new java.awt.Color(255, 0, 51));
        p1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p1MouseEntered(evt);
            }
        });
        panelFind.add(p1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 110, 130));

        p2.setForeground(new java.awt.Color(255, 0, 0));
        p2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p2MouseClicked(evt);
            }
        });
        panelFind.add(p2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 110, 140));

        p3.setForeground(new java.awt.Color(255, 51, 51));
        p3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p3MouseClicked(evt);
            }
        });
        panelFind.add(p3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 140, 110, 130));

        p4.setForeground(new java.awt.Color(255, 51, 51));
        p4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p4MouseClicked(evt);
            }
        });
        panelFind.add(p4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 110, 130));

        p5.setForeground(new java.awt.Color(255, 51, 51));
        p5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p5MouseClicked(evt);
            }
        });
        panelFind.add(p5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 360, 110, 130));

        n5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        n5.setForeground(new java.awt.Color(204, 255, 255));
        n5.setText("jLabel21");
        panelFind.add(n5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 510, 290, 40));

        n1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        n1.setForeground(new java.awt.Color(204, 255, 255));
        n1.setText("jLabel21");
        panelFind.add(n1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 300, 30));

        n2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        n2.setForeground(new java.awt.Color(204, 255, 255));
        n2.setText("jLabel21");
        panelFind.add(n2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, 280, 30));

        n3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        n3.setForeground(new java.awt.Color(204, 255, 255));
        n3.setText("jLabel21");
        panelFind.add(n3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 320, 230, 30));

        n4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        n4.setForeground(new java.awt.Color(204, 255, 255));
        n4.setText("jLabel21");
        panelFind.add(n4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 520, 300, 30));

        next.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        next.setForeground(new java.awt.Color(0, 0, 255));
        next.setText("jLabel8");
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextMouseClicked(evt);
            }
        });
        panelFind.add(next, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 510, 70, 30));

        i1.setForeground(new java.awt.Color(35, 45, 35));
        i1.setText("jLabel9");
        panelFind.add(i1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        i2.setForeground(new java.awt.Color(35, 45, 35));
        i2.setText("jLabel9");
        panelFind.add(i2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        i3.setForeground(new java.awt.Color(35, 45, 35));
        i3.setText("jLabel9");
        panelFind.add(i3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        i4.setForeground(new java.awt.Color(35, 45, 35));
        i4.setText("jLabel9");
        panelFind.add(i4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        i5.setForeground(new java.awt.Color(35, 45, 35));
        i5.setText("jLabel9");
        panelFind.add(i5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        panelCenter.add(panelFind, "card4");

        panelSettings.setBackground(new java.awt.Color(35, 45, 35));
        panelSettings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("User Name");
        panelSettings.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 100, 30));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("Password");
        panelSettings.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 100, 30));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("Email Address");
        panelSettings.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 130, 30));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("Contact Number");
        panelSettings.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 160, 30));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Present Address");
        panelSettings.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 140, 30));

        emil.setBackground(new java.awt.Color(35, 45, 35));
        emil.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        emil.setForeground(new java.awt.Color(204, 204, 204));
        emil.setBorder(null);
        panelSettings.add(emil, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 280, 30));
        panelSettings.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 290, 10));

        adrs.setBackground(new java.awt.Color(35, 45, 35));
        adrs.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        adrs.setForeground(new java.awt.Color(204, 204, 204));
        adrs.setBorder(null);
        panelSettings.add(adrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 280, 30));

        pas.setBackground(new java.awt.Color(35, 45, 35));
        pas.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        pas.setForeground(new java.awt.Color(204, 204, 204));
        pas.setBorder(null);
        panelSettings.add(pas, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 280, 30));
        panelSettings.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 290, 10));
        panelSettings.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 290, 10));
        panelSettings.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 290, 0));

        naam.setBackground(new java.awt.Color(35, 45, 35));
        naam.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        naam.setForeground(new java.awt.Color(204, 204, 204));
        naam.setBorder(null);
        panelSettings.add(naam, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 280, 30));

        con.setBackground(new java.awt.Color(35, 45, 35));
        con.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        con.setForeground(new java.awt.Color(204, 204, 204));
        con.setBorder(null);
        panelSettings.add(con, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 280, 30));
        panelSettings.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 290, 10));
        panelSettings.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 290, 20));

        up.setActionCommand("upload");
        up.setBackground(new java.awt.Color(35, 45, 35));
        up.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        up.setForeground(new java.awt.Color(204, 204, 204));
        up.setLabel("Upload");
        up.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                upMouseClicked(evt);
            }
        });
        panelSettings.add(up, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, 130, 50));

        Update.setBackground(new java.awt.Color(35, 35, 35));
        Update.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        Update.setForeground(new java.awt.Color(204, 255, 255));
        Update.setText("UPDATE");
        Update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateMouseClicked(evt);
            }
        });
        panelSettings.add(Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 130, 50));

        txt.setForeground(new java.awt.Color(35, 45, 35));
        txt.setText("nai");
        panelSettings.add(txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 460, 140, 40));

        notice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        notice.setForeground(new java.awt.Color(255, 0, 0));
        panelSettings.add(notice, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 420, 30));
        panelSettings.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 180, 50));

        pic.setBackground(new java.awt.Color(255, 255, 255));
        pic.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelSettings.add(pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 196, 173));

        panelCenter.add(panelSettings, "card5");

        panelLawyer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(35, 45, 35));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(lawPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 180, 190));

        lawSuccess.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawSuccess.setForeground(new java.awt.Color(0, 255, 204));
        lawSuccess.setText("User Name");
        jPanel2.add(lawSuccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 320, 80, 30));

        lawName1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawName1.setForeground(new java.awt.Color(204, 204, 204));
        lawName1.setText("Success rate :");
        jPanel2.add(lawName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 320, 130, 30));

        lawName.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawName.setForeground(new java.awt.Color(255, 153, 153));
        lawName.setText("User Name");
        jPanel2.add(lawName, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 250, 220, 30));

        lawName3.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lawName3.setForeground(new java.awt.Color(255, 51, 51));
        lawName3.setText("Cancle");
        lawName3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lawName3MouseClicked(evt);
            }
        });
        jPanel2.add(lawName3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 90, 50));

        lawName4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawName4.setForeground(new java.awt.Color(204, 204, 204));
        lawName4.setText("Win :");
        jPanel2.add(lawName4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 80, 30));

        lawName5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawName5.setForeground(new java.awt.Color(204, 204, 204));
        lawName5.setText("Loss : ");
        jPanel2.add(lawName5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 80, 30));

        lawName6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawName6.setForeground(new java.awt.Color(204, 204, 204));
        lawName6.setText("Total cases :");
        jPanel2.add(lawName6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 110, 30));

        lawName7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawName7.setForeground(new java.awt.Color(204, 204, 204));
        lawName7.setText("Address : ");
        jPanel2.add(lawName7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 100, 30));

        lawName8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawName8.setForeground(new java.awt.Color(204, 204, 204));
        lawName8.setText("Type your message here :");
        jPanel2.add(lawName8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 240, 30));

        lawRate.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawRate.setForeground(new java.awt.Color(0, 255, 204));
        lawRate.setText("User Name");
        jPanel2.add(lawRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 280, 100, 30));

        lawWin.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawWin.setForeground(new java.awt.Color(0, 255, 204));
        lawWin.setText("User Name");
        jPanel2.add(lawWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 100, 30));

        lawLoss.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawLoss.setForeground(new java.awt.Color(0, 255, 204));
        lawLoss.setText("User Name");
        jPanel2.add(lawLoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 100, 30));

        lawTotal.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawTotal.setForeground(new java.awt.Color(0, 255, 204));
        lawTotal.setText("User Name");
        jPanel2.add(lawTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 100, 30));

        lawAdd.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawAdd.setForeground(new java.awt.Color(0, 255, 204));
        lawAdd.setText("User Name");
        jPanel2.add(lawAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 470, 30));

        lawMail.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawMail.setForeground(new java.awt.Color(0, 255, 204));
        lawMail.setText("User Name");
        jPanel2.add(lawMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 490, 30));

        lawMsg.setBackground(new java.awt.Color(30, 50, 30));
        lawMsg.setColumns(20);
        lawMsg.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawMsg.setForeground(new java.awt.Color(255, 255, 255));
        lawMsg.setRows(5);
        jScrollPane2.setViewportView(lawMsg);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 380, 170));

        specificLawyerId.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        specificLawyerId.setForeground(new java.awt.Color(35, 45, 35));
        specificLawyerId.setText("E-mail : ");
        jPanel2.add(specificLawyerId, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 80, 30));

        lawName10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawName10.setForeground(new java.awt.Color(204, 204, 204));
        lawName10.setText("Rating : ");
        jPanel2.add(lawName10, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 280, 80, 30));

        lawName11.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lawName11.setForeground(new java.awt.Color(51, 255, 0));
        lawName11.setText("Send Request");
        lawName11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lawName11MouseClicked(evt);
            }
        });
        jPanel2.add(lawName11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 160, 50));

        lawNotice.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lawNotice.setForeground(new java.awt.Color(51, 153, 255));
        lawNotice.setText("view sent request");
        lawNotice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lawNoticeMouseClicked(evt);
            }
        });
        jPanel2.add(lawNotice, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 480, 140, 30));

        lawName12.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lawName12.setForeground(new java.awt.Color(204, 204, 204));
        lawName12.setText("E-mail : ");
        jPanel2.add(lawName12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 80, 30));

        panelLawyer.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 560));

        panelCenter.add(panelLawyer, "card7");

        panelRating.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(35, 45, 35));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lossButton.setBackground(new java.awt.Color(35, 45, 35));
        buttonGroup1.add(lossButton);
        lossButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lossButton.setForeground(new java.awt.Color(255, 51, 51));
        lossButton.setText("Loss");
        lossButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lossButtonActionPerformed(evt);
            }
        });
        jPanel5.add(lossButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, -1, -1));

        winButton.setBackground(new java.awt.Color(35, 45, 35));
        buttonGroup1.add(winButton);
        winButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        winButton.setForeground(new java.awt.Color(0, 204, 102));
        winButton.setSelected(true);
        winButton.setText("Win");
        winButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                winButtonActionPerformed(evt);
            }
        });
        jPanel5.add(winButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, -1, -1));

        rateName.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        rateName.setForeground(new java.awt.Color(153, 51, 255));
        rateName.setText("SUBMIT");
        rateName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rateNameMouseClicked(evt);
            }
        });
        jPanel5.add(rateName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, 90, 30));

        lawName14.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lawName14.setForeground(new java.awt.Color(204, 255, 204));
        lawName14.setText("What is the result of your case ?");
        jPanel5.add(lawName14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 370, 30));

        ratingNotice.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ratingNotice.setForeground(new java.awt.Color(204, 153, 0));
        jPanel5.add(ratingNotice, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 390, 50));

        rateField.setBackground(new java.awt.Color(30, 50, 30));
        rateField.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        rateField.setForeground(new java.awt.Color(255, 255, 255));
        rateField.setText("5.0");
        jPanel5.add(rateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 90, 40));

        lawName16.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lawName16.setForeground(new java.awt.Color(204, 255, 204));
        lawName16.setText("Give us your feedback(optional)");
        jPanel5.add(lawName16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 400, 30));

        lawName17.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lawName17.setForeground(new java.awt.Color(204, 255, 204));
        lawName17.setText("Please rate Mr/Mrs.");
        jPanel5.add(lawName17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 230, 30));

        feedbackField.setBackground(new java.awt.Color(30, 50, 30));
        feedbackField.setColumns(20);
        feedbackField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        feedbackField.setForeground(new java.awt.Color(255, 255, 255));
        feedbackField.setRows(5);
        jScrollPane3.setViewportView(feedbackField);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 380, 170));

        rateLawName.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        rateLawName.setForeground(new java.awt.Color(204, 255, 204));
        rateLawName.setText("Please rate Mr.");
        jPanel5.add(rateLawName, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 180, 30));

        lawName18.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lawName18.setForeground(new java.awt.Color(255, 102, 102));
        lawName18.setText("N.B. You must rate your lawyer between 0.0-5.0");
        jPanel5.add(lawName18, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 330, 30));

        backNoti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lawyer/back.png"))); // NOI18N
        backNoti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backNotiMouseClicked(evt);
            }
        });
        jPanel5.add(backNoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 60, 60));

        panelRating.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 560));

        panelCenter.add(panelRating, "card7");

        getContentPane().add(panelCenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 970, 560));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jLabel7.setBackground(new java.awt.Color(204, 255, 255));
        jLabel7.setFont(new java.awt.Font("Sitka Display", 0, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 255, 255));
        jLabel7.setText("UKIL CHAI");

        bam.setText("jLabel1");

        dan.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(bam, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(369, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bam, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(dan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homePanelMouseClicked
        // TODO add your handling code here:
        homePanel.setBackground(Color.LIGHT_GRAY);
        Color c = new Color(41,44, 51);
        notiPanel.setBackground(c);
        findPanel.setBackground(c);
        settingsPanel.setBackground(c);
        logoutPanel.setBackground(c);
        panelCenter.setVisible(true);       
        if(evt.getSource() == homePanel){
            panelHome.setVisible(true);
            panelNoti.setVisible(false);
            panelFind.setVisible(false);
            panelSettings.setVisible(false);
            panelLawyer.setVisible(false);
            panelRating.setVisible(false);
        }
    }//GEN-LAST:event_homePanelMouseClicked

    private void setNotification(int x, int y){
        int a = y-x+1;
        Color c = new Color(35,45, 35);
        Color c1 = new Color(255,153,153);
        if(a == 0){
            noti1.setForeground(c1);
            noti1.setText("You have no notification so far");
            cid1.setText(String.valueOf(id5[x]));
            noti2.setForeground(c);noti3.setForeground(c);noti4.setForeground(c);noti5.setForeground(c);
            notiNext.setForeground(c);
        }
        else if(a == 1){
            noti1.setForeground(c1);
            noti1.setText(notification5[x]);
            cid1.setText(String.valueOf(id5[x]));
            noti2.setForeground(c);noti3.setForeground(c);noti4.setForeground(c);noti5.setForeground(c);
            notiNext.setForeground(c);
        }
        else if(a == 2){
            noti1.setForeground(c1);
            noti1.setText(notification5[x]);
            cid1.setText(String.valueOf(id5[x]));
            cid2.setText(String.valueOf(id5[x+1]));
            noti2.setForeground(c1);
            noti2.setText(notification5[x+1]);
            noti3.setForeground(c);noti4.setForeground(c);noti5.setForeground(c);
            notiNext.setForeground(c);
        }
        else if(a == 3){
            noti1.setForeground(c1);
            noti1.setText(notification5[x]);
            noti2.setForeground(c1);
            noti2.setText(notification5[x+1]);
            noti3.setForeground(c1);
            noti3.setText(notification5[x+2]);
            noti4.setForeground(c);noti5.setForeground(c);notiNext.setForeground(c);
            cid1.setText(String.valueOf(id5[x]));
            cid2.setText(String.valueOf(id5[x+1]));
            cid3.setText(String.valueOf(id5[x+2]));
        }
        else if(a == 4){
            noti1.setForeground(c1);
            noti1.setText(notification5[x]);
            noti2.setForeground(c1);
            noti2.setText(notification5[x+1]);
            noti3.setForeground(c1);
            noti3.setText(notification5[x+2]);
            noti4.setForeground(c1);
            noti4.setText(notification5[x+3]);
            noti5.setForeground(c);notiNext.setForeground(c);
            cid1.setText(String.valueOf(id5[x]));
            cid2.setText(String.valueOf(id5[x+1]));
            cid3.setText(String.valueOf(id5[x+2]));
            cid4.setText(String.valueOf(id5[x+3]));
        }
        else if(a == 5){
            noti1.setForeground(c1);
            noti1.setText(notification5[x]);
            noti2.setForeground(c1);
            noti2.setText(notification5[x+1]);
            noti3.setForeground(c1);
            noti3.setText(notification5[x+2]);
            noti4.setForeground(c1);
            noti4.setText(notification5[x+3]);
            noti5.setForeground(c1);
            noti4.setText(notification5[x+4]);
            notiNext.setForeground(c);
            cid1.setText(String.valueOf(id5[x]));
            cid2.setText(String.valueOf(id5[x+1]));
            cid3.setText(String.valueOf(id5[x+2]));
            cid4.setText(String.valueOf(id5[x+3]));
             cid5.setText(String.valueOf(id5[x+4]));
        }
        else{
            noti1.setForeground(c1);
            noti1.setText(notification5[x]);
            noti2.setForeground(c1);
            noti2.setText(notification5[x+1]);
            noti3.setForeground(c1);
            noti3.setText(notification5[x+2]);
            noti4.setForeground(c1);
            noti4.setText(notification5[x+3]);
            noti5.setForeground(c1);
            noti5.setText(notification5[x+4]);
            notiNext.setForeground(Color.ORANGE);
            cid1.setText(String.valueOf(id5[x]));
            cid2.setText(String.valueOf(id5[x+1]));
            cid3.setText(String.valueOf(id5[x+2]));
            cid4.setText(String.valueOf(id5[x+3]));
            cid5.setText(String.valueOf(id5[x+4]));
        }
    }
    private void backToNotification(){
        notiPanel.setBackground(Color.LIGHT_GRAY);
        Color c = new Color(41,44, 51);
        homePanel.setBackground(c);
        findPanel.setBackground(c);
        settingsPanel.setBackground(c);
        logoutPanel.setBackground(c);
        panelCenter.setVisible(true);        
           panelNoti.setVisible(true);
           panelHome.setVisible(false);
           panelFind.setVisible(false);
           panelSettings.setVisible(false);
           panelLawyer.setVisible(false);
        panelRating.setVisible(false);
        clicking = 1;
        finish = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet rs = null;
            String query = "select descrip, caseID from userNotification where notiReceiverID = "+clientID+" order by notiID desc";
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery();    
            while(rs.next()){
                notification5[finish] = rs.getString("descrip");
                id5[finish] = rs.getInt("caseID");
                finish++;
            }           
        } catch (Exception ex) {
            System.err.println(ex);
        }
        finish--;
        setNotification(0, finish);
    }
    private void notiPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notiPanelMouseClicked
        // TODO add your handling code here:
        notiPanel.setBackground(Color.LIGHT_GRAY);
        Color c = new Color(41,44, 51);
        homePanel.setBackground(c);
        findPanel.setBackground(c);
        settingsPanel.setBackground(c);
        logoutPanel.setBackground(c);
        panelCenter.setVisible(true);        
        if(evt.getSource() == notiPanel){
            panelNoti.setVisible(true);
            panelHome.setVisible(false);
            panelFind.setVisible(false);
            panelSettings.setVisible(false);
            panelLawyer.setVisible(false);
            panelRating.setVisible(false);
        }
        clicking = 1;
        finish = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet rs = null;
            String query = "select descrip, caseID from userNotification where notiReceiverID = "+clientID+" order by notiID desc";
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery();    
            while(rs.next()){
                notification5[finish] = rs.getString("descrip");
                id5[finish] = rs.getInt("caseID");
                finish++;
            }           
        } catch (Exception ex) {
            System.err.println(ex);
        }
        finish--;
        setNotification(0, finish);
    }//GEN-LAST:event_notiPanelMouseClicked

    public void getTop5()
    {
        String[] nam5 = new String[10]; 
        String[] ima5 = new String[10]; 
        int index = 0;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            //Statement st = connection.createStatement();
            ResultSet rs = null;
            String s4 = String.valueOf(clientID);
            System.out.println(s4);
            String query = "select top 5 lawyerName, lawyerPhoto, lawyerID from lawyerRegistration where limit < 3 order by rating desc ";
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery();
            
            while(rs.next()){
                nam5[index] = rs.getString("lawyerName");
                ima5[index] = rs.getString("lawyerPhoto");
                id5[index] = rs.getInt("lawyerID");
                index++;
            }
            n1.setText(nam5[0]);
            n2.setText(nam5[1]);
            n3.setText(nam5[2]);
            n4.setText(nam5[3]);
            n5.setText(nam5[4]);
            i1.setText(String.valueOf(id5[0]));
            i2.setText(String.valueOf(id5[1]));
            i3.setText(String.valueOf(id5[2]));
            i4.setText(String.valueOf(id5[3]));
            i5.setText(String.valueOf(id5[4]));
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(ima5[0]).getImage().getScaledInstance(p1.getWidth(), p1.getHeight(), Image.SCALE_SMOOTH));
            p1.setIcon(imageIcon);
            imageIcon = new ImageIcon(new ImageIcon(ima5[1]).getImage().getScaledInstance(p2.getWidth(), p2.getHeight(), Image.SCALE_SMOOTH));
            p2.setIcon(imageIcon);
            imageIcon = new ImageIcon(new ImageIcon(ima5[2]).getImage().getScaledInstance(p3.getWidth(), p3.getHeight(), Image.SCALE_SMOOTH));
            p3.setIcon(imageIcon);
            imageIcon = new ImageIcon(new ImageIcon(ima5[3]).getImage().getScaledInstance(p4.getWidth(), p4.getHeight(), Image.SCALE_SMOOTH));
            p4.setIcon(imageIcon);
            imageIcon = new ImageIcon(new ImageIcon(ima5[4]).getImage().getScaledInstance(p5.getWidth(), p5.getHeight(), Image.SCALE_SMOOTH));
            p5.setIcon(imageIcon);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void findPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_findPanelMouseClicked
        // TODO add your handling code here:
        findPanel.setBackground(Color.LIGHT_GRAY);
        Color c = new Color(41,44, 51);
        notiPanel.setBackground(c);
        homePanel.setBackground(c);
        settingsPanel.setBackground(c);
        logoutPanel.setBackground(c);
        if(evt.getSource() == findPanel){
            panelFind.setVisible(true);
            panelNoti.setVisible(false);
            panelHome.setVisible(false);
            panelSettings.setVisible(false);
            panelLawyer.setVisible(false);
            panelRating.setVisible(false);
        }
        next.setText("");
        getTop5();
    }//GEN-LAST:event_findPanelMouseClicked

    public void getClientList()
    {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            //Statement st = connection.createStatement();
            ResultSet rs = null;
            String s4 = String.valueOf(clientID);
            System.out.println(s4);
            String query = "select userName, phone, passwrd, email, addres, photo from userRegistration where id = "+s4;
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery();
            while(rs.next()){
                String image =  rs.getString("photo");
                txt.setText(image);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH));
                pic.setIcon(imageIcon);
                naam.setText(rs.getString("userName"));
                pas.setText(rs.getString("passwrd"));
                emil.setText(rs.getString("email"));
                con.setText(rs.getString("phone"));
                adrs.setText(rs.getString("addres"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void showItem(int index)
    {
        getClientList();
    }
    
    private void settingsPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsPanelMouseClicked
        // TODO add your handling code here:
        settingsPanel.setBackground(Color.LIGHT_GRAY);
        Color c1 = new Color(41,44, 51);
        notiPanel.setBackground(c1);
        findPanel.setBackground(c1);
        homePanel.setBackground(c1);
        logoutPanel.setBackground(c1);
        if(evt.getSource() == settingsPanel){
            panelSettings.setVisible(true);
            panelNoti.setVisible(false);
            panelFind.setVisible(false);
            panelHome.setVisible(false);
            panelLawyer.setVisible(false);
            panelRating.setVisible(false);
        }
        showItem(0);
    }//GEN-LAST:event_settingsPanelMouseClicked

    private void logoutPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new ClientLogin().setVisible(true);
    }//GEN-LAST:event_logoutPanelMouseClicked

    private void upMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upMouseClicked
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String fileName = f.getAbsolutePath();
        txt.setText(fileName);
        Image getAbsolutePath = null;
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon ac = new ImageIcon(image);
        pic.setIcon(ac);
    }//GEN-LAST:event_upMouseClicked

    private void UpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateMouseClicked
        // TODO add your handling code here:
        String nam = naam.getText();
        String pass = pas.getText();
        String mail = emil.getText();
        String phn = con.getText();
        String ad = adrs.getText();
        String image = txt.getText();
        String s4 = String.valueOf(clientID);
        String qsql = "update userRegistration set photo = '"+image+"',"+"userName = '"+nam+"',phone = '"+phn+"',passwrd = '"+pass+"',email = '"+mail+"',addres = '"+ad+"' where id = "+s4;
            if (nam.isEmpty() || pass.isEmpty() || mail.isEmpty() || phn.isEmpty() || ad.isEmpty() || image.isEmpty()) {
                notice.setText("Please fill up all fields");
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
            
    }//GEN-LAST:event_UpdateMouseClicked

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        
    }//GEN-LAST:event_comboActionPerformed

    private void lessThan5(int x, int y){
        int a = y-x+1;
        Color c = new Color(35,45, 35);
  
        if(a == 1){
            n1.setForeground(Color.WHITE);i1.setText(String.valueOf(id5[x]));
            n1.setText(nam5[x]);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(ima5[x]).getImage().getScaledInstance(p1.getWidth(), p1.getHeight(), Image.SCALE_SMOOTH));
            p1.setIcon(imageIcon);
            n2.setForeground(c);n3.setForeground(c);n4.setForeground(c);
            p2.setIcon(null);p3.setIcon(null);p4.setIcon(null);
            n5.setForeground(c);p5.setIcon(null);next.setForeground(c);
        }
        else if(a == 2){
            n1.setForeground(Color.WHITE);n2.setForeground(Color.WHITE);
            n1.setText(nam5[x]);i1.setText(String.valueOf(id5[x]));
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(ima5[x]).getImage().getScaledInstance(p1.getWidth(), p1.getHeight(), Image.SCALE_SMOOTH));
            p1.setIcon(imageIcon);
            n2.setText(nam5[x+1]);i2.setText(String.valueOf(id5[x+1]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+1]).getImage().getScaledInstance(p2.getWidth(), p2.getHeight(), Image.SCALE_SMOOTH));
            p2.setIcon(imageIcon);
            n3.setForeground(c);n4.setForeground(c);
            p3.setIcon(null);p4.setIcon(null);
            n5.setForeground(c);p5.setIcon(null);next.setForeground(c);
        }
        else if(a == 3){
            n1.setForeground(Color.WHITE);n2.setForeground(Color.WHITE);n3.setForeground(Color.WHITE);
            n1.setText(nam5[x]);i1.setText(String.valueOf(id5[x]));
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(ima5[x]).getImage().getScaledInstance(p1.getWidth(), p1.getHeight(), Image.SCALE_SMOOTH));
            p1.setIcon(imageIcon);
            n2.setText(nam5[x+1]);i2.setText(String.valueOf(id5[x+1]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+1]).getImage().getScaledInstance(p2.getWidth(), p2.getHeight(), Image.SCALE_SMOOTH));
            p2.setIcon(imageIcon);
            n3.setText(nam5[x+2]);i3.setText(String.valueOf(id5[x+2]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+2]).getImage().getScaledInstance(p3.getWidth(), p3.getHeight(), Image.SCALE_SMOOTH));
            p3.setIcon(imageIcon);
            n4.setForeground(c);
            p4.setIcon(null);
            n5.setForeground(c);p5.setIcon(null);next.setForeground(c);
        }
        else if(a == 4){
            n1.setForeground(Color.WHITE);n2.setForeground(Color.WHITE);n3.setForeground(Color.WHITE);n4.setForeground(Color.WHITE);
            n1.setText(nam5[x]);i1.setText(String.valueOf(id5[x]));
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(ima5[x]).getImage().getScaledInstance(p1.getWidth(), p1.getHeight(), Image.SCALE_SMOOTH));
            p1.setIcon(imageIcon);
            n2.setText(nam5[x+1]);i2.setText(String.valueOf(id5[x+1]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+1]).getImage().getScaledInstance(p2.getWidth(), p2.getHeight(), Image.SCALE_SMOOTH));
            p2.setIcon(imageIcon);
            n3.setText(nam5[x+2]);i3.setText(String.valueOf(id5[x+2]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+2]).getImage().getScaledInstance(p3.getWidth(), p3.getHeight(), Image.SCALE_SMOOTH));
            p3.setIcon(imageIcon);
            n4.setText(nam5[x+3]);i4.setText(String.valueOf(id5[x+3]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+3]).getImage().getScaledInstance(p4.getWidth(), p4.getHeight(), Image.SCALE_SMOOTH));
            p4.setIcon(imageIcon);
            n5.setForeground(c);p5.setIcon(null);next.setForeground(c);
        }
        else if(a == 5){
            n1.setForeground(Color.WHITE);n2.setForeground(Color.WHITE);n3.setForeground(Color.WHITE);n4.setForeground(Color.WHITE);n5.setForeground(Color.WHITE);
            n1.setText(nam5[x]);i1.setText(String.valueOf(id5[x]));
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(ima5[x]).getImage().getScaledInstance(p1.getWidth(), p1.getHeight(), Image.SCALE_SMOOTH));
            p1.setIcon(imageIcon);
            n2.setText(nam5[x+1]);i2.setText(String.valueOf(id5[x+1]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+1]).getImage().getScaledInstance(p2.getWidth(), p2.getHeight(), Image.SCALE_SMOOTH));
            p2.setIcon(imageIcon);
            n3.setText(nam5[x+2]);i3.setText(String.valueOf(id5[x+2]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+2]).getImage().getScaledInstance(p3.getWidth(), p3.getHeight(), Image.SCALE_SMOOTH));
            p3.setIcon(imageIcon);
            n4.setText(nam5[x+3]);i4.setText(String.valueOf(id5[x+3]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+3]).getImage().getScaledInstance(p4.getWidth(), p4.getHeight(), Image.SCALE_SMOOTH));
            p4.setIcon(imageIcon);
            n5.setText(nam5[x+4]);i5.setText(String.valueOf(id5[x+4]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+4]).getImage().getScaledInstance(p5.getWidth(), p5.getHeight(), Image.SCALE_SMOOTH));
            p5.setIcon(imageIcon);
            next.setForeground(c);
        }
        else{
            n1.setForeground(Color.WHITE);n2.setForeground(Color.WHITE);n3.setForeground(Color.WHITE);n4.setForeground(Color.WHITE);n5.setForeground(Color.WHITE);
            n1.setText(nam5[x]);i1.setText(String.valueOf(id5[x]));
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(ima5[x]).getImage().getScaledInstance(p1.getWidth(), p1.getHeight(), Image.SCALE_SMOOTH));
            p1.setIcon(imageIcon);
            n2.setText(nam5[x+1]);i2.setText(String.valueOf(id5[x+1]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+1]).getImage().getScaledInstance(p2.getWidth(), p2.getHeight(), Image.SCALE_SMOOTH));
            p2.setIcon(imageIcon);
            n3.setText(nam5[x+2]);i3.setText(String.valueOf(id5[x+2]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+2]).getImage().getScaledInstance(p3.getWidth(), p3.getHeight(), Image.SCALE_SMOOTH));
            p3.setIcon(imageIcon);
            n4.setText(nam5[x+3]);i4.setText(String.valueOf(id5[x+3]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+3]).getImage().getScaledInstance(p4.getWidth(), p4.getHeight(), Image.SCALE_SMOOTH));
            p4.setIcon(imageIcon);
            n5.setText(nam5[x+4]);i5.setText(String.valueOf(id5[x+4]));
            imageIcon = new ImageIcon(new ImageIcon(ima5[x+4]).getImage().getScaledInstance(p5.getWidth(), p5.getHeight(), Image.SCALE_SMOOTH));
            p5.setIcon(imageIcon);
            next.setForeground(Color.BLUE);
            next.setText("Next");
        }
    }

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        String t = (String)combo.getSelectedItem();
        clicking = 1;
        finish = 0;
        Color c = new Color(41,44, 51);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet rs = null;
            String query = "select lawyerName, lawyerPhoto, lawyerID from lawyerRegistration where typ = '"+t+"' and limit < 3  order by rating desc";
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery();                
            while(rs.next()){
                nam5[finish] = rs.getString("lawyerName");
                ima5[finish] = rs.getString("lawyerPhoto");
                id5[finish] = rs.getInt("lawyerID");
                finish++;
            }
            finish--;
            lessThan5(0, finish);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }//GEN-LAST:event_searchActionPerformed

    private void nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseClicked
        // TODO add your handling code here:
        lessThan5(5*clicking, finish);
        clicking++;
    }//GEN-LAST:event_nextMouseClicked

    private void fullLawyerInformation(String s){
            panelLawyer.setVisible(true);
            panelNoti.setVisible(false);
            panelHome.setVisible(false);
            panelFind.setVisible(false);
            panelSettings.setVisible(false);
            panelRating.setVisible(false);
            specificLawyerId.setText(s);
            try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet rs = null;
            String query = "select lawyerID, lawyerName, rating, win, loss, totalCases, officeAddress, email, lawyerPhoto from lawyerRegistration where lawyerID = "+s;
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery(); 
            float db;
            int k, W = 0, L = 0, percen = 0;
            while(rs.next()){
                LawyerID = rs.getInt("lawyerID");
                lawName.setText(rs.getString("lawyerName"));
                db = rs.getFloat("rating");
                lawRate.setText(String.valueOf(db));
                k = rs.getInt("win");
                W = k;
                lawWin.setText(String.valueOf(k));
                k = rs.getInt("loss");
                lawLoss.setText(String.valueOf(k));
                k = rs.getInt("totalCases");
                L = k;
                lawTotal.setText(String.valueOf(k));
                lawAdd.setText(rs.getString("officeAddress"));
                lawMail.setText(rs.getString("email"));
                String P = rs.getString("lawyerPhoto");
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(P).getImage().getScaledInstance(lawPic.getWidth(), lawPic.getHeight(), Image.SCALE_SMOOTH));
                lawPic.setIcon(imageIcon);
            }
            percen = W*100;
            if(L == 0)L = 1;
            percen = percen/L;
            if(percen > 100)percen = 100;
            lawSuccess.setText(String.valueOf(percen)+"%");
            lawNotice.setText("view sent request");
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    private void p1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p1MouseClicked
        // TODO add your handling code here:
            fullLawyerInformation(i1.getText());
    }//GEN-LAST:event_p1MouseClicked

    private void p1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_p1MouseEntered

    private void p2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseClicked
        // TODO add your handling code here:
        fullLawyerInformation(i2.getText());
    }//GEN-LAST:event_p2MouseClicked

    private void p3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseClicked
        // TODO add your handling code here:
        fullLawyerInformation(i3.getText());
    }//GEN-LAST:event_p3MouseClicked

    private void p4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseClicked
        // TODO add your handling code here:
        fullLawyerInformation(i4.getText());
    }//GEN-LAST:event_p4MouseClicked

    private void p5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p5MouseClicked
        // TODO add your handling code here:
        fullLawyerInformation(i5.getText());
    }//GEN-LAST:event_p5MouseClicked

    private void lawName11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lawName11MouseClicked
        // TODO add your handling code here:
        int limit = 0;
        lawMsg.setText(" ");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet rs = null;
            String query = "select sentRequest from userRegistration where id = "+clientID;
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery();                
            while(rs.next()){
                limit = rs.getInt("sentRequest");
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        if(limit == 5){
            JOptionPane.showMessageDialog(null,"You have already sent 5 requests");
        }
        else{
        String msg = lawMsg.getText();
        if(!msg.equals("")){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection connection = DriverManager
                        .getConnection(
                                "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
                String sql = "Insert into request"
                    +"(lawyerID,id,clientMsg, flag)"
                    +"values(?,?,?,?)";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, LawyerID);
                pst.setInt(2, clientID);
                pst.setString(3, msg);
                 pst.setInt(4, 1);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Request sent");
                limit++;
                String q = String.valueOf(limit);
                sql = "update userRegistration set  sentRequest ="+q+" where id = "+clientID;
                pst = connection.prepareStatement(sql);
                pst.executeUpdate();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Type your message please");
        }
        }
    }//GEN-LAST:event_lawName11MouseClicked

    private void lawName3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lawName3MouseClicked
        // TODO add your handling code here:
            panelSettings.setVisible(false);
            panelNoti.setVisible(false);
            panelFind.setVisible(true);
            panelHome.setVisible(false);
            panelLawyer.setVisible(false);
            panelRating.setVisible(false);
    }//GEN-LAST:event_lawName3MouseClicked

    private void notiNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notiNextMouseClicked
        // TODO add your handling code here:
        setNotification(5*clicking, finish);
        clicking++;
    }//GEN-LAST:event_notiNextMouseClicked

    private void giveRatingToLawyer(){
            panelSettings.setVisible(false);
            panelNoti.setVisible(false);
            panelFind.setVisible(false);
            panelHome.setVisible(false);
            panelLawyer.setVisible(false);
            panelRating.setVisible(true);
            rateLawName.setText(lawyerName);
    }
    private void noti1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noti1MouseClicked
        // TODO add your handling code here:
        String q = noti1.getText().substring(0, 3);
        String wq = noti1.getText();
        CID = Integer.parseInt(cid1.getText());
        System.out.println(CID);
        lawyerName = noti1.getText().substring(17, wq.length());
        if(q.equals("You")){
            giveRatingToLawyer();
        }
        else{
            
        }
    }//GEN-LAST:event_noti1MouseClicked

    private void rateNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rateNameMouseClicked
        // TODO add your handling code here:
        String R = rateField.getText();
        float r = Float.parseFloat(R);
        String fb = feedbackField.getText();
        String a = String.valueOf(CID);
        float rateDB = 0;
        int w = 0, l = 0, t = 0;
        if(r > 5.0 || r < 0.0){
            ratingNotice.setText("You have to rate between 0.0-5.0");
        }
        else{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet rs = null;
            String query = "update cases set done = 1, rating = "+R+", feedback = '"+fb+"' where caseID = "+a;
            PreparedStatement pst = connection.prepareStatement(query);
            pst.executeUpdate(); 
            query = "select rating, win, loss, totalCases from lawyerRegistration where lawyerName = '"+lawyerName+"'";
            prepStmnt = connection.prepareStatement(query);
            rs = prepStmnt.executeQuery();                
            while(rs.next()){
                rateDB = rs.getFloat("rating");
                w = rs.getInt("win");
                l = rs.getInt("loss");
                t = rs.getInt("totalCases");
            }
            t++;
            if(WIN == 1){
                w++;
            }
            else l++;
            rateDB = (rateDB+r)/2;
            query = "update lawyerRegistration set rating = "+String.valueOf(rateDB)+" , win = "
                    +String.valueOf(w)+" , loss = "+String.valueOf(l)+" , totalCases = "+String.valueOf(t)
                    +" where lawyerName = '"+lawyerName+"'";
            pst = connection.prepareStatement(query);
            pst.executeUpdate();
            ratingNotice.setText("Thanks for your rating and feedback!");
        } catch (Exception ex) {
            System.err.println(ex);
        }
        }
    }//GEN-LAST:event_rateNameMouseClicked

    private void winButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_winButtonActionPerformed
        // TODO add your handling code here:
        WIN = 1;
    }//GEN-LAST:event_winButtonActionPerformed

    private void lossButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lossButtonActionPerformed
        // TODO add your handling code here:
        WIN = 0;
    }//GEN-LAST:event_lossButtonActionPerformed

    private void backNotiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backNotiMouseClicked
        // TODO add your handling code here:
        backToNotification();
    }//GEN-LAST:event_backNotiMouseClicked

    private void noti2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noti2MouseClicked
        // TODO add your handling code here:
        String q = noti2.getText().substring(0, 3);
        String wq = noti2.getText();
        CID = Integer.parseInt(cid2.getText());
        System.out.println(CID);
        lawyerName = noti2.getText().substring(17, wq.length());
        if(q.equals("You")){
            giveRatingToLawyer();
        }
        else{
            
        }
    }//GEN-LAST:event_noti2MouseClicked

    private void noti3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noti3MouseClicked
        // TODO add your handling code here:
        String q = noti3.getText().substring(0, 3);
        String wq = noti3.getText();
        CID = Integer.parseInt(cid3.getText());
        System.out.println(CID);
        lawyerName = noti3.getText().substring(17, wq.length());
        if(q.equals("You")){
            giveRatingToLawyer();
        }
        else{
            
        }
    }//GEN-LAST:event_noti3MouseClicked

    private void noti4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noti4MouseClicked
        // TODO add your handling code here:
        String q = noti4.getText().substring(0, 3);
        String wq = noti4.getText();
        CID = Integer.parseInt(cid4.getText());
        System.out.println(CID);
        lawyerName = noti4.getText().substring(17, wq.length());
        if(q.equals("You")){
            giveRatingToLawyer();
        }
        else{
            
        }
    }//GEN-LAST:event_noti4MouseClicked

    private void noti5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noti5MouseClicked
        // TODO add your handling code here:
        String q = noti5.getText().substring(0, 3);
        String wq = noti5.getText();
        CID = Integer.parseInt(cid5.getText());
        System.out.println(CID);
        lawyerName = noti5.getText().substring(17, wq.length());
        if(q.equals("You")){
            giveRatingToLawyer();
        }
        else{
            
        }
    }//GEN-LAST:event_noti5MouseClicked

    private void homeNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeNameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_homeNameMouseClicked

    private void case5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_case5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_case5MouseClicked

    private void noti8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noti8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_noti8MouseClicked

    private void case1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_case1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_case1MouseClicked

    private void case2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_case2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_case2MouseClicked

    private void case3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_case3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_case3MouseClicked

    private void case4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_case4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_case4MouseClicked

    private void lawNoticeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lawNoticeMouseClicked
        // TODO add your handling code here:
        new cancleRequest(String.valueOf(clientID)).setVisible(true);
        
    }//GEN-LAST:event_lawNoticeMouseClicked

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
            java.util.logging.Logger.getLogger(ClientDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientDashboard("f").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Update;
    private javax.swing.JTextField adrs;
    private javax.swing.JLabel backNoti;
    private javax.swing.JLabel bam;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel case1;
    private javax.swing.JLabel case2;
    private javax.swing.JLabel case3;
    private javax.swing.JLabel case4;
    private javax.swing.JLabel case5;
    private javax.swing.JLabel cid1;
    private javax.swing.JLabel cid2;
    private javax.swing.JLabel cid3;
    private javax.swing.JLabel cid4;
    private javax.swing.JLabel cid5;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JTextField con;
    private javax.swing.JLabel dan;
    private javax.swing.JTextField emil;
    private javax.swing.JTextArea feedbackField;
    private javax.swing.JPanel findPanel;
    private javax.swing.JLabel home;
    private javax.swing.JLabel home1;
    private javax.swing.JLabel home2;
    private javax.swing.JLabel home3;
    private javax.swing.JLabel home4;
    private javax.swing.JLabel homeName;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel homePic;
    private javax.swing.JLabel i1;
    private javax.swing.JLabel i2;
    private javax.swing.JLabel i3;
    private javax.swing.JLabel i4;
    private javax.swing.JLabel i5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lawAdd;
    private javax.swing.JLabel lawLoss;
    private javax.swing.JLabel lawMail;
    private javax.swing.JTextArea lawMsg;
    private javax.swing.JLabel lawName;
    private javax.swing.JLabel lawName1;
    private javax.swing.JLabel lawName10;
    private javax.swing.JLabel lawName11;
    private javax.swing.JLabel lawName12;
    private javax.swing.JLabel lawName14;
    private javax.swing.JLabel lawName16;
    private javax.swing.JLabel lawName17;
    private javax.swing.JLabel lawName18;
    private javax.swing.JLabel lawName3;
    private javax.swing.JLabel lawName4;
    private javax.swing.JLabel lawName5;
    private javax.swing.JLabel lawName6;
    private javax.swing.JLabel lawName7;
    private javax.swing.JLabel lawName8;
    private javax.swing.JLabel lawNotice;
    private javax.swing.JLabel lawPic;
    private javax.swing.JLabel lawRate;
    private javax.swing.JLabel lawSuccess;
    private javax.swing.JLabel lawTotal;
    private javax.swing.JLabel lawWin;
    private javax.swing.JPanel logoutPanel;
    private javax.swing.JRadioButton lossButton;
    private javax.swing.JLabel n1;
    private javax.swing.JLabel n2;
    private javax.swing.JLabel n3;
    private javax.swing.JLabel n4;
    private javax.swing.JLabel n5;
    private javax.swing.JTextField naam;
    private javax.swing.JLabel next;
    private javax.swing.JLabel noti1;
    private javax.swing.JLabel noti2;
    private javax.swing.JLabel noti3;
    private javax.swing.JLabel noti4;
    private javax.swing.JLabel noti5;
    private javax.swing.JLabel noti8;
    private javax.swing.JLabel notiNext;
    private javax.swing.JPanel notiPanel;
    private javax.swing.JLabel notice;
    private javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel p3;
    private javax.swing.JLabel p4;
    private javax.swing.JLabel p5;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelFind;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLawyer;
    private javax.swing.JPanel panelNoti;
    private javax.swing.JPanel panelRating;
    private javax.swing.JPanel panelSettings;
    private javax.swing.JTextField pas;
    private javax.swing.JLabel pic;
    private javax.swing.JTextField rateField;
    private javax.swing.JLabel rateLawName;
    private javax.swing.JLabel rateName;
    private javax.swing.JLabel ratingNotice;
    private javax.swing.JButton search;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JLabel specificLawyerId;
    private javax.swing.JTable table;
    private javax.swing.JLabel txt;
    private java.awt.Button up;
    private javax.swing.JLabel user;
    private javax.swing.JRadioButton winButton;
    // End of variables declaration//GEN-END:variables
}

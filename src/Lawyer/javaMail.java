/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lawyer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Asus
 */
public class javaMail {
    public static int flag = 0;
    private static String msg = "";
    public static void sendMail(String recepient, int n) throws MessagingException
    {
        flag = n;
        msg = recepient;
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String myAccountEmail="ukilchai7@gmail.com";
        String password="Suvomsaha";
        
       Session session=Session.getInstance(properties, new Authenticator()
       {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        
       });
       Message message=prepareMessage(session,myAccountEmail,recepient);
       
       Transport.send(message);
       System.out.println("Message sent succesfully");
       
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        String a = "";
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject("Your password ");
            if(flag == 0){
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet dataset = null;
            String query = "select passwrd from lawyerRegistration where email = '"+msg+"'";
            prepStmnt = connection.prepareStatement(query);
            dataset = prepStmnt.executeQuery();
            while(dataset.next()){
                a = dataset.getString("passwrd");
            }
            }
            else{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager
            .getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=LawFirm;selectMethod=cursor", "sa", "123456");
            PreparedStatement prepStmnt = null;
            ResultSet dataset = null;
            String query = "select passwrd from userRegistration where email = '"+msg+"'";
            prepStmnt = connection.prepareStatement(query);
            dataset = prepStmnt.executeQuery();
            while(dataset.next()){
                a = dataset.getString("passwrd");
            }
            }
            System.out.println(a);
            message.setText(a);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(javaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}

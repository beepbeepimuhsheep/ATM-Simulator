
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author melam
 */
public class Account {
    int id;
    double balance;
    String dateCreated; 
    double initbalance; 
    Database data = new Database();
    JOptionPane jpane = new JOptionPane();
    Map<String, Object> map = new HashMap();
    
    public Account(int window1ID, double initial, String username){
        data.write(window1ID, initial, username);
    }
    
    public Account(int window1ID){
        setID(window1ID);
    }
    
    
    public void setID(int window1ID){
        id = window1ID;
        setBalance();                                                           
    }
    public void setBalance(){
        map = data.read(id);
        balance = Double.parseDouble(map.get("Balance").toString());
    }
    
    public double withdraw(double withdrawAmt){
        balance -= withdrawAmt;
        data.update(balance, id);
        return balance;
    }
    
    public double deposit(double depositAmt){
        balance += depositAmt;
        data.update(balance, id);
        return balance;
    }
    
    public int getID(){
        return id;
    }
    public double getBalance(){
        return balance;
    }
    public String getDate(){
        return dateCreated;
    }
    
    public void printStatement(){
       map = data.read(id);
       LocalDate date = LocalDate.now();
       String text = "Account holder details for " + date + "\nUser ID: " + map.get("ID").toString() 
                   + "\nName: " + map.get("Username").toString() + "\nCurrent balance: R" 
                   + map.get("Balance").toString();
       JOptionPane.showMessageDialog(jpane, text);
    }
    
}

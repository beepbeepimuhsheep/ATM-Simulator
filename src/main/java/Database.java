
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class Database {
    JOptionPane jpane;
    Connection con;
    Statement statment;
    ResultSet result;
    HashMap<String, Object> map = new HashMap();
    
    public void connection(){
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:jtds:sqlserver://LAPTOP-6H0A6VN0;"
                                                       + "databaseName=Account_SAJD522;"
                                                       + "encrypt=true;"
                                                       + "trustServerCertificate=true;"
                                                       + "user=JD522;"
                                                       + "password=JD522FA");
            statment = con.createStatement();
            
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    
    public Map<String, Object> read(int id){
        jpane = new JOptionPane();
        try{
            connection();
            result = statment.executeQuery("SELECT ID, USERNAME, CURRENTBALANCE FROM Accounts WHERE ID = '" + id + "'");
            
            while(result.next()){
               map.put("ID", result.getInt(1));
               map.put("Username", result.getString(2));
               map.put("Balance", result.getDouble(3));
            }
                
            con.close();
            
        }
        catch(HeadlessException | SQLException e){
            System.out.println(e);
        }
        return map;
    }
    
    public void write(int id, double initial, String username){
        try{
           connection();
            statment.executeUpdate("Insert into Accounts(ID, USERNAME, INITIALBALANCE, CURRENTBALANCE) " +
                                           "values ('"+id+"', '"+username+"', '"+initial+"', '"+initial+"')");
               
            JOptionPane.showMessageDialog(jpane, "Account information saved!");
            
            con.close();
            
        }
        catch(HeadlessException | SQLException e){
            System.out.println(e);
        }
        
    }
    
    public void update(double balance, int id){
        jpane = new JOptionPane();
        try{
            connection();
            statment.executeUpdate("Update Accounts SET CURRENTBALANCE = '"+balance+"' WHERE ID = '"+id+"'");
               
            con.close();
            
        }
        catch(HeadlessException | SQLException e){
            System.out.println(e);
        }
     
    }
    
    
    
    
}

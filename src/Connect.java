/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raj
 */
import java.sql.*;
import javax.swing.*;
public class Connect {
     Connection con=null;
   
        public static Connection ConnectDB(){
             try{
           
         Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
         Connection con = DriverManager.getConnection("jdbc:derby:mall_mgm;create=true;","happy","happy");
         System.out.println("Connection OK ");
          return con;
            
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
    }      
}
}

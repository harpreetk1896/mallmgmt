/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Harpreet
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DisplayDistData extends JFrame  {

    Connection conn;
    ResultSet  rs1;
    Statement  st1;
    PreparedStatement pst;
    static JTable table;
    private static String Name=null,Add=null,No=null,distid=null,sql=null;
    String[] columnNames = {"Distid","Name","Contact_No","Address"};
   
    DefaultTableModel model ;
    DisplayDistData() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("Database Search Result");
        setLayout(new BorderLayout());
        setLocation(390,160);
        setSize(650,400);
        model = new DefaultTableModel();
        sql="select * from happy.distributor";
        showTable();
        setVisible(true);
       
    }

    DisplayDistData(DefaultTableModel m) {
        model=m;
        sql="select * from happy.distributor";
        showTable();
       }
    DisplayDistData(DefaultTableModel m, String s) {
        model=m;
        sql = s;
        showTable();
    }
    
    public void showTable() {
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       
        

        try {
            conn = Connect.ConnectDB();
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                Name = rs.getString("dname");
                Add = rs.getString("daddress");
                distid = rs.getString("distid");
                No = rs.getString("dcontact_no");
                
                model.addRow(new Object[]{distid,Name,No,Add});
                //JOptionPane.showMessageDialog(null, "Found", "Error", JOptionPane.ERROR_MESSAGE);
                i++;
            }
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        add(scroll);
    }

    public static void main(String args[]) {
        new DisplayDistData();
    }
}
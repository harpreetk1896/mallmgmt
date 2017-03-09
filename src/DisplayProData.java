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

public class DisplayProData extends JFrame  {

    Connection conn;
    ResultSet  rs1;
    Statement  st1;
    PreparedStatement pst;
    String ids;
    static JTable table;
    private static String Name=null,Info=null,Pid=null,Price=null;
    String[] columnNames = {"P_id","Name","Info","Price"};
    String from;
    DefaultTableModel model; 
    String sql = "select * from happy.product";
    
    DisplayProData() {
            
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("Database Search Result");
        setLayout(new BorderLayout());
        setLocation(390,160);
        setSize(650,400);
        model = new DefaultTableModel();
        showTableData();
        setVisible(true);
    }

    DisplayProData(DefaultTableModel m,String s) {
        model=m;
        sql = s;
        showTableData();
       }
    public void showTableData() {

        model.setColumnIdentifiers(columnNames);
        model.setRowCount(0);
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
                Name = rs.getString("pname");
                Info= rs.getString("pinfo");
                Pid = rs.getString("pid");
                Price = rs.getString("s_price");
                
                model.addRow(new Object[]{Pid,Name,Info,Price});
                //JOptionPane.showMessageDialog(null, "Found", "Error", JOptionPane.ERROR_MESSAGE);
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                //System.out.println(i + " Record Found");
            } else {
                //System.out.println(i + " Records Found");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        add(scroll);
        
        //setSize(400, 300);
    }

    public static void main(String args[]) {
        new DisplayProData();
    }
}
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

public class DisplayStockData extends JFrame  {

    Connection conn;
    ResultSet  rs1;
    Statement  st1;
    PreparedStatement pst;
    String ids;
    static JTable table;
    private static String Distid=null,Pid=null;
    private static int Qty=0,Price=0;
    String[] columnNames = {"P_id","Dist_ID","Quantity","Price"};
    String from;
    DefaultTableModel model; 
    
    DisplayStockData() {
            
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("Database Search Result");
        setLayout(new BorderLayout());
        setLocation(390,160);
        setSize(650,400);
        model = new DefaultTableModel();
        showTableData();
        setVisible(true);
    }

    DisplayStockData(DefaultTableModel m) {
        model=m;
        showTableData();
       }
    public void showTableData() {

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
            pst = conn.prepareStatement("select * from happy.stock");
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                Qty = rs.getInt("qty");
                Price= rs.getInt("price");
                Pid = rs.getString("pid");
                Distid = rs.getString("distid");
                
                model.addRow(new Object[]{Pid,Distid,Qty,Price});
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
        new DisplayStockData();
    }
}
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
import java.text.MessageFormat;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DisplayProData extends JFrame  {

    Connection conn;
    ResultSet  rs1;
    Statement  st1;
    PreparedStatement pst;
    static JTable table;
    private static String Name=null,Info=null,Pid=null,Price=null;
    String[] columnNames = {"P_id","Name","Info","Price"};
    DefaultTableModel model; 
    String sql = "select * from happy.product";
     
    
    DisplayProData() {
            
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("Database Search Result");
        setLayout(new BorderLayout());
        setLocation(390,160);
        setSize(650,400);
        model = new DefaultTableModel();
        
        showTableData2();
        setVisible(true);
    }

    DisplayProData(DefaultTableModel m,String s) {
        model=m;
        sql = s;
        showTableData();
       }
    DisplayProData(int m) {
        
        showTableData2();
       }
    public void showTableData() {

        model.setColumnIdentifiers(columnNames);
        model.setRowCount(0);
        table = new JTable();
        table.setModel(model);
        
        
       
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
        setVisible(true);
        //setSize(400, 300);
    }
    public void showTableData2() {

        String[] columnName = {"ITEM DESCRIPTION","QTY","AMT"};
        DefaultTableModel model1 ;
        JTable table1;
        model1=new DefaultTableModel();
        model1.setColumnIdentifiers(columnName);
        model1.setRowCount(0);
        table1 = new JTable();
        table1.setModel(model1);
        
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.getColumnModel().getColumn(0).setPreferredWidth(30);
        table1.getColumnModel().getColumn(1).setPreferredWidth(3);
        table1.getColumnModel().getColumn(2).setPreferredWidth(6);
        //********************************************************************
        
       
        table1.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table1);
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
                
                model1.addRow(new Object[]{Pid,Name,Info});
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
    
    }

    public static void main(String args[]) {
        new DisplayProData();
    }
}
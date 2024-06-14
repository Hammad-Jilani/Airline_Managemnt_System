import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightInfo extends JFrame {
    public FlightInfo(){
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JTable table = new JTable();
        try {
            Connect c = new Connect();
            String query = "select * from flight";
            ResultSet set = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(set));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(150,100,1000,400);
        add(pane);

        setVisible(true);
        setResizable(false);
        setTitle("Airline Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    public static void main(String[] args) {
        new FlightInfo();
    }
}

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JourneyDetails extends JFrame implements ActionListener {
    JTable table;
    JTextField PNR,Ticket;
    JButton show;
    public JourneyDetails(){

        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel lblpnr = new JLabel("PNR Details");
        lblpnr.setBounds(50,50,100,25);
        lblpnr.setFont(new Font("serif",Font.PLAIN,20));
        add(lblpnr);

        PNR =new JTextField();
        PNR.setBounds(200,50,250,25);
        add(PNR);

        show = new JButton("Show");
        show.setBounds(500,50,100,30);
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.addActionListener(this);
        add(show);

        table = new JTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100,100,800,400);
        add(scrollPane);

        setVisible(true);
        setResizable(false);
        setTitle("Airline Management System");
        setLocation(20,20);
        setSize(1200,700);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    public static void main(String[] args) {
        new JourneyDetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connect con = new Connect();
        String query = "select * from reservations where PNR = '"+PNR.getText()+"' ";
        try {
            ResultSet set = con.s.executeQuery(query);
                if (!set.isBeforeFirst()){
                    JOptionPane.showMessageDialog(null,"No record found");
                }
                table.setModel(DbUtils.resultSetToTableModel(set));
            TableColumn column = table.getColumnModel().getColumn(2);
            column.setPreferredWidth(100);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardingPass extends JFrame implements ActionListener{
    JTextField tfPNR;
    JButton enter;
    JLabel lblName,lblFlightName,lblFlightCode,lblDate,lblNationality,lblSource,lblDestination;
    public BoardingPass(){
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Pakistan International Airline");
        heading.setBounds(250,0,500,50);
        heading.setFont(new Font("Roboto",Font.BOLD,30));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setBounds(350,40,500,40);
        subheading.setFont(new Font("Roboto",Font.BOLD,20));
        subheading.setForeground(Color.BLUE);
        add(subheading);

        JLabel pnr = new JLabel("PNR details :");
        pnr.setBounds(50,140,120,30);
        add(pnr);

        tfPNR = new JTextField();
        tfPNR.setBounds(200,140,200,30);
        add(tfPNR);

        enter = new JButton("Enter");
        enter.setBounds(450,140,100,30);
        enter.setBackground(Color.BLACK);
        enter.addActionListener(this);
        enter.setForeground(Color.WHITE);
        add(enter);

        JLabel name = new JLabel("Name :");
        name.setBounds(50,170,120,30);
        add(name);

        lblName = new JLabel();
        lblName.setBounds(200,170,120,30);
        add(lblName);

        JLabel nationality = new JLabel("Nationality :");
        nationality.setBounds(50,200,120,30);
        add(nationality);

        lblNationality = new JLabel();
        lblNationality.setBounds(200,200,120,30);
        add(lblNationality);

        JLabel Source = new JLabel("Source :");
        Source.setBounds(50,230,120,30);
        add(Source);

        lblSource = new JLabel();
        lblSource.setBounds(200,230,120,30);
        add(lblSource);

        JLabel Destination = new JLabel("Destination :");
        Destination.setBounds(50,260,120,30);
        add(Destination);

        lblDestination = new JLabel();
        lblDestination.setBounds(200,260,120,30);
        add(lblDestination);

        JLabel FlightName = new JLabel("FlightName :");
        FlightName.setBounds(50,290,120,30);
        add(FlightName);

        lblFlightName = new JLabel();
        lblFlightName.setBounds(200,290,120,30);
        add(lblFlightName);

        JLabel FlightCode = new JLabel("Flight Code :");
        FlightCode.setBounds(50,320,120,30);
        add(FlightCode);

        lblFlightCode = new JLabel();
        lblFlightCode.setBounds(200,320,120,30);
        add(lblFlightCode);

        JLabel Date = new JLabel("Date :");
        Date.setBounds(50,350,120,30);
        add(Date);

        lblDate = new JLabel();
        lblDate.setBounds(200,350,120,30);
        add(lblDate);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/pia.png"));
        Image i2 = i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(200,380,250,250);
        add(image);

        setVisible(true);
        setSize(800,700);
        setResizable(false);
        setLocation(250,10);
    }
    public static void main(String[] args) {
        new BoardingPass();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connect c = new Connect();
        String query = "select * from reservations where PNR = '"+tfPNR.getText()+"'";
        try {
            ResultSet set = c.s.executeQuery(query);
            if (set.next()){
                lblName.setText(set.getString("name"));
                lblNationality.setText(set.getString("nationality"));
                lblFlightCode.setText(set.getString("flightCode"));
                lblFlightName.setText(set.getString("flightName"));
                lblDate.setText(set.getString("date"));
                lblSource.setText(set.getString("source"));
                lblDestination.setText(set.getString("destionation"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


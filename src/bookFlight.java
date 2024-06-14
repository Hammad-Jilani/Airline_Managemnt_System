import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class bookFlight extends JFrame implements ActionListener{
    JTextField tfCNIC;
    JButton book,fetch,fetch_flight;
    JLabel lblName,lblNationality,lblAddress,lblGender,lblflightName,lblflightCode;
    Choice cSource,cDestination;
    JDateChooser cdate;
    public bookFlight(){
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(250,0,500,50);
        heading.setFont(new Font("Roboto",Font.BOLD,30));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel CNIC =new JLabel("CNIC");
        CNIC.setBounds(40,60,100,20);
        add(CNIC);

        tfCNIC = new JTextField();
        tfCNIC.setBounds(150,60,200,20);
        add(tfCNIC);

        fetch = new JButton("Fetch");
        fetch.setBackground(Color.BLACK);
        fetch.setBounds(360,60,120,20);
        fetch.setForeground(Color.white);
        fetch.addActionListener(this);
        add(fetch);

        JLabel name = new JLabel("Name :");
        name.setBounds(40,100,90,20);
        add(name);

        lblName = new JLabel();
        lblName.setBounds(150,100,200,20);
        add(lblName);

        JLabel nationality = new JLabel("Nationality :");
        nationality.setBounds(40,140,100,20);
        add(nationality);

        lblNationality = new JLabel();
        lblNationality.setBounds(150,140,200,20);
        add(lblNationality);

        JLabel address = new JLabel("Address :");
        address.setBounds(40,180,100,20);
        add(address);

        lblAddress = new JLabel();
        lblAddress.setBounds(150,180,200,20);
        add(lblAddress);

        JLabel Gender = new JLabel("Gender :");
        Gender.setBounds(40,220,100,20);
        add(Gender);

        lblGender = new JLabel();
        lblGender.setBounds(150,220,200,20);
        add(lblGender);

        JLabel source = new JLabel("Source");
        source.setBounds(40,260,100,20);
        add(source);

        cSource = new Choice();
        cSource.add("Karachi");
        cSource.add("Islamabad");
        cSource.add("Lahore");
        cSource.add("Peshawar");
        cSource.add("Quetta");
        cSource.setBounds(150,260,200,20);
        add(cSource);

        JLabel destination = new JLabel("Destination");
        destination.setBounds(40,300,100,20);
        add(destination);

        cDestination = new Choice();
        cDestination.add("Karachi");
        cDestination.add("Islamabad");
        cDestination.add("Lahore");
        cDestination.add("Peshawar");
        cDestination.add("Quetta");
        cDestination.setBounds(150,300,200,20);
        add(cDestination);

        fetch_flight = new JButton("Fetch Flight");
        fetch_flight.setBackground(Color.BLACK);
        fetch_flight.setBounds(360,300,120,20);
        fetch_flight.setForeground(Color.white);
        fetch_flight.addActionListener(this);
        add(fetch_flight);

        JLabel flightName = new JLabel("flight Name:");
        flightName.setBounds(40,340,120,20);
        add(flightName);

        lblflightName = new JLabel();
        lblflightName.setBounds(150,340,120,20);
        add(lblflightName);

        JLabel flightCode = new JLabel("Flight Code:");
        flightCode.setBounds(40,380,120,20);
        add(flightCode);

        lblflightCode = new JLabel();
        lblflightCode.setBounds(150,380,120,20);
        add(lblflightCode);

        JLabel date = new JLabel("Travel Date:");
        date.setBounds(40,420,120,20);
        add(date);

        cdate = new JDateChooser();
        cdate.setBounds(150,420,200,20);
        add(cdate);

        book = new JButton("Book Fetch");
        book.setBounds(200,450,120,20);
        book.setBackground(Color.BLACK);
        book.setForeground(Color.WHITE);
        book.addActionListener(this);
        add(book);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500,80,250,300);
        add(image);

        setVisible(true);
        setSize(800,700);
        setResizable(false);
        setLocation(250,10);
    }
    public static void main(String[] args) {
        new bookFlight();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connect c = new Connect();
        if (e.getSource() == fetch){
            String CNIC = tfCNIC.getText();
            String query = "select * from passengers where CNIC = '"+CNIC+"'";
            try {
                ResultSet set = c.s.executeQuery(query);
                if (set.next()){
                    lblName.setText(set.getString("name"));
                    lblAddress.setText(set.getString("address"));
                    lblNationality.setText(set.getString("nationality"));
                    lblGender.setText(set.getString("gender"));
                }else{
                    JOptionPane.showMessageDialog(null,"No record found");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == fetch_flight) {
            String source = cSource.getSelectedItem();
            String destionation = cDestination.getSelectedItem();
            String query = "select * from flight where source = '"+source+"' and destination = '"+destionation+"'";
            try {
                ResultSet set = c.s.executeQuery(query);
                if (set.next()){
                    lblflightCode.setText(set.getString("f_code"));
                    lblflightName.setText(set.getString("f_name"));
                }else{
                    JOptionPane.showMessageDialog(null,"No flight available");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            Random random = new Random();
            String source = cSource.getSelectedItem();
            String destionation = cDestination.getSelectedItem();
            String CNIC = tfCNIC.getText();
            String nationality = lblNationality.getText();
            String flightCode = lblflightCode.getText();
            String flightName = lblflightName.getText();
            String date = ((JTextField) cdate.getDateEditor().getUiComponent()).getText();
            String query = "insert into reservations values('PNR-"+random.nextInt(100000)+"' ,'TIC-"+random.nextInt(1000)+"', '"+CNIC+"','"+lblName.getText()+"','"+nationality+"','"+flightName+"','"+flightCode+"','"+source+"','"+destionation+"','"+date+"' )";
            try {
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Booked Successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }

    }
}


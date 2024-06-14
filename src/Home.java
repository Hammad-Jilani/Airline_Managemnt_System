import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    JMenuItem CustomerDetails,flightDetails,bookFlight,journeyDetails,cancelTicket;
    public Home(){
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1300,700,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1300,700);
        add(image);

        JLabel heading = new JLabel("Pakistan International Airline");
        heading.setBounds(400,20,1200,100);
        heading.setFont(new Font("Roboto",Font.PLAIN,40));
        heading.setForeground(Color.BLUE);
        image.add(heading);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu details = new JMenu("Details");
        details.setBounds(50,10,50,30);
        details.addSeparator();

        JMenuItem flightDetails = new JMenuItem("Flight Details");
        flightDetails.addActionListener(this);
        details.add(flightDetails);
        JMenuItem CustomerDetails = new JMenuItem("Add Customer Details");
        CustomerDetails.addActionListener(this);
        details.add(CustomerDetails);
        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        details.add(bookFlight);
        JMenuItem journeyDetails = new JMenuItem("Journey Details");
        journeyDetails.addActionListener(this);
        details.add(journeyDetails);
        JMenuItem cancelTicket = new JMenuItem("Cancel Ticket");
        cancelTicket.addActionListener(this);
        details.add(cancelTicket);

        JMenu tickets = new JMenu("Tickets");
        menuBar.add(tickets);

        JMenuItem boardingPass = new JMenuItem("Boarding Text");
        boardingPass.addActionListener(this);
        tickets.add(boardingPass);

        menuBar.add(details);

//        add(menuBar);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Airline Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        while (true){
            try {
                heading.setVisible(false);
                Thread.sleep(500);
                heading.setVisible(true);
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args) {
        new Home();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();
        if (text.equals("Add Customer Details")){
            new addCustomer();
//            setVisible(false);
        } else if (text.equals("Flight Details")) {
            new FlightInfo();
//            setVisible(false);
        } else if (text.equals("Book Flight")) {
            new bookFlight();
//            setVisible(false);
        } else if (text.equals("Journey Details")) {
            new JourneyDetails();
//            setVisible(false);
        }else if (text.equals("Cancel Ticket")){
            new Cancel();
//            setVisible(false);
        }else if(text.equals("Boarding Text")){
            new BoardingPass();
        }
    }
}

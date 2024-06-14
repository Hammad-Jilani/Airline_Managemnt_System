import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Cancel extends JFrame implements ActionListener {
    JTextField pnr;
    JLabel lblName,lblCancellationNo,lblFlightCode,lblDate;
    JButton show,cancel;
    public Cancel(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("CANCELLATION");
        heading.setFont(new Font("serif",Font.BOLD,30));
        heading.setBounds(350,0,300,60);
        add(heading);

        JLabel lblPNR = new JLabel("PNR Number :");
        lblPNR.setBounds(100,80,100,20);
        lblPNR.setFont(new Font("serif",SOMEBITS,15));
        add(lblPNR);

        pnr = new JTextField();
        pnr.setBounds(220,80,200,30);
        add(pnr);

        show = new JButton("Show Detail");
        show.setForeground(Color.WHITE);
        show.setBackground(Color.BLACK);
        show.setBounds(450,80,150,30);
        show.addActionListener(this);
        add(show);

        JLabel name = new JLabel("Name :");
        name.setBounds(100,120,100,30);
        name.setFont(new Font("serif",SOMEBITS,15));
        add(name);

        lblName = new JLabel();
        lblName.setBounds(220,120,100,30);
        add(lblName);


        JLabel cancelNo = new JLabel("Cancellation No. :");
        cancelNo.setBounds(100,160,130,30);
        cancelNo.setFont(new Font("serif",SOMEBITS,15));
        add(cancelNo);

        lblCancellationNo = new JLabel();
        lblCancellationNo.setBounds(220,160,100,30);
        add(lblCancellationNo);

        JLabel flightCode = new JLabel("Flight Code :");
        flightCode.setBounds(100,200,130,30);
        flightCode.setFont(new Font("serif",SOMEBITS,15));
        add(flightCode);

        lblFlightCode = new JLabel();
        lblFlightCode.setBounds(220,200,100,30);
        add(lblFlightCode);

        JLabel Date = new JLabel("Date :");
        Date.setBounds(100,240,130,30);
        Date.setFont(new Font("serif",SOMEBITS,15));
        add(Date);

        lblDate = new JLabel();
        lblDate.setBounds(220,240,100,30);
        add(lblDate);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(460,150,200,200);
        add(image);

        cancel = new JButton("Cancel");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.setBounds(150,300,150,30);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
        setLocation(150,60);
        setSize(900,500);
        setTitle("Airline Management System");
    }
    public static void main(String[] args) {
        new Cancel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connect c = new Connect();
        if (e.getSource() == show){
            String query = "select * from reservations where PNR = '"+pnr.getText()+"'";
            Random random = new Random();
            int num = random.nextInt(99999);
            try {
                ResultSet set = c.s.executeQuery(query);
                if (set.next()){
                    lblName.setText(set.getString("name"));
                    lblDate.setText(set.getString("date"));
                    lblCancellationNo.setText(String.valueOf(num));
                    lblFlightCode.setText(set.getString("flightCode"));
                }else{
                    JOptionPane.showMessageDialog(null,"No record found");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }else{
            String query = "insert into cancel value('"+pnr.getText()+"','"+lblName.getText()+"','"+lblCancellationNo.getText()+"','"+lblFlightCode.getText()+"','"+lblDate.getText()+"')";

            try {
                c.s.executeUpdate(query);
                c.s.executeUpdate("delete from reservations where PNR = '"+pnr.getText()+"'");
                JOptionPane.showMessageDialog(null,"Ticket Cancelled");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

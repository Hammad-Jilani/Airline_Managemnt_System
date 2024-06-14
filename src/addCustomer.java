import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addCustomer extends JFrame implements ActionListener {
    JTextField tfName,tfNationality,tfCNIC,tfAddress,tfPhone;
    JButton save,back;
    JRadioButton rbFemale,rbMale;
    public addCustomer(){
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Add Customer Details");
        heading.setBounds(220,10,400,50);
        heading.setFont(new Font("Roboto",Font.BOLD,30));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel name =new JLabel("Name");
        name.setBounds(30,80,100,20);
        add(name);

        tfName = new JTextField();
        tfName.setBounds(150,80,200,20);
        add(tfName);

        JLabel Nationality =new JLabel("Nationality");
        Nationality.setBounds(30,120,100,20);
        add(Nationality);

        tfNationality = new JTextField();
        tfNationality.setBounds(150,120,200,20);
        add(tfNationality);

        JLabel CNIC =new JLabel("CNIC");
        CNIC.setBounds(30,160,100,20);
        add(CNIC);

        tfCNIC = new JTextField();
        tfCNIC.setBounds(150,160,200,20);
        add(tfCNIC);

        JLabel Address =new JLabel("Address");
        Address.setBounds(30,200,100,20);
        add(Address);

        tfAddress = new JTextField();
        tfAddress.setBounds(150,200,200,20);
        add(tfAddress);

        JLabel gender =new JLabel("Gender");
        gender.setBounds(30,240,100,20);
        add(gender);

        ButtonGroup buttonGroup = new ButtonGroup();

        rbMale = new JRadioButton("Male");
        rbMale.setBounds(150,240,60,20);
        rbMale.setBackground(Color.WHITE);
        add(rbMale);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(250,240,80,20);
        rbFemale.setBackground(Color.WHITE);
        add(rbFemale);

        buttonGroup.add(rbMale);
        buttonGroup.add(rbFemale);

        JLabel Phone =new JLabel("Phone");
        Phone.setBounds(30,280,100,20);
        add(Phone);

        tfPhone = new JTextField();
        tfPhone.setBounds(150,280,200,20);
        add(tfPhone);

        save = new JButton("SAVE");
        save.setBounds(140,320,80,30);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.white);
        save.addActionListener(this);
        add(save);

        back = new JButton("BACK");
        back.setBounds(250,320,80,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/emp.png"));
        Image i2 = i1.getImage().getScaledInstance(250,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,80,250,300);
        add(image);

        setVisible(true);
        setSize(800,500);
        setResizable(false);
        setLocation(250,100);
    }
    public static void main(String[] args) {
        new addCustomer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connect c = new Connect();
        String name = tfName.getText();
        String nationality = tfNationality.getText();
        String phone = tfPhone.getText();
        String address = tfAddress.getText();
        String CNIC = tfCNIC.getText();
        String gender = null;
        if (e.getSource() == save) {
            if (rbMale.isSelected()) {
                gender = "Male";
            } else {
                gender = "Female";
            }
            try {
                String query = "insert into passengers values('" + name + "','" + nationality + "','" + phone + "','" + address + "','" + CNIC + "','" + gender + "')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Information added successfully");
//            setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            setVisible(false);
            new Home();

        }
    }
}

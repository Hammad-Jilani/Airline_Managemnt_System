import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JButton close,reset,submit;
    JTextField username;
    JPasswordField password;
    public Login(){
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(80,60,150,30);
        lblUsername.setFont(new Font("serif",Font.PLAIN,20));
        add(lblUsername);

        username = new JTextField();
        username.setBounds(180,60,250,30);
        add(username);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(80,100,150,30);
        lblPassword.setFont(new Font("serif",Font.PLAIN,20));
        add(lblPassword);

        password = new JPasswordField();
        password.setBounds(180,100,250,30);
        add(password);

        submit = new JButton("Login");
        submit.setFont(new Font("serif",Font.PLAIN,20));
        submit.setBounds(50,180,100,30);
        add(submit);
        submit.addActionListener(this);

        reset = new JButton("Reset");
        reset.setFont(new Font("serif",Font.PLAIN,20));
        reset.setBounds(200,180,100,30);
        reset.addActionListener(this);
        add(reset);

        close = new JButton("Close");
        close.setFont(new Font("serif",Font.PLAIN,20));
        close.setBounds(350,180,100,30);
        close.addActionListener(this);
        add(close);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(200,100);
        setTitle("Airline Management System");
        setSize(new Dimension(800,450));
    }
    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset){
            username.setText("");
            password.setText("");
        }else if(e.getSource()==close){
            setVisible(false);
        }else{
            try{
                String name = username.getText();
                String pass = new String(password.getPassword() );
                Connect c = new Connect();
                String query = "select * from login where username = '"+name+"' and password ='"+pass+"'";
                ResultSet set = c.s.executeQuery(query);
                if (set.next()){
                    new Home();
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect credentials");
//                    setVisible(false);
                }

            }catch (Exception ea){
                ea.printStackTrace();
            }

        }
    }
}

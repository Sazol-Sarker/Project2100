package Library;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login_Frame extends JFrame {
    
    private JLabel user_name,password;
    private JTextField text;
    private JPasswordField pass;
    private JButton Log_in,Reset,Clear;
    private Container c;
    private Font font;
    private ImageIcon icon;
    
    Login_Frame()
    {
        initComponents();
    }
    
    public void initComponents()
    {
        icon = new ImageIcon(getClass().getResource("library.jpg"));
        this.setIconImage(icon.getImage());
        
        this.setDefaultCloseOperation(Login_Frame.EXIT_ON_CLOSE);
        this.setBounds(50,100,430,400);
        this.setTitle("Login form");
        
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(43, 194, 233));
        
        font = new Font ("Arial",Font.BOLD,20);
        
        user_name = new JLabel("Username : ");
        user_name.setBounds(50,50,150,50);
        user_name.setFont(font);
        c.add(user_name);
        
        text = new JTextField();
        text.setFont(font);
        text.setBounds(160,50,200,50);
        c.add(text);
        
        password = new JLabel("Password : ");
        password.setBounds(50,160,150,50);
        password.setFont(font);
        c.add(password);
        
        pass = new JPasswordField();
        pass.setFont(font);
        pass.setBounds(160,160,200,50);
        c.add(pass);
        
        Log_in = new JButton("Login");
        Log_in.setBounds(50,260,90,50);
        Log_in.setFont(font);
        c.add(Log_in);
        
        Reset = new JButton("Reset");
        Reset.setBounds(160,260,90,50);
        Reset.setFont(font);
        c.add(Reset);
        
        Clear = new JButton("Clear");
        Clear.setBounds(270,260,90,50);
        Clear.setFont(font);
        c.add(Clear);
        
        Clear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent action)
            {
                text.setText("");
                pass.setText("");
            }
        });
        
        Log_in.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent action)
            {
                try 
                {
                    Class.forName("com.mysql.jdbc.Driver");
                } 
                catch (ClassNotFoundException ex) {
                    Logger.getLogger(Book_Table.class.getName()).log(Level.SEVERE, null, ex);
                }
                String url = "jdbc:mysql://127.0.0.1/library";
                String username = "root";
                String password = "";
                try 
                {
                    Connection connection = (Connection) DriverManager.getConnection(url,username,password);
                    String query = "SELECT * FROM login WHERE Username='"+text.getText()+"' AND Password='"+pass.getText()+"'";
                    PreparedStatement pst = connection.prepareStatement(query);
                    ResultSet resultset = pst.executeQuery();
                    if(resultset.next())
                    {
                        JOptionPane.showMessageDialog(null,"Username and password matched.");
                        dispose();
                        List_Frame frame = new List_Frame();
                        frame.setVisible(true);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Invalid username or password.");
                    }
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(Login_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Reset.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent action)
            {
                Confirm_Frame frame = new Confirm_Frame();
                frame.setVisible(true);
            }
        });
    }
    
    public static void main(String[] args) {
        Login_Frame frame = new Login_Frame();
        frame.setVisible(true);
    }
    
}

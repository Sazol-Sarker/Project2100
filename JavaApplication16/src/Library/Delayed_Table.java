package Library;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Delayed_Table extends JFrame {
    
    private Container c;
    private JLabel titlelabel,rolllabel,booklabel,writer1label,writer2label,issuedlabel;
    private JTextField roll,book,writer1,writer2,issued;
    private JButton add,update,delete,clear,refresh;
    private JTable table;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private Font font;
    private ImageIcon icon;
    private String[] columns = {"Roll","Book name","Writer1 name","Writer2 name","Issued date"};
    private Object[] row = new Object[5];
    
    Delayed_Table()
    {
        initComponents();
        show_data();
        
    }
    
    public ArrayList<Get_Data_2>userList() throws ClassNotFoundException, SQLException
    {
        ArrayList<Get_Data_2>userList = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/library";
        String username = "root";
        String password = "";
        Connection connection = (Connection) DriverManager.getConnection(url,username,password);
        String query = "SELECT * FROM delayed_list ORDER BY Roll";
        Statement statement = (Statement) connection.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        Get_Data_2 user;
        while(resultset.next())
        {
            user = new Get_Data_2(resultset.getInt("Roll"),resultset.getString("Book_name"),resultset.getString("Writer1_name"),
                             resultset.getString("Writer2_name"),resultset.getString("Issued_date"));
            userList.add(user);
        }
        return userList;
    }
    
    public void initComponents()
    {
        icon = new ImageIcon(getClass().getResource("library.jpg"));
        this.setIconImage(icon.getImage());
        
        this.setDefaultCloseOperation(Delayed_Table.HIDE_ON_CLOSE);
        this.setSize(1020,800);
        this.setLocationRelativeTo(null);
        this.setTitle("Delayed List");
        
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(0, 255, 207));
        
        font = new Font ("Arial",Font.BOLD,16);
        
        titlelabel = new JLabel("Delayed List");
        titlelabel.setFont(font);
        titlelabel.setBounds(450,10,250,50);
        c.add(titlelabel);
        
        rolllabel = new JLabel("Roll                  :");
        rolllabel.setFont(font);
        rolllabel.setBounds(50,80,140,30);
        c.add(rolllabel);
        
        booklabel = new JLabel("Book name    :");
        booklabel.setFont(font);
        booklabel.setBounds(50,130,150,30);
        c.add(booklabel);
        
        writer1label = new JLabel("Writer1 name :");
        writer1label.setFont(font);
        writer1label.setBounds(50,180,150,30);
        c.add(writer1label);
        
        writer2label = new JLabel("Writer2 name :");
        writer2label.setFont(font);
        writer2label.setBounds(50,230,150,30);
        c.add(writer2label);
        
        issuedlabel = new JLabel("Issued date    :");
        issuedlabel.setFont(font);
        issuedlabel.setBounds(50,280,150,30);
        c.add(issuedlabel);
        
        roll = new JTextField();
        roll.setFont(font);
        roll.setBounds(170,80,600,30);
        c.add(roll);
        
        book = new JTextField();
        book.setFont(font);
        book.setBounds(170,130,600,30);
        c.add(book);
        
        writer1 = new JTextField();
        writer1.setFont(font);
        writer1.setBounds(170,180,600,30);
        c.add(writer1);
        
        writer2 = new JTextField();
        writer2.setFont(font);
        writer2.setBounds(170,230,600,30);
        c.add(writer2);
        
        issued = new JTextField();
        issued.setFont(font);
        issued.setBounds(170,280,600,30);
        c.add(issued);
        
        add = new JButton("Add");
        add.setFont(font);
        add.setBounds(840,80,100,30);
        c.add(add);
        
        update = new JButton("Update");
        update.setFont(font);
        update.setBounds(840,130,100,30);
        c.add(update);
        
        delete = new JButton("Delete");
        delete.setFont(font);
        delete.setBounds(840,180,100,30);
        c.add(delete);
        
        clear = new JButton("Clear");
        clear.setFont(font);
        clear.setBounds(840,230,100,30);
        c.add(clear);
        
        refresh = new JButton("Refresh");
        refresh.setFont(font);
        refresh.setBounds(840,280,100,30);
        c.add(refresh);
        
        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setFont(font);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setBackground(Color.white);
        table.setRowHeight(30);
        
        scroll = new JScrollPane(table);
        scroll.setBounds(50,360,900,350);
        scroll.setFont(font);
        c.add(scroll);
        
        table.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                int row_no = table.getSelectedRow();
                TableModel model = table.getModel();
                String roll_no = model.getValueAt(row_no,0).toString();
                String book_name = model.getValueAt(row_no,1).toString();
                String writer1_name = model.getValueAt(row_no,2).toString();
                String writer2_name = model.getValueAt(row_no,3).toString();
                String issued_date = model.getValueAt(row_no,4).toString();
                
                roll.setText(roll_no);
                book.setText(book_name);
                writer1.setText(writer1_name);
                writer2.setText(writer2_name);
                issued.setText(issued_date);
            }
        });
        
        add.addActionListener(new ActionListener()
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
                    String query = "INSERT INTO delayed_list(Roll,Book_name,Writer1_name,Writer2_name,Issued_date)VALUES("+roll.getText()+",'"+
                                   book.getText()+"','"+writer1.getText()+"','"+writer2.getText()+"','"+issued.getText()+"')";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Inserted Successfully.");
                    DefaultTableModel model = (DefaultTableModel)table.getModel();
                    model.setRowCount(0);
                    show_data();
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(Book_Table.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        update.addActionListener(new ActionListener()
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
                    int row_value = table.getSelectedRow();
                    if(row_value>=0)
                    {
                        int choice = JOptionPane.showConfirmDialog(null,"Do you want to update the data?","Warning",JOptionPane.YES_NO_OPTION);
                        if(choice == JOptionPane.YES_OPTION)
                        {
                            String row_data_1 = (table.getModel().getValueAt(row_value,0).toString());
                            String row_data_2 = (table.getModel().getValueAt(row_value,1).toString());
                            String row_data_3 = (table.getModel().getValueAt(row_value,2).toString());
                            String query = "UPDATE delayed_list SET Roll="+roll.getText()+",Book_name='"+book.getText()+"',Writer1_name='"+
                                           writer1.getText()+"',Writer2_name='"+writer2.getText()+"',Issued_date='"+
                                           issued.getText()+"' WHERE Roll="+row_data_1+" AND Book_name='"+row_data_2+"' AND Writer1_name='"+row_data_3+"'";
                            PreparedStatement pst = connection.prepareStatement(query);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null,"Updated Successfully.");
                            DefaultTableModel model = (DefaultTableModel)table.getModel();
                            model.setRowCount(0);
                            show_data();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"You selected NO.");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"No row exist or slected.");
                    }
                } 
                catch (SQLException ex) {
                    Logger.getLogger(Book_Table.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        delete.addActionListener(new ActionListener()
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
                    int row_value = table.getSelectedRow();
                    if(row_value>=0)
                    {
                        int choice = JOptionPane.showConfirmDialog(null,"Do you want to delete the data?","Warning",JOptionPane.YES_NO_OPTION);
                        if(choice == JOptionPane.YES_OPTION)
                        {
                            String row_data_1 = (table.getModel().getValueAt(row_value,0).toString());
                            String row_data_2 = (table.getModel().getValueAt(row_value,1).toString());
                            String row_data_3 = (table.getModel().getValueAt(row_value,2).toString());
                            String query = "DELETE FROM delayed_list WHERE Roll="+row_data_1+" AND Book_name='"+row_data_2+"' AND Writer1_name='"+row_data_3+"'";
                            PreparedStatement pst = connection.prepareStatement(query);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null,"Deleted Successfully.");
                            DefaultTableModel model = (DefaultTableModel)table.getModel();
                            model.setRowCount(0);
                            show_data();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"You selected NO.");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"No row exist or slected.");
                    }
                } 
                catch (SQLException ex) {
                    Logger.getLogger(Book_Table.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        clear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent action)
            {
                roll.setText("");
                book.setText("");
                writer1.setText("");
                writer2.setText("");
                issued.setText("");
            }
        });
        
        refresh.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent action)
            {
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                model.setRowCount(0);
                show_data();
            }
        });
    }
    
    public void show_data()
    {
        ArrayList<Get_Data_2> list = null;
        try {
            list = userList();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Book_Table.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Book_Table.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for(int i=0;i<list.size();i++)
        {
            row[0] = list.get(i).get_Roll();
            row[1] = list.get(i).get_Book_name();
            row[2] = list.get(i).get_Writer1_name();
            row[3] = list.get(i).get_Writer2_name();
            row[4] = list.get(i).get_Issued_date();
            model.addRow(row);
        }
        
    }
    
}

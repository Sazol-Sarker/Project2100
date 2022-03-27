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

public class Book_Table extends JFrame {
    
    private Container c;
    private JLabel titlelabel,booklabel,writer1label,writer2label,amountlabel,issuedlabel;
    private JTextField book,writer1,writer2,amount,issued;
    private JButton add,update,delete,clear;
    private JTable table;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private Font font;
    private ImageIcon icon;
    private String[] columns = {"Book name","Writer1 name","Writer2 name","Amount","Issued"};
    private Object[] row = new Object[5];
    
    Book_Table()
    {
        initComponents();
        show_data(); 
    }
    
    public ArrayList<Get_Data_1>userList() throws ClassNotFoundException, SQLException
    {
        ArrayList<Get_Data_1>userList = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/library";
        String username = "root";
        String password = "";
        Connection connection = (Connection) DriverManager.getConnection(url,username,password);
        String query = "SELECT * FROM book_list ORDER BY Book_name";
        Statement statement = (Statement) connection.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        Get_Data_1 user;
        while(resultset.next())
        {
            user = new Get_Data_1(resultset.getString("Book_name"),
                            resultset.getString("Writer1_name"),
                            resultset.getString("Writer2_name"),
                            resultset.getInt("Amount"),
                            resultset.getInt("Issued"));
            userList.add(user);
        }
        return userList;
    }
    
    public void initComponents()
    {
        icon = new ImageIcon(getClass().getResource("library.jpg"));
        this.setIconImage(icon.getImage());
        
        this.setDefaultCloseOperation(Book_Table.HIDE_ON_CLOSE);
        this.setSize(1020,800);
        this.setLocationRelativeTo(null);
        this.setTitle("Book List");
        
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(79, 185, 151));
        
        font = new Font ("Arial",Font.BOLD,16);
        
        titlelabel = new JLabel("Book List");
        titlelabel.setFont(font);
        titlelabel.setBounds(450,10,250,50);
        c.add(titlelabel);
        
        booklabel = new JLabel("Book name    :");
        booklabel.setFont(font);
        booklabel.setBounds(50,80,140,30);
        c.add(booklabel);
        
        writer1label = new JLabel("Writer1 name :");
        writer1label.setFont(font);
        writer1label.setBounds(50,130,150,30);
        c.add(writer1label);
        
        writer2label = new JLabel("Writer2 name :");
        writer2label.setFont(font);
        writer2label.setBounds(50,180,150,30);
        c.add(writer2label);
        
        amountlabel = new JLabel("Amount           :");
        amountlabel.setFont(font);
        amountlabel.setBounds(50,230,150,30);
        c.add(amountlabel);
        
        issuedlabel = new JLabel("Issued             :");
        issuedlabel.setFont(font);
        issuedlabel.setBounds(50,280,150,30);
        c.add(issuedlabel);
        
        book = new JTextField();
        book.setFont(font);
        book.setBounds(170,80,600,30);
        c.add(book);
        
        writer1 = new JTextField();
        writer1.setFont(font);
        writer1.setBounds(170,130,600,30);
        c.add(writer1);
        
        writer2 = new JTextField();
        writer2.setFont(font);
        writer2.setBounds(170,180,600,30);
        c.add(writer2);
        
        amount = new JTextField();
        amount.setFont(font);
        amount.setBounds(170,230,600,30);
        c.add(amount);
        
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
                String book_name = model.getValueAt(row_no,0).toString();
                String writer1_name = model.getValueAt(row_no,1).toString();
                String writer2_name = model.getValueAt(row_no,2).toString();
                String Amount = model.getValueAt(row_no,3).toString();
                String Issued = model.getValueAt(row_no,4).toString();
                
                book.setText(book_name);
                writer1.setText(writer1_name);
                writer2.setText(writer2_name);
                amount.setText(Amount);
                issued.setText(Issued);
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
                    String query = "INSERT INTO book_list(Book_name,Writer1_name,Writer2_name,Amount,Issued)VALUES('"+book.getText()+"','"
                                   +writer1.getText()+"','"+writer2.getText()+"',"+amount.getText()+","+issued.getText()+")";
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
                            String query = "UPDATE book_list SET Book_name='"+book.getText()+"',Writer1_name='"+
                                           writer1.getText()+"',Writer2_name='"+writer2.getText()+"',Amount="+
                                           amount.getText()+",Issued="+issued.getText()+" WHERE Book_name='"+row_data_1+
                                           "' AND Writer1_name='"+row_data_2+"'";
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
                            String query = "DELETE FROM book_list WHERE Book_name='"+row_data_1+"' AND Writer1_name='"+row_data_2+"'";
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
                book.setText("");
                writer1.setText("");
                writer2.setText("");
                amount.setText("");
                issued.setText("");
            }
        });
    }
    
    public void show_data()
    {
        ArrayList<Get_Data_1> list = null;
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
            row[0] = list.get(i).get_Book_name();
            row[1] = list.get(i).get_Writer1_name();
            row[2] = list.get(i).get_Writer2_name();
            row[3] = list.get(i).get_Amount();
            row[4] = list.get(i).get_Issued();
            model.addRow(row);
        }
        
    }
    
}

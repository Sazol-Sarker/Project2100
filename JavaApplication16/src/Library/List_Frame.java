package Library;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class List_Frame extends JFrame {
    
    private Container c;
    private JButton list1,list2,list3;
    private Font font;
    private ImageIcon icon;
    
    List_Frame()
    {
        initComponents();
    }
    
    public void initComponents()
    {
        icon = new ImageIcon(getClass().getResource("library.jpg"));
        this.setIconImage(icon.getImage());
        
        this.setDefaultCloseOperation(List_Frame.EXIT_ON_CLOSE);
        this.setBounds(50,100,320,340);
        this.setTitle("List");
        
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(100, 79, 185));
        
        font = new Font ("Arial",Font.BOLD,20);
        
        list1 = new JButton("Book List");
        list1.setBounds(50,50,200,50);
        list1.setFont(font);
        c.add(list1);
        
        list2 = new JButton("Issued List");
        list2.setBounds(50,120,200,50);
        list2.setFont(font);
        c.add(list2);
        
        list3 = new JButton("Delayed List");
        list3.setBounds(50,190,200,50);
        list3.setFont(font);
        c.add(list3);
        
        list1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent action)
            {
                Book_Table frame = new Book_Table();
                frame.setVisible(true);
            }
        });
        
        list2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent action)
            {
                Issued_Table frame = new Issued_Table();
                frame.setVisible(true);
            }
        });
        
        list3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent action)
            {
                Delayed_Table frame = new Delayed_Table();
                frame.setVisible(true);
            }
        });
    }
    
}

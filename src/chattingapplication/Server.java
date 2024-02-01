/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chattingapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import static java.awt.SystemColor.text;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.net.*;


/**
 *
 * @author nikhillohra
 */
public class Server implements ActionListener {

    JTextField t1;
    JPanel j1;
   static Box vertical = Box.createVerticalBox();
    JButton send;
     static DataOutputStream dout;
    
  static JFrame f = new JFrame();
    

    Server() {
//Layout
        f.setLayout(null);
        f.setTitle("ChatApp");
        f.getContentPane().setBackground(Color.WHITE);
//Panel
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 140, 170));
        p1.setBounds(0, 0, 600, 80);
        p1.setLayout(null);
        f.add(p1);

        //Back Image
        ImageIcon i1;
        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/b1.png"));
        Image i2 = i1.getImage().getScaledInstance(40, 55, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(1, 17, 30, 50);
        p1.add(back);

        //profile1
        ImageIcon i4;
        i4 = new ImageIcon(ClassLoader.getSystemResource("icon/bat3.png"));
        Image i5 = i4.getImage().getScaledInstance(70, 70, Image.SCALE_AREA_AVERAGING);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(34, 5, 70, 70);
        p1.add(profile);

        //videoIcon
        ImageIcon i7;
        i7 = new ImageIcon(ClassLoader.getSystemResource("icon/v1.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 25, Image.SCALE_AREA_AVERAGING);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(440, 20, 50, 40);
        p1.add(video);

//callIcon
        ImageIcon i10;
        i10 = new ImageIcon(ClassLoader.getSystemResource("icon/call2.png"));
        Image i11 = i10.getImage().getScaledInstance(25, 20, Image.SCALE_AREA_AVERAGING);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel call = new JLabel(i12);
        call.setBounds(500, 20, 35, 40);
        p1.add(call);

//userName
        JLabel bat = new JLabel("Batman.Inc");
        bat.setBounds(110, 15, 100, 40);
        bat.setFont(new Font("Helvetica-Bold", Font.BOLD, 18));
        bat.setForeground(Color.BLACK);
        f.add(bat);
        p1.add(bat);

        //activeNow
        JLabel active = new JLabel("Active Now");
        active.setBounds(110, 30, 100, 40);
        active.setFont(new Font("Helvetica-Bold", Font.BOLD, 10));
        active.setForeground(new Color(255, 210, 20));
        f.add(active);
        p1.add(active);

        //messsagePanel
        j1 = new JPanel();
        j1.setBounds(1, 80, 600, 600);
        j1.setBackground(new Color(245, 240, 245));
        j1.setLayout(new FlowLayout());
       f.add(j1);

        //TextField
        t1 = new JTextField("");
        t1.setBounds(0, 678, 504, 50);
        t1.setFont(new Font("Helvetica", Font.PLAIN, 18));
        t1.setForeground(Color.BLACK);
        t1.setBackground(Color.WHITE);   
       f.add(t1);

        //threeIcon
        ImageIcon i13;
        i13 = new ImageIcon(ClassLoader.getSystemResource("icon/31.png"));
        Image i14 = i13.getImage().getScaledInstance(35, 30, Image.SCALE_AREA_AVERAGING);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel three = new JLabel(i15);
        three.setBounds(545, 15, 50, 50);
        p1.add(three);

        //sendButton
         send = new JButton("Send");
        send.setBounds(500, 678, 100, 51);
        send.setBackground(new Color(23, 135, 23));
        send.setForeground(Color.BLACK);
        send.addActionListener(this);
        f.add(send);

        
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }

        });

        f.setSize(600, 760);
        f.setBounds(100, 40, 600, 760);
        // setUndecorated(true);
        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
     try{ if(ae.getSource() == send){
          String out = t1.getText();
           
        JPanel p2 = formatLabel(out);
       

        j1.setLayout(new BorderLayout());

        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));

        j1.add(vertical, BorderLayout.PAGE_START);
          t1.setText(" ");
          
          dout.writeUTF(out);
          
          
        f.repaint();
        f.invalidate();
        f.validate();
     }
     }catch(Exception e){
                e.printStackTrace();
                }

    
    }
    
    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         
        JLabel output = new JLabel("<html><p style=\"width: 150px\">"+ out + "</p></html>");
        output.setFont(new Font("Arial", Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH : mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        
        return panel;
    }

    public static void main(String[] args) {
        // TODO code application logic here

        new Server();
        
        try{
            ServerSocket skt = new ServerSocket(6001);
            while(true){
               Socket s = skt.accept();
               DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
               
          while(true){
                   String msg = din.readUTF();
                   JPanel panel = formatLabel(msg);
                   
                   JPanel left = new JPanel(new BorderLayout());
                   left.add(panel, BorderLayout.LINE_START);
                   vertical.add(left);
                   f.validate();
                   
                   
               }
               
               
               
               
               
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
            
        }

    }

}

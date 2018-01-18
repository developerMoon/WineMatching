package page;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.css.RGBColor;

import collaboproject2.WineShopCustomer;
import collaboproject2.searchpage;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.color.*;
/*import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;*/



import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;

public class MainPage extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton loginbtn,searchbtn;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel LOGO = new JLabel("");
		LOGO.setIcon(new ImageIcon(MainPage.class.getResource("/page/winelogo.png")));
		panel.add(LOGO);
		
		JLabel blank = new JLabel("");
		panel.add(blank);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		loginbtn = new JButton("");
		loginbtn.setSize(55,19);
		loginbtn.setBackground(Color.pink); //loginbtn 배경색을 HSB 형태로 받음
		loginbtn.setIcon(new ImageIcon(MainPage.class.getResource("/page/login_sq.png")));
		panel_1.add(loginbtn);
		
		
		searchbtn = new JButton("");
		searchbtn.setSize(55,19);
		searchbtn.setBackground(Color.white);
		searchbtn.setIcon(new ImageIcon(MainPage.class.getResource("/page/search_sq.png")));
		panel_1.add(searchbtn);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		
		JLabel mainimage = new JLabel("");
		mainimage.setIcon(new ImageIcon(MainPage.class.getResource("/page/wine_background.jpg")));
		panel_2.add(mainimage);
		
		loginbtn.addActionListener(this);
		searchbtn.addActionListener(this);		
	}

	//Action �߰��ϱ�
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton) e.getSource();

		if(btn==loginbtn) {
			//login으로 넘어가기
			JFrame login = new WineShopCustomer();
			login.setVisible(true);
		}
		if(btn==searchbtn) {
			//page2로 넘어가기
			JFrame search = new searchpage("id");
			search.setVisible(true);
		}
   

	}
}

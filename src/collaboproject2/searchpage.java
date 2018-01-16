package collaboproject2;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//선생님 소스 보고 다시 해보기
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.UIManager;

public class searchpage extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JRadioButton rd1,rd2,
	rd3,rdred,rdwhite,rdsp,rdlo,small,medium,large;
	private JLabel lblsum;
	private JButton btnorder, btncancel;
	private JRadioButton rd4;
	private JRadioButton rd5;
	public ButtonGroup toping , menu, money;
	private JButton btn;
	private String id;
	
	public searchpage(String id) {
		this.id=id;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//x버튼 비활성화
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Select Your own WINE!");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnorder = new JButton("\uC8FC\uBB38");
		btnorder.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel.add(btnorder);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uB2F9\uB3C4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(5, 0, 0, 0));
		
		rd1 = new JRadioButton("1");
		rd1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_2.add(rd1);
		
		rd2 = new JRadioButton("2");
		rd2.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_2.add(rd2);
		
		rd3 = new JRadioButton("3");
		rd3.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_2.add(rd3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC640\uC778\uC885\uB958", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(4, 0, 0, 0));
		
		rdred = new JRadioButton("레드");
		rdred.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_3.add(rdred);
		
		rdwhite = new JRadioButton("화이트");
		rdwhite.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_3.add(rdwhite);
		
		rdsp = new JRadioButton("스파클링");
		rdsp.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_3.add(rdsp);
		
		rdlo = new JRadioButton("로제");
		rdlo.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_3.add(rdlo);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uAC00\uACA9", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(3, 0, 0, 0));
		
		small = new JRadioButton("30000 이하");
		small.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_4.add(small);
		
		medium = new JRadioButton("30000~50000");
		medium.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_4.add(medium);
		
		large = new JRadioButton("50000 이상");
		large.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_4.add(large);
		
		
		//그룹으로 묶어서 한 열당 하나만 선택하도록, 당도
		menu = new ButtonGroup();
		menu.add(rd1);
		menu.add(rd2);
		menu.add(rd3);
		menu.add(rd4);
		menu.add(rd5);
		
		rd4 = new JRadioButton("4");
		rd4.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_2.add(rd4);
		
		rd5 = new JRadioButton("5");
		rd5.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_2.add(rd5);
				
		//토핑-타입
		 toping = new ButtonGroup();
		toping.add(rdred);
		toping.add(rdwhite);
		toping.add(rdsp);
		toping.add(rdlo);
		
		//가격
		money = new ButtonGroup();
		//1~3
		money.add(small);
		//3~5
		money.add(medium);
		//5~
		money.add(large);
		
		btn = new JButton("취소");
		panel.add(btn);
		
		lblsum = new JLabel("");
		panel.add(lblsum);
		lblsum.setFont(new Font("나눔고딕", Font.BOLD, 12));
		
		
		
		rd1.addActionListener(this);
		rd2.addActionListener(this);
		rd3.addActionListener(this);
		rd4.addActionListener(this);
		rd5.addActionListener(this);
		
		rdred.addActionListener(this);
		rdwhite.addActionListener(this);
		rdsp.addActionListener(this);
		rdlo.addActionListener(this);
		
		small.addActionListener(this);
		medium.addActionListener(this);
		large.addActionListener(this);
		
		btnorder.addActionListener(this);
		btn.addActionListener(this);
		
		
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btn1 = (AbstractButton) e.getSource();
		
		//JButton btn1=(JButton) e.getSource();
		int sweet=0;
		String type=null;
		int lprice=0;
		int hprice=0;
		
		if(btn1==btnorder){
		//selectedwine wine= new selectedwine();
		if(rd1.isSelected())
			sweet=1;
		
		if(rd2.isSelected())
			sweet=2;
		
		if(rd3.isSelected())
			sweet=3;
		
		if(rd4.isSelected())
			sweet=4;
		
		if(rd5.isSelected())
			sweet=5;
		
		if(rdred.isSelected())
			type="레드";
		if(rdwhite.isSelected())
			type="화이트";
		if(rdsp.isSelected())
			type="스파클링";
		if(rdlo.isSelected())
			type="로제";
		
		
		if(small.isSelected()) {
			lprice=0;
			hprice=30000;
			}
		else if(medium.isSelected()) {
			lprice=30000;
			hprice=50000;
			}
		if(large.isSelected()) {
			lprice=50000;
		 	hprice=750000;
		}
		// System.out.println(sweet+" "+type+" "+price);
		 
		 
		/*int sweet=1;
		String type="화이트";
		int money=20000;*/
			
		 selectedwine wine= new selectedwine(id);		 
		 wine.setVisible(true);
		 wine.show(sweet, type, lprice, hprice);
		//System.out.print(sweet+type+lprice+hprice);
		
		} else if(btn1==btn) {
		}
	}
}


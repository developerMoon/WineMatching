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
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;

public class searchpage extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JRadioButton rd1,rd2,rd3,rd4,rd5,rdb1,rdb2,rdb3,rdb4,rdb5,rdred,rdwhite,rdsp,rdro,price1,price2,price3,price4,price5;
	private JButton btnorder, btnlist, btncancel;
	public ButtonGroup type1 , sweet, money, body1;
	private String id;
	private JPanel bodyy;

/*	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchpage frame = new searchpage(id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	public searchpage(String id) {
		this.id=id;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//x버튼 비활성화
		setBounds(100, 100, 612, 404);
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
		
		btnlist = new JButton("전체 와인 조회");
		panel.add(btnlist);
		
		btnorder = new JButton("\uC8FC\uBB38");
		btnorder.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel.add(btnorder);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));
		
		bodyy = new JPanel();
		bodyy.setBorder(new TitledBorder(null, "\uBC14\uB514", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(bodyy);
		bodyy.setLayout(new GridLayout(0, 1, 0, 0));
		
		rdb1 = new JRadioButton("1");
		bodyy.add(rdb1);
		
		rdb2 = new JRadioButton("2");
		bodyy.add(rdb2);
		
		rdb3 = new JRadioButton("3");
		bodyy.add(rdb3);
		
		rdb4 = new JRadioButton("4");
		bodyy.add(rdb4);
		
		rdb5 = new JRadioButton("5");
		bodyy.add(rdb5);
		
		JPanel sweett = new JPanel();
		sweett.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uB2F9\uB3C4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(sweett);
		sweett.setLayout(new GridLayout(5, 0, 0, 0));
		
		rd1 = new JRadioButton("1");
		rd1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		sweett.add(rd1);
		
		rd2 = new JRadioButton("2");
		rd2.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		sweett.add(rd2);
		
		rd3 = new JRadioButton("3");
		rd3.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		sweett.add(rd3);
		
		JPanel typee = new JPanel();
		typee.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC640\uC778\uC885\uB958", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(typee);
		typee.setLayout(new GridLayout(4, 0, 0, 0));
		
		rdred = new JRadioButton("레드");
		rdred.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		typee.add(rdred);
		
		rdwhite = new JRadioButton("화이트");
		rdwhite.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		typee.add(rdwhite);
		
		rdsp = new JRadioButton("스파클링");
		rdsp.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		typee.add(rdsp);
		
		rdro = new JRadioButton("로제");
		rdro.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		typee.add(rdro);
		
		JPanel pricee = new JPanel();
		pricee.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uAC00\uACA9", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(pricee);
		pricee.setLayout(new GridLayout(5, 0, 0, 0));
		
		price1 = new JRadioButton("5,000~30,000 ");
		price1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		pricee.add(price1);
		
		price2 = new JRadioButton("30,000~50,000");
		price2.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		pricee.add(price2);
		
		price3 = new JRadioButton("50,000~150,000");
		price3.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		pricee.add(price3);
		
		rd4 = new JRadioButton("4");
		rd4.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		sweett.add(rd4);
		
		rd5 = new JRadioButton("5");
		rd5.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		sweett.add(rd5);
		
		price4 = new JRadioButton("150,000~");
		pricee.add(price4);
		//15~
		
		price4.addActionListener(this);
		
		price5 = new JRadioButton("가격 무관");
		pricee.add(price5);
		
		btncancel = new JButton("취소");
		panel.add(btncancel);
		
		
		//바디감
		body1=new ButtonGroup();
		body1.add(rdb1);
		body1.add(rdb2);
		body1.add(rdb3);
		body1.add(rdb4);
		body1.add(rdb5);
		
		
		//그룹으로 묶어서 한 열당 하나만 선택하도록, 당도
		sweet = new ButtonGroup();
		sweet.add(rd1);
		sweet.add(rd2);
		sweet.add(rd3);
		sweet.add(rd4);
		sweet.add(rd5);
		
	
				
		//타입
		type1 = new ButtonGroup();
		type1.add(rdred);
		type1.add(rdwhite);
		type1.add(rdsp);
		type1.add(rdro);
		
		//가격
		money = new ButtonGroup();
		//1~3
		money.add(price1);
		//3~5
		money.add(price2);
		//5~15
		money.add(price3);
		money.add(price4);
		money.add(price5);
		
	
		

		rdb1.addActionListener(this);
		rdb2.addActionListener(this);
		rdb3.addActionListener(this);
		rdb4.addActionListener(this);
		rdb5.addActionListener(this);
		
		rd1.addActionListener(this);
		rd2.addActionListener(this);
		rd3.addActionListener(this);
		rd4.addActionListener(this);
		rd5.addActionListener(this);
		//btngroup money
		rdred.addActionListener(this);
		rdwhite.addActionListener(this);
		rdsp.addActionListener(this);
		rdro.addActionListener(this);
		//btngroup money
		price1.addActionListener(this);
		price2.addActionListener(this);
		price3.addActionListener(this);
		price4.addActionListener(this);
		price5.addActionListener(this);
		
		btnorder.addActionListener(this);
		btncancel.addActionListener(this);
		btnlist.addActionListener(this);
		
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton btn1 = (AbstractButton) e.getSource();
		
		//JButton btn1=(JButton) e.getSource();
		int body=0;
		int sweet=0;
		String type=null;
		int lprice=0;
		int hprice=0;
		
		if(btn1==btnorder){
		
			if(rdb1.isSelected()) body=1;
			if(rdb2.isSelected()) body=2;
			if(rdb3.isSelected()) body=3;	
			if(rdb4.isSelected()) body=4;
			if(rdb5.isSelected()) body=5;
			
			if(rd1.isSelected()) sweet=1;
			if(rd2.isSelected()) sweet=2;
			if(rd3.isSelected()) sweet=3;	
			if(rd4.isSelected()) sweet=4;
			if(rd5.isSelected()) sweet=5;
			
			if(rdred.isSelected()) type="레드";
			if(rdwhite.isSelected()) type="화이트";
			if(rdsp.isSelected()) type="스파클링";
			if(rdro.isSelected()) type="로제";					
			if(price1.isSelected()) {
				lprice=0;
				hprice=30000;
				}
			if(price2.isSelected()) {
				lprice=30000;
				hprice=50000;
				}
			if(price3.isSelected()) {
				lprice=50000;
			 	hprice=150000;
			}
			if(price4.isSelected()) {
				lprice=150000;
			 	hprice=800000;
			}
			if(price5.isSelected()) {
				lprice=1000;
			 	hprice=800000;
			}
			// System.out.println(sweet+" "+type+" "+price);
			 
			 
			/*int sweet=1;
			String type="화이트";
			int money=20000;*/
				
			 selectedwine wine= new selectedwine(id);		 
			 wine.setVisible(true);
			 wine.show(body,sweet, type, lprice, hprice);
			//System.out.print(sweet+type+lprice+hprice);
			// System.out.println(body +""+ sweet + type+ lprice+""+hprice);
		} else if(btn1==btncancel) {
			dispose();
		}else if(btn1==btnlist) {
			
			
		}
	}
}


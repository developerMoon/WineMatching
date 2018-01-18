package collaboproject2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.acl.Group;
import java.util.Enumeration;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.Color;

public class WineShopCustomer extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtid, txtId, txtAge;
	private JButton btnlogin, btnsignup;
	private JPasswordField txtpw, txtPw;
	private JPanel addUserPanel;
	private JPanel panel_1;
	private JPanel addUserPanel2;
	private JButton btnAdd, btnCancel;
	private JPanel panel_2; 
	private ButtonGroup group;
	static String id=null;
	
	public WineShopCustomer() {
		setTitle("WineShop");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 325, 210); //사이즈 기본 값 setBounds(100, 100, 453, 236); 
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
				
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_3 = new JLabel("Welcome to Wine Shop");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBackground(Color.PINK);
		lblNewLabel_3.setFont(new Font("나눔명조 ExtraBold", Font.BOLD, 23));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_3);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("ID   ");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel);
		
		txtid = new JTextField();
		panel_1.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password   ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_1);
		
		txtpw = new JPasswordField();
		panel_1.add(txtpw);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		btnlogin = new JButton("로그인");
		btnlogin.setForeground(Color.WHITE);
		btnlogin.setBackground(Color.PINK);
		btnlogin.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		panel_2.add(btnlogin);
		
		btnsignup = new JButton("회원가입");
		btnsignup.setBackground(Color.PINK);
		btnsignup.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		btnsignup.setForeground(Color.WHITE);
		panel_2.add(btnsignup);
		
		//회원가입패널
		addUserPanel = new JPanel();
		addUserPanel.setBackground(Color.white); //배경색 흰색
		addUserPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBackground(Color.white); //배경 하얀색
		addUserPanel.add(idLabel);
		txtId = new JTextField();
		txtId.setColumns(10);
		addUserPanel.add(txtId);
		JLabel blankLabel = new JLabel("");
		blankLabel.setBackground(Color.white); //배경 하얀색
		addUserPanel.add(blankLabel);
		
		JLabel pwLabel = new JLabel("Password");
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		pwLabel.setBackground(Color.white);
		addUserPanel.add(pwLabel);
		txtPw = new JPasswordField();
		txtPw.setColumns(10);
		addUserPanel.add(txtPw);	
		JLabel blankLabel2 = new JLabel("");
		blankLabel2.setBackground(Color.white);
		addUserPanel.add(blankLabel2);
		
		/*JLabel genderLabel=new JLabel("gender");
		genderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		genderLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		addUserPanel.add(genderLabel);
		JRadioButton male=new JRadioButton("남자");
		JRadioButton female=new JRadioButton("여자");
		group=new ButtonGroup();
		group.add(male);
		group.add(female);
		addUserPanel.add(male);
		addUserPanel.add(female);*/
		
		JLabel ageLabel = new JLabel("age");
		ageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ageLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		ageLabel.setBackground(Color.white); //배경색 흰 색
		addUserPanel.add(ageLabel);
		txtAge = new JTextField();
		txtAge.setColumns(10);
		addUserPanel.add(txtAge);
		JLabel blankLabel3 = new JLabel("");
		blankLabel3.setBackground(Color.WHITE);
		addUserPanel.add(blankLabel3);
		
		
		addUserPanel2 = new JPanel();
		addUserPanel2.setBackground(Color.WHITE); //배경색 흰색
		//회원가입 - 가입버튼
		btnAdd = new JButton("가입");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(Color.PINK);
		btnAdd.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		addUserPanel2.add(btnAdd);
		
		//회원가입 - 취소 버튼
		btnCancel = new JButton("취소");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(Color.PINK);
		btnCancel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		addUserPanel2.add(btnCancel);
		//끝
		btnAdd.addActionListener(this);
		btnCancel.addActionListener(this);
		
		
		btnsignup.addActionListener(this);
		btnlogin.addActionListener(this);
		
		
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton) e.getSource();
		WineShopDao dao=new WineShopDao();
		WineShopVO vo=new WineShopVO();
		if(btn==btnsignup) {
			//화면전환
			contentPane.remove(panel_1);
			contentPane.remove(panel_2);
			contentPane.revalidate();
			contentPane.add(addUserPanel, BorderLayout.CENTER);
			contentPane.add(addUserPanel2,BorderLayout.SOUTH);
			contentPane.repaint();
		}else if(btn==btnlogin) {
			//회원 정보에 있으면 
			//로그인 되고 다음 화면으로 넘어가게
			String id=txtid.getText();
			//passwordfield 는 gettext가 안 쓰이는 메소드라서 getpassword(return char) -> tostring
			char charpw[]=txtpw.getPassword();
			String pw=new String(charpw,0,charpw.length);
			vo=dao.getRow(id);
			//id조회 가능하고
			//이후 추가: master경우 관리자 페이지로 넘어가게
			//일반 회원인 경우 쇼핑몰로 넘어가게
			if(vo!=null) {
				//관리자로그인
				if(vo.getId().equals("master") && vo.getPw().equals("12345")) { //text...equals...쓰자.. 
					WineShopMasterId master=new WineShopMasterId();
					master.setVisible(true);
					dispose();
				} else if(vo.getId().equals(id) && vo.getPw().equals(pw)){ //고객로그인
					searchpage search = new searchpage(id);
					search.setVisible(true);
					dispose();
				}else {
				JOptionPane.showMessageDialog(this, "아이디 혹은 패스워드가 맞지 않습니다");
				}
			}
		}else if(btn==btnCancel) {
			//이전화면으로돌리기
				repaint();
		}else if(btn==btnAdd) {
			//DB에 회원정보넣고 되돌리기
			String id=txtId.getText();
			char charpw[]=txtPw.getPassword();
			String pw=new String(charpw,0,charpw.length);
			int age=Integer.parseInt(txtAge.getText());
			//Enumeration<AbstractButton> eGender=group.getElements();
			//JRadioButton btnGender=(JRadioButton)eGender.nextElement();
			//String gender=btnGender.getText();
			//20세 이상이어야 회원가입 가능하게
			if(age>=20) {
					int result2=JOptionPane.showConfirmDialog(this, "회원가입 하시겠습니까?","회원가입",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
						if(result2==0) {
							int result=dao.userInsert(id, pw, age);						
							if(result==1) {
								JOptionPane.showMessageDialog(this, "회원가입완료","완료",JOptionPane.PLAIN_MESSAGE);
								//원래 로그인/가입 페이지로 돌아가고 다시 로그인 해야 와인 검색 가능
								repaint();
							}
						}else if(result2==1) {
							JOptionPane.showMessageDialog(this, "회원가입취소","취소",JOptionPane.PLAIN_MESSAGE);
						}					
			}else {
				JOptionPane.showMessageDialog(this, "미성년자 가입불가","경고",JOptionPane.WARNING_MESSAGE);
			}
		}
	}//버튼괄호의 끝
	
	
	public void repaint() {
		txtId.setText("");
		txtPw.setText("");
		txtAge.setText("");
		contentPane.remove(addUserPanel);
		contentPane.remove(addUserPanel2);
		contentPane.revalidate();
		contentPane.add(panel_1, BorderLayout.CENTER);
		contentPane.add(panel_2,BorderLayout.SOUTH);
		contentPane.repaint();
	}
	
}//클래스닫는 대괄호

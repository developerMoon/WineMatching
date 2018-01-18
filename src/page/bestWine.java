package page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;

public class bestWine extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnleft,btnright;
	private JLabel main;
	private int index=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bestWine frame = new bestWine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public bestWine() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 534); //보이는 화면 크기 고정, 뒤에 두 숫자 가로,세로 픽셀
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		
		JPanel title = new JPanel();
		title.setBackground(Color.PINK);
		contentPane.add(title, BorderLayout.NORTH);
		
		JLabel lblNewLabel_3 = new JLabel("< BEST WINE TOP 4 >");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 25));
		title.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		btnleft = new JButton("\u25C0");
		btnleft.setFont(new Font("굴림", Font.PLAIN, 20));
		btnleft.setForeground(Color.PINK);
		btnleft.setBackground(Color.WHITE);
		btnleft.setIcon(null);
		panel_1.add(btnleft);
		
		main = new JLabel("");
		panel_1.add(main);
		
		btnright = new JButton("\u25B6");
		btnright.setFont(new Font("굴림", Font.PLAIN, 20));
		btnright.setForeground(Color.PINK);
		btnright.setBackground(Color.WHITE);
		btnright.setIcon(null);
		panel_1.add(btnright);
		
		//기본으로 띄워놓기
		main.setIcon(new ImageIcon(bestWine.class.getResource("/page/best0.jpg")));
		
		
		//버튼 actionListener 추가
		btnright.addActionListener(this);
		btnleft.addActionListener(this);
		
		//이 창만 닫기위해서 설정
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
		}});

	}//public bestWine close
	
	//주문하기 버튼 만들어서 똑같은 플로우로...?! >>주문>>장바구니..	
	
	@Override
	public void actionPerformed(ActionEvent e) { //버튼에 따라서 순위를 순차적으로 보여줌
		JButton btn = (JButton) e.getSource();
			if(btn==btnright) {
				index+=1;
				if(index<4) {
					main.setIcon(new ImageIcon(bestWine.class.getResource("/page/best"+index+".jpg")));
				}else if(index==4) {
					index=0;
					main.setIcon(new ImageIcon(bestWine.class.getResource("/page/best"+index+".jpg")));
				}
			}
			if(btn==btnleft) {
				index-=1;
				if(index>-1) {
					main.setIcon(new ImageIcon(bestWine.class.getResource("/page/best"+index+".jpg")));
				}else if(index==-1) {
					index=3;
					main.setIcon(new ImageIcon(bestWine.class.getResource("/page/best"+index+".jpg")));
				}
			}
	}
	

}

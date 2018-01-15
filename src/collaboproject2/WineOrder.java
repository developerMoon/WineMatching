package collaboproject2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.Dimension;

//결제하는 클래스
public class WineOrder extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel titleLabel;
	private JPanel panel=new JPanel();
	private CardLayout card=new CardLayout();
	private JButton btnBack,btnNext,btnCancel;
	JTextField txt2,txt3,cTxt2;
	int pageCount=0;  //0=메인페널 1현금결제 2카드결제 3현금결제중간 4 카드겔제중간 5결제끝
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WineOrder frame = new WineOrder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WineOrder() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		panel.setLayout(card);
		add(panel);
		
		titleLabel = new JLabel("결제선택");
		titleLabel.setFont(new Font("굴림", Font.ITALIC, 16));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleLabel, BorderLayout.NORTH);
		
		JPanel south_panel = new JPanel();
		add(south_panel, BorderLayout.SOUTH);
		
		
		btnNext = new JButton("확인");
		south_panel.add(btnNext);
		
		btnBack = new JButton("이전");
		south_panel.add(btnBack);
		btnBack.setEnabled(false);
		btnCancel=new JButton("취소");
		south_panel.add(btnCancel);
		
		btnNext.addActionListener(this);
		btnBack.addActionListener(this);
		btnCancel.addActionListener(this);
		//선택패널
		JPanel selectPanel=new JPanel();
		selectPanel.setLayout(new GridLayout(0, 2));
		JRadioButton rbtnCard=new JRadioButton("카드결제");
		JRadioButton rbtnCash=new JRadioButton("현금결제");
		ButtonGroup group=new ButtonGroup();
		group.add(rbtnCash);
		group.add(rbtnCard);
		selectPanel.add(rbtnCard);
		selectPanel.add(rbtnCash);
		panel.add(selectPanel,"main");
		
		rbtnCash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panel, "cash");	
				pageCount=1;
				btnBack.setEnabled(true);
				rbtnCash.setSelected(false);
				titleLabel.setText("현금결제");
				btnNext.setText("결제하기");
			}
		});
		rbtnCard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panel, "card");	
				pageCount=2;
				btnBack.setEnabled(true);
				rbtnCard.setSelected(false);
				titleLabel.setText("카드결제");
				btnNext.setText("결제하기");
			}
		});
		//현금
		JPanel cashPanel=new JPanel();
		cashPanel.setLayout(new BorderLayout());
		JScrollPane scroll=new JScrollPane();
		String columnNames[]= {"상품번호","이름","원산지","가격"};
		DefaultTableModel model=new DefaultTableModel(null, columnNames);
		JTable table=new JTable(model);
		scroll.setViewportView(table);
		cashPanel.add(scroll,BorderLayout.CENTER);
		JLabel priceLabel=new JLabel("총 가격");
		JTextField priceTxt=new JTextField();
		priceTxt.setColumns(10);
		priceTxt.setEditable(false);
		JPanel southPanel=new JPanel();
		southPanel.add(priceLabel);
		southPanel.add(priceTxt);
		cashPanel.add(southPanel,BorderLayout.SOUTH);
		
		panel.add(cashPanel,"cash");
		//현금결제
		JPanel cashPanel2=new JPanel();
		cashPanel2.setLayout(new GridLayout(0,2));
		JLabel lbl1= new JLabel("결제할 금액");
		JTextField txt1=new JTextField();
		txt1.setColumns(10);
		txt1.setEditable(false);
		JLabel lbl2= new JLabel("지불할 금액");
		txt2=new JTextField();
		txt2.setColumns(10);
		JLabel lbl3= new JLabel("남은 금액");
		txt3=new JTextField();
		txt3.setColumns(10);
		txt3.setEditable(false);
		
		cashPanel2.add(lbl1);
		cashPanel2.add(txt1);
		cashPanel2.add(lbl2);
		cashPanel2.add(txt2);
		cashPanel2.add(lbl3);
		cashPanel2.add(txt3);
		panel.add(cashPanel2, "cash2");
		
		txt2.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					btnNext.setEnabled(true);
				int a=Integer.parseInt(txt1.getText());
				int b=Integer.parseInt(txt2.getText());
				int result=b-a;
				txt3.setText(String.valueOf(result));
				if(result>=0) {
					btnNext.setEnabled(true);
				}else {
					btnNext.setEnabled(false);
				}
				}catch(NumberFormatException nfe) {
					btnNext.setEnabled(false);
					txt3.setText("");
				}
			}
			
		});
		//현금결제 끝
		JPanel cashPanel3=new JPanel();
		cashPanel3.setLayout(new GridLayout(0, 1));
		JLabel lbl4= new JLabel("결제완료");
		JLabel lbl5=new JLabel("구매해 주셔서 감사합니다.");
		cashPanel3.add(lbl4);
		cashPanel3.add(lbl5);
		panel.add(cashPanel3, "cash3");
		
		
		//카드
		JPanel cardPanel=new JPanel();
		cardPanel.setLayout(new BorderLayout());
		JScrollPane scroll2=new JScrollPane();
		String columnNames2[]= {"상품번호","이름","원산지","가격"};
		DefaultTableModel model2=new DefaultTableModel(null, columnNames2);
		JTable table2=new JTable(model2);
		scroll2.setViewportView(table2);
		cardPanel.add(scroll2,BorderLayout.CENTER);
		JLabel priceLabel2=new JLabel("총 가격");
		JTextField priceTxt2=new JTextField();
		priceTxt2.setColumns(10);
		JPanel southPanel2=new JPanel();
		southPanel2.add(priceLabel2);
		southPanel2.add(priceTxt2);
		cardPanel.add(southPanel2, BorderLayout.SOUTH);
		panel.add(cardPanel,"card");
		
		//카드중간
		JPanel cardPanel2=new JPanel();
		cardPanel2.setLayout(new BorderLayout());
		JScrollPane scroll3=new JScrollPane();
		String listData[]= {"국민","하나","신한","농협","우리","기업","외환"};
		JList<String> list=new JList<>(listData);
		scroll3.setViewportView(list);
		cardPanel2.add(list,BorderLayout.CENTER);
		
		JLabel cLbl1= new JLabel("결제할 금액");
		JTextField cTxt1=new JTextField();
		cTxt1.setColumns(10);
		cTxt1.setEditable(false);
		JLabel cLbl2= new JLabel("지불할 금액");
		cTxt2=new JTextField();
		cTxt2.setEditable(false);
		cTxt2.setColumns(10);
		JPanel panel_cardPanel2=new JPanel();
		panel_cardPanel2.setLayout(new GridLayout(0, 2));
		panel_cardPanel2.add(cLbl1);
		panel_cardPanel2.add(cTxt1);
		panel_cardPanel2.add(cLbl2);
		panel_cardPanel2.add(cTxt2);
		cardPanel2.add(panel_cardPanel2,BorderLayout.SOUTH);
		panel.add(cardPanel2,"card2");
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				cTxt2.setText(cTxt1.getText());
				btnNext.setEnabled(true);
			}
		});
		//카드결제끝
		
		
		}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton)e.getSource();
		if(btn==btnBack) {
			if(pageCount==1 || pageCount==2) {
				card.show(panel, "main");
				btnBack.setEnabled(false);
				pageCount=0;
				titleLabel.setText("결제선택");
			}else if(pageCount==3) {
				card.show(panel, "cash");
				pageCount=1;
				btnNext.setEnabled(true);
				txt2.setText("");
				txt3.setText("");
			}else if(pageCount==4) {
				card.show(panel, "card");
				pageCount=2;
				btnNext.setEnabled(true);
				cTxt2.setText("");
			}
			
		}else if(btn==btnNext) {
			if(pageCount==1) {//현금결제
				card.show(panel, "cash2");
				pageCount=3;
				btnNext.setText("결제");
				btnNext.setEnabled(false);
			}else if(pageCount==2) {//카드결제중간
				card.show(panel, "card2");
				pageCount=4;
				btnNext.setText("결제");
				btnNext.setEnabled(false);
			}else if(pageCount==3 || pageCount==4) {//현금결제중간
				card.show(panel, "cash3");
				btnBack.setVisible(false);
				btnNext.setText("구매완료");
				pageCount=5;
				titleLabel.setText("결제완료");
			}else if(pageCount==5) {//현금결제 끝 버튼초기화닫기 
				reset();
				dispose();
			}
		}else if(btn==btnCancel) {
			winebasket basket=new winebasket();
			basket.setVisible(true);
			dispose();
		}
	}
	public void reset() {
		btnNext.setText("확인");
		btnBack.setText("이전");
		btnBack.setVisible(true);
		titleLabel.setText("결제선택");
	}
	
	
}



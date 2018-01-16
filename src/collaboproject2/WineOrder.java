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

import javafx.scene.layout.Border;

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
import java.awt.image.TileObserver;
import java.nio.ByteOrder;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

//결제하는 클래스
public class WineOrder extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel titleLabel;
	private JPanel panel=new JPanel();
	private CardLayout card=new CardLayout();
	private JButton btnBack,btnNext,btnCancel;
	private JTextField txt3;
	private int pageCount=0;  //0=메인페널 1=현금결제 2=카드결제 3=마지막페이지
	private String id;
	private int sum=0;
	
	

	
	public WineOrder(String id) {
		this.id=id;
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
				titleLabel.setText("현금결제");
				card.show(panel, "cash");
				btnNext.setText("결제하기");
				btnNext.setEnabled(false);
				btnBack.setEnabled(true);
				pageCount=1;
			}
		});
		rbtnCard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				titleLabel.setText("카드결제");
				card.show(panel, "card");
				btnNext.setText("결제하기");
				btnNext.setEnabled(false);
				btnBack.setEnabled(true);
				pageCount=2;
			}
		});
		
		//현금결제패널
		JPanel cashPanel=new JPanel(new BorderLayout());
		
		//북쪽
		//센터
		JPanel centerPanel=new JPanel();
		JScrollPane scroll=new JScrollPane();
		JTable  table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"상품번호","이름","가격","수량"
			}
		));
		scroll.setViewportView(table);
		centerPanel.add(scroll);
		cashPanel.add(centerPanel,BorderLayout.CENTER);
		
		DefaultTableModel model=(DefaultTableModel) table.getModel();
		model.setRowCount(0);
		BasketDAO bDAO=new BasketDAO();
		Vector<BasketVO> bVec=bDAO.getBasket(id);
		Vector<Object> rowData;
		for(BasketVO vo : bVec) {
			rowData=new Vector<>();
			rowData.addElement(vo.getNo());
			rowData.addElement(vo.getName());
			rowData.addElement(vo.getPrice());
			rowData.addElement(vo.getCount());
			model.addRow(rowData);
			int count=vo.getCount();
			sum +=(vo.getPrice()*count);
		}		
		//남쪽
		JPanel southPanel=new JPanel(new GridLayout(0, 2));
		JLabel label=new JLabel("총액");
		JTextField text=new JTextField();
		text.setEnabled(false);
		text.setText(sum+"");
		JLabel label2=new JLabel("입금");
		JTextField text2=new JTextField();
		JLabel label3=new JLabel("잔돈");
		JTextField text3=new JTextField();
		text3.setEnabled(false);
		southPanel.add(label);
		southPanel.add(text);
		southPanel.add(label2);
		southPanel.add(text2);
		southPanel.add(label3);
		southPanel.add(text3);
		cashPanel.add(southPanel, BorderLayout.SOUTH);
		
		text2.addKeyListener(new KeyAdapter() {
			@Override

			public void keyReleased(KeyEvent e) {
				try {
					btnNext.setEnabled(true);
				int a=Integer.parseInt(text.getText());
				int b=Integer.parseInt(text2.getText());
				int result=b-a;
				text3.setText(String.valueOf(result));
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
		panel.add(cashPanel, "cash");
		
		
		//카드결제패널
		JPanel cardPanel=new JPanel(new BorderLayout());
		//북쪽
		//센터
		JPanel c_centerPanel=new JPanel();
		JScrollPane c_scroll=new JScrollPane();
		JTable  c_table = new JTable();
		c_table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"상품번호","이름","가격","수량"
			}
		));
		c_scroll.setViewportView(c_table);
		c_centerPanel.add(c_scroll);		
		DefaultTableModel c_model=(DefaultTableModel) c_table.getModel();
		c_model.setRowCount(0);
		Vector<Object> rowData2;
		for(BasketVO vo : bVec) {
			rowData2=new Vector<>();
			rowData2.addElement(vo.getNo());
			rowData2.addElement(vo.getName());
			rowData2.addElement(vo.getPrice());
			rowData2.addElement(vo.getCount());
			c_model.addRow(rowData2);
			int count=vo.getCount();
			sum +=(vo.getPrice()*count);
		}
		cardPanel.add(c_centerPanel,BorderLayout.CENTER);
		JPanel cPanel=new JPanel(new BorderLayout());
		//남쪽
		JPanel c_southPanel=new JPanel(new GridLayout(0, 2));
		JLabel c_label=new JLabel("총액");
		JTextField c_text=new JTextField();
		c_text.setEnabled(false);
		c_text.setText(sum+"");
		JLabel c_label2=new JLabel("입금");
		JTextField c_text2=new JTextField();
		JLabel c_label3=new JLabel("잔돈");
		JTextField c_text3=new JTextField();
		c_text3.setEnabled(false);
		c_southPanel.add(c_label);
		c_southPanel.add(c_text);
		c_southPanel.add(c_label2);
		c_southPanel.add(c_text2);
		c_southPanel.add(c_label3);
		c_southPanel.add(c_text3);
				
		c_text2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					btnNext.setEnabled(true);
				int a=Integer.parseInt(c_text.getText());
				int b=Integer.parseInt(c_text2.getText());
				int result=b-a;
				c_text3.setText(String.valueOf(result));
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
		JPanel cardPanel2=new JPanel();
		cardPanel2.setLayout(new BorderLayout());
		JScrollPane scroll3=new JScrollPane();
		String listData[]= {"국민","하나","신한","농협","우리","기업","외환"};
		JList<String> list=new JList<>(listData);
		list.setVisibleRowCount(1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll3.setViewportView(list);
		cardPanel2.add(list,BorderLayout.CENTER);
		list.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				c_text2.setText(c_text.getText());
				btnNext.setEnabled(true);
				c_text3.setText("0");
			}

		});
		cPanel.add(c_southPanel,BorderLayout.CENTER);
		cPanel.add(cardPanel2,BorderLayout.SOUTH);
		cardPanel.add(cPanel, BorderLayout.SOUTH);
		
		panel.add(cardPanel, "card");
		
		
		//마지막 결제
		JPanel lastPanel=new JPanel();
		lastPanel.setLayout(new GridLayout(0, 1));
		JLabel lastLbl=new JLabel("구매해 주셔서 감사합니다.");
		lastPanel.add(lastLbl);
		panel.add(lastPanel, "last");		
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton)e.getSource();
		if(btn==btnBack) {
				card.show(panel, "main");
				reset();
				pageCount=0;		
		}else if(btn==btnNext) {
			if(pageCount==1 || pageCount==2) {
				titleLabel.setText("결제완료");
				card.show(panel, "last");
				btnNext.setText("확인");
				btnBack.setVisible(false);
				pageCount=3;
			}else if(pageCount==3) {
				reset();
				dispose();
			}
		}else if(btn==btnCancel) {
			reset();
			dispose();
		}
	}
	public void reset() {
		btnNext.setText("확인");
		btnBack.setText("이전");
		btnBack.setVisible(true);
		btnBack.setEnabled(false);
		titleLabel.setText("결제선택");
		pageCount=0;
	}
	
	
}



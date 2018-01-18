package collaboproject2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthSplitPaneUI;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;


import java.awt.Image;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridLayout;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class Pselectedwine2 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Vector<WineVO> vec;
	private WineDAO dao=new WineDAO();
	private JButton btnorder;
	private JPanel panel;
	private String id;
	private JPanel centerPanel;
	private ButtonGroup group=new ButtonGroup();
	private String name=new String();
	private JPopupMenu popupMenu;
	private JMenuItem menuItemInfo,menuItemBasket,menuItemGoBasket,menuItemOrder;
	private Vector<String> vecName=new Vector<>();
	
	public Pselectedwine2(String id) {
		this.id=id;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnorder = new JButton("주문하기");
		panel.add(btnorder);
		
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 3, 0, 5));
		
		btnorder.addActionListener(this);
	}

//
//
	public void show(int body, int sweet, String type, int lprice, int hprice) {
		//검색한 것 가져온것
			WineDAO dao=new WineDAO();
			 vec=dao.getTable(body ,sweet, type, lprice, hprice);	
			 
			 for(int i=0;i<vec.size();i++) {
				 WineVO vo=vec.get(i);
				ImageIcon icon=new ImageIcon(WineDetail.class.getResource("/collaboproject2/"+vo.getNo()+".jpg")); 
				Image image=icon.getImage();
				Image changeImage=image.getScaledInstance(100, 180, Image.SCALE_SMOOTH);
				ImageIcon newIcon=new ImageIcon(changeImage);
				String name=vo.getName();
				vecName.add(name);
				int endIndex=name.indexOf("(");
				String newName=name.substring(0, endIndex);
				JToggleButton tbtn=new JToggleButton(newName,newIcon);
				tbtn.setHorizontalTextPosition(SwingConstants.CENTER); //이 버튼에 표시되고 있는 텍스트와 아이콘과의 거리를 돌려줍니다. 
		        tbtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		        
		        tbtn.setBounds(0, 0, 30, 50);
		        tbtn.addActionListener(new buttonListener());				
				
				popupMenu = new JPopupMenu();
				addPopup(tbtn, popupMenu);				
				menuItemInfo = new JMenuItem("상세정보보기");
				menuItemOrder = new JMenuItem("주문하기");
				menuItemBasket = new JMenuItem("장바구니담기");
				menuItemGoBasket = new JMenuItem("장바구니보기");
				popupMenu.add(menuItemInfo);
				popupMenu.add(menuItemOrder);
				popupMenu.add(menuItemBasket);
				popupMenu.add(menuItemGoBasket);
				menuItemInfo.addActionListener(new menuItemListner());
				menuItemOrder.addActionListener(new menuItemListner());
				menuItemBasket.addActionListener(new menuItemListner());
				menuItemGoBasket.addActionListener(new menuItemListner());
				
				centerPanel.add(tbtn);
				group.add(tbtn);
			 }
			pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		JButton btn=(JButton) e.getSource();
		if(btn==btnorder) {
			String options[]= {"주문", "취소"};
			int result = JOptionPane.showOptionDialog(this, "와인을 주문할까요?", "주문", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					null , options, options[0]);	
			if(result==0) { 
				addBasket(1);//장바구니 DB에 넣고
				showOrderPage(); //결제페이지로 넘어가기
				System.out.println("주문하기버튼");
			}
		}
		
	}	
	
	public void showOrderPage() {
		WineOrder order=new WineOrder(id);
		order.setVisible(true);
		dispose();
	}
	public void showBasketPage() {
		winebasket basket=new winebasket(id);
		basket.setVisible(true);
	}
	public void showWineDetailPage() { //와인상세정보띄우는메소드							
			WineDAO wDAO=new WineDAO();
			//선택한거 바스켓vec에 담기
			WineVO vo=wDAO.getRow(name);
			try {
			WineDetail wd=new WineDetail(vo.getNo());
			wd.setVisible(true);
			}catch(NullPointerException e) {
				JOptionPane.showMessageDialog(this, "와인을 선택해주세요");
			}
	}
		
		
	public void addBasket(int count) { //장바구니에 넣기
		WineDAO wDAO=new WineDAO();
		//선택한거 바스켓vec에 담기
		try {
		WineVO vo=wDAO.getRow(name);
		BasketVO bVO=new BasketVO(id, vo.getNo(), vo.getName(), vo.getCountry(), vo.getPrice(), count);		
		//DB에집어넣기
		BasketDAO bDAO=new BasketDAO();
		bDAO.addBasket(bVO);
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(this, "와인을 선택해주세요");
		}
	}
	//선택한거
	class buttonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				
				name=e.getActionCommand();
					for(String str:vecName) {
						int endIndex=str.indexOf("(");
						String newName=str.substring(0, endIndex);
				if(newName.equals(name)) {
					name=str;break;
				}
			}
		}		
	}
	class menuItemListner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JMenuItem item=(JMenuItem) e.getSource();
			if(item==menuItemInfo) { //상세정보보기
				showWineDetailPage();
			}else if(item==menuItemBasket) { //장바구니 담기
				addBasket(1);
			}else if(item==menuItemGoBasket) { //장바구니가기
				showBasketPage();
			}else if(item==menuItemOrder) { //주문하기
				showOrderPage();
			}
			
		}
		
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}//클래스의 마지막 괄호

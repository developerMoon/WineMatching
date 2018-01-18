package collaboproject2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class selectedwine extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private Vector<WineVO> vec;
	private Vector<Object> rowData;
	private DefaultTableModel model;
	private WineDAO dao=new WineDAO();
	private JButton btnorder, btndetail;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private Vector <Integer> select = new Vector<Integer>();
	private JLabel lblNewLabel;
	private String id;
	private JPopupMenu popupMenu;
	private JMenuItem mntBasket;
	private JMenuItem mntGoBasket;
	private JMenuItem mntInfo;
	private JMenuItem mntOrder;
	
	class mntaction implements ActionListener{
		//팝메뉴액션리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem mnt=(JMenuItem)e.getSource();
			if(mnt==mntInfo) {//상세정보보기
				showWineDetailPage();
			}else if(mnt==mntOrder) {//결제페이지로넘어가기
				boolean result=addBasket();
				if(result) {
					showOrderPage();
				}
			}else if(mnt==mntGoBasket) {//장바구니로 넘어가기
				showBasketPage();
			}else if(mnt==mntBasket) {//장바구니에 담기
				addBasket();
			}
			
		}
		
	}
	
	public selectedwine(String id) {
		this.id=id;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 817, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"\uC0C1\uD488\uBC88\uD638", "\uC774\uB984", "\uC6D0\uC0B0\uC9C0", "\uAC00\uACA9"
			}
		));
		scrollPane.setViewportView(table);
		
		popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		mntInfo = new JMenuItem("와인 상세 정보보기");
		popupMenu.add(mntInfo);
		
		mntOrder = new JMenuItem("주문하기");
		popupMenu.add(mntOrder);
		
		mntGoBasket = new JMenuItem("장바구니 보기");
		popupMenu.add(mntGoBasket);
		
		mntBasket = new JMenuItem("장바구니 담기");
		popupMenu.add(mntBasket);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		
		
		
		btndetail = new JButton("와인 상세 정보");
		panel.add(btndetail);
		
		btnorder = new JButton("주문하기");
		panel.add(btnorder);
		
		lblNewLabel = new JLabel("여러 개의 와인 주문은 Ctrl 키를 누르세요");
		panel.add(lblNewLabel);
		
		
		
		btnorder.addActionListener(this);
		btndetail.addActionListener(this);
		
		mntInfo.addActionListener(new mntaction());
		mntGoBasket.addActionListener(new mntaction());
		mntBasket.addActionListener(new mntaction());
		mntOrder.addActionListener(new mntaction());
	}

//
//선택한 와인 목록 보여줌
	public void show(int body, int sweet, String type, int lprice, int hprice) {
		
			//System.out.println(sweet+" "+type+" "+price);
			//table refresh-우선 새 defaulttable에 담아서 다 지우고
			model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			//검색한 와인 정보 보여주기
			//레드나 타입으로 검색하게
			//시간이 나면 생각해 보는걸로
			 vec=dao.getTable(body, sweet, type, lprice, hprice);	
			System.out.println(body+sweet + type+ lprice+hprice);
			//타입별 와인 조회
				for(WineVO vo : vec) {
					rowData=new Vector<>();
					rowData.addElement(vo.getNo());
					rowData.addElement(vo.getName());
					rowData.addElement(vo.getCountry());
					rowData.addElement(vo.getPrice());
					model.addRow(rowData);
					}
			if(vec==null) {
				String options[]= {"이전"};
				int result = JOptionPane.showOptionDialog(this, "검색된 결과의 와인이 없습니다", "이전", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
						null , options, options[0]);	
			}
	
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
			}
			
		}else if(btn==btndetail) {
				showWineDetailPage();
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
		try {
		int row=table.getSelectedRow();	//선택한 행에서 첫번째 컬럼 값 가져오기
		int idx=(int) model.getValueAt(row, 0);	//선택한 행 
		WineDetail wd=new WineDetail(idx);
		wd.setVisible(true);
		}catch(ArrayIndexOutOfBoundsException e) {}
	}
	public boolean addBasket() { //장바구니에 넣기
		if(table.isColumnSelected(0) || table.isColumnSelected(1) || table.isColumnSelected(2) || table.isColumnSelected(3) ) {
			String[] selectionValues = { "1", "2", "3","4","5","6","7","8","9" };
			String strCount=(String) JOptionPane.showInputDialog(null, "몇병을 주문하시겠습니까?",
					"주문수량", JOptionPane.QUESTION_MESSAGE, null, selectionValues, "1");
			try {
				int count=Integer.parseInt(strCount);
				BasketDAO bDAO=new BasketDAO();
				int row[]=table.getSelectedRows();
				for(int i=0 ; i<row.length; i++) {
					int idx=(int) model.getValueAt(row[i], 0); //상품번호
					WineVO vo=dao.getRow(idx);
					BasketVO bvo=new BasketVO(id, vo.getNo(), vo.getName(), vo.getCountry(), vo.getPrice(),count);
					bDAO.addBasket(bvo); //장바구니DB에 넣기
				}
			}catch(Exception e) {return false;}//어레이예외 넘버포맷예외
			return true;
		}else {
			return false;
		}
	}
		
		
		public void addBasket(int count) { //장바구니에 넣기
			try {
				BasketDAO bDAO=new BasketDAO();
				int row[]=table.getSelectedRows();
				for(int i=0 ; i<row.length; i++) {
					int idx=(int) model.getValueAt(row[i], 0); //상품번호
					WineVO vo=dao.getRow(idx);
					BasketVO bvo=new BasketVO(id, vo.getNo(), vo.getName(), vo.getCountry(), vo.getPrice(),count);
					bDAO.addBasket(bvo); //장바구니DB에 넣기
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
		}
	
	
}//클래스의 마지막 괄호

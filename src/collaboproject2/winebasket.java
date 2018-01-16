package collaboproject2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class winebasket extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private JTextField txtsum;
	BasketDAO dao = new BasketDAO();
	private Vector<WineVO> vec = new Vector<WineVO>();
	private Vector<Object> rowData;
	private DefaultTableModel model;
	private JButton btnOrder, btnBack; 
	private JPopupMenu popupMenu;
	private JMenuItem mntInfo;
	private JMenuItem mntDel;
	private String id;
	
	public winebasket(String id) {
		this.id=id;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("장바구니");
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Winename", "Price","count"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("총액 :");
		panel_1.add(lblNewLabel);
		
		txtsum = new JTextField();
		panel_1.add(txtsum);
		txtsum.setColumns(10);
		
		btnOrder = new JButton("결제");
		panel_1.add(btnOrder);
		
		btnBack = new JButton("이전");
		panel_1.add(btnBack);
		
		btnOrder.addActionListener(this);
		btnBack.addActionListener(this);
		
		tableReset();
		
		popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		mntInfo = new JMenuItem("상세정보");
		popupMenu.add(mntInfo);
		
		mntDel = new JMenuItem("물건빼기");
		popupMenu.add(mntDel);
		
		mntInfo.addActionListener(new ActionListener() {
			//상세정보보기
			@Override
			public void actionPerformed(ActionEvent e) {
				showWineDetail();
			}
		});
		
		mntDel.addActionListener(new ActionListener() {
			//물건뺴기
			@Override
			public void actionPerformed(ActionEvent e) {
				delBasket();//자바 DB에서 삭제
				tableReset();//테이블에 다시 뿌리기
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton)e.getSource();
		if(btn==btnOrder) {//결제 창 띄우기
			WineOrder order=new WineOrder(id);
			order.setVisible(true);
			dispose();
		}else if(btn==btnBack) {//이전
			 dispose();
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
	
	public void showWineDetail() { //상세정보 띄워주는 페이지
		int row=table.getSelectedRow();
		String wineName=(String) model.getValueAt(row, 0);
		WineDAO dao=new WineDAO();
		WineVO vo=dao.getRow(wineName);
		WineDetail wd=new WineDetail(vo.getNo());
		wd.setVisible(true);
	}
	public void delBasket() { //장바구니 DB에서 삭제하는 부분
		BasketDAO bDao=new BasketDAO();
		model=(DefaultTableModel) table.getModel();
		int row=table.getSelectedRow();
		int count=(int) model.getValueAt(row, 2);
		String name=(String) model.getValueAt(row, 0);
		if(count>1) {
			String str="";
			for(int i=1;i<=count;i++) {
				str+=i+"-";
			}
			String[] strCount=str.split("-");
			
			String strCount1=(String) JOptionPane.showInputDialog(null, "몇병을 빼시겠습니까?",
			        "주문수정", JOptionPane.QUESTION_MESSAGE, null, strCount, "1");
			int count1=Integer.parseInt(strCount1);
			int originCount=bDao.getCount(name, id);
			
			if(originCount==count1) {
				bDao.delBasket(name, id);
			}else {
				bDao.countDown(name, id, originCount, count);
			}
		}else {
			bDao.delBasket(name, id);
		}
		
		
		
	}
	public void tableReset() {//테이블에 다시 뿌리는 것
		DefaultTableModel model=(DefaultTableModel) table.getModel();
		model.setRowCount(0);//초기화
		BasketDAO bDAO=new BasketDAO();
		Vector<BasketVO> bVec=bDAO.getBasket(id);
		int sum=0;	
		for(BasketVO vo : bVec) {
			rowData=new Vector<>();
			rowData.addElement(vo.getName());
			rowData.addElement(vo.getPrice());
			rowData.addElement(vo.getCount());
			model.addRow(rowData);
			int count=vo.getCount();
			sum +=(vo.getPrice()*count);
		}// vec for 문 끝
		txtsum.setText(sum+"");
	}
	
}

package collaboproject2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.javafx.scene.control.skin.TableHeaderRow;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class AllWineList extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private Vector<WineVO> vec;
	private Vector<Object> rowData;
	private JPanel panel;
	private DefaultTableModel model;
	private WineDAO dao=new WineDAO();
	private JButton btnorder;
	private JButton btncancel;
	private String id;
	private JPopupMenu popupMenu;
	private JMenuItem mntInfo;
	private JMenuItem mntAdd;
	private JMenuItem mntGo;

	/**
	 * Create the frame.
	 */

	public AllWineList(String id) {
			this.id=id;
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 817, 601);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			contentPane.setBackground(Color.white); //배경색 변경
			setContentPane(contentPane);
			
			JScrollPane scrollPane = new JScrollPane();
			contentPane.add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable();
			
			//header변경
			//헤더 색상
			JTableHeader header =table.getTableHeader();
			header.setBackground(Color.PINK);
			//헤더 글씨
			header.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
			
		
			
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"\uC0C1\uD488\uBC88\uD638", "\uC774\uB984", "\uD68C\uC0AC", "\uAC00\uACA9"
				}
			));
			//테이블 내부 정보 폰트 변경
			table.setFont(new Font("나눔고딕", Font.PLAIN, 12));
			
			scrollPane.setViewportView(table);
			
			popupMenu = new JPopupMenu();
			addPopup(table, popupMenu);
			
			mntInfo = new JMenuItem("상세정보보기");
			popupMenu.add(mntInfo);
			
			mntAdd = new JMenuItem("장바구니담기");
			popupMenu.add(mntAdd);
			
			mntGo = new JMenuItem("장바구니가기");
			popupMenu.add(mntGo);
			
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			
			contentPane.add(panel, BorderLayout.SOUTH);
			
			btnorder = new JButton("주문하기");
			btnorder.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
			btnorder.setForeground(Color.WHITE);
			btnorder.setBackground(Color.PINK);
			panel.add(btnorder);
			
			btncancel = new JButton("취소");
			btncancel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
			btncancel.setForeground(Color.WHITE);
			btncancel.setBackground(Color.PINK);
			panel.add(btncancel);
			
			btnorder.addActionListener(this);
			btncancel.addActionListener(this);
			
			mntInfo.addActionListener(new mntButton());
			mntGo.addActionListener(new mntButton());
			mntAdd.addActionListener(new mntButton());
			
	}
		public void wines() {
		//와인 전체 조회하는 테이블 만듬
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		 vec=dao.getList();	
		
			for(WineVO vo : vec) {
				rowData=new Vector<>();
				rowData.addElement(vo.getNo());
				rowData.addElement(vo.getName());
				rowData.addElement(vo.getCompany());
				rowData.addElement(vo.getPrice());
				model.addRow(rowData);
				}
	
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn=(JButton) e.getSource();
			
			if(btn==btnorder) {
				//주문하기
				//선택된 여러개의 와인을 장바구니에 담거나 바로 주문서 작성하도록
				addBasket();
			}else if(btn==btncancel) {
				//취소
				dispose();
			}
		}
		
		public void addBasket() {
			DefaultTableModel model=(DefaultTableModel) table.getModel();
			int rows[]=table.getSelectedRows();
			Vector<Integer> vec=new Vector<>();
			for(int row:rows) {
				int idx=(int)model.getValueAt(row, 0);
				vec.add(idx);
			}
			BasketDAO bDAO=new BasketDAO();
			for(Integer idx:vec) {
				WineVO vo=dao.getRow(idx);
				BasketVO bvo=new BasketVO(id, vo.getNo(), vo.getName(), vo.getCountry(), vo.getPrice(),1);
				bDAO.addBasket(bvo); //장바구니DB에 넣기
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
	public void showBasket() {
		winebasket frame=new winebasket(id);
		frame.setVisible(true);
	}
	public void showDetail() {
		DefaultTableModel model=(DefaultTableModel) table.getModel();
		int rows[]=table.getSelectedRows();
		Vector<Integer> vec=new Vector<>();
		for(int row:rows) {
			int idx=(int)model.getValueAt(row, 0);
			vec.add(idx);
		}
		for(Integer idx:vec) {
		WineDetail frame=new WineDetail(idx);
		frame.setVisible(true);
		}
	}
	class mntButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem mnt=(JMenuItem) e.getSource();
			if(mnt==mntInfo) {//상세정보보기
				showDetail();
			}else if(mnt==mntGo) { //장바구니가기
				showBasket();
			}else if(mnt==mntAdd) {//장바구니 담기
				addBasket();
			}
		}
		
	}
}

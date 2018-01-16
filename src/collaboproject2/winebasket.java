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
				"Winename", "Price"
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
		
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		
		popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		mntInfo = new JMenuItem("상세정보");
		popupMenu.add(mntInfo);
		
		mntDel = new JMenuItem("삭제");
		popupMenu.add(mntDel);
		
		mntInfo.addActionListener(new ActionListener() {
			//상세정보보기
			@Override
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				String wineName=(String) model.getValueAt(row, 0);
				
				WineDAO dao=new WineDAO();
				WineVO vo=dao.getRow(wineName);
				WineDetail wd=new WineDetail(vo.getNo());
				
				wd.setVisible(true);
				
			}
		});
		
		mntDel.addActionListener(new ActionListener() {
			//삭제하는부분
			@Override
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				
				String wineName=(String) model.getValueAt(row, 0);
				model.removeRow(row);
				BasketDAO bDao=new BasketDAO();
				bDao.delBasket(wineName, id);
				
				//다시출력
				table.repaint();
				BasketDAO bDAO=new BasketDAO();
				Vector<BasketVO> bVec=bDAO.getBasket(id);
				int sum=0;
				for(int i=0;i<bVec.size();i++) {
					BasketVO bVo=bVec.get(i);
					sum+=bVo.getPrice();
				}
				txtsum.setText(sum+"");
			}
		});
	}
		

	
	public void showbasket(Vector<BasketVO> bVec) {
		//System.out.println(idx);
		//매개값 int 여러개 얻기 위해 for 돌린다
		
		
		
		//for(int i : select) {
		int sum=0;	
		
		for(BasketVO vo : bVec) {
			rowData=new Vector<>();
			rowData.addElement(vo.getName());
			rowData.addElement(vo.getPrice());
			model.addRow(rowData);
			sum +=vo.getPrice();
		}// vec for 문 끝
		
		txtsum.setText(sum+"");
	//}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton)e.getSource();
		if(btn==btnOrder) {//결제 창 띄우기
			WineOrder order=new WineOrder(id);
			order.setVisible(true);
			dispose();
		}else if(btn==btnBack) {//이전
			 selectedwine wine= new selectedwine(id);		 
			 wine.setVisible(true);
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
	
	
}

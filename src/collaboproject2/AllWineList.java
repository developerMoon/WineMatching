package collaboproject2;

import java.awt.BorderLayout;
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
import javax.swing.JButton;

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
	

	/**
	 * Create the frame.
	 */

	public AllWineList() {
			//this.id=id;
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
			
			panel = new JPanel();
			contentPane.add(panel, BorderLayout.SOUTH);
			
			btnorder = new JButton("주문하기");
			panel.add(btnorder);
			
			btncancel = new JButton("취소");
			panel.add(btncancel);
			
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
				rowData.addElement(vo.getCountry());
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
				
			}else if(btn==btncancel) {
				//취소
				dispose();
			}
		}
}

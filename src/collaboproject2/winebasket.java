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
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class winebasket extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private JTextField txtsum;
	WineDAO dao = new WineDAO();
	private Vector<WineVO> vec = new Vector<WineVO>();
	private Vector<Object> rowData;
	private DefaultTableModel model;
	private JButton btnOrder, btnBack; 

	/**
	 * Create the frame.
	 */
	public winebasket() {
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
	}

	int sum=0;
	
	public void showbasket(Vector<Integer> select) {
		//System.out.println(idx);
		//매개값 int 여러개 얻기 위해 for 돌린다
		
		
		
		//for(int i : select) {
			vec=dao.getbasket(select);
		
		for(WineVO vo : vec) {
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
			WineOrder order=new WineOrder();
			order.setVisible(true);
			dispose();
		}else if(btn==btnBack) {//이전
			 selectedwine wine= new selectedwine();		 
			 wine.setVisible(true);
			 dispose();
		}
		
	}
	
	
	
}

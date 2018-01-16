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
	/**
	 * Create the frame.
	 */
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
	}

//
//
	public void show(int body, int sweet, String type, int lprice, int hprice) {
		
			//System.out.println(sweet+" "+type+" "+price);
			//table refresh-우선 새 defaulttable에 담아서 다 지우고
			model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			//검색한 와인 정보 보여주기
			//레드나 타입으로 검색하게
			//시간이 나면 생각해 보는걸로
			 vec=dao.getTable(body, sweet, type, lprice, hprice);	
			
			//타입별 와인 조회
			
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
			/*JOptionPane.showConfirmDialog(this, "주문완료!");*/
			String options[]= {"주문", "취소"};
			int result = JOptionPane.showOptionDialog(this, "와인을 주문할까요?", "주문", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					null , options, options[0]);	
			//System.out.println(result); 주문=>0 , 취소=>1
			if(result==0) { //주문
				//사용자가 선택한 행 가져오기
				//여러개 선택할 수 있도록 개선해야 함
				//how?
				BasketVO bvo;
				Vector<BasketVO> bVec=new Vector<>();
				WineShopCustomer customer=new WineShopCustomer();
				BasketDAO bDAO=new BasketDAO();
				int row[]=table.getSelectedRows();
				//선택한 행에서 첫번째 컬럼 값 가져오기
				//여러개 선택이 되긴 함 ctrl 누르고 해야 함
				//배열이 별로인 것 같아서 vector 로 변경 
				for(int i=0 ; i<row.length; i++) {
					int no=row[i];
					int idx=(int) model.getValueAt(no, 0); //상품번호
	
					WineVO vo=dao.getRow(idx);
					bvo=new BasketVO(id, vo.getNo(), vo.getName(), vo.getCountry(), vo.getPrice());
					bDAO.addBasket(bvo); //장바구니DB에 넣기
					bVec=bDAO.getBasket(id);
				//	System.out.println(select);
				
				winebasket winebask= new winebasket(id);
				//이게 idx 하나 받게 되어 있으니 한 장바구니에 여러 개 나오도록 고쳐야
				winebask.showbasket(bVec);
				winebask.setVisible(true);
				//System.out.println("true");
				}
			
			}else if(btn==btndetail) {
				//상세 내용 확인 버튼 누릴 때
				//사용자가 선택한 행 가져오기
				int row=table.getSelectedRow();
				//선택한 행에서 첫번째 컬럼 값 가져오기
				int idx=(int) model.getValueAt(row, 0);
				//선택한 행 
				WineDetail wd=new WineDetail(idx);
				//와인디테일에서 와인 보고 장바구니에 담기게 하고 싶은데 idx 를 그럼 어떻게 옮기지 
				//사실 굳이 거기에서 장바구니로 연동할 필요가 없긴 함.
				//우선 여기서 장바구니로 들어가는 기능은 생각해 볼 것
				//winebasket winebask= new winebasket();
				///winebask.showbasket(select);
				wd.setVisible(true);
			}
		}
		
	}
}

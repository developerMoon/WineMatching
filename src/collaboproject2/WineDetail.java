package collaboproject2;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

	//연결해야함
	//글씨체가 다 너무 작음
	//어울리는 음식 스크롤

public class WineDetail extends JFrame implements ActionListener{

	private JPanel contentPane;
	private WineDAO dao;
	private WineVO vo;
	private JButton btncancel, btnbuy;
	private int idx=0;
	//selectedwine 누르면 선택된 와인 정보 보여지도록 할 것
	//와인명, 원산지, 타입, 스위트, 바디, 가격, 제조사, 어울리는 음식, 사진

	
	//main으로 우선 돌아가는지 확인
	//연결하기 전에
	//dao 사용해서 정보 끌어오기
	//각 라벨에 결과값을 세팅하기 위해
	//무식하게 하자면 dao를 해당 값마다 만든다 (...)
	//idx 로 해당 열 조회하는 메소드 만들어서, 거기에서 값 가져온다


	public WineDetail(int idx) {
		
		dao=new WineDAO();
		vo=new WineVO();
		
		vo=dao.getRow(idx);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 957, 765);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel winename = new JLabel("winename");
		winename.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		panel.add(winename);
		//winename.setFont(new Font("Arial", Font.PLAIN, 14));
		winename.setText(vo.getName());
		
		
		JLabel winepicture = new JLabel("winepicture");
		winepicture.setSize(20, 50);
		
		//picture 에 사진 붙이기
		ImageIcon icon=new ImageIcon(WineDetail.class.getResource("/collaboproject2/"+vo.getNo()+".jpg"));
		winepicture.setIcon(icon);
		winepicture.setText("");
		contentPane.add(winepicture, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("가격");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_5);
		
		JLabel price = new JLabel("New label");
		panel_1.add(price);
		price.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		price.setText(vo.getPrice()+"");
		
		
		JLabel lblNewLabel_2 = new JLabel("원산지");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_2);
		
		JLabel country = new JLabel("New label");
		panel_1.add(country);
		country.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		country.setText(vo.getCountry());
		
		JLabel lblNewLabel_10 = new JLabel("빈티지");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_10);
		
		JLabel vintage = new JLabel("New label");
		panel_1.add(vintage);
		vintage.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		vintage.setText(vo.getVintage()+"");
		
		JLabel lblNewLabel_4 = new JLabel("타입");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_4);
		
		JLabel type = new JLabel("New label");
		panel_1.add(type);
		type.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		type.setText(vo.getType());
		
		JLabel lblNewLabel_6 = new JLabel("바디감 / 당도");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_6);
		
		JLabel bosw = new JLabel("New label");
		panel_1.add(bosw);
		bosw.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		bosw.setText("바디감: "+vo.getBody()+"   당도: "+vo.getSweet());
		
		
		JLabel lblNewLabel_8 = new JLabel("제조사");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_8);
		
		JLabel company = new JLabel("New label");
		panel_1.add(company);
		company.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		company.setText(vo.getCompany());
		
		JLabel lblNewLabel_13 = new JLabel("어울리는 음식");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_13);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		
		
		JLabel food = new JLabel("New label");
		food.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		scrollPane.setViewportView(food);
		//panel_1.add(food);
		food.setText(vo.getFood());
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		btnbuy = new JButton("구매");
		panel_2.add(btnbuy);
		
		btncancel = new JButton("취소");
		panel_2.add(btncancel);
		
		btnbuy.addActionListener(this);
		btncancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton) e.getSource();
		//선택된 와인 idx 를 가져와서
		//그 idx 에 따른 정보를 보여줘야 함
		//구매 누르면 구매 장바구니에 담기고
		//취소 누르면 창 닫혀야
		
		//구매시
		if(btn==btnbuy) {
			//여기서 구매하는 버튼을 유지할지 말지 생각 중
			//JOptionPane.showConfirmDialog(this, "구매 하시겠습니까?");
			
			String options[]= {"장바구니에 담기", "취소"};
			int result = JOptionPane.showOptionDialog(this, "와인을 주문할까요?", "주문",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null , options, options[0]);	
			//System.out.println(result); 주문=>0 , 취소=>1
			if(result==0) {
				
				
				BasketVO bvo;
				Vector<BasketVO> bVec=new Vector<>();
				WineShopCustomer customer=new WineShopCustomer();
				String id=customer.id;
				BasketDAO bDAO=new BasketDAO();
				
					WineVO vo=dao.getRow(idx);
					bvo=new BasketVO(id, vo.getNo(), vo.getName(), vo.getCountry(), vo.getPrice(),1);
					bDAO.addBasket(bvo); //장바구니DB에 넣기
					//임으로 넣움
					bVec=bDAO.getBasket(id);
					
					//장바구니에 담을 시
					winebasket winebask=new winebasket(id);
					winebask.setVisible(true);
					
			}else if(result==1) {
				//취소시
				dispose();
			}
			
			//장바구니에 담기
		}else if(btn==btncancel) {
		//취소시	
			dispose();
			
		}
		
	}//이벤트 끝

}//와인디테일 끝

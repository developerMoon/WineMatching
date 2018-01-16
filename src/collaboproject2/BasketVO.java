package collaboproject2;

public class BasketVO {

	String id; //고객아이디
	int no;	//상품번호
	String name;	//와인이름
	String country; //원산지
	int price;//가격
	
	public BasketVO(String id, int no, String name, String country, int price) {
		super();
		this.id = id;
		this.no = no;
		this.name = name;
		this.country = country;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}

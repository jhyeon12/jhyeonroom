package kiosk;

public class VO_Sales {

	 private int productId;// 상품번호
	 private String  productName; //  판매 상품명
	 
	 private int price;  // 주문한 상품 가격
	 private int calorie;    // 칼로리
	 private String type;   // 상품타입
	 
	 private int quantity; // 주문한 상품  갯수
	 
	public VO_Sales() {
		
	}
	
	public VO_Sales(int productId,  String productName, int price, int calorie, String type, int quantity) {
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.calorie = calorie;
		this.type = type;
	}
	
	
	public void setQuantity(int quant) {
		this.quantity = quant;
	}
	
	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	
	public int getQuantity() {
		return quantity;
	}



	public int getPrice() {
		return price;
	}

	public int getCalorie() {
		return calorie;
	}

	
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "VO_Sales [productName=" + productName + ", quantity=" + quantity + ", price=" + price + ", calorie="
				+ calorie + ", type=" + type + "]";
	}

	public boolean equals(Object o) {
		boolean result = false;
	        if (o instanceof VO_Sales) {
	        	VO_Sales b = (VO_Sales) o;
	            if (this.productName == b.productName) {
	                result = true;
	            }
	        }
	       return result;
	    }
}

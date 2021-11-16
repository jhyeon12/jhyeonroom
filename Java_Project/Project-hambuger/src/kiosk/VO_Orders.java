package kiosk;



	public class VO_Orders {

		
		private int ticketnumber;  // 번호표
		private int ProductId;
		private String PurchaseDate;   // 구매일자		
		private String ageGroup;// 연령대
	 	private String eatHereOrNOt;
	 	private String TableService;
	 	
	 	
	 
	 	
	 	public VO_Orders() {

		}
	 	
	 	
	 	public VO_Orders(int ticketnumber, int productId, String purchaseDate) {

			
			this.ticketnumber = ticketnumber;
			this.ProductId = productId;
			this.PurchaseDate = purchaseDate;
			
		}
	 	
	 	public void setTableService(String arg) {
	 		this.TableService = arg;
	 	}
	 	
	 	public void setEatHereOrNot(String arg) {
	 		this.eatHereOrNOt = arg;
	 	}
	 	
	 	public void setAgeGroup(String arg) {
	 		this.ageGroup = arg;
	 	}
	 	public String getAgeGroup() {
	 		return this.ageGroup;
	 	}
	 	
	 	public void setPurchaseDate(String arg) {
	 		this.PurchaseDate = arg;
	 	}
	 	public void setProductId(int num) {
	 		this.setProductId(num);
	 	}
	 
	 	public String getPurchaseDate() {
	 		return this.PurchaseDate;
	 	}
	 	
	 	public String getTableService() {
	 		return this.TableService;
	 	}
	 	public String getEatHereOrNot() {
	 		return this.eatHereOrNOt;
	 	}


	}
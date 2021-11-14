package kiosk;
public class Main_StoreOwner {

	// 점주의 포스기를 간단하게 구현
		// 아직 진행중

		public static void main(String[] args) {
			Input_Scan scan = new Input_Scan();
			
			while(true) {
				System.out.println(SortPrinting.ROOF);
				System.out.println(SortPrinting.SPACE);
				SortPrinting.centerAlign("Store Owner Management");
				System.out.println(SortPrinting.SPACE);
				System.out.println(SortPrinting.ROW);
				System.out.println(SortPrinting.SPACE);
				SortPrinting.centerAlign("[1] Settlement");	
				System.out.println(SortPrinting.SPACE);
				SortPrinting.centerAlign("[2] Today profit");	
				System.out.println(SortPrinting.SPACE);
				SortPrinting.centerAlign("[3] View Order By General");	
				System.out.println(SortPrinting.SPACE);
				SortPrinting.centerAlign("[4] View Order By Older");	
				System.out.println(SortPrinting.SPACE);
				SortPrinting.centerAlign("[5] EXIT");	
				System.out.println(SortPrinting.SPACE);
				System.out.println(SortPrinting.BOTTOM);
				
				int choice = scan.getChoice(5);
				switch(choice) {
				case 1:
					settlement();
				case 2:
					todayProfit();
				case 3:
					orderByGeneral();
				case 4:
					orderByOlder();
				case 5:
					System.exit(0);
				}
				
				
			}
			
		}
		
		
		
		
		private static void settlement() {
			
		}
		
		private static void todayProfit() {
			
		}
		
		private static void orderByGeneral() {
			
		}
		
		private static void orderByOlder() {
			
		}
		
		
}

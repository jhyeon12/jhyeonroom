package kiosk;

import java.util.List;

public class SortPrinting {

	// 정보를 화면에 정렬해서 출력해주는 클래스

		public static final String ROOF =   new String("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		public static final String ROW =    new String("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
		public static final String BOTTOM = new String("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		public static final String SPACE =  new String("┃                                                                              ┃");

		// 상품에 담긴 상품이름 , 상품가격 , 상품의 칼로리를
		// 상품이름 상품이름 상품이름
		// 상품가격 상품가격 상품가격
		// 칼로리 칼로리 칼로리
		// N열로 출력하는 메소드

		// 매개변수 SalesVariable 이 상품의 이름, 상품의 가격, 상품의 칼로리...
		public static void printInfoInRow(List<VO_Sales> list, int N) {
			VO_Sales[][] listToArray = make2DArrayFromList(list, N);

			for (int index = 0; index < listToArray.length; index++) {

				printNameIn_N_Row(listToArray, N, index);
				System.out.println();
				printPriceIn_N_Row(listToArray, N, index);
				System.out.println();
				printCalIn_N_Row(listToArray, N, index);
				System.out.println();
				System.out.println(SortPrinting.SPACE);

			}

		}
		// 주문 최종 확인시 내 장바구니에 들어간 상품을 출력
		public static void printMyBasketInRow(List<VO_Sales> list, int N) {
			VO_Sales[][] listToArray = make2DArrayFromList(list, N);
			
			for (int index = 0; index < listToArray.length; index++) {
				printNameIn_N_Row(listToArray, N, index);
				System.out.println();
			}
		}

		// 2차원 배열의 index번째에 있는 VO_Sales의 이름을 N열로 출력
		private static void printNameIn_N_Row(VO_Sales[][] testArr, int N, int index) {
			int countOdd =0;

			System.out.print("┃");
			for (int j = 0; j < testArr[index].length; j++) {

				if (testArr[index][j].getProductName().length() % 2 == 1) {
					countOdd++;
				}

				int indexInRealList = (index + 1) * 3 - (N - (j + 1));
				String str = new String("[".concat(String.valueOf(indexInRealList)).concat("] "));

				for (int k = 0; k < ((int) ((ROOF.length() - 2) / testArr[index].length)
						- str.concat(testArr[index][j].getProductName()).length()) / 2; k++) {

					System.out.print(" ");

				}
				// [1]: 버거이름 형식으로 출력
				System.out.print(str.concat(testArr[index][j].getProductName()));

				for (int k = 0; k < ((int) ((ROOF.length() - 2) / testArr[index].length)
						- str.concat(testArr[index][j].getProductName()).length()) / 2; k++) {
					System.out.print(" ");

				}
			}

			for(int i =0; i < countOdd; i++) {
				System.out.print(" ");
			}
			System.out.print("┃");

		}
		
		
		// 2차원 배열의 index번째에 있는 VO_Sales의 칼로리를 N열로 출력
		private static void printCalIn_N_Row(VO_Sales[][] testArr, int N, int index) {
			int countOdd =0;

			System.out.print("┃");

			for (int j = 0; j < testArr[index].length; j++) {

				if (String.valueOf(testArr[index][j].getCalorie()).concat("(kcal)").length() % 2 == 1) {
					countOdd++;
				}

				for (int k = 0; k < (((int) (ROOF.length() - 2) / testArr[index].length)
						- String.valueOf(testArr[index][j].getCalorie()).concat("(kcal)").length()) / 2; k++) {
					System.out.print(" ");

				}
				System.out.print(String.valueOf(testArr[index][j].getCalorie()).concat("(kcal)"));
				for (int k = 0; k < (((int) (ROOF.length() - 2) / testArr[index].length)
						- String.valueOf(testArr[index][j].getCalorie()).concat("(kcal)").length()) / 2; k++) {
					System.out.print(" ");

				}
			}

			for(int i =0; i < countOdd; i++) {
				System.out.print(" ");
			}
			System.out.print("┃");
		}
		
		
		// 2차원 배열의 index번째에 있는 VO_Sales의 가격을 N열로 출력
		private static void printPriceIn_N_Row(VO_Sales[][] testArr, int N, int index) {
			int countOdd =0;

			System.out.print("┃");
			for (int j = 0; j < testArr[index].length; j++) {

				if (String.valueOf(testArr[index][j].getPrice()).concat(" won").length() % 2 == 1) {
					countOdd ++;
				}

				for (int k = 0; k < (((int) (ROOF.length() - 2) / testArr[index].length)
						- String.valueOf(testArr[index][j].getPrice()).concat(" won").length()) / 2; k++) {
					System.out.print(" ");

				}
				
				System.out.print(String.valueOf(testArr[index][j].getPrice()).concat(" won"));
				
				for (int k = 0; k < (((int) (ROOF.length() - 2) / testArr[index].length)
						- String.valueOf(testArr[index][j].getPrice()).concat(" won").length()) / 2; k++) {
					System.out.print(" ");

				}
			}

			for(int i =0; i < countOdd; i++) {
				System.out.print(" ");
			}
			System.out.print("┃");

		}

		// 문자(열) 중앙 정렬
		public static void centerAlign(String string) {
			boolean plusEmpty = false;
			if (string.length() % 2 == 1) {
				plusEmpty = true;
			}

			System.out.print("┃");

			for (int j = 0; j < (((int) (ROOF.length() - 2)) - string.length()) / 2; j++) {
				System.out.print(" ");

			}

			System.out.print(string);

			for (int j = 0; j < (((int) (ROOF.length() - 2)) - string.length()) / 2; j++) {
				System.out.print(" ");

			}

			if (plusEmpty) {
				System.out.print(" ");
			}
			System.out.print("┃");
			System.out.println();

		}

		// 문자(열) 왼쪽 정렬
		public static void leftAlign(String string) {
			boolean plusEmpty = false;

			System.out.print("┃");
			System.out.print(string);
			if (string.length() % 2 == 1) {
				plusEmpty = true;
			}

			for (int j = 0; j < (((int) (ROOF.length() - 2)) - string.length()); j++) {
				System.out.print(" ");

			}
			if (!plusEmpty) {
				System.out.print(" ");
			}
			System.out.print("┃");
			System.out.println();
		}

		
		// 텍스트 정렬을 위해 리스트를 2차원 배열로 전환 (1차원 배열 >> 2차원 배열)
		private static VO_Sales[][] make2DArrayFromList(List<VO_Sales> list, int n) {

			VO_Sales[][] arr = new VO_Sales[(int) Math.ceil((double) (list.size()) / n)][];
			int arrCnt1 = 0;

			for (int i = 0; i < list.size(); i += n) {
				
				if (arrCnt1 != arr.length - 1) {
					arr[arrCnt1] = new VO_Sales[n];
				} else {
					arr[arrCnt1] = new VO_Sales[((list.size()-1) % n) + 1];
				}

			

				for (int j = i; j < i + n; j++) {
				
					if (j < list.size()) {

						arr[arrCnt1][j % n] = list.get(j);
					}
				}

				arrCnt1++;
			}
			// System.out.println(arr.length);
			

			return arr;
		}
}

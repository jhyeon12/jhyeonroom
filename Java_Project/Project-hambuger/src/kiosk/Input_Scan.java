package kiosk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input_Scan {

	// 사용자의 입력을 담당하는 Input_Scan 클래스

		private BufferedReader in;
		private Input_BadInput exception;

		public Input_Scan() {
			in = new BufferedReader(new InputStreamReader(System.in));
			exception = new Input_BadInput();
		}

		public String getInfo() {
			String result = null;
			while (true) {

				try {
					result = exception.chkInfo(in.readLine());
					break;

				} catch (Input_BadInput bad) {

				} catch (IOException e) {
					System.err.println("일시적인 오류입니다.");

				}
			}
			return result;
		}

		public int getChoice(int listSize) {
			String choice = null;
			while (true) {
				try {
					choice = exception.chkMenuNum(in.readLine(), listSize);
					break;
				} catch (Input_BadInput bad) {

				} catch (IOException e) {
					System.err.println("일시적인 오류입니다.");
				}
			}
			return Integer.parseInt(choice);
		}

		public int screenChoice1(int listSize) {
			int choice = 0;
			while (true) {
				try {
					choice = in.read() - 48;
					// System.out.println(choice);
					choice = exception.isVailedSelectOne(choice, listSize);
					in.readLine();
					break;
				} catch (Input_BadInput bad) {
					// debug
					System.out.println("오류발생");
					try {
						in.readLine();
					} catch (IOException e) {

					}

				} catch (IOException e1) {
					System.err.println("일시적인 오류입니다.");
				}
			}
			return choice;
		}

		public int screenChoice2(int listSize) {
			int choice = 0;
			while (true) {
				try {
					choice = in.read() - 48;
					// System.out.println(choice);
					choice = exception.isVailedSelectOne2(choice, listSize);
					in.readLine();
					break;
				} catch (Input_BadInput bad) {

					try {
						in.readLine();
					} catch (IOException e) {

					}

				} catch (IOException e1) {
					System.err.println("일시적인 오류입니다.");
				}
			}
			return choice;
		}

		public int screenChoice3(int listSize) {
			int choice = 0;
			while (true) {
				try {
					choice = in.read() - 48;
					// System.out.println(choice);
					choice = exception.isVailedSelectOne3(choice, listSize);
					in.readLine();
					break;
				} catch (Input_BadInput bad) {
					// debug
					System.out.println("오류발생");
					try {
						in.readLine();
					} catch (IOException e) {

					}

				} catch (IOException e1) {
					System.err.println("일시적인 오류입니다.");
				}
			}
			return choice;
		}

		public int screenChoice4() {
			int choice = 0;
			while (true) {
				try {
					choice = in.read() - 48;
					// System.out.println(choice);
					choice = exception.isVailedSelectOne4(choice);
					in.readLine();
					break;
				} catch (Input_BadInput bad) {
					// debug
					System.out.println("오류발생");
					try {
						in.readLine();
					} catch (IOException e) {

					}

				} catch (IOException e1) {
					System.err.println("일시적인 오류입니다.");
				}
			}
			return choice;
		}

		public int lastCheck() {
			int choice = 0;
			while (true) {

				try {
					choice = in.read() - 48;
					choice = exception.lastCheck(choice);
					in.readLine();
					break;
				} catch (Input_BadInput bad) {
					System.out.println("오류발생");
					try {
						in.readLine();
					} catch (IOException e) {

					}

				} catch (IOException e1) {
					System.err.println("일시적인 오류입니다.");
				}
			}
			return choice;
		}
}

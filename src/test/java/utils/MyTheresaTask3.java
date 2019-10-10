package utils;

//commented lines are for near future.

public class MyTheresaTask3 {
	public static final String OUTPUT_TEXT1 = "my";
	public static final String OUTPUT_TEXT2 = "theresa";
	//public static final String OUTPUT_TEXT3 = "clothes";

	public static void main(String[] args) {
		int input = 15;
		checkResponse(input);
	}

	public static void checkResponse(int input) {

		boolean mod3 = input % 3 == 0;
		boolean mod5 = input % 5 == 0;
		//boolean mod7 = input % 7 == 0;

		if (mod3 && mod5) {
			System.out.println(OUTPUT_TEXT1 + OUTPUT_TEXT2);
		} /*else if (mod3 && mod7) {
			System.out.println(OUTPUT_TEXT1 + OUTPUT_TEXT3);
		}*/ else if (mod3 && !mod5) {
			System.out.println(OUTPUT_TEXT1);
		} else if (mod5 && !mod3) {
			System.out.println(OUTPUT_TEXT2);
		} else {
			System.out.println(input);
		}

	}
}

/**
 * @author Lazarov94
 *
 */

public class Ex1 {

	
	// I don't use this method, cause in this particular Ex it is working slower 
	//cause I rotate the String on every iteration with i steps, that causes me : numberOfIterations*O(n) 
	public static void rotate(char[] arr, int order) {
		order = order % arr.length;

		if (arr == null || order < 0) {
			throw new IllegalArgumentException("Illegal argument!");
		}

		
		int a = arr.length - order;

		reverse(arr, 0, a - 1);
		reverse(arr, a, arr.length - 1);
		reverse(arr, 0, arr.length - 1);

	}
	// Part of rotate method. Not used.
	public static void reverse(char[] arr, int left, int right) {
		if (arr == null || arr.length == 1)
			return;

		while (left < right) {
			char temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
	}
	// USED 
	private static boolean isPalindrome(String input) {
		char[] a = input.toCharArray();
		int counter = 0;
		int b = a.length / 2;
		for (int i = 0; i < a.length / 2; i++) {
			if (a[i] == a[a.length - 1 - i]) {
				counter++;
			} else {
				return false;
			}
		}
		return counter == b;
	}
	// USED 
	private static void rotate2(char[] a){
		
		char first = a[0];
		for(int i = 0 ; i < a.length-1 ; i++){
			a[i]= a[i+1];
		}
		a[a.length-1] = first;
	}

	private static void allPalindromes(String input) {
		int none = 0;
		char[] a = input.toCharArray(); // if we go for rotate(a, i), than commend that lane
		for (int i = 0; i < input.length(); i++) {
			 // //cause i want every time to rotate form  the original IF I USE rotate  
			 // char[] a = input.toCharArray();
			 //	rotate(a, i);
			if (isPalindrome(new String(a))) {
				System.out.println(i + "->" +new String(a));
				none++;
			}
		rotate2(a);
		}
		if(none == 0){
			System.out.println("NONE");
		}
	
	}

	public static void main(String[] args) {
		String a = "shakira";
		//long start = System.currentTimeMillis();
		//for(int i = 0 ; i < 1_000_000; i++){
		allPalindromes(a);
		//}
		//System.out.println(System.currentTimeMillis() - start);
		
	}
}

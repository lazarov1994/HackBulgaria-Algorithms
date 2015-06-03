import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Lazarov94
 *
 */
public class Ex3 {
	HashMap<Character, Integer> brackets = new HashMap<Character, Integer>();

	Ex3() {
		brackets.put('(', 1);
		brackets.put(')', -1);
		brackets.put('[', 2);
		brackets.put(']', -2);
		brackets.put('{', 3);
		brackets.put('}', -3);
	}

	private boolean isBracket(char input) {
		return brackets.containsKey(input);
	}

	public boolean isValidExpression(String input) {
		char[] toCharArray = input.toCharArray();
		if (!isBracket(toCharArray[0])
				|| !isBracket(toCharArray[toCharArray.length - 1])) { // It can't start or end with something different than bracket
			return false;
		}
		if (brackets.get(toCharArray[0]) < 0
				|| brackets.get(toCharArray[0])
						+ brackets.get(toCharArray[toCharArray.length - 1]) != 0) { // It must start and end with the same type of brackets and can't begging with closing 
																			
			return false;
		}
	//	char currentBracket = ' ';
		ArrayList<Integer> openingBrackets = new ArrayList<Integer>();
	//	ArrayList<Integer> closingBrackets = new ArrayList<Integer>();
		openingBrackets.add(brackets.get(toCharArray[0]));

		
		for (int i = 1; i < toCharArray.length; i++) {
			if (isBracket(toCharArray[i]) && brackets.get(toCharArray[i]) > 0) {
				if (openingBrackets.get(openingBrackets.size() - 1)	- brackets.get(toCharArray[i]) == 1) { // includes the  case that () cannot be directly contained in {} and (())
					openingBrackets.add(brackets.get(toCharArray[i]));
				} else {
					return false;
				}
			} else if (isBracket(toCharArray[i]) && brackets.get(toCharArray[i]) < 0) { // If it is a closing bracket.
				if (openingBrackets.get(openingBrackets.size() - 1) + brackets.get(toCharArray[i]) == 0) {
					openingBrackets.remove(openingBrackets.size() - 1);
				} else {
					return false;
				}
			} else { // If it is a number(something different than bracket) just skip it.
				continue; 
			}
			if (openingBrackets.isEmpty() && i == toCharArray.length - 1) {
				return true;
			} else if (openingBrackets.isEmpty() && i != toCharArray.length - 1) {
				return false;
			}

		}
		return false;
	}

	public void calculate(String input) {
		if (isValidExpression(input)) {
			char[] toCharArray = input.toCharArray();
			int sum = 0;
			int integer = 0;
			int decimals = 1;
			int rB = 0;
			int lB = 0;
			for (int i = toCharArray.length - 2; i >= 0; i--) {
				if (Character.isDigit(toCharArray[i])) {
					integer = integer
							+ Character.getNumericValue(toCharArray[i])
							* decimals;
					decimals = decimals * 10;
				} else if (isBracket(toCharArray[i])) {
					if (rB - lB == 0) {
						sum = sum + integer;
						integer = 0;
						decimals = 1;
					} else if (rB - lB == 1) {
						sum = sum + 2 * integer;
						integer = 0;
						decimals = 1;
					} else if (rB - lB == 2) {
						sum = sum + 4 * integer;
						integer = 0;
						decimals = 1;
					}
					if (brackets.get(toCharArray[i]) < 0) {
						rB++;
					} else {
						lB++;
					}
				}
			}
			System.out.println(sum);
		} else {
			System.out.println("NO");
		}

	}

	public static void main(String[] args) {
		Ex3 a = new Ex3();
		long start = System.nanoTime();
		a.calculate("{123[123(123)123(123)]23[123]2}");
		System.out.println(System.nanoTime() - start);
	}

}

public class BSTclint {
	public static void main(String[] args) {
		RedBlack<Character, Integer> bst = new RedBlack<Character, Integer>();
		char c[] = {'S','E','A','R','H','M','C','X'};
		for(int i = 0; i < 8; i++) {
			char key = c[i];
			int value = i;
			bst.put(key, value);
		}
		System.out.println("Height of tee is :" +bst.treeHi());
		for(char i : bst.keys()) System.out.println("Keys is : "+ i + " Value is : " + bst.get(i));
	}
}
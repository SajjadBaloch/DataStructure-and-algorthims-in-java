import java.util.LinkedList;
public class RedBlack<Key extends Comparable<Key>, Value> {
	private Node root;
	private static final boolean Red = true;
	private static final boolean Black = false;
	private class Node {
		private Key key;
		private Value value;
		private Node left, right;
		private int count;
		private boolean color;
		Node(Key key, Value value, boolean color) {
			this.key = key;
			this.value = value;
			this.color = color;
		}
	}
	public RedBlack() {
		root = null;
	}
	public void put(Key key, Value value) {
		root = put(key, value, root);
	}
	private Node put(Key key, Value value, Node x) {
		if(x == null) return new Node(key, value, Red);
		int cmp = key.compareTo(x.key);
		if(cmp < 0) x.left = put(key, value, x.left);
		else if( cmp > 0) x.right = put(key, value, x.right);
		else x.value = value;
		
		if(!isRed(x.left) && isRed(x.right)) x = rotateLeft(x);
		if(isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
		if(isRed(x.left) && isRed(x.right)) x = flipColor(x);
		return x;
	}
	private Node rotateLeft(Node x) {
		Node h = x.right;
		x.right = h.left;
		h.left = x;
		h.color = x.color;
		x.color = Red;
		return h;
	}
	private Node rotateRight(Node x) {
		Node h = x.left;
		x.left = h.right;
		h.right = x;
		h.color = x.color;
		h.color = Red;
		return h;
	}
	private Node flipColor(Node x) {
		x.color = Red;
		x.left.color = Black;
		x.right.color = Black;
		return x;
	}
	public Value get(Key key) {
		return get(key, root);
	}
	private Value get(Key key, Node x) {
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) return get(key, x.left);
		else if(cmp > 0) return get(key, x.right);
		else return x.value;
	}
	public boolean isRed(Node x) {
		if(x == null) return false;
		else return x.color == Red;
	}
	public int treeHi() {
		return height(root, 0);
	}
	private int height(Node x, int c) {
		if(x == null) return c++;
		int a = c + height(x.left, c);
		int b = c + height(x.right, c);
		if(a > b) { c = a; return c; }
		else { c = b; return c; }
	}
	public Iterable<Key> keys() {
		LinkedList<Key> ll = new LinkedList<Key>();
		inorder(ll,root);
		return ll;
	}
	private void inorder(LinkedList<Key> ll, Node x) {
		if(x == null) return;
		inorder(ll, x.left);
		ll.add(x.key);
		inorder(ll, x.right);
	}
}
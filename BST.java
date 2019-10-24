import java.util.*;
public class BST<Key extends Comparable<Key>, Value> {
	private Node root;
	private class Node {
		private Key key;
		private Value value;
		private Node left, right;
		private int count;
		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
			count = 0;
		}
	}
	public BST(){
		root = null;
	}
	public void put(Key key, Value value) {
		root = put(root, key, value);
	}
	private Node put(Node x, Key key, Value value) {
		if(x == null) return new Node(key, value);
		int cmp = key.compareTo(x.key);
		if(cmp < 0) x.left = put(x.left, key, value);
		else if(cmp > 0) x.right = put(x.right, key, value);
		else x.value = value;
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}
	public Value get(Key key) {
		Node x = root;
		while(x != null) {
			int cmp = key.compareTo(x.key);
			if(cmp < 0) x = x.left;
			else if(cmp > 0) x = x.right;
			else return x.value;
		}
		return null;
	}
	public boolean contains(Key key) {
		return get(key) != null;
	}
	public int size() {
		return size(root);
	}
	private int size(Node x) {
		if(x == null) return 0;
		return x.count;
	}
	public Key min() {
		Node x = min(root);
		if(x == null) return null;
		return x.key;
	}
	private Node min(Node x) {
		while(x != null) {
			if(x.left != null) x = x.left;
			else return x;
		}
		return null;
	}
	public Key max() {
		Node x = root;
		while(x != null) {
			if(x.right != null) x = x.right;
			else return x.key;
		}
		return null;
	}
	public Key floor(Key key) {
		Node x = floor(root, key);
		if(x == null) return null;
		else return x.key;
	}
	private Node floor(Node x, Key key) {
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0) return x;
		else if(cmp < 0) return floor(x.left, key);
		Node t = floor(x.right, key);
		if(t != null) return t;
		return x;
	}
	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if(x != null) return null;
		return x.key;
	}
	private Node ceiling(Node x, Key key) {
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0) return x;
		else if(cmp > 0) return floor(x.right, key);
		Node t = floor(x.left, key);
		if(t != null) return t;
		return x;
	}
	public int rank(Key key) {
		return rank(root, key);
	}
	private int rank(Node x, Key key) {
		if(x == null) return 0;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) return rank(x.left, key);
		if(cmp > 0) return (1 + size(x.left) + rank(x.right, key));
		else return size(x.left);
	}
	public Key select(int r) {
		Node x = root;
		while(x != null) {
			int cmp = rank(x.key);
			if(cmp > r) x = x.left;
			else if(cmp < r) x = x.right;
			return x.key;
		}
		return null;
	}
	public void deleteMin() {
		delete(min());
	}
	public void delete(Key key) {
		root = delete(root, key) ;
	}
	private Node delete(Node x, Key key) {
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) x.left = delete(x.left, key);
		else if(cmp > 0) x.right = delete(x.right, key);
		else {
			if(x.left == null) return x.right;
			if(x.right == null) return x.left;
			 Node t = x;
			 x = min(x.right);
			 delete(min(x.right).key);
			 x.right = t.right;
			 x.left = t.left;
		}
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}
	public void deleteMax() {
		delete(max());
	}
	public int size(Key lo, Key hi) {
		int x = rank(lo) - rank(hi);
		if(x > 0) return x;
		return 0;
	}
	public Iterable<Key> keys() {
		LinkedList<Key> ll = new LinkedList<Key>();
		inorder(root, ll);
		return ll;
	}
	private void inorder(Node x, LinkedList<Key> ll) {
		if(x == null) return;
		inorder(x.left, ll);
		ll.add(x.key);
		inorder(x.right,ll);
	}
	public Iterable<Key> keys(Key lo, Key hi) {
		LinkedList<Key> ll = new LinkedList<Key>();
		if(!contains(lo) || !contains(hi)) return null;
		middle(root, lo, hi, ll);
		return ll;
	}
	private void middle(Node x, Key lo, Key hi, LinkedList<Key> ll) {
		if(x == null) return;
		int cmp = hi.compareTo(x.key);
		if(cmp < 0) middle(x.left, lo, hi, ll);
		cmp = lo.compareTo(x.key);
		if(cmp > 0) middle(x.right, lo, hi, ll);
		inorder(x, ll);
	}
}
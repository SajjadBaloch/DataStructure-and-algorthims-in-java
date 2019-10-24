@SuppressWarnings("unchecked")
public class MinPq<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N;
	public MinPq() {
		pq = (Key[]) new Comparable[10];
		N = 0;
	}
	public MinPq(Key[] pq) {
		this.pq = (Key[]) new Comparable[pq.length];
		N = pq.length;
		for(int i = 1; i <= N; i++) this.pq[i] = pq[i];
	}
	public boolean isEmpty() { return N == 0; }
	public int size() { return N; }
	public void insert(Key key) {
		pq[++N] = key;
		swim(N);
		if(N == pq.length - 1) resize(pq.length*2);
	}
	public Key DelMin() {
		Key min = pq[1];
		exch(1, N--);
		sink(1);
		pq[N+1] = null;
		if(N == pq.length/4) resize(pq.length/2);
		return min;
	}
	private void swim(int j) {
		while(j > 1 && less(j, j/2)) {
			exch(j, j/2);
			j = j/2;
		}
	}
	private void sink(int j) {
		while(2*j <= N) {
			int k = 2*j;
			if(k < N && less(k+1, k)) k++;
			if(less(j, k)) break;
			exch(j, k);
			j = k;
		}
	}
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	private void exch(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	private void resize(int n) {
		Key[] temp = (Key[]) new Comparable[n];
		for(int i = 1; i <= N; i++) temp[i] = pq[i];
		pq = temp;
	}
}
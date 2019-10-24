@SuppressWarnings("unchecked")
public class MaxPq<key extends Comparable<key>> {
	private key[] pq; // Immutable Priority Queue Data type.
	private int n; // Size of PQ
	MaxPq() {
		pq =  (key[]) new Comparable[10];
		n = 0;
	}
	MaxPq(key[] pq) {
		this.pq =  (key[]) new Comparable[pq.length+1];
		n = pq.length;
		for(int i = 1; i <= n; i++) this.pq[i] = pq[i];
	}
	public boolean isEmpty() { return n == 0; } // Returns true if PQ is empty.
	public int size() { return n; } // Returns the size of PQ.
	public void Insert(key k) {
		pq[++n] = k;
		swim(n);
		if(n == pq.length - 1) resize(pq.length*2);
	}
	private void swim(int j) {
		while(j > 1 && less(j/2 , j)) {
			exch(j/2, j);
			j = j/2;
		}
	}
	public key DelMax() {
		key max = pq[1];
		exch(1, n--);
		sink(1);
		pq[n+1] = null;
		if(n == pq.length/4) resize(pq.length/2);
		return max;
	}
	private void sink(int j) {
		while(2*j <= n) {
			int k = 2*j;
			if(k < n  && less(k, k+1)) k++;
			if(!less(j, k)) break;
			exch(j, k);
			j = k;
		}
	}
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	private void exch(int i, int j) {
		key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	private void resize(int k) {
		key[] temp = (key[]) new Comparable[k];
		for(int i = 1; i <= n; i++) temp[i] = pq[i];
		pq = temp;
	}
}
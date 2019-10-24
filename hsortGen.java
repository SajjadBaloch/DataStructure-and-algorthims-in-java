public class hsortGen {
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>> boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}
	
	private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
		T swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	public  static <T extends Comparable<T>> T[] sort(T[] a) {
		int h = 1;
		int N = a.length;
		while(h < N/3) h = h*3 + 1;
		while(h >= 1) {
			for(int i = h; i < N; i++) {
				for(int j = i; j >=h && less(a[j], a[j-h]); j-=h) exch(a, j, j-h);
			}
			h = h/3;
		}
		return a;
	}
}
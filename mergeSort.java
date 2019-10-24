@SuppressWarnings("unchecked")
public class mergeSort {
	private static <T extends Comparable<T>> boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}
	
	private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
		for(int k = lo;k <= hi; k++) aux[k] = a[k];
		int i=lo, j= mid+1;
		for(int k = lo; k <= hi; k++) {
			if(i > mid) a[k] = aux[j++];
			else if(j > hi) a[k] = aux[i++];
			else if(less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}
	
	private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
		if(lo >= hi) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		merge(a, aux, lo, mid, hi);
	}
	
	public static <T extends Comparable<T>> T[] sort(T[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length-1);
		return a;
	}
}
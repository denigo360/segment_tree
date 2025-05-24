package segtrees;
import java.util.Arrays;
public class NaiveArray<T, U> {
    private final T[] a;
    private final Combiner<T> combiner;
    private final Updater<T, U> updater;

    public NaiveArray(T[] array, Combiner<T> combiner, Updater<T, U> updater) {
        this.a = Arrays.copyOf(array, array.length);
        this.combiner = combiner;
        this.updater = updater;
    }

    public void update(int l, int r, U update) {
        for (int i = l; i <= r; i++) {
            a[i] = updater.apply(a[i], update, 1);
        }
    }

    public T query(int l, int r) {
        T result = a[l];
        for (int i = l + 1; i <= r; i++) {
            result = combiner.combine(result, a[i]);
        }
        return result;
    }
}
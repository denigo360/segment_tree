package segtrees;

public interface Updater<T, U>
{
    T apply(T current, U update, int segmentLength);
    U compose(U newer, U older);
    U identity();
}
packege segtrees; 

public interface Updater<T,U> 
{
    T apply(T current,U update, int segmentLenght);
    U compose(U newer, U older);
    U identity();
}

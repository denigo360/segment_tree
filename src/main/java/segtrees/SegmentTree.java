package segtrees; 
import java.utils.Arrays; 

public class SegmentTree<T,U> 
{
    private final T[] tree; 
    private final U[] lazy; 
    private final Combiner<T> combiner; 
    private final Updater<T,U> updater;
    private final int n; 

    @SupressWarnings("uncheked")
    public SegmentTree(T[] array,Combiner<T> combiner,Updater<T,U> updater)
    {
        this.n = array.length; 
        this.combiner = combiner; 
        this.updater = updater; 
        this.tree = (T[]) new Object[4*n];
        this.lazy = (U[]) new Object[4*n];
        build(0,0,n-1,array)
    }
    private void build(int node,int start,int end,T[] array)
    {
        if (start == end) {
            tree[node] = array[start];
            return;
        }
        int mid = (start+end) / 2; 
        int leftNode = 2*node+1;
        int rightNode = 2*node+2;
        build(leftNode,start,mid,array);
        build(rightNode,mid+1,end,array);
        tree[node] = combiner.combine(tree[leftNode],tree[rightNode]);
    
    }
    private void apply(int node,int start,int end,U update)
    {
        if (update == null) return; 
        tree[node] = updater.apply(tree[node],update,end-start+1);
        if (start!=end){
            lazy[node] = lazy[node] == null ? update : updater.compose(update,lazy[node]);  
        }
    }
    private void push(int node,int start,int end)
    {
        if (lazy[node] == null) return; 
        int mid = (start+end) / 2; 
        apply(2*node+1,start,mid,lazy[node])
        apply(2*node+2,mid+1,end,lazy[node])
        lazy[node] = null;
    }
    public void update(int l,int r,U update)
    {
        update(0,0,n-1,l,r,update);
    }
    private void update(int node,int start,int end,int l,int r,U update)
    {
        if (l>end || r < start) return; 
        if ( l<= start && end<=r)
        {
            apply(node,start,end,update);
            return;
        }
        push(node,start,end);
        int mid = (start+end)/2; 
        update(2+node+1,start,mid,l,r,update);
        update(2*node+2,mid+1,end,l,r,update);
        tree[node] = combiner.combine(tree[2 * node + 1], tree[2 * node + 2]);
    }
    public T query(int node,int start,int end,int l,int r)
    {
        if (l > end || r < start) return; 
        if (l<=start && end<=r) return tree[node];
        push(node,start,end);
        int mid = (start+end) / 2; 
        T left = query(2 * node + 1, start, mid, l, r);
        T right = query(2 * node + 2, mid + 1, end, l, r);
        return combiner.combine(left,right);
    }
}
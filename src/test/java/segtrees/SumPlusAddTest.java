package segtrees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class SumPlusAddTest
{
    @Test 
    void testSmallFixedScenario() 
    {
        Long[] a = {4L, 2L, 3L, 4L, 5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, SumAddOperations.sumLongs(), SumAddOperations.addLongs());
        st.update(0, 2, 3L);
        assertEquals(15L, st.query(1, 3)); 
        st.update(2, 4, 2L);
        assertEquals(20L, st.query(0, 2)); 
        st.update(1, 2, 1L);
        assertEquals(15L, st.query(1, 2)); 
        assertEquals(7L, st.query(0, 0)); 
        assertEquals(35L, st.query(0, 4));

    }
    @Test
    void testSingleElement() {
        Long[] a = {5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, SumAddOperations.sumLongs(), SumAddOperations.addLongs());
        assertEquals(5L, st.query(0, 0));
        st.update(0, 0, 3L);
        assertEquals(8L, st.query(0, 0));
    }
    @Test
    void testFullRange() {
        Long[] a = {1L, 2L, 3L, 4L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, SumAddOperations.sumLongs(), SumAddOperations.addLongs());
        assertEquals(10L, st.query(0, 3)); 
        st.update(0, 3, 2L);
        assertEquals(18L, st.query(0, 3)); 
    }
    @Test
    void testConsecutiveUpdates() {
        Long[] a = {1L, 2L, 3L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, SumAddOperations.sumLongs(), SumAddOperations.addLongs());
        st.update(0, 2, 1L); 
        st.update(0, 2, 2L); 
        assertEquals(15L, st.query(0, 2)); 
    }
    @Test
    void testRandomOperations() 
    {
        Random rnd = new Random(12345);
        int n = rnd.nextInt(1000) + 1;
        Long[] a = new Long[n];
        for (int i = 0; i < n; i++) a[i] = (long) rnd.nextInt(1000);
        SegmentTree<Long, Long> st = new SegmentTree<>(a, SumAddOperations.sumLongs(), SumAddOperations.addLongs());
        NaiveArray<Long, Long> naive = new NaiveArray<>(a, SumAddOperations.sumLongs(), SumAddOperations.addLongs());
        
        for (int i = 0; i < 5; i++) 
        {
            int l = rnd.nextInt(n);
            int r = rnd.nextInt(n - l) + l;
            if (rnd.nextBoolean())
             {
                long update = rnd.nextInt(100);
                st.update(l, r, update);
                naive.update(l, r, update);
            } else
             {
                assertEquals(naive.query(l, r), st.query(l, r));
            }
        }
    }
    
}
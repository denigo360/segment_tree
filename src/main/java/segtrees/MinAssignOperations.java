package segtrees; 
public class MinAssignOperations 
{
    public static Combiner<Long> minLongs() 
    {
        return new Combiner<>()
        {
            @Override
            public Long combine(Long left,Long right)
            {
                return Math.min(left,right);
            }
            @Override
            public Long identity() 
            {
                return Long.MAX_VALUE;
            }
        };
    }
    public static Updater<Long,Long> assignLongs() 
    {
        return new Updater<>() 
        {
            @Override
            public Long apply(Long current,Long update,int segmentLength)
            {
                return update;
            }
            @Override
            public Long compose(Long newer,Long older)
            {
                return newer;
            }
            @Override
            public Long identity() 
            {
                return null; 
            }
        };
    }
}
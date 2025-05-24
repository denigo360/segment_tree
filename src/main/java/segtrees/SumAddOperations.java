package segtrees;

public class SumAddOperations {
    public static Combiner<Long> sumLongs() {
        return new Combiner<>() {
            @Override
            public Long combine(Long left, Long right) {
                return (left == null ? 0L : left) + (right == null ? 0L : right);
            }

            @Override
            public Long identity() {
                return 0L;
            }
        };
    }

    public static Updater<Long, Long> addLongs() {
        return new Updater<>() {
            @Override
            public Long apply(Long current, Long update, int segmentLength) {
                return (current == null ? 0L : current) + update * segmentLength;
            }

            @Override
            public Long compose(Long newer, Long older) {
                return newer + older;
            }

            @Override
            public Long identity() {
                return 0L;
            }
        };
    }
}
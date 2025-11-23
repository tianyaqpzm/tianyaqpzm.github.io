
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class Solution338Test {
    @Test
    void test_count_1() {
        Solution338 obj = new Solution338();
        int[] res = obj.countBits(2);
        Assertions.assertArrayEquals(new int[] { 0, 1, 1 }, res);
    }

    @Test
    void test_zero() {
        Solution338 obj = new Solution338();
        int[] res = obj.countBits(0);
        Assertions.assertArrayEquals(new int[] { 0 }, res);
    }

    @Test
    void test_example_5() {
        Solution338 obj = new Solution338();
        int[] res = obj.countBits(5);
        Assertions.assertArrayEquals(new int[] { 0, 1, 1, 2, 1, 2 }, res);
    }

    @Test
    void test_general_range_against_bitcount() {
        Solution338 obj = new Solution338();
        for (int n = 0; n <= 20; n++) {
            int[] expected = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                expected[i] = Integer.bitCount(i);
            }
            int[] actual = obj.countBits(n);
            Assertions.assertArrayEquals(expected, actual, "Mismatch at n=" + n);
        }
    }
}

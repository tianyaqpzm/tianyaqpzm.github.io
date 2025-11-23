import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class Solution461Test {

    @Test
    void testExample1() {
        Solution461 obj = new Solution461();
        int result = obj.hammingDistance(1, 4);
        Assertions.assertEquals(2, result);
    }

    @Test
    void testSameNumbers() {
        Solution461 obj = new Solution461();
        int result = obj.hammingDistance(5, 5);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testZeroAndOne() {
        Solution461 obj = new Solution461();
        int result = obj.hammingDistance(0, 1);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testLargeNumbers() {
        Solution461 obj = new Solution461();
        int result = obj.hammingDistance(15, 0); // 1111 vs 0000
        Assertions.assertEquals(4, result);
    }

    @Test
    void testDifferentLengths() {
        Solution461 obj = new Solution461();
        int result = obj.hammingDistance(1, 8); // 1 vs 1000
        Assertions.assertEquals(2, result);
    }

    @Test
    void testZeroAndZero() {
        Solution461 obj = new Solution461();
        int result = obj.hammingDistance(0, 0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testPowerOfTwo() {
        Solution461 obj = new Solution461();
        int result = obj.hammingDistance(2, 4); // 10 vs 100
        Assertions.assertEquals(2, result);
    }

    @Test
    void testAgainstBitCount() {
        Solution461 obj = new Solution461();
        // Test against Integer.bitCount(x ^ y) for verification
        for (int x = 0; x <= 10; x++) {
            for (int y = 0; y <= 10; y++) {
                int expected = Integer.bitCount(x ^ y);
                int actual = obj.hammingDistance(x, y);
                Assertions.assertEquals(expected, actual,
                        "Mismatch for x=" + x + ", y=" + y);
            }
        }
    }
}
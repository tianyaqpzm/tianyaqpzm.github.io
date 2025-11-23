
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class Solution283Test {

    @Test
    void testTypicalCase() {
        Solution283 s = new Solution283();
        int[] nums = { 0, 1, 0, 3, 12 };
        s.moveZeroes(nums);
        Assertions.assertArrayEquals(new int[] { 1, 3, 12, 0, 0 }, nums);
    }

    @Test
    void testNoZero() {
        Solution283 s = new Solution283();
        int[] nums = { 1, 2, 3 };
        s.moveZeroes(nums);
        Assertions.assertArrayEquals(new int[] { 1, 2, 3 }, nums);
    }

    @Test
    void testAllZero() {
        Solution283 s = new Solution283();
        int[] nums = { 0, 0, 0 };
        s.moveZeroes(nums);
        Assertions.assertArrayEquals(new int[] { 0, 0, 0 }, nums);
    }

    @Test
    void testLeadingZeros() {
        Solution283 s = new Solution283();
        int[] nums = { 0, 0, 1, 2 };
        s.moveZeroes(nums);
        Assertions.assertArrayEquals(new int[] { 1, 2, 0, 0 }, nums);
    }

    @Test
    void testEmpty() {
        Solution283 s = new Solution283();
        int[] nums = {};
        s.moveZeroes(nums);
        Assertions.assertArrayEquals(new int[] {}, nums);
    }
}

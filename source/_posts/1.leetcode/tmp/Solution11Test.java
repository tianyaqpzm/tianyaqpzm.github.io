
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class Solution11Test {

    @Test
    void test() {
        Solution11 obj = new Solution11();
        int maxArea = obj.maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 });
        Assertions.assertEquals(49, maxArea);
    }
}

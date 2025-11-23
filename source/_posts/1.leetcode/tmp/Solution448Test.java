import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import java.util.Arrays;

public class Solution448Test {

    @Test
    void testTypicalCase() {
        Solution448 obj = new Solution448();
        int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1 };
        List<Integer> result = obj.findDisappearedNumbers(nums);
        List<Integer> expected = Arrays.asList(5, 6);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testAllNumbersPresent() {
        Solution448 obj = new Solution448();
        int[] nums = { 1, 2, 3, 4 };
        List<Integer> result = obj.findDisappearedNumbers(nums);
        List<Integer> expected = Arrays.asList();
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testSingleElement() {
        Solution448 obj = new Solution448();
        int[] nums = { 1 };
        List<Integer> result = obj.findDisappearedNumbers(nums);
        List<Integer> expected = Arrays.asList();
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testSingleElementMissing() {
        Solution448 obj = new Solution448();
        int[] nums = { 2 };
        List<Integer> result = obj.findDisappearedNumbers(nums);
        List<Integer> expected = Arrays.asList(1);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testMultipleMissing() {
        Solution448 obj = new Solution448();
        int[] nums = { 1, 1 };
        List<Integer> result = obj.findDisappearedNumbers(nums);
        List<Integer> expected = Arrays.asList(2);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testLargeArray() {
        Solution448 obj = new Solution448();
        int[] nums = { 1, 3, 5, 7, 9, 2, 4, 6, 8, 10 };
        List<Integer> result = obj.findDisappearedNumbers(nums);
        List<Integer> expected = Arrays.asList();
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testWithDuplicates() {
        Solution448 obj = new Solution448();
        int[] nums = { 1, 1, 1, 1 };
        List<Integer> result = obj.findDisappearedNumbers(nums);
        List<Integer> expected = Arrays.asList(2, 3, 4);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testEmptyArray() {
        Solution448 obj = new Solution448();
        int[] nums = {};
        List<Integer> result = obj.findDisappearedNumbers(nums);
        List<Integer> expected = Arrays.asList();
        Assertions.assertEquals(expected, result);
    }
}
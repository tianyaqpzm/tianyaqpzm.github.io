import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution17Test {

    @Test
    void testLetterCombinations() {
        Solution17 solu = new Solution17();
        Assertions.assertArrayEquals(solu.letterCombinations("23").toArray(),
                new String[] { "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf" });
        // List<String> list = Arrays.asList("a", "b", "c");
        // String[] array = list.stream().toArray(String[]::new);
    }

}

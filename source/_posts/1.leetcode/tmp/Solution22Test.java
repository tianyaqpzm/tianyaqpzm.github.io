import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution22Test {

    private Solution22 solution;

    @BeforeEach
    void setUp() {
        solution = new Solution22();
    }

    @Test
    void testGenerateParenthesisN1() {
        List<String> result = solution.generateParenthesis(1);
        List<String> expected = Arrays.asList("()");

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));

        // Verify all results are valid parentheses
        for (String s : result) {
            assertTrue(isValidParentheses(s), "Invalid parentheses: " + s);
        }
    }

    @Test
    void testGenerateParenthesisN2() {
        List<String> result = solution.generateParenthesis(2);
        List<String> expected = Arrays.asList("(())", "()()");

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));

        // Verify all results are valid parentheses
        for (String s : result) {
            assertTrue(isValidParentheses(s), "Invalid parentheses: " + s);
        }
    }

    @Test
    void testGenerateParenthesisN3() {
        List<String> result = solution.generateParenthesis(3);
        List<String> expected = Arrays.asList(
                "((()))", "(()())", "(())()", "()(())", "()()()");

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));

        // Verify all results are valid parentheses
        for (String s : result) {
            assertTrue(isValidParentheses(s), "Invalid parentheses: " + s);
        }
    }

    @Test
    void testGenerateParenthesisN0() {
        List<String> result = solution.generateParenthesis(0);
        List<String> expected = Arrays.asList("");

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    void testNoDuplicates() {
        List<String> result = solution.generateParenthesis(3);
        Set<String> uniqueResults = new HashSet<>(result);

        assertEquals(result.size(), uniqueResults.size(),
                "Result contains duplicates");
    }

    @Test
    void testCorrectLength() {
        for (int n = 1; n <= 4; n++) {
            List<String> result = solution.generateParenthesis(n);
            for (String s : result) {
                assertEquals(2 * n, s.length(),
                        "String length should be 2*n for n=" + n + ", but got: " + s);
            }
        }
    }

    @Test
    void testCorrectParenthesesCount() {
        for (int n = 1; n <= 4; n++) {
            List<String> result = solution.generateParenthesis(n);
            for (String s : result) {
                long openCount = s.chars().filter(ch -> ch == '(').count();
                long closeCount = s.chars().filter(ch -> ch == ')').count();

                assertEquals(n, openCount,
                        "Should have " + n + " opening parentheses in: " + s);
                assertEquals(n, closeCount,
                        "Should have " + n + " closing parentheses in: " + s);
            }
        }
    }

    @Test
    void testCatalanNumber() {
        // The number of valid parentheses combinations follows Catalan numbers
        int[] expectedCounts = { 1, 1, 2, 5, 14 }; // C(0) to C(4)

        for (int n = 0; n <= 4; n++) {
            List<String> result = solution.generateParenthesis(n);
            assertEquals(expectedCounts[n], result.size(),
                    "For n=" + n + ", expected " + expectedCounts[n] + " combinations");
        }
    }

    /**
     * Helper method to validate if a string has valid parentheses
     */
    private boolean isValidParentheses(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false; // More closing than opening at this point
                }
            }
        }
        return count == 0; // Should have equal opening and closing
    }
}
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class Solution160Test {

    @Test
    void testIntersectionAtMiddle() {
        // Create intersection node
        ListNode intersection = new ListNode(8);
        intersection.next = new ListNode(4);
        intersection.next.next = new ListNode(5);

        // Create list A: 4->1->8->4->5
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = intersection;

        // Create list B: 5->6->1->8->4->5
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = intersection;

        Solution160 obj = new Solution160();
        ListNode result = obj.getIntersectionNode(headA, headB);

        Assertions.assertEquals(intersection, result);
        Assertions.assertEquals(8, result.val);
    }

    @Test
    void testIntersectionAtEnd() {
        // Create intersection node
        ListNode intersection = new ListNode(2);
        intersection.next = new ListNode(4);

        // Create list A: 1->9->1->2->4
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(9);
        headA.next.next = new ListNode(1);
        headA.next.next.next = intersection;

        // Create list B: 3->2->4
        ListNode headB = new ListNode(3);
        headB.next = intersection;

        Solution160 obj = new Solution160();
        ListNode result = obj.getIntersectionNode(headA, headB);

        Assertions.assertEquals(intersection, result);
        Assertions.assertEquals(2, result.val);
    }

    @Test
    void testNoIntersection() {
        // Create list A: 2->6->4
        ListNode headA = new ListNode(2);
        headA.next = new ListNode(6);
        headA.next.next = new ListNode(4);

        // Create list B: 1->5
        ListNode headB = new ListNode(1);
        headB.next = new ListNode(5);

        Solution160 obj = new Solution160();
        ListNode result = obj.getIntersectionNode(headA, headB);

        Assertions.assertNull(result);
    }

    @Test
    void testSameList() {
        // Create list: 1->2->3
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        Solution160 obj = new Solution160();
        ListNode result = obj.getIntersectionNode(head, head);

        Assertions.assertEquals(head, result);
        Assertions.assertEquals(1, result.val);
    }

    @Test
    void testOneEmptyList() {
        // Create list A: 1->2->3
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);

        // List B is null
        ListNode headB = null;

        Solution160 obj = new Solution160();
        ListNode result = obj.getIntersectionNode(headA, headB);

        Assertions.assertNull(result);
    }

    @Test
    void testBothEmptyLists() {
        Solution160 obj = new Solution160();
        ListNode result = obj.getIntersectionNode(null, null);

        Assertions.assertNull(result);
    }

    @Test
    void testIntersectionAtFirstNode() {
        // Create intersection node
        ListNode intersection = new ListNode(1);
        intersection.next = new ListNode(2);
        intersection.next.next = new ListNode(3);

        // Both lists start with the same node
        ListNode headA = intersection;
        ListNode headB = intersection;

        Solution160 obj = new Solution160();
        ListNode result = obj.getIntersectionNode(headA, headB);

        Assertions.assertEquals(intersection, result);
        Assertions.assertEquals(1, result.val);
    }

    @Test
    void testDifferentLengthsWithIntersection() {
        // Create intersection node
        ListNode intersection = new ListNode(7);
        intersection.next = new ListNode(8);

        // Create list A: 1->2->3->7->8 (longer)
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);
        headA.next.next.next = intersection;

        // Create list B: 4->5->7->8 (shorter)
        ListNode headB = new ListNode(4);
        headB.next = new ListNode(5);
        headB.next.next = intersection;

        Solution160 obj = new Solution160();
        ListNode result = obj.getIntersectionNode(headA, headB);

        Assertions.assertEquals(intersection, result);
        Assertions.assertEquals(7, result.val);
    }

    @Test
    void testSingleNodeLists() {
        // Create single node
        ListNode node = new ListNode(1);

        Solution160 obj = new Solution160();
        ListNode result = obj.getIntersectionNode(node, node);

        Assertions.assertEquals(node, result);
        Assertions.assertEquals(1, result.val);
    }

    @Test
    void testSingleNodeNoIntersection() {
        ListNode nodeA = new ListNode(1);
        ListNode nodeB = new ListNode(2);

        Solution160 obj = new Solution160();
        ListNode result = obj.getIntersectionNode(nodeA, nodeB);

        Assertions.assertNull(result);
    }
}
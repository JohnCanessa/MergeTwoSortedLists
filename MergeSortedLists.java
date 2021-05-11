import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Definition for singly-linked list.
 */
class ListNode {

    // **** class members ****
    int val;
    ListNode next;

    // **** constructor(s) ****
    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val    = val;
        this.next   = next;
    }
}


/**
 * LeetCode 21. Merge Two Sorted Lists
* https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeSortedLists {


    /**
     * Populate linked list with the specified array values.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static ListNode populate(int[] arr) {

        // **** sanity ckeck(s) ****
        if (arr == null || arr.length == 0)
            return null;

        // **** initialization ****
        ListNode h = null;

        // **** traverse array adding nodes to the linked list ****
        for (int i = arr.length - 1; i >= 0; i--) {
            h = new ListNode(arr[i], h);
        }

        // **** return head of linked list ****
        return h;
    }


    /**
     * Display linked list.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static void display(ListNode head) {

        // **** sanity check(s) ****
        if (head == null)
            return;

        // **** traverse the link list ****
        for (ListNode p = head; p != null; p = p.next) {
            System.out.print(p.val);
            if (p.next != null)
                System.out.print("->");
        }
    }


    /**
     * Merge two sorted linked lists and return it as a sorted list.
     * Recursive approach.
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 38.1 MB, less than 77.21% of Java online submissions.
     * 
     */
    static ListNode mergeTwoLists0(ListNode l1, ListNode l2) {

        // **** sanity check(s) ****
        if (l1 != null && l2 == null)
            return l1;

        if (l1 == null && l2 != null)
            return l2;

        // **** initialization ****
        ListNode l3 = null;

        // **** end condition ****
        if (l1 == null && l2 == null)
            return null;

        // **** build merged list ****
        if (l1.val <= l2.val) {
            l3 = l1;
            l1 = l1.next;
        } else {
            l3 = l2;
            l2 = l2.next;
        }

        // **** recursion ****
        l3 .next = mergeTwoLists0(l1, l2);

        // **** return l3 ****
        return l3;
    }


    /**
     * Merge two sorted linked lists and return it as a sorted list.
     * Non-recursive approach.
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 38.2 MB, less than 77.21% of Java online submissions.
     */
    static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        // **** sanity checks ****
        if (l1 != null && l2 == null)
            return l1;
        
        if (l1 == null && l2 != null)
            return l2;

        if (l1 == null && l2 == null)
            return null;

        // **** initialization ****
        ListNode dummy = new ListNode(-1);
        ListNode head  = dummy;

        // **** loop traversing the linked lists ****
        while (l1 != null && l2 != null) {

            // **** select next node ****
            if (l1.val <= l2.val) {
                dummy.next = l1;
                l1 = l1.next;
            } else {
                dummy.next = l2;
                l2 = l2.next;
            }

            // **** move dummy pointer forward ****
            dummy = dummy.next;
        }

        // **** append list (if needed) ****
        if (l1 != null) 
            dummy.next = l1;
        else if (l2 != null)
            dummy.next = l2;

        // **** return merged linked list (skip dummy node) ****
        return head.next;
    }


    /**
     * Test scaffold.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // **** ****
        int[] arr1 = null;
        int[] arr2 = null;

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read array with values for first list ****
        try {
            arr1 = Arrays.stream(br.readLine().trim().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (Exception NumberFormatException) {

            // ???? ????
            // System.err.println("main <<< NumberFormatException arr1");
        }

        // **** read array with values for second list ****
        try {
            arr2 = Arrays.stream(br.readLine().trim().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        } catch (Exception NumberFormatException) {

            // ???? ????
            // System.err.println("main <<< NumberFormatException arr2");
        }

        // **** close buffered reader ****
        br.close();

        // ???? display arrays ????
        System.out.println("main <<< arr1: " + Arrays.toString(arr1));
        System.out.println("main <<< arr2: " + Arrays.toString(arr2));
        
        // **** generate first linked list ****
        ListNode l1 = populate(arr1);

        // **** generate second linked list ****
        ListNode l2 = populate(arr2);

        // ???? display first linked list ????
        System.out.print("main <<< l1: ");
        display(l1);
        System.out.println();

        // ???? display second linked list ????
        System.out.print("main <<< l2: ");
        display(l2);
        System.out.println();


        // **** merged the two lists ****
        // ListNode l3 = mergeTwoLists0(l1, l2);
        ListNode l3 = mergeTwoLists(l1, l2);


        // ???? display result linked list ????
        System.out.print("main <<< l3: ");
        display(l3);
        System.out.println();
    }

}
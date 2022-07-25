/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode cur = head;
        
        // get number of elements in list
        int count = 0;
        while(cur != null){
            count++;
            cur = cur.next;
        }
        cur = head;
        
        if(count == 1){
            head = null;
            return head;
        }
        
        // iterate to location
        if(count - n == 0){
            head = cur.next;
            return head;
        }
        
        int i = 1;
        while(i < count - n){
            cur = cur.next;
            i++;
        }
        
        cur.next = cur.next.next;
        return head;
 
    }
}
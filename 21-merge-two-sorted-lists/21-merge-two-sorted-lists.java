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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // head of new list
        ListNode prehead = new ListNode(-1);
        // keep track of last node of new list
        ListNode cur = prehead;
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                // add list1 node
                cur.next = list1;
                // head of list1 moves
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        
        // at least one of list1 and list2 can still have nodes at this poinnt,
        // so connect the non-null list to the end of the merged list
        cur.next = (list1 == null) ? list2 : list1;
        
        return prehead.next;
        
        
    }
}
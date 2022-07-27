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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        
        int interval = 1;
        while(interval < lists.length){
            for (int i = 0; i + interval< lists.length; i=i+interval*2) {
                lists[i]=mergeTwoLists(lists[i],lists[i+interval]);            
            }
            interval*=2;
        }

        return lists[0];

    }
    
    ListNode mergeTwoLists(ListNode a, ListNode b){
        if(a == null) return b;
        if(b == null) return a;
        
        ListNode prehead = new ListNode(-1);
        ListNode cur = prehead;
        
        while(a != null && b != null){
            if(a.val <= b.val){
                cur.next = a;
                a = a.next;
            }else{
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }
        
        cur.next = a == null ? b : a;
        return prehead.next;
    }
}
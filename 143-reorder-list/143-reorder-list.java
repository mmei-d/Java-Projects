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
    public void reorderList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        
        ListNode cur = head;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
    
        // if even # nodes, pop until (size/2 - 1)
        // if odd # nodes, pop until (size/2)
        int bound = stack.size() % 2 == 0 ? stack.size() - (stack.size()/2 - 1) : stack.size() - stack.size()/2;
        System.out.print("bound: " + bound);
        cur = head;
        ListNode temp = cur.next;
        
        while(stack.size() > bound){
            ListNode addNode = stack.pop();
            cur.next = addNode;
            addNode.next = temp;
            cur = temp;
            temp = temp.next;
        }
        
        // peek top node nd change node.next to = null
        stack.peek().next = null;
        
    }
}
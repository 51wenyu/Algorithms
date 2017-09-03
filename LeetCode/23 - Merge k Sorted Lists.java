Analysis: 
    1. 

Solutions:

1. PriorityQueue, O(nlogk), n means the total elements and k means the size of lists;
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        ListNode dummy = new ListNode(-1), r = dummy;
        for (ListNode node : lists)
            if (node != null) pq.offer(node);
        while (!pq.isEmpty()) {
            r.next = pq.poll();
            r = r.next;
            if (r.next != null)
                pq.offer(r.next);
        }
        return dummy.next;
    }
}

2. Discuss 速度很快，待看
class Solution {
    public ListNode mergeKLists(ListNode[] lists){
        return partion(lists,0,lists.length-1);
    }

    public ListNode partion(ListNode[] lists,int s,int e){
        if(s==e)  return lists[s];
        if(s<e){
            int q=(s+e)/2;
            ListNode l1=partion(lists,s,q);
            ListNode l2=partion(lists,q+1,e);
            return merge(l1,l2);
        }else
            return null;
    }

    public ListNode merge(ListNode l1,ListNode l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next=merge(l1.next,l2);
            return l1;
        }else{
            l2.next=merge(l1,l2.next);
            return l2;
        }
    }
}
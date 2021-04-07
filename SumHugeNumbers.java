/**
Given two singly linked-lists that represent a multi-digit number, (each digit is a node in the linked list)
        return a singly linked list that represents the sum of those two numbers.

        input : : 4->8->2->3, 3→3→3
        3>2>8>4 3>3>3
        output : 5->1→5→6

        inpu1 1-2-3 7-8-9
        9 1 2

        space:0(N)
        time : O(N)
        Explanation : 123+333 = 456 (Sum of the numbers)
//  3->2->1 3->3->3
//RUn :O(n) +O(N) +O(N) => O(N);
//space: O(4N)=>O(N)
//phase :figure the starting point
// Scenario : cannot modify input
// carryFwd LL [N] 1->0->0
//second phase: add carryfwd to output
 */


class LinkListNode
{
    int val;
    LinkListNode next;

    LinkListNode(int v)
    {
        val =v;
        next=null;
    }
}

public class SumHugeNumbers
{
    static LinkListNode outputHead;
    static LinkListNode curPrev;

    public static void  findSum(LinkListNode start1, LinkListNode start2)
    {
        LinkListNode l1head = reverseList(start1);
        LinkListNode l2head = reverseList(start2);

        LinkListNode start = l1head;

        //find the max of the 2 list
        /*
        if(l1>l2)
        {
           for(int i=0;i<l1-l2)
            {
                start1 = start1.next;
            }
        }
        else
        {
            for(int i=0;i<l2-l1)
            {
                start2 = start2.next;
            }
        }
        */


        findDigitSum(l1head,l2head);

        start= reverseList(outputHead);

        while(start!=null)
        {
            System.out.print(start.val);
            start=start.next;
        }

    }

    private static LinkListNode reverseList(LinkListNode start)
    {

        if(start==null)
            return null;

        LinkListNode prev= start;
        LinkListNode cur = start.next;
        start.next=null;


        while(cur!=null)
        {
            LinkListNode temp = cur.next;
            cur.next=prev;

            prev=cur;
            cur=temp;
        }

        return prev;
    }


    private  static int findDigitSum(LinkListNode head1, LinkListNode head2)
    {
        int carry=0;


        while(head1!=null || head2!=null) {
            int l1val=0;
            int l2val=0;
            if(head1!=null)
                l1val = head1.val;
            if(head2!=null)
                l2val = head2.val;

            int output=l1val+l2val+carry;

            if (output > 9) {
                carry = 1;
                output = output - 10;
            }

            LinkListNode opNode = new LinkListNode(output);
            //curOutput = opNode;

            if(outputHead==null) {
                outputHead = opNode;
                curPrev = opNode;
            }
            else {
                curPrev.next = opNode;
            }
            curPrev = opNode;

            //findDigitSum(head1.next, head2.next, carryfwd);

            if(head1!=null)
                 head1= head1.next;
            if(head2!=null)
                 head2= head2.next;
        }

        return carry;
    }


    public static void main(String args[])
    {
        LinkListNode list1Head = new LinkListNode(1);
        list1Head.next= new LinkListNode(2);
        list1Head.next.next= new LinkListNode(3);
        list1Head.next.next.next= new LinkListNode(4);

        LinkListNode list2Head = new LinkListNode(3);
        list2Head.next= new LinkListNode(2);
        list2Head.next.next= new LinkListNode(3);
        //list2Head.next.next.next= new LinkListNode(3);

        findSum(list1Head,list2Head);
    }


}
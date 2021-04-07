public class TrieClass {
    public static int CHAR_MAX = 26;

    static class TrieNode
    {
        boolean isLeaf;
        TrieNode[] children = new TrieNode[CHAR_MAX];

        TrieNode()
        {
            isLeaf=false;
            for(int i=0;i<CHAR_MAX;i++)
            {
                children[i]=null;
            }
        }
    };

    static TrieNode root;

    static void insert(String word)
    {
        TrieNode prev=root;
        for(int i=0;i<word.length();i++)
        {
            TrieNode temp = prev.children[word.charAt(i)-'a'];
            if(temp!=null)
            {
                prev =temp;
            }
            else
            {
                TrieNode newnode = new TrieNode();
                prev.children[word.charAt(i)-'a'] = newnode;
                prev = newnode;
            }
        }
        prev.isLeaf =true;
    }

    static boolean search(String word)
    {
        TrieNode prev=root;
        for(int i=0;i<word.length();i++) {
            TrieNode temp = prev.children[word.charAt(i) - 'a'];
            if (temp != null) {
                prev = temp;
            } else {
                return false;
            }
        }
        return true;
    }

    // Driver
    public static void main(String args[])
    {
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};


        root = new TrieNode();

        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            insert(keys[i]);

        // Search for different keys
        if(search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if(search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if(search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if(search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);

    }
}

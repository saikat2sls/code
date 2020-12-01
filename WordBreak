/*

https://leetcode.com/problems/word-break-ii/

 */


/*
//catsanddog
    |           (word: cat)
        |       (word: sand)
            |   (word: dog)

  Sol1:cat sand dog    (start-index:2)
  //catsanddog
       |
          |
             |
*/

import java.util.ArrayList;
import java.util.List;

class Wordbreak {

    TrieNode root;

    public class TrieNode
    {
        public int isLeaf;
        public TrieNode[] children = new TrieNode[26];

        public TrieNode()
        {
            for(int i=0;i<26;i++)
            {
                children[i]=null;
            }
            isLeaf=0;
        }
    }

    public void insertWord(String word)
    {
        //System.out.println("Inserting word "+word);
        int l =word.length();
        TrieNode parent = root;
        for(int i=0;i<l;i++)
        {
            int n = word.charAt(i)-'a';
            //System.out.println("Inserting index "+n);
            TrieNode temp = parent.children[n];

            if(temp==null)
            {
                temp = new TrieNode();
                parent.children[n]=temp;
            }
            parent =temp;
        }
        parent.isLeaf=1;   //mark the word is completed
    }

    public void insertTrie(TrieNode parent, char c)
    {
        if(parent.children[c-'a']==null)
        {
            TrieNode temp = new TrieNode();
            parent.children[c-'a']=temp;
        }
    }


    public void displayTrie(TrieNode prev)
    {
        TrieNode temp = prev;


        if(temp.isLeaf==1)
            System.out.println("//");


        for(int i=0;i<26;i++)
        {
            if(temp.children[i]!=null)
            {
                System.out.print(Character.toString((char)('a'+i)));
                displayTrie(temp.children[i]);
            }
        }

    }


    public void constructTrie(List<String> wordDict)
    {
        root = new TrieNode();
        for(int i=0;i<wordDict.size();i++)
        {
            insertWord(wordDict.get(i));
        }
    }

    public void searchInput(String input, String currentWordSet, List<String> output)
    {
        int wordFound=0;
        int l=input.length();
        String word;

        TrieNode prev = root;

        for(int i=0;i<l;i++)
        {
            TrieNode temp = prev.children[input.charAt(i)-'a'];
            if(temp!=null)
            {
                prev=temp;
            }
            else
            {
                return;
            }
            if(prev.isLeaf==1)
            {
                System.out.println(input.substring(0,i+1));
                word = currentWordSet + " " + input.substring(0,i+1);
                searchInput(input.substring(i+1),word,output );
            }
        }
        //System.out.println(currentWordSet);
        if(input.isEmpty())
            output.add(currentWordSet);

    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        constructTrie(wordDict);

        //displayTrie(root);

        List<String> output = new ArrayList<>();
        searchInput(s,"",output);
        return output;
    }
}

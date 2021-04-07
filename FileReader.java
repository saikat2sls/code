import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Input:
book.csv         format : id,book-name,authorId
author.csv       format  : id,author-name

output: books which have been written by an author
 */

public class FileReader {
    public static void main(String arg[])
    {
        Map<String,String> authorMap = new HashMap<>();
        Map<String, List<String>> authorBookMap = new HashMap<>();


        //pass1: read the author file and populate the author map
        try {
            BufferedReader bf = new BufferedReader(new java.io.FileReader("src/main/resources/author.csv"));
            String line = bf.readLine();
            while (line != null) {
                String words[] = line.split(",");
                String authorId = words[0];
                String authorName = words[1];
                authorMap.put(authorId, authorName);
                line = bf.readLine();
            }
        }catch (Exception e)
        {
            System.out.println("Pass 1: Exception encountered");
        }

        //pass 2: read the book name and map to author name

        try {
            BufferedReader bf = new BufferedReader(new java.io.FileReader("src/main/resources/book.csv"));
            String line = bf.readLine();
            while (line != null) {
              String words[] = line.split(",");
              String bookId = words[0];
              String bookName = words[1];
              String authorId = words[2];
              String authorName = authorMap.get(authorId);
              if(null!=authorName) {
                  if (authorBookMap.get(authorName) != null) {
                      authorBookMap.get(authorName).add(bookName);
                      //bookList.add(bookName);
                  } else {
                      List<String> bookList = new ArrayList();
                      bookList.add(bookName);
                      authorBookMap.put(authorName, bookList);
                  }
              }
                line = bf.readLine();
            }
        }catch (Exception e)
        {
            System.out.println("Pass2 :Exception encountered");
        }

        //Pass 3: print the author to book mapping
        for(String author: authorBookMap.keySet())
        {
            System.out.println(author + " : "+authorBookMap.get(author));
        }
    }
}

import edu.duke.*;
/**
 * Write a description of part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void printUrls(String url) {
        URLResource file = new URLResource(url);
        for(String item : file.words()) {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if (pos != -1){
                int beg = item.lastIndexOf("\"",pos);
                int end = item.indexOf("\"", pos+1);
                System.out.println(item.substring(beg+1,end));  
            }
        }
    }
    
    public void testUrl() {
        printUrls("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }

    
}
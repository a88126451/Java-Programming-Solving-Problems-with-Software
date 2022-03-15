
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences (String stringa, String stringb){
        int firstOccurrence = stringb.indexOf(stringa);
        int lena = stringa.length();
        if (firstOccurrence == -1) return false;
        if (stringb.indexOf(stringa, firstOccurrence + lena) == -1)
            return false;
        return true;
    }
    public String lastPart (String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        int lena = stringa.length();
        if (index == -1)
            return stringb;
        else return stringb.substring(index + lena);
    }
    
    public void testing(){
        String[][] tests = {
            {"by", "A story by Abby Long"},
            {"a", "banana"},
            {"atg", "ctgtatgta"},
            {"zoo", "forest"}
        };
        
        for (String[] test : tests){
            System.out.println(test[0] + "," + test[1]);
            System.out.println("twoOccurrences: " + twoOccurrences(test[0], test[1]));
            System.out.println("lastPart: " + lastPart(test[0], test[1]));
        }
        
    }

}

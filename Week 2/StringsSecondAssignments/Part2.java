/**
 * Part 2: HowMany - Finding Multiple Occurrences
 * This class will write a method to determine how many occurrences of a string appear in another string.
 * 
 * @author Joyce Lin
 * @version Mar 17, 2022
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
    // This method returns an integer indicating how many times stringa appears in stringb, where each occurrence of 
    // stringa must not overlap with another occurrence of it
        int count = 0;
        int startIndex = 0;
        // repeat the loop
        while (true) {
            // first find the first occurence of stringa in stringb
            startIndex = stringb.indexOf(stringa, startIndex);
            if (startIndex == -1) {
                break;
            }
            // update the startIndex
            startIndex += stringa.length();
            // count plus 1
            count ++;
        }
        return count;
    }
    
    public void testHowMany(){
	String[][] testCases = {
		{"GAA","ATGAACGAATTGAATC"},
		{"AA","ATAAAA"},
		{"ATA", "TGATATATATGGA"},
		{"TTT", "TTTTTTTTTTTTTT"}
	};
	for(String[] test : testCases){
	    System.out.println(howMany(test[0], test[1]));
	}
    }

}

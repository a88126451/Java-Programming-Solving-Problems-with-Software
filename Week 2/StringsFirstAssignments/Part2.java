
/**
 * Write a description of Part2 here.
 * 
 * @author Joyce Lin
 * @version 1.0
 */
public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon){
        //Check strings that are either all uppercase letters or all lowercase letters.
        //Set startCodon and stopCodon to all uppercase or all lowercase.
        if (dna.equals(dna.toUpperCase())){
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        }
        else{
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        //Set index of startCodon and stopCodon.
        int startIdx = dna.indexOf(startCodon);
        int stopIdx = dna.indexOf(stopCodon, startIdx);
        if (startIdx == -1 | stopIdx == -1 ) return "";
        //Check the length of the substring between the “ATG” and “TAA” is a multiple of 3.
        String result = dna.substring(startIdx, stopIdx+3);
        if (result.length() % 3 != 0) return "";
      
        return result;
    }
    
    public void testSimpleGene() {
        String[] tests = {
            "CACGAAGTAA", //no ATG
            "TTGACATGGATTA", //no TAA
            "CTATGGACACCTCATAAATC", //ATG TAA MOD 3
            "TATCGGACTACGAGTTAGAA", //no ATG no TAA
            "AGATGATATTGCTAAGA", //ATG TAA not MOD 3
            "atgctataa" // Lowercase
        };
        for(String test : tests){
            System.out.println("DNA  sequence: " + test);
            System.out.println("Gene found   : " + findSimpleGene(test, "ATG", "TAA"));
        }
        
    }

}

/**
 * Write a description of Part1 here.
 * 
 * @author Joyce Lin
 * @version 1.0
 */
public class Part1 {
    public String findSimpleGene (String dna){
        int startIdx = dna.indexOf("ATG");
        int stopIdx = dna.indexOf("TAA", startIdx);
        if (startIdx == -1 | stopIdx == -1 ) return "";
        
        String result = dna.substring(startIdx, stopIdx+3);
        if (result.length() % 3 != 0) return "";
        
        return result;
    }
    
    public void testSimpleGene() {
        String[] tests = {
            "CACGAAGTAA", //NO ATG
            "TTGACATGGATTA", //NO TAA
            "CTATGGACACCTCATAAATC", //ATG TAA MULT 3
            "TATCGGACTACGAGTTAGAA", //NO ATG NO TAA
            "AGATGATATTGCTAAGA" //ATG TAA NOT MULT 3
        };
        for(String test : tests){
            System.out.println("DNA  sequence: " + test);
            System.out.println("Gene found   : " + findSimpleGene(test));
        }
        
    }
}

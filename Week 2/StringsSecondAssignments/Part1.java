/**
 * Part 1: Finding many Genes 
 * This class is to write the code from the lesson to make the following improvements to the algorithm:
 * A. Find a gene in a strand of DNA where the stop codon could be any of the three stop codons “TAA”, “TAG”, or “TGA”.
 * B. Find all the genes (where the stop codon could be any of the three stop codons) in a strand of DNA.
 * 
 * @author Joyce Lin 
 * @version 1.0 Mar 17, 2022
 */
public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        //This method returns the index of the first occurrence of stopCodon that 
        //appears past startIndex and is a multiple of 3 away from startIndex. 
        //If there is no such stopCodon, this method returns the length of the dna strand.
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0){
                return currIndex;
            } else {
                return dna.length();
            }
        }
        return -1;
    }
    
    public String findGene (String dna, int where) {
        // find first occurence of "ATG", startIndex
        // if startIndex is -1, return ""
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) return "";
        // use tagIndex to store findStopCodon method
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        // store in minIndex the smallest of these three values
        // int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        // Instead of store minIndex by math method, it should deal with -1 value using and/or logic
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        // if minIndex is dna.lenght(), then nothing found, return empty string
        if (minIndex == -1 ){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);   
    }
    public void printAllGenes(String dna) {
        // set startIndex to 0
        int startIndex = 0;
        while (true) {
            // find the next gene after startIndex and return a string
            String currentGene = findGene(dna, startIndex);
            // if no gene was found using isEmpty method, leave this loop
            if (currentGene.isEmpty()){
                break;
            }
            // print that gene out
            System.out.println(currentGene);
            // set startIndex to just past the end of the gene
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public void testFindStopCodon() {
        //calls the method findStopCodon with several examples and prints out the results to check if findStopCodon 
        //is working correctly.
        //            0  3  6  9  2  5  8  1  45
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna, 1, "TAA");
        if (dex != -1) System.out.println("error on 26");
        dex = findStopCodon(dna, 0, "TAG");
        if (dex != -1) System.out.println("error on 26 TAG");
        System.out.println("test finished");
    }
    
    public void testFindGene() {
        String[] testCases = {
                "TAATAGTGA",// DNA with no startIndex = ATG
                "ATGXXXYYYTGA", // DNA with “ATG” and one valid stop codon
                "XXXATGTAATAGTGA", // DNA with “ATG” and multiple valid stop codons
                "ATGXXXYYYZZZ", //DNA with “ATG” and no valid stop codons
                "XXATGXXTGATAA", // DNA is not module of 3          
                };
    for(String dna : testCases){
        System.out.println("DNA: " + dna);
        System.out.println("Gene: "+ findGene(dna, 0));
    }
    }
    
    public void testFindAllGenes() {
        //            ATGv  TAAv  ATG   v  v  TGA
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAGA";
        System.out.println("DNA: " + dna);
        printAllGenes(dna);
    }
 

}

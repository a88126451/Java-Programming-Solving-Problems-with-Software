/**
 * Part 3: How Many Genes?
 * This program designs to count how many genes are in a strand of DNA.
 * @Joyce Lin 
 * @1.0 Mar 17, 2022
 */
public class part3 {
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
    
    public int countGenes (String dna) {
        int startIndex = 0;
        int count = 0;
        while (true) {
            // find the next gene after startIndex 
            String currentGene = findGene(dna, startIndex);
            // if no gene was found using isEmpty method, leave this loop
            if (currentGene.isEmpty()){
                break;
            }
            // update startIndex
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
            // update count
            count ++;
        }
        return count;
    }
    
    public void test() {
        //              ATGv  TAAv  ATG   v  v  TGA
        //String dna = "ATGATCTAATTTATGCTGCAACGGTGAGA";
        String dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println("DNA: " + dna);
        printAllGenes(dna);
        System.out.println("count: " + countGenes(dna));
    }

}

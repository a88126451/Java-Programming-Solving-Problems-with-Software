
/**
 * processGenes that has one parameter sr, which is a StorageResource of strings. 
 * This method processes all the strings in sr to find out information about them. 
 * Specifically, it should: 
 * print all the Strings in sr that are longer than 9 characters
 * print the number of Strings in sr that are longer than 9 characters
 * print the Strings in sr whose C-G-ratio is higher than 0.35
 * print the number of strings in sr whose C-G-ratio is higher than 0.35
 * print the length of the longest gene in sr
 * 
 * @Joyce Lin
 * @1.0 Mar 19, 2022
 */
import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Part3 {
    public void processGenes (StorageResource sr) {
        Part2 p2 = new Part2(); // to call cgRatio method
    
        int count1 = 0; // count character
        int count2 = 0; // count cgRatio
        String maxLength = ""; // store maxlength
    
        for (String str : sr.data()) {
            // print all strings in sr that are longer than 9 character
            if (str.length() > 60) {
                System.out.println(str);
                count1++;
            }
            // call cgratio method is higher than 0.35
            if (p2.cgRatio(str) > 0.35) { 
                System.out.println(str);
                count2++;
            }
            // print the longest gene in sr
            if (str.length() > maxLength.length()) {
                maxLength = str;
            }
        
        }
        System.out.println("the number of Strings longer than 60 characters is: " + count1);
        System.out.println("the number of C-G-ratio is higher than 0.35 is: " + count2);
        System.out.println("the longet gene is: " + maxLength);
        System.out.println("the longet gene length is: " + maxLength.length());
    }
    
    public void testProcessGenes() {
        //FileResource fr = new FileResource("brca1line.fa");
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        Part1 p1 = new Part1();
        Part2 p2 = new Part2();
        StorageResource genes = p1.getAllGenes(dna);
        System.out.println("Size: "+genes.size());
        processGenes(genes);
        System.out.println("Count of CTG: " + p2.countCTG(dna));
    }

}

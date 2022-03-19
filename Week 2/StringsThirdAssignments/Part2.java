/**
 *  the method cgRatio that has one String parameter dna, and returns the ratio 
 *  of C’s and G’s in dna as a fraction of the entire strand of DNA. .
 * 
 * @Joyce Lin
 * @1.0 Mar 19, 2022
 */
public class Part2 {
    public double cgRatio (String dna) {
        // find all c and g in dna
        int count = 0;
        int i = 0;
        while (i < dna.length()) {
            char protein = dna.charAt(i);
            if (protein == 'C' || protein == 'G') {
                count++;
            }
            i++;
        }
        double answer = ((double)(count)) / dna.length();
        return answer;
        
    }
    public int countCTG (String dna) {
        int count = 0;
        int currentIndex = 0;
        while (true) {
            currentIndex = dna.indexOf("CTG", currentIndex);
            if (currentIndex == -1) break;
            currentIndex += 3;
            count ++;
        }
        return count;
    }
    public void testCGRatio(){
    String dna = "ATGCCATAG";
    System.out.println("DNA: " + dna);
    System.out.println("cgRatio: " + cgRatio(dna));
    }
    public void testCountCTG(){
    String dna = "CTGAATGCTGCTG";
    System.out.println("DNA: " + dna);
    System.out.println("cgRatio: " + countCTG(dna));
    }

}

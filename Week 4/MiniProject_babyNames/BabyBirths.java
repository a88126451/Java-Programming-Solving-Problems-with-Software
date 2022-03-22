/**
 * Print out the names for which 100 or fewer babies were born in a chosen CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                               " Gender " + rec.get(1) +
                               " Num Born " + rec.get(2));
            }
        }
    }
    
    // This method returns the total number of births and total names of males and females in a file
    public void totalBirths (FileResource fr) {
       int totalBirths = 0;
       int totalBoysBirths = 0;
       int totalGirlsBirths = 0;
       int totalNames = 0;
       int countBoysNames = 0;
       int countGirlsNames = 0;
       for (CSVRecord rec : fr.getCSVParser(false)) {
           int numBorn = Integer.parseInt(rec.get(2));
           totalBirths += numBorn;
           if (rec.get(1).equals("M")) {
               totalBoysBirths += numBorn;
               countBoysNames++;
           } else {
               totalGirlsBirths += numBorn;
               countGirlsNames++;
           }
       }
       totalNames = countBoysNames + countGirlsNames;
       System.out.println("total births = " + totalBirths);
       System.out.println("total boys births = " + totalBoysBirths);
       System.out.println("total girls births = " + totalGirlsBirths);
       System.out.println("total names = " + totalNames);
       System.out.println("total boys names = " + countBoysNames);
       System.out.println("total girls names = " + countGirlsNames);
    }
    
    // This method returns the rank of the name in the file for the given gender.
    // If the name is not in the file, then -1 is returned.
    public int getRank (int year, String name, String gender) {
        int rank = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser){
            // check female or male
            if(record.get(1).equals(gender)){
                rank++; // update rank
                if(record.get(0).equals(name)){
                    return rank; //found
                }
            }
        }
        return -1;
    }
    
    // This method returns the name of the person in the file at this rank, for the given gender, 
    // where rank 1 is the name with the largest number of births. 
    // If the rank does not exist in the file, then “NO NAME”  is returned.
    public String getName (int year, int rank, String gender) {
        String name = "";
        int rankInFile = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser){
            // check female or male
            if(record.get(1).equals(gender)){
                rankInFile++;   // update rank
                if(rankInFile == rank) {
                    name = record.get(0);
                    return name;
                }
            }
        }
        return "NO NAME";
    }
    
    // This method determines what name would have been named if they were born in a different year, based on the same popularity. 
    public void whatIsNameInYear (String name, int year, int newYear, String gender) {
       String newName = "";
       int rankInYear = 0;
       rankInYear = getRank(year, name, gender);
       if (rankInYear != -1) {
           newName = getName(newYear, rankInYear, gender);
       }
       if(newName.equals("NO NAME")){
           System.out.println("NO NAME");
       } else {
           System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");
       }
    }
    
    // This method selects a range of files to process and returns an integer, the year with the highest rank for the name and gender. 
    // If the name and gender are not in any of the selected files, it should return -1.
    public int yearOfHighestRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int rank = 0;
        int highestRank = 0;
        int year = -1;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            rank = 0; // reset the rank
            for(CSVRecord record : parser) {
                if(record.get(1).equals(gender)){
                    rank++; // update rank
                    if(record.get(0).equals(name)){
                        if(rank < highestRank || year == -1){
                            highestRank = rank;
                            year = Integer.parseInt(f.getName().substring(3, 7));
                            break;
                        }
                    }
                }
            }
        }
        return year;
    }
    
    // This method selects a range of files to process and returns a double representing the average rank of the name and gender 
    // over the selected files. It should return -1.0 if the name is not ranked in any of the selected files. 
    public double getAverageRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int rank = 0;
        double totalRank = 0;
        double averageRank = 0;
        int fileCount = 0;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            rank = 0; // reset the rank
            for(CSVRecord record : parser) {
                if(record.get(1).equals(gender)){
                    rank++; // update rank
                    if(record.get(0).equals(name)){
                        totalRank += rank;
                    }
                }
            }
            fileCount++;
        }
        if(totalRank == 0) return -1.0;
        averageRank = totalRank / fileCount;
        return averageRank;
    }
    
    // This method returns an integer, the total number of births of those names with the same gender 
    // and same year who are ranked higher than name.
    public int getTotalBirthsRankedHigher ( String name, String gender) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int totalBirths = 0;
        for(CSVRecord record : parser) {
            if(record.get(1).equals(gender)){
                if(record.get(0).equals(name)){
                    break;
                }
                totalBirths += Integer.parseInt(record.get(2));
            }
        }
        return totalBirths;
    }
    
    public void test () {
        FileResource fr1 = new FileResource("data/yob1900.csv");
        FileResource fr2 = new FileResource("data/yob1905.csv");
        totalBirths(fr1);
        totalBirths(fr2);
        //System.out.println("Emily Rank is : " + getRank(1960,"Emily", "F"));
        //System.out.println("Frank Rank is : " + getRank(1971,"Frank", "M"));
        //System.out.println("Name is : " + getName(1980,350, "F"));
        //System.out.println("Name is : " + getName(1982,450, "M"));
        //whatIsNameInYear("Susan", 1972, 2014, "F");
        //whatIsNameInYear("Owen", 1974, 2014, "M");
        //System.out.println(yearOfHighestRank ("Genevieve", "F"));
        //System.out.println(yearOfHighestRank ("Mich", "M"));
        //System.out.println("Rank is : " + getAverageRank ("Susan", "F"));
        //System.out.println(getAverageRank ("Robert", "M"));
        //System.out.println(getTotalBirthsRankedHigher ("Emily", "F"));
        //System.out.println(getTotalBirthsRankedHigher ("Drew", "M"));
    }
}


/**
 * Parsing Export Data using CSV Libraries.
 * 
 * @author Joyce Lin 
 * @version Mar 20, 2022
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Export {
    // This method returns a string of information about the country or returns “NOT FOUND” if there is no 
    // information about the country.
    public String countryInfo(CSVParser parser, String country) {
        // for each row in the CSV File
        for (CSVRecord record : parser) {
            // look at the "country" column
            String name = record.get("Country");
            // check if it contains country
            if (name.contains(country)) {
                String result = "";
                result = record.get(0) + ":" + record.get(1) + ":" + record.get(2);
                return result;
            } 
        }
        return "NOT FOUND";
    }
    // This method prints the names of all the countries that have both exportItem1 and exportItem2 as export items.
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        // for each row in the CSV File
        for (CSVRecord record : parser) {
            // look at the "export" column which index is 1
            String export = record.get(1);
            // check if it contains item1 and item2
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                String country = record.get(0);
                System.out.println(country);
            }
        }
    }
    // This method returns the number of countries that export exportItem.
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get(1);
            if (export.contains(exportItem)) {
                String country = record.get(0);
                count++;
            }
        }
        return count;
    }
    // This method prints the names of countries and their Value amount for all countries whose Value (dollars) 
    // string is longer than the amount string.
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get(2);
            if (value.length() > amount.length()) {
                String country = record.get(0);
                System.out.println(country + " " + value);
            }
        }
    }
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "gold", "diamonds");
        System.out.println(numberOfExporters(parser, "gold"));
        //bigExporters(parser, "$999,999,999,999");
    }
}

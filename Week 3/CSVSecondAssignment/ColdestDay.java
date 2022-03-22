
/**
 * Find the coldest day in a file and in a year.
 * 
 * @author Joyce Lin 
 * @version Mar 20, 2022
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ColdestDay {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        // start with SmallestSoFar as nothing
        CSVRecord SmallestSoFar = null;
        // For each row(currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // call getSmallestOfTwo
            if (SmallestSoFar == null) {
            SmallestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double SmallestTemp = Double.parseDouble(SmallestSoFar.get("TemperatureF"));
                // Check if currentRow's temperature < largestSoFar's
                if (currentTemp < SmallestTemp && currentTemp != -9999) {
                // Update the SmallestSoFar
                SmallestSoFar = currentRow;
                }
            }
        }
        return SmallestSoFar;
    }
    public File fileWithColdestTemperature() {
        File fileName = null;
        String result = "";
        CSVRecord SmallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = coldestHourInFile(parser);
            if (SmallestSoFar == null) {
                SmallestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double SmallestTemp = Double.parseDouble(SmallestSoFar.get("TemperatureF"));
                if (currentTemp < SmallestTemp && currentTemp != -9999) {
                    SmallestSoFar = currentRow;
                    fileName = f;
                }
            }
        }
        return fileName;
    }
    public CSVRecord lowestOfTwo (CSVRecord currentRow, CSVRecord lowestSoFar) {
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        } else {
            boolean currentHumidString = currentRow.get("Humidity").contains("N/A");
            if (currentHumidString == false){
                int currentHumid = Integer.parseInt(currentRow.get("Humidity"));
                int lowestHumid = Integer.parseInt(lowestSoFar.get("Humidity"));
                if (currentHumid < lowestHumid){
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }   
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            lowestSoFar = lowestOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    public CSVRecord lowestHumidtiyInManyFiles () {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = lowestOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    public double averageTemperatureInFile (CSVParser parser) {
        double averageTemp = 0;
        double totalTemp = 0;
        int count = 0;
        for (CSVRecord row : parser) {
            totalTemp += Double.parseDouble(row.get("TemperatureF"));
            count ++;
        }
        averageTemp = totalTemp / count;
        return averageTemp;
    }
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        double averageTemp = 0;
        double totalTemp = 0;
        int count = 0;
        for (CSVRecord row : parser) {
            int humidity = Integer.parseInt(row.get("Humidity")); 
            if (humidity >= value) {
                totalTemp += Double.parseDouble(row.get("TemperatureF"));
                count ++;
            } 
        }
        averageTemp = totalTemp / count;
        return averageTemp;
    }
    public void testColdestInDay() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was: " + smallest.get("TemperatureF") + " " + smallest.get("DateUTC"));
    }
    public void testFileWithColdestTemperature() {
        File coldestFileName = fileWithColdestTemperature();
        FileResource fr = new FileResource(coldestFileName);
        System.out.println("Coldest day was in file: " + coldestFileName.getName());
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was: " + coldestRecord.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were: ");
        for (CSVRecord row : fr.getCSVParser()) {
            System.out.println(row.get("DateUTC") + " " + row.get("TemperatureF"));
        }
    }
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC")); 
    }
    public void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidtiyInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double temp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + temp); 
    }
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double temp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(Double.isNaN(temp)) {
            System.out.println("No Temperature was found");
        } else {
        System.out.println("average temperature is " + temp);
    }
    }
}

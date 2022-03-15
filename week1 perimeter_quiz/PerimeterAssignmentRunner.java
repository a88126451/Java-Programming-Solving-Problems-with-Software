import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numPoints = 0;
        for (Point currPt : s.getPoints()){
            numPoints += 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        return getPerimeter(s) / getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > largestSide){
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0;
        for (Point currPt : s.getPoints()){
            double currPtX = currPt.getX();
            if ( currPtX > largestX){
                largestX = currPtX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (perimeter > largestPerimeter){
                largestPerimeter = perimeter;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;
        // replace this code
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (perimeter > largestPerimeter){
                largestPerimeter = perimeter;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int points = getNumPoints(s);
        double averagelength = getAverageLength(s);
        double largestside = getLargestSide(s);
        double largestx = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("numpoints = " + points);
        System.out.println("the average length of a side in your shape is " + averagelength);
        System.out.println("the longest side is " + largestside);
        System.out.println("the largest X is " + largestx);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestperimeter = getLargestPerimeterMultipleFiles();
        System.out.println("the file with largest perimeter is " + largestperimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largestperimeter = getFileWithLargestPerimeter();
        System.out.println("the file with largest perimeter is " + largestperimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}

import java.io.*;
import java.util.*;

/**
 *
 * @author Swati
 */
public class Main {

  public static String[][] businessHours(String[][] intervals) {
    //taking care of edge cases
    if (intervals.length == 0 || intervals.length == 1){
        return intervals;
    }
    
    //convert string 2D array to double 2D array so I can do some computation with them
    double[][] doubleIntervalsArray = new double[intervals.length][intervals[0].length] ;
    for (int row = 0; row < intervals.length; row++) {
        for (int column = 0; column < intervals[row].length; column++) {
            double  nowDouble = Double.parseDouble(intervals[row][column]);
            doubleIntervalsArray[row][column] = nowDouble;
        }
    }     
    //sort the double 2D array based on ascending order of starting time
    Arrays.sort(doubleIntervalsArray, new Comparator<double[]>() {
        @Override
        public int compare(double[] a, double[] b) {
            return Double.compare(a[0], b[0]);
        }
    });
    //create a double 2D array to hold the final time periods
    double[][] holdFinalAnswerArr = new double[doubleIntervalsArray.length][doubleIntervalsArray[0].length];
    //get the values of the first element
    double start = doubleIntervalsArray[0][0];
    double end = doubleIntervalsArray[0][1];
    //counter
    int r = 0;
    //start at the second element; cause already have the 1st one
    for (int row = 1; row < doubleIntervalsArray.length; row++) {     
    //if the starting time of the current element is less than the previous element's end time
        if (doubleIntervalsArray[row][0] <= end){
        //then compare the current element's end time with the previous element's end time
        //and return the greater of the two end time, and set it as the new end time
            end = Math.max(doubleIntervalsArray[row][1], end);
        }else{
            //create a new element to add to the 2D array 
            holdFinalAnswerArr[r][0] = start;
            holdFinalAnswerArr[r][1] = end; 
            r++;       
            //set the new start and end to current element's start and end time
            start = doubleIntervalsArray[row][0];
            end = doubleIntervalsArray[row][1];
        }//else: when there no overlapping time periods
    }
    //final addition to the 2D array 
    holdFinalAnswerArr[r][0] = start;
    holdFinalAnswerArr[r][1] = end;       
    
    String[][] si = new String[intervals.length][intervals[0].length] ;
    //convert double 2D array back to string 2D array,in order to return a string 2D array
    for (int row = 0; row < holdFinalAnswerArr.length; row++) {
      for (int column = 0; column < holdFinalAnswerArr[row].length; column++) {
          String nowString = String.valueOf(holdFinalAnswerArr[row][column]);
          if(!nowString.equals("0.0")){
            si[row][column] = nowString;
            //System.out.println(nowString);
          }
          //intervals[row][column] = nowString;
      }
    }
    return si; 
  }
    public static void main(String[] args) {
        //TEST CASES:
        //String[][] intervals = {{"07.00", "13.00"}, {"14.00", "19.00"}, {"09.00", "15.00"}};
        //String[][] intervals = {{"07.00", "13.00"}, {"14.00", "19.00"}, {"09.00", "12.00"}};
        //String[][] intervals = {{"08.15", "10.50"}, {"13.45", "16.00"}, {"21.00", "24.00"}};
        String[][] intervals = {{"07.00", "9.00"}, {"10.00", "12.00"}, {"13.00", "14.00"}, {"14.00", "16.00"}, {"21.00", "24.00"}, {"13.00", "16.00"}};
        //String[][] intervals = {{"13.25", "15.45"}, {"18.00", "21.32"}, {"23.00", "24.00"}, {"14.00", "19.00"}, {"12.00", "15.60"}, {"13.00", "14.00"}};
        //String[][] intervals = {{"13.00", "15.00"}, {"14.00", "18.00"}, {"20.00", "22.00"}, {"15.00", "18.00"}};
        //String[][] intervals = {{"13.25", "15.45"}, {"17.00", "19.00"}, {"12.00", "15.60"}, {"09.00", "12.00"}};
        //String[][] intervals = {{"01.00", "9.00"}, {"12.00", "19.00"}, {"22.00", "24.00"}};

        for (String[] interval : businessHours(intervals)) {
          System.out.println(Arrays.toString(interval));
        }
    }  
}

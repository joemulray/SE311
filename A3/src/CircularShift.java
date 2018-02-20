
import java.util.*;
import java.io.*;

/**
 * 
 */
public class CircularShift {


    private LineStorage shifts;
    private ArrayList<String> allshifts = new ArrayList<String>();
    private ArrayList<String> data = new ArrayList<String>();

    /**
     * Default constructor
     */
    public void CircularShift() {
    }


    public void CircularShift(LineStorage lines){
         this.shifts = lines;
         this.data = lines.getData();   //declaring attributes
    }


    public void shifts()
    {
        List<String> sentences = null;
        for(String line : this.data){
            sentences = build(line);

            for(String finalline: sentences)
                allshifts.add(finalline);
        }
    }
    

    private List<String> build(String line)
    {
        String wordslist[] = line.split(" ");
        List<String> returnString = new ArrayList<String>();

        for (int index = 0; index < wordslist.length; index++)
        {
        int temp = index;
        String toline = "";
        do {
            if (toline.length() > 0)
                    toline = toline + " ";

            toline = toline + wordslist[temp];
            temp = ((temp+1) % wordslist.length);
            } while (temp != index);
            returnString.add(toline);
        }
        return returnString;
    } 



    public ArrayList<String> getLists(){
        return this.allshifts;  //return the unsorted shifts
    }

}
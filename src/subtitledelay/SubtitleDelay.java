/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitledelay;

import java.util.ArrayList;
import javax.swing.SwingWorker;

/**
 *
 * @author Daniel Munkacsi
 */
public class SubtitleDelay {
    
    private String[][] moddedTimePairs;
    private final ArrayList<String> timeslist;
    private ArrayList<String> newlist;
    private int delay;
    
    public SubtitleDelay(int d, ArrayList<String> tl, ArrayList<String> storage){
        delay = d;
        timeslist = tl;
        newlist = storage;
    }
    
    public void delaySubs(){
        SwingWorker swapsubs = new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() {
                delay();
                return null;
            }
            
            @Override
            public void done() {
                updateGUI();
            }
        };
        swapsubs.execute();
    }
    
    /**
     * Add delay to the times read from the original subtitle file.
     */
    private void delay(){
        moddedTimePairs = new String[timeslist.size()][2];
        int ARRAYLIST_INDEX_COUNT = 0;
        for(String s: timeslist){
            String[] arrowseparated = s.split(" --> "); // the separator string can later be a variable, input by the user
            // for each of the above parts (2 parts) , we now split again
            for(int i = 0; i < arrowseparated.length; i++){
                int dly = delay;
                String[] colonsplit = arrowseparated[i].split(":");
                String hoursfromsplit = colonsplit[0];
                String minutesfromsplit = colonsplit[1];
                // so we have 00 00 50,740  therefore we split again to get 50 740
                String[] commasplit = colonsplit[2].split(",");
                
                // we now have 50 740  so we can parse the 0th index of commasplit to integer
                String secondsfromsplit = commasplit[0];
                String milisecsfromsplit = commasplit[1];
                int milliseconds = Integer.parseInt(milisecsfromsplit);
                int seconds = Integer.parseInt(secondsfromsplit);
                int minutes = Integer.parseInt(minutesfromsplit);
                int hours = Integer.parseInt(hoursfromsplit);
                String milisecstring = "0";
                String secstring = "0";
                String mntstring = "0";
                String hrstring = "0";
                while(dly > 0){
                    if((milliseconds + 1) < 1000){
                        milliseconds++;
                        dly--;
                    }else{
                        if((seconds + 1) < 60){
                        milliseconds = 0;
                        seconds++;
                        dly--;
                        }else{
                            if((minutes + 1) < 60){
                                milliseconds = 0;
                                seconds = 0;
                                minutes++;
                                dly--;
                            }else{
                                milliseconds = 0;
                                minutes = 0;
                                seconds = 0;
                                hours++;
                                dly--;
                            }
                        }
                    }
                }
                if(milliseconds > 99){milisecstring = Integer.toString(milliseconds);}
                else if(milliseconds > 9 && milliseconds < 100){milisecstring += Integer.toString(milliseconds);}else{milisecstring += "0" + Integer.toString(milliseconds);}
                
                if(seconds > 9){secstring = Integer.toString(seconds);}else{secstring += Integer.toString(seconds);}
                
                if(minutes > 9){mntstring = Integer.toString(minutes);}else{mntstring += Integer.toString(minutes);}
                
                if(hours > 9){hrstring = Integer.toString(hours);}else{hrstring += Integer.toString(hours);}
                
                moddedTimePairs[ARRAYLIST_INDEX_COUNT][i] = hrstring + ":" + mntstring + ":" + secstring + "," + milisecstring;
            }
            ARRAYLIST_INDEX_COUNT++;
        } 
    }
    
    /**
     * Update the user interface with the new values.
     */
    private void updateGUI(){
        for(int i = 0; i < moddedTimePairs.length; i++){
            String line = "";
            line = moddedTimePairs[i][0] + " --> " + moddedTimePairs[i][1];
            newlist.add(line);
        }
    }
}
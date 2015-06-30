/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitledelay;

import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 *
 * @author Daniel Munkacsi
 */
public class ReplaceSubs {
    private final JTextArea outputArea;
    ArrayList<String> oldsubs;
    ArrayList<String> newsubs;
    ArrayList<String> lines;
    
    public ReplaceSubs(ArrayList<String> os, ArrayList<String> ns, ArrayList<String> lns, JTextArea opa){
        oldsubs = os;
        newsubs = ns;
        lines = lns;
        outputArea = opa;
    }
    
    public void replaceSubs(){
        SwingWorker swapsubs = new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() {
                modifySubs(newsubs,oldsubs,lines);
                return null;
            }
            
            @Override
            public void done() {
                outputArea.setText("");
                for(String s: lines){
                    outputArea.append(s + "\n");
                }
            }
        };
        swapsubs.execute();
    }
    
    /**
     * Modify subtitles with some changed data.
     * @param newTimesList the times to be written to file
     * @param oldTimesList the times to find in file for comparison
     * @param fileLines the lines from the file
     */
    private void modifySubs(ArrayList<String> newTimesList, ArrayList<String> oldTimesList, ArrayList<String> fileLines){
        for(int timeindex = 0; timeindex < oldTimesList.size(); timeindex++){
            int filelinesIndex = 0;
            for(String s: fileLines){
               if(s.equals(oldTimesList.get(timeindex))){
                   fileLines.set(filelinesIndex, newTimesList.get(timeindex));
                   break;
               }
               filelinesIndex++;
           }
       }
    }
}
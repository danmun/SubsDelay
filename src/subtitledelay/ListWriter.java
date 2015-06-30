/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitledelay;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Munkacsi
 */
public class ListWriter {
    
    private String path;
    
    public ListWriter(String p){
        path = p;
    }
    
    /**
     * Write data to file.
     * @param tobewritten   array list of strings to be written to file
     * @param mode  the operation mode, false for writing to file, true for appending to it
     */
    public void writeList(ArrayList<String> tobewritten, boolean mode){
        try{
            File file = new File(path);
            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }
            //true = append file
            FileWriter fw = new FileWriter(file.getName(),mode);
            BufferedWriter br = new BufferedWriter(fw);
            for(int i = 0; i < tobewritten.size(); i++){
                br.append(tobewritten.get(i));
                br.append(System.lineSeparator());
            }
            br.close();
            JOptionPane.showMessageDialog(null, "Subtitle file saved!");
    	}catch(IOException e){
            JOptionPane.showMessageDialog(null, "An error has occured while creating the new subtitle file!");
            return;
    	} 
    }
}
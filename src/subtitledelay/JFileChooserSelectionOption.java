/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitledelay;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Daniel Munkacsi
 */
class JFileChooserSelectionOption {
    
    private String path; 
    private boolean isCancelled = false;
    
    /**
     * Create a file chooser window.
     */
    public JFileChooserSelectionOption() {
        //if something is true then pass the two variables to comparator
        JFileChooser explorer = new JFileChooser(".");
        explorer.setFileSelectionMode(JFileChooser.FILES_ONLY);
        explorer.setDialogTitle("Directories");
        explorer.setApproveButtonText("Select");
    
        int status = explorer.showOpenDialog(null);

        if (status == JFileChooser.APPROVE_OPTION) {
            File selection = explorer.getSelectedFile();
            path = selection.getAbsolutePath();
            
        }else if (status == JFileChooser.CANCEL_OPTION) {
            isCancelled = true;
        }
    }
    
    public boolean isCancelled(){
        return isCancelled;
    }
    
    public String getPath(){
        return path;
    }
}
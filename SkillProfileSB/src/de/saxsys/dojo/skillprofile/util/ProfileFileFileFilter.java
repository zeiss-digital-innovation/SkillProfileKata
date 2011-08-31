package de.saxsys.dojo.skillprofile.util;

import java.io.File;
import java.io.FilenameFilter;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author marco.dierenfeldt
 */
public class ProfileFileFileFilter implements FilenameFilter{

    @Override
    public boolean accept(File dir, String name) {
       return name.endsWith(".profile");
    }
   
}

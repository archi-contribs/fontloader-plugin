/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */

package org.archicontribs.fontloader;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.archimatetool.editor.preferences.IPreferenceConstants;
import com.archimatetool.editor.preferences.Preferences;


/**
 * Activator
 * 
 * @author JB Sarrodie & Phil Beauvoir
 */
@SuppressWarnings("nls")
public class FontLoaderPlugin extends AbstractUIPlugin {
	public static final String PLUGIN_ID = "org.archicontribs.fontloader"; //$NON-NLS-1$
	
    /**
     * The shared instance
     */
    public static FontLoaderPlugin INSTANCE;
    
    public FontLoaderPlugin() {
        INSTANCE = this;
    }
    
    @Override
    
    public void start(BundleContext context) throws Exception {
        super.start(context);
        
        Display.getDefault().asyncExec(() -> {
            // Load fonts distributed with the plugin
            loadFonts(getFontsFolder());
            
            // Load user fonts
            loadFonts(getUserFontsFolder());
        });
    }
    
    // Scan a folder looking for fonts and load them
    private void loadFonts(File fontFolder) {
    	Display display = Display.getDefault();
    	
        if(fontFolder.exists()) {
            for(File font : fontFolder.listFiles()) {
                if(font.isFile()) {
                	display.loadFont(font.getAbsolutePath());
                }
            }
        }
    }
    
    /**
     * @return User fonts folder
     */
    public File getUserFontsFolder() {
        File folder = new File(Preferences.STORE.getString(IPreferenceConstants.USER_DATA_FOLDER), "fonts"); //$NON-NLS-1$
        folder.mkdirs();
        return folder;
    }
    
    /**
     * @return The plugin fonts folder
     */
    public File getFontsFolder() {
        URL url = FileLocator.find(getBundle(), new Path("$nl$/fonts"), null); //$NON-NLS-1$
        try {
            url = FileLocator.resolve(url);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return new File(url.getPath()); 
    }
    
    /**
     * @return The File Location of this plugin
     */
    public File getPluginFolder() {
        URL url = getBundle().getEntry("/");
        try {
            url = FileLocator.resolve(url);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        
        return new File(url.getPath());
    }
}

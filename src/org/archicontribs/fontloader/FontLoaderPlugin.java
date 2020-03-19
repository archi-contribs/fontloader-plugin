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
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;



/**
 * Activitor
 * 
 * @author JB Sarrodie
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
        
        final String fontPath = "/tmp/fonts/Archi-FontAwesome.ttf";
        
        Display.getDefault().asyncExec(() -> {
            boolean success = Display.getDefault().loadFont(fontPath);
            System.out.println(success ? "loaded" : "not loaded");
        });
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

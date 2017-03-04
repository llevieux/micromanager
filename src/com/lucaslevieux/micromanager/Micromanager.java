/*
 * Copyright 2017 Lucas LeVieux <contact@lucaslevieux.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lucaslevieux.micromanager;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * The main class for Micromanager.  main() makes a new instance of Micromanager,
 * which is a JFrame that contains the various JPanels.
 * 
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public class Micromanager extends JFrame{
    /**
     * Width of the Micromanager window in pixels.  The height depends on the 
     * preference of the main panel.
     */
    public static final int WIDTH = 300;
    
    /**
     * The x-coordinate on the screen that the micromanager is drawn.  
     * Calculated so that the frame on the far right side.  Since width is 
     * constant, this is constant (assuming a fixed screen size)
     */
    private final int X_POSITION;
    
    /**
     * The y-coordinate on the screen that the micromanager is drawn. Calculated 
     * so that the frame is at the bottom, just above the taskbar.  Must be updated
     * anytime the height is changed.
     */
    private static int Y_POSITION;
    
    /**
     * The amount of padding inside of the border.
     */
    private static final int PADDING = 10;
    
    /**
     * The wrapper that contains the content.  Also has a empty border 
     */
    private JPanel paddingWrapper;
    
    /**
     * The icon for the corner of the window, status bar, task switcher and
     * other places
     */
    private final Image icon = new ImageIcon(getClass().getResource(
            "/assets/Icon@64px.png")).getImage();

    /**
     * The current instance of micromanager.
     */
    static Micromanager instance;

    /**
     * Instantiates the main frame, and catches any exceptions in it with a neat
     * little error pane.
     * 
     * @param args command-line arguments (ignored)
     */
    public static void main (String... args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    //make the application look native
            instance = new Micromanager();
        } catch (Exception e) { //uh oh :(
            String trace = "";
            for (StackTraceElement t:e.getStackTrace())
                trace += t.toString() + "\n";
            
            JOptionPane.showMessageDialog(null, 
                    "Something weird happened and MicroManager had to quit.\n\n"
                    + "Nerd data: " + e + ", " + e.getMessage() + "\n" + trace, 
                    "So Sorry.", JOptionPane.ERROR_MESSAGE);
            
            System.exit(1);
        }
    }
    
    /**
     * Sets up the JFrame and its wrappers.
     * 
     * @throws Exception 
     */
    public Micromanager() throws Exception{
        super("MicroManager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true); //remove title bar and border
        setAlwaysOnTop(true);
        setIconImage(icon);
        
        //put the frame in the bottom right corner
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()); //height of the windows taskbar
        X_POSITION = scrSize.width - WIDTH; //the final value here
        Y_POSITION = scrSize.height - toolHeight.bottom;
        setLocation(X_POSITION, Y_POSITION);
        
        //wrap the content with a padding (ie an empty border JPanel)
        paddingWrapper = new JPanel();
        paddingWrapper.setLayout(new GridLayout());
        paddingWrapper.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        
        //wrap the padding in a border
        JPanel borderWrapper = new JPanel();
        borderWrapper.setLayout(new GridLayout());
        borderWrapper.setBorder(BorderFactory.createLineBorder(Color.black, 2, false));
        borderWrapper.add(paddingWrapper);
        
        //add the border, with the padding and content, to the JFrame
        add(borderWrapper);
        
        setVisible(true);
        
        //begin by showing the Start screen
        setMainPanel(new Start()); 
    }
    
    /**
     * Changes the content of the main frame to panel.  Also resizes and 
     * repositions the window according to the panel's prefered size.  Since
     * this method might take time, it would be a good idea to bring the frame
     * to focus after this is done.
     * 
     * Technically, this is achieved by setting the paddingWrapper, and then 
     * running a revalidate/repaint on the graphics manager.
     * 
     * @param panel the panel to replace the current one shown in the frame.
     */
    void setMainPanel(JPanel panel){
        paddingWrapper.removeAll();
        paddingWrapper.add(panel);
        
        //calculate the new height and position based on new panel prefered size
        int newHeight = (int)panel.getPreferredSize().getHeight();
        int oldHeight = this.getHeight();
        this.setSize(WIDTH, newHeight);
        
        //just subtract the change in height to get the new y
        Y_POSITION -= (newHeight - oldHeight);
        setLocation(X_POSITION, Y_POSITION);
        
        revalidate();
        repaint();
    }
    
    /**
     * Returns a random string of those passed.  Useful for messages that you
     * don't want to be the same every time.
     *
     * @param text strings that have an equal chance of being printed to the
     * screen.
     * @return one of the strings passed by text, selected randomly
     */
    public static String randomText(String... text) 
    {
        return text[new Random().nextInt(text.length)];
    }
}

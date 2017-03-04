package com.lucaslevieux.micromanager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public class Micromanager extends JFrame{
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
     * Instantiates the main frame, and catches any exceptions in it with a neat
     * little error pane.
     * 
     * @param args command-line arguments (ignored)
     */
    public static void main (String... args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    //make the application look native
            new Micromanager();
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
    
    public Micromanager() throws Exception{
        super("MicroManager");
        setSize(300, 170);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true); //remove title bar and border
        setAlwaysOnTop(true);
        setIconImage(icon);
        
        //put the frame in the bottom right corner
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        setLocation(scrSize.width - getWidth(), scrSize.height - getHeight() - toolHeight.bottom);
        
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
     * Changes the content of the main frame to panel.
     * 
     * Technically, this is achieved by setting the paddingWrapper, and then 
     * running a revalidate/repaint on the graphics manager.
     * 
     * @param panel the panel to replace the current one shown in the frame.
     */
    void setMainPanel(JPanel panel){
        paddingWrapper.removeAll();
        paddingWrapper.add(panel);
        revalidate();
        repaint();
    }
}

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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The timing function.  This panel minimizes itself, and counts down until the
 * next Nag or GoofOff.
 * 
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public class Time extends JPanel {
    /**
     * The height of the panel.
     */
    private final int HEIGHT = 100;
    
    /**
     * The panel that will be displayed as the main panel after the time runs 
     * out.  Used to make copy decisions based on instanceof.
     */
    private final JPanel nextPanel;
    
    /**
     * The current task being completed.  
     */
    private final String task;
    
    /**
     * The total amount of time, in minutes, before the nextPanel.
     */
    private final int time;
    
    /**
     * The timer object to update the countdown.
     */
    private final Timer timer;
    
    /**
     * How many seconds are left in the timer.
     */
    private int secondsLeft;
    
    private final JLabel countdown = new JLabel();
    
    /**
     * Constructor to define all the variables, as well 
     * @param nextPanel The panel that will be displayed as the main panel after 
     * the time runs out
     * @param task The current task being completed
     * @param time The total amount of time, in minutes before the nextPanel.
     */
    public Time(JPanel nextPanel, String task, int time){
        this.nextPanel = nextPanel;
        this.task = task;
        this.time = time;
        this.secondsLeft = time * 60;
        
        setPreferredSize(new Dimension(Micromanager.WIDTH, HEIGHT));
        countdown.setText(secondsLeft + ":00");
        countdown.setFont(new Font("Courier New", Font.BOLD, 60));
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        countdown.setAlignmentY(CENTER_ALIGNMENT);
        add(countdown);
        
        Micromanager.instance.setState(JFrame.ICONIFIED);
        Micromanager.instance.setAlwaysOnTop(false);
        
        timer = new Timer(1000, new CountdownListener());
        timer.start();
        
        
    }
    
    private class CountdownListener implements ActionListener{
        @Override public void actionPerformed(ActionEvent e) {
            secondsLeft -= 1;
            countdown.setText(
                    String.format("%d:%02d", (int)(secondsLeft / 60), secondsLeft % 60));
        }
    }
}

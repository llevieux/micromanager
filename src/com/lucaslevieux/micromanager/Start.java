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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The first screen that the user encounters. Asks if the user wants
 * to be productive or goof off.
 * 
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public class Start extends JPanel{
    
    JLabel question;
    
    /**
     * The height of the panel.
     */
    private final int HEIGHT = 170;
    
    public Start() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Micromanager.WIDTH, HEIGHT));
        question = new JLabel("<html><center>Are you trying to be productive?</center><html>");
        question.setFont(new Font(null, Font.BOLD, 25));
        question.setAlignmentX(CENTER_ALIGNMENT);
        question.setAlignmentY(CENTER_ALIGNMENT);
        add(question, BorderLayout.NORTH);
        add(new Buttons(), BorderLayout.SOUTH);
        
    }
    
    /**
     * A JPanel split horizontally with the two buttons. 
     */
    private class Buttons extends JPanel {
        public Buttons(){
            setPreferredSize(new Dimension(300, 50));
            setLayout(new GridLayout(1,2));
            JButton yesButton = new JButton("Yeah.");
            yesButton.setFont(new Font(null, Font.PLAIN, 20));
            JButton noButton = new JButton("Nah, I'm goofing off.");
            add(yesButton);
            add(noButton);
            
            yesButton.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    ((Micromanager)Start.this.getTopLevelAncestor()) //JFrame object
                            .setMainPanel(new Schedule());
                }
            });
            
            noButton.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            });
        }
    }
}

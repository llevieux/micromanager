/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucaslevieux.micromanager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public class Start extends JPanel{
    JLabel question;
    
    public Start() {
        setLayout(new BorderLayout());
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
            
        }
    }
}

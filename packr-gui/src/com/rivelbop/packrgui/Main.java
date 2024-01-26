package com.rivelbop.packrgui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// Creates the JFrame/GUI window
public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 400, HEIGHT = 400;
	
	public Main() {
		super("Packr GUI");
		
		JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(32, 32, 32, 32));
        setContentPane(contentPane);
        
        add(new JLabel("Welcome to Packr GUI!", JLabel.CENTER), BorderLayout.NORTH);
        add(new MainView());
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        setSize(WIDTH, HEIGHT); 
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        
        setLocationRelativeTo(null);
        
        pack();
        setVisible(true);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
	}
	
}
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
	public static final int WIDTH = 640, HEIGHT = 480;
	
	public Main() {
		super("Packr GUI");
		
		JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(32, 32, 32, 32));
        setContentPane(contentPane);
        
        add(new JLabel("Welcome to Packr GUI!", JLabel.CENTER), BorderLayout.NORTH);
        
        MainView mainView = new MainView();
        add(mainView);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        
        setSize(WIDTH, HEIGHT); 
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH * 2, HEIGHT * 2));
        
        setLocationRelativeTo(null);
        
        pack();
        setVisible(true);
        mainView.consoleWindow.setVisible(true);
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
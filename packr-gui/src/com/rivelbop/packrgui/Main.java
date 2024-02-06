package com.rivelbop.packrgui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.badlogicgames.packr.PackrConfig.Platform;

// Creates the JFrame/GUI window
public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final String OS = System.getProperty("os.name").toLowerCase();
	public static final int WIDTH = 640, HEIGHT = 480;
	public static final Platform PLATFORM = 
			OS.contains("windows") ? Platform.Windows64 :
				OS.contains("mac") ? Platform.MacOS : 
					Platform.Linux64;
	public static final String PATH_SEPARATOR = 
			PLATFORM == Platform.Windows64 ? "\\" : "/";
	
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
package com.rivelbop.packrgui;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

// GUI structure of the application
public class MainView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// GUI components
	private JLabel 
		appNameTitle,
		jarTitle, jarLocationLabel,
		mainClassTitle,
		/* TODO: ADD MORE LABELS HERE */
		OSLabel;
	private JButton jarButton;
	private JFileChooser jarChooser;
	
	// Packr parameters
	private JTextField appNameField, mainClassField;
	private String jarFile;
	
	public MainView() {
		super(new GridLayout(-1, 1));
        setBorder(new EmptyBorder(32, 32, 128, 32));
        addUI();
	}
	
	public void addUI() {
		// Application name components
		appNameTitle = new JLabel("Application Name:", JLabel.CENTER);
		appNameTitle.setFont(appNameTitle.getFont().deriveFont(Font.BOLD, 13f));
		add(appNameTitle);
		
        appNameField = new JTextField();
        add(appNameField);
        
        
        
        // Executable JAR location components
        jarTitle = new JLabel("Locate JAR Executable:", JLabel.CENTER);
        jarTitle.setFont(jarTitle.getFont().deriveFont(Font.BOLD, 13f));
        add(jarTitle);
        
        jarLocationLabel = new JLabel("No JAR Currently Selected", JLabel.CENTER);
        jarLocationLabel.setFont(jarLocationLabel.getFont().deriveFont(Font.ITALIC, 11f));
        add(jarLocationLabel);
        
        jarChooser = new JFileChooser();
        jarChooser.setFileFilter(new FileNameExtensionFilter(null, "jar"));
        
        jarButton = new JButton("Select JAR");
        jarButton.addActionListener(e -> {
        	if(jarChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        		jarFile = jarChooser.getSelectedFile().getAbsolutePath().replace(System.getProperty("user.dir"), "");
        		jarLocationLabel.setText(jarFile);
        		System.out.println("Selected JAR File: " + jarFile);
        	}
        });
        add(jarButton);
        
		
        
        // Main class path components
        mainClassTitle = new JLabel("Main Class Path:", JLabel.CENTER);
        mainClassTitle.setFont(mainClassTitle.getFont().deriveFont(Font.BOLD, 13f));
		add(mainClassTitle);
		
		mainClassField = new JTextField();
        add(mainClassField);
        
        
        
        // OS EXPORT NOTE
        OSLabel = new JLabel(System.getProperty("os.name"), JLabel.CENTER);
        OSLabel.setFont(OSLabel.getFont().deriveFont(Font.PLAIN, 13f));
        add(OSLabel);
    }
	
}
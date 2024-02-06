package com.rivelbop.packrgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;

import com.badlogicgames.packr.Packr;
import com.badlogicgames.packr.PackrConfig;

// GUI structure of the application
public class MainView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public JFrame consoleWindow;
	
	// GUI components
	private JLabel 
		appNameTitle,
		jarTitle, jarLocationLabel,
		mainClassTitle,
		jdkTitle, jdkLocationLabel,
		vmArgsTitle,
		outputTitle, outputLocationLabel,
		OSLabel;
	private JButton jarButton, jdkButton, vmArgsAddButton, vmArgsRemoveButton, outputButton, exportButton;
	private JFileChooser jarChooser, jdkChooser, outputChooser;
	
	// Packr parameters
	private JTextField appNameField, mainClassField;
	private JTable vmArgs;
	private String jarFile, jdkDir, outputDir;
	
	public MainView() {
		super(new GridLayout(-1, 1));
        setBorder(new EmptyBorder(32, 32, 0, 32));
        addUI();
	}
	
	public void addUI() {
		// Application name components
		appNameTitle = new JLabel("Application Name:", JLabel.CENTER);
		appNameTitle.setFont(appNameTitle.getFont().deriveFont(Font.BOLD, 13f));
		add(appNameTitle);
		
        appNameField = new JTextField();
        appNameField.setHorizontalAlignment(JTextField.CENTER);
        add(appNameField);
        
        add(new JLabel("------------------------------------------------", JLabel.CENTER));
        
        
        
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
        jarButton.addActionListener(l -> {
        	if(jarChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        		jarFile = jarChooser.getSelectedFile().getAbsolutePath().replace(System.getProperty("user.dir") + Main.PATH_SEPARATOR, "");
        		jarLocationLabel.setText(jarFile);
        		System.out.println("Selected JAR File: " + jarFile);
        	}
        });
        add(jarButton);
        
        add(new JLabel("------------------------------------------------", JLabel.CENTER));
        
        
        
        // Main class path components
        mainClassTitle = new JLabel("Main Class Path:", JLabel.CENTER);
        mainClassTitle.setFont(mainClassTitle.getFont().deriveFont(Font.BOLD, 13f));
		add(mainClassTitle);
		
		mainClassField = new JTextField();
		mainClassField.setHorizontalAlignment(JTextField.CENTER);
        add(mainClassField);
        
        add(new JLabel("------------------------------------------------", JLabel.CENTER));
        
        
        
        // JDK location components
        jdkTitle = new JLabel("Locate JDK:", JLabel.CENTER);
        jdkTitle.setFont(jdkTitle.getFont().deriveFont(Font.BOLD, 13f));
        add(jdkTitle);
        
        jdkLocationLabel = new JLabel("No JDK Currently Selected", JLabel.CENTER);
        jdkLocationLabel.setFont(jdkLocationLabel.getFont().deriveFont(Font.ITALIC, 11f));
        add(jdkLocationLabel);
        
        jdkChooser = new JFileChooser();
        jdkChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        jdkButton = new JButton("Select JDK");
        jdkButton.addActionListener(l -> {
        	if(jdkChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        		jdkDir = jdkChooser.getSelectedFile().getAbsolutePath().replace(System.getProperty("user.dir") + Main.PATH_SEPARATOR, "");
        		jdkLocationLabel.setText(jdkDir);
        		System.out.println("Selected JDK Directory: " + jdkChooser.getSelectedFile().getAbsolutePath());
        	}
        });
        add(jdkButton);
        
        add(new JLabel("------------------------------------------------", JLabel.CENTER));
        
        
        
        // VM Args components
        vmArgsTitle = new JLabel("Specify VM Arguments:", JLabel.CENTER);
        vmArgsTitle.setFont(vmArgsTitle.getFont().deriveFont(Font.BOLD, 13f));
        add(vmArgsTitle);
        
        vmArgs = new JTable(new DefaultTableModel(0, 1));
        vmArgs.setTableHeader(null);
        vmArgs.setBackground(new Color(225, 225, 225));
        
        JScrollPane vmArgsScrollPane = new JScrollPane(vmArgs);
        vmArgsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(vmArgsScrollPane);
        
        vmArgsAddButton = new JButton("Add VM Arg");
        vmArgsAddButton.addActionListener(l -> {
        	((DefaultTableModel)vmArgs.getModel()).addRow(new Vector<String>());
        });
        add(vmArgsAddButton);
        
        vmArgsRemoveButton = new JButton("Remove VM Arg");
        vmArgsRemoveButton.addActionListener(l -> {
        	if(vmArgs.getSelectedRow() != -1) {
        		((DefaultTableModel)vmArgs.getModel()).removeRow(vmArgs.getSelectedRow());
        	}
        });
        add(vmArgsRemoveButton);
        
        add(new JLabel("------------------------------------------------", JLabel.CENTER));
        
        
        
        // Output location components
        outputTitle = new JLabel("Select Output Directory:", JLabel.CENTER);
        outputTitle.setFont(outputTitle.getFont().deriveFont(Font.BOLD, 13f));
        add(outputTitle);
        
        outputLocationLabel = new JLabel("No Output Directory Selected", JLabel.CENTER);
        outputLocationLabel.setFont(outputLocationLabel.getFont().deriveFont(Font.ITALIC, 11f));
        add(outputLocationLabel);
        
        outputChooser = new JFileChooser();
        outputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        outputButton = new JButton("Select Output");
        outputButton.addActionListener(l -> {
        	if(outputChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        		outputDir = outputChooser.getSelectedFile().getAbsolutePath().replace(System.getProperty("user.dir") + Main.PATH_SEPARATOR, "");
        		outputLocationLabel.setText(outputDir);
        		System.out.println("Selected Output Directory: " + outputChooser.getSelectedFile().getAbsolutePath());
        	}
        });
        add(outputButton);
        
        add(new JLabel("------------------------------------------------", JLabel.CENTER));
        
        
        
        // Console components
        consoleWindow = new JFrame("Console");
        JTextArea console = new JTextArea();
        console.setBackground(Color.BLACK);
        console.setForeground(new Color(50, 205, 50));
        console.setEditable(false);
        
        PrintStream printStream = new PrintStream(new ConsoleOutputStream(console));
        System.setOut(printStream);
        System.setErr(printStream);
        
        JScrollPane consoleScrollPane = new JScrollPane(console);
        consoleScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        consoleWindow.add(consoleScrollPane);
        
        consoleWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        consoleWindow.setResizable(true);
        
        consoleWindow.setSize(Main.WIDTH, Main.HEIGHT); 
        consoleWindow.setMinimumSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        consoleWindow.setMaximumSize(new Dimension(Main.WIDTH * 2, Main.HEIGHT * 2));
        
        consoleWindow.setLocationRelativeTo(null);
        consoleWindow.pack();
        
        
        
        // Export components
        exportButton = new JButton("Export");
        exportButton.addActionListener(l -> {
        	if(!appNameField.getText().isEmpty() && !mainClassField.getText().isEmpty()) {
        		Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						PackrConfig config = new PackrConfig();
		        		config.platform = Main.PLATFORM;
		                config.jdk = jdkDir;
		                config.jrePath = jdkDir + Main.PATH_SEPARATOR +
		                		"Contents" + Main.PATH_SEPARATOR +
		                		"Home" + Main.PATH_SEPARATOR + "jre";
		                config.executable = appNameField.getText();
		                config.classpath = Arrays.asList(jarFile);
		                config.removePlatformLibs = config.classpath;
		                config.mainClass = mainClassField.getText();
		                
		                String[] args = new String[vmArgs.getRowCount()];
		                for(int r = 0; r < args.length; r++) {
		                	args[r] = (String) vmArgs.getModel().getValueAt(r, 0);
		                }
		                
		                config.vmArgs = Arrays.asList(args);
		                config.minimizeJre = "hard";
		                config.outDir = new java.io.File(outputDir + Main.PATH_SEPARATOR + "out-" + Main.PLATFORM.toString().toLowerCase());
		                config.useZgcIfSupportedOs = true;
		                
		                try {
		        			new Packr().pack(config);
		        		} catch (IOException | CompressorException | ArchiveException e) {
		        			e.printStackTrace();
		        		}
						
					}
        			
        		});
        		thread.start();
        	}
        });
        add(exportButton);
        
        
        
        // OS EXPORT NOTE
        OSLabel = new JLabel(Main.PLATFORM.toString(), JLabel.CENTER);
        OSLabel.setFont(OSLabel.getFont().deriveFont(Font.PLAIN, 13f));
        add(OSLabel);
    }
	
}
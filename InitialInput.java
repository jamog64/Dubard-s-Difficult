import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InitialInput extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<String> names = new ArrayList<String>();
	
	private JFrame nextFrame;
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton addName;
	private JButton done;
	private JButton restart;
	private JButton restart1;
	private JButton restart2;
	private JButton edit;
	
	private JFrame errorFrame;
//	private JLabel errorLabel;
//	private JPanel errorPanel;
	
	private int classSize;
	
	private static final int height = 25;
	private static final int length = 25;
	
	private JTextArea resultArea;

	public InitialInput() {
		resultArea = new JTextArea(height, length);
		resultArea.setText("");
		resultArea.setEditable(true);
		
		classSize = 0;
		createTextField();
		createButton();
		createPanel();
		this.setBackground(new Color(221,160,221));
	}
	
	private void createTextField() {
		final int fieldWidth = 10;
		
		nameLabel = new JLabel("Enter Name: ");
		nameField = new JTextField(fieldWidth);
		nameField.setText("");
	}
	
	private void createButton() {
		addName = new JButton("Add Name");
		ActionListener nameListener = new addName();
		addName.addActionListener(nameListener);
		
		done = new JButton("Done");
		ActionListener doneListener = new done();
		done.addActionListener(doneListener);
		
		restart = new JButton("Restart");
		ActionListener restartListener = new restart();
		restart.addActionListener(restartListener);
		
		restart1 = new JButton("Restart");
		ActionListener restart1Listener = new restart1();
		restart1.addActionListener(restart1Listener);
		
		restart2 = new JButton("Restart");
		ActionListener restart2Listener = new restart2();
		restart2.addActionListener(restart2Listener);
		
		edit = new JButton("Edit");
		ActionListener editListener = new edit();
		edit.addActionListener(editListener);
	}
	
	class addName implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String nameInput = nameField.getText();
			resultArea.append(nameInput + "\n");
			nameField.setText("");
		}
	}
		
	class done implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			resultArea.setEditable(false);
			addName.setEnabled(false);
			done.setEnabled(false);
			for (String line : resultArea.getText().split("\\n")) {
				names.add(line);
				classSize++;
			}
			
			if(classSize % 2 == 1) {
				//createErrorFrame();
			}
//			else {
				System.out.println(names);
				createNextFrame();
//			}
		}
	}
	
	class restart implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//nextFrame = new JFrame();
			//errorFrame = new JFrame();
			
			resultArea.setText("");
			resultArea.setEditable(true);
			addName.setEnabled(true);
			done.setEnabled(true);
			names = new ArrayList<String>();
			classSize = 0;
		}
	}
	
	class restart1 implements ActionListener {
		public void actionPerformed(ActionEvent event) {		
			resultArea.setText("");
			resultArea.setEditable(true);
			addName.setEnabled(true);
			done.setEnabled(true);
			errorFrame.dispose();
			names = new ArrayList<String>();
			classSize = 0;
		}
	}
	
	class restart2 implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//nextFrame = new JFrame();
			//errorFrame = new JFrame();
			
			resultArea.setText("");
			resultArea.setEditable(true);
			addName.setEnabled(true);
			done.setEnabled(true);
			nextFrame.dispose();
			names = new ArrayList<String>();
			classSize = 0;
		}
	}
	
	class edit implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			resultArea.setEditable(true);
			names = new ArrayList<String>();
			addName.setEnabled(true);
			done.setEnabled(true);
			nextFrame.dispose();
			//errorFrame.dispose();
		}
	}
	
	private void createPanel() {
		JPanel namePanel = new JPanel();
		
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		namePanel.add(addName);
		namePanel.setBackground(new Color(221,160,221));
		
		add(namePanel, BorderLayout.PAGE_START);
		
		JScrollPane scrollPane = new JScrollPane(resultArea);
		add(scrollPane, BorderLayout.CENTER);
		
		add(done, BorderLayout.PAGE_END);
		add(restart, BorderLayout.PAGE_END);
		add(edit, BorderLayout.PAGE_END);
	}

	private void createNextFrame() {
		JPanel instructions = new NamePanel(names);
		instructions.setBackground(new Color(221,160,221));
		nextFrame = new JFrame();
		
		nextFrame.setTitle("Dubard's Difficult Dilemma");
		nextFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nextFrame.setSize(1400, 800);
		nextFrame.add(instructions);
		nextFrame.add(restart2, BorderLayout.PAGE_END);
		nextFrame.setResizable(true);
		nextFrame.setVisible(true); 
	}
	
//	private void createErrorFrame() {
//		String error = "Error! Even number of people only";
//		errorLabel = new JLabel(error);
//		
//		errorPanel = new JPanel();
//		errorPanel.setBackground(new Color(221,160,221));
//		errorPanel.add(errorLabel, BorderLayout.CENTER);
//		errorPanel.add(restart1, BorderLayout.CENTER);
//		
//		errorFrame = new JFrame();
//		errorFrame.setTitle("Dubard's Difficult Dilemma");
//		errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		errorFrame.setSize(300, 275);
//		errorFrame.add(errorPanel);
//		errorFrame.setResizable(true);
//		errorFrame.setVisible(true); 
//	}
//	
//	private ArrayList<String> getNames() {
//		return names;
//	}

}


	

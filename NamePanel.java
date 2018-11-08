import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;


	public class NamePanel extends JPanel 
	{
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int ROWS;
	    private int COLUMNS;
	    private Button buttonfield[][];
	    private int[][] data;
	    private ArrayList<String> names;
	    private ArrayList<String> result;
	    //instance variables
	    
	    private JPanel instructions = new JPanel();
	    private JPanel information = new JPanel();
	    private JPanel boxGrid = new JPanel();
	    
	    private JFrame finalFrame;
	    private JPanel outputPanel;
	    private JTextArea outputArea;
	    private JLabel outputLabel;
	    
	   public NamePanel(ArrayList<String> input) {
	        ROWS = input.size();
	        COLUMNS = input.size()+1;
//	        int width = 53 * COLUMNS;
//   		int length = 28 * ROWS;
   			
   			names = input;
 			
   			boxGrid.setLayout(new GridLayout(ROWS, COLUMNS, 0, 0));
	        
   			if(input.size()<6) {
   				boxGrid.setPreferredSize(new Dimension(1000, 500));
   			} else if(input.size() < 12) {
   				boxGrid.setPreferredSize(new Dimension(1100, 550));
   			} else if(input.size() < 18) {
   				boxGrid.setPreferredSize(new Dimension(1200, 600));
   			} else if(input.size() < 24) {
   				boxGrid.setPreferredSize(new Dimension(1300, 650));
   			} else {
   				boxGrid.setPreferredSize(new Dimension(1450, 700));
   			}
	        
	        buildInformation();
	        buildInstructions();
	        
	        this.add(instructions, BorderLayout.PAGE_START);
	        this.add(boxGrid, BorderLayout.CENTER);
	        this.add(information, BorderLayout.PAGE_END);
	        
	        buttonfield = new Button[ROWS][COLUMNS];
	        buildButtonField();
	    }
	   //constructor (x is for resizability)
	    
	   public void buildInformation() { 
		   	JButton submit = new JButton("Submit");
		   	submit listener1 = new submit();
			submit.addActionListener(listener1);
			
			JButton cancel = new JButton("Clear");
		   	cancel listener2 = new cancel();
			cancel.addActionListener(listener2);
			
//			JButton cancel = new JButton("Restart");
//		   	cancel listener2 = new cancel();
//			cancel.addActionListener(listener2);
			
			information.add(submit);
			information.add(cancel);
			information.setBackground(new Color(221,160,221));
	   }
	   
	   public void buildInstructions() {
		   String string1 = "Enter up to 3 choices for each person";
		   String string2 = "(1st choice = 1 click = green, 2nd choice = 2 clicks = yellow, 3rd choice = 3 clicks = red)";
		   JLabel label1 = new JLabel(string1);
		   JLabel label2 = new JLabel(string2);
		   
		   instructions.add(label1);
		   instructions.add(label2);
		   instructions.setBackground(new Color(221,160,221));
	   }
	   
	   private void createTextArea() {
			outputArea = new JTextArea(25, 25);
			outputArea.setEditable(false);
			outputArea.setText("");
			
			for(int i=0; i<result.size(); i++) {
				outputArea.append("Pair " + (i+1) + ": " + result.get(i) + "\n");
			}
		}
	   
	   public void createFinalFrame() {
		   	finalFrame = new JFrame("Dubard's Difficult Dilemma");
			outputPanel = new JPanel();
			outputLabel = new JLabel("Optimized Pairings");
			
			createTextArea();
			JScrollPane scrollPane = new JScrollPane(outputArea);
	
			outputPanel.setBackground(new Color(221,160,221));
			outputPanel.add(outputLabel, BorderLayout.PAGE_START);
			outputPanel.add(scrollPane, BorderLayout.CENTER);

			finalFrame.setTitle("Dubard's Difficult Dilemma");
			finalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			finalFrame.setSize(385, 550);
			finalFrame.add(outputPanel);
			finalFrame.setResizable(false);
			finalFrame.setVisible(true); 
	   }
	   
	   class submit implements ActionListener {   
		   public void actionPerformed(ActionEvent event) {
			   int value;
			   data = new int[buttonfield.length][buttonfield[0].length-1];
			   for(int i=0; i<buttonfield.length; i++) {
				   for(int j=0; j<buttonfield[0].length; j++) {
					   value = buttonfield[i][j].getValue();
					   if(j>0) {
						   data[i][j-1] = (4-value)%4;
					   } 
					   buttonfield[i][j].setEnabled(false);
				   }
			   }
			   try {
					new DubardAlgorithm2(data, names);
					result = DubardAlgorithm2.getCorrectGrouping();
			   } catch (FileNotFoundException e) {
				   e.printStackTrace();
			   }
			   
			   createFinalFrame();
			}
		}
	   
	   class cancel implements ActionListener {   
		   public void actionPerformed(ActionEvent event) {
			   for(int r = 0; r < ROWS; r++){
		            for(int c = 0; c < COLUMNS; c++) {
		                buttonfield[r][c].value = 0;
		                buttonfield[r][c].setColor();
		                if(c>0) {
		                	buttonfield[r][c].setEnabled(true);
		                }
		            }
		        }
			}
		}
	   
	   public void buildButtonField() {
	       int k = 0;
	       int j = 0;
		   for(int r = 0; r < ROWS; r++){
	            for(int c = 0; c < COLUMNS; c++) {
	                if(c>0) {
	                	if(r == c-1) {
	                		buttonfield[r][c] = new Button(names.get(k));
	                		buttonfield[r][c].addActionListener(new ClickListener(r, c));
	                		buttonfield[r][c].setEnabled(false);
	                		boxGrid.add(buttonfield[r][c]);
			                k++;
	                	} else {
	                		buttonfield[r][c] = new Button(names.get(k));
		                	buttonfield[r][c].addActionListener(new ClickListener(r, c));
			                boxGrid.add(buttonfield[r][c]);
			                k++;
	                	}
	                } else {
	                	buttonfield[r][c] = new Button(names.get(j) + ": ");
	                	buttonfield[r][c].setEnabled(false);
		                boxGrid.add(buttonfield[r][c]);
		                j++;
	                }
	            }
	            k=0;
	        }
	    }
	   
	   public int[][] getData() {
		   return data;
	   }
	   
	   class ClickListener implements ActionListener {
	    		public int r, c; 
	    		
	    		public ClickListener(int r, int c) {
	    			this.r = r;
	    			this.c = c;
	    		}
	    	
	    		public void actionPerformed(ActionEvent event) {
	    			buttonfield[r][c].addClick();
	    			buttonfield[r][c].setColor();
	        	}
	   }
	   
	   class Button extends JButton {
		   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public int value;
		   public String name;
		   
		   public Button(String string) {
			   value = 0;
			   name = string;
			   this.setText(name);
			   //setPreferredSize(new Dimension(50,30));
			   setMargin(new Insets(0,0,0,0));
			   setFont(new Font("Arial", Font.PLAIN, 9));
		   }
		   
		   public void addClick() {
			   value++;
		   }
		   
		   public int getValue() {
			   return value;
		   }
		   
		   public void setColor() {
			   value = value%4;
			   
			   Color red = Color.RED;
			   Color yellow = new Color(255,255,0);
			   Color green = new Color(50,205,50);
			   
			   if(value == 0) {
				   this.setOpaque(false);
				   this.setForeground(Color.BLACK);
			   } else {
			   		this.setOpaque(true);
			   		if(value == 1) {
			   			this.setBackground(green);
			   			this.setForeground(green);
			   		} else if(value == 2) {
			   			this.setBackground(yellow);
			   			this.setForeground(yellow);
			   		} else {
			   			this.setBackground(red);
			   			this.setForeground(red);
			   		}
			   }
		   }
		   
	   }
	  
}
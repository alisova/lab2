
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Interface extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	File keyF=null,msgF=null,cphF=null;
	 // The input stream of bytes to be processed for encryption
	 private BufferedInputStream keyStream = null;

	 // The output stream of bytes to be procssed
	 private BufferedInputStream messageStream = null;
	 
	 // The output stream of bytes to be procssed
	 private BufferedOutputStream chiperStream = null; 
	 
	 private byte keyByte[] = null , messageByte[] = null , chiperByte[] = null;//read data from files into them
	


	public Interface() {
		setTitle("AES.2014.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500 ,400);
		setVisible(true);
		JLabel lblNewLabel = new JLabel("FILE key");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton(". . .");
		
		JLabel lblNewLabel_1 = new JLabel("File source text");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton(". . .");
		
		JLabel lblNewLabel_2 = new JLabel("Crypt/Decrypt file");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JButton btnNewButton_2 = new JButton(". . .");
		
		JButton btnNewButton_3 = new JButton("Crypt");
		
		JButton btnNewButton_4 = new JButton("Decrypt");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField_2, Alignment.LEADING)
								.addComponent(textField_1, Alignment.LEADING)
								.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_2)
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton)
								.addComponent(btnNewButton_3)
								.addComponent(btnNewButton_4))))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2))
					.addGap(18)
					.addComponent(btnNewButton_3)
					.addGap(18)
					.addComponent(btnNewButton_4)
					.addContainerGap(168, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		 // Choose file to input source
		btnNewButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){				
					JFileChooser fileopen = new JFileChooser();  
						int ret = fileopen.showDialog(null, "OPEN");
							if (ret == JFileChooser.APPROVE_OPTION) {
								keyF = fileopen.getSelectedFile(); 			   
								textField.setText(keyF.getAbsolutePath());	
				}}});
		
		 // Choose file to input source
		btnNewButton_1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){				
					JFileChooser fileopen = new JFileChooser();  
						int ret = fileopen.showDialog(null, "OPEN");
							if (ret == JFileChooser.APPROVE_OPTION) {
								msgF = fileopen.getSelectedFile(); 			   
								textField_1.setText(msgF.getAbsolutePath());	
				}}});
		
		 // Choose file to input source
		btnNewButton_2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){				
					JFileChooser fileopen = new JFileChooser();  
						int ret = fileopen.showDialog(null, "OPEN");
							if (ret == JFileChooser.APPROVE_OPTION) {
								cphF = fileopen.getSelectedFile(); 			   
								textField_2.setText(cphF.getAbsolutePath());	
				}}});
		
		 // Choose file to input source
		btnNewButton_3.addActionListener(new ActionListener(){
				@SuppressWarnings("unused")
				private AES aes;

				public void actionPerformed(ActionEvent ae){	

					aes = new AES();
					try
			         {
			             keyStream = new BufferedInputStream(new FileInputStream(keyF));
			        	 messageStream = new BufferedInputStream(new FileInputStream(msgF));
			         }
			         catch (IOException fnf)
			         {
			             System.err.println("Input file not found ["+keyF.getAbsolutePath()+"]");
			             System.exit(1);
			         }
			         try
			         {
			        	 chiperStream = new BufferedOutputStream(new FileOutputStream(cphF));
			         }
			         catch (IOException fnf)
			         {
			             System.err.println("Output file not created ["+cphF.getAbsolutePath()+"]");
			             System.exit(1);
			         }
			 //READ DATA FROM KEY FILE
			 		try 
			 		{
			 			int len = keyStream.available();
			 			keyByte = new byte [len];
			 			keyStream.read(keyByte,0,len);
			 			
			 			len = messageStream.available();
			 			messageByte=new byte [len];
			 			messageStream.read(messageByte,0,len);
			 			
			 		} 
			 		catch (IOException e1)
			 		{
			 			System.err.println ("Error in file source Buffered read ( of encrypt)");
			 			System.exit(1);
			 		}


			// CLOSE CONTEINER FILE 
			 	try 
			 	{
			 		
					//aes.setKey(keyByte);
					//System.out.println(messageByte.length);
				//	if (messageByte.length==0)return ;
				//	String a = messageByte.toString();
				//	String a = new String (messageByte);
				//	System.out.println("en a="+a);
				chiperByte = AES.encrypt(messageByte,keyByte);					
					//String b = aes.Encrypt(a);
				//	System.out.println("en b="+b);
				chiperStream.write(chiperByte);
					//System.out.println("en b="+b);
				//	chiperStream.write(b.getBytes());				
			 		chiperStream.flush();
			 		chiperStream.close();
			 	} 
			 	catch (IOException e)
			 	{
			 		System.err.println ("Error of close container file");
			 		System.exit(1);
			 	}
				 //CLOSE SOURCE FILE	
			 	try 
			 	{
			 		keyStream.close();
			 	} 
			 	catch (IOException e) 
			 	{
			 		System.err.println ("Error of close container file");
			 		System.exit(1);
			 	}
			 //CLOSE CONTAINER FILE
			 	try 
			 	{
			 		messageStream.close();
			 	} 
			 	catch (IOException e) 
			 	{
			 		System.err.println ("Error of close source file");
			 		System.exit(1);
			 	}


				}});
		
		 // Choose file to input source
			btnNewButton_4.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){	

						@SuppressWarnings("unused")
						AES aes = new AES();
						
						try
				         {
				             keyStream = new BufferedInputStream(new FileInputStream(keyF));
				         }
				         catch (IOException fnf)
				         {
				             System.err.println("Input file not found ["+keyF.getAbsolutePath()+"]");
				             System.exit(1);
				         }
				
				         try
				         {
				        	 messageStream = new BufferedInputStream(new FileInputStream(msgF));
				         }
				         catch (IOException fnf)
				         {
				             System.err.println("Output file not created ["+msgF.getAbsolutePath()+"]");
				             System.exit(1);
				         }
				         try
				         {
				        	 chiperStream = new BufferedOutputStream(new FileOutputStream(cphF));
				         }
				         catch (IOException fnf)
				         {
				             System.err.println("Output file not created ["+cphF.getAbsolutePath()+"]");
				             System.exit(1);
				         }
				 //READ DATA FROM KEY FILE
				 		try 
				 		{
				 			int len = keyStream.available();
				 			keyByte = new byte [len];
				 			keyStream.read(keyByte,0,len);
				 			
				 		} 
				 		catch (IOException e1)
				 		{
				 			System.err.println ("Error in file source Buffered read ( of encrypt)");
				 			System.exit(1);
				 		}
				 //READ DATA FROM MESSAGE FILE
				 		try 
				 		{
				 			int len = messageStream.available();
				 			messageByte=new byte [len];
				 			messageStream.read(messageByte,0,len);
				 		} 
				 		catch (IOException e1)
				 		{
				 			System.err.println ("Error in file container Buffered read ( of encrypt)");
				 			System.exit(1);						 
				 		}

				 //CLOSE CONTEINER FILE 
				 	try 
				 	{
					//	aes.setKey(keyByte);
					//	String a = messageByte.toString();
						chiperByte = AES.decrypt(messageByte,keyByte);
					//	String b =aes.Decrypt(a);	
					//	System.out.println("dec b="+b);
					//	data = aes.Decrypt(data);
				 		chiperStream.write(chiperByte);
						
						//chiperStream.write(b.getBytes());
				 		chiperStream.flush();
				 		chiperStream.close();
				 	} 
				 	catch (IOException e)
				 	{
				 		System.err.println ("Error of close container file");
				 		System.exit(1);
				 	}
					 //CLOSE SOURCE FILE	
				 	try 
				 	{
				 		keyStream.close();
				 	} 
				 	catch (IOException e) 
				 	{
				 		System.err.println ("Error of close container file");
				 		System.exit(1);
				 	}
				 //CLOSE CONTAINER FILE
				 	try 
				 	{
				 		messageStream.close();
				 	} 
				 	catch (IOException e) 
				 	{
				 		System.err.println ("Error of close source file");
				 		System.exit(1);
				 	}		
						

					}});
		
		
	}
}

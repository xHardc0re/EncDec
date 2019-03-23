import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class MainFrame {

	private JFrame frmEncdec;
	private JTextField txtDevelopedByOmada;
	private static JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmEncdec.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	public File choose()
	{
        JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files (.txt)", "txt");
        file.setFileFilter(filter);
        int returnVal = file.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        }
       return file.getSelectedFile();
	}
	
    public static char cipher(char c, int k) {
        // helping variables
        final int alphaLength = 26;
        final char asciiShift = Character.isUpperCase(c) ? 'A' : 'a';
        final int cipherShift = k % alphaLength;

        // 0 to 25
        char shifted = (char) (c - asciiShift);
        shifted = (char) ((shifted + cipherShift + alphaLength) % alphaLength);
        return (char) (shifted + asciiShift);
    }

    // rotation
    public static String cipher(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(cipher(s.charAt(i), k));
        }
        return sb.toString();
    }
    
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmEncdec = new JFrame();
		frmEncdec.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\Programs\\Eclipse\\Projects\\Images\\EncDec\\1.png"));
		frmEncdec.setResizable(false);
		frmEncdec.setTitle("Encrypt Decrypt");
		frmEncdec.setBounds(100, 100, 540, 375);
		frmEncdec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEncdec.getContentPane().setLayout(null);
		
		txtDevelopedByOmada = new JTextField();
		txtDevelopedByOmada.setText("Developed by Omada Piravlos");
		txtDevelopedByOmada.setBounds(0, 326, 534, 20);
		frmEncdec.getContentPane().add(txtDevelopedByOmada);
		txtDevelopedByOmada.setColumns(10);
		
		JButton btnNewButton = new JButton("Κρυπτογράφιση");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setIcon(new ImageIcon("F:\\Programs\\Eclipse\\Projects\\Images\\EncDec\\2.png"));
		btnNewButton.setBounds(189, 130, 174, 23);
		frmEncdec.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					File x=choose();
					String text = textField.getText();
					int key = Integer.parseInt(text);
					InputStream inStr = new FileInputStream(x.getName());
					OutputStream outStr = new FileOutputStream("Encrypted.txt");
					EncryptDecrypt cipher = new EncryptDecrypt(key);
					cipher.encrypt(inStr, outStr);
					inStr.close();
					outStr.close();
					x.delete();
				}catch(IOException e){
					System.out.println("Error "+e);
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Αποκρυπτογράφιση");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(new ImageIcon("F:\\Programs\\Eclipse\\Projects\\Images\\EncDec\\3.png"));
		btnNewButton_1.setBounds(189, 167, 174, 23);
		frmEncdec.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					File x=choose();
					String text = textField.getText();
					int key = Integer.parseInt(text);
					InputStream inStr = new FileInputStream(x.getName());
					OutputStream outStr = new FileOutputStream("Decrypted.txt");
					EncryptDecrypt cipher = new EncryptDecrypt(key);
					cipher.decrypt(inStr, outStr);
					inStr.close();
					outStr.close();
					x.delete();
				}catch(IOException e){
					System.out.println("Error "+e);
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Κλειδί (από 1-25)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(200, 64, 154, 43);
		frmEncdec.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(6, 16, 138, 20);
		panel.add(textField);
		textField.setColumns(10);
	}
}

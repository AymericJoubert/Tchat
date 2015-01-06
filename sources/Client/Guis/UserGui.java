package Client.Guis;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

public class UserGui implements ActionListener, Gui {
	private String name;
	private JFrame gui;
	private JPanel jp;
	private JButton jbSend;
	private JTextArea jtSend;
	private JTextArea discussionArea;
	PrintWriter out;
	BufferedReader in;

	public UserGui(String name, Socket server){
		this.name = name;
		gui = new JFrame(name);
		jbSend = new JButton("Envoyer");
		jtSend = new JTextArea();
		discussionArea = new JTextArea();
		try {
			out = new PrintWriter(server.getOutputStream());
			in  = new BufferedReader(new InputStreamReader(server.getInputStream()));
		}
		catch(Exception ex){
			ex.printStackTrace();
			try {
				out.close();
				in.close();
			}catch(Exception ee){}
			System.exit(1);
		}
		makeGui();
	}

	public void makeGui() {
		gui.setResizable(true);
		gui.setExtendedState(gui.MAXIMIZED_BOTH);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creating gui content
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		
		// Container images
		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
		JPanel jpContact = new JPanel();
		JPanel jpFifou = new JPanel();
		jpContact.setPreferredSize(new Dimension(100,100));
		jpFifou.setPreferredSize(new Dimension(100,100));
		jp.add(jpContact);
		jp.add(jpFifou);
		jp.setBackground(Color.GREEN);
		content.add(jp, BorderLayout.WEST);

		// Container Discussion
		jp = new JPanel();
		discussionArea.setPreferredSize(new Dimension(700,500));
		discussionArea.setEditable(false);
		jp.add(discussionArea);

//		jp.setBackground(Color.BLACK);
		content.add(jp, BorderLayout.CENTER);

		// TextField
		jp = new JPanel(new FlowLayout());
		jbSend.addActionListener(this);
		jtSend.setPreferredSize(new Dimension(600,200));
		jbSend.setSize(50,50);
		jp.add(jtSend);
		jp.add(jbSend);
//		jp.setBackground(Color.GREEN);
		content.add(jp, BorderLayout.SOUTH);
		

		gui.setContentPane(content);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(gui);
			gui.pack();
		}
		catch(Exception fifou){

		}
		gui.setVisible(true);

		try {
			String line;
			line = in.readLine();
			while (!line.equals("stop")) {
				System.out.println("J'ai reçu : " + line);
				discussionArea.setText(discussionArea.getText() + "\n" + line);
				line = in.readLine();
			}
		}
		catch(Exception eee){
			eee.printStackTrace();
		}
		finally {
			try {
				in.close();
				out.close();
			}
			catch(Exception fre){}
		}
	}

	public void actionPerformed(ActionEvent e){

		if (e.getSource() == jbSend) {
			// Recuperation des données et envoi au serveur
			if (!jtSend.getText().equals("")){
				discussionArea.setText(discussionArea.getText() + "\n" + jtSend.getText());
				System.out.println("J'envoi : " + jtSend.getText());
				out.println(jtSend.getText());
				out.flush();
				jtSend.setText("");
			}
		}
	}

}

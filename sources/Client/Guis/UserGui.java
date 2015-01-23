package Client.Guis;

import Client.Chat.ClientTchat;
import Client.Chat.User;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class UserGui implements ActionListener, Gui {
	private boolean waiting;
	private String name;
	private ClientTchat clientTchat;
	private User user;
	private JFrame gui;
	private JPanel jp;
	private JPanel contactList;
	private JButton jbSend;
	private JTextArea jtSend;
	private JTextArea discussionArea;
	PrintWriter out;
	BufferedReader in;

	public UserGui(String name, Socket server, ClientTchat clientTchat, User user){
		this.name = name;
		this.clientTchat = clientTchat;
		this.waiting = false;
		this.user = user;
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
		// Get current screen size (in case of multiple monitors)
		double width = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
		double height = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;

		gui.setResizable(true);
		gui.setExtendedState(Frame.MAXIMIZED_BOTH);
		gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Creating gui content
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());

		// Contact infos
		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
		JPanel jpImageContact = new JPanel();
		jpImageContact.setBackground(Color.BLUE);
		jpImageContact.setPreferredSize(new Dimension((int)(0.12*width),(int)(0.2*height)));
		jp.add(jpImageContact);

		jp.setBackground(Color.GREEN);
		jpImageContact.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		content.add(jp, BorderLayout.EAST);

		// Contact List
		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.setBackground(Color.RED);
//		jp.setPreferredSize(new Dimension(200,500));
		JTextField jtf = new JTextField("Contacts");
		jtf.setEditable(false);
		jtf.setPreferredSize(new Dimension(200,30));
		contactList = new JPanel();
		contactList.setLayout(new BoxLayout(contactList, BoxLayout.Y_AXIS));
		jp.add(jtf);
		jp.add(contactList);
		content.add(jp, BorderLayout.WEST);


		// Container Discussion
		jp = new JPanel();
		discussionArea.setPreferredSize(new Dimension((int) (0.55 * width), (int) (0.6 * height)));
		discussionArea.setEditable(false);
		discussionArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		jp.add(discussionArea);

//		jp.setBackground(Color.BLACK);
		content.add(jp, BorderLayout.CENTER);

		// TextField
		jp = new JPanel(new FlowLayout());
		JPanel jpImageFifou = new JPanel();
		jpImageFifou.setBackground(Color.BLACK);
		jpImageFifou.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		jpImageFifou.setPreferredSize(new Dimension((int) (0.12 * width), (int) (0.2 * height)));
		jbSend.addActionListener(this);
		jbSend.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		jtSend.setPreferredSize(new Dimension((int) (0.61 * width), (int) (0.2 * height)));
		jp.add(jpImageFifou);
		jp.add(jtSend);
		jp.add(jbSend);
//		jp.setBackground(Color.GREEN);
		content.add(jp, BorderLayout.SOUTH);

		gui.setJMenuBar(createMenu());

		gui.setContentPane(content);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(gui);
			gui.pack();
		}
		catch(Exception fifou){

		}
//		try {
//			String line;
//			line = in.readLine();
//			while (!line.equals("stop")) {
//				System.out.println("J'ai reçu : " + line);
//				discussionArea.setText(discussionArea.getText() + "\n" + line);
//				line = in.readLine();
//			}
//		}
//		catch(Exception eee){
//			eee.printStackTrace();
//		}
//		finally {
//			try {
//				in.close();
//				out.close();
//			}
//			catch(Exception fre){}
//		}
	}

	public void setVisible(boolean visible){
		gui.setVisible(visible);
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

	public void setContactList(String contacts){
		System.out.println(contacts);
		String [] contactListString = contacts.split("###");
		for(String currentContactString : contactListString){
			String[] currentContact = currentContactString.split("%%%");
			contactList.add(new JLabel(currentContact[1]));
		}
		contactList.repaint();
	}

	public JMenuBar createMenu() {
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;
		menuBar = new JMenuBar();
		menu = new JMenu("A Menu");
		menuBar.add(menu);
		menuItem = new JMenuItem("A menu item");
		menu.add(menuItem);
		menuItem = new JMenuItem("Another menu item");
		menu.add(menuItem);

		return menuBar;
	}

	public void close() {
		gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
	}

	public void popupMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void stopWaiting(boolean resetContainer){
		if(waiting){
		}
	}

}

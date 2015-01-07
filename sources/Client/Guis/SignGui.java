package Client.Guis;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SignGui extends Thread implements ActionListener, Gui {

    // Connection infos
    InetAddress address;
    int port;

    // Register Panel
    private JTextField registerUsername;
    private JTextField registerPassword;
    private JTextField registerPasswordConf;
    private JTextField registerEmail;
    private TitledBorder registerUsernameBorder;
    private TitledBorder registerPasswordBorder;
    private TitledBorder registerPasswordConfBorder;
    private TitledBorder registerEmailBorder;
    private JButton registerButton;

    // Login Panel
    private JTextField loginUsername;
    private JTextField loginPassword;
    private TitledBorder loginUsernameBorder;
    private TitledBorder loginPasswordBorder;
    private JButton loginButton;

    private JPanel container;
    private JPanel credentialsContainer;

    private ImageIcon loadingImg;

    public SignGui(InetAddress address, int port) {
        this.address = address;
        this.port = port;
        try {
            String imgLoading = System.getProperty("user.dir") + File.separator + "sources" + File.separator + "Client" + File.separator + "images" + File.separator + "loading.gif";
            System.out.println(imgLoading);
            loadingImg = new ImageIcon(new ImageIcon(imgLoading).getImage().getScaledInstance(230, 300, Image.SCALE_DEFAULT));
        }
        catch(Exception imgE){
            System.out.println("An error occured when generating images");
        }

        JFrame gui = new JFrame();
        container = new JPanel(new BorderLayout());
        credentialsContainer = new JPanel(new FlowLayout());
        JPanel loginContainer = new JPanel();
        JPanel registerContainer = new JPanel();
        loginContainer.setLayout(new BoxLayout(loginContainer, BoxLayout.Y_AXIS));
        registerContainer.setLayout(new BoxLayout(registerContainer, BoxLayout.Y_AXIS));

        // Setting Menu Bar
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

        // Setting Login Container
        loginUsername = new JTextField();
        loginPassword = new JPasswordField();
        loginButton = new JButton("Login");
        loginUsernameBorder = new TitledBorder("Username");
        loginPasswordBorder = new TitledBorder("Password");
        loginUsername.setBorder(loginUsernameBorder);
        loginPassword.setBorder(loginPasswordBorder);
        loginButton.setPreferredSize(new Dimension(300, 50));
        loginContainer.add(loginUsername);
        loginContainer.add(loginPassword);
        loginContainer.add(loginButton);

        // Setting Register Container
        registerUsername = new JTextField();
        registerPassword = new JPasswordField();
        registerPasswordConf = new JPasswordField();
        registerEmail = new JTextField();
        registerButton = new JButton("Register");
        registerUsernameBorder = new TitledBorder("Username");
        registerPasswordBorder = new TitledBorder("Password");
        registerPasswordConfBorder = new TitledBorder("Password Confirmation");
        registerEmailBorder = new TitledBorder("Email");
        registerUsername.setBorder(registerUsernameBorder);
        registerPassword.setBorder(registerPasswordBorder);
        registerPasswordConf.setBorder(registerPasswordConfBorder);
        registerEmail.setBorder(registerEmailBorder);
        registerUsername.setPreferredSize(new Dimension(300,50));
        registerContainer.add(registerUsername);
        registerContainer.add(registerPassword);
        registerContainer.add(registerPasswordConf);
        registerContainer.add(registerEmail);
        registerContainer.add(registerButton);

        // Setting Credentials Container
        credentialsContainer.add(loginContainer);
        credentialsContainer.add(registerContainer);

        // Listeners
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        // Setting full container and create gui
        container.add(credentialsContainer, BorderLayout.CENTER);
        gui.setContentPane(container);
        gui.setJMenuBar(menuBar);
        gui.setResizable(false);
        gui.setSize(new Dimension(800, 600));
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(gui);
//            gui.pack();
        }
        catch(Exception e){
            System.out.println("An error occured when creating sign gui");
            e.printStackTrace();
        }
//        gui.setBackground(new Color(0,175,240));
        gui.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == loginButton) {
            System.out.println("Connection");
        }
        if(e.getSource() == registerButton) {
            System.out.println("Inscription ...");
            if(!registerUsername.getText().equals("") && !registerPassword.getText().equals("") && !registerEmail.getText().equals("")) {
                if (registerPassword.getText().equals(registerPasswordConf.getText())) {
                    container.remove(credentialsContainer);
                    JLabel image = new JLabel(loadingImg);
                    container.add(new JLabel("Inscription ..."), BorderLayout.NORTH);
                    container.add(image, BorderLayout.CENTER);
                    container.revalidate();

                    inscription(registerUsername.getText(), registerPassword.getText(), registerEmail.getText());
                }
                else{
                    registerUsernameBorder.setTitleColor(null);
                    registerPasswordBorder.setTitleColor(Color.RED);
                    registerPasswordConfBorder.setTitleColor(Color.RED);
                    registerEmailBorder.setTitleColor(null);
                    container.add(new JLabel("Password Error"), BorderLayout.NORTH);
                    container.revalidate();
                }
            }
            else {
                container.add(new JLabel("Fields are not all filled."), BorderLayout.NORTH);
                container.revalidate();
            }
        }
    }

    public String [] inscription (String username, String password, String email){
        Socket connection = null;
        String [] ret = null;
        try {
            connection = new Socket(address, port);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out.println("register###"+username+"###"+password+"###"+email);
            out.flush();
            return in.readLine().split("###");
        }
        catch(Exception e){
            System.out.println("An error occured during registration");
            try {
                connection.close();
            }
            catch(Exception ee){}
            return new String [0];
        }
    }

}

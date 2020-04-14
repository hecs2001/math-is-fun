package mathtutorial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 *     Math Tutorial
 *     Submitted by the Group Members from BCS 13:
 *         Valdez, Hector Liam
 *         Villaluz, Ryan Bryle
 */

public class MathTutorial extends JFrame{
    String startAtDifficulty;
        // Generate Color (Red, Green, Blue)
    Color SlateGreen = new Color(47, 79, 79);
    Color GoldenOak = new Color(187, 129, 65);
        // Components
    JPanel body = new JPanel();
    JButton jbStart = new JButton(new ImageIcon("src/resources/other-icons/start-button.png"));
    JButton jbEasy = new JButton(new ImageIcon("src/resources/other-icons/easy-button.png"));
    JButton jbMedium = new JButton(new ImageIcon("src/resources/other-icons/medium-button.png"));
    JButton jbHard = new JButton(new ImageIcon("src/resources/other-icons/hard-button.png"));
    JButton jbExit = new JButton(new ImageIcon("src/resources/other-icons/exit-button.png"));
    JLabel title = new JLabel(new ImageIcon("src/resources/other-icons/title-text.png"));
    JLabel plus = new JLabel(new ImageIcon("src/resources/operators/plus.png"));
    JLabel minus = new JLabel(new ImageIcon("src/resources/operators/minus.png"));
    JLabel times = new JLabel(new ImageIcon("src/resources/operators/times.png"));
    JLabel divide = new JLabel(new ImageIcon("src/resources/operators/divide.png"));
    modeDialog dialogBox = new modeDialog();
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MathTutorial(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(700, 400);
        setTitle("Math Tutorial");
        setUndecorated(true);
        Container Form = getContentPane();
        Form.setLayout(null);
        ToolTipManager.sharedInstance().setInitialDelay(20);
        ToolTipManager.sharedInstance().setDismissDelay(120000); 
            // jPanel Body
        Form.add(body);
        body.setBackground(SlateGreen);
        body.setBorder(BorderFactory.createLineBorder(GoldenOak, 10));
        body.setBounds(0, 0, 700, 400);
        body.setLayout(null);
            // jButton Start
        body.add(jbStart);
        jbStart.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String startFile = jbStart.getIcon().toString();
                if(startFile.contains("src/resources/other-icons/start-button.png")){
                    dialogBox.setVisible(true);
                    jbStart.setIcon(new ImageIcon("src/resources/other-icons/cancel-button.png"));
                } else {
                    dialogBox.setVisible(false);
                    jbStart.setIcon(new ImageIcon("src/resources/other-icons/start-button.png"));
                }
            }
        });
        jbStart.setBackground(SlateGreen);
        jbStart.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        jbStart.setBounds(40, 200, 160, 60);
            // jButton Exit
        body.add(jbExit);
        jbExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        jbExit.setBackground(SlateGreen);
        jbExit.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        jbExit.setBounds(40, 280, 160, 60);
            // jLabel "Math Tutorial"
        body.add(title);
        title.setBounds(45, 70, 300, 75);
            // jLabel "+"
        body.add(plus);
        plus.setBounds(450, 80, 120, 120);
            // jLabel "-"
        body.add(minus);
        minus.setBounds(550, 150, 120, 120);
            // jlabel "x"
        body.add(times);
        times.setBounds(380, 210, 120, 120);
            // jLabel "%"
        body.add(divide);
        setLocationRelativeTo(null);
        divide.setBounds(520, 250, 120, 120);
    }
    
    public class modeDialog extends JDialog {
        @SuppressWarnings("OverridableMethodCallInConstructor")
        public modeDialog(){
            setLayout(null);
            setResizable(false);
            setSize(220, 300);
            setTitle("Select Difficulty");
            
            addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e){
                    jbStart.setIcon(new ImageIcon("src/resources/other-icons/start-button.png"));
                };
            });
                //  jButton Easy
            add(jbEasy);
            jbEasy.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    modeSelectAtStart(e);
                }
            });
            jbEasy.setBounds(20, 20, 160, 60);
            jbEasy.setBorderPainted(false);
            jbEasy.setToolTipText("<html><h2>Easy Mode<h4>Two numbers will generate<br>ranging from 1-10</html>");
                //  jButton Medium
            add(jbMedium);
            jbMedium.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    modeSelectAtStart(e);
                }
            });
            jbMedium.setBounds(20, 100, 160, 60);
            jbMedium.setBorderPainted(false);
            jbMedium.setToolTipText("<html><h2>Medium Mode<h4>Two numbers will generate<br>ranging from 1-20</html>");
                //  jButton Hard
            add(jbHard);
            jbHard.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    modeSelectAtStart(e);
                }
            });
            jbHard.setBounds(20, 180, 160, 60);
            jbHard.setBorderPainted(false);
            jbHard.setToolTipText("<html><h2>Hard Mode<h4>Two numbers will generate<br>ranging from 1-30</html>");
            setLocationRelativeTo(null);
        }
    }
    
    public void modeSelectAtStart(ActionEvent e){
        if(e.getSource() == jbEasy)
            startAtDifficulty = "easy";
        if(e.getSource() == jbMedium)
            startAtDifficulty = "medium";
        if(e.getSource() == jbHard)
            startAtDifficulty = "hard";
        dialogBox.dispose();
        new TutorialWindow(startAtDifficulty).setVisible(true);
        dispose();
    }
    
    public static void main(String[] args) {
        new MathTutorial().setVisible(true);
    }
}
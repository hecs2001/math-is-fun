package mathtutorial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public final class TutorialWindow extends JFrame{
    // Public Variables
    boolean plusEnabled, minusEnabled, timesEnabled, divideEnabled;
    char operatorOf, selectMode;
    int operatorActive, selectOperator, questionNum, questionKey, responseNum, responseKey, numQstn1, numQstn2, numAns1, numAns2, numAns3, numAns4, numAns5, numAns6, correctAnswer, tries;
    String operatorSelect, operatorFile, difficulty, inputAnswer, result, response;
    Random generateRandom = new Random();
        // Generate Color (Red, Green, Blue)
    Color darkSlateGreen = new Color(38, 63, 63);
    Color SlateGreen = new Color(47, 79, 79);
    Color GoldenOak = new Color(187, 129, 65);
        // JPanel Components
    JPanel jpBody = new JPanel();
    JPanel jpBlackboard = new JPanel();
    JPanel jpBottomBox = new JPanel();
    JPanel jpAnswerBox = new JPanel();
        // JButton Components
    JButton jbExit = new JButton(new ImageIcon("src/resources/other-icons/exit-50px.png"));
    JButton jbChangeMode = new JButton(new ImageIcon("src/resources/other-icons/change-50px.png"));
    JButton jbRefresh = new JButton(new ImageIcon("src/resources/other-icons/refresh-50px.png"));
    JButton jbEasy = new JButton(new ImageIcon("src/resources/other-icons/easy-button.png"));
    JButton jbMedium = new JButton(new ImageIcon("src/resources/other-icons/medium-button.png"));
    JButton jbHard = new JButton(new ImageIcon("src/resources/other-icons/hard-button.png"));
    JButton jbPlus = new JButton(new ImageIcon("src/resources/operators/plus-block.png"));
    JButton jbMinus = new JButton(new ImageIcon("src/resources/operators/minus-block.png"));
    JButton jbTimes = new JButton(new ImageIcon("src/resources/operators/times-block.png"));
    JButton jbDivide = new JButton(new ImageIcon("src/resources/operators/divide-block.png"));
    JButton jbToggleAll = new JButton(new ImageIcon("src/resources/other-icons/select-all-block.png"));
    JButton jbCheckAnswer = new JButton(new ImageIcon("src/resources/other-icons/check-block.png"));
        // JLabel Components
    JLabel jlDifficulty = new JLabel(new ImageIcon("src/resources/other-icons/difficulty-text.png"));
    JLabel jlMode = new JLabel();
    JLabel jlNum1 = new JLabel();
    JLabel jlOperator = new JLabel();
    JLabel jlNum2 = new JLabel();
    JLabel jlEquals = new JLabel(new ImageIcon("src/resources/other-icons/equals.png"));
    JLabel jlAnswer = new JLabel();
    JLabel jlAns1 = new JLabel();
    JLabel jlAns2 = new JLabel();
    JLabel jlAns3 = new JLabel();
    JLabel jlAns4 = new JLabel();
    JLabel jlAns5 = new JLabel();
    JLabel jlAns6 = new JLabel();
        // Dialog Box
    modeDialog dialogBox = new modeDialog();

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public TutorialWindow(){
        setupComponents();
    }
    
    public TutorialWindow(String startAtDifficulty){
        setupComponents();
        difficulty = startAtDifficulty;
        changeDifficulty();
        changeOperator();
    }
    
    public void setupComponents() {
            //  jFrame
        setTitle("Math Tutorial");
        setSize(1920, 1080);
        setResizable(false);
        setUndecorated(true);
        Container Form = getContentPane();
        Form.setLayout(null);
        ToolTipManager.sharedInstance().setInitialDelay(20);
        ToolTipManager.sharedInstance().setDismissDelay(120000);
        
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                closingConfirmation();
            };
        });
        
        MouseListener mouseListener = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                JComponent component = (JComponent)e.getSource();
                TransferHandler handler = component.getTransferHandler();
                handler.exportAsDrag(component, e, TransferHandler.COPY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
            //  jPanel Body
        Form.add(jpBody);
        jpBody.setBackground(Color.LIGHT_GRAY);
        jpBody.setBounds(0, 0, 1920, 1080);
        jpBody.setLayout(null);
            //  jPanel Blackboard
        jpBody.add(jpBlackboard);
        jpBlackboard.setBackground(SlateGreen);
        jpBlackboard.setBorder(BorderFactory.createLineBorder(GoldenOak, 15));
        jpBlackboard.setBounds(160, 180, 1600, 750);
        jpBlackboard.setLayout(null);
            //  jPanel Operator Box
        jpBlackboard.add(jpBottomBox);
        jpBottomBox.setBackground(darkSlateGreen);
        jpBottomBox.setBounds(130, 528, 980, 160);
        jpBottomBox.setLayout(null);
            //  jPanel Answer Box
        jpBlackboard.add(jpAnswerBox);
        jpAnswerBox.setBackground(darkSlateGreen);
        jpAnswerBox.setBounds(1200, 58, 330, 630);
        jpAnswerBox.setLayout(null);
        
            //  jButton Exit
        jpBody.add(jbExit);
        jbExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                closingConfirmation();
            }
        });
        jbExit.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        jbExit.setBounds(1830, 40, 50, 50);
        jbExit.setToolTipText("<html><h2>Return to<br>Main Menu</html>");
            // jButton Select Mode
        jpBlackboard.add(jbChangeMode);
        jbChangeMode.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String iconFile = jbChangeMode.getIcon().toString();
                if(iconFile.contains("src/resources/other-icons/change-50px.png")){
                    dialogBox.setVisible(true);
                    jbChangeMode.setIcon(new ImageIcon("src/resources/other-icons/cancel-50px.png"));
                } else {
                    dialogBox.dispose();
                    jbChangeMode.setIcon(new ImageIcon("src/resources/other-icons/change-50px.png"));
                }
            }
        });
        jbChangeMode.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        jbChangeMode.setBounds(1130, 75, 50, 50);
            // jButton Refresh
        jpBlackboard.add(jbRefresh);
        jbRefresh.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                generateEquation();
            }
        });
        jbRefresh.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        jbRefresh.setBounds(1130, 150, 50, 50);
            // jButton Plus
        jpBottomBox.add(jbPlus);
        jbPlus.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                operatorActionPerformed(e);
            }
        });
        jbPlus.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        jbPlus.setBorderPainted(false);
        jbPlus.setBounds(30, 20, 120, 120);
        jbPlus.setToolTipText("<html><h2>Operator: Addition<h4>Hover the mouse into the<br>\"Operator\" shown above to<br>know how it works...</html>");
            //  jButton Minus
        jpBottomBox.add(jbMinus);
        jbMinus.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                operatorActionPerformed(e);
            }
        });
        jbMinus.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        jbMinus.setBorderPainted(false);
        jbMinus.setBounds(180, 20, 120, 120);
        jbMinus.setToolTipText("<html><h2>Operator: Subtraction<h4>Hover the mouse into the<br>\"Operator\" shown above to<br>know how it works...</html>");
            //  jButton Times
        jpBottomBox.add(jbTimes);
        jbTimes.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                operatorActionPerformed(e);
            }
        });
        jbTimes.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        jbTimes.setBorderPainted(false);
        jbTimes.setBounds(330, 20, 120, 120);
        jbTimes.setToolTipText("<html><h2>Operator: Multiplication<h4>Hover the mouse into the<br>\"Operator\" shown above to<br>know how it works...</html>");
            //  jButton Divide
        jpBottomBox.add(jbDivide);
        jbDivide.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                operatorActionPerformed(e);
            }
        });
        jbDivide.setBounds(480, 20, 120, 120);
        jbDivide.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        jbDivide.setBorderPainted(false);
        jbDivide.setToolTipText("<html><h2>Operator: Division<h4>Hover the mouse into the<br>\"Operator\" shown above to<br>know how it works...</html>");
            // jButton Toggle All
        jpBottomBox.add(jbToggleAll);
        jbToggleAll.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               operatorActionPerformed(e);
           }
        });
        jbToggleAll.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        jbToggleAll.setBounds(630, 20, 120, 120);
        jbToggleAll.setToolTipText("<html><h2>Select All Operators<h4>Hover the mouse into the<br>\"Operator\" shown above to<br>know how it works...</html>");
            //  jButton Check Answer
        jpBottomBox.add(jbCheckAnswer);
        jbCheckAnswer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
        jbCheckAnswer.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        jbCheckAnswer.setBounds(830, 20, 120, 120);
        jbCheckAnswer.setToolTipText("<html><h2>Check the<br>Answer</html>");
 
            // jLabel "Difficulty:"
        jpBlackboard.add(jlDifficulty);
        jlDifficulty.setBounds(40, 50, 400, 100);
            // jLabel Mode
        jpBlackboard.add(jlMode);
        jlMode.setBounds(400, 50, 300, 100);
            // jLabel Question Number Value 1
        jpBlackboard.add(jlNum1);
        jlNum1.setBounds(320, 260, 120, 120);
        jlNum1.setToolTipText("<html><h2>This is a Number<h4>Hover the mouse to the Right</html>");
            //  jLabel Operator
        jpBlackboard.add(jlOperator);
        jlOperator.setBounds(450, 260, 120, 120);
        jlOperator.setToolTipText("<html><h2>You just found the Operator<h4>How this works:<br>Displays your selected operator. If you select<br>more than one operator, a random selection between<br>the selected operators will be generated.</html>");
            // jLabel Question Number Value 2
        jpBlackboard.add(jlNum2);
        jlNum2.setBounds(580, 260, 120, 120);
        jlNum2.setToolTipText("<html><h2>This is a Number<h4>Hover the mouse to the Left</html>");
            // jLabel "="
        jpBlackboard.add(jlEquals);
        jlEquals.setBounds(710, 260, 120, 120);
        jlEquals.setToolTipText("<html><h2>This is an Equals Sign<h4>Hover the mouse to the Left</html>");
            // jLabel Answer
        jpBlackboard.add(jlAnswer);
        jlAnswer.setBackground(darkSlateGreen);
        jlAnswer.setBounds(840, 260, 120, 120);
        jlAnswer.setOpaque(true);
        jlAnswer.setToolTipText("<html><h2>Drag your<br>Answer Here</html>");
        jlAnswer.setTransferHandler(new TransferHandler("icon"));
            // jLabel Answer Choice 1
        jpAnswerBox.add(jlAns1);
        jlAns1.addMouseListener(mouseListener);
        jlAns1.setBounds(30, 80, 120, 120);
        jlAns1.setTransferHandler(new TransferHandler("icon"));
            // jLabel Answer Choice 2
        jpAnswerBox.add(jlAns2);
        jlAns2.addMouseListener(mouseListener);
        jlAns2.setBounds(180, 80, 120, 120);
        jlAns2.setTransferHandler(new TransferHandler("icon"));
            // jLabel Answer Choice 3
        jpAnswerBox.add(jlAns3);
        jlAns3.addMouseListener(mouseListener);
        jlAns3.setBounds(30, 255, 120, 120);
        jlAns3.setTransferHandler(new TransferHandler("icon"));
            // jLabel Answer Choice 4
        jpAnswerBox.add(jlAns4);
        jlAns4.addMouseListener(mouseListener);
        jlAns4.setBounds(180, 255, 120, 120);
        jlAns4.setTransferHandler(new TransferHandler("icon"));
            // jLabel Answer Choice 5
        jpAnswerBox.add(jlAns5);
        jlAns5.addMouseListener(mouseListener);
        jlAns5.setBounds(30, 430, 120, 120);
        jlAns5.setTransferHandler(new TransferHandler("icon"));
            // jLabel Answer Choice 6
        jpAnswerBox.add(jlAns6);
        jlAns6.addMouseListener(mouseListener);
        jlAns6.setBounds(180, 430, 120, 120);
        jlAns6.setTransferHandler(new TransferHandler("icon"));
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
                    dialogClosing();
                };
            });
                //  jButton Easy
            add(jbEasy);
            jbEasy.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    difficulty = "easy";
                    changeDifficulty();
                    dialogClosing();
                }
            });
            jbEasy.setBounds(20, 20, 160, 60);
            jbEasy.setBorderPainted(false);
            jbEasy.setToolTipText("<html><h2>Easy Mode<h4>Two numbers will generate<br>ranging from 1-10</html>");
                //  jButton Medium
            add(jbMedium);
            jbMedium.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    difficulty = "medium";
                    changeDifficulty();
                    dialogClosing();
                }
            });
            jbMedium.setBounds(20, 100, 160, 60);
            jbMedium.setBorderPainted(false);
            jbMedium.setToolTipText("<html><h2>Medium Mode<h4>Two numbers will generate<br>ranging from 1-20</html>");
                //  jButton Hard
            add(jbHard);
            jbHard.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    difficulty = "hard";
                    changeDifficulty();
                    dialogClosing();
                }
            });
            jbHard.setBounds(20, 180, 160, 60);
            jbHard.setBorderPainted(false);
            jbHard.setToolTipText("<html><h2>Hard Mode<h4>Two numbers will generate<br>ranging from 1-30</html>");
            setLocationRelativeTo(null);
        }
        
        private void dialogClosing(){
            dispose();
            jbChangeMode.setIcon(new ImageIcon("src/resources/other-icons/change-50px.png"));
        }
    }

    public void closingConfirmation() {
        int confirm = JOptionPane.showConfirmDialog(null, "<html><h2>Returning to Menu will<br>stop the Tutorial. Continue?</html>", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(confirm == JOptionPane.YES_OPTION){
            dialogBox.dispose();
            new MathTutorial().setVisible(true);
            dispose();
        }
    }
    
    public void operatorActionPerformed(ActionEvent e){
        if(e.getSource() == jbToggleAll){
            if(plusEnabled == true && minusEnabled == true && timesEnabled == true && divideEnabled == true){
                jbPlus.setBorderPainted(false);
                jbMinus.setBorderPainted(false);
                jbTimes.setBorderPainted(false);
                jbDivide.setBorderPainted(false);
                plusEnabled = false;
                minusEnabled = false;
                timesEnabled = false;
                divideEnabled = false;
            } else {
                jbPlus.setBorderPainted(true);
                jbMinus.setBorderPainted(true);
                jbTimes.setBorderPainted(true);
                jbDivide.setBorderPainted(true);
                plusEnabled = true;
                minusEnabled = true;
                timesEnabled = true;
                divideEnabled = true;
            }
        }
        if(e.getSource() == jbPlus){
            if(plusEnabled == true){
                jbPlus.setBorderPainted(false);
                plusEnabled = false;
            } else {
                jbPlus.setBorderPainted(true);
                plusEnabled = true;
            }
        }
        if(e.getSource() == jbMinus){
            if(minusEnabled == true){
                jbMinus.setBorderPainted(false);
                minusEnabled = false;
            } else {
                jbMinus.setBorderPainted(true);
                minusEnabled = true;
            }
        }
        if(e.getSource() == jbTimes){
            if(timesEnabled == true){
                jbTimes.setBorderPainted(false);
                timesEnabled = false;
            } else {
                jbTimes.setBorderPainted(true);
                timesEnabled = true;
            }
        }
        if(e.getSource() == jbDivide){
            if(divideEnabled == true){
                jbDivide.setBorderPainted(false);
                divideEnabled = false;
            } else {
                jbDivide.setBorderPainted(true);
                divideEnabled = true;
            }
        }
        changeOperator();
    }
    
    public void changeOperator(){
        operatorActive = 0;
        operatorSelect = "";
        operatorOf = 'x';
        if(plusEnabled == true){
            operatorOf = 'p';
            operatorSelect += operatorOf;
            operatorActive++;
        }
        if(minusEnabled == true){
            operatorOf = 'm';
            operatorSelect += operatorOf;
            operatorActive++;
        }
        if(timesEnabled == true){
            operatorOf = 't';
            operatorSelect += operatorOf;
            operatorActive++;
        }
        if(divideEnabled == true){
            operatorOf = 'd';
            operatorSelect += operatorOf;
            operatorActive++;
        }
        
        if(operatorActive > 1){
            selectOperator = generateRandom.nextInt(operatorActive);
            operatorOf = operatorSelect.charAt(selectOperator);
        }
        
        switch(operatorOf){
            case 'p':
                operatorFile = "plus";
                break;
            case 'm':
                operatorFile = "minus";
                break;
            case 't':
                operatorFile = "times";
                break;
            case 'd':
                operatorFile = "divide";
                break;
            default:
                operatorFile = "unknown";
        }
        jlOperator.setIcon(new ImageIcon("src/resources/operators/" + operatorFile + ".png"));
    }
    
    public void changeDifficulty(){
        jlMode.setIcon(new ImageIcon("src/resources/other-icons/"+ difficulty + "-text.png"));
        generateEquation();
    }
    
    public void generateEquation(){
        try{
            //var fileLocation = "src/resources/" + difficulty + ".txt";
            FileReader readEquationFile = new FileReader("src/resources/" + difficulty + ".txt");
            Scanner scanFile = new Scanner(readEquationFile);
            questionNum = 1 + (generateRandom.nextInt(10));
            while(scanFile.hasNextInt()){
                questionKey = scanFile.nextInt();
                numQstn1 = scanFile.nextInt();
                numQstn2 = scanFile.nextInt();
                numAns1 = scanFile.nextInt();
                numAns2 = scanFile.nextInt();
                numAns3 = scanFile.nextInt();
                numAns4 = scanFile.nextInt();
                numAns5 = scanFile.nextInt();
                numAns6 = scanFile.nextInt();
                if(questionNum == questionKey){
                    break;
                }
            }
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, ex + "", "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        
        jlNum1.setIcon(new ImageIcon("src/resources/transparent-numbers/" + numQstn1 + ".png"));
        jlNum2.setIcon(new ImageIcon("src/resources/transparent-numbers/" + numQstn2 + ".png"));
        jlAnswer.setIcon(new ImageIcon("src/resources/block-numbers/unknown-block.png"));
        jlAns1.setIcon(new ImageIcon("src/resources/block-numbers/" + numAns1 + "-block.png"));
        jlAns2.setIcon(new ImageIcon("src/resources/block-numbers/" + numAns2 + "-block.png")); 
        jlAns3.setIcon(new ImageIcon("src/resources/block-numbers/" + numAns3 + "-block.png"));
        jlAns4.setIcon(new ImageIcon("src/resources/block-numbers/" + numAns4 + "-block.png"));
        jlAns5.setIcon(new ImageIcon("src/resources/block-numbers/" + numAns5 + "-block.png"));
        jlAns6.setIcon(new ImageIcon("src/resources/block-numbers/" + numAns6 + "-block.png"));
        jbChangeMode.setEnabled(false);
        jbRefresh.setEnabled(false);
        tries = 3;
        jbChangeMode.setToolTipText("<html><h2>Tries Left: " + tries + "</html>");
        jbRefresh.setToolTipText("<html><h2>Tries Left: " + tries + "</html>");
        
    }
    
    public void checkAnswer(){
        inputAnswer = jlAnswer.getIcon().toString();
        if(operatorOf == 'x') {
            JOptionPane.showMessageDialog(null, "<html><h2>Make sure that you<br>select an Operator</html>", "", JOptionPane.PLAIN_MESSAGE);
        } else if(inputAnswer.contains("src/resources/block-numbers/unknown-block.png")) {
            JOptionPane.showMessageDialog(null, "<html><h2>Choose your<br>Corresponding Answer</html>", "", JOptionPane.PLAIN_MESSAGE);
        } else {
            switch(operatorOf){
                case 'p':
                    correctAnswer = numQstn1 + numQstn2;
                    break;
                case 'm':
                    correctAnswer = numQstn1 - numQstn2;
                    break;
                case 't':
                    correctAnswer = numQstn1 * numQstn2;
                    break;
                case 'd':
                    correctAnswer = numQstn1 / numQstn2;
                    break;
            }
            if(inputAnswer.contains("src/resources/block-numbers/" + correctAnswer + "-block.png")){
                result = "Correct";
                generateEquation();
                changeOperator();
            } else {
                result = "Wrong";
                tries--;
                if(tries <= 0){
                    jbChangeMode.setEnabled(true);
                    jbRefresh.setEnabled(true);
                    jbChangeMode.setToolTipText("<html><h2>Change Mode</html>");
                    jbRefresh.setToolTipText("<html><h2>Generate New Equation</html>");
                } else {
                    jbChangeMode.setToolTipText("<html><h2>Tries Left: " + tries + "</html>");
                    jbRefresh.setToolTipText("<html><h2>Tries Left: " + tries + "</html>");
                }
            }
            
            try {
                //var fileLocation = "src/resources/answerIs" + result + ".txt";
                FileReader readResponseFile = new FileReader("src/resources/answerIs" + result + ".txt");
                Scanner scanFile = new Scanner(readResponseFile);
                responseNum = 1 + (generateRandom.nextInt(5));
                while(scanFile.hasNextInt()){
                    responseKey = scanFile.nextInt();
                    response = scanFile.nextLine();
                    if(responseNum == responseKey){
                        break;
                    }
                }
            } catch(FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex, "", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "<html><h2>The Answer is " + result + "<h3>" + response + "</html>", "", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        new TutorialWindow().setVisible(true);
    }
}

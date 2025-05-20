import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Main extends JPanel implements ActionListener{
    JButton[] numberButton = new JButton[10];
    JButton[] functionButton = new JButton[8];
    JButton addButton, minusButton, timeButton, divideButton,
            equalButton, acButton, dotButton, delButton;

    JTextField textField;
    double num1,num2,result;
    char operator;
    Font enfont = new Font("Times New Roman",Font.PLAIN,60);
    Font khFont = new Font("Kh Content",Font.PLAIN,30);

    public Main(){
        setLayout(new GridLayout(3,4));
        setBounds(0,200,400,300);

        textField = new JTextField();
        textField.setText("0");
        textField.setBounds(0,0,400,100);
        textField.setEditable(false);
        textField.setFont(enfont);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBorder(BorderFactory.createBevelBorder(1));

        addButton    = new JButton("បូក");
        minusButton  = new JButton("ដក");
        timeButton   = new JButton("គុណ");
        divideButton = new JButton("ចែក");
        equalButton  = new JButton("សើ្ម");
        acButton     = new JButton("លុបទាំងអស់");
        dotButton    = new JButton(".");
        delButton    = new JButton("លុប");

        functionButton[0] = addButton;
        functionButton[1] = minusButton;
        functionButton[2] = timeButton;
        functionButton[3] = divideButton;
        functionButton[4] = equalButton;
        functionButton[5] = acButton;
        functionButton[6] = dotButton;
        functionButton[7] = delButton;

        for(int i = 0 ; i<8 ; i++){
            functionButton[i].setFocusable(false);
            functionButton[i].addActionListener(this);
            functionButton[i].setFont(khFont);
        }

        for(int i = 0 ; i <10 ; i++){
            numberButton[i] = new JButton(String.valueOf(i));
            numberButton[i].setFocusable(false);
            numberButton[i].addActionListener(this);
            numberButton[i].setFont(enfont);
        }

        add(numberButton[1]);
        add(numberButton[2]);
        add(numberButton[3]);
        add(minusButton);
        add(numberButton[4]);
        add(numberButton[5]);
        add(numberButton[6]);
        add(timeButton);
        add(numberButton[7]);
        add(numberButton[8]);
        add(numberButton[9]);
        add(divideButton);

        JPanel panel = new JPanel();
        panel.setSize(300,100);
        panel.setBounds(0,100,400,100);
        panel.setLayout(null);
        acButton.setBounds(0,0,200,100);
        delButton.setBounds(200,0,100,100);
        addButton.setBounds(300,0,100,100);
        panel.add(acButton);
        panel.add(delButton);
        panel.add(addButton);

        JPanel panel1 = new JPanel();
        panel1.setBounds(0,500,400,100);
        panel1.setLayout(null);
        dotButton.setBounds(0,0,100,100);
        numberButton[0].setBounds(100,0,100,100);
        equalButton.setBounds(200,0,200,100);
        panel1.add(dotButton);
        panel1.add(numberButton[0]);
        panel1.add(equalButton);

        JFrame frame = new JFrame("Calculator");
        frame.setSize(414,637);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.add(textField);
        frame.add(panel);
        frame.add(this);
        frame.add(panel1);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0 ; i <10 ; i++){
            if(e.getSource()==numberButton[i]){
                if(textField.getText().compareTo("0")==0 || textField.getText().compareTo("គិតអី")==0){
                    textField.setFont(enfont);
                    textField.setHorizontalAlignment(JTextField.RIGHT);
                    textField.setText(String.valueOf(i));
                }else{
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }
            }
        }

        if(textField.getText().length()>=13){
            textField.setText("គិតអី");
            textField.setFont(khFont);
            textField.setHorizontalAlignment(JTextField.CENTER);
        }

        if(textField.getText().compareTo("គិតអី") !=0){
            if(e.getSource()==dotButton){
                textField.setText(textField.getText().concat("."));
            }
            if(e.getSource()==addButton){
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
                operator = '+';
            }else if(e.getSource()==minusButton){
                if(textField.getText().compareTo("0")!=0){
                    num1 = Double.parseDouble(textField.getText());
                    textField.setText("");
                    operator = '-';
                }else{
                    textField.setText("-");
                }

            }else if(e.getSource()==timeButton){
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
                operator = '*';
            }else if(e.getSource()==divideButton){
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
                operator = '/';
            }
            if(e.getSource()==equalButton){
                num2 = Double.parseDouble(textField.getText());
                switch (operator){
                    case '+'->{
                        result = num1+num2;
                    }
                    case '-'->{
                        result = num1-num2;
                    }
                    case '*'->{
                        result = num1*num2;
                    }
                    case '/'->{
                        result = num1/num2;
                    }
                }
                DecimalFormat df = new DecimalFormat("#.############");
                textField.setText(String.valueOf(df.format(result)));
                num1=result;
                if(textField.getText().length()>=13){
                    textField.setText("គិតអី");
                    textField.setFont(khFont);
                    textField.setHorizontalAlignment(JTextField.CENTER);
                }
            }
        }
        if(e.getSource()==acButton){
            textField.setText("0");
        }
        if(e.getSource()==delButton){
            if(textField.getText().length() == 1)
                textField.setText("0");
            else {
                String text = textField.getText();
                textField.setText("");
                for(int i = 0 ; i<text.length()-1 ; i++) {
                    textField.setText(textField.getText() + text.charAt(i));
                }
            }

        }
    }

    public static void main(String[] args) {
        new Main();
    }
}

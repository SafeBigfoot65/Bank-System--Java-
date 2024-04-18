package github;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class calculator extends bankApplication {
    static JTextField calcField=new JTextField();
    static int buttonTrack=0;
    static int dotIndex;
    static double transaction;
    JLabel transactLabel=new JLabel(),question=new JLabel();
    static JButton yesDeposit=new JButton(),noDeposit=new JButton(),yesWithdraw=new JButton(),noWithdraw=new JButton();
    static boolean failedTransaction=false;
    final DecimalFormat dec=new DecimalFormat("0.00");
    transferApp calcTransfer=new transferApp();
    //Function for setting up a new button for the calculator
    void calcButtonLayout(JButton button,int xPosition,int yPosition,String buttonNum){
        button.addActionListener(this);
        button.setText(buttonNum);
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Comic Sans",Font.BOLD,20));
        button.setForeground(Color.blue);
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBounds(xPosition,yPosition, 75, 50);
        if(buttonTrack<10){
            numButtons[buttonTrack]=button;
            buttonTrack++;
        }
    }
    //enables calculator whenever deposit or withdraw button is clicked on
    void enableCalc(){
        for (int i=0;i<10;i++){
            redPanel.add(numButtons[i]);
        }
        redPanel.add(zeroButton);
        redPanel.add(dotButton);
        redPanel.add(deleteButton);
        redPanel.add(clearButton);
        redPanel.add(enterButton);
        redPanel.add(transactLabel);
    }
    //Assign functions to calc buttons...
    void labelQuestionForDepositOrWithdraw(){
        if(depositOrWithdraw.equals("GREEN")){
            question.setText("Please input the amount of funds you would like to deposit:");
        }
        else{
            question.setText("Please input the amount of funds you would like to withdraw:");
        }
        labelFormat(question, 100, 50, 1100, 100,depositOrWithdraw );
    }
    void calcNum(){
        calcButtonLayout(zeroButton,50,350,"0");
        calcButtonLayout(oneButton, 50, 500,"1");
        calcButtonLayout(twoButton, 125, 500, "2");
        calcButtonLayout(threeButton, 200, 500, "3");
        calcButtonLayout(fourButton, 50, 450, "4");
        calcButtonLayout(fiveButton, 125, 450, "5");
        calcButtonLayout(sixButton, 200,450, "6");
        calcButtonLayout(sevenButton, 50, 400, "7");
        calcButtonLayout(eightButton, 125, 400, "8");
        calcButtonLayout(nineButton, 200, 400, "9");
        calcButtonLayout(deleteButton, 125, 350, "Delete");
        calcButtonLayout(dotButton, 200, 350, ".");
        calcButtonLayout(clearButton, 90, 550, "Clear");
        calcButtonLayout(enterButton, 160, 550, "Enter");
        calcButtonLayout(yesDeposit, 625, 250, "Yes");
        calcButtonLayout(noDeposit, 700, 250, "No");
        calcButtonLayout(yesWithdraw, 625, 250, "Yes");
        calcButtonLayout(noWithdraw, 700, 250, "No");
    }
    //Sets up the textField for the calculator
    void calcTextField(String color){
        calcField.setBounds(50,290,225,50);
        calcField.setForeground(colorFormat(color));
        calcField.setHorizontalAlignment(JTextField.CENTER);
        calcField.setFont(new Font("Times New Roman",Font.BOLD,35));
        calcField.setText(moneyString);
        calcField.setEditable(false);
        redPanel.add(calcField);
    }
    //Use to send an element to the text field 
    void numString(Character userInputNum){
        if(((userInputNum>='0')&&(userInputNum<='9'))){
           calcField.setText(calcField.getText()+(userInputNum));
        }
        else if((userInputNum=='.')&&(dotIndex==1)){
            calcField.setText(calcField.getText()+(userInputNum));
            dotIndex++;
        }
    }
    //Used to delete the most recent element from the textfield
    void deleteEntry(){
        String keepPartOfText=calcField.getText();
        calcField.setText("");
        if (keepPartOfText.charAt(keepPartOfText.length()-1)=='.'){
            dotIndex=1;
        }
        for(int i=0;i<keepPartOfText.length()-1;i++){
            calcField.setText(calcField.getText()+keepPartOfText.charAt(i));
        }
    }
    //Used to clear entries
    void clearEntries(){
        calcField.setText("");
        dotIndex=1;
    }
    //Used to enter transactions, and prompt the user if they truly want to deposit/withdraw money
    void enterTransaction(){
        redPanel.add(yesDeposit);
        redPanel.add(noDeposit);
        redPanel.add(yesWithdraw);
        redPanel.add(noWithdraw);
        transactLabel.setVisible(true);
        transaction=Double.valueOf(calcField.getText());
        if (depositOrWithdraw.equals("GREEN")){
            yesDeposit.setVisible(true);
            noDeposit.setVisible(true);
            yesWithdraw.setVisible(false);
            noWithdraw.setVisible(false);
        }
        else if((depositOrWithdraw.equals("RED"))&&(money>transaction)){
            yesDeposit.setVisible(false);
            noDeposit.setVisible(false);
            yesWithdraw.setVisible(true);
            noWithdraw.setVisible(true);
        }
        else{
            yesDeposit.setVisible(false);
            noDeposit.setVisible(false);
            yesWithdraw.setVisible(false);
            noWithdraw.setVisible(false);
        }
        if ((depositOrWithdraw.equals("RED")&&(money<transaction))){
            transactLabel.setText("Sorry, you don't have enough funds to withdraw. Please try again.");
            labelFormat(transactLabel, 200, 200, 1100, 100, depositOrWithdraw);
            failedTransaction=true;
        }
        else if((depositOrWithdraw.equals("GREEN"))||(depositOrWithdraw.equals("RED")&&(money>transaction))){
            String transactName;
            if(depositOrWithdraw.equals("GREEN")){
                transactName="deposit";
            }
            else{
                transactName="withdraw";
            }
            String temp=dec.format(transaction);
            transaction=Double.parseDouble(temp);
            transactLabel.setText("Are you sure you want to "+transactName+" $"+ temp+"?");
            labelFormat(transactLabel, 400, 200, 700, 100, depositOrWithdraw);
        }
        else {
            calcTransfer.confirmWithUser();
            calcTransfer.buttonsConfirmationFormat(transferApp.userConfirmation);
        }
        
    }
}

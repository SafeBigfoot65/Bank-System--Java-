package github;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.text.DecimalFormat;

public class transferApp extends bankApplication {
    final JLabel start=new JLabel();
    final JLabel notPossible=new JLabel();
    final JLabel confirm=new JLabel();
    static JButton[]accButtons=new JButton[2];
    static JButton[]userConfirmation=new JButton[2];
    static boolean accOne,accTwo,transferPossible=false;
    final DecimalFormat dec=new DecimalFormat("0.00");
    

    void initiateTransfer(){
        if((checkingAccTwo==true)||(savingsAccTwo==true)){
        start.setText("Please select what account you would like to transfer from first.");
        labelFormat(start, 100, 0, 1000, 200, "WHITE");
        transferPossible=true;
        }
    }
    void beforeInitiation(){
        if((checkingAccTwo==false)&&(savingsAccTwo==false)){
            notPossible.setText("Transfer not possible, please open a new secondary account first.");
            labelFormat(notPossible, 100, 0, 1000, 200, "WHITE");
        }
    }
    void initializeAccButtons(){
        if(transferPossible==true){
            accButtons[0]=new JButton("Account #1");
            accButtons[1]=new JButton("Account #2");
            int xPosition=400;
            for (int i=0;i<2;i++){
                accButtons[i].setBounds(xPosition,50, 250, 75);
                accButtons[i].addActionListener(this);
                accButtons[i].setHorizontalTextPosition(JButton.CENTER);
                accButtons[i].setVerticalTextPosition(JButton.BOTTOM);
                accButtons[i].setFont(new Font("Comic Sans",Font.BOLD,20));
                accButtons[i].setForeground(Color.blue);
                accButtons[i].setBackground(Color.LIGHT_GRAY);
                accButtons[i].setBorder(BorderFactory.createEtchedBorder());
                redPanel.add(accButtons[i]);
                xPosition=xPosition+250;
        }
    }
    } 
    void buttonsConfirmationFormat(JButton[] button){
        button[0]=new JButton("YES");
            button[1]=new JButton("NO");
            int xPosition=550;
            for (int i=0;i<2;i++){
                button[i].setBounds(xPosition,300, 200, 75);
                button[i].addActionListener(this);
                button[i].setHorizontalTextPosition(JButton.CENTER);
                button[i].setVerticalTextPosition(JButton.BOTTOM);
                button[i].setFont(new Font("Comic Sans",Font.BOLD,20));
                button[i].setForeground(Color.blue);
                button[i].setBackground(Color.LIGHT_GRAY);
                button[i].setBorder(BorderFactory.createEtchedBorder());
                redPanel.add(button[i]);
                xPosition=xPosition+200;
            }
    }
    void confirmWithUser(){
        confirm.setText("Are you sure you want to transfer funds?");
        labelFormat(confirm, 400, 200, 700, 100, "WHITE");
    }
    void transferFunds(){
        if(accOne==true){
            String transfer=""+calculator.transaction;
            money=money-Double.parseDouble(transfer);
            newMoney=newMoney+Double.parseDouble(transfer);
        }
        else if(accTwo==true){
            String transfer=""+calculator.transaction;
            newMoney=newMoney-Double.parseDouble(transfer);
            money=money+Double.parseDouble(transfer);
        }
        String tempOne=dec.format(money);
        money=Double.parseDouble(tempOne);
        String tempTwo=dec.format(newMoney);
        newMoney=Double.parseDouble(tempTwo);
        confirm.setText("Transfer Sucessful!");
        labelFormat(confirm, 400, 300, 700, 100, "GREEN");

    }
    void declineTransfer(){
        confirm.setText("Ok, transfer won't go through.");
        labelFormat(confirm, 400, 300, 700, 100, "WHITE");
    }
}

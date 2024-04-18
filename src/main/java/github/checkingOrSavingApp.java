package github;

import javax.swing.*;
import java.awt.Image;
import java.text.DecimalFormat;

public class checkingOrSavingApp extends bankApplication{
    JLabel labelOne= new JLabel();
    JLabel labelTwo= new JLabel();
    JButton clickMe=new JButton();
    JLabel promptUser=new JLabel();
    JLabel newAccJLabelOne=new JLabel();
    JLabel newAccJLabelTwo=new JLabel();
    calculator calc=new calculator();
    int accsTrack=1;
    final DecimalFormat dec=new DecimalFormat("0.00");
    settingsApp cSSetting=new settingsApp();
    
    //First displays the first account info and also an image
    void visibleCheckOrSave(){
        String temp=dec.format(money);
        money=Double.parseDouble(temp);

        labelOne.setText("Here, your "+accCheckOrSave+" account has a total amount of:");
        labelFormat(labelOne, 200,60,900,50,"WHITE");

        labelTwo.setText("$"+money);
        labelFormat(labelTwo, 200,120,900,50,"GREEN");

        ImageIcon moneyIcon=new ImageIcon("C:\\Users\\toben\\OneDrive\\Documents\\GitHub\\app\\src\\main\\resources\\patrick.png");
        moneyIcon.setImage(moneyIcon.getImage().getScaledInstance(500,490,Image.SCALE_DEFAULT));
        JLabel moneyLabel=new JLabel();
        moneyLabel.setIcon(moneyIcon);
        labelFormat(moneyLabel, 100, 300, 1100, 900,"WHITE");
        
    }
    //Checks if user has a secondary account saved/created before
    void preCheck(){
        String temp=""+newMoney;
        if ((!temp.equals("EmptySecondMoney"+secondID))&&(tryCatchFailed==false)){
            accsTrack++;
            if(accCheckOrSaveTwo.equals("Checking")){
                checkingAccTwo=true;
            }
            else{
                savingsAccTwo=true;
            }
        }
    }
    //If user doesn't have a secondary account, this would replace the empty slots in the text file 
    //according to their info
    void existingSecondAcc(){
        String temp="";
        if (checkingAccTwo==true){
            temp="Checking";
        }
        else if(savingsAccTwo==true){
            temp="Savings";
        }

        if((!accCheckOrSaveTwo.equals("Checking"))&&(!accCheckOrSaveTwo.equals("Savings"))){
            cSSetting.replaceLine("EmptySecondAcc"+secondID,temp);
            cSSetting.replaceLine("EmptySecondMoney"+secondID,""+newMoney);
        }
    }
    //Ask user what type of account do they want to open up (Checking/Saving)
    void promptUserForNewAcc(){
        promptUser.setText("Click on this button if you would like to open a new account: ");
        labelFormat(promptUser,150,0,1000,50,"WHITE");
        calc.calcButtonLayout(clickMe,1100,0,"CLICK");
        redPanel.add(clickMe);

    }
    //Function to create the secondary account
    void initiateNewAcc(){
        questionAsked=JOptionPane.showInputDialog(null,"What account would you like to open up today? (checking/savings)");
         questionString=questionCheck(questionAsked,"Checking","Savings");
         if (questionString.equals("Checking")){
             questionInt=JOptionPane.showConfirmDialog(null, "You have chosen a checking account. Is this correct?", "Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
             if (questionInt==0){
                 JOptionPane.showMessageDialog(null,"Got it, thank you!");
                 checkingAccTwo=true;
 
             }
             else if (questionInt==1) {
                 JOptionPane.showMessageDialog(null, "Oh ok. Starting from the top again......");
                 initiateNewAcc();
             }
         }
         else if (questionString.equals("Savings")){
             questionInt=JOptionPane.showConfirmDialog(null, "You have chosen a savings account. Is this correct?", "Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
             if (questionInt==0){
                 JOptionPane.showMessageDialog(null,"Got it, thank you!");
                 savingsAccTwo=true;
             }
             else if (questionInt==1) {
                 JOptionPane.showMessageDialog(null,"Oh ok. Starting from the top again......");
                 initiateNewAcc();
             }
             else{
                 JOptionPane.showMessageDialog(null,"Invalid input, please try again");
                 initiateNewAcc();
             }
         }
         else{
             JOptionPane.showMessageDialog(null,"Invalid input, please try again");
             initiateNewAcc();
         }
         newMoney=(Math.random()*((5000)+1)+0.00);
         newMoney=Math.round(newMoney*100.00)/100.00;
         clickMe.setText("OFF");
         clickMe.setEnabled(false);
         cSApp.accsTrack++;
         setNewAcc();
    }
    //Finally displays it on the main JFrame
    void setNewAcc(){
        newAccJLabelOne.setText("Here is your second account: ");
        labelFormat(newAccJLabelOne,200,200,900,60,"WHITE");
        if (checkingAccTwo==true){
            newAccJLabelTwo.setText("Checking Account has $"+newMoney);
            labelFormat(newAccJLabelTwo,200,250,900,60,"GREEN");
        }
        else if(savingsAccTwo==true){
            newAccJLabelTwo.setText("Savings Account has $"+newMoney);
            labelFormat(newAccJLabelTwo,200,250,900,60,"GREEN");
        }
    }
}

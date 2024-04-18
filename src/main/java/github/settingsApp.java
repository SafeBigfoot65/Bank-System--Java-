package github;

import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;

public class settingsApp extends bankApplication {
    final JLabel promptSettingOne=new JLabel("Click on one of the available text fields to change your information.");
    final JLabel promptSettingTwo=new JLabel("After that, click on the save button.");
    static JLabel settingsInfo[]=new JLabel[5];
    String tempName, tempDob;
    JButton settingsConfirmation[]=new JButton[2];
    JButton changeButton[]=new JButton[2];
    JLabel change=new JLabel();
    
    //Updates main money in text file
    void updateMoney(){
        String targetStr, altStr;
        if (depositOrWithdraw.equals("GREEN")){
            targetStr=""+depositApp.tempMoney;
        }
        else if(depositOrWithdraw.equals("RED")) {
            targetStr=""+withdrawApp.tempMoney;
        }
        else{
            targetStr=""+money;
        }
        altStr =""+money;
        File file = new File("userInfo.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            StringBuilder buffer = new StringBuilder();
            while(scanner.hasNext()){
                buffer.append(scanner.nextLine().replaceAll(targetStr, altStr));
                if(scanner.hasNext())buffer.append("\n");
            }
            scanner.close();
            PrintWriter printer = new PrintWriter(file);
            printer.print(buffer);
            printer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to replace.");
        }
    }
    //This function will be available for all menu tabs. Ex- opening up an account will update the text file.
    void replaceLine(String targetStr, String altStr){
        File file = new File("userInfo.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            StringBuilder buffer = new StringBuilder();
            while(scanner.hasNext()){
                buffer.append(scanner.nextLine().replaceAll(targetStr, altStr));
                if(scanner.hasNext())buffer.append("\n");
            }
            scanner.close();
            PrintWriter printer = new PrintWriter(file);
            printer.print(buffer);
            printer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to replace.");
        }
    }
    //Displays the user's information
    void initiateUserInfo(){
        labelFormat(promptSettingOne,50,10,1100,200,"WHITE");
        labelFormat(promptSettingTwo,180,50,800,200,"WHITE");
        settingsInfo[0]=new JLabel("Name: "+name);
        settingsInfo[1]=new JLabel("Date Of Birth : "+dob);
        settingsInfo[2]=new JLabel("Account Type (fixed): "+accNormalOrHigh);
        settingsInfo[3]=new JLabel("Bank ID: "+secondID);
        settingsInfo[4]=new JLabel("Save Information? ");
        changeButtonFormat();
        int yPosition=150;
        for (int i=0;i<5;i++){
            labelFormat(settingsInfo[i], 50, yPosition, 800, 200, "WHITE");
            yPosition=yPosition+100;
        }
    }
    //The following 2 functions forms the buttons
    void saveButtonsFormat(){
        settingsConfirmation[0]=new JButton("Save");
            settingsConfirmation[1]=new JButton("Revert");
            int xPosition=650;
            for (int i=0;i<2;i++){
                settingsConfirmation[i].setBounds(xPosition,530, 200, 75);
                settingsConfirmation[i].addActionListener(this);
                settingsConfirmation[i].setHorizontalTextPosition(JButton.CENTER);
                settingsConfirmation[i].setVerticalTextPosition(JButton.BOTTOM);
                settingsConfirmation[i].setFont(new Font("Comic Sans",Font.BOLD,20));
                settingsConfirmation[i].setForeground(Color.blue);
                settingsConfirmation[i].setBackground(Color.LIGHT_GRAY);
                settingsConfirmation[i].setBorder(BorderFactory.createEtchedBorder());
                redPanel.add(settingsConfirmation[i]);
                xPosition=xPosition+200;
            }
    }
    void changeButtonFormat(){
        changeButton[0]=new JButton("Change Name");
            changeButton[1]=new JButton("Change D.O.B");
            int yPosition=150;
            for (int i=0;i<2;i++){
                changeButton[i].setBounds(750,yPosition, 200, 75);
                changeButton[i].addActionListener(this);
                changeButton[i].setHorizontalTextPosition(JButton.CENTER);
                changeButton[i].setVerticalTextPosition(JButton.BOTTOM);
                changeButton[i].setFont(new Font("Comic Sans",Font.BOLD,20));
                changeButton[i].setForeground(Color.blue);
                changeButton[i].setBackground(Color.LIGHT_GRAY);
                changeButton[i].setBorder(BorderFactory.createEtchedBorder());
                redPanel.add(changeButton[i]);
                yPosition=yPosition+100;
            }
    }
    //Save the user's information in the text file
    void saveInfo(){
        replaceLine(tempName, name);
        replaceLine(tempDob, dob);
        change.setText("Save Successful!");
        labelFormat(change, 300, 650, 600, 200, "GREEN");
    }
    //Reverts the information to what it was previously known
    void revertInfo(){
        name=tempName;
        dob=tempDob;
        change.setText("Ok, reverting to old information.");
        labelFormat(change, 300, 650, 600, 200, "RED");
        redPanel.removeAll();
        initiateUserInfo();
        setting.saveButtonsFormat();
    }
    //Changes the name temporarily
    void updateName(){
        boolean nameValidation;
         int nameSpaceDetected=0;
         name=JOptionPane.showInputDialog("Please type in your full legal name: ");
         nameValidation=isString(name);
         char[] stringSplit=name.toCharArray();
         for (char naming:stringSplit){
            if (Character.isWhitespace(naming)){
                nameSpaceDetected++;
                break;
            }
         }
         if ((nameValidation!=true)||(name.isEmpty())||(nameSpaceDetected==0)){
            JOptionPane.showMessageDialog(null,"Name is not valid, please try again: ");
             updateName();
         }
    }
    //Changes the date of birth temporarily
    void updateDOB(){
        dob=JOptionPane.showInputDialog(null,"Please type in your date of birth (YYYY-MM-DD): ");
            try{LocalDate localDate=LocalDate.parse(dob);
                System.out.println(localDate);
                dateValidation=true;
            }
            catch (DateTimeException x){
                    JOptionPane.showMessageDialog(null, "Date of Birth is not valid, please try again.");
                    updateDOB();
        }
    }
}

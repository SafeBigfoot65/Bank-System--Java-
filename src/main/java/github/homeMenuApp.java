package github;

import java.awt.Image;
import javax.swing.*;


public class homeMenuApp extends bankApplication{
    
    JLabel labelOne= new JLabel();
    JLabel labelTwo= new JLabel();

    //Welcomes the user in the home page, also notifies the user to click on the buttons
    //on the left to utilize the different options/features of the application.
    //Furthermore, it displays a meme! 
    void welcomeUser(){
        labelOne.setText("Welcome Back " + name);
        labelFormat(labelOne, 200, 0,900,50,"WHITE");

        labelTwo.setText("Please use the Tab for more options! ");
        labelFormat(labelTwo, 200, 60,900,50,"WHITE");
    }
    void homeGIF(){
        ImageIcon moneyIcon=new ImageIcon("C:\\Users\\toben\\OneDrive\\Documents\\GitHub\\app\\src\\main\\resources\\takeMyMoney.png");
        moneyIcon.setImage(moneyIcon.getImage().getScaledInstance(500,490,Image.SCALE_DEFAULT));
        JLabel moneyLabel=new JLabel();
        moneyLabel.setIcon(moneyIcon);
        labelFormat(moneyLabel, 100, 150, 1100, 900,"WHITE");

    }
}
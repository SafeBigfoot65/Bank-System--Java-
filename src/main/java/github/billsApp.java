package github;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.text.DecimalFormat;

public class billsApp extends bankApplication {
    final JLabel billsWelcomeLabel=new JLabel();
    JLabel billsOneTitle=new JLabel(), billsTwoTitle=new JLabel(), billsThreeTitle=new JLabel();
    static JLabel iconOne=new JLabel(), iconTwo=new JLabel(), iconThree= new JLabel();
    static ImageIcon imageOne, imageTwo, imageThree;
    JButton[] yes=new JButton[3];
    final JLabel priceOne=new JLabel(), priceTwo=new JLabel(), priceThree=new JLabel();
    JLabel checkBillInput=new JLabel();
    HashMap<Integer, String> keys= new HashMap<Integer,String>();
    ArrayList<Integer> randomNum=new ArrayList<Integer>();
    int arrayRandom[]=new int[3];
    static int oneRandomListOnly=0;
    final DecimalFormat dec=new DecimalFormat("0.00");
    double payment;
    
    //Sets up the 3 panels for the bills
    void initiateBillsPanels(JPanel billsPanel,int xPosition,int yPosition){
        billsPanel.setBackground(Color.BLUE);
        billsPanel.setBounds(xPosition,yPosition,300,600);
        billsPanel.setLayout(null);
        redPanel.add(billsPanel);
    }
    //Adds the 3 panels to the main red panel
    void enableBillsPanels(){
        billsWelcomeLabel.setText("Please select a bill you want to pay.");
        labelFormat(billsWelcomeLabel, 90, 60, 1100, 100, "WHITE");
        initiateBillsPanels(billsOne, 100 , 200);
        initiateBillsPanels(billsTwo, 500 , 200);
        initiateBillsPanels(billsThree, 900 , 200);
        billsOne.add(billsOneTitle);
        billsTwo.add(billsTwoTitle);
        billsThree.add(billsThreeTitle);
    }
    //Initiates the hashmap, easier to set up random bills
    void initiateHashMap(){
        keys.put(1,"Groceries");
        keys.put(2,"Car Payment");
        keys.put(3,"Mortgage");
        keys.put(4,"Utilities");
        keys.put(5,"Monthly Debt");
        keys.put(6,"Medical");
        keys.put(7,"Netflix");
        keys.put(8,"Phone Bill");
        keys.put(9,"Internet Bill");
        keys.put(10,"Student Loans");
    }
    //Randomizes the 3 numbers
    void randomizedBills(){
        for(int i=1; i<11;i++){
            randomNum.add(i);
        }
        Collections.shuffle(randomNum);
        if (oneRandomListOnly==0){
            for (int i=0;i<3;i++){
                arrayRandom[i]=randomNum.get(i);
            }
            oneRandomListOnly++;
        }
    }
    //Sends content from HashMap to the 3 blue panels according to the random array
    void assignRandomArrayToPanels(){
        for (int i=0;i<3;i++){
            String title=keys.get(arrayRandom[i]);
            if (i==0){
                billsOneTitle.setText(title);
                labelFormat(billsOneTitle,0,50,300,100,"WHITE");
            }
            else if(i==1){
                billsTwoTitle.setText(title);
                labelFormat(billsTwoTitle,0,50,300,100,"WHITE");
            }
            else if(i==2){
                billsThreeTitle.setText(title);
                labelFormat(billsThreeTitle,0,50,300,100,"WHITE");
            }
        }
    }
    //These two functions set icons accordingly to its title 
    //(groceries will have an image of a bag of food)
    String setIconNamesInImages(int i){
        String path="";
        path="C:\\Users\\toben\\OneDrive\\Documents\\GitHub\\app\\src\\main\\resources\\"+keys.get(arrayRandom[i])+".png";
        return path;
    }
    void assignIconsToPanels(int i,ImageIcon icon,JLabel labelImage,JPanel panel){
        String path;
            path=setIconNamesInImages(i);
            icon=new ImageIcon(path);
            icon.setImage(icon.getImage().getScaledInstance(100,90,Image.SCALE_DEFAULT));
            labelImage.setIcon(icon);
            panel.add(iconOne);
    }
    //Repositions the icons
    void moveImageLabelsAround(){
        labelFormat(iconOne, 200, 300, 100, 200, "WHITE");
        labelFormat(iconTwo, 600, 300, 100, 200, "WHITE");
        labelFormat(iconThree, 1000, 300, 100, 200, "WHITE");
    }
    //Randomizes the three different prices
    String generateRandomPrices(){
        Random random=new Random();
        double price=5.00+(500.00-5.00)*random.nextDouble();
        return String.format("%.2f",price);
    }
    //Set prices to the 3 price labels
    void setRandomPrices(){
        if (oneRandomListOnly==1){
        priceOne.setText("$"+generateRandomPrices());
        priceTwo.setText("$"+generateRandomPrices());
        priceThree.setText("$"+generateRandomPrices());
        oneRandomListOnly++;
        }
        labelFormat(priceOne, 150, 400, 200,200, "WHITE");
        labelFormat(priceTwo, 550, 400, 200,200, "WHITE");
        labelFormat(priceThree, 950, 400, 200,200, "WHITE");
    }
    //Formats the pay buttons respectively
    void billsButtonFormat(){
        for (int i=0;i<3;i++){
            yes[i]=new JButton(""+i);
            yes[i].setBounds(25,250, 250, 75);
            yes[i].addActionListener(this);
            yes[i].setText("Pay");
            yes[i].setFocusable(false);
            yes[i].setHorizontalTextPosition(JButton.CENTER);
            yes[i].setVerticalTextPosition(JButton.BOTTOM);
            yes[i].setFont(new Font("Comic Sans",Font.BOLD,30));
            yes[i].setForeground(Color.RED);
            yes[i].setBackground(Color.LIGHT_GRAY);
            yes[i].setBorder(BorderFactory.createEtchedBorder());
        }
        billsOne.add(yes[0]);
        billsTwo.add(yes[1]);
        billsThree.add(yes[2]);
    }
    //Reformat the price to have only two decimal places
    void convertPriceStringToDouble(int i){
        String temp="";
        String newStr="";
        if(i==0){
            temp=priceOne.getText();
        }
        else if(i==1){
            temp=priceTwo.getText();
        }
        else if(i==2){
            temp=priceThree.getText();
        }
        for (int j=0;j<temp.length();j++){
            if((temp.charAt(j)!='$')&&((temp.charAt(j)>='0')&&(temp.charAt(j)<='9'))||(temp.charAt(j)=='.')){
                newStr=newStr+temp.charAt(j);
            }
        }
        payment=Double.parseDouble(newStr);
        newStr=dec.format(payment);
        payment=Double.parseDouble(newStr);
    }
    //Special format for the blue panels with its respective components
    void billsAppSpecialFormat(JLabel label,JPanel panel, int xPosition, int yPosition,int length, int width, String colorString){
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setFont(new Font("Times New Roman",Font.BOLD,35));
        label.setForeground(colorFormat(colorString));
        label.setVerticalAlignment(JLabel.NORTH);
        label.setBounds(xPosition,yPosition,length,width);
        panel.add(label);
    }
    //Notifies the user if the payment went through or not
    //If payment is sucessful, the pay button for the chosen bill is locked
    void paymentConfirmation(int i){
        if ((money-payment)>0){
            checkBillInput.setText("Payment Successful!");
            if (i==0){
                billsAppSpecialFormat(checkBillInput,billsOne, 0, 400, 700, 500, "GREEN");
                yes[i].setText("LOCKED");
                yes[i].setEnabled(false);
            }
            else if(i==1){
                billsAppSpecialFormat(checkBillInput,billsTwo, 0, 400, 800, 500, "GREEN");
                yes[i].setText("LOCKED");
                yes[i].setEnabled(false);
            }
            else if(i==2){
                billsAppSpecialFormat(checkBillInput,billsThree, 0, 400, 800, 500, "GREEN");
                yes[i].setText("LOCKED");
                yes[i].setEnabled(false);
            }
            money=money-payment;
        }
        else{
            checkBillInput.setText("Insufficient Funds.");
            if (i==0){
                billsAppSpecialFormat(checkBillInput,billsOne, 10, 400, 700, 500, "RED");
            }
            else if(i==1){
                billsAppSpecialFormat(checkBillInput,billsTwo, 10, 400, 800, 500, "RED");
            }
            else if(i==2){
                billsAppSpecialFormat(checkBillInput,billsThree, 10, 400, 800, 500, "RED");
            }
        }
    }
}

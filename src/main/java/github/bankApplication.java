package github;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bankApplication extends mainBankSystem implements ActionListener {
    static JButton checkOrSaveButton=new JButton(),settings=new JButton(),deposits=new JButton(),
    withdrawals=new JButton(),transfers=new JButton(),payBills=new JButton(),homeMenu=new JButton(),exit=new JButton(),
    oneButton=new JButton(),twoButton=new JButton(),threeButton=new JButton(),fourButton=new JButton(),
    fiveButton=new JButton(),sixButton=new JButton(),sevenButton=new JButton(),eightButton=new JButton(),
    nineButton= new JButton(),zeroButton=new JButton(), dotButton=new JButton(),deleteButton=new JButton(),
    clearButton=new JButton(),enterButton=new JButton();
    static JButton[] numButtons=new JButton[10];
    static JPanel billsOne=new JPanel(), billsTwo=new JPanel(), billsThree=new JPanel();
    static String moneyString;
    static String depositOrWithdraw="";
    static calculator calc=new calculator();
    static checkingOrSavingApp cSApp=new checkingOrSavingApp();
    static homeMenuApp home= new homeMenuApp();
    static depositApp deposit= new depositApp();
    static withdrawApp withdraw= new withdrawApp();
    static settingsApp setting= new settingsApp();
    static billsApp billing=new billsApp();
    static transferApp transferring=new transferApp();
    //Main purpose is to create a GUI for the online banking system by using JFrame
    String cSString="";
    void bankFrame(){
        frame=new JFrame("Freedom Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logo=new ImageIcon("C:\\Users\\toben\\OneDrive\\Documents\\GitHub\\app\\src\\main\\resources\\logo.png");
        frame.setIconImage(logo.getImage());
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
    }    
    void bluePanel(){
            bluePanel=new JPanel();
            bluePanel.setBackground(Color.blue);
            bluePanel.setBounds(0,250,250,600);
            if (checkingAcc==true){
                checkOrSave=new ImageIcon("C:\\Users\\toben\\OneDrive\\Documents\\GitHub\\app\\src\\main\\resources\\checking.png");
                cSString="Checking.png";           
            }
            else if(savingsAcc==true){
                checkOrSave=new ImageIcon("C:\\Users\\toben\\OneDrive\\Documents\\GitHub\\app\\src\\main\\resources\\savings.png");
                cSString="Savings.png";
            }
            else{
                checkOrSave=new ImageIcon("C:\\Users\\toben\\OneDrive\\Documents\\GitHub\\app\\src\\main\\resources\\checking.png");
                cSString="Checking.png";
                accCheckOrSave="Checking"; 
            }
   
        }
    void bankFrameCustomization(){
        JLabel label= new JLabel();
        label.setText("Freedom Bank");
        ImageIcon imageLabel= new ImageIcon("C:\\Users\\toben\\OneDrive\\Documents\\GitHub\\app\\src\\main\\resources\\logo.png");
        label.setIcon(imageLabel);
        frame.add(label);
        frame.add(bluePanel);
        frame.add(redPanel);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(Color.RED);
        label.setFont(new Font("Times New Roman",Font.BOLD,35));
        label.setIconTextGap(30);
        label.setBackground(Color.BLUE);
        label.setOpaque(true);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(0,0,250,250);
        frame.setVisible(true);
    }
    void tabButtons(JButton buttonFormat, String buttonName, String logoName, ImageIcon tabLogo, int yPosition){
        tabLogo=new ImageIcon("C:\\Users\\toben\\OneDrive\\Documents\\GitHub\\app\\src\\main\\resources\\"+logoName);
        buttonFormat.setBounds(0,yPosition, 250, 75);
        tabLogo.setImage(tabLogo.getImage().getScaledInstance(60,35,Image.SCALE_DEFAULT));
        buttonFormat.addActionListener(this);
        buttonFormat.setText(buttonName);
        buttonFormat.setFocusable(false);
        buttonFormat.setIcon(tabLogo);
        buttonFormat.setHorizontalTextPosition(JButton.CENTER);
        buttonFormat.setVerticalTextPosition(JButton.BOTTOM);
        buttonFormat.setFont(new Font("Comic Sans",Font.BOLD,20));
        buttonFormat.setForeground(Color.blue);
        buttonFormat.setBackground(Color.LIGHT_GRAY);
        buttonFormat.setBorder(BorderFactory.createEtchedBorder());
        frame.add(buttonFormat);
    }
    void labelFormat(JLabel label, int xPosition, int yPosition,int length, int width, String colorString){
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setFont(new Font("Times New Roman",Font.BOLD,35));
        label.setForeground(colorFormat(colorString));
        label.setVerticalAlignment(JLabel.NORTH);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(xPosition,yPosition,length,width);
        redPanel.add(label);
    }
    Color colorFormat(String numColor){
        Color color=null;;
        if (numColor=="WHITE"){
            color=Color.WHITE;
        }
        else if (numColor=="BLACK"){
            color=Color.BLACK;
        }
        else if (numColor=="GREEN"){
            color=Color.GREEN;
        }
        else if (numColor=="ORANGE"){
            color=Color.ORANGE;
        }
        else if (numColor=="YELLOW"){
            color=Color.YELLOW;
        }
        else if (numColor=="PINK"){
            color=Color.PINK;
        }
        else if (numColor=="RED"){
            color=Color.RED;
        }
        else if (numColor=="BLUE"){
            color=Color.BLUE;
        }
        return color;
    }
    void redPanel(){
        redPanel.setBackground(new Color(123,50,60));
        redPanel.setBounds(250,0,1400,1000);
        redPanel.setLayout(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==checkOrSaveButton){
            redPanel.removeAll();
            cSApp.preCheck();
            cSApp.visibleCheckOrSave();
            if(cSApp.accsTrack==1){
                cSApp.promptUserForNewAcc();
            }
            else if (cSApp.accsTrack!=1){
                cSApp.setNewAcc();
            }
        }
        if(e.getSource()==settings){
            setting.tempName=name;
            setting.tempDob=dob;
            redPanel.removeAll();
            setting.initiateUserInfo();
            setting.saveButtonsFormat();

        }
        if(e.getSource()==deposits){
            depositOrWithdraw="GREEN";
            setting.updateMoney();
            redPanel.removeAll();
            calc.transactLabel.setVisible(false);
            calc.labelQuestionForDepositOrWithdraw();
            calculator.dotIndex=1;
            calc.calcTextField(depositOrWithdraw);
            calc.enableCalc();
        }
        if(e.getSource()==homeMenu){
            redPanel.removeAll();
            home.welcomeUser();
            home.homeGIF();
        }
        if(e.getSource()==withdrawals){
            depositOrWithdraw="RED";
            setting.updateMoney();
            calculator.dotIndex=1;
            redPanel.removeAll();
            calc.transactLabel.setVisible(false);
            calc.labelQuestionForDepositOrWithdraw();
            calc.calcTextField(depositOrWithdraw);
            calc.enableCalc();
        }
        if(e.getSource()==transfers){
            redPanel.removeAll();
            transferring.beforeInitiation();
            transferring.initiateTransfer();
            transferring.initializeAccButtons();
        }
        if(e.getSource()==payBills){
            redPanel.removeAll();
            billing.initiateHashMap();
            billing.randomizedBills();
            billing.assignRandomArrayToPanels();
            billing.assignIconsToPanels(0,billsApp.imageOne,billsApp.iconOne,billsOne);
            billing.assignIconsToPanels(1,billsApp.imageTwo,billsApp.iconTwo,billsTwo);
            billing.assignIconsToPanels(2,billsApp.imageThree,billsApp.iconThree,billsThree);
            billing.moveImageLabelsAround();
            billing.setRandomPrices();
            billing.billsButtonFormat();
            billing.enableBillsPanels();
        }
        if(e.getSource()==exit){
            setting.updateMoney();
            questionInt=JOptionPane.showConfirmDialog(null, "Are you sure want to close the application?", "Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(questionInt==0){
                frame.dispose();
            }
        }
        for (int i=0;i<10;i++){
            if(e.getSource()==numButtons[i]){
                calculator.calcField.setText(calculator.calcField.getText()+String.valueOf(i));
            }
        }
        if(e.getSource()==dotButton){
            calc.numString('.');
        }
        if(e.getSource()==deleteButton){
            calc.deleteEntry();
        }
        if(e.getSource()==clearButton){
            calc.clearEntries();
        }
        if(e.getSource()==enterButton){
            redPanel.removeAll();
            calc.enterTransaction();
            calculator enabCalc=new calculator();
            enabCalc.calcTextField(depositOrWithdraw);
            enabCalc.enableCalc();
        }
        if(e.getSource()==calculator.yesDeposit){
            deposit.depositMoneyYes();
            if(calculator.failedTransaction==false){
                calculator.yesDeposit.setVisible(false);
                calculator.noDeposit.setVisible(false);
                setting.updateMoney();
            }
        }
        if(e.getSource()==calculator.noDeposit){
            deposit.depositMoneyNo();
            if(calculator.failedTransaction==false){
                calculator.yesDeposit.setVisible(false);
                calculator.noDeposit.setVisible(false);
            }
        }
        if(e.getSource()==calculator.yesWithdraw){
            withdraw.withdrawMoneyYes();
            if (calculator.failedTransaction==false){
                calculator.yesWithdraw.setVisible(false);
                calculator.noWithdraw.setVisible(false);
                setting.updateMoney();
            }
        }
        if(e.getSource()==calculator.noWithdraw){
            if(calculator.failedTransaction==false){
                withdraw.withdrawMoneyNo();
                calculator.yesWithdraw.setVisible(false);
                calculator.noWithdraw.setVisible(false);
            }
        }
        for (int i=0;i<3;i++){
            if(e.getSource()==billing.yes[i]){
                billing.convertPriceStringToDouble(i);
                billing.paymentConfirmation(i);
            }
        }
        if(e.getSource()==cSApp.clickMe){
            cSApp.initiateNewAcc();
            cSApp.existingSecondAcc();
        }
        if (e.getSource()==transferApp.accButtons[0]){
            transferApp.accOne=true;
            transferApp.accTwo=false;
            depositOrWithdraw="NULL";
            calc.enableCalc();
            calc.calcTextField("RED");
        }
        if (e.getSource()==transferApp.accButtons[1]){
            transferApp.accOne=false;
            transferApp.accTwo=true;
            depositOrWithdraw="NULL";
            calc.enableCalc();
            calc.calcTextField("RED");
        }
        if(e.getSource()==transferApp.userConfirmation[0]){
            redPanel.removeAll();
            transferring.transferFunds();
            calc.enableCalc();
            calc.calcTextField("RED");
        }
        if(e.getSource()==transferApp.userConfirmation[1]){
            redPanel.removeAll();
            transferring.declineTransfer();
            calc.enableCalc();
            calc.calcTextField("RED");
        }
        if (e.getSource()==setting.settingsConfirmation[0]){
            setting.saveInfo();
        }
        if (e.getSource()==setting.settingsConfirmation[1]){
            setting.revertInfo();
        }
        if (e.getSource()==setting.changeButton[0]){
            setting.updateName();
            redPanel.removeAll();
            setting.initiateUserInfo();
            setting.saveButtonsFormat();
        }
        if (e.getSource()==setting.changeButton[1]){
            setting.updateDOB();
            redPanel.removeAll();
            setting.initiateUserInfo();
            setting.saveButtonsFormat();
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public static void main(String[] args){
        bankApplication bankApplication=new bankApplication();
        homeMenuApp home= new homeMenuApp();
        bankApplication.bankFrame();
        bankApplication.bluePanel();
        bankApplication.redPanel();
        home.welcomeUser();
        home.homeGIF();
        calc.calcNum();
        bankApplication.tabButtons(homeMenu, "Main Menu", "homeLogo.png",homeLogo,250);
        bankApplication.tabButtons(checkOrSaveButton,accCheckOrSave,bankApplication.cSString,checkOrSave, 325);
        bankApplication.tabButtons(deposits, "Deposit", "depositLogo.png",depositLogo, 400);
        bankApplication.tabButtons(withdrawals, "Withdrawal", "withdrawalLogo.png",withdrawalsLogo, 475);
        bankApplication.tabButtons(transfers, "Transfer", "transfer.png",transferLogo, 550);
        bankApplication.tabButtons(payBills, "Pay Bills", "billLogo.png",payBillsLogo, 625);
        bankApplication.tabButtons(settings, "Settings", "settings.png",settingsLogo, 700);
        bankApplication.tabButtons(exit, "Exit", "exit.png",exitLogo, 774);
        bankApplication.bankFrameCustomization();
        
    }

}

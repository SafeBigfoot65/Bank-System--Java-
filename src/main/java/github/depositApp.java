package github;

import java.text.DecimalFormat;
import javax.swing.*;

public class depositApp extends bankApplication {
    static JLabel depositConfirmation=new JLabel();
    static double tempMoney;
    String tempStr;
    final DecimalFormat dec=new DecimalFormat("0.00");
    //Performs transaction when user agrees
    void depositMoneyYes(){
        depositConfirmation.setText("Alright, the funds have been deposited successfully!");
        labelFormat(depositConfirmation,400,300,800,100,"GREEN");

        String temp=dec.format(money);
        money=Double.parseDouble(temp);
        tempMoney=money;
        money=money+calculator.transaction;
    }
    //Informs user that transaction won't go through
    void depositMoneyNo(){
        depositConfirmation.setText("Alright, the transaction won't go through!");
        labelFormat(depositConfirmation,350,300,800,100,"GREEN");
    }
}

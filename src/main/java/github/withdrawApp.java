package github;

import javax.swing.*;
import java.text.DecimalFormat;

public class withdrawApp extends bankApplication {
    JLabel withdrawConfirmation=new JLabel();
    static double tempMoney;
    final DecimalFormat dec=new DecimalFormat("0.00");
    //Performs transaction when user agrees
    void withdrawMoneyYes(){
        withdrawConfirmation.setText("Alright, the funds have been withdrawed successfully!");
        labelFormat(withdrawConfirmation,400,300,800,100,"RED");

        String temp=dec.format(money);
        money=Double.parseDouble(temp);
        tempMoney=money;
        money=money-calculator.transaction;
    }
    //Informs user that transaction won't go through
    void withdrawMoneyNo(){
        withdrawConfirmation.setText("Alright, the transaction won't go through!");
        labelFormat(withdrawConfirmation,350,300,800,100,"RED");
    }
}


package entity;

import java.io.Serializable;

public class Shop implements Serializable{
    private int income;
    private int countPurchases;

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getCountPurchases() {
        return countPurchases;
    }

    public void setCountPurchases(int countPurchases) {
        this.countPurchases = countPurchases;
    }
}

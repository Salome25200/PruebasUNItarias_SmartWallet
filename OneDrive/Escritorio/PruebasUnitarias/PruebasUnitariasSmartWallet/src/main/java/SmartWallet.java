class SmartWallet {

private double balance;
private String userType;
private boolean isActive;

        SmartWallet(String userType) {
        this.userType = userType;
        this.balance = 0.0;
        this.isActive = true;
        }

        boolean deposit(double amount) {
        if (amount <= 0) {
        return false;
        }

        double cashback = 0.0;
        if (amount > 100.0) {
        cashback = amount * 0.01;
        }

        double projectedBalance = this.balance + amount + cashback;

        if ("Standard".equalsIgnoreCase(this.userType) && projectedBalance > 5000.0) {
        return false;
        }

        this.balance = projectedBalance;

        if (!this.isActive && this.balance > 0) {
        this.isActive = true;
        }

        return true;
        }

        boolean withdraw(double amount) {
        if (amount <= 0) {
        return false;
        }

        if (amount > this.balance) {
        return false;
        }

        this.balance -= amount;

        if (Math.abs(this.balance) < 0.0001) {
        this.balance = 0.0;
        this.isActive = false;
        }

        return true;
        }

        double getBalance() {
        return balance;
        }

        boolean isActive() {
        return isActive;
        }
        }
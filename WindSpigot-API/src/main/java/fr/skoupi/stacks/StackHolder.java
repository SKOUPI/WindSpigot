package fr.skoupi.stacks;

/*
 *  * @Created on 2021 - 13:27
 *  * @Project SkSpigot
 *  * @Author jimmy  / vSKAH#0075
 */

public class StackHolder implements IStack {

    private int stackAmount = 1;

    @Override
    public int getStackAmount() {
        return stackAmount;
    }

    @Override
    public void setStackAmount(int amount) {
        stackAmount = amount;
    }

}
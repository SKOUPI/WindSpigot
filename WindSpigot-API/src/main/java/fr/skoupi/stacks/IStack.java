package fr.skoupi.stacks;

/*
 *  * @Created on 2021 - 13:19
 *  * @Project SkSpigot
 *  * @Author jimmy  / vSKAH#0075
 */

import net.minecraft.server.NBTTagCompound;

public interface IStack {

    int getStackAmount();

    void setStackAmount(int amount);

    default void substractStackAmount(int amount) {
        setStackAmount(getStackAmount() - amount);
    }

    default void incrementStackAmount(int amount) {
        setStackAmount(getStackAmount() + amount);
    }

    default void saveStackAmountNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInt("stackAmount", getStackAmount());
    }

    default void readStackAmount(NBTTagCompound nbtTagCompound) {
        if (nbtTagCompound.hasKey("stackAmount"))
            setStackAmount(nbtTagCompound.getInt("stackAmount"));
    }
}
package net.techcable.tacospigot;

import org.bukkit.Bukkit;

public class CompatHacks {

    private static CompatHacks instance;
    private boolean isChecked = false, isLoaded = false;

    public CompatHacks() {
        instance = this;
    }

    public boolean hasProtocolSupport() {
        return isChecked ? isLoaded : processCheck();
    }


    private boolean processCheck() {
        if (!isChecked) {
            isChecked = true;
            isLoaded = Bukkit.getPluginManager().isPluginEnabled("ProtocolSupport");
        }
        return isLoaded;
    }

    public static CompatHacks getInstance() {
        return instance == null ? new CompatHacks() : instance;
    }

}
package fr.skoupi.events.potions;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;

/*  PotionEvent
 *  By: vSKAH <vskahhh@gmail.com>

 * Created with IntelliJ IDEA
 * For the project WindSpigot

 * Created At 04/11/2022 17:21:26 */
public abstract class PotionEvent extends Event implements Cancellable {

    protected Entity entity;
    protected PotionEffect potionEffect;

    public PotionEvent (Entity entity, PotionEffect potionEffect)
    {
        this.entity = entity;
        this.potionEffect = potionEffect;
    }

    public Entity getEntity() {
        return entity;
    }

    public PotionEffect getPotionEffect ()
    {
        return potionEffect;
    }

    private boolean cancelled = false;
    private static final HandlerList handlerList = new HandlerList();


    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }


    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}

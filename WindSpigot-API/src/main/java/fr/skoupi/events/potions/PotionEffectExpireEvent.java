package fr.skoupi.events.potions;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;

/*  PotionEffectRemoveEvent
 *  By: vSKAH <vskahhh@gmail.com>

 * Created with IntelliJ IDEA
 * For the project WindSpigot

 * Created At 04/11/2022 17:30:35 */
public class PotionEffectExpireEvent extends PotionEvent {

    private int duration;
    public PotionEffectExpireEvent (Entity entity, PotionEffect potionEffect)
    {
        super(entity, potionEffect);
    }

    public int getDuration ()
    {
        return duration;
    }

    public void setDuration (int duration)
    {
        this.duration = duration;
    }
}

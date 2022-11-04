package fr.skoupi.events.potions;

import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffect;

/*  PotionEffectAddEvent
 *  By: vSKAH <vskahhh@gmail.com>

 * Created with IntelliJ IDEA
 * For the project WindSpigot

 * Created At 04/11/2022 17:14:44 */
public class PotionEffectAddEvent extends PotionEvent {

    public PotionEffectAddEvent (Entity entity, PotionEffect potionEffect)
    {
        super(entity, potionEffect);
    }
}

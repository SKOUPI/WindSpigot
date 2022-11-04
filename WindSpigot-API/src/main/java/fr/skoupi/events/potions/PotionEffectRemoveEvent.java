package fr.skoupi.events.potions;

import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffect;

/*  PotionEffectRemoveEvent
 *  By: vSKAH <vskahhh@gmail.com>

 * Created with IntelliJ IDEA
 * For the project WindSpigot

 * Created At 04/11/2022 17:30:35 */
public class PotionEffectRemoveEvent extends PotionEvent {

	public PotionEffectRemoveEvent (Entity entity, PotionEffect potionEffect)
	{
		super(entity, potionEffect);
	}


}

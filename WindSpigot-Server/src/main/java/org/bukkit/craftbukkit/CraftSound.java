package org.bukkit.craftbukkit;

import static org.bukkit.Sound.*;

import org.apache.commons.lang3.Validate;import org.bukkit.Sound;

public class CraftSound {
	private static final String[] sounds = new String[Sound.values().length];

	static {
		// Ambient
		set(AMBIENCE_CAVE, "ambient.cave.cave");
		set(AMBIENCE_RAIN, "ambient.weather.rain");
		set(AMBIENCE_THUNDER, "ambient.weather.thunder");
		// Damage
		set(HURT_FLESH, "game.neutral.hurt");
		set(FALL_BIG, "game.neutral.hurt.fall.big");
		set(FALL_SMALL, "game.neutral.hurt.fall.small");
		// Dig Sounds
		set(DIG_WOOL, "dig.cloth");
		set(DIG_GRASS, "dig.grass");
		set(DIG_GRAVEL, "dig.gravel");
		set(DIG_SAND, "dig.sand");
		set(DIG_SNOW, "dig.snow");
		set(DIG_STONE, "dig.stone");
		set(DIG_WOOD, "dig.wood");
		// Fire
		set(FIRE, "fire.fire");
		set(FIRE_IGNITE, "fire.ignite");
		// Fireworks
		set(FIREWORK_BLAST, "fireworks.blast");
		set(FIREWORK_BLAST2, "fireworks.blast_far");
		set(FIREWORK_LARGE_BLAST, "fireworks.largeBlast");
		set(FIREWORK_LARGE_BLAST2, "fireworks.largeBlast_far");
		set(FIREWORK_TWINKLE, "fireworks.twinkle");
		set(FIREWORK_TWINKLE2, "fireworks.twinkle_far");
		set(FIREWORK_LAUNCH, "fireworks.launch");
		// Liquid
		set(SPLASH2, "game.neutral.swim.splash");
		set(SWIM, "game.neutral.swim");
		set(WATER, "liquid.water");
		set(LAVA, "liquid.lava");
		set(LAVA_POP, "liquid.lavapop");
		// Minecart
		set(MINECART_BASE, "minecart.base");
		set(MINECART_INSIDE, "minecart.inside");
		// Mob
		set(BAT_DEATH, "mob.bat.death");
		set(BAT_HURT, "mob.bat.hurt");
		set(BAT_IDLE, "mob.bat.idle");
		set(BAT_LOOP, "mob.bat.loop");
		set(BAT_TAKEOFF, "mob.bat.takeoff");
		set(BLAZE_BREATH, "mob.blaze.breathe");
		set(BLAZE_DEATH, "mob.blaze.death");
		set(BLAZE_HIT, "mob.blaze.hit");
		set(CAT_HISS, "mob.cat.hiss");
		set(CAT_HIT, "mob.cat.hitt");
		set(CAT_MEOW, "mob.cat.meow");
		set(CAT_PURR, "mob.cat.purr");
		set(CAT_PURREOW, "mob.cat.purreow");
		set(CHICKEN_IDLE, "mob.chicken.say");
		set(CHICKEN_HURT, "mob.chicken.hurt");
		set(CHICKEN_EGG_POP, "mob.chicken.plop");
		set(CHICKEN_WALK, "mob.chicken.step");
		set(COW_HURT, "mob.cow.hurt");
		set(COW_IDLE, "mob.cow.say");
		set(COW_WALK, "mob.cow.step");
		set(CREEPER_DEATH, "mob.creeper.death");
		set(CREEPER_HISS, "mob.creeper.say");
		set(ENDERDRAGON_DEATH, "mob.enderdragon.end");
		set(ENDERDRAGON_GROWL, "mob.enderdragon.growl");
		set(ENDERDRAGON_HIT, "mob.enderdragon.hit");
		set(ENDERDRAGON_WINGS, "mob.enderdragon.wings");
		set(ENDERMAN_DEATH, "mob.endermen.death");
		set(ENDERMAN_HIT, "mob.endermen.hit");
		set(ENDERMAN_IDLE, "mob.endermen.idle");
		set(ENDERMAN_TELEPORT, "mob.endermen.portal");
		set(ENDERMAN_SCREAM, "mob.endermen.scream");
		set(ENDERMAN_STARE, "mob.endermen.stare");
		set(GHAST_SCREAM2, "mob.ghast.affectionate_scream");
		set(GHAST_CHARGE, "mob.ghast.charge");
		set(GHAST_DEATH, "mob.ghast.death");
		set(GHAST_FIREBALL, "mob.ghast.fireball");
		set(GHAST_MOAN, "mob.ghast.moan");
		set(GHAST_SCREAM, "mob.ghast.scream");
		set(HORSE_ANGRY, "mob.horse.angry");
		set(HORSE_ARMOR, "mob.horse.armor");
		set(HORSE_BREATHE, "mob.horse.breathe");
		set(HORSE_DEATH, "mob.horse.death");
		set(HORSE_GALLOP, "mob.horse.gallop");
		set(HORSE_HIT, "mob.horse.hit");
		set(HORSE_IDLE, "mob.horse.idle");
		set(HORSE_JUMP, "mob.horse.jump");
		set(HORSE_LAND, "mob.horse.land");
		set(HORSE_SADDLE, "mob.horse.leather");
		set(HORSE_SOFT, "mob.horse.soft");
		set(HORSE_WOOD, "mob.horse.wood");
		set(DONKEY_ANGRY, "mob.horse.donkey.angry");
		set(DONKEY_DEATH, "mob.horse.donkey.death");
		set(DONKEY_HIT, "mob.horse.donkey.hit");
		set(DONKEY_IDLE, "mob.horse.donkey.idle");
		set(HORSE_SKELETON_DEATH, "mob.horse.skeleton.death");
		set(HORSE_SKELETON_HIT, "mob.horse.skeleton.hit");
		set(HORSE_SKELETON_IDLE, "mob.horse.skeleton.idle");
		set(HORSE_ZOMBIE_DEATH, "mob.horse.zombie.death");
		set(HORSE_ZOMBIE_HIT, "mob.horse.zombie.hit");
		set(HORSE_ZOMBIE_IDLE, "mob.horse.zombie.idle");
		set(IRONGOLEM_DEATH, "mob.irongolem.death");
		set(IRONGOLEM_HIT, "mob.irongolem.hit");
		set(IRONGOLEM_THROW, "mob.irongolem.throw");
		set(IRONGOLEM_WALK, "mob.irongolem.walk");
		set(MAGMACUBE_WALK, "mob.magmacube.small");
		set(MAGMACUBE_WALK2, "mob.magmacube.big");
		set(MAGMACUBE_JUMP, "mob.magmacube.jump");
		set(PIG_IDLE, "mob.pig.say");
		set(PIG_DEATH, "mob.pig.death");
		set(PIG_WALK, "mob.pig.step");
		set(SHEEP_IDLE, "mob.sheep.say");
		set(SHEEP_SHEAR, "mob.sheep.shear");
		set(SHEEP_WALK, "mob.sheep.step");
		set(SILVERFISH_HIT, "mob.silverfish.hit");
		set(SILVERFISH_KILL, "mob.silverfish.kill");
		set(SILVERFISH_IDLE, "mob.silverfish.say");
		set(SILVERFISH_WALK, "mob.silverfish.step");
		set(SKELETON_IDLE, "mob.skeleton.say");
		set(SKELETON_DEATH, "mob.skeleton.death");
		set(SKELETON_HURT, "mob.skeleton.hurt");
		set(SKELETON_WALK, "mob.skeleton.step");
		set(SLIME_ATTACK, "mob.slime.attack");
		set(SLIME_WALK, "mob.slime.small");
		set(SLIME_WALK2, "mob.slime.big");
		set(SPIDER_IDLE, "mob.spider.say");
		set(SPIDER_DEATH, "mob.spider.death");
		set(SPIDER_WALK, "mob.spider.step");
		set(VILLAGER_DEATH, "mob.villager.death");
		set(VILLAGER_HAGGLE, "mob.villager.haggle");
		set(VILLAGER_HIT, "mob.villager.hit");
		set(VILLAGER_IDLE, "mob.villager.idle");
		set(VILLAGER_NO, "mob.villager.no");
		set(VILLAGER_YES, "mob.villager.yes");
		set(WITHER_DEATH, "mob.wither.death");
		set(WITHER_HURT, "mob.wither.hurt");
		set(WITHER_IDLE, "mob.wither.idle");
		set(WITHER_SHOOT, "mob.wither.shoot");
		set(WITHER_SPAWN, "mob.wither.spawn");
		set(WOLF_BARK, "mob.wolf.bark");
		set(WOLF_DEATH, "mob.wolf.death");
		set(WOLF_GROWL, "mob.wolf.growl");
		set(WOLF_HOWL, "mob.wolf.howl");
		set(WOLF_HURT, "mob.wolf.hurt");
		set(WOLF_PANT, "mob.wolf.panting");
		set(WOLF_SHAKE, "mob.wolf.shake");
		set(WOLF_WALK, "mob.wolf.step");
		set(WOLF_WHINE, "mob.wolf.whine");
		set(ZOMBIE_METAL, "mob.zombie.metal");
		set(ZOMBIE_WOOD, "mob.zombie.wood");
		set(ZOMBIE_WOODBREAK, "mob.zombie.woodbreak");
		set(ZOMBIE_IDLE, "mob.zombie.say");
		set(ZOMBIE_DEATH, "mob.zombie.death");
		set(ZOMBIE_HURT, "mob.zombie.hurt");
		set(ZOMBIE_INFECT, "mob.zombie.infect");
		set(ZOMBIE_UNFECT, "mob.zombie.unfect");
		set(ZOMBIE_REMEDY, "mob.zombie.remedy");
		set(ZOMBIE_WALK, "mob.zombie.step");
		set(ZOMBIE_PIG_IDLE, "mob.zombiepig.zpig");
		set(ZOMBIE_PIG_ANGRY, "mob.zombiepig.zpigangry");
		set(ZOMBIE_PIG_DEATH, "mob.zombiepig.zpigdeath");
		set(ZOMBIE_PIG_HURT, "mob.zombiepig.zpighurt");
		// Note (blocks)
		set(NOTE_BASS_GUITAR, "note.bassattack");
		set(NOTE_SNARE_DRUM, "note.snare");
		set(NOTE_PLING, "note.pling");
		set(NOTE_BASS, "note.bass");
		set(NOTE_PIANO, "note.harp");
		set(NOTE_BASS_DRUM, "note.bd");
		set(NOTE_STICKS, "note.hat");
		// Portal
		set(PORTAL, "portal.portal");
		set(PORTAL_TRAVEL, "portal.travel");
		set(PORTAL_TRIGGER, "portal.trigger");
		// Random
		set(ANVIL_BREAK, "random.anvil_break");
		set(ANVIL_LAND, "random.anvil_land");
		set(ANVIL_USE, "random.anvil_use");
		set(SHOOT_ARROW, "random.bow");
		set(ARROW_HIT, "random.bowhit");
		set(ITEM_BREAK, "random.break");
		set(BURP, "random.burp");
		set(CHEST_CLOSE, "random.chestclosed");
		set(CHEST_OPEN, "random.chestopen");
		set(CLICK, "random.click");
		set(DOOR_CLOSE, "random.door_close");
		set(DOOR_OPEN, "random.door_open");
		set(DRINK, "random.drink");
		set(EAT, "random.eat");
		set(EXPLODE, "random.explode");
		set(FIZZ, "random.fizz");
		set(FUSE, "creeper.primed");
		set(GLASS, "dig.glass");
		set(LEVEL_UP, "random.levelup");
		set(ORB_PICKUP, "random.orb");
		set(ITEM_PICKUP, "random.pop");
		set(SPLASH, "random.splash");
		set(SUCCESSFUL_HIT, "random.successful_hit");
		set(WOOD_CLICK, "random.wood_click");
		// Step
		set(STEP_WOOL, "step.cloth");
		set(STEP_GRASS, "step.grass");
		set(STEP_GRAVEL, "step.gravel");
		set(STEP_LADDER, "step.ladder");
		set(STEP_SAND, "step.sand");
		set(STEP_SNOW, "step.snow");
		set(STEP_STONE, "step.stone");
		set(STEP_WOOD, "step.wood");
		// Tile
		set(PISTON_EXTEND, "tile.piston.out");
		set(PISTON_RETRACT, "tile.piston.in");
	}

	private static void set(Sound sound, String key) {
		sounds[sound.ordinal()] = key;
	}

	public static String getSound(final Sound sound) {
		Validate.notNull(sound, "Sound cannot be null");
		return sounds[sound.ordinal()];
	}

	private CraftSound() {
	}
}

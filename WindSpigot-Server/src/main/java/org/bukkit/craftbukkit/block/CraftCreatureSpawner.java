package org.bukkit.craftbukkit.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.EntityType;

import net.minecraft.server.TileEntityMobSpawner;

public class CraftCreatureSpawner extends CraftBlockState implements CreatureSpawner {
	private final TileEntityMobSpawner spawner;

	public CraftCreatureSpawner(final Block block) {
		super(block);

		spawner = (TileEntityMobSpawner) ((CraftWorld) block.getWorld()).getTileEntityAt(getX(), getY(), getZ());
	}

	public CraftCreatureSpawner(final Material material, TileEntityMobSpawner te) {
		super(material);
		spawner = te;
	}

	@Override
	@Deprecated
	public CreatureType getCreatureType() {
		return CreatureType.fromName(spawner.getSpawner().getMobName());
	}

	@Override
	public EntityType getSpawnedType() {
		return EntityType.fromName(spawner.getSpawner().getMobName());
	}

	@Override
	@Deprecated
	public void setCreatureType(CreatureType creatureType) {
		spawner.getSpawner().setMobName(creatureType.getName());
	}

	@Override
	public void setSpawnedType(EntityType entityType) {
		if (entityType == null || entityType.getName() == null) {
			throw new IllegalArgumentException("Can't spawn EntityType " + entityType + " from mobspawners!");
		}

		spawner.getSpawner().setMobName(entityType.getName());
	}

	@Override
	@Deprecated
	public String getCreatureTypeId() {
		return spawner.getSpawner().getMobName();
	}

	@Override
	@Deprecated
	public void setCreatureTypeId(String creatureName) {
		setCreatureTypeByName(creatureName);
	}

	@Override
	public String getCreatureTypeName() {
		return spawner.getSpawner().getMobName();
	}

	@Override
	public void setCreatureTypeByName(String creatureType) {
		// Verify input
		EntityType type = EntityType.fromName(creatureType);
		if (type == null) {
			return;
		}
		setSpawnedType(type);
	}

	@Override
	public int getDelay() {
		return spawner.getSpawner().spawnDelay;
	}

	@Override
	public void setDelay(int delay) {
		spawner.getSpawner().spawnDelay = delay;
	}

	@Override
	public int getMinDelay() {
		return spawner.getSpawner().getMinSpawnDelay();
	}

	@Override
	public void setMinDelay(int minDelay) {
		spawner.getSpawner().setMinSpawnDelay(minDelay);
	}

	@Override
	public int getMaxDelay() {
		return spawner.getSpawner().getMaxSpawnDelay();
	}

	@Override
	public void setMaxDelay(int maxDelay) {
		spawner.getSpawner().setMaxSpawnDelay(maxDelay);
	}

	@Override
	public int getSpawnCount() {
		return spawner.getSpawner().getSpawnCount();
	}

	@Override
	public void setSpawnCount(int spawnCount) {
		spawner.getSpawner().setSpawnCount(spawnCount);
	}

	@Override
	public int getMaxNearbyEntity() {
		return spawner.getSpawner().getMaxNearbyEntities();
	}

	@Override
	public void setMaxNearbyEntity(int maxNearbyEntity) {
		spawner.getSpawner().setMaxNearbyEntities(maxNearbyEntity);
	}

	@Override
	public int getRequiredPlayerRange() {
		return spawner.getSpawner().getRequiredPlayerRange();
	}

	@Override
	public void setRequiredPlayerRange(int requiredPlayerRange) {
		spawner.getSpawner().setRequiredPlayerRange(requiredPlayerRange);
	}

	@Override
	public int getSpawnRange() {
		return spawner.getSpawner().getSpawnRange();
	}

	@Override
	public void setSpawnRange(int spawnRange) {
		spawner.getSpawner().setSpawnRange(spawnRange);
	}

	@Override
	public int getCurse() {
		return spawner.getSpawner().getMobCurse();
	}

	@Override
	public void setCurse(int curse) {
		spawner.getSpawner().setMobCurse(curse);
	}

	@Override
	public TileEntityMobSpawner getTileEntity() {
		return spawner;
	}
}

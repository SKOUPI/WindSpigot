package org.bukkit.craftbukkit.scoreboard;

import org.apache.commons.lang3.Validate;import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import net.minecraft.server.Scoreboard;
import net.minecraft.server.ScoreboardObjective;

final class CraftObjective extends CraftScoreboardComponent implements Objective {
	private final ScoreboardObjective objective;
	private final CraftCriteria criteria;

	CraftObjective(CraftScoreboard scoreboard, ScoreboardObjective objective) {
		super(scoreboard);
		this.objective = objective;
		this.criteria = CraftCriteria.getFromNMS(objective);
	}

	ScoreboardObjective getHandle() {
		return objective;
	}

	@Override
	public String getName() throws IllegalStateException {
		CraftScoreboard scoreboard = checkState();

		return objective.getName();
	}

	@Override
	public String getDisplayName() throws IllegalStateException {
		CraftScoreboard scoreboard = checkState();

		return objective.getDisplayName();
	}

	@Override
	public void setDisplayName(String displayName) throws IllegalStateException, IllegalArgumentException {
		Validate.notNull(displayName, "Display name cannot be null");
		Validate.isTrue(displayName.length() <= 32,
				"Display name '" + displayName + "' is longer than the limit of 32 characters");
		CraftScoreboard scoreboard = checkState();

		objective.setDisplayName(displayName);
	}

	@Override
	public String getCriteria() throws IllegalStateException {
		CraftScoreboard scoreboard = checkState();

		return criteria.bukkitName;
	}

	@Override
	public boolean isModifiable() throws IllegalStateException {
		CraftScoreboard scoreboard = checkState();

		return !criteria.criteria.isReadOnly();
	}

	@Override
	public void setDisplaySlot(DisplaySlot slot) throws IllegalStateException {
		CraftScoreboard scoreboard = checkState();
		Scoreboard board = scoreboard.board;
		ScoreboardObjective objective = this.objective;

		for (int i = 0; i < CraftScoreboardTranslations.MAX_DISPLAY_SLOT; i++) {
			if (board.getObjectiveForSlot(i) == objective) {
				board.setDisplaySlot(i, null);
			}
		}
		if (slot != null) {
			int slotNumber = CraftScoreboardTranslations.fromBukkitSlot(slot);
			board.setDisplaySlot(slotNumber, getHandle());
		}
	}

	@Override
	public DisplaySlot getDisplaySlot() throws IllegalStateException {
		CraftScoreboard scoreboard = checkState();
		Scoreboard board = scoreboard.board;
		ScoreboardObjective objective = this.objective;

		for (int i = 0; i < CraftScoreboardTranslations.MAX_DISPLAY_SLOT; i++) {
			if (board.getObjectiveForSlot(i) == objective) {
				return CraftScoreboardTranslations.toBukkitSlot(i);
			}
		}
		return null;
	}

	@Override
	public Score getScore(OfflinePlayer player) throws IllegalArgumentException, IllegalStateException {
		Validate.notNull(player, "Player cannot be null");
		CraftScoreboard scoreboard = checkState();

		return new CraftScore(this, player.getName());
	}

	@Override
	public Score getScore(String entry) throws IllegalArgumentException, IllegalStateException {
		Validate.notNull(entry, "Entry cannot be null");
		if (entry.length() > 40) {
			throw new IllegalArgumentException("Entry cannot be longer than 40 characters!"); // Spigot
		}
		CraftScoreboard scoreboard = checkState();

		return new CraftScore(this, entry);
	}

	@Override
	public void unregister() throws IllegalStateException {
		CraftScoreboard scoreboard = checkState();

		scoreboard.board.unregisterObjective(objective);
	}

	@Override
	CraftScoreboard checkState() throws IllegalStateException {
		if (getScoreboard().board.getObjective(objective.getName()) == null) {
			throw new IllegalStateException("Unregistered scoreboard component");
		}

		return getScoreboard();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + (this.objective != null ? this.objective.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final CraftObjective other = (CraftObjective) obj;
		return !(this.objective != other.objective
				&& (this.objective == null || !this.objective.equals(other.objective)));
	}

}

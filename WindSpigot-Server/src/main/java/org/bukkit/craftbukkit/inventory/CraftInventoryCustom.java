package org.bukkit.craftbukkit.inventory;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;

import net.minecraft.server.ChatComponentText;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.IChatBaseComponent;
import net.minecraft.server.IInventory;
import net.minecraft.server.ItemStack;

public class CraftInventoryCustom extends CraftInventory {
	public CraftInventoryCustom(InventoryHolder owner, InventoryType type) {
		super(new MinecraftInventory(owner, type));
	}

	public CraftInventoryCustom(InventoryHolder owner, InventoryType type, String title) {
		super(new MinecraftInventory(owner, type, title));
	}

	public CraftInventoryCustom(InventoryHolder owner, int size) {
		super(new MinecraftInventory(owner, size));
	}

	public CraftInventoryCustom(InventoryHolder owner, int size, String title) {
		super(new MinecraftInventory(owner, size, title));
	}

	static class MinecraftInventory implements IInventory {
		private final ItemStack[] items;
		private int maxStack = MAX_STACK;
		private final List<HumanEntity> viewers;
		private final String title;
		private InventoryType type;
		private final InventoryHolder owner;

		public MinecraftInventory(InventoryHolder owner, InventoryType type) {
			this(owner, type.getDefaultSize(), type.getDefaultTitle());
			this.type = type;
		}

		public MinecraftInventory(InventoryHolder owner, InventoryType type, String title) {
			this(owner, type.getDefaultSize(), title);
			this.type = type;
		}

		public MinecraftInventory(InventoryHolder owner, int size) {
			this(owner, size, "Chest");
		}

		public MinecraftInventory(InventoryHolder owner, int size, String title) {
			Validate.notNull(title, "Title cannot be null");
			this.items = new ItemStack[size];
			this.title = title;
			this.viewers = new ArrayList<HumanEntity>();
			this.owner = owner;
			this.type = InventoryType.CHEST;
		}

		@Override
		public int getSize() {
			return items.length;
		}

		@Override
		public ItemStack getItem(int i) {
			return items[i];
		}

		@Override
		public ItemStack splitStack(int i, int j) {
			ItemStack stack = this.getItem(i);
			ItemStack result;
			if (stack == null) {
				return null;
			}
			if (stack.count <= j) {
				this.setItem(i, null);
				result = stack;
			} else {
				result = CraftItemStack.copyNMSStack(stack, j);
				stack.count -= j;
			}
			this.update();
			return result;
		}

		@Override
		public ItemStack splitWithoutUpdate(int i) {
			ItemStack stack = this.getItem(i);
			ItemStack result;
			if (stack == null) {
				return null;
			}
			if (stack.count <= 1) {
				this.setItem(i, null);
				result = stack;
			} else {
				result = CraftItemStack.copyNMSStack(stack, 1);
				stack.count -= 1;
			}
			return result;
		}

		@Override
		public void setItem(int i, ItemStack itemstack) {
			items[i] = itemstack;
			if (itemstack != null && this.getMaxStackSize() > 0 && itemstack.count > this.getMaxStackSize()) {
				itemstack.count = this.getMaxStackSize();
			}
		}

		@Override
		public int getMaxStackSize() {
			return maxStack;
		}

		@Override
		public void setMaxStackSize(int size) {
			maxStack = size;
		}

		@Override
		public void update() {
		}

		@Override
		public boolean a(EntityHuman entityhuman) {
			return true;
		}

		@Override
		public ItemStack[] getContents() {
			return items;
		}

		@Override
		public void onOpen(CraftHumanEntity who) {
			viewers.add(who);
		}

		@Override
		public void onClose(CraftHumanEntity who) {
			viewers.remove(who);
		}

		@Override
		public List<HumanEntity> getViewers() {
			return viewers;
		}

		public InventoryType getType() {
			return type;
		}

		@Override
		public InventoryHolder getOwner() {
			return owner;
		}

		@Override
		public boolean b(int i, ItemStack itemstack) {
			return true;
		}

		@Override
		public void startOpen(EntityHuman entityHuman) {

		}

		@Override
		public void closeContainer(EntityHuman entityHuman) {

		}

		@Override
		public int getProperty(int i) {
			return 0;
		}

		@Override
		public void b(int i, int i1) {

		}

		@Override
		public int g() {
			return 0;
		}

		@Override
		public void l() {

		}

		@Override
		public String getName() {
			return title;
		}

		@Override
		public boolean hasCustomName() {
			return title != null;
		}

		@Override
		public IChatBaseComponent getScoreboardDisplayName() {
			return new ChatComponentText(title);
		}
	}
}

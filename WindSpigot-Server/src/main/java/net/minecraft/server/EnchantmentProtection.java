package net.minecraft.server;

/*  EnchantmentProtection
 * By: vSKAH <vskahhh@gmail.com>
 * Created with IntelliJ IDEA
 * For the project WindSpigot
 */

public class EnchantmentProtection extends Enchantment {
    private static final String[] E = new String[]{"all", "fire", "fall", "explosion", "projectile"};
    private static final int[] F = new int[]{1, 10, 5, 5, 3};
    private static final int[] G = new int[]{11, 8, 6, 8, 6};
    private static final int[] H = new int[]{20, 12, 10, 12, 15};
    public final int a;

    public EnchantmentProtection(int var1, MinecraftKey var2, int var3, int var4) {
        super(var1, var2, var3, EnchantmentSlotType.ARMOR);
        this.a = var4;
        if (var4 == 2) {
            this.slot = EnchantmentSlotType.ARMOR_FEET;
        }

    }

    public int a(int var1) {
        return F[this.a] + (var1 - 1) * G[this.a];
    }

    public int b(int var1) {
        return this.a(var1) + H[this.a];
    }

    public int getMaxLevel() {
        return 4;
    }

    public int a(int var1, DamageSource var2) {
        if (var2.ignoresInvulnerability()) {
            return 0;
        } else {
            float var3 = (float)(6 + var1 * var1) / 3.0F;
            if (this.a == 0) {
                return (int) var3;
            } else if (this.a == 1 && var2.o()) {
                return MathHelper.d(var3 * 1.25F);
            } else if (this.a == 2 && var2 == DamageSource.FALL) {
                return MathHelper.d(var3 * 2.5F);
            } else if (this.a == 3 && var2.isExplosion()) {
                return MathHelper.d(var3 * 1.5F);
            } else {
                return this.a == 4 && var2.a() ? MathHelper.d(var3 * 1.5F) : 0;
            }
        }
    }

    public String a() {
        return "enchantment.protect." + E[this.a];
    }

    public boolean a(Enchantment var1) {
        if (var1 instanceof EnchantmentProtection) {
            EnchantmentProtection var2 = (EnchantmentProtection)var1;
            if (var2.a == this.a) {
                return false;
            } else {
                return this.a == 2 || var2.a == 2;
            }
        } else {
            return super.a(var1);
        }
    }

    //Fire protection
    public static int a(Entity var0, int var1) {
        int var2 = EnchantmentManager.a(Enchantment.PROTECTION_FIRE.id, var0.getEquipment());
        if (var2 > 0) {
            var1 -= MathHelper.d((float)var1 * (float)var2 * 0.15F);
        }

        return var1;
    }

    //Blast Protection
    public static double a(Entity var0, double var1) {
        int var3 = EnchantmentManager.a(Enchantment.PROTECTION_EXPLOSIONS.id, var0.getEquipment());
        if (var3 > 0) {
            var1 -= MathHelper.floor(var1 * (double)((float)var3 * 0.15F));
        }

        return var1;
    }
}

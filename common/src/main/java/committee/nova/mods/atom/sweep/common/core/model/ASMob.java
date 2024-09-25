package committee.nova.mods.atom.sweep.common.core.model;


import committee.nova.mods.atom.sweep.common.config.ModConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;


/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/4/8 15:03
 * Version: 1.0
 */
public class ASMob {
    private final Mob entity;
    private final ResourceLocation registryName;

    public ASMob(Mob entity) {
        this.entity = entity;
        this.registryName = EntityType.getKey(entity.getType());
    }

    public boolean filtrate() {
        if (ModConfig.mobWhiteMode.get()) {
            // Whitelist
            for (String s : ModConfig.mobEntitiesWhitelist.get()) {
                if (mobMatch(s, this.registryName)) return false;
            }
            return true;
        }
        if (ModConfig.mobBlackMode.get()) {
            // Blacklist
            for (String s : ModConfig.mobEntitiesBlacklist.get()) {
                if (ASItem.itemMatch(s, this.registryName)) return true;
            }
            return false;
        }
        return true;
    }

    static boolean mobMatch(String s, ResourceLocation registryName) {
        int index;
        if (s.equals(registryName.toString())) {
            return true;
        } else if ((index = s.indexOf('*')) != -1) {
            s = s.substring(0, index - 1);
            return registryName.getNamespace().equals(s);
        }
        return false;
    }

    public Mob getEntity() {
        return entity;
    }

    public ResourceLocation getRegistryName() {
        return registryName;
    }
}

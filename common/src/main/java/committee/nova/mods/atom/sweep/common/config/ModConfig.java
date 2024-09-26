package committee.nova.mods.atom.sweep.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/4/8 21:38
 * Version: 1.0
 */

public class ModConfig {

    public static final ForgeConfigSpec COMMON;


    public static final ForgeConfigSpec.IntValue sweepPeriod;
    public static final ForgeConfigSpec.IntValue sweepNotify;
    public static final ForgeConfigSpec.IntValue sweepDiscount;
    public static final ForgeConfigSpec.ConfigValue<String> sweepNotice;
    public static final ForgeConfigSpec.ConfigValue<String> sweepNoticeComplete;

    public static final ForgeConfigSpec.BooleanValue isItemEntityCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue itemWhiteMode;
    public static final ForgeConfigSpec.BooleanValue itemBlackMode;
    public static final ForgeConfigSpec.ConfigValue<List<String>> itemEntitiesWhitelist;
    public static final ForgeConfigSpec.ConfigValue<List<String>> itemEntitiesBlacklist;

    public static final ForgeConfigSpec.BooleanValue isExpOn;
    public static final ForgeConfigSpec.BooleanValue isMobEntityCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue isMonsterEntitiesCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue isAnimalEntitiesCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue mobWhiteMode;
    public static final ForgeConfigSpec.BooleanValue mobBlackMode;
    public static final ForgeConfigSpec.ConfigValue<List<String>> mobEntitiesWhitelist;
    public static final ForgeConfigSpec.ConfigValue<List<String>> mobEntitiesBlacklist;

    public static final ForgeConfigSpec.BooleanValue isExperienceOrbEntityCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue isFallingBlocksEntityCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue isArrowEntityCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue isTridentEntityCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue isDamagingProjectileEntityCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue isShulkerBulletEntityCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue isFireworkRocketEntityCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue isItemFrameEntityCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue isPaintingEntityCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue isBoatEntityCleanupEnable;
    public static final ForgeConfigSpec.BooleanValue isTNTEntityCleanupEnable;

    //SERVER
    static {
        final var common = new ForgeConfigSpec.Builder();
        common.comment("Atom Sweep Config");
        common.push("common");
        sweepPeriod = buildInt(common, "sweepPeriod", 4, 0, 100000, "扫地周期（分钟）");
        sweepNotify = buildInt(common, "sweepNotify", 20, 0, 100000, "提前通知时间（秒）");
        sweepDiscount = buildInt(common, "sweepDiscount", 5, 0, 100000, "a");
        sweepNotice = buildString(common, "sweepNotice", "<演变> 注意：还有 %d 秒就要去你家吃饭了~",
                "通知提示");
        sweepNoticeComplete = buildString(common, "sweepNoticeComplete", "<演变> 这次一共吃掉了 %d 个掉落物， %d 个生物 %d 个经验球和 %d 个其他实体~",
                "清扫完通知提示");
        common.pop();
        common.push("item");
        isItemEntityCleanupEnable = buildBoolean(common, "isItemEntityCleanupEnable", true, "物品实体清理功能");
        itemWhiteMode = buildBoolean(common, "itemWhiteMode", true, "白名单模式");
        itemBlackMode = buildBoolean(common, "itemBlackMode", true, "黑名单模式");
        itemEntitiesWhitelist = buildStrings(common, "itemEntitiesWhitelist", Arrays.asList("minecraft:diamond", "minecraft:emerald"), "白名单(优先级高于黑名单),使用<modid:*>实现通配符功能");
        itemEntitiesBlacklist = buildStrings(common, "itemEntitiesBlacklist", Collections.emptyList(), "黑名单,使用<modid:*>实现通配符功能");
        common.pop();
        common.push("entity");
        isMobEntityCleanupEnable = buildBoolean(common, "isAnimalEntitiesCleanupEnable", true, "生物实体清理功能(最高优先级)");
        isExpOn = buildBoolean(common, "isExpOn", false, "生物清理是否掉落经验");
        isAnimalEntitiesCleanupEnable = buildBoolean(common, "isMonsterEntitiesCleanupEnable", true, "动物实体清理功能(次级)");
        isMonsterEntitiesCleanupEnable = buildBoolean(common, "isMobEntityCleanupEnable", true, "怪物实体清理功能(次级)");
        mobWhiteMode = buildBoolean(common, "mobWhiteMode", true, "白名单模式");
        mobBlackMode = buildBoolean(common, "mobBlackMode", true, "黑名单模式");
        mobEntitiesWhitelist = buildStrings(common, "mobEntitiesWhitelist", Arrays.asList("minecraft:chicken", "minecraft:cat", "minecraft:mule", "minecraft:wolf", "minecraft:horse", "minecraft:donkey",
                "minecraft:wither", "minecraft:guardian", "minecraft:villager", "minecraft:iron_golem", "minecraft:snow_golem",
                "minecraft:vindicator", "minecraft:ender_dragon", "minecraft:elder_guardian"), "生物白名单(优先级高于黑名单),使用<modid:*>实现通配符功能");
        mobEntitiesBlacklist = buildStrings(common, "mobEntitiesBlacklist", Collections.emptyList(), "生物黑名单,使用<modid:*>实现通配符功能");
        common.pop();
        common.push("others");
        isExperienceOrbEntityCleanupEnable = buildBoolean(common, "isExperienceOrbEntityCleanupEnable", true, "经验球实体清理功能");
        isFallingBlocksEntityCleanupEnable = buildBoolean(common, "isFallingBlocksEntityCleanupEnable", true, "下落方块实体清理功能");
        isArrowEntityCleanupEnable = buildBoolean(common, "isArrowEntityCleanupEnable", true, "箭头实体清理功能");
        isTridentEntityCleanupEnable = buildBoolean(common, "isTridentEntityCleanupEnable", false, "三叉戟实体清理功能");
        isDamagingProjectileEntityCleanupEnable = buildBoolean(common, "isDamagingProjectileEntityCleanupEnable", false, "投射物实体清理功能");
        isShulkerBulletEntityCleanupEnable = buildBoolean(common, "isShulkerBulletEntityCleanupEnable", true, "子弹实体清理功能");
        isFireworkRocketEntityCleanupEnable = buildBoolean(common, "isFireworkRocketEntityCleanupEnable", false, "烟花火箭实体清理功能");
        isItemFrameEntityCleanupEnable = buildBoolean(common, "isItemFrameEntityCleanupEnable", false, "物品框实体清理功能");
        isPaintingEntityCleanupEnable = buildBoolean(common, "isPaintingEntityCleanupEnable", false, "画实体清理功能");
        isBoatEntityCleanupEnable = buildBoolean(common, "isBoatEntityCleanupEnable", false, "船实体清理功能");
        isTNTEntityCleanupEnable = buildBoolean(common, "isTNTEntityCleanupEnable", true, "TNT实体清理功能");
        common.pop();
        COMMON = common.build();
    }

    private static ForgeConfigSpec.BooleanValue buildBoolean(ForgeConfigSpec.Builder builder, String name, boolean defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }

    private static ForgeConfigSpec.IntValue buildInt(ForgeConfigSpec.Builder builder, String name, int defaultValue, int min, int max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }

    private static ForgeConfigSpec.ConfigValue<String> buildString(ForgeConfigSpec.Builder builder, String name, String defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }

    private static ForgeConfigSpec.ConfigValue<List<String>> buildStrings(ForgeConfigSpec.Builder builder, String name, List<String> defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }

}

package committee.nova.mods.atom.sweep.common.core;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import committee.nova.mods.atom.sweep.common.Constants;
import committee.nova.mods.atom.sweep.common.config.ModConfig;
import committee.nova.mods.atom.sweep.common.config.Config;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;


/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/4/8 18:08
 * Version: 1.0
 */
public class SweepCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal(Constants.MOD_ID)

                        .then(
                                Commands.literal("items")
                                        .executes(SweepCommand::itemsExe)
                        )
                        .then(
                                Commands.literal("monsters")
                                        .executes(SweepCommand::monstersExe)
                        )
                        .then(
                                Commands.literal("animals")
                                        .executes(SweepCommand::animalsExe)
                        )
                        .then(
                                Commands.literal("others")
                                        .executes(SweepCommand::othersExe)
                        )
                        .then(
                                Commands.literal("xps")
                                        .executes(SweepCommand::xpsExe)
                        )
                        .then(
                                Commands.literal("itemWhite")
                                           .then(
                                                   Commands.literal("add")
                                                           .executes(SweepCommand::itemWhiteAdd)

                                           )
                                           .then(
                                                   Commands.literal("del")
                                                           .executes(SweepCommand::itemWhiteDel)
                                           )
                        )
                        .then(
                                Commands.literal("itemBlack")
                                        .then(
                                                Commands.literal("add")
                                                        .executes(SweepCommand::itemBlackAdd)

                                        )
                                        .then(
                                                Commands.literal("del")
                                                        .executes(SweepCommand::itemBlackDel)
                                        )
                        )

//                        .then(
//                                Commands.literal("entityWhite")
//                                        .then(
//                                                Commands.literal("add")
//                                                        .executes(SweepCommand::itemBlackAdd)
//
//                                        )
//                                        .then(
//                                                Commands.literal("del")
//                                                        .executes(SweepCommand::itemBlackDel)
//                                        )
//                        )

                        .requires(context -> Constants.cmdPermission(context, "atom.sweep.command.all", true))

        );
    }

    private static int itemWhiteAdd(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var player = context.getSource().getPlayerOrException();
        var itemStack = player.getMainHandItem();
        ModConfig.itemEntitiesWhitelist.get().add(Registry.ITEM.getKey(itemStack.getItem()).toString());
        Config.write(ModConfig.class);
        player.sendSystemMessage(Component.literal("已经添加到白名单"));
        return 1;
    }

    private static int itemWhiteDel(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var player = context.getSource().getPlayerOrException();
        var itemStack = player.getMainHandItem();
        ModConfig.itemEntitiesWhitelist.get().add(Registry.ITEM.getKey(itemStack.getItem()).toString());
        Config.write(ModConfig.class);
        player.sendSystemMessage(Component.literal("已经从白名单移除"));
        return 1;
    }

    private static int itemBlackAdd(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var player = context.getSource().getPlayerOrException();
        var itemStack = player.getMainHandItem();
        ModConfig.itemEntitiesBlacklist.get().add(Registry.ITEM.getKey(itemStack.getItem()).toString());
        Config.write(ModConfig.class);
        player.sendSystemMessage(Component.literal("已经添加到白名单"));
        return 1;
    }

    private static int itemBlackDel(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var player = context.getSource().getPlayerOrException();
        var itemStack = player.getMainHandItem();
        ModConfig.itemEntitiesBlacklist.get().remove(Registry.ITEM.getKey(itemStack.getItem()).toString());
        Config.write(ModConfig.class);
        player.sendSystemMessage(Component.literal("已经从白名单移除"));
        return 1;
    }

    private static int entityWhiteAdd(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var player = context.getSource().getPlayerOrException();
        var lookAngle = player.getLookAngle();
        //BuiltInRegistries.ITEM.getKey(player.level().getE);
        //ModConfig.itemsClean.addItemEntitiesWhitelist(BuiltInRegistries.ITEM.getKey(lookAngle.getItem()).toString());
        Config.write(ModConfig.class);
        player.sendSystemMessage(Component.literal("已经添加到白名单"));
        return 1;
    }

    private static int entityWhiteDel(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var player = context.getSource().getPlayerOrException();
        var itemStack = player.getMainHandItem();
        ModConfig.itemEntitiesWhitelist.get().remove(Registry.ITEM.getKey(itemStack.getItem()).toString());
        Config.write(ModConfig.class);
        player.sendSystemMessage(Component.literal("已经从白名单移除"));
        return 1;
    }

    private static int itemsExe(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var world = context.getSource().getLevel();
        var killItemCount = Sweeper.INSTANCE.cleanupItemEntity(world);
        Constants.sendMessageToAllPlayers(world.getServer(), ModConfig.sweepNoticeComplete.get(),
                killItemCount, 0, 0, 0);

        return 1;
    }

    private static int monstersExe(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var world = context.getSource().getLevel();
        var killLivingCount = Sweeper.INSTANCE.cleanupMonsterEntity(world);

        Constants.sendMessageToAllPlayers(world.getServer(), ModConfig.sweepNoticeComplete.get(),
                0, killLivingCount, 0, 0);

        return 1;
    }

    private static int animalsExe(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var world = context.getSource().getLevel();
        var killLivingCount = Sweeper.INSTANCE.cleanupAnimalEntity(world);
        Constants.sendMessageToAllPlayers(world.getServer(), ModConfig.sweepNoticeComplete.get(),
                0, killLivingCount, 0, 0);

        return 1;
    }

    private static int xpsExe(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var world = context.getSource().getLevel();
        var killXpCount = Sweeper.INSTANCE.cleanupXpEntity(world);
        Constants.sendMessageToAllPlayers(world.getServer(), ModConfig.sweepNoticeComplete.get(),
                0, 0, killXpCount, 0);
        return 1;
    }

    private static int othersExe(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var world = context.getSource().getLevel();
        var killOtherCount = Sweeper.INSTANCE.cleanOtherEntities(world);
        Constants.sendMessageToAllPlayers(world.getServer(), ModConfig.sweepNoticeComplete.get(),
                0, 0, 0, killOtherCount);
        return 1;
    }

}

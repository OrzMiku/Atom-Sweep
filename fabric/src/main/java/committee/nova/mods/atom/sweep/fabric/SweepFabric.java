package committee.nova.mods.atom.sweep.fabric;

import committee.nova.mods.atom.sweep.common.Constants;
import committee.nova.mods.atom.sweep.common.core.SweepCommand;
import committee.nova.mods.atom.sweep.common.SweepCommon;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraftforge.api.ModLoadingContext;

import static committee.nova.mods.atom.sweep.common.config.ModConfig.COMMON;

public class SweepFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        SweepCommon.init();
        ModLoadingContext.registerConfig(Constants.MOD_ID, net.minecraftforge.fml.config.ModConfig.Type.COMMON, COMMON);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> SweepCommand.register(dispatcher));
        ServerLifecycleEvents.SERVER_STARTING.register(SweepCommon::onServerAboutToStart);
        ServerLifecycleEvents.SERVER_STARTED.register(SweepCommon::onServerStarted);
        ServerLifecycleEvents.SERVER_STOPPING.register(SweepCommon::onServerStopping);
        ServerTickEvents.END_SERVER_TICK.register(SweepCommon::onServerTick);
    }
}

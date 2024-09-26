package committee.nova.mods.atom.sweep.forge;

import committee.nova.mods.atom.sweep.common.core.SweepCommand;
import committee.nova.mods.atom.sweep.common.SweepCommon;
import committee.nova.mods.atom.sweep.common.Constants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

import static committee.nova.mods.atom.sweep.common.config.ModConfig.COMMON;

@Mod(Constants.MOD_ID)
@Mod.EventBusSubscriber
public class SweepForge {

    public SweepForge() {
        SweepCommon.init();
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, COMMON);
        //MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void cmdRegister(RegisterCommandsEvent event) {
        SweepCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {
        SweepCommon.onServerAboutToStart(event.getServer());
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        SweepCommon.onServerStarted(event.getServer());
    }

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        SweepCommon.onServerStopping(event.getServer());
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            SweepCommon.onServerTick(event.getServer());
        }
    }
}
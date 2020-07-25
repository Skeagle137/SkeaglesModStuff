package net.skeagle.skeaglesmodstuff;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.skeagle.skeaglesmodstuff.block.DuckBlock;
import net.skeagle.skeaglesmodstuff.block.DuckCake;
import net.skeagle.skeaglesmodstuff.item.BreadStickItem;
import net.skeagle.skeaglesmodstuff.item.ChainLinkItem;
import net.skeagle.skeaglesmodstuff.setup.ClientProxy;
import net.skeagle.skeaglesmodstuff.setup.IProxy;
import net.skeagle.skeaglesmodstuff.setup.ServerProxy;
import net.skeagle.skeaglesmodstuff.setup.ModSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("smodstuff")
public class SMSMain {
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static ModSetup setup = new ModSetup();

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public SMSMain() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        setup.init();
        proxy.init();
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> e) {
            e.getRegistry().register(new DuckBlock());
            e.getRegistry().register(new DuckCake());
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> e) {
            Item.Properties propertiesBlocks = new Item.Properties().group(setup.blockGroup);
            //block items
            e.getRegistry().register(new BlockItem(SMSBlockReg.DUCKBLOCK, propertiesBlocks).setRegistryName("duckblock"));
            e.getRegistry().register(new BlockItem(SMSBlockReg.DUCKCAKE, propertiesBlocks).setRegistryName("duckcake"));
            //generic items
            e.getRegistry().register(new ChainLinkItem());
            e.getRegistry().register(new BreadStickItem());
        }
    }
}

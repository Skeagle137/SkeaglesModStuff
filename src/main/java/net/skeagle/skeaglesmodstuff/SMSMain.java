package net.skeagle.skeaglesmodstuff;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SMSMain.MODID)
public class SMSMain {
    public static final String MODID = "smodstuff";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public SMSMain() {
        ModSetup.register();
    }
}

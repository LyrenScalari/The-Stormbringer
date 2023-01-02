package theStormbringer.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theStormbringer.StormbringerMod;
import theStormbringer.util.TextureLoader;

public class WaterMana extends AbstractCustomIcon {
    public static final String ID = StormbringerMod.makeID("WaterMana");
    private static WaterMana singleton;

    public WaterMana() {
        super(ID, TextureLoader.getTexture("theStormbringerResources/images/WaterMana.png"));
    }

    public static WaterMana get()
    {
        if (singleton == null) {
            singleton = new WaterMana();
        }
        return singleton;
    }
}
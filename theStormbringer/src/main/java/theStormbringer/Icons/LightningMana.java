package theStormbringer.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theStormbringer.StormbringerMod;
import theStormbringer.util.TextureLoader;

public class LightningMana extends AbstractCustomIcon {
    public static final String ID = StormbringerMod.makeID("LightningMana");
    private static LightningMana singleton;

    public LightningMana() {
        super(ID, TextureLoader.getTexture("theStormbringerResources/images/ElectricMana.png"));
    }

    public static LightningMana get() {
        if (singleton == null) {
            singleton = new LightningMana();
        }
        return singleton;
    }
}

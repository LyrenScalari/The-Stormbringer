package theStormbringer.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theStormbringer.StormbringerMod;
import theStormbringer.util.TextureLoader;

public class LightningIcon extends AbstractCustomIcon {
    public static final String ID = StormbringerMod.makeID("Lightning");
    private static LightningIcon singleton;

    public LightningIcon() {
        super(ID, TextureLoader.getTexture("theStormbringerResources/images/Icons/Electric.png"));
    }

    public static LightningIcon get()
    {
        if (singleton == null) {
            singleton = new LightningIcon();
        }
        return singleton;
    }
}
package theStormbringer.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theStormbringer.StormbringerMod;
import theStormbringer.util.TextureLoader;

public class FireIcon extends AbstractCustomIcon {
    public static final String ID = StormbringerMod.makeID("Fire");
    private static FireIcon singleton;

    public FireIcon() {
        super(ID, TextureLoader.getTexture("theStormbringerResources/images/Icons/Fire.png"));
    }

    public static FireIcon get()
    {
        if (singleton == null) {
            singleton = new FireIcon();
        }
        return singleton;
    }
}
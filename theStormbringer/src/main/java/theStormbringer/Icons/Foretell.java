package theStormbringer.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theStormbringer.StormbringerMod;
import theStormbringer.util.TextureLoader;

public class Foretell extends AbstractCustomIcon {
    public static final String ID = StormbringerMod.makeID("Foretell");
    private static Foretell singleton;

    public Foretell() {
        super(ID, TextureLoader.getTexture("theStormbringerResources/images/Icons/ForetellIcon.png"));
    }

    public static Foretell get() {
        if (singleton == null) {
            singleton = new Foretell();
        }
        return singleton;
    }
}

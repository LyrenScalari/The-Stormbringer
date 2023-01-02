package theStormbringer.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theStormbringer.StormbringerMod;
import theStormbringer.util.TextureLoader;

public class GhostMana extends AbstractCustomIcon {
    public static final String ID = StormbringerMod.makeID("GhostMana");
    private static GhostMana singleton;

    public GhostMana() {
        super(ID, TextureLoader.getTexture("theStormbringerResources/images/GhostMana.png"));
    }

    public static GhostMana get()
    {
        if (singleton == null) {
            singleton = new GhostMana();
        }
        return singleton;
    }
}
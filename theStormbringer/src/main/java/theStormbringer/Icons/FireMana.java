package theStormbringer.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theStormbringer.StormbringerMod;
import theStormbringer.util.TextureLoader;

public class FireMana extends AbstractCustomIcon {
    public static final String ID = StormbringerMod.makeID("FireMana");
    private static FireMana singleton;

    public FireMana() {
        super(ID, TextureLoader.getTexture("theStormbringerResources/images/FireMana.png"));
    }

    public static FireMana get()
    {
        if (singleton == null) {
            singleton = new FireMana();
        }
        return singleton;
    }
}
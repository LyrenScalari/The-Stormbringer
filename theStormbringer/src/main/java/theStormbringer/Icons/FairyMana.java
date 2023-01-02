package theStormbringer.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theStormbringer.StormbringerMod;
import theStormbringer.util.TextureLoader;

public class FairyMana extends AbstractCustomIcon {
    public static final String ID = StormbringerMod.makeID("FairyMana");
    private static FairyMana singleton;

    public FairyMana() {
        super(ID, TextureLoader.getTexture("theStormbringerResources/images/FairyMana.png"));
    }

    public static FairyMana get()
    {
        if (singleton == null) {
            singleton = new FairyMana();
        }
        return singleton;
    }
}
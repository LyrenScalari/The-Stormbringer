package theStormbringer.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theStormbringer.StormbringerMod;
import theStormbringer.util.TextureLoader;

public class IceMana extends AbstractCustomIcon {
    public static final String ID = StormbringerMod.makeID("IceMana");
    private static IceMana singleton;

    public IceMana() {
        super(ID, TextureLoader.getTexture("theStormbringerResources/images/IceMana.png"));
    }

    public static IceMana get()
    {
        if (singleton == null) {
            singleton = new IceMana();
        }
        return singleton;
    }
}
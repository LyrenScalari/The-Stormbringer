package theStormbringer.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theStormbringer.StormbringerMod;
import theStormbringer.util.TextureLoader;

public class IceIcon extends AbstractCustomIcon {
    public static final String ID = StormbringerMod.makeID("Ice");
    private static IceIcon singleton;

    public IceIcon() {
        super(ID, TextureLoader.getTexture("theStormbringerResources/images/Icons/Ice.png"));
    }

    public static IceIcon get()
    {
        if (singleton == null) {
            singleton = new IceIcon();
        }
        return singleton;
    }
}
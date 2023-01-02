package theStormbringer.Icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theStormbringer.StormbringerMod;
import theStormbringer.util.TextureLoader;

public class Style extends AbstractCustomIcon {
    public static final String ID = StormbringerMod.makeID("Style");
    private static Style singleton;

    public Style() {
        super(ID, TextureLoader.getTexture("theStormbringerResources/images/Icons/StyleIcon.png"));
    }

    public static Style get() {
        if (singleton == null) {
            singleton = new Style();
        }
        return singleton;
    }
}

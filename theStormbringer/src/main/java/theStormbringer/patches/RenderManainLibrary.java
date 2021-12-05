package theStormbringer.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theStormbringer.cards.AbstractStormbringerCard;
@SpirePatch(
        clz = AbstractCard.class,
        method = "renderEnergy",
        paramtypes = { "com.badlogic.gdx.graphics.g2d.SpriteBatch"}
        )
public class RenderManainLibrary {
    public static void Postfix(AbstractCard __instance, SpriteBatch sb) {
        if (__instance instanceof AbstractStormbringerCard) {
            AbstractStormbringerCard.renderManaCost((AbstractStormbringerCard) __instance, sb);
        }
    }
}

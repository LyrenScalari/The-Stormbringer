package theStormbringer.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.util.SingleCardViewManaRender;

@SpirePatch(
        clz = SingleCardViewPopup.class,
        method = "renderCost",
        paramtypes = {"com.badlogic.gdx.graphics.g2d.SpriteBatch"}
)
public class RenderManaSingleCardPatch {
    public static void Postfix(SingleCardViewPopup __instance, SpriteBatch sb) {
        AbstractCard c = (AbstractCard) ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
        if(c instanceof AbstractStormbringerCard) {
            SingleCardViewManaRender.renderElementCost((AbstractStormbringerCard) c, sb);
        }
    }
}

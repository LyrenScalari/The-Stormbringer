package theStormbringer.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CtBehavior;
import theStormbringer.StormbringerMod;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "render"
)
public class WeatherRenderPatch {
    @SpireInsertPatch(
            locator=DamageLocator.class,
            localvars={"sb"}
    )
    public static void InsertRender(AbstractPlayer __instance, SpriteBatch sb) {
        if (StormbringerMod.currentWeather != null){
            StormbringerMod.currentWeather.render(sb);
        }
    }


    private static class DamageLocator extends SpireInsertLocator {
        private DamageLocator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "stance");
            int[] tmp = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{tmp[0]};
        }
    }
}

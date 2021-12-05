package theStormbringer.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;
import theStormbringer.StormbringerMod;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "applyStartOfTurnRelics"
)
public class WeatherStartofTurnPatch {
    @SpireInsertPatch(
            locator= DamageLocator.class
    )
    public static void InsertRender(AbstractPlayer __instance) {
        if (StormbringerMod.currentWeather != null){
            StormbringerMod.currentWeather.atStartOfTurn();
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

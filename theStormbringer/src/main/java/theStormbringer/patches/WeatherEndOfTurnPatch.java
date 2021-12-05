package theStormbringer.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;
import theStormbringer.StormbringerMod;

@SpirePatch(
        clz = GameActionManager.class,
        method = "callEndOfTurnActions"
)
public class WeatherEndOfTurnPatch {
    @SpireInsertPatch(
            locator= DamageLocator.class
    )
    public static void InsertRender(GameActionManager __instance) {
        if (StormbringerMod.currentWeather != null){
            StormbringerMod.currentWeather.onEndOfTurn();
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

package theStormbringer.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;
import theStormbringer.StormbringerMod;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "combatUpdate"
)
public class WeatherCombatUpdate {
    @SpireInsertPatch(
            locator= DamageLocator.class
    )
    public static void InsertRender(AbstractPlayer __instance) {
        if (StormbringerMod.currentWeather != null){
            StormbringerMod.currentWeather.update();
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

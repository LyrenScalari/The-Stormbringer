package theStormbringer.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import javassist.CtBehavior;
import theStormbringer.StormbringerMod;
import theStormbringer.util.WeatherEffects.AbstractWeather;
@SpirePatch(
        clz = AbstractCard.class,
        method = "calculateCardDamage"
)
public class WeatherDamageBonusPatch {
    @SpireInsertPatch(
            locator=DamageLocator.class,
            localvars={"tmp"}
    )
    public static void InsertDamage(AbstractCard __instance, AbstractMonster m, @ByRef float[] tmp) {
        if (StormbringerMod.currentWeather != null){
            tmp[0] = StormbringerMod.currentWeather.atDamageGive(tmp[0],__instance.damageTypeForTurn,__instance);
        }
    }

    @SpireInsertPatch(
            locator = MultiDamageLocator.class,
            localvars = {"tmp", "i"}
    )
    public static void InsertDamageMulti(AbstractCard __instance, AbstractMonster m, float[] tmp, int i) {
        if (StormbringerMod.currentWeather != null){
            tmp[i] = StormbringerMod.currentWeather.atDamageGive(tmp[0],__instance.damageTypeForTurn,__instance);
        }
    }
    private static class MultiDamageLocator extends SpireInsertLocator {
        private MultiDamageLocator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "stance");
            int[] tmp = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{tmp[1]};
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

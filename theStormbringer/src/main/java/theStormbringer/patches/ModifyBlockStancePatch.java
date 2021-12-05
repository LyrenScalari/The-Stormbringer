package theStormbringer.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CtBehavior;
import theStormbringer.StormbringerMod;
import theStormbringer.util.ModifyBlockStance;

@SpirePatch(clz = AbstractCard.class, method = "applyPowersToBlock")
public class ModifyBlockStancePatch {
    @SpireInsertPatch(
            locator= EvokeLocator.class,
            localvars = {"tmp"}
    )
    public static void onCardDrawOrb(AbstractCard __instance,@ByRef float[] tmp) {
        if (StormbringerMod.currentWeather instanceof ModifyBlockStance){
            tmp[0] = ((ModifyBlockStance) StormbringerMod.currentWeather).modifyBlock(tmp[0]);
        }
    }

    private static class EvokeLocator extends SpireInsertLocator {
        private EvokeLocator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "baseBlock");
            int[] tmp = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{tmp[1]};
        }
    }
}

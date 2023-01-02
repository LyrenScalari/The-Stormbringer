package theStormbringer.patches;

import com.evacipated.cardcrawl.mod.stslib.vfx.combat.TempDamageNumberEffect;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.DamageImpactLineEffect;
import com.megacrit.cardcrawl.vfx.combat.StrikeEffect;
import javassist.CtBehavior;
import theStormbringer.util.OnDamageBerry;

import java.util.ArrayList;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "damage"
)
public class OnDamageBerryPatch {
    static boolean hadDivineArmor;

    public OnDamageBerryPatch() {
    }

    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"damageAmount", "hadBlock"}
    )
    public static void Insert(AbstractCreature __instance, DamageInfo info, @ByRef int[] damageAmount, @ByRef boolean[] hadBlock) {
        for (AbstractPotion p : AbstractDungeon.player.potions){
            if (p instanceof OnDamageBerry){
                ((OnDamageBerry) p).DamageActivation();
            }
        }
    }

    @SpireInsertPatch(
            locator = StrikeEffectLocator.class
    )
    public static SpireReturn<Void> Insert(AbstractCreature __instance, DamageInfo info) {
        if (hadDivineArmor) {
            return SpireReturn.Return();
        } else {
            return SpireReturn.Continue();
        }
    }

    private static class StrikeEffectLocator extends SpireInsertLocator {
        private StrikeEffectLocator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.NewExprMatcher(StrikeEffect.class);
            int[] all = LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList(), finalMatcher);
            return new int[]{all[all.length - 1]};
        }
    }

    private static class Locator extends SpireInsertLocator {
        private Locator() {
        }

        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "decrementBlock");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList(), finalMatcher);
        }
    }
}

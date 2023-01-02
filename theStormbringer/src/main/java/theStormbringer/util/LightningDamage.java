package theStormbringer.util;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theStormbringer.powers.BurnPower;

import java.util.ArrayList;

public class LightningDamage extends AbstractDamageModifier {
    boolean inherent;
    public LightningDamage(boolean isinherent, boolean autoAdd){
        inherent = isinherent;
        this.automaticBindingForCards = autoAdd;
    }
    public boolean isInherent() {
        return inherent;
    }
    public ArrayList<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(BaseMod.getKeywordTitle("thestormbringer:Lightning_Damage"), BaseMod.getKeywordDescription("thestormbringer:Lightning_Damage")));
        return tips;
    }
    public void onDamageModifiedByBlock(DamageInfo info, int unblockedAmount, int blockedAmount, AbstractCreature target) {
        if (info.type == DamageInfo.DamageType.NORMAL) {
            addToBot(new DamageAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true), new DamageInfo(info.owner, unblockedAmount / 2, DamageInfo.DamageType.THORNS)));
        }
    }
    @Override
    public AbstractDamageModifier makeCopy() {
        return new FireDamage(inherent,automaticBindingForCards);
    }

    public int priority() {
        return 1;
    }
}
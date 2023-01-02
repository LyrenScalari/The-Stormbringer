package theStormbringer.util;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theStormbringer.powers.BurnPower;
import theStormbringer.powers.FreezePower;
import theStormbringer.powers.FrostbitePower;

import java.util.ArrayList;

public class IceDamage extends AbstractDamageModifier {
    boolean inherent;
    public IceDamage(boolean isinherent, boolean autoAdd){
        inherent = isinherent;
        this.automaticBindingForCards = autoAdd;
    }
    public boolean isInherent() {
        return inherent;
    }
    public ArrayList<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(BaseMod.getKeywordTitle("thestormbringer:Ice_Damage"), BaseMod.getKeywordDescription("thestormbringer:Ice_Damage")));
        return tips;
    }
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (target.hasPower(FreezePower.POWER_ID)){
            addToBot(new ApplyPowerAction(target,info.owner,new FrostbitePower(target,info.owner,damageAmount)));
            return 0;
        } else return damageAmount;
    }
    @Override
    public AbstractDamageModifier makeCopy() {
        return new FireDamage(inherent,automaticBindingForCards);
    }

    public int priority() {
        return 1;
    }
}
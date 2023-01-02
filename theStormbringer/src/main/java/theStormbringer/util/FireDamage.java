package theStormbringer.util;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theStormbringer.powers.BurnPower;

import java.util.ArrayList;

public class FireDamage extends AbstractDamageModifier {
    boolean inherent;
    public FireDamage(boolean isinherent, boolean autoAdd){
        inherent = isinherent;
        this.automaticBindingForCards = autoAdd;
    }
    public boolean isInherent() {
        return inherent;
    }
    public ArrayList<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(BaseMod.getKeywordTitle("thestormbringer:Fire_Damage"), BaseMod.getKeywordDescription("thestormbringer:Fire_Damage")));
        return tips;
    }
    public void onDamageModifiedByBlock(DamageInfo info, int unblockedAmount, int blockedAmount, AbstractCreature target) {
        addToBot(new ApplyPowerAction(target,info.owner,new BurnPower(target,info.owner,(int)Math.ceil(unblockedAmount))));
    }
    @Override
    public AbstractDamageModifier makeCopy() {
        return new FireDamage(inherent,automaticBindingForCards);
    }

    public int priority() {
        return 1;
    }
}
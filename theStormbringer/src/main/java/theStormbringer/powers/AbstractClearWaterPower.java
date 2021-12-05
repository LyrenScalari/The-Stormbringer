package theStormbringer.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractClearWaterPower extends AbstractEasyPower implements OnReceivePowerPower {
    public AbstractClearWaterPower(String NAME, int amount) {
        super(NAME, PowerType.BUFF, true, AbstractDungeon.player, amount);
        this.loadRegion("like_water");
    }
    public AbstractClearWaterPower(String NAME, int amount,int amount2) {
        this(NAME,amount);
        this.amount2 = amount2;
    }
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        if (abstractPower instanceof AbstractClearWaterPower){
            if (((AbstractClearWaterPower) abstractPower).amount2 > 0){
                amount2 += ((AbstractClearWaterPower) abstractPower).amount2;
            }
        }
        return  true;
    }
    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(owner,owner,this));
    }
}

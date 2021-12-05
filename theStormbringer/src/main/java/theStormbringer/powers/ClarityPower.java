package theStormbringer.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theStormbringer.StormbringerMod;

public class ClarityPower extends AbstractEasyPower implements OnReceivePowerPower {
    public AbstractCreature source;
    public static final String POWER_ID = StormbringerMod.makeID("Clarity");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public ClarityPower(int amount) {
        super(NAME, PowerType.BUFF,false, AbstractDungeon.player, amount);
        name = NAME;
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.source = owner;
        this.loadRegion("master_reality");
        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those txtures here.

        updateDescription();
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount;
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        if (abstractPower instanceof AbstractClearWaterPower ||
                (abstractPower instanceof NextTurnPowerPower && abstractPower.name.equals("Clear Water"))){
            abstractPower.amount += this.amount;
            if (((AbstractEasyPower) abstractPower).amount2 > 0){
                ((AbstractEasyPower) abstractPower).amount2 += this.amount;
            }
            abstractPower.updateDescription();
        }
        return true;
    }
}

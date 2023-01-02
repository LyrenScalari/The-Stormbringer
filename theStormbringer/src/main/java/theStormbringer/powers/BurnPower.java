package theStormbringer.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theStormbringer.StormbringerMod;

public class BurnPower extends AbstractPower {
    public AbstractCreature source;
    public static final String POWER_ID = StormbringerMod.makeID("Burn");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    int MODIFERAMOUNT = 3;
    public BurnPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;
        this.loadRegion("flameBarrier");
        type = PowerType.DEBUFF;
        isTurnBased = false;

        // We load those txtures here.

        updateDescription();
    }
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return (damage * (1+(MODIFERAMOUNT*amount)/100f));
        } else {
            return damage;
        }
    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }
    @Override
    public void atEndOfTurn(final boolean isplayer) {
        addToBot(new ReducePowerAction(owner,owner,this,1));
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + MODIFERAMOUNT*amount + DESCRIPTIONS[1];
    }
}

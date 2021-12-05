package theStormbringer.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
    @Override
    public void atEndOfTurn(final boolean isplayer) {
        addToBot(new DamageAction(owner,new DamageInfo(owner,((int)Math.floor(owner.maxHealth*0.10)), DamageInfo.DamageType.THORNS)));
        addToBot(new ReducePowerAction(owner,owner,this,1));
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}

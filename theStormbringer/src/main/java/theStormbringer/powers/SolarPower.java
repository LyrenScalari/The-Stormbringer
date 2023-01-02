package theStormbringer.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theStormbringer.StormbringerMod;

public class SolarPower extends AbstractPower {
    public AbstractCreature source;
    public static final String POWER_ID = StormbringerMod.makeID("SolarPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SolarPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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
    public void onSpecificTrigger() {
        addToBot(new ApplyPowerAction(owner,owner,new VigorPower(owner,amount)));
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
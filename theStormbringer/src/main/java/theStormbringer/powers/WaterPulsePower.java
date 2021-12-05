package theStormbringer.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theStormbringer.StormbringerMod;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.util.TypeEnergyHelper;

public class WaterPulsePower extends AbstractClearWaterPower {
    public AbstractCreature source;
    public static final String POWER_ID = StormbringerMod.makeID("WaterPulse");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public WaterPulsePower(String NAME, int amount,int amount2) {
        super(NAME, amount,amount2);
        name = NAME;
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.amount2 = amount2;
        this.source = owner;
        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those txtures here.

        updateDescription();
    }
    public void atStartOfTurn() {
        addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player,amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Water,amount2));
        super.atStartOfTurn();
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
    }
}

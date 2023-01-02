package theStormbringer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theStormbringer.StormbringerMod;

public class FrostbitePower extends TwoAmountPower implements CloneablePowerInterface, HealthBarRenderPower {
    public AbstractCreature source;
    public static final String POWER_ID = StormbringerMod.makeID("Frostbite");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FrostbitePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;
        amount2 = 10;
        this.loadRegion("ai");
        type = PowerType.DEBUFF;
        isTurnBased = false;

        // We load those txtures here.

        updateDescription();
    }
    @Override
    public void atEndOfTurn(final boolean isplayer) {
        addToBot(new DamageAction(owner,new DamageInfo(owner,amount, DamageInfo.DamageType.THORNS)));
        addToBot(new ReducePowerAction(owner,owner,this,1));
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= amount2) {
            addToBot(new ApplyPowerAction(owner,owner,new FreezePower(owner,owner,2)));
            amount2 +=10;
        }

    }
    @Override
    public AbstractPower makeCopy() {
        return new FrostbitePower(owner,owner,amount);
    }

    @Override
    public int getHealthBarAmount() {
        return amount;
    }

    @Override
    public Color getColor() {
        return CardHelper.getColor(0.0f,230.0f,255.0f);
    }
}
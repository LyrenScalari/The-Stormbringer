package theStormbringer.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.StormbringerMod;
import theStormbringer.cards.AbstractForetellCard;
import theStormbringer.relics.AbstractEasyRelic;

public class ForetellPower extends AbstractEasyPower {
    public AbstractCreature source;
    public static final String POWER_ID = StormbringerMod.makeID("Foretell");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCard card;
    private AbstractMonster Target;

    public ForetellPower(String NAME, AbstractCard Card) {
        super(NAME, PowerType.BUFF , false, AbstractDungeon.player,-1);
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = -1;
        this.source = owner;
        type = PowerType.BUFF;
        isTurnBased = false;
        card = Card;
        // We load those txtures here.

        updateDescription();
    }

    public void atStartOfTurn() {
        addToBot(new MakeTempCardInHandAction(card));
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player,AbstractDungeon.player,this));
    }

    public void updateDescription() {
        if (card != null){
            this.description = DESCRIPTIONS[0] + card.name + DESCRIPTIONS[1];
        } else this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }
}
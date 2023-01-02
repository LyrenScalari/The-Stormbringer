package theStormbringer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theStormbringer.util.TypeEnergyHelper;

import java.util.EnumMap;

public abstract class AbstractForetellCard extends AbstractClickableCard {
    public boolean Foretold = false;
    private AbstractGameAction action;
    private static boolean looping;
    public AbstractForetellCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }

    @Override
    public void upp() {

    }
    @Override
    public void onRightClick() {
        if (canSwap() && action == null && cardsToPreview != null) {
            CardCrawlGame.sound.play("CARD_SELECT", 0.1F);
            if (AbstractDungeon.player.hand.contains(this)){
                action = new ExhaustSpecificCardAction(this,AbstractDungeon.player.hand);
            }
            this.addToTop(action);
        }
    }

    public boolean canSwap() {
        return true;
    }

    public void onSwapOut() {}

    public void onSwapIn() {}
}

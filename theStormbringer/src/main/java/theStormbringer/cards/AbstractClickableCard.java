package theStormbringer.cards;

import com.evacipated.cardcrawl.mod.stslib.patches.HitboxRightClick;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class AbstractClickableCard extends AbstractStormbringerCard {

    // "How come DefaultCommonAttack extends CustomCard and not DynamicCard like all the rest?"

    // Well every card, at the end of the day, extends CustomCard.
    // Abstract Default Card extends CustomCard and builds up on it, adding a second magic number. Your card can extend it and
    // bam - you can have a second magic number in that card (Learn Java inheritance if you want to know how that works).
    // Abstract Dynamic Card builds up on Abstract Default Card even more and makes it so that you don't need to add
    // the NAME and the DESCRIPTION into your card - it'll get it automatically. Of course, this functionality could have easily
    // Been added to the default card rather than creating a new Dynamic one, but was done so to deliberately.

    public AbstractClickableCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {

        super(cardID, cost, type, rarity, target, color);

    }

    @Override
    public void upp() {

    }

    @Override
    public void update() {
        super.update();
        if (AbstractDungeon.player != null) {
            clickUpdate();
        }
    }

    public void clickUpdate() {
        if (HitboxRightClick.rightClicked.get(this.hb) && !AbstractDungeon.actionManager.turnHasEnded) {
            onRightClick();
        }
    }

    public abstract void onRightClick();
}
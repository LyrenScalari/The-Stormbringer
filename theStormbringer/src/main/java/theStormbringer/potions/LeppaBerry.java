package theStormbringer.potions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theStormbringer.powers.Hydration;
import theStormbringer.util.TextureLoader;

public class LeppaBerry extends AbstractBerry {

    public static final String POTION_ID = theStormbringer.StormbringerMod.makeID("LeppaBerry");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public LeppaBerry() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionColor.SMOKE);
        potency = getPotency();
        description = DESCRIPTIONS[0];
        isThrown = false;
        tips.add(new PowerTip(name, description));
        Icon = TextureLoader.getTexture("theStormbringerResources/images/Items/Leppa.png");
    }

    @Override
    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    for (AbstractCard c : AbstractDungeon.player.exhaustPile.group){
                        AbstractDungeon.player.exhaustPile.removeCard(c);
                        c.unfadeOut();
                        AbstractDungeon.player.drawPile.addToRandomSpot(c);
                    }
                    isDone = true;
                }
            });

        }
    }
    @Override
    public AbstractPotion makeCopy() {
        return new LeppaBerry();
    }

    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 4;
    }

    public void upgradePotion()
    {
        potency += 1;
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
}
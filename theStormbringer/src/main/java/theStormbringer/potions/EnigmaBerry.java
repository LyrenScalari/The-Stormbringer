package theStormbringer.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theStormbringer.util.TextureLoader;

public class EnigmaBerry extends AbstractBerry {

    public static final String POTION_ID = theStormbringer.StormbringerMod.makeID("EnigmaBerry");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public EnigmaBerry() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.M, PotionColor.SMOKE);
        potency = getPotency();
        description = DESCRIPTIONS[0];
        isThrown = false;
        tips.add(new PowerTip(name, description));
        Icon = TextureLoader.getTexture("theStormbringerResources/images/Items/Enigma.png");
    }

    @Override
    public void use(AbstractCreature target) {
        target = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
        }
    }
    @Override
    public AbstractPotion makeCopy() {
        return new FigyBerry();
    }

    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 1;
    }

    public void upgradePotion()
    {
        potency += 1;
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
}
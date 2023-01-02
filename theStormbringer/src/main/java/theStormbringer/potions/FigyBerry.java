package theStormbringer.potions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theStormbringer.util.OnDamageBerry;
import theStormbringer.util.TextureLoader;

public class FigyBerry extends AbstractBerry implements OnDamageBerry {

    public static final String POTION_ID = theStormbringer.StormbringerMod.makeID("FigyBerry");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public FigyBerry() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionColor.SMOKE);
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        isThrown = false;
        tips.add(new PowerTip(name, description));
        Icon = TextureLoader.getTexture("theStormbringerResources/images/Items/Figy.png");
    }

    @Override
    public void use(AbstractCreature target) {
        target = AbstractDungeon.player;
        AbstractDungeon.player.heal(potency, true);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,target,new ConfusionPower(target)));
        AbstractDungeon.topPanel.destroyPotion(this.slot);
    }
    @Override
    public AbstractPotion makeCopy() {
        return new FigyBerry();
    }
    public boolean canUse() {
        return false;
    }
    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 34;
    }

    public void upgradePotion()
    {
        potency += 1;
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void DamageActivation() {
        if (AbstractDungeon.player.currentHealth <= AbstractDungeon.player.maxHealth * 0.25){
            this.use(AbstractDungeon.player);
        }
    }
}
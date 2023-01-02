package theStormbringer.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theStormbringer.StormbringerMod;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.util.TextureLoader;
import theStormbringer.util.TypeEnergyHelper;

import static theStormbringer.StormbringerMod.makeRelicOutlinePath;
import static theStormbringer.StormbringerMod.makeRelicPath;

public class KineticGenerator extends AbstractEasyRelic {
    public static final String ID = StormbringerMod.makeID("KineticGenerator");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    public KineticGenerator() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK);
    }
    public void atBattleStart() {
        this.counter = 0;
    }
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractStormbringerCard) {
            if (((AbstractStormbringerCard)card).Type == null) {
                ++this.counter;
                if (this.counter % 2 == 0 && counter > 0) {
                    this.counter = 0;
                    this.flash();
                    this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                    addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Colorless, 1));
                }
            }
        } else {
            ++this.counter;
            if (this.counter % 2 == 0 && counter > 0) {
                this.counter = 0;
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Colorless, 1));
            }
        }
    }
    public void onVictory() {
        this.counter = -1;
    }
}

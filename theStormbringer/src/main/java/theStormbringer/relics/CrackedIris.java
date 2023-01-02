package theStormbringer.relics;

import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theStormbringer.StormbringerMod;
import theStormbringer.actions.ChangeWeatherAction;
import theStormbringer.util.TextureLoader;
import theStormbringer.util.WeatherEffects.*;

import java.util.ArrayList;

import static theStormbringer.StormbringerMod.makeRelicOutlinePath;
import static theStormbringer.StormbringerMod.makeRelicPath;

public class CrackedIris extends AbstractEasyRelic {
    public static final String ID = StormbringerMod.makeID("CrackedIris");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    public CrackedIris() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK);
    }
    public void atTurnStart() {
        this.counter = 0;
    }
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.SKILL) {
            ++this.counter;
            if (this.counter % 2 == 0 && counter > 0) {
                this.counter = 0;
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, 3), 3));
            }
        }
    }
    public void onVictory() {
        this.counter = -1;
    }
}

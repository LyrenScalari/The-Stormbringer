package theStormbringer.util.WeatherEffects;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import theStormbringer.StormbringerMod;
import theStormbringer.VFX.HailstormEffect;
import theStormbringer.VFX.SandstormEffect;

import static theStormbringer.StormbringerMod.makeID;

public class SandstormWeather extends AbstractWeather {
    static final String Weather_ID = makeID("Sandstorm");
    static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(Weather_ID);
    int blockgain;
    public SandstormWeather(){
        name = stanceString.NAME;
        description = stanceString.DESCRIPTION[0];
        blockgain = 8;
        updateDescription();
    }
    @Override
    public String updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(stanceString.DESCRIPTION[0]);
        sb.append(blockgain);
        sb.append(stanceString.DESCRIPTION[1]);
        return sb.toString();
    }

    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.2F;
                AbstractDungeon.effectsQueue.add(new SandstormEffect());
                AbstractDungeon.effectsQueue.add(new SandstormEffect());
                AbstractDungeon.effectsQueue.add(new SandstormEffect());
                AbstractDungeon.effectsQueue.add(new SandstormEffect());
            }
            updateDescription();
        }
    }
    @Override
    public void onEndOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player,blockgain));
    }
}
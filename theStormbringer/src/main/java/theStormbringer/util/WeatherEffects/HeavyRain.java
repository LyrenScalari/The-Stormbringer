package theStormbringer.util.WeatherEffects;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theStormbringer.StormbringerMod;
import theStormbringer.VFX.HailstormEffect;
import theStormbringer.VFX.HeavyRainEffect;
import theStormbringer.util.ModifyBlockStance;

import static theStormbringer.StormbringerMod.makeID;

public class HeavyRain extends AbstractWeather implements ModifyBlockStance {
    static final String Weather_ID = makeID("HeavyRain");
    static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(Weather_ID);
    boolean lightningCycle = false;
    float secondParticleTimer = 0;
    int powerbonus = 3;
    public HeavyRain(){
        name =  stanceString.NAME;
        description = stanceString.DESCRIPTION[0];
    }
    @Override
    public String updateDescription() {
        return description + powerbonus;
    }
    public void onSummonWeather() {
        weathertimer = 4;
    }
    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (lightningCycle){
                secondParticleTimer -= Gdx.graphics.getDeltaTime();
                if (secondParticleTimer < 0.0F){
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_LIGHTNING_EVOKE", 0.5F));
                    AbstractDungeon.effectsQueue.add(new LightningEffect(MathUtils.random(-50.0F, 1500.0F),AbstractDungeon.floorY + MathUtils.random(-200.0F, 50.0F) * Settings.scale));
                    secondParticleTimer = MathUtils.random(1.5f,4.5f);
                }
            }
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.3F;
                if (!lightningCycle) {
                    secondParticleTimer = MathUtils.random(1.5f,4.5f);
                    lightningCycle = true;
                }
                for (int i = 0; i < 100; i++) {
                    AbstractDungeon.effectsQueue.add(new HeavyRainEffect(8));

                }
            }
            updateDescription();
        }
    }
    @Override
    public float modifyBlock(float blockAmount) {
        return blockAmount + powerbonus;
    }
}
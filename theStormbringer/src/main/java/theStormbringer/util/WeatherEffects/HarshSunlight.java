package theStormbringer.util.WeatherEffects;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theStormbringer.StormbringerMod;
import theStormbringer.VFX.HarshSunHaloEffect;
import theStormbringer.VFX.HarshSunlightEffect;
import theStormbringer.VFX.HeavyRainEffect;
import theStormbringer.VFX.SunFlareEffect;
import theStormbringer.actions.ChangeWeatherAction;
import theStormbringer.powers.DroughtPower;
import theStormbringer.powers.ResistancePower;
import theStormbringer.powers.SolarPower;
import theStormbringer.util.Wiz;

import javax.smartcardio.Card;

import static theStormbringer.StormbringerMod.makeID;

public class HarshSunlight extends AbstractWeather {
    static final String Weather_ID = makeID("HarshSunlight");
    static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(Weather_ID);
    int powerbonus = 3;
    boolean lightningCycle = false;
    float secondParticleTimer = 0;
    float thirdParticleTimer = 0;
    float fourthParticleTimer = 0;
    AbstractGameEffect Sun;
    public HarshSunlight(){
        name = stanceString.NAME;
        description = stanceString.DESCRIPTION[0] + powerbonus;
    }
    @Override
    public String updateDescription() {
        return description;
    }
    public void atStartOfTurn() {
        AbstractPower drought = AbstractDungeon.player.getPower(DroughtPower.POWER_ID);
        if (drought != null){
            drought.onSpecificTrigger();
        }
        AbstractPower solarpower = AbstractDungeon.player.getPower(SolarPower.POWER_ID);
        if (solarpower != null){
            solarpower.onSpecificTrigger();
        }
        super.atStartOfTurn();
    }
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card != null){
            return type == DamageInfo.DamageType.NORMAL ? damage + powerbonus: damage;
        }
        return  damage;
    }
    @Override
    public void onWeatherSubside() {
        Sun.duration = 0.0f;
        super.onWeatherSubside();
    }
    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (lightningCycle){
                secondParticleTimer -= Gdx.graphics.getDeltaTime();
                thirdParticleTimer -= Gdx.graphics.getDeltaTime();
                fourthParticleTimer -= Gdx.graphics.getDeltaTime();
                if (secondParticleTimer < 0.0F){
                    AbstractDungeon.effectsQueue.add(new HarshSunlightEffect());
                    secondParticleTimer = MathUtils.random(1.0f,1.5f);
                }
                if (thirdParticleTimer < 0.0F){
                    AbstractDungeon.effectsQueue.add(new HarshSunlightEffect());
                    thirdParticleTimer = MathUtils.random(1.0f,1.5f);
                }
            }
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.3F;
                if (!lightningCycle) {
                    Sun = new HarshSunHaloEffect();
                    AbstractDungeon.effectsQueue.add(Sun);
                    secondParticleTimer = MathUtils.random(1.0f,1.5f);
                    thirdParticleTimer = MathUtils.random(1.4f,2.3f);
                    lightningCycle = true;
                }
            }
            updateDescription();
        }
    }
    public void onSummonWeather() {
        weathertimer = 4;
    }
}
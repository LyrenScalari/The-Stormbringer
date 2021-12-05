package theStormbringer.util.WeatherEffects;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import theStormbringer.StormbringerMod;
import theStormbringer.VFX.HailstormEffect;
import theStormbringer.powers.IcePower;
import theStormbringer.powers.PowderSnow;
import theStormbringer.powers.ResistancePower;
import theStormbringer.powers.SnowWarning;
import theStormbringer.util.Wiz;
import theStormbringer.util.Wiz.*;
import static theStormbringer.StormbringerMod.makeID;

public class Hailstorm extends AbstractWeather {
    static final String Weather_ID = makeID("Hailstorm");
    static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(Weather_ID);
    int hitcount;
    int damage;
    public Hailstorm(){
        name = stanceString.NAME;
        description = stanceString.DESCRIPTION[0];
        damage = 3;
        hitcount = 3;
        updateDescription();
    }
    @Override
    public String updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(stanceString.DESCRIPTION[0]);
        if (AbstractDungeon.player != null) {
            if (AbstractDungeon.player.hasPower(IcePower.POWER_ID)) {
                sb.append(damage + AbstractDungeon.player.getPower(IcePower.POWER_ID).amount);
            } else sb.append(damage);
            sb.append(stanceString.DESCRIPTION[1]);
            if (AbstractDungeon.player.hasPower(PowderSnow.POWER_ID)) {
                sb.append(hitcount + AbstractDungeon.player.getPower(PowderSnow.POWER_ID).amount);
            } else sb.append(hitcount);
            sb.append(stanceString.DESCRIPTION[2]);
        }
        return sb.toString();
    }
    @Override
    public void onWeatherSubside() {
        if (Wiz.adp().hasPower(ResistancePower.POWER_ID)){
            Wiz.atb(new RemoveSpecificPowerAction(Wiz.adp(),Wiz.adp(),ResistancePower.POWER_ID));
        }
        super.onWeatherSubside();
    }
    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.3F;
                AbstractDungeon.effectsQueue.add(new HailstormEffect(8,false));
                AbstractDungeon.effectsQueue.add(new HailstormEffect(8,false));
            }
            updateDescription();
        }
    }
    public void onSummonWeather() {
        weathertimer = 3;
        if (AbstractDungeon.player.hasPower(SnowWarning.POWER_ID)){
            weathertimer += AbstractDungeon.player.getPower(SnowWarning.POWER_ID).amount;
        }
    }

    @Override
    public void onEndOfTurn() {
        if (AbstractDungeon.player.hasPower(PowderSnow.POWER_ID)){
            for (int i = 0; i < hitcount + AbstractDungeon.player.getPower(PowderSnow.POWER_ID).amount; ++i) {
                if (AbstractDungeon.player.getPower(IcePower.POWER_ID) != null){
                    AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, damage + AbstractDungeon.player.getPower(IcePower.POWER_ID).amount, DamageInfo.DamageType.THORNS),
                            AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                } else AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        } else {
            for (int i = 0; i < hitcount; ++i) {
                if (AbstractDungeon.player.getPower(IcePower.POWER_ID) != null){
                    AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, damage + AbstractDungeon.player.getPower(IcePower.POWER_ID).amount, DamageInfo.DamageType.THORNS),
                            AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                } else AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
    }
}
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
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import theStormbringer.StormbringerMod;
import theStormbringer.VFX.HailstormEffect;
import theStormbringer.cards.AbstractForetellCard;
import theStormbringer.powers.*;
import theStormbringer.util.Wiz;
import theStormbringer.util.Wiz.*;
import static theStormbringer.StormbringerMod.makeID;

public class Hailstorm extends AbstractWeather {
    static final String Weather_ID = makeID("Hailstorm");
    static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(Weather_ID);
    int hitcount;
    public static int damage = 2;
    public Hailstorm(){
        name = stanceString.NAME;
        description = stanceString.DESCRIPTION[0];
        updateDescription();
    }
    @Override
    public String updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(stanceString.DESCRIPTION[0]);
        sb.append(damage);
        sb.append(stanceString.DESCRIPTION[1]);
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
        weathertimer = 4;
    }

    @Override
    public void onEndOfTurn() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (!m.isDeadOrEscaped()) {
                Wiz.applyToEnemy(m,new FrostbitePower(m,m,damage));
            }
        }

    }
}
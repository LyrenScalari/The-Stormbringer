package theStormbringer.cards.Rain;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import theStormbringer.actions.EmpowerAction;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;
import theStormbringer.util.WeatherEffects.HeavyRain;
import theStormbringer.util.Wiz;

import java.util.EnumMap;

import static theStormbringer.StormbringerMod.*;

public class Hurricane extends AbstractStormbringerCard {
    public final static String ID = makeID(Hurricane.class.getSimpleName());
    // intellij stuff attack, all_enemy, rare, 12, 3, , , 3, 

    public Hurricane() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY, TheStormbringer.Enums.COLOR_NAVY);
        baseDamage = 18;
        baseSecondDamage = 5;
        baseMagicNumber = magicNumber = 3;
        isMultiDamage = true;
        setOrbTexture(Elec_Energy, Elec_Energy_Portrait);
        energyCosts = new EnumMap<TypeEnergyHelper.Mana, Integer>(TypeEnergyHelper.Mana.class);
        energyCosts.put(TypeEnergyHelper.Mana.Water, 2);
        energyCosts.put(TypeEnergyHelper.Mana.Electric, 2);
        energyCosts.put(TypeEnergyHelper.Mana.Colorless, 2);
        Type = TypeEnergyHelper.Mana.Electric;
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (currentWeather instanceof HeavyRain) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }

    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else if (!(currentWeather instanceof HeavyRain)) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        } else {
            return canUse;
        }
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new WhirlwindEffect(Color.SKY.cpy(),false));
        Wiz.vfx(new WhirlwindEffect(Color.SKY.cpy(),true));
        addToBot(new DamageAllEnemiesAction(p,multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new DamageAllEnemiesAction(p,multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if (!TypeEnergyHelper.hasEnoughMana(energyCosts).containsValue(false)) {
            addToBot(new EmpowerAction(energyCosts,()-> new AbstractGameAction() {
                @Override
                public void update() {
                    addToBot(new DamageAllEnemiesAction(p,multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                    addToBot(new DamageAllEnemiesAction(p,multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                    isDone = true;
                }
            }));
        } else super.use(p,m);
    }

    public void upp() {
        upgradeDamage(3);
        upgradeSecondDamage(2);
    }
}
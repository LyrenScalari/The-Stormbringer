package theStormbringer.cards.Rain;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import theStormbringer.actions.EmpowerAction;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.powers.SurfPower;
import theStormbringer.util.TypeEnergyHelper;

import java.util.EnumMap;

import static theStormbringer.StormbringerMod.*;

public class Surf extends AbstractStormbringerCard {
    public final static String ID = makeID(Surf.class.getSimpleName());
    // intellij stuff attack, all_enemy, uncommon, 7, 3, , , 3, 1

    public Surf() {
        super(ID,1 , CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, TheStormbringer.Enums.COLOR_NAVY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 3;
        isMultiDamage = true;
        setOrbTexture(Water_Energy, Water_Energy_Portrait);
        energyCosts = new EnumMap<TypeEnergyHelper.Mana, Integer>(TypeEnergyHelper.Mana.class);
        energyCosts.put(TypeEnergyHelper.Mana.Water, 1);
        energyCosts.put(TypeEnergyHelper.Mana.Electric, 1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new BorderLongFlashEffect(Color.TEAL.cpy())));
        addToBot(new VFXAction(new BorderFlashEffect(Color.BLUE.cpy())));
        addToBot(new VFXAction(new WhirlwindEffect(Color.CYAN.cpy(),false)));
        addToBot(new DamageAllEnemiesAction(p,multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new EmpowerAction(energyCosts,()-> new ApplyPowerAction(p,p,new SurfPower("Clear Water",magicNumber))));
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}
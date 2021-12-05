package theStormbringer.cards.Ice;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.powers.ResistancePower;
import theStormbringer.util.TypeEnergyHelper;
import theStormbringer.util.WeatherEffects.Hailstorm;

import static theStormbringer.StormbringerMod.*;

public class AuroraBeam extends AbstractStormbringerCard {
    public final static String ID = makeID(AuroraBeam.class.getSimpleName());
    // intellij stuff attack, enemy, common, 7, 2, , , 1, 2

    public AuroraBeam() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, TheStormbringer.Enums.COLOR_NAVY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
        setOrbTexture(Ice_Energy,Ice_Energy_Portrait);
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Ice,secondMagic));
        if (currentWeather instanceof Hailstorm){
            addToBot(new ApplyPowerAction(p,p,new ResistancePower(p,p,magicNumber)));
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
        initializeDescription();
    }
}
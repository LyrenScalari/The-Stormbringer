package theStormbringer.cards.Ice;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.powers.IcePower;
import theStormbringer.util.TypeEnergyHelper;
import theStormbringer.util.WeatherEffects.Hailstorm;

import static theStormbringer.StormbringerMod.*;

public class IceBeam extends AbstractStormbringerCard {
    public final static String ID = makeID(IceBeam.class.getSimpleName());
    // intellij stuff Skill, All_Enemy, Uncommon, , , 10, 4, 1, 

    public IceBeam() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, TheStormbringer.Enums.COLOR_NAVY);
        baseBlock = 11;
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 2;
        setOrbTexture(Ice_Energy, Ice_Energy_Portrait);
        Type = TypeEnergyHelper.Mana.Ice;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        if (currentWeather instanceof Hailstorm) {
            currentWeather.weathertimer += magicNumber;
            addToBot(new ApplyPowerAction(p,p,new IcePower(p,p,secondMagic)));
        }
        super.use(p,m);
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
    }
}
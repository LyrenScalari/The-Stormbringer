package theStormbringer.cards.Ice;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.powers.IcePower;
import theStormbringer.powers.PowderSnow;
import theStormbringer.util.TypeEnergyHelper;

import static theStormbringer.StormbringerMod.*;

public class Blizzard extends AbstractStormbringerCard {
    public final static String ID = makeID(Blizzard.class.getSimpleName());
    // intellij stuff Power, None, Rare, , , , , 2, 

    public Blizzard() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.ENEMY, TheStormbringer.Enums.COLOR_NAVY);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 2;
        setOrbTexture(Ice_Energy, Ice_Energy_Portrait);
        Type = TypeEnergyHelper.Mana.Ice;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new PowderSnow(p,p,secondMagic)));
        addToBot(new ApplyPowerAction(p,p,new IcePower(p,p,magicNumber)));
        super.use(p,m);
    }

    public void upp() {
    }
}
package theStormbringer.cards.Rain;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;
import theStormbringer.util.Wiz;

import static theStormbringer.StormbringerMod.*;

public class Spark extends AbstractStormbringerCard {
    public final static String ID = makeID(Spark.class.getSimpleName());
    // intellij stuff skill, enemy, common, , , , , 1, 

    public Spark() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, TheStormbringer.Enums.COLOR_NAVY);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
        setOrbTexture(Elec_Energy,Elec_Energy_Portrait);
        Type = TypeEnergyHelper.Mana.Electric;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m,new WeakPower(m,magicNumber,false));
        super.use(p,m);
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
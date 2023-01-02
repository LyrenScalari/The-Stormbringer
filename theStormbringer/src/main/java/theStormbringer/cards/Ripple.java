package theStormbringer.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;

import static theStormbringer.StormbringerMod.*;

public class Ripple extends AbstractStormbringerCard {
    public final static String ID = makeID(Ripple.class.getSimpleName());
    // intellij stuff attack, enemy, common, 7, 2, , , 1, 2
    public Ripple() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, TheStormbringer.Enums.COLOR_SILVER);
        baseMagicNumber = magicNumber = 2;
        block = baseBlock = 8;
        setOrbTexture(Psy_Energy,Psy_Energy_Portrait);
        Type = TypeEnergyHelper.Mana.Psychic;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        super.use(p,m);
    }
    public void upp() {
        initializeDescription();
        upgradeBlock(2);
    }
}
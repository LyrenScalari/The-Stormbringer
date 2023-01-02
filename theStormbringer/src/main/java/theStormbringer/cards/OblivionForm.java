package theStormbringer.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;

import static theStormbringer.StormbringerMod.*;

public class OblivionForm extends AbstractStormbringerCard {
    public final static String ID = makeID(OblivionForm.class.getSimpleName());
    // intellij stuff attack, enemy, common, 7, 2, , , 1, 2
    public OblivionForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.ALL_ENEMY, TheStormbringer.Enums.COLOR_SILVER);
        baseMagicNumber = magicNumber = 4;
        setOrbTexture(Fairy_Energy,Fairy_Energy_Portrait);
        Type = TypeEnergyHelper.Mana.Fairy;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p,m);
    }

    public void upp() {
        initializeDescription();
    }
}
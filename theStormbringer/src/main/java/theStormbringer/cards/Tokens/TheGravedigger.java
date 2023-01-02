package theStormbringer.cards.Tokens;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;

import static theStormbringer.StormbringerMod.*;

public class TheGravedigger extends AbstractStormbringerCard {
    public final static String ID = makeID(TheGravedigger.class.getSimpleName());
    // intellij stuff attack, enemy, common, 7, 2, , , 1, 2
    public TheGravedigger() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, TheStormbringer.Enums.COLOR_SILVER);
        baseMagicNumber = magicNumber = 5;
        damage = baseDamage = 5;
        setOrbTexture(Dark_Energy,Dark_Energy_Portrait);
        Type = TypeEnergyHelper.Mana.Dark;
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        super.use(p,m);
    }
    public void upp() {
        initializeDescription();
        upgradeBlock(2);
    }
}
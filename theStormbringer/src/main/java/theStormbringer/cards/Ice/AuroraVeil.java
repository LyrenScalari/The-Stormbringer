package theStormbringer.cards.Ice;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.powers.ResistancePower;
import theStormbringer.util.WeatherEffects.Hailstorm;
import theStormbringer.util.Wiz;

import static theStormbringer.StormbringerMod.*;

public class AuroraVeil extends AbstractStormbringerCard {
    public final static String ID = makeID(AuroraVeil.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , 6, 4, 2, 

    public AuroraVeil() {
        super(ID,1 , CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, TheStormbringer.Enums.COLOR_NAVY);
        baseBlock = 7;
        baseMagicNumber = magicNumber = 3;
        setOrbTexture(Ice_Energy, Ice_Energy_Portrait);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        if (currentWeather instanceof Hailstorm){
            Wiz.applyToSelf(new ResistancePower(p,p,magicNumber));
        }
    }

    public void upp() {
        upgradeBlock(3);
    }
}
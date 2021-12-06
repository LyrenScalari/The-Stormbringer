package theStormbringer.cards.Rain;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.powers.ClarityPower;
import theStormbringer.powers.WaterPulsePower;
import theStormbringer.util.WeatherEffects.HeavyRain;
import theStormbringer.util.Wiz;

import static theStormbringer.StormbringerMod.*;

public class WaterPulse extends AbstractStormbringerCard {
    public final static String ID = makeID(WaterPulse.class.getSimpleName());
    // intellij stuff attack, Enemy, uncommon, 10, 2, , , 1, 

    public WaterPulse() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, TheStormbringer.Enums.COLOR_NAVY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 1;
        setOrbTexture(Water_Energy, Water_Energy_Portrait);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        Wiz.applyToSelf(new WaterPulsePower("Water Pulse",damage));
        super.use(p,m);
    }

    public void upp() {
        upgradeDamage(3);
    }
}
package theStormbringer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.Rain.WaterPulse;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.powers.WaterPulsePower;
import theStormbringer.util.TypeEnergyHelper;
import theStormbringer.util.Wiz;

import static theStormbringer.StormbringerMod.*;

public class MegaBurst  extends AbstractStormbringerCard {
    public final static String ID = makeID(MegaBurst.class.getSimpleName());
    // intellij stuff attack, Enemy, uncommon, 10, 2, , , 1,

    public MegaBurst() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.NONE, TheStormbringer.Enums.COLOR_NAVY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 1;
        setOrbTexture(Fairy_Energy, Fairy_Energy_Portrait);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Water,1));
        addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Fire,1));
        addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Ice,1));
        addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Dark,1));
        addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Psychic,1));
        addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Fairy,1));
        addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Electric,1));
    }

    public void upp() {
        upgradeDamage(3);
    }
}
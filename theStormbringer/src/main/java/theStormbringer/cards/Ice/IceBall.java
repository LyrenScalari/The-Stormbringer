package theStormbringer.cards.Ice;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.actions.EmpowerAction;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;

import java.util.EnumMap;

import static theStormbringer.StormbringerMod.*;

public class IceBall extends AbstractStormbringerCard {
    public final static String ID = makeID(IceBall.class.getSimpleName());
    // intellij stuff attack, enemy, common, 4, , , , 2, 

    public IceBall() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, TheStormbringer.Enums.COLOR_NAVY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 2;
        setOrbTexture(Ice_Energy, Ice_Energy_Portrait);
        energyCosts = new EnumMap<TypeEnergyHelper.Mana, Integer>(TypeEnergyHelper.Mana.class);
        energyCosts.put(TypeEnergyHelper.Mana.Ice, 2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new EmpowerAction(energyCosts,()-> new AbstractGameAction() {
            @Override
            public void update() {
                baseDamage = baseDamage*2;
                isDone = true;
            }
        }));
    }

    public void upp() {
        upgradeDamage(2);
    }
}
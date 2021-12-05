package theStormbringer.cards.Ice;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.powers.IcePower;
import theStormbringer.util.TypeEnergyHelper;

import static theStormbringer.StormbringerMod.*;

public class IcicleCrash extends AbstractStormbringerCard {
    public final static String ID = makeID(IcicleCrash.class.getSimpleName());
    // intellij stuff Attack, Enemy, Uncommon, , , , , , 

    public IcicleCrash() {
        super(ID,1 , CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, TheStormbringer.Enums.COLOR_NAVY);
        setOrbTexture(Ice_Energy, Ice_Energy_Portrait);
        damage = baseDamage = 8;
        magicNumber = baseMagicNumber = 1;
    }
    public void applyPowers() {
        AbstractPower icePower = AbstractDungeon.player.getPower(IcePower.POWER_ID);
        if (icePower != null) {
            baseDamage += icePower.amount;
        }
        super.applyPowers();
        baseDamage = 8;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p,baseDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        AbstractPower icePower = AbstractDungeon.player.getPower(IcePower.POWER_ID);
        if (icePower != null) {
            addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Ice,magicNumber+icePower.amount));
        } else addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Ice,magicNumber));

    }

    public void upp() {
        upgradeDamage(4);
    }
}
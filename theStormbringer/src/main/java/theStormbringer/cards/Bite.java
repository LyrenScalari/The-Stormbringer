package theStormbringer.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;

import java.util.ArrayList;
import java.util.List;

import static theStormbringer.StormbringerMod.*;

public class Bite extends AbstractStormbringerCard {
    public final static String ID = makeID(Bite.class.getSimpleName());
    // intellij stuff attack, enemy, common, 7, 2, , , 1, 2
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(BaseMod.getKeywordTitle("thestormbringer:Style"),BaseMod.getKeywordDescription("thestormbringer:Style")));
        return retVal;
    }
    public Bite() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, TheStormbringer.Enums.COLOR_SILVER);
        baseMagicNumber = magicNumber = 2;
        baseDamage = damage = 8;
        setOrbTexture(Dark_Energy,Dark_Energy_Portrait);
        Type = TypeEnergyHelper.Mana.Dark;
        energyCosts.put(TypeEnergyHelper.Mana.Dark, 1);
        energyCosts.put(TypeEnergyHelper.Mana.Colorless, 1);
        initializeDescription();
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (TypeEnergyHelper.hasEnoughMana(energyCosts).containsValue(false)) {
            super.use(p,m);
        } else {
            TypeEnergyHelper.handleElementalCosts(energyCosts);
            addToBot(new ApplyPowerAction(m,p,new WeakPower(m,magicNumber,false)));
        }
    }

    public void upp() {
        initializeDescription();
        energyCosts.remove(TypeEnergyHelper.Mana.Dark);
        upgradeDamage(2);
    }
}
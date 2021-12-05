package theStormbringer.cards.Ice;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import theStormbringer.actions.EmpowerAction;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;
import theStormbringer.util.Wiz;
import theStormbringer.util.Wiz.*;

import java.util.EnumMap;

import static theStormbringer.StormbringerMod.*;

public class IcyWind extends AbstractStormbringerCard {
    public final static String ID = makeID(IcyWind.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , 8, 2, 3, -1

    public IcyWind() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, TheStormbringer.Enums.COLOR_NAVY);
        baseBlock = 4;
        baseDamage = 4;
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 1;
        setOrbTexture(Ice_Energy, Ice_Energy_Portrait);
        energyCosts = new EnumMap<TypeEnergyHelper.Mana, Integer>(TypeEnergyHelper.Mana.class);
        energyCosts.put(TypeEnergyHelper.Mana.Ice, 2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new WhirlwindEffect());
        addToBot(new GainBlockAction(p,block));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        addToBot(new EmpowerAction(energyCosts,()-> new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    Wiz.applyToEnemy(mo, new WeakPower(mo, secondMagic, false));
                }
                isDone = true;
            }
        }));
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(-1);
        elementCost = new int[]{0,0,magicNumber,0,0,0,0,0};
    }
}
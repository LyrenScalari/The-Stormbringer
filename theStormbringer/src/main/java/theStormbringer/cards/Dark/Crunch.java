package theStormbringer.cards.Dark;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import theStormbringer.actions.EmpowerAction;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;

import java.util.EnumMap;

import static theStormbringer.StormbringerMod.makeID;
import static theStormbringer.StormbringerMod.*;
import static theStormbringer.util.Wiz.*;

public class Crunch extends AbstractStormbringerCard {
    public final static String ID = makeID(Crunch.class.getSimpleName());
    // intellij stuff Dark, Enemy, Common, 10, , , , 1, 

    public Crunch() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, TheStormbringer.Enums.COLOR_NAVY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 2;
        setOrbTexture(Dark_Energy, Dark_Energy_Portrait);
        initializeDescription();
        energyCosts = new EnumMap<TypeEnergyHelper.Mana, Integer>(TypeEnergyHelper.Mana.class);
        energyCosts.put(TypeEnergyHelper.Mana.Dark, magicNumber);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectsQueue.add(new BiteEffect(m.drawX,m.drawY, Color.DARK_GRAY));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        addToBot(new EmpowerAction(energyCosts,()-> new AbstractGameAction() {
            @Override
            public void update() {
                dmg(m, AbstractGameAction.AttackEffect.NONE);
                addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,secondMagic,false)));
                AbstractDungeon.effectsQueue.add(new BiteEffect(m.drawX,m.drawY, Color.LIGHT_GRAY));
                isDone = true;
            }
        }));

    }

    public void upp() {
        upgradeDamage(2);
    }
}
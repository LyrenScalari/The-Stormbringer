package theStormbringer.cards.Dark;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import theStormbringer.actions.EmpowerAction;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.patches.RenderFloatyMana;
import theStormbringer.util.TypeEnergyHelper;

import java.util.EnumMap;

import static theStormbringer.StormbringerMod.*;

public class Bite extends AbstractStormbringerCard {

    public static final String ID = makeID(Bite.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheStormbringer.Enums.COLOR_NAVY;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 2;

    public Bite() {
        super(ID,COST,TYPE,RARITY,TARGET,COLOR);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 1;
        setOrbTexture(Dark_Energy,Dark_Energy_Portrait);
        energyCosts = new EnumMap<TypeEnergyHelper.Mana, Integer>(TypeEnergyHelper.Mana.class);
        energyCosts.put(TypeEnergyHelper.Mana.Colorless, 2);
        Type = TypeEnergyHelper.Mana.Dark;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new BiteEffect(m.drawX,m.drawY)));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new ApplyPowerAction(m,p,new WeakPower(m,magicNumber,false)));
        if (!TypeEnergyHelper.hasEnoughMana(energyCosts).containsValue(false)) {
            addToBot(new EmpowerAction(energyCosts,()-> new DrawCardAction(1)));
        } else super.use(p,m);
    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeName();
        upgradeDamage(UPGRADE_PLUS_DMG);
        initializeDescription();
    }
}
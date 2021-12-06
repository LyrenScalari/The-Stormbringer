package theStormbringer.cards.Rain;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theStormbringer.actions.EmpowerAction;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;
import theStormbringer.util.WeatherEffects.HeavyRain;

import java.util.EnumMap;

import static theStormbringer.StormbringerMod.*;

public class Thunderbolt extends AbstractStormbringerCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Strike Deal 7(9) damage.
     */

    // TEXT DECLARATION

    public static final String ID = makeID(Thunderbolt.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");
    // Setting the image as as easy as can possibly be now. You just need to provide the image name
    // and make sure it's in the correct folder. That's all.
    // There's makeCardPath, makeRelicPath, power, orb, event, etc..
    // The list of all of them can be found in the main StormbringerMod.java file in the
    // ==INPUT TEXTURE LOCATION== section under ==MAKE IMAGE PATHS==


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheStormbringer.Enums.COLOR_NAVY;

    private static final int COST = 2;
    private static final int DAMAGE = 15;
    private static final int UPGRADE_PLUS_DMG = 2;

    public Thunderbolt() {
        super(ID,COST,TYPE,RARITY,TARGET,COLOR);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 2;
        setOrbTexture(Elec_Energy,Elec_Energy_Portrait);
        Type = TypeEnergyHelper.Mana.Electric;
        energyCosts = new EnumMap<TypeEnergyHelper.Mana, Integer>(TypeEnergyHelper.Mana.class);
        energyCosts.put(TypeEnergyHelper.Mana.Colorless,2);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p,m);
        addToBot(new VFXAction(new LightningEffect(m.drawX,m.drawY)));
        addToBot(new DamageAction(m,new DamageInfo(p,damage)));
        if (!TypeEnergyHelper.hasEnoughMana(energyCosts).containsValue(false)) {
            addToBot(new EmpowerAction(energyCosts,()-> new AbstractGameAction() {
                @Override
                public void update() {
                    addToBot(new GainEnergyAction(magicNumber));
                    addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Electric,magicNumber));
                    isDone = true;
                }
            }));
        } else super.use(p,m);
    }

    // Upgraded stats.
    @Override
    public void upp() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
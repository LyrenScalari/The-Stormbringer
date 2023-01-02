package theStormbringer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.cards.Tokens.SawitcomingToken;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.powers.ForetellPower;
import theStormbringer.util.TypeEnergyHelper;

import static theStormbringer.StormbringerMod.*;

public class Sawitcoming extends AbstractForetellCard {
    public final static String ID = makeID(Sawitcoming.class.getSimpleName());
    // intellij stuff attack, enemy, common, 7, 2, , , 1, 2
    public Sawitcoming() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, TheStormbringer.Enums.COLOR_SILVER);
        baseMagicNumber = magicNumber = 3;
        block = baseBlock = 8;
        setOrbTexture(Psy_Energy,Psy_Energy_Portrait);
        Type = TypeEnergyHelper.Mana.Psychic;
        cardsToPreview = new SawitcomingToken();
        ForetellCost.put(TypeEnergyHelper.Mana.Psychic, 1);
        ForetellCost.put(TypeEnergyHelper.Mana.Colorless, 1);
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryAction(magicNumber));
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (monster.getIntentBaseDmg() > 0){
                addToBot(new GainBlockAction(p,block));
                break;
            }
        }
        super.use(p,m);
    }
    public void triggerOnGlowCheck() {
        if (canSwap()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void onRightClick() {
        if (canSwap() && cardsToPreview != null) {
            if (AbstractDungeon.player.hand.contains(this)){
                addToBot(new ExhaustSpecificCardAction(this,AbstractDungeon.player.hand));
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        TypeEnergyHelper.handleElementalCosts(ForetellCost);
                        isDone = true;
                    }
                });
                addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new ForetellPower(this.name,cardsToPreview)));
            }
        }
    }

    public boolean canSwap() {
        return !TypeEnergyHelper.hasEnoughMana(ForetellCost).containsValue(false);
    }
    public void upp() {
        initializeDescription();
        upgradeBlock(2);
    }
}
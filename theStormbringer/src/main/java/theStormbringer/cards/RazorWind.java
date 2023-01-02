package theStormbringer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.cards.Tokens.RazorWindToken;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.powers.ForetellPower;
import theStormbringer.util.TypeEnergyHelper;

import static theStormbringer.StormbringerMod.*;

public class RazorWind extends AbstractForetellCard {
    public final static String ID = makeID(RazorWind.class.getSimpleName());
    // intellij stuff attack, enemy, common, 7, 2, , , 1, 2
    public RazorWind() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, TheStormbringer.Enums.COLOR_SILVER);
        baseMagicNumber = magicNumber = 3;
        baseDamage = damage = 4;
        cardsToPreview = new RazorWindToken();
        Type = TypeEnergyHelper.Mana.Colorless;
        ForetellCost.put(TypeEnergyHelper.Mana.Colorless, 3);
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++){
            addToBot(new DamageAction(m,new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
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
        upgradeDamage(1);
    }
}
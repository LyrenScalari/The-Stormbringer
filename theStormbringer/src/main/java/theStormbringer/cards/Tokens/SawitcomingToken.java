package theStormbringer.cards.Tokens;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.CommonKeywordIconsField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.cards.Sawitcoming;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;

import static theStormbringer.StormbringerMod.*;

@NoCompendium
public class SawitcomingToken extends AbstractStormbringerCard {
    public final static String ID = makeID(SawitcomingToken.class.getSimpleName());
    private AbstractCard origin;
    // intellij stuff attack, enemy, common, 7, 2, , , 1, 2
    public SawitcomingToken() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, TheStormbringer.Enums.COLOR_SILVER);
        baseMagicNumber = magicNumber = 3;
        block = baseBlock = 9;
        setOrbTexture(Psy_Energy,Psy_Energy_Portrait);
        Type = TypeEnergyHelper.Mana.Psychic;
        CommonKeywordIconsField.useIcons.set(this,true);
        purgeOnUse = true;
        selfRetain = true;
        initializeDescription();
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryAction(magicNumber));
        addToBot(new GainBlockAction(p,block));
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group){
            if (c instanceof Sawitcoming){
                AbstractCard card = c;
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        card.stopGlowing();
                        card.unhover();
                        card.unfadeOut();
                        card.setAngle(0.0F, true);
                        card.lighten(false);
                        card.drawScale = 0.12F;
                        card.targetDrawScale = 0.75F;
                        card.applyPowers();
                        AbstractDungeon.player.exhaustPile.removeCard(card);
                        AbstractDungeon.player.discardPile.addToTop(card);
                        isDone = true;
                    }
                });
                break;
            }
        }
        super.use(p,m);
    }

    public void upp() {
        initializeDescription();
        upgradeBlock(2);
    }
}
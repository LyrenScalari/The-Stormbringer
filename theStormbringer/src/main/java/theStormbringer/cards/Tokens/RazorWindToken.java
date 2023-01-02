package theStormbringer.cards.Tokens;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.CommonKeywordIconsField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.cards.RazorWind;
import theStormbringer.characters.TheStormbringer;

import static theStormbringer.StormbringerMod.makeID;
@NoCompendium
public class RazorWindToken extends AbstractStormbringerCard {
    public final static String ID = makeID(RazorWindToken.class.getSimpleName());
    private AbstractCard origin;
    // intellij stuff attack, enemy, common, 7, 2, , , 1, 2
    public RazorWindToken() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, TheStormbringer.Enums.COLOR_SILVER);
        baseMagicNumber = magicNumber = 3;
        baseDamage = damage = 4;
        CommonKeywordIconsField.useIcons.set(this,true);
        purgeOnUse = true;
        selfRetain = true;
        initializeDescription();
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++){
            addToBot(new DamageAction(m,new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group){
            if (c instanceof RazorWind){
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
        upgradeDamage(1);
    }
}
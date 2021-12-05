package theStormbringer.cards;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.ExceptionHandler;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theStormbringer.StormbringerMod;
import theStormbringer.util.CardArtRoller;
import theStormbringer.util.TypeEnergyHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

import static theStormbringer.StormbringerMod.makeImagePath;
import static theStormbringer.StormbringerMod.modID;
import static theStormbringer.util.Wiz.atb;
import static theStormbringer.util.Wiz.att;

public abstract class AbstractStormbringerCard extends CustomCard {
    protected final CardStrings cardStrings;

    public int secondMagic;
    public int baseSecondMagic;
    public boolean upgradedSecondMagic;
    public boolean isSecondMagicModified;
    private Logger logger = LogManager.getLogger(StormbringerMod.class.getName());
    public int secondDamage;
    public int baseSecondDamage;
    public boolean upgradedSecondDamage;
    public boolean isSecondDamageModified;
    public EnumMap<TypeEnergyHelper.Mana, Integer> energyCosts = new EnumMap<>(TypeEnergyHelper.Mana.class);
    protected int[] elementCost = new int[8];
    private float rotationTimer = 0;
    private int previewIndex;
    public static boolean alwaysFreeToCast = false;
    public boolean freeManaOnce = false;
    protected ArrayList<AbstractCard> cardToPreview = new ArrayList<>();
    private static float centerX = (float)Settings.WIDTH / 2.0F;
    private static float centerY = (float)Settings.HEIGHT / 2.0F;
    private Color renderColor = Color.WHITE.cpy();
    private boolean needsArtRefresh = false;
    private static Texture Mana_Orb_Dark, Mana_Orb_Psychic , Mana_Orb_Water, Mana_Orb_Fire, Mana_Orb_Ice, Mana_Orb_Fairy, Mana_Orb_Normal, Mana_Orb_Electric;

    public AbstractStormbringerCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(cardID, "", getCardTextureString(cardID.replace(modID + ":", ""), type),
                cost, "", type, color, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        rawDescription = cardStrings.DESCRIPTION;
        name = originalName = cardStrings.NAME;
        initializeTitle();
        initializeDescription();

        if (textureImg.contains("ui/missing.png")) {
            if (CardLibrary.getAllCards() != null && !CardLibrary.getAllCards().isEmpty()) {
                CardArtRoller.computeCard(this);
            } else
                needsArtRefresh = true;
        }
    }

    @Override
    protected Texture getPortraitImage() {
        if (textureImg.contains("ui/missing.png")) {
            return CardArtRoller.getPortraitTexture(this);
        }
        else {
            return super.getPortraitImage();
        }
    }

    public static String getCardTextureString(final String cardName, final AbstractCard.CardType cardType) {
        String textureString;

        switch (cardType) {
            case ATTACK:
            case POWER:
            case SKILL:
                textureString = makeImagePath("cards/" + cardName + ".png");
                break;
            default:
                textureString = makeImagePath("ui/missing.png");
                break;
        }

        FileHandle h = Gdx.files.internal(textureString);
        if (!h.exists()) {
            textureString = makeImagePath("ui/missing.png");
        }
        return textureString;
    }

    @Override
    public void applyPowers() {
        if (baseSecondDamage > -1) {
            secondDamage = baseSecondDamage;

            int tmp = baseDamage;
            baseDamage = baseSecondDamage;

            super.applyPowers();

            secondDamage = damage;
            baseDamage = tmp;

            super.applyPowers();

            isSecondDamageModified = (secondDamage != baseSecondDamage);
        } else super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (baseSecondDamage > -1) {
            secondDamage = baseSecondDamage;

            int tmp = baseDamage;
            baseDamage = baseSecondDamage;

            super.calculateCardDamage(mo);

            secondDamage = damage;
            baseDamage = tmp;

            super.calculateCardDamage(mo);

            isSecondDamageModified = (secondDamage != baseSecondDamage);
        } else super.calculateCardDamage(mo);
    }

    public void resetAttributes() {
        super.resetAttributes();
        secondMagic = baseSecondMagic;
        isSecondMagicModified = false;
        secondDamage = baseSecondDamage;
        isSecondDamageModified = false;
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedSecondMagic) {
            secondMagic = baseSecondMagic;
            isSecondMagicModified = true;
        }
        if (upgradedSecondDamage) {
            secondDamage = baseSecondDamage;
            isSecondDamageModified = true;
        }
    }

    protected void upgradeSecondMagic(int amount) {
        baseSecondMagic += amount;
        secondMagic = baseSecondMagic;
        upgradedSecondMagic = true;
    }

    protected void upgradeSecondDamage(int amount) {
        baseSecondDamage += amount;
        secondDamage = baseSecondDamage;
        upgradedSecondDamage = true;
    }

    protected void uDesc() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    protected void upgradeCardToPreview() {
        for (AbstractCard q : cardToPreview) {
            q.upgrade();
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upp();
        }
    }

    public abstract void upp();

    public void update() {
        super.update();
        if (needsArtRefresh) {
            CardArtRoller.computeCard(this);
        }
        if (!cardToPreview.isEmpty()) {
            if (hb.hovered) {
                if (rotationTimer <= 0F) {
                    rotationTimer = getRotationTimeNeeded();
                    cardsToPreview = cardToPreview.get(previewIndex);
                    if (previewIndex == cardToPreview.size() - 1) {
                        previewIndex = 0;
                    } else {
                        previewIndex++;
                    }
                } else {
                    rotationTimer -= Gdx.graphics.getDeltaTime();
                }
            }
        }
    }
    public int[] getManaCost(){ return elementCost;}
    public static void renderManaCost(AbstractStormbringerCard card, SpriteBatch sb){
        EnumMap<TypeEnergyHelper.Mana, Boolean> hasEnoughMana = TypeEnergyHelper.hasEnoughMana(card.energyCosts);
        float drawX = card.current_x - 256.0F;
        float drawY = card.current_y - 256.0F;

        if(Mana_Orb_Water == null){
            Mana_Orb_Water = ImageMaster.loadImage("theStormbringerResources/images/512/Water_Mana.png");
            Mana_Orb_Fire = ImageMaster.loadImage("theStormbringerResources/images/512/Fire_Mana.png");
            Mana_Orb_Ice = ImageMaster.loadImage("theStormbringerResources/images/512/Ice_Mana.png");
            Mana_Orb_Dark = ImageMaster.loadImage("theStormbringerResources/images/512/Dark_Mana.png");
            Mana_Orb_Normal = ImageMaster.loadImage("theStormbringerResources/images/512/Typless_Mana.png");
            Mana_Orb_Psychic = ImageMaster.loadImage("theStormbringerResources/images/512/Psychic_Mana.png");
            Mana_Orb_Fairy = ImageMaster.loadImage("theStormbringerResources/images/512/Fairy_Mana.png");
            Mana_Orb_Electric = ImageMaster.loadImage("theStormbringerResources/images/512/Electric_Mana.png");
        }

        if(!card.isLocked && card.isSeen) {
            float yOffset = 55.0F * Settings.scale * card.drawScale;
            int counter = 0;
            //logger.info("attempting render");
            for(Map.Entry<TypeEnergyHelper.Mana,Integer> e : card.energyCosts.entrySet()){
                TypeEnergyHelper.Mana mana = e.getKey();
                Texture tex;
                switch (mana) {
                    case Water:
                        tex = Mana_Orb_Water;
                        break;
                    case Fire:
                        tex = Mana_Orb_Fire;
                        break;
                    case Ice:
                        tex = Mana_Orb_Ice;
                        break;
                    case Dark:
                        tex = Mana_Orb_Dark;
                        break;
                    case Psychic:
                        tex = Mana_Orb_Psychic;
                        break;
                    case Fairy:
                        tex = Mana_Orb_Fairy;
                        break;
                    case Electric:
                        tex = Mana_Orb_Electric;
                        break;
                    case Colorless:
                        tex = Mana_Orb_Normal;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected mana type");
                }
                if (card.energyCosts.get(mana) != 0) {
                    Vector2 offset = new Vector2(0, -yOffset * counter);
                    offset.rotate(card.angle);
                    card.renderHelper(sb, card.renderColor, tex, drawX + offset.x, drawY + offset.y);

                    String msg = card.energyCosts.get(mana) + "";
                    Color costColor = Color.WHITE.cpy();
                    if (AbstractDungeon.player != null && AbstractDungeon.player.hand.contains(card)){
                        if (!hasEnoughMana.get(mana)) {
                            costColor = Color.RED.cpy();
                        } else if (alwaysFreeToCast || card.freeManaOnce) {
                            msg = "0";
                            costColor = Color.GREEN.cpy();
                        }
                    }

                    FontHelper.renderRotatedText(sb, getElementFont(card), msg, card.current_x,
                            card.current_y, -132.0F * card.drawScale * Settings.scale,
                            129.0F * card.drawScale * Settings.scale - yOffset * counter, card.angle,
                            true, costColor);
                    counter++;
                }
            }
        }
    }
    private void renderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
        try {
            sb.draw(img, drawX, drawY,
                    256.0F, 256.0F, 512.0F, 512.0F,
                    this.drawScale * Settings.scale, this.drawScale * Settings.scale,
                    this.angle, 0, 0, 512, 512, false, false);

        } catch (Exception e) {
            ExceptionHandler.handleException(e, logger);
        }
    }
    private static BitmapFont getElementFont(AbstractCard card) {
        FontHelper.cardEnergyFont_L.getData().setScale(card.drawScale * 0.75f);
        return FontHelper.cardEnergyFont_L;
    }
    protected float getRotationTimeNeeded() {
        return 1f;
    }

    // These shortcuts are specifically for cards. All other shortcuts that aren't specifically for cards can go in Wiz.
    protected void dmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
    }

    protected void dmgTop(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        att(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
    }

    protected void allDmg(AbstractGameAction.AttackEffect fx) {
        atb(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx));
    }

    protected void blck() {
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }
}
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package paparatto.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GoorruptionPower extends AbstractPower {
    public static final String POWER_ID = "Goorruption";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public GoorruptionPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Goorruption";
        this.owner = owner;
        this.amount = -1;
        this.description = DESCRIPTIONS[0];
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/GoorruptionBig.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Goorruption.png"), 0, 0, 32, 32);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void onCardDraw(AbstractCard card) {
        if (card.type == CardType.STATUS) {
            card.setCostForTurn(-9);
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == CardType.STATUS) {
            this.flash();
            card.setCostForTurn(-9);
            card.exhaust = true;
            action.exhaustCard = true;
        }

    }

    public boolean canPlayCard(AbstractCard card) {
        if (card.type == CardType.STATUS) {
            card.modifyCostForCombat(-9);
        }
        return true;
    }



    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Goorruption");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }


}

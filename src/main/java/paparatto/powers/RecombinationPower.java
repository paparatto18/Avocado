
package paparatto.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paparatto.actions.RandomDiscardCopyAction;
import paparatto.actions.SetCostRandomCardAction;

public class RecombinationPower extends AbstractPower {
    public static final String POWER_ID = "Recombination";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Recombination");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p;


    public RecombinationPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Recombination";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/RecombinationBig.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Recombination.png"), 0, 0, 32, 32);
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }


    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new RandomDiscardCopyAction(this.amount));

    }

}

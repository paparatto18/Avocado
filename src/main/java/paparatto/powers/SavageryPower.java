
package paparatto.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paparatto.actions.SetCostRandomCardAction;

import java.util.ArrayList;
import java.util.Iterator;

public class SavageryPower extends AbstractPower {
    public static final String POWER_ID = "Savagery";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Savagery");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p;


    public SavageryPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Savagery";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/SavageryBig.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Savagery.png"), 0, 0, 32, 32);
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4];
        }

    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new LoseHPAction(this.owner, this.owner, this.amount));
        for (int j = 0; j < this.amount; ++j) {
            this.addToBot(new SetCostRandomCardAction(0));
        }
    }


}

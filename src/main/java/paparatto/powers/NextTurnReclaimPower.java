
package paparatto.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import paparatto.actions.RandomDiscardCopyAction;

public class NextTurnReclaimPower extends AbstractPower {
    public static final String POWER_ID = "NextTurnReclaimPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("NextTurnReclaimPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p;


    public NextTurnReclaimPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "NextTurnReclaimPower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/NextTurnReclaimPowerBig.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/NextTurnReclaimPower.png"), 0, 0, 32, 32);
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
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "NextTurnReclaimPower"));
    }


}

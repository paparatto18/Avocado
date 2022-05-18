
package paparatto.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

public class StrangulationPower extends AbstractPower {
    public static final String POWER_ID = "Strangulation";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Strangulation");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    boolean hasProcced = false;

    public StrangulationPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Strangulation";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/StrangulationBig.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Strangulation.png"), 0, 0, 32, 32);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + "#b" + this.amount + DESCRIPTIONS[1];
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals("Constricted")) {
            if (target != this.owner) {
                if (!hasProcced) {
                    this.addToBot(new ApplyPowerAction(target, this.owner, new ConstrictedPower(target, this.owner, this.amount), this.amount));
                    hasProcced = true;
                } else {
                    hasProcced = false;
                }
            }
        }

    }


}


package paparatto.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.DeleteSaveConfirmPopup;

public class ElectroplatingPower extends AbstractPower implements BetterOnApplyPowerPower {
    public static final String POWER_ID = "Electroplating";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Electroplating");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    boolean hasProcced = false;

    public ElectroplatingPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Electroplating";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/ElectroplatingBig.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Electroplating.png"), 0, 0, 32, 32);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + "#b" + this.amount + DESCRIPTIONS[1];
    }
//
//    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
//        if (power.ID.equals("Plated Armor")) {
//            if (target == this.owner) {
//                if (!hasProcced) {
//                    this.addToBot(new ApplyPowerAction(this.owner, this.owner, new PlatedArmorPower(this.owner, this.amount), this.amount));
//                    hasProcced = true;
//                } else {
//                    hasProcced = false;
//                }
//            }
//        }
//
//    }

    @Override
    public boolean betterOnApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals("Plated Armor") && target == this.owner) {
            power.amount += this.amount;
        }
        return true;
    }

    @Override
    public int betterOnApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (power.ID.equals("Plated Armor") && target == this.owner) {
            stackAmount += this.amount;
        }
        return stackAmount;
    }


}

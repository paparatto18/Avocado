
package paparatto.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class FungalPower extends AbstractPower implements OnReceivePowerPower {
    public static final String POWER_ID = "Fungal";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Fungal");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    boolean hasProcced = false;

    public FungalPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Fungal";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/FungalBig.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Fungal.png"), 0, 0, 32, 32);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + "#b" + this.amount;
    }



    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals("Vulnerable") && target == this.owner){

            power.amount -= this.amount;
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            if (power.amount > 0) {
                return true;
            }else {
                return false;
            }

        }else {
            return true;
        }
    }

    public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (power.ID.equals("Vulnerable") && target == this.owner){

            stackAmount -= this.amount;
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            if (power.amount > 0) {
                return stackAmount;
            }else {
                return 0;
            }
        }else {
            return stackAmount;
        }
    }
}





package paparatto.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class EnragedPower extends AbstractPower implements BetterOnApplyPowerPower {
    public static final String POWER_ID = "Enraged";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Enraged");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    boolean hasProcced = false;

    public EnragedPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Enraged";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("anger");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }


    @Override
    public boolean betterOnApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == PowerType.DEBUFF && target == this.owner ){
            this.flash();
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
        }
        return true;
    }

//    @Override
//    public int betterOnApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
//        if (power.type == PowerType.DEBUFF){
//            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
//        }
//        return stackAmount;
//    }


}

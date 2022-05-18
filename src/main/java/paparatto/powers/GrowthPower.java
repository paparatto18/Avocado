//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package paparatto.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class GrowthPower extends AbstractPower {

    public static final String POWER_ID = "GrowthPower";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public GrowthPower(AbstractCreature owner, int strAmt) {
        this.name = NAME;
        this.ID = "GrowthPower";
        this.owner = owner;
        this.amount = strAmt;
        if (this.amount >= 999) {
            this.amount = 999;
        }

        this.updateDescription();
        this.loadRegion("energized_green");
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 999) {
            this.amount = 999;
        }

    }
    @Override
    public void updateDescription() { this.description = DESCRIPTIONS[0] + "#b" + amount + DESCRIPTIONS[1]; }


    public void onEnergyRecharge() {
        this.flash();
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "GrowthPower"));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("GrowthPower");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}

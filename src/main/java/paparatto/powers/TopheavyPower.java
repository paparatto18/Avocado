
package paparatto.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.EntangleEffect;

public class TopheavyPower extends AbstractPower {
    public static final String POWER_ID = "Topheavy";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Topheavy");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TopheavyPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Topheavy";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.DEBUFF;
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/TopheavyBig.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Topheavy.png"), 0, 0, 32, 32);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];

    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount > 0) {
            this.flash();
            this.addToTop(new ApplyPowerAction(this.owner, this.owner, new WeakPower(this.owner, this.amount, true), this.amount));
        }

        return damageAmount;
    }


}

package paparatto.powers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ParasiticFormPower
  extends AbstractPower {
  public static final String POWER_ID = paparatto.Avocado.makeID("ParasiticForm");
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  public static final String IMG = "pits/images/powers/placeholder_power.png";
  Color greenColor = new Color(0.0F, 1.0F, 0.0F, 1.0F);

  private static final int STARTING_BLOCK = 3;

  public static int baseBlockAmount = 3;
  private int blockAmount = 0;

  public ParasiticFormPower(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = "ParasiticForm";
    this.owner = owner;
    this.amount = amount;
    this.blockAmount = baseBlockAmount;

    updateDescription();
    this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/MalleableBig.png"), 0, 0, 92, 92);
    this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Malleable.png"), 0, 0, 32, 32);
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.blockAmount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
  }

  public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
    if (power.ID.equals("ParasiticForm")) {
      this.blockAmount += baseBlockAmount;
    }
  }

  public void wasHPLost(DamageInfo info, int damageAmount) {
    if ((damageAmount < this.owner.currentHealth) && (damageAmount > 0) && (info.owner != null)) {
      flash();
      this.addToTop(new GainBlockAction(this.owner, this.owner, this.blockAmount));
      this.blockAmount += this.amount;
      updateDescription();
    }


  }
@Override
  public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
    if (this.amount > 0) {
      if (!this.isTurnBased) {
        this.greenColor.a = c.a;
        c = this.greenColor;
      }

      FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount), x, y - 2.0F, this.fontScale, c);
      FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.blockAmount), x, y + 20.0F, this.fontScale, c);
    }

}

}






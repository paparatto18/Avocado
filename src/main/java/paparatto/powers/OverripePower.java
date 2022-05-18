package paparatto.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;


public class OverripePower extends AbstractPower

{
    public static final String POWER_ID = paparatto.Avocado.makeID("OverripePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("OverripePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;




  private boolean trigger;

  public OverripePower(AbstractCreature owner, int amount)
  {
    this.name = NAME;
    this.ID = "OverripePower";
    this.owner = owner;

    this.amount = amount;
    this.type = PowerType.DEBUFF;
    
    updateDescription();
    this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/OverripeBig.png"), 0, 0, 92, 92);
    this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Overripe.png"), 0, 0, 32, 32);
  }


  public void updateDescription()
  {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }

  public void atStartOfTurn() {
    this.flash();
    this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -this.amount), -this.amount));
  }




}

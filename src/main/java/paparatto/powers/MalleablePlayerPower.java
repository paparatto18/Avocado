package paparatto.powers;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MalleablePlayerPower
  extends AbstractPower
{
    public static final String POWER_ID = paparatto.Avocado.makeID("MalleablePlayer");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "pits/images/powers/placeholder_power.png";

  private static final int STARTING_BLOCK = 3;

  public static int baseAmount = 0;
  
  public MalleablePlayerPower(AbstractCreature owner, int amount)
  {
    this.name = NAME;
    this.ID = "Malleable";
    this.owner = owner;
    this.baseAmount = amount;
    this.amount = amount;
    
    updateDescription();
    this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/MalleableBig.png"), 0, 0, 92, 92);
    this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Malleable.png"), 0, 0, 32, 32);
  }
  
  public void updateDescription()
  {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.name + DESCRIPTIONS[2]);
  }
  
  public void atEndOfRound()
  {
    this.amount = this.baseAmount;
    updateDescription();
  }

  public void atEndOfTurn(boolean isPlayer)
  {
    this.amount = this.baseAmount;
    updateDescription();
  }
  
  public int onAttacked(DamageInfo info, int damageAmount)
  {
    if ((damageAmount < this.owner.currentHealth) && (damageAmount > 0) && (info.owner != null) && (info.type == DamageInfo.DamageType.NORMAL) && (info.type != DamageInfo.DamageType.HP_LOSS))
    {
      flash();
      AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.amount));
      this.amount += 1;
      updateDescription();
    }
    return damageAmount;
  }
}

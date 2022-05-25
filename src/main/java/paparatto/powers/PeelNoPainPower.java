package paparatto.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class PeelNoPainPower extends AbstractPower

{
    public static final String POWER_ID = paparatto.Avocado.makeID("PeelNoPain");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("PeelNoPain");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "pits/images/powers/placeholder_power.png";



  public static int baseAmount = 0;
  private boolean trigger;

  public PeelNoPainPower(AbstractCreature owner, int amount)
  {
    this.name = NAME;
    this.ID = "PeelNoPain";
    this.owner = owner;
    this.baseAmount = amount;
    this.amount = amount;
    
    updateDescription();
    this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/FeelNoGunkBig.png"), 0, 0, 84, 84);
    this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/FeelNoGunk.png"), 0, 0, 32, 32);
  }

  public void playApplyPowerSfx() {
    CardCrawlGame.sound.play("POWER_METALLICIZE", 0.05F);
  }

  public void updateDescription()
  {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
  }

}

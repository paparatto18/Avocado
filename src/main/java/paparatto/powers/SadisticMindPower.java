package paparatto.powers;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import paparatto.Avocado;

public class SadisticMindPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = paparatto.Avocado.makeID("SadisticMindPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "pits/images/powers/placeholder_power.png";

    public SadisticMindPower(AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        type = PowerType.BUFF;
        isTurnBased = false;
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/SadisticMindBig.png"), 0, 0, 92, 92);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/SadisticMind.png"), 0, 0, 32, 32);
    }


  public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source)
  {
    if ((power.type == AbstractPower.PowerType.DEBUFF) && (!power.ID.equals("Shackled")) && (source == this.owner) && (target != this.owner) && 
      (!target.hasPower("Artifact")))
    {
      flash();
      AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.amount));
    }
  }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}

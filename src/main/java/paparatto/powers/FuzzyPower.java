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

public class FuzzyPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = paparatto.Avocado.makeID("FuzzyPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "pits/images/powers/placeholder_power.png";

    public FuzzyPower(final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        updateDescription();
        type = PowerType.BUFF;
        isTurnBased = false;
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/FuzzyBig.png"), 0, 0, 92, 92);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Fuzzy.png"), 0, 0, 32, 32);
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!isPlayer) { return; }

        // No cards.
        if (AbstractDungeon.player.hand.group.size() == 0) { return; }
        
        // we're retaining the whole group
        if (AbstractDungeon.player.hand.group.size() <= this.amount) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                c.retain = true;
            }
            return;
        }

        // We're retaining part of the group
        for (int i = AbstractDungeon.player.hand.group.size()-1; i >= AbstractDungeon.player.hand.group.size()-this.amount; i--) {
            AbstractDungeon.player.hand.group.get(i).retain = true;
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}

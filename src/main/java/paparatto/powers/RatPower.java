
package paparatto.powers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseTempHpPower;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import paparatto.cards.BiteChoice;
import paparatto.cards.GrowChoice;

import java.util.ArrayList;
import java.util.Iterator;

public class RatPower extends AbstractPower {
    public static final String POWER_ID = "Rat";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Rat");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p;
    private boolean upgraded;
    private ArrayList<AbstractGameEffect> effect = new ArrayList();
    private float vfxTimer = 1.0F;




    public RatPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Rat";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/RatBig.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Rat.png"), 0, 0, 32, 32);
    }

    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0];
        }else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        for (int i = 0; i < amount; i++) {
            ArrayList<AbstractCard> stanceChoices = new ArrayList();
            stanceChoices.add(new GrowChoice());
            stanceChoices.add(new BiteChoice());
            if (this.upgraded) {
                Iterator var4 = stanceChoices.iterator();

                while (var4.hasNext()) {
                    AbstractCard c = (AbstractCard) var4.next();
                    c.upgrade();
                }
            }


            this.addToBot(new ChooseOneAction(stanceChoices));
        }
    }






}

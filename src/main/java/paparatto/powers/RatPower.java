
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
    private float animationFrame;



    public RatPower(AbstractCreature owner, boolean upgraded) {
        this.name = NAME;
        this.ID = "Rat";
        this.owner = owner;
        this.updateDescription();
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/RatBig.png"), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Rat.png"), 0, 0, 32, 32);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        ArrayList<AbstractCard> stanceChoices = new ArrayList();
        stanceChoices.add(new GrowChoice());
        stanceChoices.add(new BiteChoice());
        if (this.upgraded) {
            Iterator var4 = stanceChoices.iterator();

            while(var4.hasNext()) {
                AbstractCard c = (AbstractCard)var4.next();
                c.upgrade();
            }
        }

        this.addToBot(new ChooseOneAction(stanceChoices));
    }


//
//    @Override
//    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
//        float vfxTimer = Gdx.graphics.getDeltaTime() * 100.0F;
//        int animationFrame = MathUtils.round((vfxTimer / 3)%35);
//        if (this.img != null) {
//            this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Rat/frame_"+animationFrame+"_delay-0.03s.png"), 0, 0, 32, 32);
//            sb.setColor(c);
//            sb.draw(this.img, x - 12.0F, y - 12.0F, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0, 32, 32, false, false);
//        } else {
//            sb.setColor(c);
//            if (Settings.isMobile) {
//                this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Rat/frame_"+this.animationFrame+"_delay-0.03s.png"), 0, 0, 32, 32);
//                sb.draw(this.region48, x - (float)this.region48.packedWidth / 2.0F, y - (float)this.region48.packedHeight / 2.0F, (float)this.region48.packedWidth / 2.0F, (float)this.region48.packedHeight / 2.0F, (float)this.region48.packedWidth, (float)this.region48.packedHeight, Settings.scale * 1.17F, Settings.scale * 1.17F, 0.0F);
//            } else {
//                this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("pits/images/powers/Rat/frame_"+this.animationFrame+"_delay-0.03s.png"), 0, 0, 32, 32);
//                sb.draw(this.region48, x - (float)this.region48.packedWidth / 2.0F, y - (float)this.region48.packedHeight / 2.0F, (float)this.region48.packedWidth / 2.0F, (float)this.region48.packedHeight / 2.0F, (float)this.region48.packedWidth, (float)this.region48.packedHeight, Settings.scale, Settings.scale, 0.0F);
//            }
//        }
//
//        Iterator var5 = this.effect.iterator();
//
//        while(var5.hasNext()) {
//            AbstractGameEffect e = (AbstractGameEffect)var5.next();
//            e.render(sb, x, y);
//        }
//
//    }



}

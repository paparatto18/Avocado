
package paparatto.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseTempHpPower;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paparatto.cards.BiteChoice;
import paparatto.cards.GrowChoice;

import java.util.ArrayList;
import java.util.Iterator;

public class RatPower extends AbstractPower implements OnLoseTempHpPower {
    public static final String POWER_ID = "Rat";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Rat");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p;
    private boolean upgraded;


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



    @Override
    public int onLoseTempHp(DamageInfo damageInfo, int i) {
//        if ((Integer)TempHPField.tempHp <= 0) {
//            this.addToBot( new RemoveSpecificPowerAction(this.owner, this.owner,"Rat"));
//            return i;
//        }

        return i;
    }
}

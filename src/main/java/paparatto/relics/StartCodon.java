package paparatto.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;

public class StartCodon extends CustomRelic {
    public static final String ID = "StartCodon";
    private boolean firstTurn;


    public StartCodon() {
        super(ID, new Texture("pits/images/relics/StartCodon.png"),new Texture("pits/images/relics/outline/StartCodon.png"), RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atPreBattle() {
        this.firstTurn = true;
    }

    public void atBattleStart() {
        if (this.firstTurn) {
            int count = 0;
            Iterator<AbstractCard> var1 = AbstractDungeon.player.masterDeck.group.iterator();

            AbstractCard c;
            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (c.isInnate) {
                    ++count;
                }
            }
            if (count >0) {
                this.flash();
            }
            this.addToTop(new GainEnergyAction(count));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.firstTurn = false;
        }

    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new StartCodon();
    }

}
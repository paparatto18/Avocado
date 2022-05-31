//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package paparatto.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RandomDiscardCopyAction extends AbstractGameAction {


    private boolean freeToPlayOnce;
    private AbstractPlayer p;

    public RandomDiscardCopyAction(int amount) {
        this.p = AbstractDungeon.player;
        this.amount = amount;
    }

    public RandomDiscardCopyAction(int amount, boolean freeToPlayOnce) {
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.freeToPlayOnce = freeToPlayOnce;

    }

    public void update() {
        if (this.p.discardPile.size() != 0) {
            for (int i = 0; i < this.amount; ++i) {
                AbstractCard card = p.discardPile.getRandomCard(AbstractDungeon.cardRandomRng).makeStatEquivalentCopy();
                card.freeToPlayOnce = freeToPlayOnce;
                this.addToBot(new MakeTempCardInHandAction(card));

            }
        }

        this.isDone = true;


    }


}

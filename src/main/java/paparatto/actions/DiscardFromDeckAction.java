package paparatto.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import java.util.ArrayList;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;


public class DiscardFromDeckAction extends AbstractGameAction {
  private AbstractPlayer p;
  
  public DiscardFromDeckAction(int amount)
  {
    this.p = AbstractDungeon.player;
    setValues(this.p, AbstractDungeon.player, amount);
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.duration = Settings.ACTION_DUR_MED;
  }
  
  public void update()
  {
    CardGroup tmp;
    if (this.duration == Settings.ACTION_DUR_MED)
    {
      tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
      for (AbstractCard c : this.p.drawPile.group) {
        tmp.addToRandomSpot(c);
      }
      if (tmp.size() == 0)
      {
        this.isDone = true;

      }
      if (tmp.size() == 1)
      {
        AbstractCard card = tmp.getTopCard();
        card.unhover();
        card.lighten(true);
        card.setAngle(0.0F);
        card.drawScale = 0.12F;
        card.targetDrawScale = 0.75F;
        card.current_x = CardGroup.DRAW_PILE_X;
        card.current_y = CardGroup.DRAW_PILE_Y;
        AbstractDungeon.actionManager.addToTop(new DiscardSpecificCardAction(card, this.p.drawPile));

        this.isDone = true;

      }
      if (tmp.size() <= this.amount)
      {
        for (int i = 0; i < tmp.size(); i++)
        {
          AbstractCard card = tmp.getNCardFromTop(i);
          card.unhover();
          card.lighten(true);
          card.setAngle(0.0F);
          card.drawScale = 0.12F;
          card.targetDrawScale = 0.75F;
          card.current_x = CardGroup.DRAW_PILE_X;
          card.current_y = CardGroup.DRAW_PILE_Y;
          AbstractDungeon.actionManager.addToTop(new DiscardSpecificCardAction(card, this.p.drawPile));
        }
        this.isDone = true;

      }
      if (this.amount == 1) {
        AbstractDungeon.gridSelectScreen.open(tmp, this.amount, "Discard", false);
      } else {
        AbstractDungeon.gridSelectScreen.open(tmp, this.amount, "Discard " + Integer.toString(this.amount) + " cards.", false);
      }
      tickDuration();

    }
    if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0)
    {
      for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
      {
        c.unhover();
        AbstractDungeon.actionManager.addToTop(new DiscardSpecificCardAction(c, this.p.drawPile));
      }
      AbstractDungeon.gridSelectScreen.selectedCards.clear();
    }
    tickDuration();

  }
}

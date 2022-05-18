package paparatto.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.MummifiedHand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;


public class SetCostRandomCardAction extends AbstractGameAction {
  private final int cost;
  private AbstractPlayer p;
  private static final Logger logger = LogManager.getLogger(SetCostRandomCardAction.class.getName());

  public SetCostRandomCardAction(int cost)
  {
    this.p = AbstractDungeon.player;
    this.actionType = ActionType.CARD_MANIPULATION;
    this.duration = Settings.ACTION_DUR_MED;
    this.cost = cost;
  }
  
  public void update() {
    ArrayList<AbstractCard> groupCopy = new ArrayList();
    Iterator var4 = AbstractDungeon.player.hand.group.iterator();

    while (true) {
      while (var4.hasNext()) {
        AbstractCard c = (AbstractCard) var4.next();
        if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce) {
          groupCopy.add(c);
        } else {
          logger.info("COST IS 0: " + c.name);
        }
      }

      var4 = AbstractDungeon.actionManager.cardQueue.iterator();

      while (var4.hasNext()) {
        CardQueueItem i = (CardQueueItem) var4.next();
        if (i.card != null) {
          logger.info("INVALID: " + i.card.name);
          groupCopy.remove(i.card);
        }
      }

      AbstractCard c = null;
      if (groupCopy.isEmpty()) {
        logger.info("NO VALID CARDS");
      } else {
        logger.info("VALID CARDS: ");
        Iterator var9 = groupCopy.iterator();

        while (var9.hasNext()) {
          AbstractCard cc = (AbstractCard) var9.next();
          logger.info(cc.name);
        }

        c = (AbstractCard) groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
      }

      if (c != null) {
        logger.info("SetCostRandomCardAction: " + c.name);
        c.setCostForTurn(this.cost);
      } else {
        logger.info("ERROR: SET COST RANDOM CARD ACTION NOT WORKING");
      }
      break;
    }
    this.isDone = true;
  }
}

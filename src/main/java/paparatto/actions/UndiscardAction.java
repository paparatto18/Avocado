package paparatto.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import java.util.ArrayList;

public class UndiscardAction
  extends AbstractGameAction
{
  private AbstractCard card;
  private boolean exhaust;
  
  public UndiscardAction(AbstractCard card, boolean exhaust)
  {
    this.duration = Settings.ACTION_DUR_XFAST;
    this.card = card;
    this.exhaust = exhaust;
  }
  
  public UndiscardAction(AbstractCard card)
  {
    this(card, false);
  }
  
  public void update()
  {
    if (this.duration == Settings.ACTION_DUR_XFAST)
    {
      if (!this.exhaust) {}
      AbstractDungeon.player.discardPile.removeCard(this.card);
      if (this.exhaust) {
        AbstractDungeon.effectList.add(new ExhaustCardEffect(this.card));
      }
      this.isDone = true;
    }

  }
}

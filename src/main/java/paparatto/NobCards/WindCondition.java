package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;

import java.util.ArrayList;
import java.util.Iterator;

public class WindCondition extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("WindCondition");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public WindCondition() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 7;
        damageUp = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList();
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.type == CardType.ATTACK && c != this) {
                cardsToExhaust.add(c);
            }
        }

        var2 = cardsToExhaust.iterator();

        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            act(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }

        var2 = cardsToExhaust.iterator();

        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        }


    }
}
package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;
import paparatto.cards.AbstractAvocadoCharacterCard;

public class BasicDraw extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("BasicDraw");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public BasicDraw() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseMagicNumber = 2;
        magicNumberUp = 1;



    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DrawCardAction(p, this.magicNumber));
    }
}
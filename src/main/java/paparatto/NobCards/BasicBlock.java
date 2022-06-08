package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;
import paparatto.cards.AbstractAvocadoCharacterCard;

;
    
public class BasicBlock extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("BasicBlock");

    private static final CardRarity RARITY = CardRarity.COMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    ;

    public BasicBlock() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseBlock = 9;
        blockUp = 4;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new GainBlockAction(p, p, this.block));
    }
}
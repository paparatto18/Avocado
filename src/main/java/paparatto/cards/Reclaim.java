package paparatto.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;
import paparatto.actions.RandomDiscardCopyAction;

import java.util.Random;

public class Reclaim extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Reclaim");

    private static final CardRarity RARITY = CardRarity.COMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Reclaim() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseBlock = 8;
        blockUp = 2;
        baseMagicNumber = 1;
        magicNumberUp = 1;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new GainBlockAction(p, p, this.block));
        act(new RandomDiscardCopyAction(this.magicNumber));
    }
}
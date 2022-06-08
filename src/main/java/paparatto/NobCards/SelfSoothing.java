package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import paparatto.Avocado;

;

public class SelfSoothing extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("SelfSoothing");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs



    public SelfSoothing() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseBlock = 12;
        blockUp = 4;
        this.shuffleBackIntoDrawPile = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new GainBlockAction(p, p, this.block));
        act(new ApplyPowerAction(p,p, new WeakPower(p, 2, false), 2));
    }
}
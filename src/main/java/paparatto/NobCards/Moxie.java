package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import paparatto.Avocado;
import paparatto.cards.AbstractAvocadoCharacterCard;

;

public class Moxie extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("Moxie");

    private static final CardRarity RARITY = CardRarity.COMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    ;

    public Moxie() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseBlock = 4;
        blockUp = 1;
        baseMagicNumber = 4;
        magicNumberUp = 2;
        magicNumber = baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new GainBlockAction(p, p, this.block));
        act(new ApplyPowerAction(p,p, new VigorPower(p, this.magicNumber), this.magicNumber));
    }
}
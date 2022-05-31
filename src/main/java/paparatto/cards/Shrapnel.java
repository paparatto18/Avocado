package paparatto.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;
import paparatto.powers.PeelNoPainPower;
import paparatto.powers.ShrapnelPower;

public class Shrapnel extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Shrapnel");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.POWER;       // ATTACK, SKILL, POWER

    private static final int COST = 0;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Shrapnel() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseMagicNumber = 5;
        magicNumberUp = 2;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ApplyPowerAction(p, p, new ShrapnelPower(p, this.magicNumber), this.magicNumber));
    }
}
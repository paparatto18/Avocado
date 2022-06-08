package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import paparatto.Avocado;
import paparatto.powers.EnragedPower;

public class Bellow extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("Bellow");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.POWER;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Bellow() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseMagicNumber = 1;
        magicNumberUp = 1;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ApplyPowerAction(p, p, new EnragedPower(p, this.magicNumber), this.magicNumber));
    }
}
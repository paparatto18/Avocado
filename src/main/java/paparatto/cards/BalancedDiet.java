package paparatto.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import paparatto.Avocado;

public class BalancedDiet extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("BalancedDiet");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.POWER;       // ATTACK, SKILL, POWER

    private static final int COST = 2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public BalancedDiet() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseMagicNumber = 1;
        magicNumberUp = 1;
        magicNumber = baseMagicNumber;

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        act(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        act(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber), this.magicNumber));
    }
}
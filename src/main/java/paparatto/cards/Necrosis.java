package paparatto.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import paparatto.Avocado;

public class Necrosis extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Necrosis");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 0;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Necrosis() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseMagicNumber = 2;
        magicNumberUp = -1;
        magicNumber = baseMagicNumber;
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ApplyPowerAction(p,p, new VulnerablePower(p, this.magicNumber, false), this.magicNumber));
        act(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 6), 6));
    }
}
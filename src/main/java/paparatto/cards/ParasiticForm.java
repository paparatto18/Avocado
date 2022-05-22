package paparatto.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;
import paparatto.powers.OrichalcumPower;
import paparatto.powers.ParasiticFormPower;

public class ParasiticForm extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Parasitic_Form");

    private static final CardRarity RARITY = CardRarity.RARE; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.POWER;       // ATTACK, SKILL, POWER

    private static final int COST = 3;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public ParasiticForm() {
        super(ID, COST, TYPE, RARITY, TARGET);
        isEthereal = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ApplyPowerAction(p, p, new ParasiticFormPower(p,1),1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.isEthereal = false;
            this.upgradeName();
            this.rawDescription = this.upgradeDescription;
            this.initializeDescription();
        }
    }
}

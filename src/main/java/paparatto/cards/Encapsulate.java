package paparatto.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import paparatto.Avocado;

public class Encapsulate extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Encapsulate");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Encapsulate() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseMagicNumber = 3;
        magicNumberUp = 1;
        magicNumber = baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ApplyPowerAction(m, p, new PlatedArmorPower(m,this.magicNumber), this.magicNumber));
        for (int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new WaitAction(0.1F));
            act(new ApplyPowerAction(m, p, new ConstrictedPower(m, p, 3), 3));
        }
    }
}
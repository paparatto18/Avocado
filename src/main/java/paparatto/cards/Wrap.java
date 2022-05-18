package paparatto.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;
import paparatto.actions.WrapAction;

public class Wrap extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Wrap");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;   // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = -1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Wrap() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseMagicNumber = 4;
        magicNumberUp = 0;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

       act( new WrapAction(p, this.upgraded, this.freeToPlayOnce, this.energyOnUse, this.magicNumber));
    }
}
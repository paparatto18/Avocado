package paparatto.cards;

import com.megacrit.cardcrawl.actions.unique.SkewerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;
import paparatto.actions.PitStormAction;
import paparatto.actions.WrapAction;

public class PitStorm extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("PitStorm");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;   // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = -1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public PitStorm() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new PitStormAction(p, m, this.upgraded, this.damage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }
}
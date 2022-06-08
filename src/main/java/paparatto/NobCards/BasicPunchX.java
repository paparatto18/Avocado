package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;
import paparatto.cards.AbstractAvocadoCharacterCard;

public class BasicPunchX extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("BasicPunchX");

    private static final CardRarity RARITY = CardRarity.RARE;   // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = -1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public BasicPunchX() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 12;         
        damageUp = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new SFXAction("ATTACK_HEAVY"));
        act(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
}
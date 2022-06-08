package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import paparatto.Avocado;
import paparatto.cards.AbstractAvocadoCharacterCard;

public class BasicPowerPunch extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("BasicPowerPunch");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.POWER;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public BasicPowerPunch() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 28;         
        damageUp = 12;

        baseMagicNumber = 6;    
        magicNumberUp = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        act(new ApplyPowerAction(p, p, new VigorPower(p, magicNumber), magicNumber));    
    }
}
package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;

public class Regenerate extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("Regenerate");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs
    private int damageTaken = 0;

    public Regenerate() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseMagicNumber = 2;
        magicNumberUp = 1;
        magicNumber = baseMagicNumber;
        this.exhaust = true;

    }

    public void tookDamage() {
        this.damageTaken ++;
        this.rawDescription = "Heals !M! HP more for each time you lose HP this combat. Heal "+this.damageTaken*this.magicNumber+" HP.";
        this.initializeDescription();
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new HealAction(p, p, this.damageTaken*this.magicNumber));
    }

    public void applyPowers() {
        if (this.damageTaken >= 1) {
            this.rawDescription = "Heals !M! HP more for each time you lose HP this combat. NL Heal "+this.damageTaken*this.magicNumber+" HP. NL Exhaust";
            this.initializeDescription();
        }else {
            this.rawDescription = "Heals !M! HP more for each time you lose HP this combat. NL Heal 0 HP. NL Exhaust.";
            this.initializeDescription();

        }
    }
}
package paparatto.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;
import paparatto.actions.SpoilsAction;

;

public class Spoils extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Spoils");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    ;

    public Spoils() {
        super(ID, COST, TYPE, RARITY, TARGET);

        this.exhaust = true;

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = this.upgradeDescription;
            this.initializeDescription();
        }

    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new SpoilsAction());
    }
}
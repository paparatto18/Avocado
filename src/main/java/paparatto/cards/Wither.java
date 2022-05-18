package paparatto.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import paparatto.Avocado;

/* How to make cards quickly, by Chrono. Sub 5 mins per card if you're fast and have images ready.

1. Copy paste this entire file into a new file called CardName.java
2. Find "cardTemplate" -> Replace "yourCardName"
3. Fill in the values for every line with a comment on the right from the options
4. Change the stats in the stats section
5. in "use()", add in some actions. You can find all the actions in the decompiled game in com/megacrit/cardcrawl/actions
6. Add an entry with the card name to "resources/pits/localization/eng/Avocado-Card-Strings.json"
7. Add two images: "resources/pits/images/cards/cardTemplate.png" and "resources/pits/images/cards/cardTemplate_p.png"
8. Add the card class to Avocado.java in receiveEditCards()

*/

public class Wither extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Wither");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Wither() {
        super(ID, COST, TYPE, RARITY, TARGET);


        // Stats of the card. base is for the original, "Up" is the amount added when it upgrades. You can delete any of these lines as well.

        baseDamage = 8;
        damageUp = 3;
        baseBlock = 0;
        blockUp = 0;
        baseMagicNumber = 2;    // Magic number is used for powers and similar
        magicNumberUp = 1;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
         act(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
         act(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
         act(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
    }
}

// For the game to run, you need 9 cards - one of each rarity (Common, Uncommon, Rare), and each type!
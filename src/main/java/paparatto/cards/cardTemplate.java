package paparatto.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.actions.unique.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.combat.*;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;

import paparatto.Avocado;
import paparatto.cards.*;
import paparatto.patches.*;
import paparatto.powers.*;
import paparatto.actions.*;

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
    
public class cardTemplate extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("cardTemplate");

    private static final CardRarity RARITY = CardRarity.COMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public cardTemplate() {
        super(ID, COST, TYPE, RARITY, TARGET);


        // Stats of the card. base is for the original, "Up" is the amount added when it upgrades. You can delete any of these lines as well.

        baseDamage = 0;         
        damageUp = 0;
        baseBlock = 0;
        blockUp = 0;
        baseMagicNumber = 0;    // Magic number is used for powers and similar
        magicNumberUp = 0;
        newCost = -2;           // As an exception, -2 is "Doesn't upgrade" and anything is else is the FINAL COST, it's not added like the others.

        // End Stats
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // act(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
}

// For the game to run, you need 9 cards - one of each rarity (Common, Uncommon, Rare), and each type!
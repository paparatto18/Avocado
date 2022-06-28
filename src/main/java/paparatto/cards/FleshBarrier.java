package paparatto.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import paparatto.Avocado;

import java.util.Iterator;

public class FleshBarrier extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("FleshBarrier");
    boolean hasProcced = false;


    private static final CardRarity RARITY = CardRarity.COMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 0;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public FleshBarrier() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseBlock = 9;
        blockUp = 3;
        baseMagicNumber = 1;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int plate = 0; if (AbstractDungeon.player.hasPower("Plated Armor")) {
            plate = (AbstractDungeon.player.getPower("Plated Armor").amount);
        }
        int realBaseBlock = this.baseBlock;
        this.baseBlock -= this.magicNumber * plate;
        if (this.baseBlock < 0) {
            this.baseBlock = 0;
        }
        super.applyPowers();
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
        act(new GainBlockAction(p, p, this.block));
    }



    public void applyPowers() {
        int plate = 0; if (AbstractDungeon.player.hasPower("Plated Armor")) {
            plate = (AbstractDungeon.player.getPower("Plated Armor").amount);
        }
        int realBaseBlock = this.baseBlock;
        this.baseBlock -= this.magicNumber * plate;
        if (this.baseBlock < 0) {
            this.baseBlock = 0;
        }
        super.applyPowers();
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }
}
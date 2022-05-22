package paparatto.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;

public class Overguac extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Overguac");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 0;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Overguac() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseMagicNumber = 3;
        magicNumberUp = -1;
        magicNumber = baseMagicNumber;

    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower("Plated Armor")) {
            if (AbstractDungeon.player.getPower("Plated Armor").amount >= this.magicNumber) {
                this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.hasPower("Plated Armor")) {
            if (AbstractDungeon.player.getPower("Plated Armor").amount >= this.magicNumber) {
                act(new ReducePowerAction(p,p, "Plated Armor", this.magicNumber));
                act(new DrawCardAction(p, 3));
            }
        }
    }
}
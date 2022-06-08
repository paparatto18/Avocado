package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import paparatto.Avocado;

;import java.util.Iterator;

public class Irritation extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("Irritation");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    ;

    public Irritation() {
        super(ID, COST, TYPE, RARITY, TARGET);

        newCost = 0;

    }

    public void triggerOnGlowCheck() {
        boolean glow = true;
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.type == CardType.ATTACK) {
                glow = false;
                break;
            }
        }

        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean glow = true;
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.type == CardType.ATTACK) {
                glow = false;
                break;
            }
        }
        if (glow) {
            act(new ApplyPowerAction(p, p, new DoubleDamagePower(p, 2, false), 2));
        }
    }
}
package paparatto.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import paparatto.Avocado;
import paparatto.actions.PeelAction;

public class ShellSmash extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("ShellSmash");

    private static final CardRarity RARITY = CardRarity.RARE; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 0;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public ShellSmash() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.exhaust = true;
        this.baseMagicNumber = 2;
        magicNumber = baseMagicNumber;
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower("Plated Armor")) {
            if (AbstractDungeon.player.getPower("Plated Armor").amount >= 0) {
                this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.hasPower("Plated Armor")) {
            if (AbstractDungeon.player.getPower("Plated Armor").amount >= 1) {
                int count;
                if (this.upgraded) {
                    count = (AbstractDungeon.player.getPower("Plated Armor").amount);
                } else {
                    count = (int) Math.floor(((float)AbstractDungeon.player.getPower("Plated Armor").amount) / 2);
                }
                act(new PeelAction(p, AbstractDungeon.player.getPower("Plated Armor").amount));
                act(new DrawCardAction(count));
                act(new GainEnergyAction(count));
            }
        }
    }
}
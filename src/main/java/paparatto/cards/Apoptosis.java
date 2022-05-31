package paparatto.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import paparatto.Avocado;
import paparatto.actions.PeelAction;

public class Apoptosis extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Apoptosis");

    private static final CardRarity RARITY = CardRarity.COMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 0;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Apoptosis() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseMagicNumber = 1;
        magicNumberUp = 1;
        magicNumber = baseMagicNumber;

    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower("Plated Armor")) {
            if (AbstractDungeon.player.getPower("Plated Armor").amount >= 1) {
                this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.hasPower("Plated Armor")) {
            if (AbstractDungeon.player.getPower("Plated Armor").amount >= 1) {
                act(new PeelAction(p, 1));
                act(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 1), 1));
                if (this.upgraded){
                    if (AbstractDungeon.player.getPower("Plated Armor").amount >= 1) {
                        act(new PeelAction(p, 1));
                        act(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 1), 1));
                    }
                }
                act(new DrawCardAction(p, 1));
            }
        }
    }
}
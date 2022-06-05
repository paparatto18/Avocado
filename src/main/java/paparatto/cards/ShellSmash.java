package paparatto.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
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
        magicNumberUp = -1;
        magicNumber = baseMagicNumber;
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower("Plated Armor")) {
            if (AbstractDungeon.player.getPower("Plated Armor").amount >= 2) {
                this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.hasPower("Plated Armor")) {
            int plate = (int) Math.floor(((float)AbstractDungeon.player.getPower("Plated Armor").amount) / 2);
            for (int i = 0; i < plate ; ++i) {
                act(new PeelAction(p, this.magicNumber));
                act(new DrawCardAction(1));
                act(new GainEnergyAction(1));

            }
        }

    }

    public void applyPowers() {
        if (AbstractDungeon.player.hasPower("Plated Armor")) {
            int plate = (int) Math.floor(((float) AbstractDungeon.player.getPower("Plated Armor").amount) / 2);
            if (plate == 1) {
                this.rawDescription = "Peel !M!, Gain [E] , NL and draw 1 card for each 2 *Plated *Armor you have. (" + plate + " time) NL Exhaust.";

            } else {
                this.rawDescription = "Peel !M!, Gain [E] , NL and draw 1 card for each 2 *Plated *Armor you have. (" + plate + " times) NL Exhaust.";
            }
            this.initializeDescription();
        }else {
            this.rawDescription = "Peel !M!, Gain [E] , NL and draw 1 card for each 2 *Plated *Armor you have. (0 times) NL Exhaust.";
            this.initializeDescription();

        }
    }

}
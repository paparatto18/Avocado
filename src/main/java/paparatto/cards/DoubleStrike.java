package paparatto.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;

public class DoubleStrike extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("DoubleStrike");

    private static final CardRarity RARITY = CardRarity.BASIC; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public DoubleStrike() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 6;
        damageUp = 2;
        tags.add(CardTags.STRIKE);
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
                act(new ReducePowerAction(p,p, "Plated Armor", 1));
                act(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                act(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }
    }
}
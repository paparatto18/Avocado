package paparatto.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.EssenceOfSteel;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import paparatto.Avocado;
import paparatto.actions.PeelAction;

public class Eau extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Eau");

    private static final CardRarity RARITY = CardRarity.RARE; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Eau() {
        super(ID, COST, TYPE, RARITY, TARGET);

        this.exhaust = true;
        newCost = 0;

    }




    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ObtainPotionAction(new EssenceOfSteel()));
        this.addToBot(new VFXAction(p, new VerticalAuraEffect(Avocado.AVOCADO_GREEN.cpy(), p.hb.cX, p.hb.cY), 0.33F));
    }
}
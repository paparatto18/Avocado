package paparatto.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import paparatto.Avocado;
import paparatto.powers.GoorruptionPower;

import java.util.Iterator;

public class Goorruption extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Goorruption");

    private static final CardRarity RARITY = CardRarity.RARE; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.POWER;       // ATTACK, SKILL, POWER

    private static final int COST = 2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Goorruption() {
        super(ID, COST, TYPE, RARITY, TARGET);

        this.newCost = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(p, new VerticalAuraEffect(CardHelper.getColor(110.0f, 112.0f, 66.0f), p.hb.cX, p.hb.cY), 0.33F));
        this.addToBot(new SFXAction("ATTACK_FIRE"));
        this.addToBot(new VFXAction(p, new VerticalAuraEffect(Color.OLIVE, p.hb.cX, p.hb.cY), 0.33F));
        this.addToBot(new VFXAction(p, new VerticalAuraEffect(CardHelper.getColor(110.0f, 112.0f, 66.0f), p.hb.cX, p.hb.cY), 0.0F));
        this.addToBot(new VFXAction(p, new BorderLongFlashEffect(Color.LIME), 0.0F, true));
        boolean powerExists = false;
        Iterator var4 = p.powers.iterator();

        while(var4.hasNext()) {
            AbstractPower pow = (AbstractPower)var4.next();
            if (pow.ID.equals("Goorruption")) {
                powerExists = true;
                break;
            }
        }

        if (!powerExists) {
            this.addToBot(new ApplyPowerAction(p, p, new GoorruptionPower(p)));
        }

    }
}
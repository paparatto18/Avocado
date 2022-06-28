package paparatto.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GiantTextEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import paparatto.Avocado;
import paparatto.actions.ModifyMagicAction;
import paparatto.vfx.TextEffect;

public class Suction extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Suction");

    private static final CardRarity RARITY = CardRarity.RARE; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.ATTACK;       // ATTACK, SKILL, POWER

    private static final int COST = 3;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Suction() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 26;
        damageUp = 6;
        baseMagicNumber = 5;
        magicNumberUp = 2;
        magicNumber = baseMagicNumber;
        this.tags.add(CardTags.HEALING);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY, Avocado.AVOCADO_GREEN.cpy())));
        }
        this.addToBot(new WaitAction(0.8F));
        this.addToBot(new VFXAction(new TextEffect(m.hb.cX, m.hb.cY, "SUCKED", 1.0f)));
        act(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        act(new HealAction(p, p, this.magicNumber));
        act(new ModifyMagicAction(this.uuid, -1));

    }
}
package paparatto.NobCards;

import basemod.helpers.BaseModCardTags;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import paparatto.Avocado;

public class Rush extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("Rush");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 2;

    public Rush() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseDamage = 14;
        damageUp = 4;


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, CardHelper.getColor(171.0f, 57.0f, 73.0f))));
        act(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
}
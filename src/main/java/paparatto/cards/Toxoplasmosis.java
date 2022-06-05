package paparatto.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import paparatto.Avocado;
import paparatto.actions.ToxoplasmosisAction;

public class Toxoplasmosis extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Toxoplasmosis");

    private static final CardRarity RARITY = CardRarity.RARE; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public Toxoplasmosis() {
        super(ID, COST, TYPE, RARITY, TARGET);

      newCost = 1;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ToxoplasmosisAction(p,m));
    }
}
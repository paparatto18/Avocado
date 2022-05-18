package paparatto.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmokeBlurEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import paparatto.Avocado;

public class StunSpore extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("StunSpore");

    private static final CardRarity RARITY = CardRarity.RARE; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.ENEMY;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public StunSpore() {
        super(ID, COST, TYPE, RARITY, TARGET);

      this.isEthereal = true;
      newCost = 1;
      this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int random_int = (int)Math.floor(Math.random()*(100));
        if (random_int <= 75) {
            this.addToBot(new VFXAction(p, new VerticalAuraEffect(Color.YELLOW, m.hb.cX, m.hb.cY), 0.2F));
            act(new StunMonsterAction(m, p));
        } else {
            this.addToBot(new VFXAction(p, new VerticalAuraEffect(Color.LIGHT_GRAY, m.hb.cX, m.hb.cY), 0.2F));
        }
    }
}
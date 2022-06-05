package paparatto.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import paparatto.Avocado;
import paparatto.vfx.TextEffect;

public class BiteChoice extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("BiteChoice");

    private static final CardRarity RARITY = CardRarity.SPECIAL; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final CardColor CARD_COLOR = CardColor.COLORLESS;

    private static final int COST = -2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public BiteChoice() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.cardsToPreview = new Bite();
    }

    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new VFXAction(new BorderFlashEffect(Color.RED, true)));
        this.addToBot(new VFXAction(p, new MiracleEffect(Color.FIREBRICK, Color.RED, "BLOCK_GAIN_1"), 0.1F));
        this.addToBot(new VFXAction(new TextEffect(p.hb.cX, p.hb.cY, "EAT")));
        this.addToBot(new MakeTempCardInHandAction(new Bite(), 1));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

}
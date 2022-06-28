package paparatto.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import paparatto.Avocado;
import paparatto.powers.GrowthPower;
import paparatto.vfx.TextEffect;

public class GrowChoice extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("GrowChoice");

    private static final CardRarity RARITY = CardRarity.SPECIAL; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.POWER;       // ATTACK, SKILL, POWER

    private static final CardColor CARD_COLOR = CardColor.COLORLESS;

    private static final int COST = -2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public GrowChoice() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = 2;
        magicNumberUp = 1;
        magicNumber = baseMagicNumber;

    }

    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new VFXAction(new BorderLongFlashEffect(Color.FIREBRICK.cpy(), true)));
        this.addToBot(new VFXAction(p, new InflameEffect(p), 0.1F));
        this.addToBot(new VFXAction(new TextEffect(p.hb.cX, p.hb.cY, "BIG",1.0f)));
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

}
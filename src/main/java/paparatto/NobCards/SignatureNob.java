package paparatto.NobCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import paparatto.Avocado;

import java.util.Iterator;

public class SignatureNob extends AbstractNobCharacterCard {

    public static final String ID = Avocado.makeID("SignatureNob");

    private static final CardRarity RARITY = CardRarity.UNCOMMON; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.SELF;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.SKILL;       // ATTACK, SKILL, POWER

    private static final int COST = 1;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public SignatureNob() {
        super(ID, COST, TYPE, RARITY, TARGET);

        baseMagicNumber = 3;
        magicNumberUp = 1;
        magicNumber = baseMagicNumber;
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            Iterator var4 = p.hand.group.iterator();

            while(var4.hasNext()) {
                AbstractCard c = (AbstractCard)var4.next();
                if (c.type != CardType.ATTACK && c != this) {
                    canUse = false;
                    this.cantUseMessage = CardCrawlGame.languagePack.getUIString("SignatureMoveMessage").TEXT[0];
                }
            }

            return canUse;
        }
    }

    public void triggerOnGlowCheck() {
        boolean glow = true;
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.type != CardType.ATTACK && c != this) {
                glow = false;
                break;
            }
        }

        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ApplyPowerAction(p,p, new StrengthPower(p, this.magicNumber), this.magicNumber));
    }
}
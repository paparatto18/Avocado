package paparatto.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paparatto.Avocado;

public class HyperParasite extends AbstractAvocadoCharacterCard {

    public static final String ID = Avocado.makeID("Hyperparasite");

    private static final CardRarity RARITY = CardRarity.RARE; // COMMON, UNCOMMON, RARE, SPECIAL
    private static final CardTarget TARGET = CardTarget.NONE;  // ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL
    private static final CardType TYPE = CardType.CURSE;       // ATTACK, SKILL, POWER

    private static final int COST = -2;                          // -1 for X cost, -2 for no cost, 0 and up for regular costs

    public HyperParasite() {
        super(ID, COST, TYPE, RARITY, TARGET);

    }

    public void onObtainCard(AbstractCard card) {
        if (card == this) {
            AbstractDungeon.player.increaseMaxHp(8, true);
        }

    }

    public void onRemoveFromMasterDeck() {
        AbstractDungeon.player.decreaseMaxHealth(3);
        CardCrawlGame.sound.play("BLOOD_SWISH");
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}
package cardWithPower;

public class Card {
    private CardSuits cardSuit;
    private CardRanks cardRank;
    private int cardPower;

    public Card(CardSuits cardSuit, CardRanks cardRank) {
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
        this.cardPower = this.cardRank.getPower() + this.cardSuit.getPower();
    }

    public CardSuits getCardSuit() {
        return cardSuit;
    }

    public CardRanks getCardRank() {
        return cardRank;
    }

    public int getCardPower() {
        return cardPower;
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d", this.cardRank, this.cardSuit, this.cardPower);
    }
}

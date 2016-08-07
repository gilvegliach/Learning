public class FrenchCardFaceValue implements
Comparable<FrenchCardFaceValue>{
	final private int mValue;
	final private Rank mRank;
	public FrenchCardFaceValue(int val, Rank r) {
		mValue = val;
		mRank = r;
	}

	public int getNumericalValue() {
		return mValue;
	}

	public Rank getRank() {
		return mRank;
	}
	
    @Override 
	public int compareTo(FrenchCardFaceValue card) {
		int cmp = mRank.compareTo(card.mRank);
        if (cmp != 0) return cmp;
        return mValue - card.mValue;
    }

    public enum Rank { 
        HEARTS(0), DIAMONDS(1), CLUBS(2), SPADES(3);
        final int mValue;
        Rank(int val) {
         	mValue = val;
        }
    }
}

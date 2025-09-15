
public class AllCards {
	public int x;
	public int f;
    private Card[]cards;
    
    public AllCards() {
    	cards=new Card[0];
    }
    public int kartSayısı() {
    	return cards.length;
    }
    
    public void kartEkle(Card card) {
    	//yeni bir kopya dizi
    	Card[]kopyaCards= new Card[kartSayısı()+1];
    	System.arraycopy(cards, 0, kopyaCards, 0, kartSayısı());
    	kopyaCards[kopyaCards.length-1]= card;  //kopyacard dizisinin son elemanı
    	cards= new Card[kopyaCards.length];
    	System.arraycopy(kopyaCards, 0, cards, 0, kopyaCards.length);
    }
    public Card[] kartlarıAl() {
    	Card[] kopyaCards= new Card[kartSayısı()];
    	for (int k=0; k< kartSayısı();k++) {
    		kopyaCards[k]=cards[k]; //kartları kopyanın içine
    	}
    	cards= new Card[0];
    	return kopyaCards;
    }
    public void kartCikar(int kartNo) {
    	Card[]kopyaCards= new Card[kartSayısı()-1];
    	int kopNO=0, orjNO=0;
    	
    	do {
    		if(orjNO != kartNo) {
    			kopyaCards[kopNO]= cards[orjNO];
    			kopNO++;
    		}
    		orjNO++;
    		
    	}while(kopNO<kopyaCards.length);
    	cards = new Card[kopyaCards.length];
    	System.arraycopy(kopyaCards, 0, cards, 0, kopyaCards.length);
    }
    public Card kartAl() {
    	if(kartSayısı()>=0) {
    		Card card= cards[kartSayısı()-1];
    		kartCikar(kartSayısı()-1); //desteden eksilt
    			return card;
    	}else {
    		System.out.println("Kart yok");
    		return null;
    	}
    }
    
    public Card kartGoster(int no) {
    	return cards[no];
    }
    public void karistir() {
    	int i;
    	for(int k=0;k<cards.length;k++) {
    		i=(int)(Math.random()*cards.length);   //0-52 arasında rastgele bir index
    		Card temp=cards[k];
    		cards[k]= cards[i];
    		cards[i]= temp;
    	}
    }
    public void isimBuyuktenKucuge() {
		for(int k=0;k< kartSayısı();k++) {
			for(int i=0;i<kartSayısı();i++) {
				if(cards[k].getNo()%13>cards[i].getNo()%13) { //so that K>Q
					Card temp= cards[k];
					cards[k]= cards[i];
					cards[i]=temp;
					
				}
			}
		}
	}
    public int findBiggestCardNo() {
        int biggestCardNo = cards[0].getNo() % 13;

        for (int k = 1; k < kartSayısı(); k++) {
            int currentCardNo = cards[k].getNo() % 13;

            if (currentCardNo > biggestCardNo) {
                biggestCardNo = currentCardNo;
            }
        }

        return biggestCardNo;
    }
    
    public boolean pair()
	{
    	isimBuyuktenKucuge();
		int check = 0;
		for(int counter = 1; counter < 5; counter++)
		{
			if (cards[counter - 1].getNo() == cards[counter].getNo())
			{
				x=cards[counter].getNo();
				check++;
			}
		}
		if (check == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
    public boolean twoPairs()
	{   
    	isimBuyuktenKucuge();
		int check = 0;
		for(int counter = 1; counter < 5; counter++)
		{
			if (cards[counter - 1].getNo() == cards[counter].getNo())
			{
				check++;
			}
		}
		if (check == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
    public boolean triple()
	{
    	isimBuyuktenKucuge();
		if (cards[0].getNo() == cards[2].getNo() || cards[2].getNo() == cards[4].getNo())
		{
			return true;
		}
		return false;
	}
    public boolean straight()
	{
    	isimBuyuktenKucuge();
		for (int counter2 = 1; counter2 < 5; counter2++)
		{
			if (cards[counter2 - 1].getNo() != (cards[counter2].getNo() - 1))
			{
				return false;
			}
				
		}
		return true;
	}
    public boolean flush()
	{
    	isimBuyuktenKucuge();
		for (int counter = 1; counter < 5; counter++)
		{
			if (cards[0].getSeri() != cards[counter].getSeri())
			{
				return false;
			}
		}
		return true;
	}
    public boolean fullHouse()
	{
    	isimBuyuktenKucuge();
		int comparison = 0;
		for (int counter = 1; counter < 5; counter++)
		{
			if (cards[counter - 1].getNo() == cards[counter].getNo())
			{
				f=cards[counter].getNo();
				comparison++;
			}
		}
		if (comparison == 3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
    public boolean fourOfaKind()
	{
    	isimBuyuktenKucuge();
		if (cards[0].getNo() != cards[3].getNo() && cards[1].getNo() != cards[4].getNo())
		{
			return false;
		}
		else
		{
			
			return true;
		}
	}
    public boolean straightFlush()
	{
    	isimBuyuktenKucuge();
		for (int counter = 1; counter < 5; counter++)
		{
			if (cards[0].getSeri() != cards[counter].getSeri())
			{
				return false;
			}
		}
		for (int counter2 = 1; counter2 < 5; counter2++)
		{
			if (cards[counter2 - 1].getNo() != (cards[counter2].getNo() - 1))
			{
				return false;
			}
				
		}
		return true;
		
	}
    public boolean royalFlush()
	{
    	isimBuyuktenKucuge();
		if (cards[0].getNo() == 1 && cards[1].getNo() == 10 && cards[2].getNo() == 11 &&
				cards[3].getNo() == 12 && cards[4].getNo() == 13)
		{
			return true;
		}
		else
		{
			return false;
		}
	}



}

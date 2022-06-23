import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class BlackJack {

    public static void buildDeck(ArrayList<Card> deck) {
      //clears deck before new game
      deck.clear();

      String[] suit = {"Hearts", "Spades", "Clubs", "Diamonds"};

      /*creates 4 Ace cards;
      each Ace is set to a default value of 11 since max possible benefit*/
      for(int a = 0; a < 4; a++){
        deck.add(new Card("Ace", suit[a], 11));
      }
      //creates 4 of each number card from 2 to 10
      for(int i = 2; i < 11; i++){
        for(int j = 0; j < 4; j++){
          deck.add(new Card("" + i, suit[j], i));
        }
      }
      //creates 4 of each face card
      String[] faces = {"Jack", "Queen", "King"};
      for(int k = 0; k < faces.length; k++){
        for(int l = 0; l < 4; l++){
          deck.add(new Card(faces[k], suit[l], 10));
        }
      }
    }

    public static void initialDeal(ArrayList<Card> deck, ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
      //clears both hands before new game starts
      dealerHand.clear();
      playerHand.clear();

      //calls dealOne() method twice for each hand
      for(int i = 0; i < 2; i++){
        dealOne(deck, playerHand);
        dealOne(deck, dealerHand);
      }
    }

    public static void dealOne(ArrayList<Card> deck, ArrayList<Card> hand){
      /*the random cards which are added to the hand are also removed from the deck
      so that there are no repeats*/
      hand.add(deck.remove((int)(Math.random() * deck.size())));
    }

    public static boolean checkBust(ArrayList<Card> hand){
      //hand is bust if its value is over 21
      if(calcSum(hand) > 21)
        return true;
      else
        return false;
    }

    public static boolean dealerTurn(ArrayList<Card> deck, ArrayList<Card> hand){
      //if the dealer's current hand is < 17, they are dealt one more card
      if(calcSum(hand) < 17){
        dealOne(deck, hand);
      }

      //checks if dealer's hand is bust after potential additional card
      return checkBust(hand);
    }

    public static int whoWins(ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
      //if the playerhand sum is greater than the dealerhand, the player wins
      if(calcSum(playerHand) > calcSum(dealerHand))
        return 1;
      else
        return 2;
    }

    public static String displayCard(ArrayList<Card> hand){
      //displays type and suit of the second card
      return hand.get(1).getType() + "-" + hand.get(1).getSuit();
    }

    public static String displayHand(ArrayList<Card> hand){
      //displays all the cards in a hand
      String h = "";
      for(int i = 0; i < hand.size(); i++){
        //if it's not the last card in the hand, add a comma after the card description
        if(i != hand.size() - 1)
          h += hand.get(i).getType() + "-" + hand.get(i).getSuit() + ", ";
        //the last card in the hand doesn't have a comma after its description
        else
          h += hand.get(i).getType() + "-" + hand.get(i).getSuit();
      }
      return h;
    }

    public static int calcSum(ArrayList<Card> hand){
      //calculates the total sum of the values in a given hand

      int totalVal = 0;
      ArrayList<Integer> index = new ArrayList<Integer>();

      for(int i = 0; i < hand.size(); i++){
        /*if the card is an ace, add it to ArrayList index and calculate
        the value of the ace later after summing up all the number and face cards*/
        if(hand.get(i).getType().equals("Ace"))
          index.add(i);
        /*if the card is NOT an Ace, add up the number or
        face card value to the total value of the hand now*/
        else
          totalVal += hand.get(i).getVal();
      }
      /*determine value of ace (1 or 11) and increase totalVal by either 1 or 11
      (after all number and face cards have been considered)*/
      for(int j = 0; j < index.size(); j++){
        if((11 + totalVal) > 21){
          //sets the value of the ace to 1 if 11 is not the most beneficial value
          hand.get(index.get(j)).setAceVal();
        }
        totalVal += hand.get(index.get(j)).getVal();
      }

      return totalVal;
    }


    public static void main(String[] args) {

		int playerChoice, winner;
		ArrayList<Card> deck = new ArrayList<Card>();


		playerChoice = JOptionPane.showConfirmDialog(
			null,
			"Ready to Play Blackjack?",
			"Blackjack",
			JOptionPane.OK_CANCEL_OPTION
		);

		if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
		    System.exit(0);

		Object[] options = {"Hit","Stand"};
		boolean isBusted;	// Player busts?
		boolean dealerBusted;
		boolean isPlayerTurn;
		ArrayList<Card> playerHand = new ArrayList<>();
		ArrayList<Card> dealerHand = new ArrayList<>();

		do{ // Game loop
			buildDeck(deck);  // Initializes the deck for a new game
		    initialDeal(deck, playerHand, dealerHand);
		    isPlayerTurn=true;
		    isBusted=false;
		    dealerBusted=false;

		    while(isPlayerTurn){

				// Shows the hand and prompts player to hit or stand
				playerChoice = JOptionPane.showOptionDialog(
					null,
					"Dealer shows " + displayCard(dealerHand) + "\n Your hand is: "
						+ displayHand(playerHand) + "\n What do you want to do?",
					"Hit or Stand",
				   JOptionPane.YES_NO_OPTION,
				   JOptionPane.QUESTION_MESSAGE,
				   null,
				   options,
				   options[0]
				);

				if(playerChoice == JOptionPane.CLOSED_OPTION)
				    System.exit(0);

				else if(playerChoice == JOptionPane.YES_OPTION){
				    dealOne(deck, playerHand);
				    isBusted = checkBust(playerHand);
				    if(isBusted){
						// Case: Player Busts
						playerChoice = JOptionPane.showConfirmDialog(
							null,
							"Player has busted!",
							"You lose",
							JOptionPane.OK_CANCEL_OPTION
						);

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);

						isPlayerTurn=false;
				    }
				}

				else{
				    isPlayerTurn=false;
				}
		    }

		    if(!isBusted){ // Continues if player hasn't busted
				dealerBusted = dealerTurn(deck, dealerHand);
				if(dealerBusted){ // Case: Dealer Busts
				    playerChoice = JOptionPane.showConfirmDialog(
				    	null,
				    	"The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: "
				    		+ displayHand(playerHand) + "\nThe dealer busted.\n Congrautions!",
				    	"You Win!!!",
				    	JOptionPane.OK_CANCEL_OPTION
				    );

					if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						System.exit(0);
				}


				else{ //The Dealer did not bust.  The winner must be determined
				    winner = whoWins(playerHand, dealerHand);

				    if(winner == 1){ //Player Wins
						playerChoice = JOptionPane.showConfirmDialog(
							null,
							"The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: "
								+ displayHand(playerHand) + "\n Congratulations!",
							"You Win!!!",
							JOptionPane.OK_CANCEL_OPTION
						);

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
				    }

				    else{ //Player Loses
						playerChoice = JOptionPane.showConfirmDialog(
							null,
							"The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: "
								+ displayHand(playerHand) + "\n Better luck next time!",
							"You lose!!!",
							JOptionPane.OK_CANCEL_OPTION
						);

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
				    }
				}
		    }
		}while(true);
    }
}



class Card {

  private String type; //the general card type (Ace, King, 1, etc.)
  private String suit; //the suit of the cards (clubs, spades, etc.)
  private int val; //The card's actual value (1 or 11, 10, 1, etc.)

	Card(String type, String suit, int val){
    this.type = type;
    this.suit = suit;
    this.val = val;
	}

  public String getType(){
    return type;
  }

  public int getVal(){
    return val;
  }

  public String getSuit(){
    return suit;
  }

  public void setAceVal(){
//sets the value of an Ace from its default value of 11 to 1
    val = 1;
  }
}
